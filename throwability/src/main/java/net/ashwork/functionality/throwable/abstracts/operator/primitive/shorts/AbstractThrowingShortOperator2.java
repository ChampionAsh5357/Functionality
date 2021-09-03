/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.operator.primitive.shorts;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.operator.primitive.shorts.ShortOperator2;
import net.ashwork.functionality.partial.Unboxed;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction2;
import net.ashwork.functionality.throwable.abstracts.operator.AbstractThrowingOperator2;
import net.ashwork.functionality.throwable.abstracts.operator.AbstractThrowingOperatorN;
import net.ashwork.functionality.throwable.abstracts.primitive.shorts.AbstractThrowingToShortFunction2;
import net.ashwork.functionality.throwable.abstracts.primitive.shorts.AbstractThrowingToShortFunctionN;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents an operation that accepts two {@code short}-valued operands and produces a result of the same type as its operands or throws a throwable.
 * This is the two-arity specialization of {@link AbstractThrowingOperatorN}.
 * This is the {@code short}-consuming primitive specialization of {@link AbstractThrowingToShortFunction2}.
 * This is the throwing variation of {@link ShortOperator2}.
 *
 * @apiNote
 * This is an abstract operator and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the operator
 *
 * @see AbstractThrowingOperatorN
 * @see AbstractThrowingToShortFunction2
 * @see ShortOperator2
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingShortOperator2<H extends AbstractThrowingShortOperator2.Handler> extends AbstractThrowingOperatorN<Short, H>, AbstractThrowingToShortFunctionN<H>, Unboxed<AbstractThrowingOperator2<Short, ?>>, UnboxedInput<AbstractThrowingToShortFunction2<Short, Short, ?>> {

    /**
     * Applies this operator to the given operands or throws a throwable.
     *
     * @param value1 the first operand
     * @param value2 the second operand
     * @return the operator result
     * @throws Throwable if the operation cannot be computed
     */
    short applyAsShort(final short value1, final short value2) throws Throwable;

    @Override
    default short applyAllAsShortUnchecked(final Object... args) throws Throwable {
        return this.applyAsShort((short) args[0], (short) args[1]);
    }

    @Override
    default int arity() {
        return 2;
    }

    /**
     * @see AbstractThrowingOperator2
     */
    @Override
    AbstractThrowingOperator2<Short, ?> box();

    /**
     * @see AbstractThrowingToShortFunction2
     */
    @Override
    AbstractThrowingToShortFunction2<Short, Short, ?> boxInput();

    /**
     * @see ShortOperator2
     */
    @Override
    default ShortOperator2 handle(final H handler) {
        return (final short value1, final short value2) -> {
            try {
                return this.applyAsShort(value1, value2);
            } catch (final Throwable t) {
                return handler.onThrown(t, value1, value2);
            }
        };
    }

    /**
     * @see ShortOperator2
     */
    @Override
    ShortOperator2 swallow();

    /**
     * @see AbstractThrowingFunction2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingFunction2<Short, Short, V, ?> andThen(final Function1<? super Short, ? extends V> after) {
        return (AbstractThrowingFunction2<Short, Short, V, ?>) AbstractThrowingOperatorN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingFunction2
     */
    @Override
    <V> AbstractThrowingFunction2<Short, Short, V, ?> andThenUnchecked(final Function1<? super Short, ? extends V> after);

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     */
    @FunctionalInterface
    interface Handler extends AbstractThrowingToShortFunctionN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param value1 the first operand
         * @param value2 the second operand
         * @return the handled result
         */
        short onThrown(final Throwable t, final short value1, final short value2);

        @Override
        default short onThrownAsShortUnchecked(final Throwable t, final Object... args) {
            return this.onThrown(t, (short) args[0], (short) args[1]);
        }
    }
}
