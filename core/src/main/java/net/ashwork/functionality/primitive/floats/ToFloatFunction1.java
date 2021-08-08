/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.primitive.floats;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedResult;

/**
 * Represents a function that accepts one argument and produces a {@code float}-valued result.
 * This is the one-arity specialization of {@link ToFloatFunctionN}.
 * This is the {@code float}-producing primitive specialization of {@link Function1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsFloat(Object)}.
 *
 * @param <T1> the type of the input to the function
 *
 * @see Function1
 * @see ToFloatFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface ToFloatFunction1<T1> extends ToFloatFunctionN, InputChainableInput<T1>, UnboxedResult<Function1<T1, Float>> {

    /**
     * Applies this function to the given argument.
     *
     * @param t1 the function argument
     * @return the function result
     */
    float applyAsFloat(final T1 t1);

    @SuppressWarnings("unchecked")
    @Override
    default float applyAllAsFloatUnchecked(final Object... args) {
        return this.applyAsFloat((T1) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<T1, Float> boxResult() {
        return this::applyAsFloat;
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> ToFloatFunction1<V> compose(final Function1<? super V, ? extends T1> before) {
        return (ToFloatFunction1<V>) InputChainableInput.super.compose(before);
    }

    @Override
    default <V> ToFloatFunction1<V> composeUnchecked(final Function1<? super V, ? extends T1> before) {
        return (final V v) -> this.applyAsFloat(before.apply(v));
    }

    /**
     * @see Function1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Function1<T1, V> andThen(final Function1<? super Float, ? extends V> after) {
        return (Function1<T1, V>) ToFloatFunctionN.super.andThen(after);
    }

    /**
     * @see Function1
     */
    @Override
    default <V> Function1<T1, V> andThenUnchecked(final Function1<? super Float, ? extends V> after) {
        return (final T1 t1) -> after.apply(this.applyAsFloat(t1));
    }
}
