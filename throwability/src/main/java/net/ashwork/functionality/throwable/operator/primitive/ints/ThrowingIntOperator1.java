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
import net.ashwork.functionality.operator.primitive.ints.IntOperator1;
import net.ashwork.functionality.throwable.abstracts.operator.primitive.ints.AbstractThrowingIntOperator1;
import net.ashwork.functionality.throwable.operator.ThrowingOperator1;
import net.ashwork.functionality.throwable.operator.ThrowingOperatorN;
import net.ashwork.functionality.throwable.primitive.ints.ThrowingIntFunction1;
import net.ashwork.functionality.throwable.primitive.ints.ThrowingToIntFunction1;

/**
 * Represents an operation that accepts an {@code int}-valued operand and produces a result of the same type as its operand or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingOperatorN}.
 * This is the {@code int}-consuming primitive specialization of {@link ThrowingToIntFunction1}.
 * This is the {@code int}-producing primitive specialization of {@link ThrowingIntFunction1}.
 * This is the throwing variation of {@link IntOperator1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsInt(int)}.
 *
 * @see ThrowingOperatorN
 * @see ThrowingToIntFunction1
 * @see ThrowingIntFunction1
 * @see IntOperator1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingIntOperator1 extends AbstractThrowingIntOperator1<AbstractThrowingIntOperator1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param operator the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see IntOperator1
     */
    static ThrowingIntOperator1 from(final IntOperator1 operator) {
        return operator::applyAsInt;
    }

    /**
     * @see ThrowingOperator1
     */
    @Override
    default ThrowingOperator1<Integer> box() {
        return this::applyAsInt;
    }

    /**
     * @see ThrowingToIntFunction1
     */
    @Override
    default ThrowingToIntFunction1<Integer> boxInput() {
        return this::applyAsInt;
    }

    /**
     * @see IntOperator1
     */
    @Override
    default ThrowingIntFunction1<Integer> boxResult() {
        return this::applyAsInt;
    }

    /**
     * @see IntOperator1
     */
    @Override
    default IntOperator1 swallow() {
        return this.handle((t, value) -> 0);
    }

    /**
     * @see ThrowingToIntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToIntFunction1<V> compose(final Function1<? super V, ? extends Integer> before) {
        return (ThrowingToIntFunction1<V>) AbstractThrowingIntOperator1.super.compose(before);
    }

    /**
     * @see ThrowingToIntFunction1
     */
    @Override
    default <V> ThrowingToIntFunction1<V> composeUnchecked(final Function1<? super V, ? extends Integer> before) {
        return (final V v) -> this.applyAsInt(before.apply(v));
    }

    /**
     * @see ThrowingIntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingIntFunction1<V> andThen(final Function1<? super Integer, ? extends V> after) {
        return (ThrowingIntFunction1<V>) AbstractThrowingIntOperator1.super.andThen(after);
    }

    /**
     * @see ThrowingIntFunction1
     */
    @Override
    default <V> ThrowingIntFunction1<V> andThenUnchecked(final Function1<? super Integer, ? extends V> after) {
        return (final int value) -> after.apply(this.applyAsInt(value));
    }
}
