/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.primitive.longs;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.Function2;
import net.ashwork.functionality.partial.UnboxedResult;
import net.ashwork.functionality.partial.Variant;

import java.util.function.ToLongBiFunction;

/**
 * Represents a function that accepts two arguments and produces a {@code long}-valued result.
 * This is the two-arity specialization of {@link ToLongFunctionN}.
 * This is the {@code long}-producing primitive specialization of {@link Function2}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsLong(Object, Object)}.
 *
 * @param <T1> the type of the input to the function
 * @param <T2> the type of the second argument to the function
 *
 * @see Function2
 * @see ToLongFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface ToLongFunction2<T1, T2> extends ToLongFunctionN, UnboxedResult<Function2<T1, T2, Long>>, Variant<ToLongBiFunction<T1, T2>> {

    /**
     * Applies this function to the given arguments.
     *
     * @param t1 the first function argument
     * @param t2 the second function argument
     * @return the function result
     */
    long applyAsLong(final T1 t1, final T2 t2);

    @SuppressWarnings("unchecked")
    @Override
    default long applyAllAsLongUnchecked(final Object... args) {
        return this.applyAsLong((T1) args[0], (T2) args[1]);
    }

    @Override
    default int arity() {
        return 2;
    }

    /**
     * Creates an instance of this object from its {@link ToLongBiFunction} variant.
     *
     * @param function the variant of this object
     * @param <T1> the type of the input to the function
     * @param <T2> the type of the second argument to the function
     * @return an instance of this object
     *
     * @see ToLongBiFunction
     */
    static <T1, T2> ToLongFunction2<T1, T2> fromVariant(final ToLongBiFunction<T1, T2> function) {
        return function::applyAsLong;
    }

    @Override
    default ToLongBiFunction<T1, T2> toVariant() {
        return this::applyAsLong;
    }

    /**
     * @see Function2
     */
    @Override
    default Function2<T1, T2, Long> boxResult() {
        return this::applyAsLong;
    }

    /**
     * @see Function2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Function2<T1, T2, V> andThen(final Function1<? super Long, ? extends V> after) {
        return (Function2<T1, T2, V>) ToLongFunctionN.super.andThen(after);
    }

    /**
     * @see Function2
     */
    @Override
    default <V> Function2<T1, T2, V> andThenUnchecked(final Function1<? super Long, ? extends V> after) {
        return (final T1 t1, final T2 t2) -> after.apply(this.applyAsLong(t1, t2));
    }
}
