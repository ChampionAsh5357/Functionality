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
import net.ashwork.functionality.primitive.combined.ShortToIntFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingShortToIntFunction1;
import net.ashwork.functionality.throwable.primitive.shorts.ThrowingShortFunction1;
import net.ashwork.functionality.throwable.primitive.ints.ThrowingToIntFunction1;
import net.ashwork.functionality.throwable.primitive.ints.ThrowingToIntFunctionN;

/**
 * Represents a function that accepts a {@code short}-valued argument and produces an {@code int}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToIntFunctionN}.
 * This is the {@code short}-consuming primitive specialization of {@link ThrowingToIntFunction1}.
 * This is the {@code int}-producing primitive specialization of {@link ThrowingShortFunction1}.
 * This is the throwing variation of {@link ShortToIntFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsInt(short)}.
 *
 * @see ThrowingShortFunction1
 * @see ThrowingToIntFunction1
 * @see ThrowingToIntFunctionN
 * @see ShortToIntFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingShortToIntFunction1 extends AbstractThrowingShortToIntFunction1<ThrowingFunction1<Short, Integer>, ThrowingToIntFunction1<Short>, ThrowingShortFunction1<Integer>, AbstractThrowingShortToIntFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ShortToIntFunction1
     */
    static ThrowingShortToIntFunction1 from(final ShortToIntFunction1 function) {
        return function::applyAsInt;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Short, Integer> box() {
        return this::applyAsInt;
    }

    /**
     * @see ThrowingToIntFunction1
     */
    @Override
    default ThrowingToIntFunction1<Short> boxInput() {
        return this::applyAsInt;
    }

    /**
     * @see ThrowingShortFunction1
     */
    @Override
    default ThrowingShortFunction1<Integer> boxResult() {
        return this::applyAsInt;
    }

    @Override
    default ShortToIntFunction1 swallow() {
        return this.handle((t, value) -> 0);
    }

    /**
     * @see ThrowingToIntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToIntFunction1<V> compose(final Function1<? super V, ? extends Short> before) {
        return (ThrowingToIntFunction1<V>) AbstractThrowingShortToIntFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToIntFunction1
     */
    @Override
    default <V> ThrowingToIntFunction1<V> composeUnchecked(final Function1<? super V, ? extends Short> before) {
        return (final V v) -> this.applyAsInt(before.apply(v));
    }

    /**
     * @see ThrowingShortFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingShortFunction1<V> andThen(final Function1<? super Integer, ? extends V> after) {
        return (ThrowingShortFunction1<V>) AbstractThrowingShortToIntFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingShortFunction1
     */
    @Override
    default <V> ThrowingShortFunction1<V> andThenUnchecked(final Function1<? super Integer, ? extends V> after) {
        return (final short value) -> after.apply(this.applyAsInt(value));
    }
}
