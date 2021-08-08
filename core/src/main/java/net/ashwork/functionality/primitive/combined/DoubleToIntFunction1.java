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
import net.ashwork.functionality.partial.Variant;
import net.ashwork.functionality.primitive.doubles.DoubleFunction1;
import net.ashwork.functionality.primitive.ints.ToIntFunction1;
import net.ashwork.functionality.primitive.ints.ToIntFunctionN;

import java.util.function.DoubleToIntFunction;

/**
 * Represents a function that accepts a {@code double}-valued argument and produces an {@code int}-valued result.
 * This is the one-arity specialization of {@link ToIntFunctionN}.
 * This is the {@code double}-consuming primitive specialization of {@link ToIntFunction1}.
 * This is the {@code int}-producing primitive specialization of {@link DoubleFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsInt(double)}.
 *
 * @see DoubleFunction1
 * @see ToIntFunction1
 * @see ToIntFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface DoubleToIntFunction1 extends ToIntFunctionN, InputChainableInput<Double>, UnboxedAll<Function1<Double, Integer>, ToIntFunction1<Double>, DoubleFunction1<Integer>>, Variant<DoubleToIntFunction> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    int applyAsInt(final double value);

    @Override
    default int applyAllAsIntUnchecked(final Object... args) {
        return this.applyAsInt((double) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * Creates an instance of this object from its {@link DoubleToIntFunction} variant.
     *
     * @param function the variant of this object
     * @return an instance of this object
     *
     * @see DoubleToIntFunction
     */
    static DoubleToIntFunction1 fromVariant(final DoubleToIntFunction function) {
        return function::applyAsInt;
    }

    @Override
    default DoubleToIntFunction toVariant() {
        return this::applyAsInt;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<Double, Integer> box() {
        return this::applyAsInt;
    }

    /**
     * @see ToIntFunction1
     */
    @Override
    default ToIntFunction1<Double> boxInput() {
        return this::applyAsInt;
    }

    /**
     * @see DoubleFunction1
     */
    @Override
    default DoubleFunction1<Integer> boxResult() {
        return this::applyAsInt;
    }

    /**
     * @see ToIntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToIntFunction1<V> compose(final Function1<? super V, ? extends Double> before) {
        return (ToIntFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToIntFunction1
     */
    @Override
    default <V> ToIntFunction1<V> composeUnchecked(final Function1<? super V, ? extends Double> before) {
        return (final V v) -> this.applyAsInt(before.apply(v));
    }

    /**
     * @see DoubleFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> DoubleFunction1<V> andThen(final Function1<? super Integer, ? extends V> after) {
        return (DoubleFunction1<V>) ToIntFunctionN.super.andThen(after);
    }

    /**
     * @see DoubleFunction1
     */
    @Override
    default <V> DoubleFunction1<V> andThenUnchecked(final Function1<? super Integer, ? extends V> after) {
        return (final double value) -> after.apply(this.applyAsInt(value));
    }
}
