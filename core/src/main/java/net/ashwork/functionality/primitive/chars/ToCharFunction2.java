/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.primitive.chars;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.Function2;
import net.ashwork.functionality.partial.UnboxedResult;

/**
 * Represents a function that accepts two arguments and produces a {@code char}-valued result.
 * This is the two-arity specialization of {@link ToCharFunctionN}.
 * This is the {@code char}-producing primitive specialization of {@link Function2}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsChar(Object, Object)}.
 *
 * @param <T1> the type of the input to the function
 * @param <T2> the type of the second argument to the function
 *
 * @see Function2
 * @see ToCharFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface ToCharFunction2<T1, T2> extends ToCharFunctionN, UnboxedResult<Function2<T1, T2, Character>> {

    /**
     * Applies this function to the given arguments.
     *
     * @param t1 the first function argument
     * @param t2 the second function argument
     * @return the function result
     */
    char applyAsChar(final T1 t1, final T2 t2);

    @SuppressWarnings("unchecked")
    @Override
    default char applyAllAsCharUnchecked(final Object... args) {
        return this.applyAsChar((T1) args[0], (T2) args[1]);
    }

    @Override
    default int arity() {
        return 2;
    }

    /**
     * @see Function2
     */
    @Override
    default Function2<T1, T2, Character> boxResult() {
        return this::applyAsChar;
    }

    /**
     * @see Function2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Function2<T1, T2, V> andThen(final Function1<? super Character, ? extends V> after) {
        return (Function2<T1, T2, V>) ToCharFunctionN.super.andThen(after);
    }

    /**
     * @see Function2
     */
    @Override
    default <V> Function2<T1, T2, V> andThenUnchecked(final Function1<? super Character, ? extends V> after) {
        return (final T1 t1, final T2 t2) -> after.apply(this.applyAsChar(t1, t2));
    }
}
