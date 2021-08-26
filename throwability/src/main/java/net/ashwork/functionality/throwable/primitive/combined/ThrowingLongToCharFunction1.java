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
import net.ashwork.functionality.primitive.combined.LongToCharFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingLongToCharFunction1;
import net.ashwork.functionality.throwable.primitive.chars.ThrowingToCharFunction1;
import net.ashwork.functionality.throwable.primitive.chars.ThrowingToCharFunctionN;
import net.ashwork.functionality.throwable.primitive.longs.ThrowingLongFunction1;

/**
 * Represents a function that accepts a {@code long}-valued argument and produces a {@code char}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToCharFunctionN}.
 * This is the {@code long}-consuming primitive specialization of {@link ThrowingToCharFunction1}.
 * This is the {@code char}-producing primitive specialization of {@link ThrowingLongFunction1}.
 * This is the throwing variation of {@link LongToCharFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsChar(long)}.
 *
 * @see ThrowingLongFunction1
 * @see ThrowingToCharFunction1
 * @see ThrowingToCharFunctionN
 * @see LongToCharFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingLongToCharFunction1 extends AbstractThrowingLongToCharFunction1<AbstractThrowingLongToCharFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see LongToCharFunction1
     */
    static ThrowingLongToCharFunction1 from(final LongToCharFunction1 function) {
        return function::applyAsChar;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Long, Character> box() {
        return this::applyAsChar;
    }

    /**
     * @see ThrowingToCharFunction1
     */
    @Override
    default ThrowingToCharFunction1<Long> boxInput() {
        return this::applyAsChar;
    }

    /**
     * @see ThrowingLongFunction1
     */
    @Override
    default ThrowingLongFunction1<Character> boxResult() {
        return this::applyAsChar;
    }

    @Override
    default LongToCharFunction1 swallow() {
        return this.handle((t, value) -> '\u0000');
    }

    /**
     * @see ThrowingToCharFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToCharFunction1<V> compose(final Function1<? super V, ? extends Long> before) {
        return (ThrowingToCharFunction1<V>) AbstractThrowingLongToCharFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToCharFunction1
     */
    @Override
    default <V> ThrowingToCharFunction1<V> composeUnchecked(final Function1<? super V, ? extends Long> before) {
        return (final V v) -> this.applyAsChar(before.apply(v));
    }

    /**
     * @see ThrowingLongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingLongFunction1<V> andThen(final Function1<? super Character, ? extends V> after) {
        return (ThrowingLongFunction1<V>) AbstractThrowingLongToCharFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingLongFunction1
     */
    @Override
    default <V> ThrowingLongFunction1<V> andThenUnchecked(final Function1<? super Character, ? extends V> after) {
        return (final long value) -> after.apply(this.applyAsChar(value));
    }
}
