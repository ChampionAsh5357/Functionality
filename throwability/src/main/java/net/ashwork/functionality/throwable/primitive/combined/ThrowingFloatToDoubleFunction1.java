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
import net.ashwork.functionality.primitive.combined.FloatToDoubleFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingFloatToDoubleFunction1;
import net.ashwork.functionality.throwable.primitive.doubles.ThrowingToDoubleFunction1;
import net.ashwork.functionality.throwable.primitive.doubles.ThrowingToDoubleFunctionN;
import net.ashwork.functionality.throwable.primitive.floats.ThrowingFloatFunction1;

/**
 * Represents a function that accepts a {@code float}-valued argument and produces a {@code double}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToDoubleFunctionN}.
 * This is the {@code float}-consuming primitive specialization of {@link ThrowingToDoubleFunction1}.
 * This is the {@code double}-producing primitive specialization of {@link ThrowingFloatFunction1}.
 * This is the throwing variation of {@link FloatToDoubleFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsDouble(float)}.
 *
 * @see ThrowingFloatFunction1
 * @see ThrowingToDoubleFunction1
 * @see ThrowingToDoubleFunctionN
 * @see FloatToDoubleFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingFloatToDoubleFunction1 extends AbstractThrowingFloatToDoubleFunction1<AbstractThrowingFloatToDoubleFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see FloatToDoubleFunction1
     */
    static ThrowingFloatToDoubleFunction1 from(final FloatToDoubleFunction1 function) {
        return function::applyAsDouble;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Float, Double> box() {
        return this::applyAsDouble;
    }

    /**
     * @see ThrowingToDoubleFunction1
     */
    @Override
    default ThrowingToDoubleFunction1<Float> boxInput() {
        return this::applyAsDouble;
    }

    /**
     * @see ThrowingFloatFunction1
     */
    @Override
    default ThrowingFloatFunction1<Double> boxResult() {
        return this::applyAsDouble;
    }

    @Override
    default FloatToDoubleFunction1 swallow() {
        return this.handle((t, value) -> 0.0d);
    }

    /**
     * @see ThrowingToDoubleFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToDoubleFunction1<V> compose(final Function1<? super V, ? extends Float> before) {
        return (ThrowingToDoubleFunction1<V>) AbstractThrowingFloatToDoubleFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToDoubleFunction1
     */
    @Override
    default <V> ThrowingToDoubleFunction1<V> composeUnchecked(final Function1<? super V, ? extends Float> before) {
        return (final V v) -> this.applyAsDouble(before.apply(v));
    }

    /**
     * @see ThrowingFloatFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFloatFunction1<V> andThen(final Function1<? super Double, ? extends V> after) {
        return (ThrowingFloatFunction1<V>) AbstractThrowingFloatToDoubleFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingFloatFunction1
     */
    @Override
    default <V> ThrowingFloatFunction1<V> andThenUnchecked(final Function1<? super Double, ? extends V> after) {
        return (final float value) -> after.apply(this.applyAsDouble(value));
    }
}
