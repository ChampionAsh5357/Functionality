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
import net.ashwork.functionality.primitive.combined.DoubleToByteFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingDoubleToByteFunction1;
import net.ashwork.functionality.throwable.primitive.bytes.ThrowingToByteFunction1;
import net.ashwork.functionality.throwable.primitive.bytes.ThrowingToByteFunctionN;
import net.ashwork.functionality.throwable.primitive.doubles.ThrowingDoubleFunction1;

/**
 * Represents a function that accepts a {@code double}-valued argument and produces a {@code byte}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToByteFunctionN}.
 * This is the {@code double}-consuming primitive specialization of {@link ThrowingToByteFunction1}.
 * This is the {@code byte}-producing primitive specialization of {@link ThrowingDoubleFunction1}.
 * This is the throwing variation of {@link DoubleToByteFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsByte(double)}.
 *
 * @see ThrowingDoubleFunction1
 * @see ThrowingToByteFunction1
 * @see ThrowingToByteFunctionN
 * @see DoubleToByteFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingDoubleToByteFunction1 extends AbstractThrowingDoubleToByteFunction1<AbstractThrowingDoubleToByteFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see DoubleToByteFunction1
     */
    static ThrowingDoubleToByteFunction1 from(final DoubleToByteFunction1 function) {
        return function::applyAsByte;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Double, Byte> box() {
        return this::applyAsByte;
    }

    /**
     * @see ThrowingToByteFunction1
     */
    @Override
    default ThrowingToByteFunction1<Double> boxInput() {
        return this::applyAsByte;
    }

    /**
     * @see ThrowingDoubleFunction1
     */
    @Override
    default ThrowingDoubleFunction1<Byte> boxResult() {
        return this::applyAsByte;
    }

    @Override
    default DoubleToByteFunction1 swallow() {
        return this.handle((t, value) -> (byte) 0);
    }

    /**
     * @see ThrowingToByteFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToByteFunction1<V> compose(final Function1<? super V, ? extends Double> before) {
        return (ThrowingToByteFunction1<V>) AbstractThrowingDoubleToByteFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToByteFunction1
     */
    @Override
    default <V> ThrowingToByteFunction1<V> composeUnchecked(final Function1<? super V, ? extends Double> before) {
        return (final V v) -> this.applyAsByte(before.apply(v));
    }

    /**
     * @see ThrowingDoubleFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingDoubleFunction1<V> andThen(final Function1<? super Byte, ? extends V> after) {
        return (ThrowingDoubleFunction1<V>) AbstractThrowingDoubleToByteFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingDoubleFunction1
     */
    @Override
    default <V> ThrowingDoubleFunction1<V> andThenUnchecked(final Function1<? super Byte, ? extends V> after) {
        return (final double value) -> after.apply(this.applyAsByte(value));
    }
}
