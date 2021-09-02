/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.operator.primitive.booleans;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.operator.primitive.booleans.BooleanOperator2;
import net.ashwork.functionality.throwable.ThrowingFunction2;
import net.ashwork.functionality.throwable.abstracts.operator.primitive.booleans.AbstractThrowingBooleanOperator2;
import net.ashwork.functionality.throwable.operator.ThrowingOperator2;
import net.ashwork.functionality.throwable.operator.ThrowingOperatorN;
import net.ashwork.functionality.throwable.primitive.booleans.ThrowingToBooleanFunction2;

/**
 * Represents an operation that accepts two {@code boolean}-valued operands and produces a result of the same type as its operands or throws a throwable.
 * This is the two-arity specialization of {@link ThrowingOperatorN}.
 * This is the {@code boolean}-consuming primitive specialization of {@link ThrowingToBooleanFunction2}.
 * This is the throwing variation of {@link BooleanOperator2}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @see ThrowingOperatorN
 * @see ThrowingToBooleanFunction2
 * @see BooleanOperator2
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingBooleanOperator2 extends AbstractThrowingBooleanOperator2<AbstractThrowingBooleanOperator2.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param operator the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see BooleanOperator2
     */
    static ThrowingBooleanOperator2 from(final BooleanOperator2 operator) {
        return operator::applyAsBoolean;
    }

    /**
     * @see ThrowingOperator2
     */
    @Override
    default ThrowingOperator2<Boolean> box() {
        return this::applyAsBoolean;
    }

    /**
     * @see ThrowingToBooleanFunction2
     */
    @Override
    default ThrowingToBooleanFunction2<Boolean, Boolean> boxInput() {
        return this::applyAsBoolean;
    }

    /**
     * @see BooleanOperator2
     */
    @Override
    default BooleanOperator2 swallow() {
        return this.handle((t, value1, value2) -> false);
    }

    /**
     * @see ThrowingFunction2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction2<Boolean, Boolean, V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (ThrowingFunction2<Boolean, Boolean, V>) AbstractThrowingBooleanOperator2.super.andThen(after);
    }

    /**
     * @see ThrowingFunction2
     */
    @Override
    default <V> ThrowingFunction2<Boolean, Boolean, V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final Boolean value1, final Boolean value2) -> after.apply(this.applyAsBoolean(value1, value2));
    }
}
