/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/**
 * This package holds all basic {@code Functions} (from some input(s) T1, T2, ... to some output R),
 * {@code Consumers} (from some input(s) T1, T2, ... to no output), {@code Operators} (from some input(s)
 * of the same type to an output of the same type), and {@code Predicates} (from some input(s) T1, T2, ...
 * to some boolean primitive) where a throwable may be thrown. Some functional interfaces are specialized
 * such that the type parameters are primitives with additional type prefixes. For those that return a primitive
 * value, the interface is prefixed with {@code ToX} where {@code X} is the primitive type.
 * These schemes can be combined, as in {@link net.ashwork.functionality.primitive.combined.ByteToLongFunction1}.
 */
package net.ashwork.functionality.throwable;