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
import net.ashwork.functionality.primitive.longs.ToLongFunction1;
import net.ashwork.functionality.primitive.longs.ToLongFunctionN;

import java.util.function.DoubleToLongFunction;

/**
 * Represents a function that accepts a {@code double}-valued argument and produces a {@code long}-valued result.
 * This is the one-arity specialization of {@link ToLongFunctionN}.
 * This is the {@code double}-consuming primitive specialization of {@link ToLongFunction1}.
 * This is the {@code long}-producing primitive specialization of {@link DoubleFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsLong(double)}.
 *
 * @see DoubleFunction1
 * @see ToLongFunction1
 * @see ToLongFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface DoubleToLongFunction1 extends ToLongFunctionN, InputChainableInput<Double>, UnboxedAll<Function1<Double, Long>, ToLongFunction1<Double>, DoubleFunction1<Long>>, Variant<DoubleToLongFunction> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    long applyAsLong(final double value);

    @Override
    default long applyAllAsLongUnchecked(final Object... args) {
        return this.applyAsLong((double) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * Creates an instance of this object from its {@link DoubleToLongFunction} variant.
     *
     * @param function the variant of this object
     * @return an instance of this object
     *
     * @see DoubleToLongFunction
     */
    static DoubleToLongFunction1 fromVariant(final DoubleToLongFunction function) {
        return function::applyAsLong;
    }

    @Override
    default DoubleToLongFunction toVariant() {
        return this::applyAsLong;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<Double, Long> box() {
        return this::applyAsLong;
    }

    /**
     * @see ToLongFunction1
     */
    @Override
    default ToLongFunction1<Double> boxInput() {
        return this::applyAsLong;
    }

    /**
     * @see DoubleFunction1
     */
    @Override
    default DoubleFunction1<Long> boxResult() {
        return this::applyAsLong;
    }

    /**
     * @see ToLongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToLongFunction1<V> compose(final Function1<? super V, ? extends Double> before) {
        return (ToLongFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToLongFunction1
     */
    @Override
    default <V> ToLongFunction1<V> composeUnchecked(final Function1<? super V, ? extends Double> before) {
        return (final V v) -> this.applyAsLong(before.apply(v));
    }

    /**
     * @see DoubleFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> DoubleFunction1<V> andThen(final Function1<? super Long, ? extends V> after) {
        return (DoubleFunction1<V>) ToLongFunctionN.super.andThen(after);
    }

    /**
     * @see DoubleFunction1
     */
    @Override
    default <V> DoubleFunction1<V> andThenUnchecked(final Function1<? super Long, ? extends V> after) {
        return (final double value) -> after.apply(this.applyAsLong(value));
    }
}
