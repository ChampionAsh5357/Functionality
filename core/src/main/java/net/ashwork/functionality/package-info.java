/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/**
 * Functionality is a project recreating Java functional interfaces to provide
 * more detailed and partial expressions. Each functional interface has a single
 * abstract method, called the <i>functional method</i> for that functional interface,
 * to which the lambda expression's parameter and return types are matched or adapted.
 * Functional interfaces can provide a target type in multiple contexts, such as
 * assignment, method invocation, or cast context:
 *
 * <pre>
 *     // Assignment Context
 *     Function1&lt;String, byte[]&gt; f = String::getBytes;
 *
 *     // Method Invocation Context
 *     byteToIntFunction.andThen(r -&gt; r &lt; 30);
 *
 *     // Cast Context
 *     byteToIntFunction.andThen(((IntFunction1&lt;Boolean&gt;) r -&gt; r &lt; 30).boxInput());
 * </pre>
 *
 * <p>The interfaces in this package are supposed to represent the core of all
 * functions as all functional interfaces in this package are connected. They
 * are meant to take partial points of existing functions and generalize them
 * such that they can be referenced separately from the actual interface itself.
 *
 * <p>The interfaces in this package that are annotated with {@link java.lang.FunctionalInterface}
 * are meant as merely an aid to capture design intent and enlist the help of the compiler
 * in identifying accidental violations of design intent. The interfaces in this package
 * that are annotated with {@link net.ashwork.functionality.util.InheritOnly} are meant as an aid to capture that
 * the interface should only be inherited and never used directly as a functional interface.
 *
 * <p>This package holds all basic {@code Functions} (from some input(s) T1, T2, ... to some output R).
 * Some functional interfaces are specialized such that the type parameters are
 * primitives with additional type prefixes. For those that return a primitive value,
 * the interface is prefixed with {@code ToX} where {@code X} is the primitive type.
 * These schemes can be combined, as in {@link net.ashwork.functionality.primitive.combined.ByteToLongFunction1}.
 */
package net.ashwork.functionality;