/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.operator.primitive.ints;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.operator.primitive.ints.IntOperator2;
import net.ashwork.functionality.partial.Unboxed;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction2;
import net.ashwork.functionality.throwable.abstracts.operator.AbstractThrowingOperator2;
import net.ashwork.functionality.throwable.abstracts.operator.AbstractThrowingOperatorN;
import net.ashwork.functionality.throwable.abstracts.primitive.ints.AbstractThrowingToIntFunction2;
import net.ashwork.functionality.throwable.abstracts.primitive.ints.AbstractThrowingToIntFunctionN;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents an operation that accepts two {@code int}-valued operands and produces a result of the same type as its operands or throws a throwable.
 * This is the two-arity specialization of {@link AbstractThrowingOperatorN}.
 * This is the {@code int}-consuming primitive specialization of {@link AbstractThrowingToIntFunction2}.
 * This is the throwing variation of {@link IntOperator2}.
 *
 * @apiNote
 * This is an abstract operator and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the operator
 *
 * @see AbstractThrowingOperatorN
 * @see AbstractThrowingToIntFunction2
 * @see IntOperator2
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingIntOperator2<H extends AbstractThrowingIntOperator2.Handler> extends AbstractThrowingOperatorN<Integer, H>, AbstractThrowingToIntFunctionN<H>, Unboxed<AbstractThrowingOperator2<Integer, ?>>, UnboxedInput<AbstractThrowingToIntFunction2<Integer, Integer, ?>> {

    /**
     * Applies this operator to the given operands or throws a throwable.
     *
     * @param value1 the first operand
     * @param value2 the second operand
     * @return the operator result
     */
    int applyAsInt(final int value1, final int value2) throws Throwable;

    @Override
    default int applyAllAsIntUnchecked(final Object... args) throws Throwable {
        return this.applyAsInt((int) args[0], (int) args[1]);
    }

    @Override
    default int arity() {
        return 2;
    }

    /**
     * @see AbstractThrowingOperator2
     */
    @Override
    AbstractThrowingOperator2<Integer, ?> box();

    /**
     * @see AbstractThrowingToIntFunction2
     */
    @Override
    AbstractThrowingToIntFunction2<Integer, Integer, ?> boxInput();

    /**
     * @see IntOperator2
     */
    @Override
    default IntOperator2 handle(final H handler) {
        return (final int value1, final int value2) -> {
            try {
                return this.applyAsInt(value1, value2);
            } catch (final Throwable t) {
                return handler.onThrown(t, value1, value2);
            }
        };
    }

    /**
     * @see IntOperator2
     */
    @Override
    IntOperator2 swallow();

    /**
     * @see AbstractThrowingFunction2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingFunction2<Integer, Integer, V, ?> andThen(final Function1<? super Integer, ? extends V> after) {
        return (AbstractThrowingFunction2<Integer, Integer, V, ?>) AbstractThrowingOperatorN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingFunction2
     */
    @Override
    <V> AbstractThrowingFunction2<Integer, Integer, V, ?> andThenUnchecked(final Function1<? super Integer, ? extends V> after);

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     */
    @FunctionalInterface
    interface Handler extends AbstractThrowingToIntFunctionN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param value1 the first operand
         * @param value2 the second operand
         * @return the handled result
         */
        int onThrown(final Throwable t, final int value1, final int value2);

        @Override
        default int onThrownAsIntUnchecked(final Throwable t, final Object... args) {
            return this.onThrown(t, (int) args[0], (int) args[1]);
        }
    }
}
