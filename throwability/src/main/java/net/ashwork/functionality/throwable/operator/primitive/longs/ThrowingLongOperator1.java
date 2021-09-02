/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.operator.primitive.longs;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.operator.primitive.longs.LongOperator1;
import net.ashwork.functionality.throwable.abstracts.operator.primitive.longs.AbstractThrowingLongOperator1;
import net.ashwork.functionality.throwable.operator.ThrowingOperator1;
import net.ashwork.functionality.throwable.operator.ThrowingOperatorN;
import net.ashwork.functionality.throwable.primitive.longs.ThrowingLongFunction1;
import net.ashwork.functionality.throwable.primitive.longs.ThrowingToLongFunction1;

/**
 * Represents an operation that accepts a {@code long}-valued operand and produces a result of the same type as its operand or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingOperatorN}.
 * This is the {@code long}-consuming primitive specialization of {@link ThrowingToLongFunction1}.
 * This is the {@code long}-producing primitive specialization of {@link ThrowingLongFunction1}.
 * This is the throwing variation of {@link LongOperator1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsLong(long)}.
 *
 * @see ThrowingOperatorN
 * @see ThrowingToLongFunction1
 * @see ThrowingLongFunction1
 * @see LongOperator1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingLongOperator1 extends AbstractThrowingLongOperator1<AbstractThrowingLongOperator1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param operator the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see LongOperator1
     */
    static ThrowingLongOperator1 from(final LongOperator1 operator) {
        return operator::applyAsLong;
    }

    /**
     * @see ThrowingOperator1
     */
    @Override
    default ThrowingOperator1<Long> box() {
        return this::applyAsLong;
    }

    /**
     * @see ThrowingToLongFunction1
     */
    @Override
    default ThrowingToLongFunction1<Long> boxInput() {
        return this::applyAsLong;
    }

    /**
     * @see LongOperator1
     */
    @Override
    default ThrowingLongFunction1<Long> boxResult() {
        return this::applyAsLong;
    }

    /**
     * @see LongOperator1
     */
    @Override
    default LongOperator1 swallow() {
        return this.handle((t, value) -> 0L);
    }

    /**
     * @see ThrowingToLongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToLongFunction1<V> compose(final Function1<? super V, ? extends Long> before) {
        return (ThrowingToLongFunction1<V>) AbstractThrowingLongOperator1.super.compose(before);
    }

    /**
     * @see ThrowingToLongFunction1
     */
    @Override
    default <V> ThrowingToLongFunction1<V> composeUnchecked(final Function1<? super V, ? extends Long> before) {
        return (final V v) -> this.applyAsLong(before.apply(v));
    }

    /**
     * @see ThrowingLongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingLongFunction1<V> andThen(final Function1<? super Long, ? extends V> after) {
        return (ThrowingLongFunction1<V>) AbstractThrowingLongOperator1.super.andThen(after);
    }

    /**
     * @see ThrowingLongFunction1
     */
    @Override
    default <V> ThrowingLongFunction1<V> andThenUnchecked(final Function1<? super Long, ? extends V> after) {
        return (final long value) -> after.apply(this.applyAsLong(value));
    }
}
