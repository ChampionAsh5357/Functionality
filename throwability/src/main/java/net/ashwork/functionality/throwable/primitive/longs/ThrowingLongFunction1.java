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
import net.ashwork.functionality.primitive.longs.LongFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.longs.AbstractThrowingLongFunction1;

/**
 * Represents a function that accepts a {@code long}-valued argument and produces a result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToLongFunctionN}.
 * This is the {@code long}-consuming primitive specialization of {@link ThrowingFunction1}.
 * This is the throwing variation of {@link LongFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #apply(long)}.
 *
 * @param <R> the type of the result of the function
 *
 * @see ThrowingFunction1
 * @see ThrowingToLongFunctionN
 * @see LongFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingLongFunction1<R> extends AbstractThrowingLongFunction1<R, ThrowingFunction1<Long, R>, AbstractThrowingLongFunction1.Handler<R>> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @param <R> the type of the result of the function
     * @return a throwing instance of the original type
     *
     * @see LongFunction1
     */
    static <R> ThrowingLongFunction1<R> from(final LongFunction1<R> function) {
        return function::apply;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Long, R> boxInput() {
        return this::apply;
    }

    @Override
    default LongFunction1<R> swallow() {
        return this.handle((t, value) -> null);
    }

    /**
     * @see ThrowingFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction1<V, R> compose(final Function1<? super V, ? extends Long> before) {
        return (ThrowingFunction1<V, R>) AbstractThrowingLongFunction1.super.compose(before);
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default <V> ThrowingFunction1<V, R> composeUnchecked(final Function1<? super V, ? extends Long> before) {
        return (final V v) -> this.apply(before.apply(v));
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingLongFunction1<V> andThen(final Function1<? super R, ? extends V> after) {
        return (ThrowingLongFunction1<V>) AbstractThrowingLongFunction1.super.andThen(after);
    }

    @Override
    default <V> ThrowingLongFunction1<V> andThenUnchecked(final Function1<? super R, ? extends V> after) {
        return (final long value) -> after.apply(this.apply(value));
    }
}
