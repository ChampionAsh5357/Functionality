/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.operator.primitive.doubles;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.operator.primitive.doubles.DoubleOperator2;
import net.ashwork.functionality.partial.Unboxed;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction2;
import net.ashwork.functionality.throwable.abstracts.operator.AbstractThrowingOperator2;
import net.ashwork.functionality.throwable.abstracts.operator.AbstractThrowingOperatorN;
import net.ashwork.functionality.throwable.abstracts.primitive.doubles.AbstractThrowingToDoubleFunction2;
import net.ashwork.functionality.throwable.abstracts.primitive.doubles.AbstractThrowingToDoubleFunctionN;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents an operation that accepts two {@code double}-valued operands and produces a result of the same type as its operands or throws a throwable.
 * This is the two-arity specialization of {@link AbstractThrowingOperatorN}.
 * This is the {@code double}-consuming primitive specialization of {@link AbstractThrowingToDoubleFunction2}.
 * This is the throwing variation of {@link DoubleOperator2}.
 *
 * @apiNote
 * This is an abstract operator and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the operator
 *
 * @see AbstractThrowingOperatorN
 * @see AbstractThrowingToDoubleFunction2
 * @see DoubleOperator2
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingDoubleOperator2<H extends AbstractThrowingDoubleOperator2.Handler> extends AbstractThrowingOperatorN<Double, H>, AbstractThrowingToDoubleFunctionN<H>, Unboxed<AbstractThrowingOperator2<Double, ?>>, UnboxedInput<AbstractThrowingToDoubleFunction2<Double, Double, ?>> {

    /**
     * Applies this operator to the given operands or throws a throwable.
     *
     * @param value1 the first operand
     * @param value2 the second operand
     * @return the operator result
     * @throws Throwable if the operation cannot be computed
     */
    double applyAsDouble(final double value1, final double value2) throws Throwable;

    @Override
    default double applyAllAsDoubleUnchecked(final Object... args) throws Throwable {
        return this.applyAsDouble((double) args[0], (double) args[1]);
    }

    @Override
    default int arity() {
        return 2;
    }

    /**
     * @see AbstractThrowingOperator2
     */
    @Override
    AbstractThrowingOperator2<Double, ?> box();

    /**
     * @see AbstractThrowingToDoubleFunction2
     */
    @Override
    AbstractThrowingToDoubleFunction2<Double, Double, ?> boxInput();

    /**
     * @see DoubleOperator2
     */
    @Override
    default DoubleOperator2 handle(final H handler) {
        return (final double value1, final double value2) -> {
            try {
                return this.applyAsDouble(value1, value2);
            } catch (final Throwable t) {
                return handler.onThrown(t, value1, value2);
            }
        };
    }

    /**
     * @see DoubleOperator2
     */
    @Override
    DoubleOperator2 swallow();

    /**
     * @see AbstractThrowingFunction2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingFunction2<Double, Double, V, ?> andThen(final Function1<? super Double, ? extends V> after) {
        return (AbstractThrowingFunction2<Double, Double, V, ?>) AbstractThrowingOperatorN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingFunction2
     */
    @Override
    <V> AbstractThrowingFunction2<Double, Double, V, ?> andThenUnchecked(final Function1<? super Double, ? extends V> after);

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     */
    @FunctionalInterface
    interface Handler extends AbstractThrowingToDoubleFunctionN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param value1 the first operand
         * @param value2 the second operand
         * @return the handled result
         */
        double onThrown(final Throwable t, final double value1, final double value2);

        @Override
        default double onThrownAsDoubleUnchecked(final Throwable t, final Object... args) {
            return this.onThrown(t, (double) args[0], (double) args[1]);
        }
    }
}
