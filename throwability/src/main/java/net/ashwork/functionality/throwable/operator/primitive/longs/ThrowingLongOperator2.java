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
import net.ashwork.functionality.operator.primitive.longs.LongOperator2;
import net.ashwork.functionality.throwable.ThrowingFunction2;
import net.ashwork.functionality.throwable.abstracts.operator.primitive.longs.AbstractThrowingLongOperator2;
import net.ashwork.functionality.throwable.operator.ThrowingOperator2;
import net.ashwork.functionality.throwable.operator.ThrowingOperatorN;
import net.ashwork.functionality.throwable.primitive.longs.ThrowingToLongFunction2;

/**
 * Represents an operation that accepts two {@code long}-valued operands and produces a result of the same type as its operands or throws a throwable.
 * This is the two-arity specialization of {@link ThrowingOperatorN}.
 * This is the {@code long}-consuming primitive specialization of {@link ThrowingToLongFunction2}.
 * This is the throwing variation of {@link LongOperator2}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @see ThrowingOperatorN
 * @see ThrowingToLongFunction2
 * @see LongOperator2
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingLongOperator2 extends AbstractThrowingLongOperator2<AbstractThrowingLongOperator2.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param operator the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see LongOperator2
     */
    static ThrowingLongOperator2 from(final LongOperator2 operator) {
        return operator::applyAsLong;
    }

    /**
     * @see ThrowingOperator2
     */
    @Override
    default ThrowingOperator2<Long> box() {
        return this::applyAsLong;
    }

    /**
     * @see ThrowingToLongFunction2
     */
    @Override
    default ThrowingToLongFunction2<Long, Long> boxInput() {
        return this::applyAsLong;
    }

    /**
     * @see LongOperator2
     */
    @Override
    default LongOperator2 swallow() {
        return this.handle((t, value1, value2) -> 0L);
    }

    /**
     * @see ThrowingFunction2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction2<Long, Long, V> andThen(final Function1<? super Long, ? extends V> after) {
        return (ThrowingFunction2<Long, Long, V>) AbstractThrowingLongOperator2.super.andThen(after);
    }

    /**
     * @see ThrowingFunction2
     */
    @Override
    default <V> ThrowingFunction2<Long, Long, V> andThenUnchecked(final Function1<? super Long, ? extends V> after) {
        return (final Long value1, final Long value2) -> after.apply(this.applyAsLong(value1, value2));
    }
}
