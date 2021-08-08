/*
 * Aritiality (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.arity.function;

import java.util.Objects;
import java.util.function.Function;

/**
 * Represents a function that accepts six arguments and produces a result.
 * This is the six-arity specialization of {@link Function}.
 *
 * <p>This is a functional interface whose functional method is
 * {@link #apply(Object, Object, Object, Object, Object, Object)}.
 *
 * @param <T1> The type of the first argument to the function
 * @param <T2> The type of the second argument to the function
 * @param <T3> The type of the third argument to the function
 * @param <T4> The type of the fourth argument to the function
 * @param <T5> The type of the fifth argument to the function
 * @param <T6> The type of the sixth argument to the function
 * @param <R> The type of the result of the function
 *
 * @see Function
 * @since 2.0.0
 */
@FunctionalInterface
public interface Function6<T1, T2, T3, T4, T5, T6, R> {

    /**
     * Applies this function to the given arguments.
     *
     * @param t1 The first function argument
     * @param t2 The second function argument
     * @param t3 The third function argument
     * @param t4 The fourth function argument
     * @param t5 The fifth function argument
     * @param t6 The sixth function argument
     * @return The function result
     */
    R apply(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6);

    /**
     * Returns a composed function that first applies this function to
     * its input, and then applies the {@code after} function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <V> The type of output of the {@code after} function, and of the
     *            composed function
     * @param after The function to apply after this function is applied
     * @return A composed function that first applies this function and then
     *         applies the {@code after} function
     * @throws NullPointerException If {@code after} is null
     */
    default <V> Function6<T1, T2, T3, T4, T5, T6, V> andThen(final Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after, "The applied function cannot be null.");
        return (final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6)
                -> after.apply(this.apply(t1, t2, t3, t4, t5, t6));
    }
}