/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.primitive.shorts;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.primitive.shorts.ShortFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.shorts.AbstractThrowingShortFunction1;

/**
 * Represents a function that accepts a {@code short}-valued argument and produces a result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToShortFunctionN}.
 * This is the {@code short}-consuming primitive specialization of {@link ThrowingFunction1}.
 * This is the throwing variation of {@link ShortFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #apply(short)}.
 *
 * @param <R> the type of the result of the function
 *
 * @see ThrowingFunction1
 * @see ThrowingToShortFunctionN
 * @see ShortFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingShortFunction1<R> extends AbstractThrowingShortFunction1<R, ThrowingFunction1<Short, R>, AbstractThrowingShortFunction1.Handler<R>> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @param <R> the type of the result of the function
     * @return a throwing instance of the original type
     *
     * @see ShortFunction1
     */
    static <R> ThrowingShortFunction1<R> from(final ShortFunction1<R> function) {
        return function::apply;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Short, R> boxInput() {
        return this::apply;
    }

    @Override
    default ShortFunction1<R> swallow() {
        return this.handle((t, value) -> null);
    }

    /**
     * @see ThrowingFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction1<V, R> compose(final Function1<? super V, ? extends Short> before) {
        return (ThrowingFunction1<V, R>) AbstractThrowingShortFunction1.super.compose(before);
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default <V> ThrowingFunction1<V, R> composeUnchecked(final Function1<? super V, ? extends Short> before) {
        return (final V v) -> this.apply(before.apply(v));
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingShortFunction1<V> andThen(final Function1<? super R, ? extends V> after) {
        return (ThrowingShortFunction1<V>) AbstractThrowingShortFunction1.super.andThen(after);
    }

    @Override
    default <V> ThrowingShortFunction1<V> andThenUnchecked(final Function1<? super R, ? extends V> after) {
        return (final short value) -> after.apply(this.apply(value));
    }
}
