/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.primitive.chars;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.FunctionN;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedInput;

/**
 * Represents a function that accepts a {@code char}-valued argument and produces a result.
 * This is the one-arity specialization of {@link FunctionN}.
 * This is the {@code char}-consuming primitive specialization of {@link Function1}.
 *
 * <p>This is a functional interface whose functional method is {@link #apply(char)}.
 *
 * @param <R> the type of the result of the function
 *
 * @see Function1
 * @see FunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface CharFunction1<R> extends FunctionN<R>, InputChainableInput<Character>, UnboxedInput<Function1<Character, R>> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    R apply(final char value);

    @Override
    default R applyAllUnchecked(final Object... args) {
        return this.apply((char) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<Character, R> boxInput() {
        return this::apply;
    }

    /**
     * @see Function1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Function1<V, R> compose(final Function1<? super V, ? extends Character> before) {
        return (Function1<V, R>) InputChainableInput.super.compose(before);
    }

    /**
     * @see Function1
     */
    @Override
    default <V> Function1<V, R> composeUnchecked(final Function1<? super V, ? extends Character> before) {
        return (final V v) -> this.apply(before.apply(v));
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> CharFunction1<V> andThen(final Function1<? super R, ? extends V> after) {
        return (CharFunction1<V>) FunctionN.super.andThen(after);
    }

    @Override
    default <V> CharFunction1<V> andThenUnchecked(final Function1<? super R, ? extends V> after) {
        return (final char value) -> after.apply(this.apply(value));
    }
}
