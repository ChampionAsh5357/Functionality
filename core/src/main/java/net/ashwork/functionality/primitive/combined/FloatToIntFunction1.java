/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.primitive.combined;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedAll;
import net.ashwork.functionality.primitive.floats.FloatFunction1;
import net.ashwork.functionality.primitive.ints.ToIntFunction1;
import net.ashwork.functionality.primitive.ints.ToIntFunctionN;

/**
 * Represents a function that accepts a {@code float}-valued argument and produces an {@code int}-valued result.
 * This is the one-arity specialization of {@link ToIntFunctionN}.
 * This is the {@code float}-consuming primitive specialization of {@link ToIntFunction1}.
 * This is the {@code int}-producing primitive specialization of {@link FloatFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsInt(float)}.
 *
 * @see FloatFunction1
 * @see ToIntFunction1
 * @see ToIntFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface FloatToIntFunction1 extends ToIntFunctionN, InputChainableInput<Float>, UnboxedAll<Function1<Float, Integer>, ToIntFunction1<Float>, FloatFunction1<Integer>> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    int applyAsInt(final float value);

    @Override
    default int applyAllAsIntUnchecked(final Object... args) {
        return this.applyAsInt((float) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<Float, Integer> box() {
        return this::applyAsInt;
    }

    /**
     * @see ToIntFunction1
     */
    @Override
    default ToIntFunction1<Float> boxInput() {
        return this::applyAsInt;
    }

    /**
     * @see FloatFunction1
     */
    @Override
    default FloatFunction1<Integer> boxResult() {
        return this::applyAsInt;
    }

    /**
     * @see ToIntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToIntFunction1<V> compose(final Function1<? super V, ? extends Float> before) {
        return (ToIntFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToIntFunction1
     */
    @Override
    default <V> ToIntFunction1<V> composeUnchecked(final Function1<? super V, ? extends Float> before) {
        return (final V v) -> this.applyAsInt(before.apply(v));
    }

    /**
     * @see FloatFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> FloatFunction1<V> andThen(final Function1<? super Integer, ? extends V> after) {
        return (FloatFunction1<V>) ToIntFunctionN.super.andThen(after);
    }

    /**
     * @see FloatFunction1
     */
    @Override
    default <V> FloatFunction1<V> andThenUnchecked(final Function1<? super Integer, ? extends V> after) {
        return (final float value) -> after.apply(this.applyAsInt(value));
    }
}
