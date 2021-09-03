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
import net.ashwork.functionality.operator.primitive.bytes.ByteOperator2;
import net.ashwork.functionality.partial.Unboxed;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction2;
import net.ashwork.functionality.throwable.abstracts.operator.AbstractThrowingOperator2;
import net.ashwork.functionality.throwable.abstracts.operator.AbstractThrowingOperatorN;
import net.ashwork.functionality.throwable.abstracts.primitive.bytes.AbstractThrowingToByteFunction2;
import net.ashwork.functionality.throwable.abstracts.primitive.bytes.AbstractThrowingToByteFunctionN;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents an operation that accepts two {@code byte}-valued operands and produces a result of the same type as its operands or throws a throwable.
 * This is the two-arity specialization of {@link AbstractThrowingOperatorN}.
 * This is the {@code byte}-consuming primitive specialization of {@link AbstractThrowingToByteFunction2}.
 * This is the throwing variation of {@link ByteOperator2}.
 *
 * @apiNote
 * This is an abstract operator and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the operator
 *
 * @see AbstractThrowingOperatorN
 * @see AbstractThrowingToByteFunction2
 * @see ByteOperator2
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingByteOperator2<H extends AbstractThrowingByteOperator2.Handler> extends AbstractThrowingOperatorN<Byte, H>, AbstractThrowingToByteFunctionN<H>, Unboxed<AbstractThrowingOperator2<Byte, ?>>, UnboxedInput<AbstractThrowingToByteFunction2<Byte, Byte, ?>> {

    /**
     * Applies this operator to the given operands or throws a throwable.
     *
     * @param value1 the first operand
     * @param value2 the second operand
     * @return the operator result
     * @throws Throwable if the operation cannot be computed
     */
    byte applyAsByte(final byte value1, final byte value2) throws Throwable;

    @Override
    default byte applyAllAsByteUnchecked(final Object... args) throws Throwable {
        return this.applyAsByte((byte) args[0], (byte) args[1]);
    }

    @Override
    default int arity() {
        return 2;
    }

    /**
     * @see AbstractThrowingOperator2
     */
    @Override
    AbstractThrowingOperator2<Byte, ?> box();

    /**
     * @see AbstractThrowingToByteFunction2
     */
    @Override
    AbstractThrowingToByteFunction2<Byte, Byte, ?> boxInput();

    /**
     * @see ByteOperator2
     */
    @Override
    default ByteOperator2 handle(final H handler) {
        return (final byte value1, final byte value2) -> {
            try {
                return this.applyAsByte(value1, value2);
            } catch (final Throwable t) {
                return handler.onThrown(t, value1, value2);
            }
        };
    }

    /**
     * @see ByteOperator2
     */
    @Override
    ByteOperator2 swallow();

    /**
     * @see AbstractThrowingFunction2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingFunction2<Byte, Byte, V, ?> andThen(final Function1<? super Byte, ? extends V> after) {
        return (AbstractThrowingFunction2<Byte, Byte, V, ?>) AbstractThrowingOperatorN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingFunction2
     */
    @Override
    <V> AbstractThrowingFunction2<Byte, Byte, V, ?> andThenUnchecked(final Function1<? super Byte, ? extends V> after);

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
         * @param value1 the first operand
         * @param value2 the second operand
         * @return the handled result
         */
        byte onThrown(final Throwable t, final byte value1, final byte value2);

        @Override
        default byte onThrownAsByteUnchecked(final Throwable t, final Object... args) {
            return this.onThrown(t, (byte) args[0], (byte) args[1]);
        }
    }
}
