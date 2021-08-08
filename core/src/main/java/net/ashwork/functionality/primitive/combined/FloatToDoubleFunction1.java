/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.primitive.combined;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedAll;
import net.ashwork.functionality.primitive.floats.FloatFunction1;
import net.ashwork.functionality.primitive.doubles.ToDoubleFunction1;
import net.ashwork.functionality.primitive.doubles.ToDoubleFunctionN;

/**
 * Represents a function that accepts a {@code float}-valued argument and produces a {@code double}-valued result.
 * This is the one-arity specialization of {@link ToDoubleFunctionN}.
 * This is the {@code float}-consuming primitive specialization of {@link ToDoubleFunction1}.
 * This is the {@code double}-producing primitive specialization of {@link FloatFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsDouble(float)}.
 *
 * @see FloatFunction1
 * @see ToDoubleFunction1
 * @see ToDoubleFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface FloatToDoubleFunction1 extends ToDoubleFunctionN, InputChainableInput<Float>, UnboxedAll<Function1<Float, Double>, ToDoubleFunction1<Float>, FloatFunction1<Double>> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    double applyAsDouble(final float value);

    @Override
    default double applyAllAsDoubleUnchecked(final Object... args) {
        return this.applyAsDouble((float) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<Float, Double> box() {
        return this::applyAsDouble;
    }

    /**
     * @see ToDoubleFunction1
     */
    @Override
    default ToDoubleFunction1<Float> boxInput() {
        return this::applyAsDouble;
    }

    /**
     * @see FloatFunction1
     */
    @Override
    default FloatFunction1<Double> boxResult() {
        return this::applyAsDouble;
    }

    /**
     * @see ToDoubleFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToDoubleFunction1<V> compose(final Function1<? super V, ? extends Float> before) {
        return (ToDoubleFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToDoubleFunction1
     */
    @Override
    default <V> ToDoubleFunction1<V> composeUnchecked(final Function1<? super V, ? extends Float> before) {
        return (final V v) -> this.applyAsDouble(before.apply(v));
    }

    /**
     * @see FloatFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> FloatFunction1<V> andThen(final Function1<? super Double, ? extends V> after) {
        return (FloatFunction1<V>) ToDoubleFunctionN.super.andThen(after);
    }

    /**
     * @see FloatFunction1
     */
    @Override
    default <V> FloatFunction1<V> andThenUnchecked(final Function1<? super Double, ? extends V> after) {
        return (final float value) -> after.apply(this.applyAsDouble(value));
    }
}
