/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.primitive.shorts;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.operator.primitive.shorts.ShortOperator1;
import net.ashwork.functionality.throwable.abstracts.operator.primitive.shorts.AbstractThrowingShortOperator1;
import net.ashwork.functionality.throwable.operator.ThrowingOperator1;
import net.ashwork.functionality.throwable.operator.ThrowingOperatorN;

/**
 * Represents an operation that accepts a {@code short}-valued operand and produces a result of the same type as its operand or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingOperatorN}.
 * This is the {@code short}-consuming primitive specialization of {@link ThrowingToShortFunction1}.
 * This is the {@code short}-producing primitive specialization of {@link ThrowingShortFunction1}.
 * This is the throwing variation of {@link ShortOperator1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsShort(short)}.
 *
 * @see ThrowingOperatorN
 * @see ThrowingToShortFunction1
 * @see ThrowingShortFunction1
 * @see ShortOperator1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingShortOperator1 extends AbstractThrowingShortOperator1<AbstractThrowingShortOperator1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param operator the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ShortOperator1
     */
    static ThrowingShortOperator1 from(final ShortOperator1 operator) {
        return operator::applyAsShort;
    }

    /**
     * @see ThrowingOperator1
     */
    @Override
    default ThrowingOperator1<Short> box() {
        return this::applyAsShort;
    }

    /**
     * @see ThrowingToShortFunction1
     */
    @Override
    default ThrowingToShortFunction1<Short> boxInput() {
        return this::applyAsShort;
    }

    /**
     * @see ShortOperator1
     */
    @Override
    default ThrowingShortFunction1<Short> boxResult() {
        return this::applyAsShort;
    }

    /**
     * @see ShortOperator1
     */
    @Override
    default ShortOperator1 swallow() {
        return this.handle((t, value) -> (short) 0);
    }

    /**
     * @see ThrowingToShortFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToShortFunction1<V> compose(final Function1<? super V, ? extends Short> before) {
        return (ThrowingToShortFunction1<V>) AbstractThrowingShortOperator1.super.compose(before);
    }

    /**
     * @see ThrowingToShortFunction1
     */
    @Override
    default <V> ThrowingToShortFunction1<V> composeUnchecked(final Function1<? super V, ? extends Short> before) {
        return (final V v) -> this.applyAsShort(before.apply(v));
    }

    /**
     * @see ThrowingShortFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingShortFunction1<V> andThen(final Function1<? super Short, ? extends V> after) {
        return (ThrowingShortFunction1<V>) AbstractThrowingShortOperator1.super.andThen(after);
    }

    /**
     * @see ThrowingShortFunction1
     */
    @Override
    default <V> ThrowingShortFunction1<V> andThenUnchecked(final Function1<? super Short, ? extends V> after) {
        return (final short value) -> after.apply(this.applyAsShort(value));
    }
}
