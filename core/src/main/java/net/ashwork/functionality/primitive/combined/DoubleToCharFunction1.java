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
import net.ashwork.functionality.primitive.doubles.DoubleFunction1;
import net.ashwork.functionality.primitive.chars.ToCharFunction1;
import net.ashwork.functionality.primitive.chars.ToCharFunctionN;

/**
 * Represents a function that accepts a {@code double}-valued argument and produces a {@code char}-valued result.
 * This is the one-arity specialization of {@link ToCharFunctionN}.
 * This is the {@code double}-consuming primitive specialization of {@link ToCharFunction1}.
 * This is the {@code char}-producing primitive specialization of {@link DoubleFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsChar(double)}.
 *
 * @see DoubleFunction1
 * @see ToCharFunction1
 * @see ToCharFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface DoubleToCharFunction1 extends ToCharFunctionN, InputChainableInput<Double>, UnboxedAll<Function1<Double, Character>, ToCharFunction1<Double>, DoubleFunction1<Character>> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    char applyAsChar(final double value);

    @Override
    default char applyAllAsCharUnchecked(final Object... args) {
        return this.applyAsChar((double) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<Double, Character> box() {
        return this::applyAsChar;
    }

    /**
     * @see ToCharFunction1
     */
    @Override
    default ToCharFunction1<Double> boxInput() {
        return this::applyAsChar;
    }

    /**
     * @see DoubleFunction1
     */
    @Override
    default DoubleFunction1<Character> boxResult() {
        return this::applyAsChar;
    }

    /**
     * @see ToCharFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToCharFunction1<V> compose(final Function1<? super V, ? extends Double> before) {
        return (ToCharFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToCharFunction1
     */
    @Override
    default <V> ToCharFunction1<V> composeUnchecked(final Function1<? super V, ? extends Double> before) {
        return (final V v) -> this.applyAsChar(before.apply(v));
    }

    /**
     * @see DoubleFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> DoubleFunction1<V> andThen(final Function1<? super Character, ? extends V> after) {
        return (DoubleFunction1<V>) ToCharFunctionN.super.andThen(after);
    }

    /**
     * @see DoubleFunction1
     */
    @Override
    default <V> DoubleFunction1<V> andThenUnchecked(final Function1<? super Character, ? extends V> after) {
        return (final double value) -> after.apply(this.applyAsChar(value));
    }
}
