/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.primitive.ints;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedResult;
import net.ashwork.functionality.partial.Variant;

import java.util.function.ToIntFunction;

/**
 * Represents a function that accepts one argument and produces an {@code int}-valued result.
 * This is the one-arity specialization of {@link ToIntFunctionN}.
 * This is the {@code int}-producing primitive specialization of {@link Function1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsInt(Object)}.
 *
 * @param <T1> the type of the input to the function
 *
 * @see Function1
 * @see ToIntFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface ToIntFunction1<T1> extends ToIntFunctionN, InputChainableInput<T1>, UnboxedResult<Function1<T1, Integer>>, Variant<ToIntFunction<T1>> {

    /**
     * Applies this function to the given argument.
     *
     * @param t1 the function argument
     * @return the function result
     */
    int applyAsInt(final T1 t1);

    @SuppressWarnings("unchecked")
    @Override
    default int applyAllAsIntUnchecked(final Object... args) {
        return this.applyAsInt((T1) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * Creates an instance of this object from its {@link ToIntFunction} variant.
     *
     * @param function the variant of this object
     * @param <T1> the type of the input to the function
     * @return an instance of this object
     *
     * @see ToIntFunction
     */
    static <T1> ToIntFunction1<T1> fromVariant(final ToIntFunction<T1> function) {
        return function::applyAsInt;
    }

    @Override
    default ToIntFunction<T1> toVariant() {
        return this::applyAsInt;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<T1, Integer> boxResult() {
        return this::applyAsInt;
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> ToIntFunction1<V> compose(final Function1<? super V, ? extends T1> before) {
        return (ToIntFunction1<V>) InputChainableInput.super.compose(before);
    }

    @Override
    default <V> ToIntFunction1<V> composeUnchecked(final Function1<? super V, ? extends T1> before) {
        return (final V v) -> this.applyAsInt(before.apply(v));
    }

    /**
     * @see Function1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Function1<T1, V> andThen(final Function1<? super Integer, ? extends V> after) {
        return (Function1<T1, V>) ToIntFunctionN.super.andThen(after);
    }

    /**
     * @see Function1
     */
    @Override
    default <V> Function1<T1, V> andThenUnchecked(final Function1<? super Integer, ? extends V> after) {
        return (final T1 t1) -> after.apply(this.applyAsInt(t1));
    }
}
