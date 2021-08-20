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
import net.ashwork.functionality.primitive.combined.ByteToLongFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingByteToLongFunction1;
import net.ashwork.functionality.throwable.primitive.bytes.ThrowingByteFunction1;
import net.ashwork.functionality.throwable.primitive.longs.ThrowingToLongFunction1;
import net.ashwork.functionality.throwable.primitive.longs.ThrowingToLongFunctionN;

/**
 * Represents a function that accepts a {@code byte}-valued argument and produces a {@code long}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToLongFunctionN}.
 * This is the {@code byte}-consuming primitive specialization of {@link ThrowingToLongFunction1}.
 * This is the {@code long}-producing primitive specialization of {@link ThrowingByteFunction1}.
 * This is the throwing variation of {@link ByteToLongFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsLong(byte)}.
 *
 * @see ThrowingByteFunction1
 * @see ThrowingToLongFunction1
 * @see ThrowingToLongFunctionN
 * @see ByteToLongFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingByteToLongFunction1 extends AbstractThrowingByteToLongFunction1<ThrowingFunction1<Byte, Long>, ThrowingToLongFunction1<Byte>, ThrowingByteFunction1<Long>, AbstractThrowingByteToLongFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ByteToLongFunction1
     */
    static ThrowingByteToLongFunction1 from(final ByteToLongFunction1 function) {
        return function::applyAsLong;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Byte, Long> box() {
        return this::applyAsLong;
    }

    /**
     * @see ThrowingToLongFunction1
     */
    @Override
    default ThrowingToLongFunction1<Byte> boxInput() {
        return this::applyAsLong;
    }

    /**
     * @see ThrowingByteFunction1
     */
    @Override
    default ThrowingByteFunction1<Long> boxResult() {
        return this::applyAsLong;
    }

    @Override
    default ByteToLongFunction1 swallow() {
        return this.handle((t, value) -> 0L);
    }

    /**
     * @see ThrowingToLongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToLongFunction1<V> compose(final Function1<? super V, ? extends Byte> before) {
        return (ThrowingToLongFunction1<V>) AbstractThrowingByteToLongFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToLongFunction1
     */
    @Override
    default <V> ThrowingToLongFunction1<V> composeUnchecked(final Function1<? super V, ? extends Byte> before) {
        return (final V v) -> this.applyAsLong(before.apply(v));
    }

    /**
     * @see ThrowingByteFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingByteFunction1<V> andThen(final Function1<? super Long, ? extends V> after) {
        return (ThrowingByteFunction1<V>) AbstractThrowingByteToLongFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingByteFunction1
     */
    @Override
    default <V> ThrowingByteFunction1<V> andThenUnchecked(final Function1<? super Long, ? extends V> after) {
        return (final byte value) -> after.apply(this.applyAsLong(value));
    }
}
