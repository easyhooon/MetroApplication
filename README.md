# Metro Compiler Bug Reproduction

This is a minimal reproduction project for Metro DI compiler crash issue.

## Issue Description

Metro compiler crashes with `IrErrorTypeImpl cannot be cast to IrSimpleType` error when:
- A component in `AppScope` depends on a `Repository` from `DataScope`
- The `Repository` depends on a `DataSource` that uses qualified `DataStore<Preferences>`

## Environment

- Metro: 0.7.6
- Kotlin: 2.2.21
- AGP: 8.9.3
- KSP: 2.3.0

## Reproduction Steps

### 1. Current State (BUILD SUCCESSFUL ✅)

The project currently builds successfully because `NotificationDataSource` injection is commented out:

```kotlin
// core/data/impl/repository/DefaultUserRepository.kt
@SingleIn(DataScope::class)
@Inject
class DefaultUserRepository(
    // Uncomment this line to reproduce Metro bug:
    // private val notificationDataSource: NotificationDataSource,
) : UserRepository
```

**Current dependency graph (works fine):**
```
MainActivity → MainPresenter → UserRepository ✅
TestService → UserRepository ✅
```

**This proves that:**
- Cross-scope injection (AppScope → DataScope) works fine
- Repository injection into Presenter/Service works fine
- The bug is NOT related to the binding method itself

Run build:
```bash
./gradlew clean :app:compileDebugKotlin
# Result: BUILD SUCCESSFUL ✅
```

### 2. Reproduce the Bug (BUILD FAILED ❌)

Uncomment the `notificationDataSource` dependency in `DefaultUserRepository.kt`:

```kotlin
@SingleIn(DataScope::class)
@Inject
class DefaultUserRepository(
    private val notificationDataSource: NotificationDataSource,  // Uncomment this line
) : UserRepository
```

Run build:
```bash
./gradlew clean :app:compileDebugKotlin
```

**Result: Metro compiler crashes with:**
```
e: java.lang.AssertionError: Code gen exception while processing com/easyhooon/metroapplication/di/AppGraph.
class org.jetbrains.kotlin.ir.types.impl.IrErrorTypeImpl cannot be cast to class org.jetbrains.kotlin.ir.types.IrSimpleType

Caused by: java.lang.ClassCastException: class org.jetbrains.kotlin.ir.types.impl.IrErrorTypeImpl cannot be cast to class org.jetbrains.kotlin.ir.types.IrSimpleType
	at dev.zacsweers.metro.compiler.ir.graph.IrBindingGraph.findSimilarBindings(IrBindingGraph.kt:519)
	at dev.zacsweers.metro.compiler.graph.MutableBindingGraph.reportMissingBinding(BindingGraph.kt:457)
```

## Dependency Graph

```
AppScope:
  TestService (@Inject, @SingleIn(AppScope))
    └─ UserRepository (interface from DataScope)
         └─ DefaultUserRepository (@Inject, @SingleIn(DataScope))
              └─ NotificationDataSource (interface) ← TRIGGERS BUG
                   └─ DefaultNotificationDataSource (@Inject, @SingleIn(DataScope))
                        └─ @NotificationDataStore DataStore<Preferences>
                             └─ DataStoreGraph.provideNotificationDataStore()
```

## Error Analysis

The Metro compiler crashes with the following error:

```
ClassCastException: class IrErrorTypeImpl cannot be cast to class IrSimpleType
	at IrBindingGraph.findSimilarBindings(IrBindingGraph.kt:519)
	at MutableBindingGraph.reportMissingBinding(BindingGraph.kt:457)
```

**Confirmed behavior:**
1. Metro detects an issue in the dependency graph
2. Attempts to call `reportMissingBinding()` to generate an error message
3. Calls `findSimilarBindings()` to suggest alternatives
4. **Crashes during type checking before reporting the actual issue**

**Unable to determine:**
- The actual root cause (Metro crashes before reporting it)
- Whether it's a missing binding or a different issue
- The exact component that causes the problem

## Key Files

- **App Module**:
  - `app/di/AppGraph.kt` - Main dependency graph
  - `app/MainActivity.kt` - Activity that injects MainPresenter
  - `app/MainPresenter.kt` - Presenter that injects UserRepository (proves Repository injection works)
  - `app/TestService.kt` - Service that injects UserRepository

- **Data Layer**:
  - `core/data/impl/repository/DefaultUserRepository.kt` - **Toggle this to reproduce bug**
  - `core/data/api/repository/UserRepository.kt` - Repository interface

- **DataStore Layer**:
  - `core/datastore/impl/datasource/DefaultNotificationDataSource.kt` - DataSource with qualified DataStore
  - `core/datastore/impl/di/DataStoreGraph.kt` - Provides qualified DataStore
  - `core/datastore/impl/di/DataStoreQualifiers.kt` - `@NotificationDataStore` qualifier

## Related Issues

- Metro Issue: https://github.com/ZacSweers/metro/discussions/1358
- Original project where bug was discovered: [Reed-Android](https://github.com/YAPP-Github/Reed-Android)

## Notes

- Metro compiler crashes during error reporting, preventing identification of the actual issue
- The crash occurs in `IrBindingGraph.findSimilarBindings()` when trying to suggest alternatives
- Repository injection works fine (proven by Presenter and Service injection)
- The bug is specifically triggered when Repository depends on NotificationDataSource
- Unable to determine the root cause due to the compiler crash
