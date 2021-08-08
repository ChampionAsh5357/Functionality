# Functionality

Functionality is a project recreating Java functional interfaces to provide more detailed and partial expressions. Each functional interface has a single abstract method, called the <i>functional method</i> for that functional interface, to which the lambda expression's parameter and return types are matched or adapted.vFunctional interfaces can provide a target type in multiple contexts, such as assignment, method invocation, or cast context:

```java
// Assignment Context
Function1<String, byte[]> f = String::getBytes;

// Method Invocation Context
byteToIntFunction.andThen(r -> r < 30);

// Cast Context
byteToIntFunction.andThen(((IntFunction1<Boolean>) r -> r < 30).boxInput());
```

The interfaces in this package are supposed to represent the core of all functions as all functional interfaces in this package are connected. They are meant to take partial points of existing functions and generalize them
such that they can be referenced separately from the actual interface itself.

The interfaces in this package that are annotated with `@FunctionalInterface` are meant as merely an aid to capture design intent and enlist the help of the compiler in identifying accidental violations of design intent. The interfaces in this package that are annotated with `@InheritOnly` are meant as an aid to capture that the interface should only be inherited and never used directly as a functional interface.

The functions in this package are currently broken into the following sub-projects:

Project Name | Description
:---: | :---
**Functionality** | Represents functions (from some input(s) T1, T2, ... to some output R)
**Consumability** | Represents consumers (from some input(s) T1, T2, ... to no output)
**Operating** | Represents operators (from some input(s) of the same type to some output of the same type)
**Predicating** | Represents predicates (from some input(s) T1, T2, ... to some boolean primitive)
**Throwability** | Represents functions, consumers, operators, and predicates that may or may not throw an exception

Some functional interfaces are specialized such that the type parameters are primitives with additional type prefixes. For those that return a primitive value, the interface is prefixed with `ToX` where `X` is the primitive type. These schemes can be combined, as in `ByteToLongFunction1`.

## License

The entire project is licensed under Mozilla Public License 2.0. This does allow use in commercial, patent, and private use provided the source is disclosed under the same license and copyright. This project does not allow use of trademarks held by ChampionAsh5357 and provides no warranty or liability for any issues that may occur by using this project.