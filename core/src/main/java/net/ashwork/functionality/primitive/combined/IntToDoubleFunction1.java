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
import net.ashwork.functionality.primitive.doubles.ToDoubleFunction1;
import net.ashwork.functionality.primitive.doubles.ToDoubleFunctionN;
import net.ashwork.functionality.primitive.ints.IntFunction1;

import java.util.function.IntToDoubleFunction;

/**
 * Represents a function that accepts an {@code int}-valued argument and produces a {@code double}-valued result.
 * This is the one-arity specialization of {@link ToDoubleFunctionN}.
 * This is the {@code int}-consuming primitive specialization of {@link ToDoubleFunction1}.
 * This is the {@code double}-producing primitive specialization of {@link IntFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsDouble(int)}.
 *
 * @see IntFunction1
 * @see ToDoubleFunction1
 * @see ToDoubleFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface IntToDoubleFunction1 extends ToDoubleFunctionN, InputChainableInput<Integer>, UnboxedAll<Function1<Integer, Double>, ToDoubleFunction1<Integer>, IntFunction1<Double>>, Variant<IntToDoubleFunction> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    double applyAsDouble(final int value);

    @Override
    default double applyAllAsDoubleUnchecked(final Object... args) {
        return this.applyAsDouble((int) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * Creates an instance of this object from its {@link IntToDoubleFunction} variant.
     *
     * @param function the variant of this object
     * @return an instance of this object
     *
     * @see IntToDoubleFunction
     */
    static IntToDoubleFunction1 fromVariant(final IntToDoubleFunction function) {
        return function::applyAsDouble;
    }

    @Override
    default IntToDoubleFunction toVariant() {
        return this::applyAsDouble;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<Integer, Double> box() {
        return this::applyAsDouble;
    }

    /**
     * @see ToDoubleFunction1
     */
    @Override
    default ToDoubleFunction1<Integer> boxInput() {
        return this::applyAsDouble;
    }

    /**
     * @see IntFunction1
     */
    @Override
    default IntFunction1<Double> boxResult() {
        return this::applyAsDouble;
    }

    /**
     * @see ToDoubleFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToDoubleFunction1<V> compose(final Function1<? super V, ? extends Integer> before) {
        return (ToDoubleFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToDoubleFunction1
     */
    @Override
    default <V> ToDoubleFunction1<V> composeUnchecked(final Function1<? super V, ? extends Integer> before) {
        return (final V v) -> this.applyAsDouble(before.apply(v));
    }

    /**
     * @see IntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> IntFunction1<V> andThen(final Function1<? super Double, ? extends V> after) {
        return (IntFunction1<V>) ToDoubleFunctionN.super.andThen(after);
    }

    /**
     * @see IntFunction1
     */
    @Override
    default <V> IntFunction1<V> andThenUnchecked(final Function1<? super Double, ? extends V> after) {
        return (final int value) -> after.apply(this.applyAsDouble(value));
    }
}
