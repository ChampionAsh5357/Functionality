/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.operator.primitive.shorts;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.operator.primitive.shorts.ShortOperator2;
import net.ashwork.functionality.throwable.ThrowingFunction2;
import net.ashwork.functionality.throwable.abstracts.operator.primitive.shorts.AbstractThrowingShortOperator2;
import net.ashwork.functionality.throwable.operator.ThrowingOperator2;
import net.ashwork.functionality.throwable.operator.ThrowingOperatorN;
import net.ashwork.functionality.throwable.primitive.shorts.ThrowingToShortFunction2;

/**
 * Represents an operation that accepts two {@code short}-valued operands and produces a result of the same type as its operands or throws a throwable.
 * This is the two-arity specialization of {@link ThrowingOperatorN}.
 * This is the {@code short}-consuming primitive specialization of {@link ThrowingToShortFunction2}.
 * This is the throwing variation of {@link ShortOperator2}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @see ThrowingOperatorN
 * @see ThrowingToShortFunction2
 * @see ShortOperator2
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingShortOperator2 extends AbstractThrowingShortOperator2<AbstractThrowingShortOperator2.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param operator the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ShortOperator2
     */
    static ThrowingShortOperator2 from(final ShortOperator2 operator) {
        return operator::applyAsShort;
    }

    /**
     * @see ThrowingOperator2
     */
    @Override
    default ThrowingOperator2<Short> box() {
        return this::applyAsShort;
    }

    /**
     * @see ThrowingToShortFunction2
     */
    @Override
    default ThrowingToShortFunction2<Short, Short> boxInput() {
        return this::applyAsShort;
    }

    /**
     * @see ShortOperator2
     */
    @Override
    default ShortOperator2 swallow() {
        return this.handle((t, value1, value2) -> (short) 0);
    }

    /**
     * @see ThrowingFunction2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction2<Short, Short, V> andThen(final Function1<? super Short, ? extends V> after) {
        return (ThrowingFunction2<Short, Short, V>) AbstractThrowingShortOperator2.super.andThen(after);
    }

    /**
     * @see ThrowingFunction2
     */
    @Override
    default <V> ThrowingFunction2<Short, Short, V> andThenUnchecked(final Function1<? super Short, ? extends V> after) {
        return (final Short value1, final Short value2) -> after.apply(this.applyAsShort(value1, value2));
    }
}
