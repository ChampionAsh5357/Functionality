/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.operator.primitive.floats;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.operator.primitive.floats.FloatOperator1;
import net.ashwork.functionality.throwable.abstracts.operator.primitive.floats.AbstractThrowingFloatOperator1;
import net.ashwork.functionality.throwable.operator.ThrowingOperator1;
import net.ashwork.functionality.throwable.operator.ThrowingOperatorN;
import net.ashwork.functionality.throwable.primitive.floats.ThrowingFloatFunction1;
import net.ashwork.functionality.throwable.primitive.floats.ThrowingToFloatFunction1;

/**
 * Represents an operation that accepts a {@code float}-valued operand and produces a result of the same type as its operand or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingOperatorN}.
 * This is the {@code float}-consuming primitive specialization of {@link ThrowingToFloatFunction1}.
 * This is the {@code float}-producing primitive specialization of {@link ThrowingFloatFunction1}.
 * This is the throwing variation of {@link FloatOperator1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsFloat(float)}.
 *
 * @see ThrowingOperatorN
 * @see ThrowingToFloatFunction1
 * @see ThrowingFloatFunction1
 * @see FloatOperator1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingFloatOperator1 extends AbstractThrowingFloatOperator1<AbstractThrowingFloatOperator1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param operator the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see FloatOperator1
     */
    static ThrowingFloatOperator1 from(final FloatOperator1 operator) {
        return operator::applyAsFloat;
    }

    /**
     * @see ThrowingOperator1
     */
    @Override
    default ThrowingOperator1<Float> box() {
        return this::applyAsFloat;
    }

    /**
     * @see ThrowingToFloatFunction1
     */
    @Override
    default ThrowingToFloatFunction1<Float> boxInput() {
        return this::applyAsFloat;
    }

    /**
     * @see FloatOperator1
     */
    @Override
    default ThrowingFloatFunction1<Float> boxResult() {
        return this::applyAsFloat;
    }

    /**
     * @see FloatOperator1
     */
    @Override
    default FloatOperator1 swallow() {
        return this.handle((t, value) -> 0.0f);
    }

    /**
     * @see ThrowingToFloatFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToFloatFunction1<V> compose(final Function1<? super V, ? extends Float> before) {
        return (ThrowingToFloatFunction1<V>) AbstractThrowingFloatOperator1.super.compose(before);
    }

    /**
     * @see ThrowingToFloatFunction1
     */
    @Override
    default <V> ThrowingToFloatFunction1<V> composeUnchecked(final Function1<? super V, ? extends Float> before) {
        return (final V v) -> this.applyAsFloat(before.apply(v));
    }

    /**
     * @see ThrowingFloatFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFloatFunction1<V> andThen(final Function1<? super Float, ? extends V> after) {
        return (ThrowingFloatFunction1<V>) AbstractThrowingFloatOperator1.super.andThen(after);
    }

    /**
     * @see ThrowingFloatFunction1
     */
    @Override
    default <V> ThrowingFloatFunction1<V> andThenUnchecked(final Function1<? super Float, ? extends V> after) {
        return (final float value) -> after.apply(this.applyAsFloat(value));
    }
}
