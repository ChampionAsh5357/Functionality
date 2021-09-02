/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.operator.primitive.doubles;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.operator.primitive.doubles.DoubleOperator2;
import net.ashwork.functionality.throwable.ThrowingFunction2;
import net.ashwork.functionality.throwable.abstracts.operator.primitive.doubles.AbstractThrowingDoubleOperator2;
import net.ashwork.functionality.throwable.operator.ThrowingOperator2;
import net.ashwork.functionality.throwable.operator.ThrowingOperatorN;
import net.ashwork.functionality.throwable.primitive.doubles.ThrowingToDoubleFunction2;

/**
 * Represents an operation that accepts two {@code double}-valued operands and produces a result of the same type as its operands or throws a throwable.
 * This is the two-arity specialization of {@link ThrowingOperatorN}.
 * This is the {@code double}-consuming primitive specialization of {@link ThrowingToDoubleFunction2}.
 * This is the throwing variation of {@link DoubleOperator2}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @see ThrowingOperatorN
 * @see ThrowingToDoubleFunction2
 * @see DoubleOperator2
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingDoubleOperator2 extends AbstractThrowingDoubleOperator2<AbstractThrowingDoubleOperator2.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param operator the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see DoubleOperator2
     */
    static ThrowingDoubleOperator2 from(final DoubleOperator2 operator) {
        return operator::applyAsDouble;
    }

    /**
     * @see ThrowingOperator2
     */
    @Override
    default ThrowingOperator2<Double> box() {
        return this::applyAsDouble;
    }

    /**
     * @see ThrowingToDoubleFunction2
     */
    @Override
    default ThrowingToDoubleFunction2<Double, Double> boxInput() {
        return this::applyAsDouble;
    }

    /**
     * @see DoubleOperator2
     */
    @Override
    default DoubleOperator2 swallow() {
        return this.handle((t, value1, value2) -> 0.0d);
    }

    /**
     * @see ThrowingFunction2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction2<Double, Double, V> andThen(final Function1<? super Double, ? extends V> after) {
        return (ThrowingFunction2<Double, Double, V>) AbstractThrowingDoubleOperator2.super.andThen(after);
    }

    /**
     * @see ThrowingFunction2
     */
    @Override
    default <V> ThrowingFunction2<Double, Double, V> andThenUnchecked(final Function1<? super Double, ? extends V> after) {
        return (final Double value1, final Double value2) -> after.apply(this.applyAsDouble(value1, value2));
    }
}
