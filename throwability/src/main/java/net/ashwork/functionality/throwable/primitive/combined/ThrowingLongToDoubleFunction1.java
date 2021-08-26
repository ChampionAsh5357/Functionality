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
import net.ashwork.functionality.primitive.combined.LongToDoubleFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingLongToDoubleFunction1;
import net.ashwork.functionality.throwable.primitive.doubles.ThrowingToDoubleFunction1;
import net.ashwork.functionality.throwable.primitive.doubles.ThrowingToDoubleFunctionN;
import net.ashwork.functionality.throwable.primitive.longs.ThrowingLongFunction1;

/**
 * Represents a function that accepts a {@code long}-valued argument and produces a {@code double}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToDoubleFunctionN}.
 * This is the {@code long}-consuming primitive specialization of {@link ThrowingToDoubleFunction1}.
 * This is the {@code double}-producing primitive specialization of {@link ThrowingLongFunction1}.
 * This is the throwing variation of {@link LongToDoubleFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsDouble(long)}.
 *
 * @see ThrowingLongFunction1
 * @see ThrowingToDoubleFunction1
 * @see ThrowingToDoubleFunctionN
 * @see LongToDoubleFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingLongToDoubleFunction1 extends AbstractThrowingLongToDoubleFunction1<AbstractThrowingLongToDoubleFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see LongToDoubleFunction1
     */
    static ThrowingLongToDoubleFunction1 from(final LongToDoubleFunction1 function) {
        return function::applyAsDouble;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Long, Double> box() {
        return this::applyAsDouble;
    }

    /**
     * @see ThrowingToDoubleFunction1
     */
    @Override
    default ThrowingToDoubleFunction1<Long> boxInput() {
        return this::applyAsDouble;
    }

    /**
     * @see ThrowingLongFunction1
     */
    @Override
    default ThrowingLongFunction1<Double> boxResult() {
        return this::applyAsDouble;
    }

    @Override
    default LongToDoubleFunction1 swallow() {
        return this.handle((t, value) -> 0.0d);
    }

    /**
     * @see ThrowingToDoubleFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToDoubleFunction1<V> compose(final Function1<? super V, ? extends Long> before) {
        return (ThrowingToDoubleFunction1<V>) AbstractThrowingLongToDoubleFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToDoubleFunction1
     */
    @Override
    default <V> ThrowingToDoubleFunction1<V> composeUnchecked(final Function1<? super V, ? extends Long> before) {
        return (final V v) -> this.applyAsDouble(before.apply(v));
    }

    /**
     * @see ThrowingLongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingLongFunction1<V> andThen(final Function1<? super Double, ? extends V> after) {
        return (ThrowingLongFunction1<V>) AbstractThrowingLongToDoubleFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingLongFunction1
     */
    @Override
    default <V> ThrowingLongFunction1<V> andThenUnchecked(final Function1<? super Double, ? extends V> after) {
        return (final long value) -> after.apply(this.applyAsDouble(value));
    }
}
