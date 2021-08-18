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
import net.ashwork.functionality.primitive.longs.ToLongFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.longs.AbstractThrowingToLongFunction1;

/**
 * Represents a function that accepts one argument and produces a {@code long}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToLongFunctionN}.
 * This is the {@code long}-producing primitive specialization of {@link ThrowingFunction1}.
 * This is the throwing variation of {@link ToLongFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsLong(Object)}.
 *
 * @param <T1> the type of the input to the function
 *
 * @see ThrowingFunction1
 * @see ThrowingToLongFunctionN
 * @see ToLongFunction1
 * @since 1.0.0
 */
public interface ThrowingToLongFunction1<T1> extends AbstractThrowingToLongFunction1<T1, ThrowingFunction1<T1, Long>, AbstractThrowingToLongFunction1.Handler<T1>> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @param <T1> the type of the input to the function
     * @return a throwing instance of the original type
     *
     * @see ToLongFunction1
     */
    static <T1> ThrowingToLongFunction1<T1> from(final ToLongFunction1<T1> function) {
        return function::applyAsLong;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<T1, Long> boxResult() {
        return this::applyAsLong;
    }

    @Override
    default ToLongFunction1<T1> swallow() {
        return this.handle((t, t1) -> 0L);
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToLongFunction1<V> compose(final Function1<? super V, ? extends T1> before) {
        return (ThrowingToLongFunction1<V>) AbstractThrowingToLongFunction1.super.compose(before);
    }

    @Override
    default <V> ThrowingToLongFunction1<V> composeUnchecked(final Function1<? super V, ? extends T1> before) {
        return (final V v) -> this.applyAsLong(before.apply(v));
    }

    /**
     * @see ThrowingFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction1<T1, V> andThen(final Function1<? super Long, ? extends V> after) {
        return (ThrowingFunction1<T1, V>) AbstractThrowingToLongFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default <V> ThrowingFunction1<T1, V> andThenUnchecked(final Function1<? super Long, ? extends V> after) {
        return (final T1 t1) -> after.apply(this.applyAsLong(t1));
    }
}
