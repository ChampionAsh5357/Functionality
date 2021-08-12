/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.primitive.floats;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.primitive.floats.FloatFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.floats.AbstractThrowingFloatFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.floats.AbstractThrowingToFloatFunctionN;

/**
 * Represents a function that accepts a {@code float}-valued argument and produces a result or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingToFloatFunctionN}.
 * This is the {@code float}-consuming primitive specialization of {@link AbstractThrowingFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #apply(float)}.
 *
 * @param <R> the type of the result of the function
 *
 * @see AbstractThrowingFunction1
 * @see AbstractThrowingToFloatFunctionN
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingFloatFunction1<R> extends AbstractThrowingFloatFunction1<R, AbstractThrowingFloatFunction1.Handler<R>> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @param <R> the type of the result of the function
     * @return a throwing instance of the original type
     *
     * @see FloatFunction1
     */
    static <R> ThrowingFloatFunction1<R> from(final FloatFunction1<R> function) {
        return function::apply;
    }

    @Override
    default FloatFunction1<R> swallow() {
        return this.handle((t, value) -> null);
    }

    /**
     * @see ThrowingFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction1<V, R> compose(final Function1<? super V, ? extends Float> before) {
        return (ThrowingFunction1<V, R>) AbstractThrowingFloatFunction1.super.compose(before);
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default <V> ThrowingFunction1<V, R> composeUnchecked(final Function1<? super V, ? extends Float> before) {
        return (final V v) -> this.apply(before.apply(v));
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFloatFunction1<V> andThen(final Function1<? super R, ? extends V> after) {
        return (ThrowingFloatFunction1<V>) AbstractThrowingFloatFunction1.super.andThen(after);
    }

    @Override
    default <V> ThrowingFloatFunction1<V> andThenUnchecked(final Function1<? super R, ? extends V> after) {
        return (final float value) -> after.apply(this.apply(value));
    }
}
