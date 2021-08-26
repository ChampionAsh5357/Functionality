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
import net.ashwork.functionality.primitive.combined.ShortToLongFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingShortToLongFunction1;
import net.ashwork.functionality.throwable.primitive.longs.ThrowingToLongFunction1;
import net.ashwork.functionality.throwable.primitive.longs.ThrowingToLongFunctionN;
import net.ashwork.functionality.throwable.primitive.shorts.ThrowingShortFunction1;

/**
 * Represents a function that accepts a {@code short}-valued argument and produces a {@code long}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToLongFunctionN}.
 * This is the {@code short}-consuming primitive specialization of {@link ThrowingToLongFunction1}.
 * This is the {@code long}-producing primitive specialization of {@link ThrowingShortFunction1}.
 * This is the throwing variation of {@link ShortToLongFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsLong(short)}.
 *
 * @see ThrowingShortFunction1
 * @see ThrowingToLongFunction1
 * @see ThrowingToLongFunctionN
 * @see ShortToLongFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingShortToLongFunction1 extends AbstractThrowingShortToLongFunction1<AbstractThrowingShortToLongFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ShortToLongFunction1
     */
    static ThrowingShortToLongFunction1 from(final ShortToLongFunction1 function) {
        return function::applyAsLong;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Short, Long> box() {
        return this::applyAsLong;
    }

    /**
     * @see ThrowingToLongFunction1
     */
    @Override
    default ThrowingToLongFunction1<Short> boxInput() {
        return this::applyAsLong;
    }

    /**
     * @see ThrowingShortFunction1
     */
    @Override
    default ThrowingShortFunction1<Long> boxResult() {
        return this::applyAsLong;
    }

    @Override
    default ShortToLongFunction1 swallow() {
        return this.handle((t, value) -> 0L);
    }

    /**
     * @see ThrowingToLongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToLongFunction1<V> compose(final Function1<? super V, ? extends Short> before) {
        return (ThrowingToLongFunction1<V>) AbstractThrowingShortToLongFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToLongFunction1
     */
    @Override
    default <V> ThrowingToLongFunction1<V> composeUnchecked(final Function1<? super V, ? extends Short> before) {
        return (final V v) -> this.applyAsLong(before.apply(v));
    }

    /**
     * @see ThrowingShortFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingShortFunction1<V> andThen(final Function1<? super Long, ? extends V> after) {
        return (ThrowingShortFunction1<V>) AbstractThrowingShortToLongFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingShortFunction1
     */
    @Override
    default <V> ThrowingShortFunction1<V> andThenUnchecked(final Function1<? super Long, ? extends V> after) {
        return (final short value) -> after.apply(this.applyAsLong(value));
    }
}
