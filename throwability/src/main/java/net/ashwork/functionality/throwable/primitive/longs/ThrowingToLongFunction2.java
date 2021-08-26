/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.primitive.longs;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.primitive.longs.ToLongFunction2;
import net.ashwork.functionality.throwable.ThrowingFunction2;
import net.ashwork.functionality.throwable.abstracts.primitive.longs.AbstractThrowingToLongFunction2;

/**
 * Represents a function that accepts two arguments and produces a {@code long}-valued result or throws a throwable.
 * This is the two-arity specialization of {@link ThrowingToLongFunctionN}.
 * This is the {@code long}-producing primitive specialization of {@link ThrowingFunction2}.
 * This is the throwing variation of {@link ToLongFunction2}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsLong(Object, Object)}.
 *
 * @param <T1> the type of the first argument to the function
 * @param <T2> the type of the second argument to the function
 *
 * @see ThrowingFunction2
 * @see ThrowingToLongFunctionN
 * @see ToLongFunction2
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingToLongFunction2<T1, T2> extends AbstractThrowingToLongFunction2<T1, T2, AbstractThrowingToLongFunction2.Handler<T1, T2>> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @param <T1> the type of the first argument to the function
     * @param <T2> the type of the second argument to the function
     * @return a throwing instance of the original type
     *
     * @see ToLongFunction2
     */
    static <T1, T2> ThrowingToLongFunction2<T1, T2> from(final ToLongFunction2<T1, T2> function) {
        return function::applyAsLong;
    }

    /**
     * @see ThrowingFunction2
     */
    @Override
    default ThrowingFunction2<T1, T2, Long> boxResult() {
        return this::applyAsLong;
    }

    @Override
    default ToLongFunction2<T1, T2> swallow() {
        return this.handle((t, t1, t2) -> 0L);
    }


    /**
     * @see ThrowingFunction2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction2<T1, T2, V> andThen(final Function1<? super Long, ? extends V> after) {
        return (ThrowingFunction2<T1, T2, V>) AbstractThrowingToLongFunction2.super.andThen(after);
    }

    /**
     * @see ThrowingFunction2
     */
    @Override
    default <V> ThrowingFunction2<T1, T2, V> andThenUnchecked(final Function1<? super Long, ? extends V> after) {
        return (final T1 t1, final T2 t2) -> after.apply(this.applyAsLong(t1, t2));
    }
}
