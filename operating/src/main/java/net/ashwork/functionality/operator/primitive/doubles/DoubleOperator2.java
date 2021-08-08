/*
 * Operating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.operator.primitive.doubles;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.Function2;
import net.ashwork.functionality.operator.Operator2;
import net.ashwork.functionality.operator.OperatorN;
import net.ashwork.functionality.partial.Unboxed;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.partial.Variant;
import net.ashwork.functionality.primitive.doubles.ToDoubleFunction2;
import net.ashwork.functionality.primitive.doubles.ToDoubleFunctionN;

import java.util.function.DoubleBinaryOperator;

/**
 * Represents an operation that accepts two {@code double}-valued operands and produces a result of the same type as its operands.
 * This is the two-arity specialization of {@link OperatorN}.
 * This is the {@code double}-consuming primitive specialization of {@link ToDoubleFunction2}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsDouble(double, double)}.
 *
 * @see OperatorN
 * @see ToDoubleFunction2
 * @since 1.0.0
 */
@FunctionalInterface
public interface DoubleOperator2 extends OperatorN<Double>, ToDoubleFunctionN, Unboxed<Operator2<Double>>, UnboxedInput<ToDoubleFunction2<Double, Double>>, Variant<DoubleBinaryOperator> {

    /**
     * Applies this operator to the given operands.
     *
     * @param value1 the first operand
     * @param value2 the second operand
     * @return the operator result
     */
    double applyAsDouble(final double value1, final double value2);

    @Override
    default double applyAllAsDoubleUnchecked(final Object... args) {
        return this.applyAsDouble((double) args[0], (double) args[1]);
    }

    @Override
    default int arity() {
        return 2;
    }

    /**
     * Creates an instance of this object from its {@link DoubleBinaryOperator} variant.
     *
     * @param operator the variant of this object
     * @return an instance of this object
     *
     * @see DoubleBinaryOperator
     */
    static DoubleOperator2 fromVariant(final DoubleBinaryOperator operator) {
        return operator::applyAsDouble;
    }

    /**
     * @see DoubleBinaryOperator
     */
    @Override
    default DoubleBinaryOperator toVariant() {
        return this::applyAsDouble;
    }

    /**
     * @see Operator2
     */
    @Override
    default Operator2<Double> box() {
        return this::applyAsDouble;
    }

    /**
     * @see ToDoubleFunction2
     */
    @Override
    default ToDoubleFunction2<Double, Double> boxInput() {
        return this::applyAsDouble;
    }

    /**
     * @see Function2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Function2<Double, Double, V> andThen(final Function1<? super Double, ? extends V> after) {
        return (Function2<Double, Double, V>) ToDoubleFunctionN.super.andThen(after);
    }

    /**
     * @see Function2
     */
    @Override
    default <V> Function2<Double, Double, V> andThenUnchecked(final Function1<? super Double, ? extends V> after) {
        return (final Double value1, final Double value2) -> after.apply(this.applyAsDouble(value1, value2));
    }
}
