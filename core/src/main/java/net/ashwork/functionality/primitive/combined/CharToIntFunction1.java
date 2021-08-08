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
import net.ashwork.functionality.primitive.ints.ToIntFunction1;
import net.ashwork.functionality.primitive.ints.ToIntFunctionN;

/**
 * Represents a function that accepts a {@code char}-valued argument and produces an {@code int}-valued result.
 * This is the one-arity specialization of {@link ToIntFunctionN}.
 * This is the {@code char}-consuming primitive specialization of {@link ToIntFunction1}.
 * This is the {@code int}-producing primitive specialization of {@link CharFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsInt(char)}.
 *
 * @see CharFunction1
 * @see ToIntFunction1
 * @see ToIntFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface CharToIntFunction1 extends ToIntFunctionN, InputChainableInput<Character>, UnboxedAll<Function1<Character, Integer>, ToIntFunction1<Character>, CharFunction1<Integer>> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    int applyAsInt(final char value);

    @Override
    default int applyAllAsIntUnchecked(final Object... args) {
        return this.applyAsInt((char) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<Character, Integer> box() {
        return this::applyAsInt;
    }

    /**
     * @see ToIntFunction1
     */
    @Override
    default ToIntFunction1<Character> boxInput() {
        return this::applyAsInt;
    }

    /**
     * @see CharFunction1
     */
    @Override
    default CharFunction1<Integer> boxResult() {
        return this::applyAsInt;
    }

    /**
     * @see ToIntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToIntFunction1<V> compose(final Function1<? super V, ? extends Character> before) {
        return (ToIntFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToIntFunction1
     */
    @Override
    default <V> ToIntFunction1<V> composeUnchecked(final Function1<? super V, ? extends Character> before) {
        return (final V v) -> this.applyAsInt(before.apply(v));
    }

    /**
     * @see CharFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> CharFunction1<V> andThen(final Function1<? super Integer, ? extends V> after) {
        return (CharFunction1<V>) ToIntFunctionN.super.andThen(after);
    }

    /**
     * @see CharFunction1
     */
    @Override
    default <V> CharFunction1<V> andThenUnchecked(final Function1<? super Integer, ? extends V> after) {
        return (final char value) -> after.apply(this.applyAsInt(value));
    }
}
