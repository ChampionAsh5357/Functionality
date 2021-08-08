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
import net.ashwork.functionality.primitive.chars.CharFunction1;
import net.ashwork.functionality.primitive.doubles.ToDoubleFunction1;
import net.ashwork.functionality.primitive.doubles.ToDoubleFunctionN;

/**
 * Represents a function that accepts a {@code char}-valued argument and produces a {@code double}-valued result.
 * This is the one-arity specialization of {@link ToDoubleFunctionN}.
 * This is the {@code char}-consuming primitive specialization of {@link ToDoubleFunction1}.
 * This is the {@code double}-producing primitive specialization of {@link CharFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsDouble(char)}.
 *
 * @see CharFunction1
 * @see ToDoubleFunction1
 * @see ToDoubleFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface CharToDoubleFunction1 extends ToDoubleFunctionN, InputChainableInput<Character>, UnboxedAll<Function1<Character, Double>, ToDoubleFunction1<Character>, CharFunction1<Double>> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    double applyAsDouble(final char value);

    @Override
    default double applyAllAsDoubleUnchecked(final Object... args) {
        return this.applyAsDouble((char) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<Character, Double> box() {
        return this::applyAsDouble;
    }

    /**
     * @see ToDoubleFunction1
     */
    @Override
    default ToDoubleFunction1<Character> boxInput() {
        return this::applyAsDouble;
    }

    /**
     * @see CharFunction1
     */
    @Override
    default CharFunction1<Double> boxResult() {
        return this::applyAsDouble;
    }

    /**
     * @see ToDoubleFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToDoubleFunction1<V> compose(final Function1<? super V, ? extends Character> before) {
        return (ToDoubleFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToDoubleFunction1
     */
    @Override
    default <V> ToDoubleFunction1<V> composeUnchecked(final Function1<? super V, ? extends Character> before) {
        return (final V v) -> this.applyAsDouble(before.apply(v));
    }

    /**
     * @see CharFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> CharFunction1<V> andThen(final Function1<? super Double, ? extends V> after) {
        return (CharFunction1<V>) ToDoubleFunctionN.super.andThen(after);
    }

    /**
     * @see CharFunction1
     */
    @Override
    default <V> CharFunction1<V> andThenUnchecked(final Function1<? super Double, ? extends V> after) {
        return (final char value) -> after.apply(this.applyAsDouble(value));
    }
}
