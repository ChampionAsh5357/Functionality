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
import net.ashwork.functionality.primitive.chars.ToCharFunction2;
import net.ashwork.functionality.throwable.ThrowingFunction2;
import net.ashwork.functionality.throwable.abstracts.primitive.chars.AbstractThrowingToCharFunction2;

/**
 * Represents a function that accepts two arguments and produces a {@code char}-valued result or throws a throwable.
 * This is the two-arity specialization of {@link ThrowingToCharFunctionN}.
 * This is the {@code char}-producing primitive specialization of {@link ThrowingFunction2}.
 * This is the throwing variation of {@link ToCharFunction2}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsChar(Object, Object)}.
 *
 * @param <T1> the type of the first argument to the function
 * @param <T2> the type of the second argument to the function
 *
 * @see ThrowingFunction2
 * @see ThrowingToCharFunctionN
 * @see ToCharFunction2
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingToCharFunction2<T1, T2> extends AbstractThrowingToCharFunction2<T1, T2, ThrowingFunction2<T1, T2, Character>, AbstractThrowingToCharFunction2.Handler<T1, T2>> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @param <T1> the type of the first argument to the function
     * @param <T2> the type of the second argument to the function
     * @return a throwing instance of the original type
     *
     * @see ToCharFunction2
     */
    static <T1, T2> ThrowingToCharFunction2<T1, T2> from(final ToCharFunction2<T1, T2> function) {
        return function::applyAsChar;
    }

    /**
     * @see ThrowingFunction2
     */
    @Override
    default ThrowingFunction2<T1, T2, Character> boxResult() {
        return this::applyAsChar;
    }

    @Override
    default ToCharFunction2<T1, T2> swallow() {
        return this.handle((t, t1, t2) -> '\u0000');
    }


    /**
     * @see ThrowingFunction2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction2<T1, T2, V> andThen(final Function1<? super Character, ? extends V> after) {
        return (ThrowingFunction2<T1, T2, V>) AbstractThrowingToCharFunction2.super.andThen(after);
    }

    /**
     * @see ThrowingFunction2
     */
    @Override
    default <V> ThrowingFunction2<T1, T2, V> andThenUnchecked(final Function1<? super Character, ? extends V> after) {
        return (final T1 t1, final T2 t2) -> after.apply(this.applyAsChar(t1, t2));
    }
}
