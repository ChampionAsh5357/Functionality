/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.operator.primitive.ints;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.operator.primitive.ints.IntOperator2;
import net.ashwork.functionality.throwable.ThrowingFunction2;
import net.ashwork.functionality.throwable.abstracts.operator.primitive.ints.AbstractThrowingIntOperator2;
import net.ashwork.functionality.throwable.operator.ThrowingOperator2;
import net.ashwork.functionality.throwable.operator.ThrowingOperatorN;
import net.ashwork.functionality.throwable.primitive.ints.ThrowingToIntFunction2;

/**
 * Represents an operation that accepts two {@code int}-valued operands and produces a result of the same type as its operands or throws a throwable.
 * This is the two-arity specialization of {@link ThrowingOperatorN}.
 * This is the {@code int}-consuming primitive specialization of {@link ThrowingToIntFunction2}.
 * This is the throwing variation of {@link IntOperator2}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @see ThrowingOperatorN
 * @see ThrowingToIntFunction2
 * @see IntOperator2
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingIntOperator2 extends AbstractThrowingIntOperator2<AbstractThrowingIntOperator2.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param operator the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see IntOperator2
     */
    static ThrowingIntOperator2 from(final IntOperator2 operator) {
        return operator::applyAsInt;
    }

    /**
     * @see ThrowingOperator2
     */
    @Override
    default ThrowingOperator2<Integer> box() {
        return this::applyAsInt;
    }

    /**
     * @see ThrowingToIntFunction2
     */
    @Override
    default ThrowingToIntFunction2<Integer, Integer> boxInput() {
        return this::applyAsInt;
    }

    /**
     * @see IntOperator2
     */
    @Override
    default IntOperator2 swallow() {
        return this.handle((t, value1, value2) -> 0);
    }

    /**
     * @see ThrowingFunction2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction2<Integer, Integer, V> andThen(final Function1<? super Integer, ? extends V> after) {
        return (ThrowingFunction2<Integer, Integer, V>) AbstractThrowingIntOperator2.super.andThen(after);
    }

    /**
     * @see ThrowingFunction2
     */
    @Override
    default <V> ThrowingFunction2<Integer, Integer, V> andThenUnchecked(final Function1<? super Integer, ? extends V> after) {
        return (final Integer value1, final Integer value2) -> after.apply(this.applyAsInt(value1, value2));
    }
}
