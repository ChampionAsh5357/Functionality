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
import net.ashwork.functionality.Function2;
import net.ashwork.functionality.operator.Operator2;
import net.ashwork.functionality.operator.OperatorN;
import net.ashwork.functionality.partial.Unboxed;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.partial.Variant;
import net.ashwork.functionality.primitive.longs.ToLongFunction2;
import net.ashwork.functionality.primitive.longs.ToLongFunctionN;

import java.util.function.LongBinaryOperator;

/**
 * Represents an operation that accepts two {@code long}-valued operands and produces a result of the same type as its operands.
 * This is the two-arity specialization of {@link OperatorN}.
 * This is the {@code long}-consuming primitive specialization of {@link ToLongFunction2}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsLong(long, long)}.
 *
 * @see OperatorN
 * @see ToLongFunction2
 * @since 1.0.0
 */
@FunctionalInterface
public interface LongOperator2 extends OperatorN<Long>, ToLongFunctionN, Unboxed<Operator2<Long>>, UnboxedInput<ToLongFunction2<Long, Long>>, Variant<LongBinaryOperator> {

    /**
     * Applies this operator to the given operands.
     *
     * @param value1 the first operand
     * @param value2 the second operand
     * @return the operator result
     */
    long applyAsLong(final long value1, final long value2);

    @Override
    default long applyAllAsLongUnchecked(final Object... args) {
        return this.applyAsLong((long) args[0], (long) args[1]);
    }

    @Override
    default int arity() {
        return 2;
    }

    /**
     * Creates an instance of this object from its {@link LongBinaryOperator} variant.
     *
     * @param operator the variant of this object
     * @return an instance of this object
     *
     * @see LongBinaryOperator
     */
    static LongOperator2 fromVariant(final LongBinaryOperator operator) {
        return operator::applyAsLong;
    }

    /**
     * @see LongBinaryOperator
     */
    @Override
    default LongBinaryOperator toVariant() {
        return this::applyAsLong;
    }

    /**
     * @see Operator2
     */
    @Override
    default Operator2<Long> box() {
        return this::applyAsLong;
    }

    /**
     * @see ToLongFunction2
     */
    @Override
    default ToLongFunction2<Long, Long> boxInput() {
        return this::applyAsLong;
    }

    /**
     * @see Function2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Function2<Long, Long, V> andThen(final Function1<? super Long, ? extends V> after) {
        return (Function2<Long, Long, V>) ToLongFunctionN.super.andThen(after);
    }

    /**
     * @see Function2
     */
    @Override
    default <V> Function2<Long, Long, V> andThenUnchecked(final Function1<? super Long, ? extends V> after) {
        return (final Long value1, final Long value2) -> after.apply(this.applyAsLong(value1, value2));
    }
}
