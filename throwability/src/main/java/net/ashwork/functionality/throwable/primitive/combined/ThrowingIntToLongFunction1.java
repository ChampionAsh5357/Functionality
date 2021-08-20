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
import net.ashwork.functionality.primitive.combined.IntToLongFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingIntToLongFunction1;
import net.ashwork.functionality.throwable.primitive.ints.ThrowingIntFunction1;
import net.ashwork.functionality.throwable.primitive.longs.ThrowingToLongFunction1;
import net.ashwork.functionality.throwable.primitive.longs.ThrowingToLongFunctionN;

/**
 * Represents a function that accepts an {@code int}-valued argument and produces a {@code long}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToLongFunctionN}.
 * This is the {@code int}-consuming primitive specialization of {@link ThrowingToLongFunction1}.
 * This is the {@code long}-producing primitive specialization of {@link ThrowingIntFunction1}.
 * This is the throwing variation of {@link IntToLongFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsLong(int)}.
 *
 * @see ThrowingIntFunction1
 * @see ThrowingToLongFunction1
 * @see ThrowingToLongFunctionN
 * @see IntToLongFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingIntToLongFunction1 extends AbstractThrowingIntToLongFunction1<ThrowingFunction1<Integer, Long>, ThrowingToLongFunction1<Integer>, ThrowingIntFunction1<Long>, AbstractThrowingIntToLongFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see IntToLongFunction1
     */
    static ThrowingIntToLongFunction1 from(final IntToLongFunction1 function) {
        return function::applyAsLong;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Integer, Long> box() {
        return this::applyAsLong;
    }

    /**
     * @see ThrowingToLongFunction1
     */
    @Override
    default ThrowingToLongFunction1<Integer> boxInput() {
        return this::applyAsLong;
    }

    /**
     * @see ThrowingIntFunction1
     */
    @Override
    default ThrowingIntFunction1<Long> boxResult() {
        return this::applyAsLong;
    }

    @Override
    default IntToLongFunction1 swallow() {
        return this.handle((t, value) -> 0L);
    }

    /**
     * @see ThrowingToLongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToLongFunction1<V> compose(final Function1<? super V, ? extends Integer> before) {
        return (ThrowingToLongFunction1<V>) AbstractThrowingIntToLongFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToLongFunction1
     */
    @Override
    default <V> ThrowingToLongFunction1<V> composeUnchecked(final Function1<? super V, ? extends Integer> before) {
        return (final V v) -> this.applyAsLong(before.apply(v));
    }

    /**
     * @see ThrowingIntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingIntFunction1<V> andThen(final Function1<? super Long, ? extends V> after) {
        return (ThrowingIntFunction1<V>) AbstractThrowingIntToLongFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingIntFunction1
     */
    @Override
    default <V> ThrowingIntFunction1<V> andThenUnchecked(final Function1<? super Long, ? extends V> after) {
        return (final int value) -> after.apply(this.applyAsLong(value));
    }
}
