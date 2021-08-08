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
import net.ashwork.functionality.primitive.longs.LongFunction1;

import java.util.function.LongToDoubleFunction;

/**
 * Represents a function that accepts a {@code long}-valued argument and produces a {@code double}-valued result.
 * This is the one-arity specialization of {@link ToDoubleFunctionN}.
 * This is the {@code long}-consuming primitive specialization of {@link ToDoubleFunction1}.
 * This is the {@code double}-producing primitive specialization of {@link LongFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsDouble(long)}.
 *
 * @see LongFunction1
 * @see ToDoubleFunction1
 * @see ToDoubleFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface LongToDoubleFunction1 extends ToDoubleFunctionN, InputChainableInput<Long>, UnboxedAll<Function1<Long, Double>, ToDoubleFunction1<Long>, LongFunction1<Double>>, Variant<LongToDoubleFunction> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    double applyAsDouble(final long value);

    @Override
    default double applyAllAsDoubleUnchecked(final Object... args) {
        return this.applyAsDouble((long) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * Creates an instance of this object from its {@link LongToDoubleFunction} variant.
     *
     * @param function the variant of this object
     * @return an instance of this object
     *
     * @see LongToDoubleFunction
     */
    static LongToDoubleFunction1 fromVariant(final LongToDoubleFunction function) {
        return function::applyAsDouble;
    }

    @Override
    default LongToDoubleFunction toVariant() {
        return this::applyAsDouble;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<Long, Double> box() {
        return this::applyAsDouble;
    }

    /**
     * @see ToDoubleFunction1
     */
    @Override
    default ToDoubleFunction1<Long> boxInput() {
        return this::applyAsDouble;
    }

    /**
     * @see LongFunction1
     */
    @Override
    default LongFunction1<Double> boxResult() {
        return this::applyAsDouble;
    }

    /**
     * @see ToDoubleFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToDoubleFunction1<V> compose(final Function1<? super V, ? extends Long> before) {
        return (ToDoubleFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToDoubleFunction1
     */
    @Override
    default <V> ToDoubleFunction1<V> composeUnchecked(final Function1<? super V, ? extends Long> before) {
        return (final V v) -> this.applyAsDouble(before.apply(v));
    }

    /**
     * @see LongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> LongFunction1<V> andThen(final Function1<? super Double, ? extends V> after) {
        return (LongFunction1<V>) ToDoubleFunctionN.super.andThen(after);
    }

    /**
     * @see LongFunction1
     */
    @Override
    default <V> LongFunction1<V> andThenUnchecked(final Function1<? super Double, ? extends V> after) {
        return (final long value) -> after.apply(this.applyAsDouble(value));
    }
}
