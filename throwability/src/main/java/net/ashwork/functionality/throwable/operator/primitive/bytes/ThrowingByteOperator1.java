/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.operator.primitive.bytes;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.operator.primitive.bytes.ByteOperator1;
import net.ashwork.functionality.throwable.abstracts.operator.primitive.bytes.AbstractThrowingByteOperator1;
import net.ashwork.functionality.throwable.operator.ThrowingOperator1;
import net.ashwork.functionality.throwable.operator.ThrowingOperatorN;
import net.ashwork.functionality.throwable.primitive.bytes.ThrowingByteFunction1;
import net.ashwork.functionality.throwable.primitive.bytes.ThrowingToByteFunction1;

/**
 * Represents an operation that accepts a {@code byte}-valued operand and produces a result of the same type as its operand or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingOperatorN}.
 * This is the {@code byte}-consuming primitive specialization of {@link ThrowingToByteFunction1}.
 * This is the {@code byte}-producing primitive specialization of {@link ThrowingByteFunction1}.
 * This is the throwing variation of {@link ByteOperator1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsByte(byte)}.
 *
 * @see ThrowingOperatorN
 * @see ThrowingToByteFunction1
 * @see ThrowingByteFunction1
 * @see ByteOperator1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingByteOperator1 extends AbstractThrowingByteOperator1<AbstractThrowingByteOperator1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param operator the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ByteOperator1
     */
    static ThrowingByteOperator1 from(final ByteOperator1 operator) {
        return operator::applyAsByte;
    }

    /**
     * @see ThrowingOperator1
     */
    @Override
    default ThrowingOperator1<Byte> box() {
        return this::applyAsByte;
    }

    /**
     * @see ThrowingToByteFunction1
     */
    @Override
    default ThrowingToByteFunction1<Byte> boxInput() {
        return this::applyAsByte;
    }

    /**
     * @see ByteOperator1
     */
    @Override
    default ThrowingByteFunction1<Byte> boxResult() {
        return this::applyAsByte;
    }

    /**
     * @see ByteOperator1
     */
    @Override
    default ByteOperator1 swallow() {
        return this.handle((t, value) -> (byte) 0);
    }

    /**
     * @see ThrowingToByteFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToByteFunction1<V> compose(final Function1<? super V, ? extends Byte> before) {
        return (ThrowingToByteFunction1<V>) AbstractThrowingByteOperator1.super.compose(before);
    }

    /**
     * @see ThrowingToByteFunction1
     */
    @Override
    default <V> ThrowingToByteFunction1<V> composeUnchecked(final Function1<? super V, ? extends Byte> before) {
        return (final V v) -> this.applyAsByte(before.apply(v));
    }

    /**
     * @see ThrowingByteFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingByteFunction1<V> andThen(final Function1<? super Byte, ? extends V> after) {
        return (ThrowingByteFunction1<V>) AbstractThrowingByteOperator1.super.andThen(after);
    }

    /**
     * @see ThrowingByteFunction1
     */
    @Override
    default <V> ThrowingByteFunction1<V> andThenUnchecked(final Function1<? super Byte, ? extends V> after) {
        return (final byte value) -> after.apply(this.applyAsByte(value));
    }
}
