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
import net.ashwork.functionality.primitive.combined.DoubleToLongFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingDoubleToLongFunction1;
import net.ashwork.functionality.throwable.primitive.doubles.ThrowingDoubleFunction1;
import net.ashwork.functionality.throwable.primitive.longs.ThrowingToLongFunction1;
import net.ashwork.functionality.throwable.primitive.longs.ThrowingToLongFunctionN;

/**
 * Represents a function that accepts a {@code double}-valued argument and produces a {@code long}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToLongFunctionN}.
 * This is the {@code double}-consuming primitive specialization of {@link ThrowingToLongFunction1}.
 * This is the {@code long}-producing primitive specialization of {@link ThrowingDoubleFunction1}.
 * This is the throwing variation of {@link DoubleToLongFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsLong(double)}.
 *
 * @see ThrowingDoubleFunction1
 * @see ThrowingToLongFunction1
 * @see ThrowingToLongFunctionN
 * @see DoubleToLongFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingDoubleToLongFunction1 extends AbstractThrowingDoubleToLongFunction1<ThrowingFunction1<Double, Long>, ThrowingToLongFunction1<Double>, ThrowingDoubleFunction1<Long>, AbstractThrowingDoubleToLongFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see DoubleToLongFunction1
     */
    static ThrowingDoubleToLongFunction1 from(final DoubleToLongFunction1 function) {
        return function::applyAsLong;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Double, Long> box() {
        return this::applyAsLong;
    }

    /**
     * @see ThrowingToLongFunction1
     */
    @Override
    default ThrowingToLongFunction1<Double> boxInput() {
        return this::applyAsLong;
    }

    /**
     * @see ThrowingDoubleFunction1
     */
    @Override
    default ThrowingDoubleFunction1<Long> boxResult() {
        return this::applyAsLong;
    }

    @Override
    default DoubleToLongFunction1 swallow() {
        return this.handle((t, value) -> 0L);
    }

    /**
     * @see ThrowingToLongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToLongFunction1<V> compose(final Function1<? super V, ? extends Double> before) {
        return (ThrowingToLongFunction1<V>) AbstractThrowingDoubleToLongFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToLongFunction1
     */
    @Override
    default <V> ThrowingToLongFunction1<V> composeUnchecked(final Function1<? super V, ? extends Double> before) {
        return (final V v) -> this.applyAsLong(before.apply(v));
    }

    /**
     * @see ThrowingDoubleFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingDoubleFunction1<V> andThen(final Function1<? super Long, ? extends V> after) {
        return (ThrowingDoubleFunction1<V>) AbstractThrowingDoubleToLongFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingDoubleFunction1
     */
    @Override
    default <V> ThrowingDoubleFunction1<V> andThenUnchecked(final Function1<? super Long, ? extends V> after) {
        return (final double value) -> after.apply(this.applyAsLong(value));
    }
}
