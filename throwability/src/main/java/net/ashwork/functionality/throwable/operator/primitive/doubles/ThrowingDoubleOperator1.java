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
import net.ashwork.functionality.operator.primitive.doubles.DoubleOperator1;
import net.ashwork.functionality.throwable.abstracts.operator.primitive.doubles.AbstractThrowingDoubleOperator1;
import net.ashwork.functionality.throwable.operator.ThrowingOperator1;
import net.ashwork.functionality.throwable.operator.ThrowingOperatorN;
import net.ashwork.functionality.throwable.primitive.doubles.ThrowingDoubleFunction1;
import net.ashwork.functionality.throwable.primitive.doubles.ThrowingToDoubleFunction1;

/**
 * Represents an operation that accepts a {@code double}-valued operand and produces a result of the same type as its operand or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingOperatorN}.
 * This is the {@code double}-consuming primitive specialization of {@link ThrowingToDoubleFunction1}.
 * This is the {@code double}-producing primitive specialization of {@link ThrowingDoubleFunction1}.
 * This is the throwing variation of {@link DoubleOperator1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsDouble(double)}.
 *
 * @see ThrowingOperatorN
 * @see ThrowingToDoubleFunction1
 * @see ThrowingDoubleFunction1
 * @see DoubleOperator1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingDoubleOperator1 extends AbstractThrowingDoubleOperator1<AbstractThrowingDoubleOperator1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param operator the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see DoubleOperator1
     */
    static ThrowingDoubleOperator1 from(final DoubleOperator1 operator) {
        return operator::applyAsDouble;
    }

    /**
     * @see ThrowingOperator1
     */
    @Override
    default ThrowingOperator1<Double> box() {
        return this::applyAsDouble;
    }

    /**
     * @see ThrowingToDoubleFunction1
     */
    @Override
    default ThrowingToDoubleFunction1<Double> boxInput() {
        return this::applyAsDouble;
    }

    /**
     * @see DoubleOperator1
     */
    @Override
    default ThrowingDoubleFunction1<Double> boxResult() {
        return this::applyAsDouble;
    }

    /**
     * @see DoubleOperator1
     */
    @Override
    default DoubleOperator1 swallow() {
        return this.handle((t, value) -> 0.0d);
    }

    /**
     * @see ThrowingToDoubleFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToDoubleFunction1<V> compose(final Function1<? super V, ? extends Double> before) {
        return (ThrowingToDoubleFunction1<V>) AbstractThrowingDoubleOperator1.super.compose(before);
    }

    /**
     * @see ThrowingToDoubleFunction1
     */
    @Override
    default <V> ThrowingToDoubleFunction1<V> composeUnchecked(final Function1<? super V, ? extends Double> before) {
        return (final V v) -> this.applyAsDouble(before.apply(v));
    }

    /**
     * @see ThrowingDoubleFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingDoubleFunction1<V> andThen(final Function1<? super Double, ? extends V> after) {
        return (ThrowingDoubleFunction1<V>) AbstractThrowingDoubleOperator1.super.andThen(after);
    }

    /**
     * @see ThrowingDoubleFunction1
     */
    @Override
    default <V> ThrowingDoubleFunction1<V> andThenUnchecked(final Function1<? super Double, ? extends V> after) {
        return (final double value) -> after.apply(this.applyAsDouble(value));
    }
}
