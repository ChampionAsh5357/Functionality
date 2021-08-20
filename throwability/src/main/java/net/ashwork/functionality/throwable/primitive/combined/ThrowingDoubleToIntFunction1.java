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
import net.ashwork.functionality.primitive.combined.DoubleToIntFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingDoubleToIntFunction1;
import net.ashwork.functionality.throwable.primitive.doubles.ThrowingDoubleFunction1;
import net.ashwork.functionality.throwable.primitive.ints.ThrowingToIntFunction1;
import net.ashwork.functionality.throwable.primitive.ints.ThrowingToIntFunctionN;

/**
 * Represents a function that accepts a {@code double}-valued argument and produces an {@code int}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToIntFunctionN}.
 * This is the {@code double}-consuming primitive specialization of {@link ThrowingToIntFunction1}.
 * This is the {@code int}-producing primitive specialization of {@link ThrowingDoubleFunction1}.
 * This is the throwing variation of {@link DoubleToIntFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsInt(double)}.
 *
 * @see ThrowingDoubleFunction1
 * @see ThrowingToIntFunction1
 * @see ThrowingToIntFunctionN
 * @see DoubleToIntFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingDoubleToIntFunction1 extends AbstractThrowingDoubleToIntFunction1<ThrowingFunction1<Double, Integer>, ThrowingToIntFunction1<Double>, ThrowingDoubleFunction1<Integer>, AbstractThrowingDoubleToIntFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see DoubleToIntFunction1
     */
    static ThrowingDoubleToIntFunction1 from(final DoubleToIntFunction1 function) {
        return function::applyAsInt;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Double, Integer> box() {
        return this::applyAsInt;
    }

    /**
     * @see ThrowingToIntFunction1
     */
    @Override
    default ThrowingToIntFunction1<Double> boxInput() {
        return this::applyAsInt;
    }

    /**
     * @see ThrowingDoubleFunction1
     */
    @Override
    default ThrowingDoubleFunction1<Integer> boxResult() {
        return this::applyAsInt;
    }

    @Override
    default DoubleToIntFunction1 swallow() {
        return this.handle((t, value) -> 0);
    }

    /**
     * @see ThrowingToIntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToIntFunction1<V> compose(final Function1<? super V, ? extends Double> before) {
        return (ThrowingToIntFunction1<V>) AbstractThrowingDoubleToIntFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToIntFunction1
     */
    @Override
    default <V> ThrowingToIntFunction1<V> composeUnchecked(final Function1<? super V, ? extends Double> before) {
        return (final V v) -> this.applyAsInt(before.apply(v));
    }

    /**
     * @see ThrowingDoubleFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingDoubleFunction1<V> andThen(final Function1<? super Integer, ? extends V> after) {
        return (ThrowingDoubleFunction1<V>) AbstractThrowingDoubleToIntFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingDoubleFunction1
     */
    @Override
    default <V> ThrowingDoubleFunction1<V> andThenUnchecked(final Function1<? super Integer, ? extends V> after) {
        return (final double value) -> after.apply(this.applyAsInt(value));
    }
}
