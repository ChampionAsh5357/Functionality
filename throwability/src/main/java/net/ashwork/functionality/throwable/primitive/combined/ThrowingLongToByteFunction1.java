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
import net.ashwork.functionality.primitive.combined.LongToByteFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingLongToByteFunction1;
import net.ashwork.functionality.throwable.primitive.bytes.ThrowingToByteFunction1;
import net.ashwork.functionality.throwable.primitive.bytes.ThrowingToByteFunctionN;
import net.ashwork.functionality.throwable.primitive.longs.ThrowingLongFunction1;

/**
 * Represents a function that accepts a {@code long}-valued argument and produces a {@code byte}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToByteFunctionN}.
 * This is the {@code long}-consuming primitive specialization of {@link ThrowingToByteFunction1}.
 * This is the {@code byte}-producing primitive specialization of {@link ThrowingLongFunction1}.
 * This is the throwing variation of {@link LongToByteFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsByte(long)}.
 *
 * @see ThrowingLongFunction1
 * @see ThrowingToByteFunction1
 * @see ThrowingToByteFunctionN
 * @see LongToByteFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingLongToByteFunction1 extends AbstractThrowingLongToByteFunction1<AbstractThrowingLongToByteFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see LongToByteFunction1
     */
    static ThrowingLongToByteFunction1 from(final LongToByteFunction1 function) {
        return function::applyAsByte;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Long, Byte> box() {
        return this::applyAsByte;
    }

    /**
     * @see ThrowingToByteFunction1
     */
    @Override
    default ThrowingToByteFunction1<Long> boxInput() {
        return this::applyAsByte;
    }

    /**
     * @see ThrowingLongFunction1
     */
    @Override
    default ThrowingLongFunction1<Byte> boxResult() {
        return this::applyAsByte;
    }

    @Override
    default LongToByteFunction1 swallow() {
        return this.handle((t, value) -> (byte) 0);
    }

    /**
     * @see ThrowingToByteFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToByteFunction1<V> compose(final Function1<? super V, ? extends Long> before) {
        return (ThrowingToByteFunction1<V>) AbstractThrowingLongToByteFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToByteFunction1
     */
    @Override
    default <V> ThrowingToByteFunction1<V> composeUnchecked(final Function1<? super V, ? extends Long> before) {
        return (final V v) -> this.applyAsByte(before.apply(v));
    }

    /**
     * @see ThrowingLongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingLongFunction1<V> andThen(final Function1<? super Byte, ? extends V> after) {
        return (ThrowingLongFunction1<V>) AbstractThrowingLongToByteFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingLongFunction1
     */
    @Override
    default <V> ThrowingLongFunction1<V> andThenUnchecked(final Function1<? super Byte, ? extends V> after) {
        return (final long value) -> after.apply(this.applyAsByte(value));
    }
}
