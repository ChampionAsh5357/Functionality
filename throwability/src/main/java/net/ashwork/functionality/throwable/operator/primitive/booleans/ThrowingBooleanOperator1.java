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
import net.ashwork.functionality.operator.primitive.booleans.BooleanOperator1;
import net.ashwork.functionality.throwable.abstracts.operator.primitive.booleans.AbstractThrowingBooleanOperator1;
import net.ashwork.functionality.throwable.operator.ThrowingOperator1;
import net.ashwork.functionality.throwable.operator.ThrowingOperatorN;
import net.ashwork.functionality.throwable.primitive.booleans.ThrowingBooleanFunction1;
import net.ashwork.functionality.throwable.primitive.booleans.ThrowingToBooleanFunction1;

/**
 * Represents an operation that accepts a {@code boolean}-valued operand and produces a result of the same type as its operand or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingOperatorN}.
 * This is the {@code boolean}-consuming primitive specialization of {@link ThrowingToBooleanFunction1}.
 * This is the {@code boolean}-producing primitive specialization of {@link ThrowingBooleanFunction1}.
 * This is the throwing variation of {@link BooleanOperator1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsBoolean(boolean)}.
 *
 * @see ThrowingOperatorN
 * @see ThrowingToBooleanFunction1
 * @see ThrowingBooleanFunction1
 * @see BooleanOperator1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingBooleanOperator1 extends AbstractThrowingBooleanOperator1<AbstractThrowingBooleanOperator1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param operator the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see BooleanOperator1
     */
    static ThrowingBooleanOperator1 from(final BooleanOperator1 operator) {
        return operator::applyAsBoolean;
    }

    /**
     * @see ThrowingOperator1
     */
    @Override
    default ThrowingOperator1<Boolean> box() {
        return this::applyAsBoolean;
    }

    /**
     * @see ThrowingToBooleanFunction1
     */
    @Override
    default ThrowingToBooleanFunction1<Boolean> boxInput() {
        return this::applyAsBoolean;
    }

    /**
     * @see BooleanOperator1
     */
    @Override
    default ThrowingBooleanFunction1<Boolean> boxResult() {
        return this::applyAsBoolean;
    }

    /**
     * @see BooleanOperator1
     */
    @Override
    default BooleanOperator1 swallow() {
        return this.handle((t, value) -> false);
    }

    /**
     * @see ThrowingToBooleanFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToBooleanFunction1<V> compose(final Function1<? super V, ? extends Boolean> before) {
        return (ThrowingToBooleanFunction1<V>) AbstractThrowingBooleanOperator1.super.compose(before);
    }

    /**
     * @see ThrowingToBooleanFunction1
     */
    @Override
    default <V> ThrowingToBooleanFunction1<V> composeUnchecked(final Function1<? super V, ? extends Boolean> before) {
        return (final V v) -> this.applyAsBoolean(before.apply(v));
    }

    /**
     * @see ThrowingBooleanFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingBooleanFunction1<V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (ThrowingBooleanFunction1<V>) AbstractThrowingBooleanOperator1.super.andThen(after);
    }

    /**
     * @see ThrowingBooleanFunction1
     */
    @Override
    default <V> ThrowingBooleanFunction1<V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final boolean value) -> after.apply(this.applyAsBoolean(value));
    }
}
