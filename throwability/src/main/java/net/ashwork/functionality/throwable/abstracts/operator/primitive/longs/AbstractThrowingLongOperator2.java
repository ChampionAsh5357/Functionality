/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.operator.primitive.longs;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.operator.primitive.longs.LongOperator2;
import net.ashwork.functionality.partial.Unboxed;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction2;
import net.ashwork.functionality.throwable.abstracts.operator.AbstractThrowingOperator2;
import net.ashwork.functionality.throwable.abstracts.operator.AbstractThrowingOperatorN;
import net.ashwork.functionality.throwable.abstracts.primitive.longs.AbstractThrowingToLongFunction2;
import net.ashwork.functionality.throwable.abstracts.primitive.longs.AbstractThrowingToLongFunctionN;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents an operation that accepts two {@code long}-valued operands and produces a result of the same type as its operands or throws a throwable.
 * This is the two-arity specialization of {@link AbstractThrowingOperatorN}.
 * This is the {@code long}-consuming primitive specialization of {@link AbstractThrowingToLongFunction2}.
 * This is the throwing variation of {@link LongOperator2}.
 *
 * @apiNote
 * This is an abstract operator and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the operator
 *
 * @see AbstractThrowingOperatorN
 * @see AbstractThrowingToLongFunction2
 * @see LongOperator2
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingLongOperator2<H extends AbstractThrowingLongOperator2.Handler> extends AbstractThrowingOperatorN<Long, H>, AbstractThrowingToLongFunctionN<H>, Unboxed<AbstractThrowingOperator2<Long, ?>>, UnboxedInput<AbstractThrowingToLongFunction2<Long, Long, ?>> {

    /**
     * Applies this operator to the given operands or throws a throwable.
     *
     * @param value1 the first operand
     * @param value2 the second operand
     * @return the operator result
     * @throws Throwable if the operation cannot be computed
     */
    long applyAsLong(final long value1, final long value2) throws Throwable;

    @Override
    default long applyAllAsLongUnchecked(final Object... args) throws Throwable {
        return this.applyAsLong((long) args[0], (long) args[1]);
    }

    @Override
    default int arity() {
        return 2;
    }

    /**
     * @see AbstractThrowingOperator2
     */
    @Override
    AbstractThrowingOperator2<Long, ?> box();

    /**
     * @see AbstractThrowingToLongFunction2
     */
    @Override
    AbstractThrowingToLongFunction2<Long, Long, ?> boxInput();

    /**
     * @see LongOperator2
     */
    @Override
    default LongOperator2 handle(final H handler) {
        return (final long value1, final long value2) -> {
            try {
                return this.applyAsLong(value1, value2);
            } catch (final Throwable t) {
                return handler.onThrown(t, value1, value2);
            }
        };
    }

    /**
     * @see LongOperator2
     */
    @Override
    LongOperator2 swallow();

    /**
     * @see AbstractThrowingFunction2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingFunction2<Long, Long, V, ?> andThen(final Function1<? super Long, ? extends V> after) {
        return (AbstractThrowingFunction2<Long, Long, V, ?>) AbstractThrowingOperatorN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingFunction2
     */
    @Override
    <V> AbstractThrowingFunction2<Long, Long, V, ?> andThenUnchecked(final Function1<? super Long, ? extends V> after);

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     */
    @FunctionalInterface
    interface Handler extends AbstractThrowingToLongFunctionN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param value1 the first operand
         * @param value2 the second operand
         * @return the handled result
         */
        long onThrown(final Throwable t, final long value1, final long value2);

        @Override
        default long onThrownAsLongUnchecked(final Throwable t, final Object... args) {
            return this.onThrown(t, (long) args[0], (long) args[1]);
        }
    }
}
