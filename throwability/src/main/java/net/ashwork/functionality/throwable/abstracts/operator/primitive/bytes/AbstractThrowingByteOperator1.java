/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.operator.primitive.bytes;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.operator.primitive.bytes.ByteOperator1;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedAll;
import net.ashwork.functionality.throwable.abstracts.operator.AbstractThrowingOperator1;
import net.ashwork.functionality.throwable.abstracts.operator.AbstractThrowingOperatorN;
import net.ashwork.functionality.throwable.abstracts.primitive.bytes.AbstractThrowingByteFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.bytes.AbstractThrowingToByteFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.bytes.AbstractThrowingToByteFunctionN;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents an operation that accepts a {@code byte}-valued operand and produces a result of the same type as its operand or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingOperatorN}.
 * This is the {@code byte}-consuming primitive specialization of {@link AbstractThrowingToByteFunction1}.
 * This is the {@code byte}-producing primitive specialization of {@link AbstractThrowingByteFunction1}.
 * This is the throwing variation of {@link ByteOperator1}.
 *
 * @apiNote
 * This is an abstract operator and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the operator
 *
 * @see AbstractThrowingOperatorN
 * @see AbstractThrowingToByteFunction1
 * @see AbstractThrowingByteFunction1
 * @see ByteOperator1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingByteOperator1<H extends AbstractThrowingByteOperator1.Handler> extends AbstractThrowingOperatorN<Byte, H>, AbstractThrowingToByteFunctionN<H>, InputChainableInput<Byte>, UnboxedAll<AbstractThrowingOperator1<Byte, ?>, AbstractThrowingToByteFunction1<Byte, ?>, AbstractThrowingByteFunction1<Byte, ?>> {

    /**
     * Applies this operator to the given operand or throws a throwable.
     *
     * @param value the operand
     * @return the operator result
     * @throws Throwable if the operation cannot be computed
     */
    byte applyAsByte(final byte value) throws Throwable;

    @Override
    default byte applyAllAsByteUnchecked(final Object... args) throws Throwable {
        return this.applyAsByte((byte) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see AbstractThrowingOperator1
     */
    @Override
    AbstractThrowingOperator1<Byte, ?> box();

    /**
     * @see AbstractThrowingToByteFunction1
     */
    @Override
    AbstractThrowingToByteFunction1<Byte, ?> boxInput();

    /**
     * @see AbstractThrowingByteFunction1
     */
    @Override
    AbstractThrowingByteFunction1<Byte, ?> boxResult();

    /**
     * @see ByteOperator1
     */
    @Override
    default ByteOperator1 handle(final H handler) {
        return (final byte value) -> {
            try {
                return this.applyAsByte(value);
            } catch (final Throwable t) {
                return handler.onThrown(t, value);
            }
        };
    }

    /**
     * @see ByteOperator1
     */
    @Override
    ByteOperator1 swallow();

    /**
     * @see AbstractThrowingToByteFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingToByteFunction1<V, ?> compose(final Function1<? super V, ? extends Byte> before) {
        return (AbstractThrowingToByteFunction1<V, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractThrowingToByteFunction1
     */
    @Override
    <V> AbstractThrowingToByteFunction1<V, ?> composeUnchecked(final Function1<? super V, ? extends Byte> before);

    /**
     * @see AbstractThrowingByteFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingByteFunction1<V, ?> andThen(final Function1<? super Byte, ? extends V> after) {
        return (AbstractThrowingByteFunction1<V, ?>) AbstractThrowingOperatorN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingByteFunction1
     */
    @Override
    <V> AbstractThrowingByteFunction1<V, ?> andThenUnchecked(final Function1<? super Byte, ? extends V> after);

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     */
    @FunctionalInterface
    interface Handler extends AbstractThrowingToByteFunctionN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param value the operand
         * @return the handled result
         */
        byte onThrown(final Throwable t, final byte value);

        @Override
        default byte onThrownAsByteUnchecked(final Throwable t, final Object... args) {
            return this.onThrown(t, (byte) args[0]);
        }
    }
}
