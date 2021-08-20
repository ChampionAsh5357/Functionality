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
import net.ashwork.functionality.primitive.combined.ByteToShortFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingByteToShortFunction1;
import net.ashwork.functionality.throwable.primitive.bytes.ThrowingByteFunction1;
import net.ashwork.functionality.throwable.primitive.shorts.ThrowingToShortFunction1;
import net.ashwork.functionality.throwable.primitive.shorts.ThrowingToShortFunctionN;

/**
 * Represents a function that accepts a {@code byte}-valued argument and produces a {@code short}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToShortFunctionN}.
 * This is the {@code byte}-consuming primitive specialization of {@link ThrowingToShortFunction1}.
 * This is the {@code short}-producing primitive specialization of {@link ThrowingByteFunction1}.
 * This is the throwing variation of {@link ByteToShortFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsShort(byte)}.
 *
 * @see ThrowingByteFunction1
 * @see ThrowingToShortFunction1
 * @see ThrowingToShortFunctionN
 * @see ByteToShortFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingByteToShortFunction1 extends AbstractThrowingByteToShortFunction1<ThrowingFunction1<Byte, Short>, ThrowingToShortFunction1<Byte>, ThrowingByteFunction1<Short>, AbstractThrowingByteToShortFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ByteToShortFunction1
     */
    static ThrowingByteToShortFunction1 from(final ByteToShortFunction1 function) {
        return function::applyAsShort;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Byte, Short> box() {
        return this::applyAsShort;
    }

    /**
     * @see ThrowingToShortFunction1
     */
    @Override
    default ThrowingToShortFunction1<Byte> boxInput() {
        return this::applyAsShort;
    }

    /**
     * @see ThrowingByteFunction1
     */
    @Override
    default ThrowingByteFunction1<Short> boxResult() {
        return this::applyAsShort;
    }

    @Override
    default ByteToShortFunction1 swallow() {
        return this.handle((t, value) -> (short) 0);
    }

    /**
     * @see ThrowingToShortFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToShortFunction1<V> compose(final Function1<? super V, ? extends Byte> before) {
        return (ThrowingToShortFunction1<V>) AbstractThrowingByteToShortFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToShortFunction1
     */
    @Override
    default <V> ThrowingToShortFunction1<V> composeUnchecked(final Function1<? super V, ? extends Byte> before) {
        return (final V v) -> this.applyAsShort(before.apply(v));
    }

    /**
     * @see ThrowingByteFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingByteFunction1<V> andThen(final Function1<? super Short, ? extends V> after) {
        return (ThrowingByteFunction1<V>) AbstractThrowingByteToShortFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingByteFunction1
     */
    @Override
    default <V> ThrowingByteFunction1<V> andThenUnchecked(final Function1<? super Short, ? extends V> after) {
        return (final byte value) -> after.apply(this.applyAsShort(value));
    }
}
