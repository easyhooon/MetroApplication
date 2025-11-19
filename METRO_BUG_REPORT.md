# Metro Compiler Crash: IrErrorTypeImpl Cast Exception

## 🐛 Bug Description

Metro compiler crashes with `ClassCastException` when trying to report a missing binding in the dependency graph.

## 💥 Error Message

```
e: java.lang.AssertionError: Code gen exception while processing com/easyhooon/metroapplication/di/AppGraph.
class org.jetbrains.kotlin.ir.types.impl.IrErrorTypeImpl cannot be cast to class org.jetbrains.kotlin.ir.types.IrSimpleType

Caused by: java.lang.ClassCastException: class org.jetbrains.kotlin.ir.types.impl.IrErrorTypeImpl cannot be cast to class org.jetbrains.kotlin.ir.types.IrSimpleType
	at org.jetbrains.kotlin.ir.types.IrTypeSystemContext$DefaultImpls.withNullability(IrTypeSystemContext.kt:85)
	...
	at dev.zacsweers.metro.compiler.ir.graph.IrBindingGraph.findSimilarBindings(IrBindingGraph.kt:519)
	at dev.zacsweers.metro.compiler.ir.graph.IrBindingGraph.realGraph$lambda$2(IrBindingGraph.kt:93)
	at dev.zacsweers.metro.compiler.graph.MutableBindingGraph.reportMissingBinding(BindingGraph.kt:457)
```

## 🔍 Root Cause

1. Metro detects a **missing binding** somewhere in the dependency graph
2. Metro tries to call `reportMissingBinding()` to generate a helpful error message
3. During error reporting, Metro calls `findSimilarBindings()` to suggest alternatives
4. `findSimilarBindings()` performs type checking using `isSubtypeOf()`
5. **The missing binding has an `IrErrorType`** (because it's unresolved)
6. Type checker tries to cast `IrErrorTypeImpl` to `IrSimpleType` and crashes
7. **Result**: User never sees what binding is actually missing

## 📦 Reproduction Scenario

### Exact Trigger

**The bug is triggered by injecting `NotificationDataSource` into `DefaultUserRepository`**, regardless of whether the Service injects the Repository or not.

### Test Results

| Scenario | Result |
|----------|--------|
| Service → Repository (no DataSource) | ✅ Builds successfully |
| Repository → NotificationDataSource (with Service) | ❌ Metro crashes |
| Repository → NotificationDataSource (without Service) | ❌ Metro crashes |

**Conclusion**: The issue is specifically with `NotificationDataSource` dependency chain, not with cross-scope injection.

### Project Structure
```
app (AppScope)
├── MetroFirebaseMessagingService (@ContributesIntoMap)
│   └── injects: UserRepository (DataScope)  // Optional - not the cause
│
core:data:impl (DataScope)
├── DefaultUserRepository (@Inject, @SingleIn)
│   └── injects: NotificationDataSource  // ← THIS TRIGGERS THE BUG
│
core:datastore:impl (DataScope)
└── DefaultNotificationDataSource (@Inject, @SingleIn)
    └── injects: @NotificationDataStore DataStore<Preferences>
         └─ DataStoreGraph.provideNotificationDataStore() (@Provides)
```

### Trigger Conditions

1. **Cross-scope injection**: Service (AppScope) → Repository (DataScope)
2. **Deep dependency chain**: Service → Repository → DataSource → Qualified DataStore
3. **Missing binding exists** somewhere in the chain
4. **Qualifiers are used** on injected types

## 🎯 Key Finding

The **actual missing binding** cannot be identified because Metro crashes before printing it. This is a **Metro error reporting bug**, not a user configuration error.

## 🔧 Affected Versions

- Metro: 0.7.6
- Kotlin: 2.2.21
- KSP: 2.3.0
- Circuit: 0.30.0

## 📝 Expected Behavior

Metro should print:
```
Error: Missing binding for [TYPE] in [SCOPE]
Did you mean: [SIMILAR_TYPE]?
```

## 🐞 Actual Behavior

Metro crashes with:
```
ClassCastException: IrErrorTypeImpl cannot be cast to IrSimpleType
```

## 💡 Workaround

Manually remove dependencies one-by-one until the build succeeds, then identify which binding was missing.

## 🔗 Minimal Reproduction

This repository contains a minimal reproduction case demonstrating the bug.
