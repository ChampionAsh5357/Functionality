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
import net.ashwork.functionality.operator.primitive.ints.IntOperator1;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedAll;
import net.ashwork.functionality.throwable.abstracts.operator.AbstractThrowingOperator1;
import net.ashwork.functionality.throwable.abstracts.operator.AbstractThrowingOperatorN;
import net.ashwork.functionality.throwable.abstracts.primitive.ints.AbstractThrowingIntFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.ints.AbstractThrowingToIntFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.ints.AbstractThrowingToIntFunctionN;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents an operation that accepts an {@code int}-valued operand and produces a result of the same type as its operand or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingOperatorN}.
 * This is the {@code int}-consuming primitive specialization of {@link AbstractThrowingToIntFunction1}.
 * This is the {@code int}-producing primitive specialization of {@link AbstractThrowingIntFunction1}.
 * This is the throwing variation of {@link IntOperator1}.
 *
 * @apiNote
 * This is an abstract operator and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the operator
 *
 * @see AbstractThrowingOperatorN
 * @see AbstractThrowingToIntFunction1
 * @see AbstractThrowingIntFunction1
 * @see IntOperator1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingIntOperator1<H extends AbstractThrowingIntOperator1.Handler> extends AbstractThrowingOperatorN<Integer, H>, AbstractThrowingToIntFunctionN<H>, InputChainableInput<Integer>, UnboxedAll<AbstractThrowingOperator1<Integer, ?>, AbstractThrowingToIntFunction1<Integer, ?>, AbstractThrowingIntFunction1<Integer, ?>> {

    /**
     * Applies this operator to the given operand or throws a throwable.
     *
     * @param value the operand
     * @return the operator result
     * @throws Throwable if the operation cannot be computed
     */
    int applyAsInt(final int value) throws Throwable;

    @Override
    default int applyAllAsIntUnchecked(final Object... args) throws Throwable {
        return this.applyAsInt((int) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see AbstractThrowingOperator1
     */
    @Override
    AbstractThrowingOperator1<Integer, ?> box();

    /**
     * @see AbstractThrowingToIntFunction1
     */
    @Override
    AbstractThrowingToIntFunction1<Integer, ?> boxInput();

    /**
     * @see AbstractThrowingIntFunction1
     */
    @Override
    AbstractThrowingIntFunction1<Integer, ?> boxResult();

    /**
     * @see IntOperator1
     */
    @Override
    default IntOperator1 handle(final H handler) {
        return (final int value) -> {
            try {
                return this.applyAsInt(value);
            } catch (final Throwable t) {
                return handler.onThrown(t, value);
            }
        };
    }

    /**
     * @see IntOperator1
     */
    @Override
    IntOperator1 swallow();

    /**
     * @see AbstractThrowingToIntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingToIntFunction1<V, ?> compose(final Function1<? super V, ? extends Integer> before) {
        return (AbstractThrowingToIntFunction1<V, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractThrowingToIntFunction1
     */
    @Override
    <V> AbstractThrowingToIntFunction1<V, ?> composeUnchecked(final Function1<? super V, ? extends Integer> before);

    /**
     * @see AbstractThrowingIntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingIntFunction1<V, ?> andThen(final Function1<? super Integer, ? extends V> after) {
        return (AbstractThrowingIntFunction1<V, ?>) AbstractThrowingOperatorN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingIntFunction1
     */
    @Override
    <V> AbstractThrowingIntFunction1<V, ?> andThenUnchecked(final Function1<? super Integer, ? extends V> after);

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
         * @param value the operand
         * @return the handled result
         */
        int onThrown(final Throwable t, final int value);

        @Override
        default int onThrownAsIntUnchecked(final Throwable t, final Object... args) {
            return this.onThrown(t, (int) args[0]);
        }
    }
}
