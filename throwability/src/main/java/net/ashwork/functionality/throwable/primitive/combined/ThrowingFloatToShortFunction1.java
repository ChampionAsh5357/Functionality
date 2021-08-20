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
import net.ashwork.functionality.primitive.combined.FloatToShortFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingFloatToShortFunction1;
import net.ashwork.functionality.throwable.primitive.floats.ThrowingFloatFunction1;
import net.ashwork.functionality.throwable.primitive.shorts.ThrowingToShortFunction1;
import net.ashwork.functionality.throwable.primitive.shorts.ThrowingToShortFunctionN;

/**
 * Represents a function that accepts a {@code float}-valued argument and produces a {@code short}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToShortFunctionN}.
 * This is the {@code float}-consuming primitive specialization of {@link ThrowingToShortFunction1}.
 * This is the {@code short}-producing primitive specialization of {@link ThrowingFloatFunction1}.
 * This is the throwing variation of {@link FloatToShortFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsShort(float)}.
 *
 * @see ThrowingFloatFunction1
 * @see ThrowingToShortFunction1
 * @see ThrowingToShortFunctionN
 * @see FloatToShortFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingFloatToShortFunction1 extends AbstractThrowingFloatToShortFunction1<ThrowingFunction1<Float, Short>, ThrowingToShortFunction1<Float>, ThrowingFloatFunction1<Short>, AbstractThrowingFloatToShortFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see FloatToShortFunction1
     */
    static ThrowingFloatToShortFunction1 from(final FloatToShortFunction1 function) {
        return function::applyAsShort;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Float, Short> box() {
        return this::applyAsShort;
    }

    /**
     * @see ThrowingToShortFunction1
     */
    @Override
    default ThrowingToShortFunction1<Float> boxInput() {
        return this::applyAsShort;
    }

    /**
     * @see ThrowingFloatFunction1
     */
    @Override
    default ThrowingFloatFunction1<Short> boxResult() {
        return this::applyAsShort;
    }

    @Override
    default FloatToShortFunction1 swallow() {
        return this.handle((t, value) -> (short) 0);
    }

    /**
     * @see ThrowingToShortFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToShortFunction1<V> compose(final Function1<? super V, ? extends Float> before) {
        return (ThrowingToShortFunction1<V>) AbstractThrowingFloatToShortFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToShortFunction1
     */
    @Override
    default <V> ThrowingToShortFunction1<V> composeUnchecked(final Function1<? super V, ? extends Float> before) {
        return (final V v) -> this.applyAsShort(before.apply(v));
    }

    /**
     * @see ThrowingFloatFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFloatFunction1<V> andThen(final Function1<? super Short, ? extends V> after) {
        return (ThrowingFloatFunction1<V>) AbstractThrowingFloatToShortFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingFloatFunction1
     */
    @Override
    default <V> ThrowingFloatFunction1<V> andThenUnchecked(final Function1<? super Short, ? extends V> after) {
        return (final float value) -> after.apply(this.applyAsShort(value));
    }
}
