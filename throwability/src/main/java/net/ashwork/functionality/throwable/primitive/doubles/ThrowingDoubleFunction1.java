/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.primitive.doubles;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.primitive.doubles.DoubleFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.doubles.AbstractThrowingDoubleFunction1;

/**
 * Represents a function that accepts a {@code double}-valued argument and produces a result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToDoubleFunctionN}.
 * This is the {@code double}-consuming primitive specialization of {@link ThrowingFunction1}.
 * This is the throwing variation of {@link DoubleFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #apply(double)}.
 *
 * @param <R> the type of the result of the function
 *
 * @see ThrowingFunction1
 * @see ThrowingToDoubleFunctionN
 * @see DoubleFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingDoubleFunction1<R> extends AbstractThrowingDoubleFunction1<R, AbstractThrowingDoubleFunction1.Handler<R>> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @param <R> the type of the result of the function
     * @return a throwing instance of the original type
     *
     * @see DoubleFunction1
     */
    static <R> ThrowingDoubleFunction1<R> from(final DoubleFunction1<R> function) {
        return function::apply;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Double, R> boxInput() {
        return this::apply;
    }

    @Override
    default DoubleFunction1<R> swallow() {
        return this.handle((t, value) -> null);
    }

    /**
     * @see ThrowingFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction1<V, R> compose(final Function1<? super V, ? extends Double> before) {
        return (ThrowingFunction1<V, R>) AbstractThrowingDoubleFunction1.super.compose(before);
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default <V> ThrowingFunction1<V, R> composeUnchecked(final Function1<? super V, ? extends Double> before) {
        return (final V v) -> this.apply(before.apply(v));
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingDoubleFunction1<V> andThen(final Function1<? super R, ? extends V> after) {
        return (ThrowingDoubleFunction1<V>) AbstractThrowingDoubleFunction1.super.andThen(after);
    }

    @Override
    default <V> ThrowingDoubleFunction1<V> andThenUnchecked(final Function1<? super R, ? extends V> after) {
        return (final double value) -> after.apply(this.apply(value));
    }
}
