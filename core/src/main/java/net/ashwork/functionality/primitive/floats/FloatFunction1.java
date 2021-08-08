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
import net.ashwork.functionality.FunctionN;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedInput;

/**
 * Represents a function that accepts a {@code float}-valued argument and produces a result.
 * This is the one-arity specialization of {@link FunctionN}.
 * This is the {@code float}-consuming primitive specialization of {@link Function1}.
 *
 * <p>This is a functional interface whose functional method is {@link #apply(float)}.
 *
 * @param <R> the type of the result of the function
 *
 * @see Function1
 * @see FunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface FloatFunction1<R> extends FunctionN<R>, InputChainableInput<Float>, UnboxedInput<Function1<Float, R>> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    R apply(final float value);

    @Override
    default R applyAllUnchecked(final Object... args) {
        return this.apply((float) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<Float, R> boxInput() {
        return this::apply;
    }

    /**
     * @see Function1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Function1<V, R> compose(final Function1<? super V, ? extends Float> before) {
        return (Function1<V, R>) InputChainableInput.super.compose(before);
    }

    /**
     * @see Function1
     */
    @Override
    default <V> Function1<V, R> composeUnchecked(final Function1<? super V, ? extends Float> before) {
        return (final V v) -> this.apply(before.apply(v));
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> FloatFunction1<V> andThen(final Function1<? super R, ? extends V> after) {
        return (FloatFunction1<V>) FunctionN.super.andThen(after);
    }

    @Override
    default <V> FloatFunction1<V> andThenUnchecked(final Function1<? super R, ? extends V> after) {
        return (final float value) -> after.apply(this.apply(value));
    }
}
