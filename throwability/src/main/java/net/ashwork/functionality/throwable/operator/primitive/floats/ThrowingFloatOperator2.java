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
import net.ashwork.functionality.operator.primitive.floats.FloatOperator2;
import net.ashwork.functionality.throwable.ThrowingFunction2;
import net.ashwork.functionality.throwable.abstracts.operator.primitive.floats.AbstractThrowingFloatOperator2;
import net.ashwork.functionality.throwable.operator.ThrowingOperator2;
import net.ashwork.functionality.throwable.operator.ThrowingOperatorN;
import net.ashwork.functionality.throwable.primitive.floats.ThrowingToFloatFunction2;

/**
 * Represents an operation that accepts two {@code float}-valued operands and produces a result of the same type as its operands or throws a throwable.
 * This is the two-arity specialization of {@link ThrowingOperatorN}.
 * This is the {@code float}-consuming primitive specialization of {@link ThrowingToFloatFunction2}.
 * This is the throwing variation of {@link FloatOperator2}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @see ThrowingOperatorN
 * @see ThrowingToFloatFunction2
 * @see FloatOperator2
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingFloatOperator2 extends AbstractThrowingFloatOperator2<AbstractThrowingFloatOperator2.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param operator the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see FloatOperator2
     */
    static ThrowingFloatOperator2 from(final FloatOperator2 operator) {
        return operator::applyAsFloat;
    }

    /**
     * @see ThrowingOperator2
     */
    @Override
    default ThrowingOperator2<Float> box() {
        return this::applyAsFloat;
    }

    /**
     * @see ThrowingToFloatFunction2
     */
    @Override
    default ThrowingToFloatFunction2<Float, Float> boxInput() {
        return this::applyAsFloat;
    }

    /**
     * @see FloatOperator2
     */
    @Override
    default FloatOperator2 swallow() {
        return this.handle((t, value1, value2) -> 0.0f);
    }

    /**
     * @see ThrowingFunction2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction2<Float, Float, V> andThen(final Function1<? super Float, ? extends V> after) {
        return (ThrowingFunction2<Float, Float, V>) AbstractThrowingFloatOperator2.super.andThen(after);
    }

    /**
     * @see ThrowingFunction2
     */
    @Override
    default <V> ThrowingFunction2<Float, Float, V> andThenUnchecked(final Function1<? super Float, ? extends V> after) {
        return (final Float value1, final Float value2) -> after.apply(this.applyAsFloat(value1, value2));
    }
}
