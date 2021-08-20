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
import net.ashwork.functionality.primitive.combined.ShortToCharFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingShortToCharFunction1;
import net.ashwork.functionality.throwable.primitive.shorts.ThrowingShortFunction1;
import net.ashwork.functionality.throwable.primitive.chars.ThrowingToCharFunction1;
import net.ashwork.functionality.throwable.primitive.chars.ThrowingToCharFunctionN;

/**
 * Represents a function that accepts a {@code short}-valued argument and produces a {@code char}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToCharFunctionN}.
 * This is the {@code short}-consuming primitive specialization of {@link ThrowingToCharFunction1}.
 * This is the {@code char}-producing primitive specialization of {@link ThrowingShortFunction1}.
 * This is the throwing variation of {@link ShortToCharFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsChar(short)}.
 *
 * @see ThrowingShortFunction1
 * @see ThrowingToCharFunction1
 * @see ThrowingToCharFunctionN
 * @see ShortToCharFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingShortToCharFunction1 extends AbstractThrowingShortToCharFunction1<ThrowingFunction1<Short, Character>, ThrowingToCharFunction1<Short>, ThrowingShortFunction1<Character>, AbstractThrowingShortToCharFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ShortToCharFunction1
     */
    static ThrowingShortToCharFunction1 from(final ShortToCharFunction1 function) {
        return function::applyAsChar;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Short, Character> box() {
        return this::applyAsChar;
    }

    /**
     * @see ThrowingToCharFunction1
     */
    @Override
    default ThrowingToCharFunction1<Short> boxInput() {
        return this::applyAsChar;
    }

    /**
     * @see ThrowingShortFunction1
     */
    @Override
    default ThrowingShortFunction1<Character> boxResult() {
        return this::applyAsChar;
    }

    @Override
    default ShortToCharFunction1 swallow() {
        return this.handle((t, value) -> '\u0000');
    }

    /**
     * @see ThrowingToCharFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToCharFunction1<V> compose(final Function1<? super V, ? extends Short> before) {
        return (ThrowingToCharFunction1<V>) AbstractThrowingShortToCharFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToCharFunction1
     */
    @Override
    default <V> ThrowingToCharFunction1<V> composeUnchecked(final Function1<? super V, ? extends Short> before) {
        return (final V v) -> this.applyAsChar(before.apply(v));
    }

    /**
     * @see ThrowingShortFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingShortFunction1<V> andThen(final Function1<? super Character, ? extends V> after) {
        return (ThrowingShortFunction1<V>) AbstractThrowingShortToCharFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingShortFunction1
     */
    @Override
    default <V> ThrowingShortFunction1<V> andThenUnchecked(final Function1<? super Character, ? extends V> after) {
        return (final short value) -> after.apply(this.applyAsChar(value));
    }
}
