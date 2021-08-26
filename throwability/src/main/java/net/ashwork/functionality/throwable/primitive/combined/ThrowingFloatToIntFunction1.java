/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.primitive.combined;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.primitive.combined.FloatToIntFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingFloatToIntFunction1;
import net.ashwork.functionality.throwable.primitive.floats.ThrowingFloatFunction1;
import net.ashwork.functionality.throwable.primitive.ints.ThrowingToIntFunction1;
import net.ashwork.functionality.throwable.primitive.ints.ThrowingToIntFunctionN;

/**
 * Represents a function that accepts a {@code float}-valued argument and produces an {@code int}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToIntFunctionN}.
 * This is the {@code float}-consuming primitive specialization of {@link ThrowingToIntFunction1}.
 * This is the {@code int}-producing primitive specialization of {@link ThrowingFloatFunction1}.
 * This is the throwing variation of {@link FloatToIntFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsInt(float)}.
 *
 * @see ThrowingFloatFunction1
 * @see ThrowingToIntFunction1
 * @see ThrowingToIntFunctionN
 * @see FloatToIntFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingFloatToIntFunction1 extends AbstractThrowingFloatToIntFunction1<AbstractThrowingFloatToIntFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see FloatToIntFunction1
     */
    static ThrowingFloatToIntFunction1 from(final FloatToIntFunction1 function) {
        return function::applyAsInt;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Float, Integer> box() {
        return this::applyAsInt;
    }

    /**
     * @see ThrowingToIntFunction1
     */
    @Override
    default ThrowingToIntFunction1<Float> boxInput() {
        return this::applyAsInt;
    }

    /**
     * @see ThrowingFloatFunction1
     */
    @Override
    default ThrowingFloatFunction1<Integer> boxResult() {
        return this::applyAsInt;
    }

    @Override
    default FloatToIntFunction1 swallow() {
        return this.handle((t, value) -> 0);
    }

    /**
     * @see ThrowingToIntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToIntFunction1<V> compose(final Function1<? super V, ? extends Float> before) {
        return (ThrowingToIntFunction1<V>) AbstractThrowingFloatToIntFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToIntFunction1
     */
    @Override
    default <V> ThrowingToIntFunction1<V> composeUnchecked(final Function1<? super V, ? extends Float> before) {
        return (final V v) -> this.applyAsInt(before.apply(v));
    }

    /**
     * @see ThrowingFloatFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFloatFunction1<V> andThen(final Function1<? super Integer, ? extends V> after) {
        return (ThrowingFloatFunction1<V>) AbstractThrowingFloatToIntFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingFloatFunction1
     */
    @Override
    default <V> ThrowingFloatFunction1<V> andThenUnchecked(final Function1<? super Integer, ? extends V> after) {
        return (final float value) -> after.apply(this.applyAsInt(value));
    }
}
