/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.primitive.combined;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.primitive.combined.CharToIntFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingCharToIntFunction1;
import net.ashwork.functionality.throwable.primitive.chars.ThrowingCharFunction1;
import net.ashwork.functionality.throwable.primitive.ints.ThrowingToIntFunction1;
import net.ashwork.functionality.throwable.primitive.ints.ThrowingToIntFunctionN;

/**
 * Represents a function that accepts an {@code char}-valued argument and produces an {@code int}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToIntFunctionN}.
 * This is the {@code char}-consuming primitive specialization of {@link ThrowingToIntFunction1}.
 * This is the {@code int}-producing primitive specialization of {@link ThrowingCharFunction1}.
 * This is the throwing variation of {@link CharToIntFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsInt(char)}.
 *
 * @see ThrowingCharFunction1
 * @see ThrowingToIntFunction1
 * @see ThrowingToIntFunctionN
 * @see CharToIntFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingCharToIntFunction1 extends AbstractThrowingCharToIntFunction1<AbstractThrowingCharToIntFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see CharToIntFunction1
     */
    static ThrowingCharToIntFunction1 from(final CharToIntFunction1 function) {
        return function::applyAsInt;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Character, Integer> box() {
        return this::applyAsInt;
    }

    /**
     * @see ThrowingToIntFunction1
     */
    @Override
    default ThrowingToIntFunction1<Character> boxInput() {
        return this::applyAsInt;
    }

    /**
     * @see ThrowingCharFunction1
     */
    @Override
    default ThrowingCharFunction1<Integer> boxResult() {
        return this::applyAsInt;
    }

    @Override
    default CharToIntFunction1 swallow() {
        return this.handle((t, value) -> 0);
    }

    /**
     * @see ThrowingToIntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToIntFunction1<V> compose(final Function1<? super V, ? extends Character> before) {
        return (ThrowingToIntFunction1<V>) AbstractThrowingCharToIntFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToIntFunction1
     */
    @Override
    default <V> ThrowingToIntFunction1<V> composeUnchecked(final Function1<? super V, ? extends Character> before) {
        return (final V v) -> this.applyAsInt(before.apply(v));
    }

    /**
     * @see ThrowingCharFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingCharFunction1<V> andThen(final Function1<? super Integer, ? extends V> after) {
        return (ThrowingCharFunction1<V>) AbstractThrowingCharToIntFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingCharFunction1
     */
    @Override
    default <V> ThrowingCharFunction1<V> andThenUnchecked(final Function1<? super Integer, ? extends V> after) {
        return (final char value) -> after.apply(this.applyAsInt(value));
    }
}
