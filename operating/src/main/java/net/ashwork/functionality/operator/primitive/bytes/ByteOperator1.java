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
import net.ashwork.functionality.operator.Operator1;
import net.ashwork.functionality.operator.OperatorN;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedAll;
import net.ashwork.functionality.primitive.bytes.ByteFunction1;
import net.ashwork.functionality.primitive.bytes.ToByteFunction1;
import net.ashwork.functionality.primitive.bytes.ToByteFunctionN;

/**
 * Represents an operation that accepts a {@code byte}-valued operand and produces a result of the same type as its operand.
 * This is the one-arity specialization of {@link OperatorN}.
 * This is the {@code byte}-consuming primitive specialization of {@link ToByteFunction1}.
 * This is the {@code byte}-producing primitive specialization of {@link ByteFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsByte(byte)}.
 *
 * @see OperatorN
 * @see ToByteFunction1
 * @see ByteFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ByteOperator1 extends OperatorN<Byte>, ToByteFunctionN, InputChainableInput<Byte>, UnboxedAll<Operator1<Byte>, ToByteFunction1<Byte>, ByteFunction1<Byte>> {

    /**
     * Applies this operator to the given operand.
     *
     * @param value the operand
     * @return the operator result
     */
    byte applyAsByte(final byte value);

    @Override
    default byte applyAllAsByteUnchecked(final Object... args) {
        return this.applyAsByte((byte) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see Operator1
     */
    @Override
    default Operator1<Byte> box() {
        return this::applyAsByte;
    }

    /**
     * @see ToByteFunction1
     */
    @Override
    default ToByteFunction1<Byte> boxInput() {
        return this::applyAsByte;
    }

    /**
     * @see ByteFunction1
     */
    @Override
    default ByteFunction1<Byte> boxResult() {
        return this::applyAsByte;
    }

    /**
     * @see ToByteFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToByteFunction1<V> compose(final Function1<? super V, ? extends Byte> before) {
        return (ToByteFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToByteFunction1
     */
    @Override
    default <V> ToByteFunction1<V> composeUnchecked(final Function1<? super V, ? extends Byte> before) {
        return (final V v) -> this.applyAsByte(before.apply(v));
    }

    /**
     * @see ByteFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ByteFunction1<V> andThen(final Function1<? super Byte, ? extends V> after) {
        return (ByteFunction1<V>) ToByteFunctionN.super.andThen(after);
    }

    /**
     * @see ByteFunction1
     */
    @Override
    default <V> ByteFunction1<V> andThenUnchecked(final Function1<? super Byte, ? extends V> after) {
        return (final byte value) -> after.apply(this.applyAsByte(value));
    }
}
