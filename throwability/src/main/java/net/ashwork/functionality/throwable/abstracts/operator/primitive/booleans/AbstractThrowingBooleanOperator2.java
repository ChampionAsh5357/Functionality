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
import net.ashwork.functionality.operator.primitive.booleans.BooleanOperator2;
import net.ashwork.functionality.partial.Unboxed;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction2;
import net.ashwork.functionality.throwable.abstracts.operator.AbstractThrowingOperator2;
import net.ashwork.functionality.throwable.abstracts.operator.AbstractThrowingOperatorN;
import net.ashwork.functionality.throwable.abstracts.primitive.booleans.AbstractThrowingToBooleanFunction2;
import net.ashwork.functionality.throwable.abstracts.primitive.booleans.AbstractThrowingToBooleanFunctionN;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents an operation that accepts two {@code boolean}-valued operands and produces a result of the same type as its operands or throws a throwable.
 * This is the two-arity specialization of {@link AbstractThrowingOperatorN}.
 * This is the {@code boolean}-consuming primitive specialization of {@link AbstractThrowingToBooleanFunction2}.
 * This is the throwing variation of {@link BooleanOperator2}.
 *
 * @apiNote
 * This is an abstract operator and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the operator
 *
 * @see AbstractThrowingOperatorN
 * @see AbstractThrowingToBooleanFunction2
 * @see BooleanOperator2
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingBooleanOperator2<H extends AbstractThrowingBooleanOperator2.Handler> extends AbstractThrowingOperatorN<Boolean, H>, AbstractThrowingToBooleanFunctionN<H>, Unboxed<AbstractThrowingOperator2<Boolean, ?>>, UnboxedInput<AbstractThrowingToBooleanFunction2<Boolean, Boolean, ?>> {

    /**
     * Applies this operator to the given operands or throws a throwable.
     *
     * @param value1 the first operand
     * @param value2 the second operand
     * @return the operator result
     * @throws Throwable if the operation cannot be computed
     */
    boolean applyAsBoolean(final boolean value1, final boolean value2) throws Throwable;

    @Override
    default boolean applyAllAsBooleanUnchecked(final Object... args) throws Throwable {
        return this.applyAsBoolean((boolean) args[0], (boolean) args[1]);
    }

    @Override
    default int arity() {
        return 2;
    }

    /**
     * @see AbstractThrowingOperator2
     */
    @Override
    AbstractThrowingOperator2<Boolean, ?> box();

    /**
     * @see AbstractThrowingToBooleanFunction2
     */
    @Override
    AbstractThrowingToBooleanFunction2<Boolean, Boolean, ?> boxInput();

    /**
     * @see BooleanOperator2
     */
    @Override
    default BooleanOperator2 handle(final H handler) {
        return (final boolean value1, final boolean value2) -> {
            try {
                return this.applyAsBoolean(value1, value2);
            } catch (final Throwable t) {
                return handler.onThrown(t, value1, value2);
            }
        };
    }

    /**
     * @see BooleanOperator2
     */
    @Override
    BooleanOperator2 swallow();

    /**
     * @see AbstractThrowingFunction2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingFunction2<Boolean, Boolean, V, ?> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (AbstractThrowingFunction2<Boolean, Boolean, V, ?>) AbstractThrowingOperatorN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingFunction2
     */
    @Override
    <V> AbstractThrowingFunction2<Boolean, Boolean, V, ?> andThenUnchecked(final Function1<? super Boolean, ? extends V> after);

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
         * @param value1 the first operand
         * @param value2 the second operand
         * @return the handled result
         */
        boolean onThrown(final Throwable t, final boolean value1, final boolean value2);

        @Override
        default boolean onThrownAsBooleanUnchecked(final Throwable t, final Object... args) {
            return this.onThrown(t, (boolean) args[0], (boolean) args[1]);
        }
    }
}
