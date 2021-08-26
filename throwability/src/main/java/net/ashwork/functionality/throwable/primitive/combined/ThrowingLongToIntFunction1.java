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
import net.ashwork.functionality.primitive.combined.LongToIntFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingLongToIntFunction1;
import net.ashwork.functionality.throwable.primitive.ints.ThrowingToIntFunction1;
import net.ashwork.functionality.throwable.primitive.ints.ThrowingToIntFunctionN;
import net.ashwork.functionality.throwable.primitive.longs.ThrowingLongFunction1;

/**
 * Represents a function that accepts a {@code long}-valued argument and produces an {@code int}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToIntFunctionN}.
 * This is the {@code long}-consuming primitive specialization of {@link ThrowingToIntFunction1}.
 * This is the {@code int}-producing primitive specialization of {@link ThrowingLongFunction1}.
 * This is the throwing variation of {@link LongToIntFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsInt(long)}.
 *
 * @see ThrowingLongFunction1
 * @see ThrowingToIntFunction1
 * @see ThrowingToIntFunctionN
 * @see LongToIntFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingLongToIntFunction1 extends AbstractThrowingLongToIntFunction1<AbstractThrowingLongToIntFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see LongToIntFunction1
     */
    static ThrowingLongToIntFunction1 from(final LongToIntFunction1 function) {
        return function::applyAsInt;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Long, Integer> box() {
        return this::applyAsInt;
    }

    /**
     * @see ThrowingToIntFunction1
     */
    @Override
    default ThrowingToIntFunction1<Long> boxInput() {
        return this::applyAsInt;
    }

    /**
     * @see ThrowingLongFunction1
     */
    @Override
    default ThrowingLongFunction1<Integer> boxResult() {
        return this::applyAsInt;
    }

    @Override
    default LongToIntFunction1 swallow() {
        return this.handle((t, value) -> 0);
    }

    /**
     * @see ThrowingToIntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToIntFunction1<V> compose(final Function1<? super V, ? extends Long> before) {
        return (ThrowingToIntFunction1<V>) AbstractThrowingLongToIntFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToIntFunction1
     */
    @Override
    default <V> ThrowingToIntFunction1<V> composeUnchecked(final Function1<? super V, ? extends Long> before) {
        return (final V v) -> this.applyAsInt(before.apply(v));
    }

    /**
     * @see ThrowingLongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingLongFunction1<V> andThen(final Function1<? super Integer, ? extends V> after) {
        return (ThrowingLongFunction1<V>) AbstractThrowingLongToIntFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingLongFunction1
     */
    @Override
    default <V> ThrowingLongFunction1<V> andThenUnchecked(final Function1<? super Integer, ? extends V> after) {
        return (final long value) -> after.apply(this.applyAsInt(value));
    }
}
