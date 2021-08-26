/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.primitive.chars;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.primitive.chars.ToCharFunction0;
import net.ashwork.functionality.throwable.ThrowingFunction0;
import net.ashwork.functionality.throwable.abstracts.primitive.chars.AbstractThrowingToCharFunction0;

/**
 * Represents a function that accepts no arguments and produces a {@code char}-valued result or throws a throwable.
 * This is the zero-arity specialization of {@link ThrowingToCharFunctionN}.
 * This is the {@code char}-producing primitive specialization of {@link ThrowingFunction0}.
 * This is the throwing variation of {@link ToCharFunction0}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsChar()}.
 *
 * @see ThrowingFunction0
 * @see ThrowingToCharFunctionN
 * @see ToCharFunction0
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingToCharFunction0 extends AbstractThrowingToCharFunction0<AbstractThrowingToCharFunction0.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ToCharFunction0
     */
    static ThrowingToCharFunction0 from(final ToCharFunction0 function) {
        return function::applyAsChar;
    }

    /**
     * @see ThrowingFunction0
     */
    @Override
    default ThrowingFunction0<Character> boxResult() {
        return this::applyAsChar;
    }

    @Override
    default ToCharFunction0 swallow() {
        return this.handle(t -> '\u0000');
    }

    /**
     * @see ThrowingFunction0
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction0<V> andThen(final Function1<? super Character, ? extends V> after) {
        return (ThrowingFunction0<V>) AbstractThrowingToCharFunction0.super.andThen(after);
    }

    /**
     * @see ThrowingFunction0
     */
    @Override
    default <V> ThrowingFunction0<V> andThenUnchecked(final Function1<? super Character, ? extends V> after) {
        return () -> after.apply(this.applyAsChar());
    }
}
