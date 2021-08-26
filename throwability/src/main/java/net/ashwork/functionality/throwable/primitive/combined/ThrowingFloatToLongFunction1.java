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
import net.ashwork.functionality.primitive.combined.FloatToLongFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingFloatToLongFunction1;
import net.ashwork.functionality.throwable.primitive.floats.ThrowingFloatFunction1;
import net.ashwork.functionality.throwable.primitive.longs.ThrowingToLongFunction1;
import net.ashwork.functionality.throwable.primitive.longs.ThrowingToLongFunctionN;

/**
 * Represents a function that accepts a {@code float}-valued argument and produces a {@code long}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToLongFunctionN}.
 * This is the {@code float}-consuming primitive specialization of {@link ThrowingToLongFunction1}.
 * This is the {@code long}-producing primitive specialization of {@link ThrowingFloatFunction1}.
 * This is the throwing variation of {@link FloatToLongFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsLong(float)}.
 *
 * @see ThrowingFloatFunction1
 * @see ThrowingToLongFunction1
 * @see ThrowingToLongFunctionN
 * @see FloatToLongFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingFloatToLongFunction1 extends AbstractThrowingFloatToLongFunction1<AbstractThrowingFloatToLongFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see FloatToLongFunction1
     */
    static ThrowingFloatToLongFunction1 from(final FloatToLongFunction1 function) {
        return function::applyAsLong;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Float, Long> box() {
        return this::applyAsLong;
    }

    /**
     * @see ThrowingToLongFunction1
     */
    @Override
    default ThrowingToLongFunction1<Float> boxInput() {
        return this::applyAsLong;
    }

    /**
     * @see ThrowingFloatFunction1
     */
    @Override
    default ThrowingFloatFunction1<Long> boxResult() {
        return this::applyAsLong;
    }

    @Override
    default FloatToLongFunction1 swallow() {
        return this.handle((t, value) -> 0L);
    }

    /**
     * @see ThrowingToLongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToLongFunction1<V> compose(final Function1<? super V, ? extends Float> before) {
        return (ThrowingToLongFunction1<V>) AbstractThrowingFloatToLongFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToLongFunction1
     */
    @Override
    default <V> ThrowingToLongFunction1<V> composeUnchecked(final Function1<? super V, ? extends Float> before) {
        return (final V v) -> this.applyAsLong(before.apply(v));
    }

    /**
     * @see ThrowingFloatFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFloatFunction1<V> andThen(final Function1<? super Long, ? extends V> after) {
        return (ThrowingFloatFunction1<V>) AbstractThrowingFloatToLongFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingFloatFunction1
     */
    @Override
    default <V> ThrowingFloatFunction1<V> andThenUnchecked(final Function1<? super Long, ? extends V> after) {
        return (final float value) -> after.apply(this.applyAsLong(value));
    }
}
