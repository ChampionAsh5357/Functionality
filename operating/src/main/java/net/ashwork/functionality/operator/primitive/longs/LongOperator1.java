/*
 * Operating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.operator.primitive.longs;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.operator.Operator1;
import net.ashwork.functionality.operator.OperatorN;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedAll;
import net.ashwork.functionality.partial.Variant;
import net.ashwork.functionality.primitive.longs.LongFunction1;
import net.ashwork.functionality.primitive.longs.ToLongFunction1;
import net.ashwork.functionality.primitive.longs.ToLongFunctionN;

import java.util.function.LongUnaryOperator;

/**
 * Represents an operation that accepts a {@code long}-valued operand and produces a result of the same type as its operand.
 * This is the one-arity specialization of {@link OperatorN}.
 * This is the {@code long}-consuming primitive specialization of {@link ToLongFunction1}.
 * This is the {@code long}-producing primitive specialization of {@link LongFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsLong(long)}.
 *
 * @see OperatorN
 * @see ToLongFunction1
 * @see LongFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface LongOperator1 extends OperatorN<Long>, ToLongFunctionN, InputChainableInput<Long>, UnboxedAll<Operator1<Long>, ToLongFunction1<Long>, LongFunction1<Long>>, Variant<LongUnaryOperator> {

    /**
     * Applies this operator to the given operand.
     *
     * @param value the operand
     * @return the operator result
     */
    long applyAsLong(final long value);

    @Override
    default long applyAllAsLongUnchecked(final Object... args) {
        return this.applyAsLong((long) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * Creates an instance of this object from its {@link LongUnaryOperator} variant.
     *
     * @param operator the variant of this object
     * @return an instance of this object
     *
     * @see LongUnaryOperator
     */
    static LongOperator1 fromVariant(final LongUnaryOperator operator) {
        return operator::applyAsLong;
    }

    /**
     * @see LongUnaryOperator
     */
    @Override
    default LongUnaryOperator toVariant() {
        return this::applyAsLong;
    }

    /**
     * @see Operator1
     */
    @Override
    default Operator1<Long> box() {
        return this::applyAsLong;
    }

    /**
     * @see ToLongFunction1
     */
    @Override
    default ToLongFunction1<Long> boxInput() {
        return this::applyAsLong;
    }

    /**
     * @see LongFunction1
     */
    @Override
    default LongFunction1<Long> boxResult() {
        return this::applyAsLong;
    }

    /**
     * @see ToLongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToLongFunction1<V> compose(final Function1<? super V, ? extends Long> before) {
        return (ToLongFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToLongFunction1
     */
    @Override
    default <V> ToLongFunction1<V> composeUnchecked(final Function1<? super V, ? extends Long> before) {
        return (final V v) -> this.applyAsLong(before.apply(v));
    }

    /**
     * @see LongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> LongFunction1<V> andThen(final Function1<? super Long, ? extends V> after) {
        return (LongFunction1<V>) ToLongFunctionN.super.andThen(after);
    }

    /**
     * @see LongFunction1
     */
    @Override
    default <V> LongFunction1<V> andThenUnchecked(final Function1<? super Long, ? extends V> after) {
        return (final long value) -> after.apply(this.applyAsLong(value));
    }
}
