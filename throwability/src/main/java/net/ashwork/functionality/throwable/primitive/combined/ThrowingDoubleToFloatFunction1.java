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
import net.ashwork.functionality.primitive.combined.DoubleToFloatFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingDoubleToFloatFunction1;
import net.ashwork.functionality.throwable.primitive.doubles.ThrowingDoubleFunction1;
import net.ashwork.functionality.throwable.primitive.floats.ThrowingToFloatFunction1;
import net.ashwork.functionality.throwable.primitive.floats.ThrowingToFloatFunctionN;

/**
 * Represents a function that accepts a {@code double}-valued argument and produces a {@code float}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToFloatFunctionN}.
 * This is the {@code double}-consuming primitive specialization of {@link ThrowingToFloatFunction1}.
 * This is the {@code float}-producing primitive specialization of {@link ThrowingDoubleFunction1}.
 * This is the throwing variation of {@link DoubleToFloatFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsFloat(double)}.
 *
 * @see ThrowingDoubleFunction1
 * @see ThrowingToFloatFunction1
 * @see ThrowingToFloatFunctionN
 * @see DoubleToFloatFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingDoubleToFloatFunction1 extends AbstractThrowingDoubleToFloatFunction1<AbstractThrowingDoubleToFloatFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see DoubleToFloatFunction1
     */
    static ThrowingDoubleToFloatFunction1 from(final DoubleToFloatFunction1 function) {
        return function::applyAsFloat;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Double, Float> box() {
        return this::applyAsFloat;
    }

    /**
     * @see ThrowingToFloatFunction1
     */
    @Override
    default ThrowingToFloatFunction1<Double> boxInput() {
        return this::applyAsFloat;
    }

    /**
     * @see ThrowingDoubleFunction1
     */
    @Override
    default ThrowingDoubleFunction1<Float> boxResult() {
        return this::applyAsFloat;
    }

    @Override
    default DoubleToFloatFunction1 swallow() {
        return this.handle((t, value) -> 0.0f);
    }

    /**
     * @see ThrowingToFloatFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToFloatFunction1<V> compose(final Function1<? super V, ? extends Double> before) {
        return (ThrowingToFloatFunction1<V>) AbstractThrowingDoubleToFloatFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToFloatFunction1
     */
    @Override
    default <V> ThrowingToFloatFunction1<V> composeUnchecked(final Function1<? super V, ? extends Double> before) {
        return (final V v) -> this.applyAsFloat(before.apply(v));
    }

    /**
     * @see ThrowingDoubleFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingDoubleFunction1<V> andThen(final Function1<? super Float, ? extends V> after) {
        return (ThrowingDoubleFunction1<V>) AbstractThrowingDoubleToFloatFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingDoubleFunction1
     */
    @Override
    default <V> ThrowingDoubleFunction1<V> andThenUnchecked(final Function1<? super Float, ? extends V> after) {
        return (final double value) -> after.apply(this.applyAsFloat(value));
    }
}
