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
import net.ashwork.functionality.primitive.combined.CharToShortFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingCharToShortFunction1;
import net.ashwork.functionality.throwable.primitive.chars.ThrowingCharFunction1;
import net.ashwork.functionality.throwable.primitive.shorts.ThrowingToShortFunction1;
import net.ashwork.functionality.throwable.primitive.shorts.ThrowingToShortFunctionN;

/**
 * Represents a function that accepts a {@code char}-valued argument and produces a {@code short}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToShortFunctionN}.
 * This is the {@code char}-consuming primitive specialization of {@link ThrowingToShortFunction1}.
 * This is the {@code short}-producing primitive specialization of {@link ThrowingCharFunction1}.
 * This is the throwing variation of {@link CharToShortFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsShort(char)}.
 *
 * @see ThrowingCharFunction1
 * @see ThrowingToShortFunction1
 * @see ThrowingToShortFunctionN
 * @see CharToShortFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingCharToShortFunction1 extends AbstractThrowingCharToShortFunction1<AbstractThrowingCharToShortFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see CharToShortFunction1
     */
    static ThrowingCharToShortFunction1 from(final CharToShortFunction1 function) {
        return function::applyAsShort;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Character, Short> box() {
        return this::applyAsShort;
    }

    /**
     * @see ThrowingToShortFunction1
     */
    @Override
    default ThrowingToShortFunction1<Character> boxInput() {
        return this::applyAsShort;
    }

    /**
     * @see ThrowingCharFunction1
     */
    @Override
    default ThrowingCharFunction1<Short> boxResult() {
        return this::applyAsShort;
    }

    @Override
    default CharToShortFunction1 swallow() {
        return this.handle((t, value) -> (short) 0);
    }

    /**
     * @see ThrowingToShortFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToShortFunction1<V> compose(final Function1<? super V, ? extends Character> before) {
        return (ThrowingToShortFunction1<V>) AbstractThrowingCharToShortFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToShortFunction1
     */
    @Override
    default <V> ThrowingToShortFunction1<V> composeUnchecked(final Function1<? super V, ? extends Character> before) {
        return (final V v) -> this.applyAsShort(before.apply(v));
    }

    /**
     * @see ThrowingCharFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingCharFunction1<V> andThen(final Function1<? super Short, ? extends V> after) {
        return (ThrowingCharFunction1<V>) AbstractThrowingCharToShortFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingCharFunction1
     */
    @Override
    default <V> ThrowingCharFunction1<V> andThenUnchecked(final Function1<? super Short, ? extends V> after) {
        return (final char value) -> after.apply(this.applyAsShort(value));
    }
}
