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
import net.ashwork.functionality.operator.Operator1;
import net.ashwork.functionality.operator.OperatorN;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedAll;
import net.ashwork.functionality.partial.Variant;
import net.ashwork.functionality.primitive.doubles.DoubleFunction1;
import net.ashwork.functionality.primitive.doubles.ToDoubleFunction1;
import net.ashwork.functionality.primitive.doubles.ToDoubleFunctionN;

import java.util.function.DoubleUnaryOperator;

/**
 * Represents an operation that accepts a {@code double}-valued operand and produces a result of the same type as its operand.
 * This is the one-arity specialization of {@link OperatorN}.
 * This is the {@code double}-consuming primitive specialization of {@link ToDoubleFunction1}.
 * This is the {@code double}-producing primitive specialization of {@link DoubleFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsDouble(double)}.
 *
 * @see OperatorN
 * @see ToDoubleFunction1
 * @see DoubleFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface DoubleOperator1 extends OperatorN<Double>, ToDoubleFunctionN, InputChainableInput<Double>, UnboxedAll<Operator1<Double>, ToDoubleFunction1<Double>, DoubleFunction1<Double>>, Variant<DoubleUnaryOperator> {

    /**
     * Applies this operator to the given operand.
     *
     * @param value the operand
     * @return the operator result
     */
    double applyAsDouble(final double value);

    @Override
    default double applyAllAsDoubleUnchecked(final Object... args) {
        return this.applyAsDouble((double) args[0]);
    }
    
    @Override
    default int arity() {
        return 1;
    }

    /**
     * Creates an instance of this object from its {@link DoubleUnaryOperator} variant.
     *
     * @param operator the variant of this object
     * @return an instance of this object
     *
     * @see DoubleUnaryOperator
     */
    static DoubleOperator1 fromVariant(final DoubleUnaryOperator operator) {
        return operator::applyAsDouble;
    }

    /**
     * @see DoubleUnaryOperator
     */
    @Override
    default DoubleUnaryOperator toVariant() {
        return this::applyAsDouble;
    }

    /**
     * @see Operator1
     */
    @Override
    default Operator1<Double> box() {
        return this::applyAsDouble;
    }

    /**
     * @see ToDoubleFunction1
     */
    @Override
    default ToDoubleFunction1<Double> boxInput() {
        return this::applyAsDouble;
    }

    /**
     * @see DoubleFunction1
     */
    @Override
    default DoubleFunction1<Double> boxResult() {
        return this::applyAsDouble;
    }

    /**
     * @see ToDoubleFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToDoubleFunction1<V> compose(final Function1<? super V, ? extends Double> before) {
        return (ToDoubleFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToDoubleFunction1
     */
    @Override
    default <V> ToDoubleFunction1<V> composeUnchecked(final Function1<? super V, ? extends Double> before) {
        return (final V v) -> this.applyAsDouble(before.apply(v));
    }

    /**
     * @see DoubleFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> DoubleFunction1<V> andThen(final Function1<? super Double, ? extends V> after) {
        return (DoubleFunction1<V>) ToDoubleFunctionN.super.andThen(after);
    }

    /**
     * @see DoubleFunction1
     */
    @Override
    default <V> DoubleFunction1<V> andThenUnchecked(final Function1<? super Double, ? extends V> after) {
        return (final double value) -> after.apply(this.applyAsDouble(value));
    }
}
