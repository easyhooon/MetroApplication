# Metro Compiler Bug Report: IrErrorTypeImpl Cast Exception

## 🐛 Bug Summary

Metro compiler crashes with `ClassCastException` when trying to report a missing binding, making it impossible to identify what dependency is actually missing.

## 💥 Error Output

```
e: java.lang.AssertionError: Code gen exception while processing com/..../di/AppGraph.
class org.jetbrains.kotlin.ir.types.impl.IrErrorTypeImpl cannot be cast to class org.jetbrains.kotlin.ir.types.IrSimpleType

Caused by: java.lang.ClassCastException:
class org.jetbrains.kotlin.ir.types.impl.IrErrorTypeImpl cannot be cast to class org.jetbrains.kotlin.ir.types.IrSimpleType
	at org.jetbrains.kotlin.ir.types.IrTypeSystemContext$DefaultImpls.withNullability(IrTypeSystemContext.kt:85)
	...
	at dev.zacsweers.metro.compiler.ir.graph.IrBindingGraph.findSimilarBindings(IrBindingGraph.kt:519)
	at dev.zacsweers.metro.compiler.ir.graph.IrBindingGraph.realGraph$lambda$2(IrBindingGraph.kt:93)
	at dev.zacsweers.metro.compiler.graph.MutableBindingGraph.reportMissingBinding(BindingGraph.kt:457)
```

## 🔍 Root Cause Analysis

1. Metro detects a **missing binding** in the dependency graph
2. Metro calls `reportMissingBinding()` to generate a helpful error message
3. `reportMissingBinding()` calls `findSimilarBindings()` to suggest alternatives
4. `findSimilarBindings()` performs type checking with `isSubtypeOf()`
5. **The unresolved binding has type `IrErrorTypeImpl`**
6. Type checker tries to cast `IrErrorTypeImpl` to `IrSimpleType` → **💥 CRASH**
7. **User never sees what binding is actually missing**

## 📦 Minimal Reproduction

I've created a minimal reproduction case. The bug is triggered by this dependency chain:

```kotlin
// In core/data/impl/repository/DefaultUserRepository.kt
@SingleIn(DataScope::class)
@Inject
class DefaultUserRepository(
    private val notificationDataSource: NotificationDataSource, // ← Triggers bug
) : UserRepository

// In core/datastore/impl/datasource/DefaultNotificationDataSource.kt
@SingleIn(DataScope::class)
@Inject
class DefaultNotificationDataSource(
    @NotificationDataStore private val dataStore: DataStore<Preferences>,
) : NotificationDataSource

// In core/datastore/impl/di/DataStoreGraph.kt
@ContributesTo(DataScope::class)
interface DataStoreGraph {
    @NotificationDataStore
    @Provides
    fun provideNotificationDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = context.notificationDataStore

    @Binds
    val DefaultNotificationDataSource.bind: NotificationDataSource
}
```

### Test Results

| Scenario | Result |
|----------|--------|
| Repository without DataSource injection | ✅ Builds successfully |
| Repository with NotificationDataSource injection | ❌ Metro crashes |

**Key Finding**: Even though all bindings appear to be properly configured with `@Inject`, `@SingleIn`, `@Binds`, and `@Provides`, Metro still crashes without revealing what's actually missing.

## 🔧 Environment

- **Metro**: 0.7.6
- **Kotlin**: 2.2.21
- **KSP**: 2.3.0
- **AGP**: 8.9.3

## 📝 Expected Behavior

Metro should print a clear error message like:
```
Error: Missing binding for [ACTUAL_TYPE] in [SCOPE]
Required by: DefaultUserRepository
Suggestion: Did you forget to @Provide [SIMILAR_TYPE]?
```

## 🐞 Actual Behavior

Metro crashes with:
```
ClassCastException: IrErrorTypeImpl cannot be cast to IrSimpleType
```

## 💡 Impact

This bug makes debugging dependency injection issues extremely difficult because:
1. The actual missing binding is never reported
2. Users must manually bisect their code to find the problem
3. Hours can be wasted tracking down a simple missing `@Provides` annotation

## 🔗 Reproduction Repository

[Link to minimal reproduction project will be provided]

## 🛠️ Suggested Fix

In `IrBindingGraph.kt:519` (`findSimilarBindings`), add a type check before casting:

```kotlin
// Before type checking, verify we don't have IrErrorType
if (candidateType is IrErrorTypeImpl) {
    // Skip similarity check for unresolved types
    continue
}
```

This would allow Metro to at least print "Missing binding for X" instead of crashing.

---

**Is this a known issue?** Should I open a separate issue or is there already a fix in progress?
