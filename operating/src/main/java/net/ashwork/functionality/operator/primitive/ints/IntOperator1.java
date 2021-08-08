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
import net.ashwork.functionality.operator.Operator1;
import net.ashwork.functionality.operator.OperatorN;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedAll;
import net.ashwork.functionality.partial.Variant;
import net.ashwork.functionality.primitive.ints.IntFunction1;
import net.ashwork.functionality.primitive.ints.ToIntFunction1;
import net.ashwork.functionality.primitive.ints.ToIntFunctionN;

import java.util.function.IntUnaryOperator;

/**
 * Represents an operation that accepts an {@code int}-valued operand and produces a result of the same type as its operand.
 * This is the one-arity specialization of {@link OperatorN}.
 * This is the {@code int}-consuming primitive specialization of {@link ToIntFunction1}.
 * This is the {@code int}-producing primitive specialization of {@link IntFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsInt(int)}.
 *
 * @see OperatorN
 * @see ToIntFunction1
 * @see IntFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface IntOperator1 extends OperatorN<Integer>, ToIntFunctionN, InputChainableInput<Integer>, UnboxedAll<Operator1<Integer>, ToIntFunction1<Integer>, IntFunction1<Integer>>, Variant<IntUnaryOperator> {

    /**
     * Applies this operator to the given operand.
     *
     * @param value the operand
     * @return the operator result
     */
    int applyAsInt(final int value);

    @Override
    default int applyAllAsIntUnchecked(final Object... args) {
        return this.applyAsInt((int) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * Creates an instance of this object from its {@link IntUnaryOperator} variant.
     *
     * @param operator the variant of this object
     * @return an instance of this object
     *
     * @see IntUnaryOperator
     */
    static IntOperator1 fromVariant(final IntUnaryOperator operator) {
        return operator::applyAsInt;
    }

    /**
     * @see IntUnaryOperator
     */
    @Override
    default IntUnaryOperator toVariant() {
        return this::applyAsInt;
    }

    /**
     * @see Operator1
     */
    @Override
    default Operator1<Integer> box() {
        return this::applyAsInt;
    }

    /**
     * @see ToIntFunction1
     */
    @Override
    default ToIntFunction1<Integer> boxInput() {
        return this::applyAsInt;
    }

    /**
     * @see IntFunction1
     */
    @Override
    default IntFunction1<Integer> boxResult() {
        return this::applyAsInt;
    }

    /**
     * @see ToIntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToIntFunction1<V> compose(final Function1<? super V, ? extends Integer> before) {
        return (ToIntFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToIntFunction1
     */
    @Override
    default <V> ToIntFunction1<V> composeUnchecked(final Function1<? super V, ? extends Integer> before) {
        return (final V v) -> this.applyAsInt(before.apply(v));
    }

    /**
     * @see IntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> IntFunction1<V> andThen(final Function1<? super Integer, ? extends V> after) {
        return (IntFunction1<V>) ToIntFunctionN.super.andThen(after);
    }

    /**
     * @see IntFunction1
     */
    @Override
    default <V> IntFunction1<V> andThenUnchecked(final Function1<? super Integer, ? extends V> after) {
        return (final int value) -> after.apply(this.applyAsInt(value));
    }
}
