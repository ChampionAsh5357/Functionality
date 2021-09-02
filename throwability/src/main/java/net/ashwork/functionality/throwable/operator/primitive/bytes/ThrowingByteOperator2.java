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
import net.ashwork.functionality.operator.primitive.bytes.ByteOperator2;
import net.ashwork.functionality.throwable.ThrowingFunction2;
import net.ashwork.functionality.throwable.abstracts.operator.primitive.bytes.AbstractThrowingByteOperator2;
import net.ashwork.functionality.throwable.operator.ThrowingOperator2;
import net.ashwork.functionality.throwable.operator.ThrowingOperatorN;
import net.ashwork.functionality.throwable.primitive.bytes.ThrowingToByteFunction2;

/**
 * Represents an operation that accepts two {@code byte}-valued operands and produces a result of the same type as its operands or throws a throwable.
 * This is the two-arity specialization of {@link ThrowingOperatorN}.
 * This is the {@code byte}-consuming primitive specialization of {@link ThrowingToByteFunction2}.
 * This is the throwing variation of {@link ByteOperator2}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @see ThrowingOperatorN
 * @see ThrowingToByteFunction2
 * @see ByteOperator2
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingByteOperator2 extends AbstractThrowingByteOperator2<AbstractThrowingByteOperator2.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param operator the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ByteOperator2
     */
    static ThrowingByteOperator2 from(final ByteOperator2 operator) {
        return operator::applyAsByte;
    }

    /**
     * @see ThrowingOperator2
     */
    @Override
    default ThrowingOperator2<Byte> box() {
        return this::applyAsByte;
    }

    /**
     * @see ThrowingToByteFunction2
     */
    @Override
    default ThrowingToByteFunction2<Byte, Byte> boxInput() {
        return this::applyAsByte;
    }

    /**
     * @see ByteOperator2
     */
    @Override
    default ByteOperator2 swallow() {
        return this.handle((t, value1, value2) -> (byte) 0);
    }

    /**
     * @see ThrowingFunction2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction2<Byte, Byte, V> andThen(final Function1<? super Byte, ? extends V> after) {
        return (ThrowingFunction2<Byte, Byte, V>) AbstractThrowingByteOperator2.super.andThen(after);
    }

    /**
     * @see ThrowingFunction2
     */
    @Override
    default <V> ThrowingFunction2<Byte, Byte, V> andThenUnchecked(final Function1<? super Byte, ? extends V> after) {
        return (final Byte value1, final Byte value2) -> after.apply(this.applyAsByte(value1, value2));
    }
}
