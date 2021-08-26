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
import net.ashwork.functionality.primitive.combined.BooleanToFloatFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingBooleanToFloatFunction1;
import net.ashwork.functionality.throwable.primitive.booleans.ThrowingBooleanFunction1;
import net.ashwork.functionality.throwable.primitive.floats.ThrowingToFloatFunction1;
import net.ashwork.functionality.throwable.primitive.floats.ThrowingToFloatFunctionN;

/**
 * Represents a function that accepts a {@code boolean}-valued argument and produces a {@code float}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToFloatFunctionN}.
 * This is the {@code boolean}-consuming primitive specialization of {@link ThrowingToFloatFunction1}.
 * This is the {@code float}-producing primitive specialization of {@link ThrowingBooleanFunction1}.
 * This is the throwing variation of {@link BooleanToFloatFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsFloat(boolean)}.
 *
 * @see ThrowingBooleanFunction1
 * @see ThrowingToFloatFunction1
 * @see ThrowingToFloatFunctionN
 * @see BooleanToFloatFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingBooleanToFloatFunction1 extends AbstractThrowingBooleanToFloatFunction1<AbstractThrowingBooleanToFloatFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see BooleanToFloatFunction1
     */
    static ThrowingBooleanToFloatFunction1 from(final BooleanToFloatFunction1 function) {
        return function::applyAsFloat;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Boolean, Float> box() {
        return this::applyAsFloat;
    }

    /**
     * @see ThrowingToFloatFunction1
     */
    @Override
    default ThrowingToFloatFunction1<Boolean> boxInput() {
        return this::applyAsFloat;
    }

    /**
     * @see ThrowingBooleanFunction1
     */
    @Override
    default ThrowingBooleanFunction1<Float> boxResult() {
        return this::applyAsFloat;
    }

    @Override
    default BooleanToFloatFunction1 swallow() {
        return this.handle((t, value) -> 0.0f);
    }

    /**
     * @see ThrowingToFloatFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToFloatFunction1<V> compose(final Function1<? super V, ? extends Boolean> before) {
        return (ThrowingToFloatFunction1<V>) AbstractThrowingBooleanToFloatFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToFloatFunction1
     */
    @Override
    default <V> ThrowingToFloatFunction1<V> composeUnchecked(final Function1<? super V, ? extends Boolean> before) {
        return (final V v) -> this.applyAsFloat(before.apply(v));
    }

    /**
     * @see ThrowingBooleanFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingBooleanFunction1<V> andThen(final Function1<? super Float, ? extends V> after) {
        return (ThrowingBooleanFunction1<V>) AbstractThrowingBooleanToFloatFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingBooleanFunction1
     */
    @Override
    default <V> ThrowingBooleanFunction1<V> andThenUnchecked(final Function1<? super Float, ? extends V> after) {
        return (final boolean value) -> after.apply(this.applyAsFloat(value));
    }
}
