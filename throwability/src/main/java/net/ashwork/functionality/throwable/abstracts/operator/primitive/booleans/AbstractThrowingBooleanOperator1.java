/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.operator.primitive.booleans;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.operator.primitive.booleans.BooleanOperator1;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedAll;
import net.ashwork.functionality.throwable.abstracts.operator.AbstractThrowingOperator1;
import net.ashwork.functionality.throwable.abstracts.operator.AbstractThrowingOperatorN;
import net.ashwork.functionality.throwable.abstracts.primitive.booleans.AbstractThrowingBooleanFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.booleans.AbstractThrowingToBooleanFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.booleans.AbstractThrowingToBooleanFunctionN;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents an operation that accepts a {@code boolean}-valued operand and produces a result of the same type as its operand or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingOperatorN}.
 * This is the {@code boolean}-consuming primitive specialization of {@link AbstractThrowingToBooleanFunction1}.
 * This is the {@code boolean}-producing primitive specialization of {@link AbstractThrowingBooleanFunction1}.
 * This is the throwing variation of {@link BooleanOperator1}.
 *
 * @apiNote
 * This is an abstract operator and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the operator
 *
 * @see AbstractThrowingOperatorN
 * @see AbstractThrowingToBooleanFunction1
 * @see AbstractThrowingBooleanFunction1
 * @see BooleanOperator1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingBooleanOperator1<H extends AbstractThrowingBooleanOperator1.Handler> extends AbstractThrowingOperatorN<Boolean, H>, AbstractThrowingToBooleanFunctionN<H>, InputChainableInput<Boolean>, UnboxedAll<AbstractThrowingOperator1<Boolean, ?>, AbstractThrowingToBooleanFunction1<Boolean, ?>, AbstractThrowingBooleanFunction1<Boolean, ?>> {

    /**
     * Applies this operator to the given operand or throws a throwable.
     *
     * @param value the operand
     * @return the operator result
     */
    boolean applyAsBoolean(final boolean value) throws Throwable;

    @Override
    default boolean applyAllAsBooleanUnchecked(final Object... args) throws Throwable {
        return this.applyAsBoolean((boolean) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see AbstractThrowingOperator1
     */
    @Override
    AbstractThrowingOperator1<Boolean, ?> box();

    /**
     * @see AbstractThrowingToBooleanFunction1
     */
    @Override
    AbstractThrowingToBooleanFunction1<Boolean, ?> boxInput();

    /**
     * @see AbstractThrowingBooleanFunction1
     */
    @Override
    AbstractThrowingBooleanFunction1<Boolean, ?> boxResult();

    /**
     * @see BooleanOperator1
     */
    @Override
    default BooleanOperator1 handle(final H handler) {
        return (final boolean value) -> {
            try {
                return this.applyAsBoolean(value);
            } catch (final Throwable t) {
                return handler.onThrown(t, value);
            }
        };
    }

    /**
     * @see BooleanOperator1
     */
    @Override
    BooleanOperator1 swallow();

    /**
     * @see AbstractThrowingToBooleanFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingToBooleanFunction1<V, ?> compose(final Function1<? super V, ? extends Boolean> before) {
        return (AbstractThrowingToBooleanFunction1<V, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractThrowingToBooleanFunction1
     */
    @Override
    <V> AbstractThrowingToBooleanFunction1<V, ?> composeUnchecked(final Function1<? super V, ? extends Boolean> before);

    /**
     * @see AbstractThrowingBooleanFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingBooleanFunction1<V, ?> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (AbstractThrowingBooleanFunction1<V, ?>) AbstractThrowingOperatorN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingBooleanFunction1
     */
    @Override
    <V> AbstractThrowingBooleanFunction1<V, ?> andThenUnchecked(final Function1<? super Boolean, ? extends V> after);

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     */
    @FunctionalInterface
    interface Handler extends AbstractThrowingToBooleanFunctionN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param value the operand
         * @return the handled result
         */
        boolean onThrown(final Throwable t, final boolean value);

        @Override
        default boolean onThrownAsBooleanUnchecked(final Throwable t, final Object... args) {
            return this.onThrown(t, (boolean) args[0]);
        }
    }
}
