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
import net.ashwork.functionality.primitive.booleans.ToBooleanFunction1;
import net.ashwork.functionality.primitive.booleans.ToBooleanFunctionN;

/**
 * Represents a function that accepts a {@code char}-valued argument and produces a {@code boolean}-valued result.
 * This is the one-arity specialization of {@link ToBooleanFunctionN}.
 * This is the {@code char}-consuming primitive specialization of {@link ToBooleanFunction1}.
 * This is the {@code boolean}-producing primitive specialization of {@link CharFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsBoolean(char)}.
 *
 * @see CharFunction1
 * @see ToBooleanFunction1
 * @see ToBooleanFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface CharToBooleanFunction1 extends ToBooleanFunctionN, InputChainableInput<Character>, UnboxedAll<Function1<Character, Boolean>, ToBooleanFunction1<Character>, CharFunction1<Boolean>> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    boolean applyAsBoolean(final char value);

    @Override
    default boolean applyAllAsBooleanUnchecked(final Object... args) {
        return this.applyAsBoolean((char) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<Character, Boolean> box() {
        return this::applyAsBoolean;
    }

    /**
     * @see ToBooleanFunction1
     */
    @Override
    default ToBooleanFunction1<Character> boxInput() {
        return this::applyAsBoolean;
    }

    /**
     * @see CharFunction1
     */
    @Override
    default CharFunction1<Boolean> boxResult() {
        return this::applyAsBoolean;
    }

    /**
     * @see ToBooleanFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToBooleanFunction1<V> compose(final Function1<? super V, ? extends Character> before) {
        return (ToBooleanFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToBooleanFunction1
     */
    @Override
    default <V> ToBooleanFunction1<V> composeUnchecked(final Function1<? super V, ? extends Character> before) {
        return (final V v) -> this.applyAsBoolean(before.apply(v));
    }

    /**
     * @see CharFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> CharFunction1<V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (CharFunction1<V>) ToBooleanFunctionN.super.andThen(after);
    }

    /**
     * @see CharFunction1
     */
    @Override
    default <V> CharFunction1<V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final char value) -> after.apply(this.applyAsBoolean(value));
    }
}
