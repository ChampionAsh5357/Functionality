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
import net.ashwork.functionality.primitive.combined.CharToLongFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingCharToLongFunction1;
import net.ashwork.functionality.throwable.primitive.longs.ThrowingToLongFunction1;
import net.ashwork.functionality.throwable.primitive.longs.ThrowingToLongFunctionN;
import net.ashwork.functionality.throwable.primitive.chars.ThrowingCharFunction1;

/**
 * Represents a function that accepts a {@code char}-valued argument and produces a {@code long}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToLongFunctionN}.
 * This is the {@code char}-consuming primitive specialization of {@link ThrowingToLongFunction1}.
 * This is the {@code long}-producing primitive specialization of {@link ThrowingCharFunction1}.
 * This is the throwing variation of {@link CharToLongFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsLong(char)}.
 *
 * @see ThrowingCharFunction1
 * @see ThrowingToLongFunction1
 * @see ThrowingToLongFunctionN
 * @see CharToLongFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingCharToLongFunction1 extends AbstractThrowingCharToLongFunction1<ThrowingFunction1<Character, Long>, ThrowingToLongFunction1<Character>, ThrowingCharFunction1<Long>, AbstractThrowingCharToLongFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see CharToLongFunction1
     */
    static ThrowingCharToLongFunction1 from(final CharToLongFunction1 function) {
        return function::applyAsLong;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Character, Long> box() {
        return this::applyAsLong;
    }

    /**
     * @see ThrowingToLongFunction1
     */
    @Override
    default ThrowingToLongFunction1<Character> boxInput() {
        return this::applyAsLong;
    }

    /**
     * @see ThrowingCharFunction1
     */
    @Override
    default ThrowingCharFunction1<Long> boxResult() {
        return this::applyAsLong;
    }

    @Override
    default CharToLongFunction1 swallow() {
        return this.handle((t, value) -> 0L);
    }

    /**
     * @see ThrowingToLongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToLongFunction1<V> compose(final Function1<? super V, ? extends Character> before) {
        return (ThrowingToLongFunction1<V>) AbstractThrowingCharToLongFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToLongFunction1
     */
    @Override
    default <V> ThrowingToLongFunction1<V> composeUnchecked(final Function1<? super V, ? extends Character> before) {
        return (final V v) -> this.applyAsLong(before.apply(v));
    }

    /**
     * @see ThrowingCharFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingCharFunction1<V> andThen(final Function1<? super Long, ? extends V> after) {
        return (ThrowingCharFunction1<V>) AbstractThrowingCharToLongFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingCharFunction1
     */
    @Override
    default <V> ThrowingCharFunction1<V> andThenUnchecked(final Function1<? super Long, ? extends V> after) {
        return (final char value) -> after.apply(this.applyAsLong(value));
    }
}
