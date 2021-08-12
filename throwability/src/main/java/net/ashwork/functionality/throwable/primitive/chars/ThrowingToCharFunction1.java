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
import net.ashwork.functionality.primitive.chars.ToCharFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.chars.AbstractThrowingToCharFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.chars.AbstractThrowingToCharFunctionN;

/**
 * Represents a function that accepts one argument and produces a {@code char}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingToCharFunctionN}.
 * This is the {@code char}-producing primitive specialization of {@link AbstractThrowingFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsChar(Object)}.
 *
 * @param <T1> the type of the input to the function
 *
 * @see AbstractThrowingFunction1
 * @see AbstractThrowingToCharFunctionN
 * @since 1.0.0
 */
public interface ThrowingToCharFunction1<T1> extends AbstractThrowingToCharFunction1<T1, AbstractThrowingToCharFunction1.Handler<T1>> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @param <T1> the type of the input to the function
     * @return a throwing instance of the original type
     *
     * @see ToCharFunction1
     */
    static <T1> ThrowingToCharFunction1<T1> from(final ToCharFunction1<T1> function) {
        return function::applyAsChar;
    }

    @Override
    default ToCharFunction1<T1> swallow() {
        return this.handle((t, t1) -> '\u0000');
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToCharFunction1<V> compose(final Function1<? super V, ? extends T1> before) {
        return (ThrowingToCharFunction1<V>) AbstractThrowingToCharFunction1.super.compose(before);
    }

    @Override
    default <V> ThrowingToCharFunction1<V> composeUnchecked(final Function1<? super V, ? extends T1> before) {
        return (final V v) -> this.applyAsChar(before.apply(v));
    }

    /**
     * @see ThrowingFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction1<T1, V> andThen(final Function1<? super Character, ? extends V> after) {
        return (ThrowingFunction1<T1, V>) AbstractThrowingToCharFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default <V> ThrowingFunction1<T1, V> andThenUnchecked(final Function1<? super Character, ? extends V> after) {
        return (final T1 t1) -> after.apply(this.applyAsChar(t1));
    }
}
