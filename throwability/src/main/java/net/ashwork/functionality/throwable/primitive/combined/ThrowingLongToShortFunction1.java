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
import net.ashwork.functionality.primitive.combined.LongToShortFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingLongToShortFunction1;
import net.ashwork.functionality.throwable.primitive.longs.ThrowingLongFunction1;
import net.ashwork.functionality.throwable.primitive.shorts.ThrowingToShortFunction1;
import net.ashwork.functionality.throwable.primitive.shorts.ThrowingToShortFunctionN;

/**
 * Represents a function that accepts a {@code long}-valued argument and produces a {@code short}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToShortFunctionN}.
 * This is the {@code long}-consuming primitive specialization of {@link ThrowingToShortFunction1}.
 * This is the {@code short}-producing primitive specialization of {@link ThrowingLongFunction1}.
 * This is the throwing variation of {@link LongToShortFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsShort(long)}.
 *
 * @see ThrowingLongFunction1
 * @see ThrowingToShortFunction1
 * @see ThrowingToShortFunctionN
 * @see LongToShortFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingLongToShortFunction1 extends AbstractThrowingLongToShortFunction1<AbstractThrowingLongToShortFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see LongToShortFunction1
     */
    static ThrowingLongToShortFunction1 from(final LongToShortFunction1 function) {
        return function::applyAsShort;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Long, Short> box() {
        return this::applyAsShort;
    }

    /**
     * @see ThrowingToShortFunction1
     */
    @Override
    default ThrowingToShortFunction1<Long> boxInput() {
        return this::applyAsShort;
    }

    /**
     * @see ThrowingLongFunction1
     */
    @Override
    default ThrowingLongFunction1<Short> boxResult() {
        return this::applyAsShort;
    }

    @Override
    default LongToShortFunction1 swallow() {
        return this.handle((t, value) -> (short) 0);
    }

    /**
     * @see ThrowingToShortFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToShortFunction1<V> compose(final Function1<? super V, ? extends Long> before) {
        return (ThrowingToShortFunction1<V>) AbstractThrowingLongToShortFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToShortFunction1
     */
    @Override
    default <V> ThrowingToShortFunction1<V> composeUnchecked(final Function1<? super V, ? extends Long> before) {
        return (final V v) -> this.applyAsShort(before.apply(v));
    }

    /**
     * @see ThrowingLongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingLongFunction1<V> andThen(final Function1<? super Short, ? extends V> after) {
        return (ThrowingLongFunction1<V>) AbstractThrowingLongToShortFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingLongFunction1
     */
    @Override
    default <V> ThrowingLongFunction1<V> andThenUnchecked(final Function1<? super Short, ? extends V> after) {
        return (final long value) -> after.apply(this.applyAsShort(value));
    }
}
