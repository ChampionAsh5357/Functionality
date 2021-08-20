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
import net.ashwork.functionality.primitive.combined.IntToCharFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingIntToCharFunction1;
import net.ashwork.functionality.throwable.primitive.ints.ThrowingIntFunction1;
import net.ashwork.functionality.throwable.primitive.chars.ThrowingToCharFunction1;
import net.ashwork.functionality.throwable.primitive.chars.ThrowingToCharFunctionN;

/**
 * Represents a function that accepts an {@code int}-valued argument and produces a {@code char}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToCharFunctionN}.
 * This is the {@code int}-consuming primitive specialization of {@link ThrowingToCharFunction1}.
 * This is the {@code char}-producing primitive specialization of {@link ThrowingIntFunction1}.
 * This is the throwing variation of {@link IntToCharFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsChar(int)}.
 *
 * @see ThrowingIntFunction1
 * @see ThrowingToCharFunction1
 * @see ThrowingToCharFunctionN
 * @see IntToCharFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingIntToCharFunction1 extends AbstractThrowingIntToCharFunction1<ThrowingFunction1<Integer, Character>, ThrowingToCharFunction1<Integer>, ThrowingIntFunction1<Character>, AbstractThrowingIntToCharFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see IntToCharFunction1
     */
    static ThrowingIntToCharFunction1 from(final IntToCharFunction1 function) {
        return function::applyAsChar;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Integer, Character> box() {
        return this::applyAsChar;
    }

    /**
     * @see ThrowingToCharFunction1
     */
    @Override
    default ThrowingToCharFunction1<Integer> boxInput() {
        return this::applyAsChar;
    }

    /**
     * @see ThrowingIntFunction1
     */
    @Override
    default ThrowingIntFunction1<Character> boxResult() {
        return this::applyAsChar;
    }

    @Override
    default IntToCharFunction1 swallow() {
        return this.handle((t, value) -> '\u0000');
    }

    /**
     * @see ThrowingToCharFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToCharFunction1<V> compose(final Function1<? super V, ? extends Integer> before) {
        return (ThrowingToCharFunction1<V>) AbstractThrowingIntToCharFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToCharFunction1
     */
    @Override
    default <V> ThrowingToCharFunction1<V> composeUnchecked(final Function1<? super V, ? extends Integer> before) {
        return (final V v) -> this.applyAsChar(before.apply(v));
    }

    /**
     * @see ThrowingIntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingIntFunction1<V> andThen(final Function1<? super Character, ? extends V> after) {
        return (ThrowingIntFunction1<V>) AbstractThrowingIntToCharFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingIntFunction1
     */
    @Override
    default <V> ThrowingIntFunction1<V> andThenUnchecked(final Function1<? super Character, ? extends V> after) {
        return (final int value) -> after.apply(this.applyAsChar(value));
    }
}
