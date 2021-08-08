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
import net.ashwork.functionality.primitive.ints.IntFunction1;
import net.ashwork.functionality.primitive.chars.ToCharFunction1;
import net.ashwork.functionality.primitive.chars.ToCharFunctionN;

/**
 * Represents a function that accepts an {@code int}-valued argument and produces a {@code char}-valued result.
 * This is the one-arity specialization of {@link ToCharFunctionN}.
 * This is the {@code int}-consuming primitive specialization of {@link ToCharFunction1}.
 * This is the {@code char}-producing primitive specialization of {@link IntFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsChar(int)}.
 *
 * @see IntFunction1
 * @see ToCharFunction1
 * @see ToCharFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface IntToCharFunction1 extends ToCharFunctionN, InputChainableInput<Integer>, UnboxedAll<Function1<Integer, Character>, ToCharFunction1<Integer>, IntFunction1<Character>> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    char applyAsChar(final int value);

    @Override
    default char applyAllAsCharUnchecked(final Object... args) {
        return this.applyAsChar((int) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<Integer, Character> box() {
        return this::applyAsChar;
    }

    /**
     * @see ToCharFunction1
     */
    @Override
    default ToCharFunction1<Integer> boxInput() {
        return this::applyAsChar;
    }

    /**
     * @see IntFunction1
     */
    @Override
    default IntFunction1<Character> boxResult() {
        return this::applyAsChar;
    }

    /**
     * @see ToCharFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToCharFunction1<V> compose(final Function1<? super V, ? extends Integer> before) {
        return (ToCharFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToCharFunction1
     */
    @Override
    default <V> ToCharFunction1<V> composeUnchecked(final Function1<? super V, ? extends Integer> before) {
        return (final V v) -> this.applyAsChar(before.apply(v));
    }

    /**
     * @see IntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> IntFunction1<V> andThen(final Function1<? super Character, ? extends V> after) {
        return (IntFunction1<V>) ToCharFunctionN.super.andThen(after);
    }

    /**
     * @see IntFunction1
     */
    @Override
    default <V> IntFunction1<V> andThenUnchecked(final Function1<? super Character, ? extends V> after) {
        return (final int value) -> after.apply(this.applyAsChar(value));
    }
}
