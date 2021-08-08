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
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedResult;
import net.ashwork.functionality.partial.Variant;

import java.util.function.ToLongFunction;

/**
 * Represents a function that accepts one argument and produces a {@code long}-valued result.
 * This is the one-arity specialization of {@link ToLongFunctionN}.
 * This is the {@code long}-producing primitive specialization of {@link Function1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsLong(Object)}.
 *
 * @param <T1> the type of the input to the function
 *
 * @see Function1
 * @see ToLongFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface ToLongFunction1<T1> extends ToLongFunctionN, InputChainableInput<T1>, UnboxedResult<Function1<T1, Long>>, Variant<ToLongFunction<T1>> {

    /**
     * Applies this function to the given argument.
     *
     * @param t1 the function argument
     * @return the function result
     */
    long applyAsLong(final T1 t1);

    @SuppressWarnings("unchecked")
    @Override
    default long applyAllAsLongUnchecked(final Object... args) {
        return this.applyAsLong((T1) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * Creates an instance of this object from its {@link ToLongFunction} variant.
     *
     * @param function the variant of this object
     * @param <T1> the type of the input to the function
     * @return an instance of this object
     *
     * @see ToLongFunction
     */
    static <T1> ToLongFunction1<T1> fromVariant(final ToLongFunction<T1> function) {
        return function::applyAsLong;
    }

    @Override
    default ToLongFunction<T1> toVariant() {
        return this::applyAsLong;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<T1, Long> boxResult() {
        return this::applyAsLong;
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> ToLongFunction1<V> compose(final Function1<? super V, ? extends T1> before) {
        return (ToLongFunction1<V>) InputChainableInput.super.compose(before);
    }

    @Override
    default <V> ToLongFunction1<V> composeUnchecked(final Function1<? super V, ? extends T1> before) {
        return (final V v) -> this.applyAsLong(before.apply(v));
    }

    /**
     * @see Function1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Function1<T1, V> andThen(final Function1<? super Long, ? extends V> after) {
        return (Function1<T1, V>) ToLongFunctionN.super.andThen(after);
    }

    /**
     * @see Function1
     */
    @Override
    default <V> Function1<T1, V> andThenUnchecked(final Function1<? super Long, ? extends V> after) {
        return (final T1 t1) -> after.apply(this.applyAsLong(t1));
    }
}
