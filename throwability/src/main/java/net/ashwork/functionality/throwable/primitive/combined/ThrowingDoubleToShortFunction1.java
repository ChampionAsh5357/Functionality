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
import net.ashwork.functionality.primitive.combined.DoubleToShortFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingDoubleToShortFunction1;
import net.ashwork.functionality.throwable.primitive.doubles.ThrowingDoubleFunction1;
import net.ashwork.functionality.throwable.primitive.shorts.ThrowingToShortFunction1;
import net.ashwork.functionality.throwable.primitive.shorts.ThrowingToShortFunctionN;

/**
 * Represents a function that accepts a {@code double}-valued argument and produces a {@code short}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToShortFunctionN}.
 * This is the {@code double}-consuming primitive specialization of {@link ThrowingToShortFunction1}.
 * This is the {@code short}-producing primitive specialization of {@link ThrowingDoubleFunction1}.
 * This is the throwing variation of {@link DoubleToShortFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsShort(double)}.
 *
 * @see ThrowingDoubleFunction1
 * @see ThrowingToShortFunction1
 * @see ThrowingToShortFunctionN
 * @see DoubleToShortFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingDoubleToShortFunction1 extends AbstractThrowingDoubleToShortFunction1<ThrowingFunction1<Double, Short>, ThrowingToShortFunction1<Double>, ThrowingDoubleFunction1<Short>, AbstractThrowingDoubleToShortFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see DoubleToShortFunction1
     */
    static ThrowingDoubleToShortFunction1 from(final DoubleToShortFunction1 function) {
        return function::applyAsShort;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Double, Short> box() {
        return this::applyAsShort;
    }

    /**
     * @see ThrowingToShortFunction1
     */
    @Override
    default ThrowingToShortFunction1<Double> boxInput() {
        return this::applyAsShort;
    }

    /**
     * @see ThrowingDoubleFunction1
     */
    @Override
    default ThrowingDoubleFunction1<Short> boxResult() {
        return this::applyAsShort;
    }

    @Override
    default DoubleToShortFunction1 swallow() {
        return this.handle((t, value) -> (short) 0);
    }

    /**
     * @see ThrowingToShortFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToShortFunction1<V> compose(final Function1<? super V, ? extends Double> before) {
        return (ThrowingToShortFunction1<V>) AbstractThrowingDoubleToShortFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToShortFunction1
     */
    @Override
    default <V> ThrowingToShortFunction1<V> composeUnchecked(final Function1<? super V, ? extends Double> before) {
        return (final V v) -> this.applyAsShort(before.apply(v));
    }

    /**
     * @see ThrowingDoubleFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingDoubleFunction1<V> andThen(final Function1<? super Short, ? extends V> after) {
        return (ThrowingDoubleFunction1<V>) AbstractThrowingDoubleToShortFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingDoubleFunction1
     */
    @Override
    default <V> ThrowingDoubleFunction1<V> andThenUnchecked(final Function1<? super Short, ? extends V> after) {
        return (final double value) -> after.apply(this.applyAsShort(value));
    }
}
