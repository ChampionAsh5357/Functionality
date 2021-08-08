/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.primitive.chars;

import net.ashwork.functionality.Function0;
import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.UnboxedResult;

/**
 * Represents a function that accepts no arguments and produces a {@code char}-valued result.
 * This is the zero-arity specialization of {@link ToCharFunctionN}.
 * This is the {@code char}-producing primitive specialization of {@link Function0}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsChar()}.
 *
 * @see Function0
 * @see ToCharFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface ToCharFunction0 extends ToCharFunctionN, UnboxedResult<Function0<Character>> {

    /**
     * Applies this function.
     *
     * @return the function result
     */
    char applyAsChar();

    @Override
    default char applyAllAsCharUnchecked(final Object... args) {
        return this.applyAsChar();
    }

    @Override
    default int arity() {
        return 0;
    }

    /**
     * @see Function0
     */
    @Override
    default Function0<Character> boxResult() {
        return this::applyAsChar;
    }

    /**
     * @see Function0
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Function0<V> andThen(final Function1<? super Character, ? extends V> after) {
        return (Function0<V>) ToCharFunctionN.super.andThen(after);
    }

    /**
     * @see Function0
     */
    @Override
    default <V> Function0<V> andThenUnchecked(final Function1<? super Character, ? extends V> after) {
        return () -> after.apply(this.applyAsChar());
    }
}
