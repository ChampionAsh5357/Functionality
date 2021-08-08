/*
 * Operating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.operator.primitive.ints;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.Function2;
import net.ashwork.functionality.operator.Operator2;
import net.ashwork.functionality.operator.OperatorN;
import net.ashwork.functionality.partial.Unboxed;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.partial.Variant;
import net.ashwork.functionality.primitive.ints.ToIntFunction2;
import net.ashwork.functionality.primitive.ints.ToIntFunctionN;

import java.util.function.IntBinaryOperator;

/**
 * Represents an operation that accepts two {@code int}-valued operands and produces a result of the same type as its operands.
 * This is the two-arity specialization of {@link OperatorN}.
 * This is the {@code int}-consuming primitive specialization of {@link ToIntFunction2}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsInt(int, int)}.
 *
 * @see OperatorN
 * @see ToIntFunction2
 * @since 1.0.0
 */
@FunctionalInterface
public interface IntOperator2 extends OperatorN<Integer>, ToIntFunctionN, Unboxed<Operator2<Integer>>, UnboxedInput<ToIntFunction2<Integer, Integer>>, Variant<IntBinaryOperator> {

    /**
     * Applies this operator to the given operands.
     *
     * @param value1 the first operand
     * @param value2 the second operand
     * @return the operator result
     */
    int applyAsInt(final int value1, final int value2);

    @Override
    default int applyAllAsIntUnchecked(final Object... args) {
        return this.applyAsInt((int) args[0], (int) args[1]);
    }

    @Override
    default int arity() {
        return 2;
    }

    /**
     * Creates an instance of this object from its {@link IntBinaryOperator} variant.
     *
     * @param operator the variant of this object
     * @return an instance of this object
     *
     * @see IntBinaryOperator
     */
    static IntOperator2 fromVariant(final IntBinaryOperator operator) {
        return operator::applyAsInt;
    }

    /**
     * @see IntBinaryOperator
     */
    @Override
    default IntBinaryOperator toVariant() {
        return this::applyAsInt;
    }

    /**
     * @see Operator2
     */
    @Override
    default Operator2<Integer> box() {
        return this::applyAsInt;
    }

    /**
     * @see ToIntFunction2
     */
    @Override
    default ToIntFunction2<Integer, Integer> boxInput() {
        return this::applyAsInt;
    }

    /**
     * @see Function2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Function2<Integer, Integer, V> andThen(final Function1<? super Integer, ? extends V> after) {
        return (Function2<Integer, Integer, V>) ToIntFunctionN.super.andThen(after);
    }

    /**
     * @see Function2
     */
    @Override
    default <V> Function2<Integer, Integer, V> andThenUnchecked(final Function1<? super Integer, ? extends V> after) {
        return (final Integer value1, final Integer value2) -> after.apply(this.applyAsInt(value1, value2));
    }
}
