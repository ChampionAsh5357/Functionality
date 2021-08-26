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
import net.ashwork.functionality.primitive.combined.FloatToByteFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingFloatToByteFunction1;
import net.ashwork.functionality.throwable.primitive.bytes.ThrowingToByteFunction1;
import net.ashwork.functionality.throwable.primitive.bytes.ThrowingToByteFunctionN;
import net.ashwork.functionality.throwable.primitive.floats.ThrowingFloatFunction1;

/**
 * Represents a function that accepts a {@code float}-valued argument and produces a {@code byte}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToByteFunctionN}.
 * This is the {@code float}-consuming primitive specialization of {@link ThrowingToByteFunction1}.
 * This is the {@code byte}-producing primitive specialization of {@link ThrowingFloatFunction1}.
 * This is the throwing variation of {@link FloatToByteFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsByte(float)}.
 *
 * @see ThrowingFloatFunction1
 * @see ThrowingToByteFunction1
 * @see ThrowingToByteFunctionN
 * @see FloatToByteFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingFloatToByteFunction1 extends AbstractThrowingFloatToByteFunction1<AbstractThrowingFloatToByteFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see FloatToByteFunction1
     */
    static ThrowingFloatToByteFunction1 from(final FloatToByteFunction1 function) {
        return function::applyAsByte;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Float, Byte> box() {
        return this::applyAsByte;
    }

    /**
     * @see ThrowingToByteFunction1
     */
    @Override
    default ThrowingToByteFunction1<Float> boxInput() {
        return this::applyAsByte;
    }

    /**
     * @see ThrowingFloatFunction1
     */
    @Override
    default ThrowingFloatFunction1<Byte> boxResult() {
        return this::applyAsByte;
    }

    @Override
    default FloatToByteFunction1 swallow() {
        return this.handle((t, value) -> (byte) 0);
    }

    /**
     * @see ThrowingToByteFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToByteFunction1<V> compose(final Function1<? super V, ? extends Float> before) {
        return (ThrowingToByteFunction1<V>) AbstractThrowingFloatToByteFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToByteFunction1
     */
    @Override
    default <V> ThrowingToByteFunction1<V> composeUnchecked(final Function1<? super V, ? extends Float> before) {
        return (final V v) -> this.applyAsByte(before.apply(v));
    }

    /**
     * @see ThrowingFloatFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFloatFunction1<V> andThen(final Function1<? super Byte, ? extends V> after) {
        return (ThrowingFloatFunction1<V>) AbstractThrowingFloatToByteFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingFloatFunction1
     */
    @Override
    default <V> ThrowingFloatFunction1<V> andThenUnchecked(final Function1<? super Byte, ? extends V> after) {
        return (final float value) -> after.apply(this.applyAsByte(value));
    }
}
