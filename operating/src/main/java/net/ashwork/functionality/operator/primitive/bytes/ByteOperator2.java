/*
 * Operating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.operator.primitive.bytes;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.Function2;
import net.ashwork.functionality.operator.Operator2;
import net.ashwork.functionality.operator.OperatorN;
import net.ashwork.functionality.partial.Unboxed;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.primitive.bytes.ToByteFunction2;
import net.ashwork.functionality.primitive.bytes.ToByteFunctionN;

/**
 * Represents an operation that accepts two {@code byte}-valued operands and produces a result of the same type as its operands.
 * This is the two-arity specialization of {@link OperatorN}.
 * This is the {@code byte}-consuming primitive specialization of {@link ToByteFunction2}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsByte(byte, byte)}.
 *
 * @see OperatorN
 * @see ToByteFunction2
 * @since 1.0.0
 */
@FunctionalInterface
public interface ByteOperator2 extends OperatorN<Byte>, ToByteFunctionN, Unboxed<Operator2<Byte>>, UnboxedInput<ToByteFunction2<Byte, Byte>> {

    /**
     * Applies this operator to the given operands.
     *
     * @param value1 the first operand
     * @param value2 the second operand
     * @return the operator result
     */
    byte applyAsByte(final byte value1, final byte value2);

    @Override
    default byte applyAllAsByteUnchecked(final Object... args) {
        return this.applyAsByte((byte) args[0], (byte) args[1]);
    }

    @Override
    default int arity() {
        return 2;
    }

    /**
     * @see Operator2
     */
    @Override
    default Operator2<Byte> box() {
        return this::applyAsByte;
    }

    /**
     * @see ToByteFunction2
     */
    @Override
    default ToByteFunction2<Byte, Byte> boxInput() {
        return this::applyAsByte;
    }

    /**
     * @see Function2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Function2<Byte, Byte, V> andThen(final Function1<? super Byte, ? extends V> after) {
        return (Function2<Byte, Byte, V>) ToByteFunctionN.super.andThen(after);
    }

    /**
     * @see Function2
     */
    @Override
    default <V> Function2<Byte, Byte, V> andThenUnchecked(final Function1<? super Byte, ? extends V> after) {
        return (final Byte value1, final Byte value2) -> after.apply(this.applyAsByte(value1, value2));
    }
}
