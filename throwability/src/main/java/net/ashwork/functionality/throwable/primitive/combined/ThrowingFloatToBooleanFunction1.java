/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.primitive.combined;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.primitive.combined.FloatToBooleanFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingFloatToBooleanFunction1;
import net.ashwork.functionality.throwable.primitive.booleans.ThrowingToBooleanFunction1;
import net.ashwork.functionality.throwable.primitive.booleans.ThrowingToBooleanFunctionN;
import net.ashwork.functionality.throwable.primitive.floats.ThrowingFloatFunction1;

/**
 * Represents a function that accepts a {@code float}-valued argument and produces a {@code boolean}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToBooleanFunctionN}.
 * This is the {@code float}-consuming primitive specialization of {@link ThrowingToBooleanFunction1}.
 * This is the {@code boolean}-producing primitive specialization of {@link ThrowingFloatFunction1}.
 * This is the throwing variation of {@link FloatToBooleanFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsBoolean(float)}.
 *
 * @see ThrowingFloatFunction1
 * @see ThrowingToBooleanFunction1
 * @see ThrowingToBooleanFunctionN
 * @see FloatToBooleanFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingFloatToBooleanFunction1 extends AbstractThrowingFloatToBooleanFunction1<ThrowingFunction1<Float, Boolean>, ThrowingToBooleanFunction1<Float>, ThrowingFloatFunction1<Boolean>, AbstractThrowingFloatToBooleanFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see FloatToBooleanFunction1
     */
    static ThrowingFloatToBooleanFunction1 from(final FloatToBooleanFunction1 function) {
        return function::applyAsBoolean;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Float, Boolean> box() {
        return this::applyAsBoolean;
    }

    /**
     * @see ThrowingToBooleanFunction1
     */
    @Override
    default ThrowingToBooleanFunction1<Float> boxInput() {
        return this::applyAsBoolean;
    }

    /**
     * @see ThrowingFloatFunction1
     */
    @Override
    default ThrowingFloatFunction1<Boolean> boxResult() {
        return this::applyAsBoolean;
    }

    @Override
    default FloatToBooleanFunction1 swallow() {
        return this.handle((t, value) -> false);
    }

    /**
     * @see ThrowingToBooleanFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToBooleanFunction1<V> compose(final Function1<? super V, ? extends Float> before) {
        return (ThrowingToBooleanFunction1<V>) AbstractThrowingFloatToBooleanFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToBooleanFunction1
     */
    @Override
    default <V> ThrowingToBooleanFunction1<V> composeUnchecked(final Function1<? super V, ? extends Float> before) {
        return (final V v) -> this.applyAsBoolean(before.apply(v));
    }

    /**
     * @see ThrowingFloatFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFloatFunction1<V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (ThrowingFloatFunction1<V>) AbstractThrowingFloatToBooleanFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingFloatFunction1
     */
    @Override
    default <V> ThrowingFloatFunction1<V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final float value) -> after.apply(this.applyAsBoolean(value));
    }
}
