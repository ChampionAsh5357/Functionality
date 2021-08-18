/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.primitive.ints;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.primitive.ints.ToIntFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.ints.AbstractThrowingToIntFunction1;

/**
 * Represents a function that accepts one argument and produces an {@code int}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToIntFunctionN}.
 * This is the {@code int}-producing primitive specialization of {@link ThrowingFunction1}.
 * This is the throwing variation of {@link ToIntFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsInt(Object)}.
 *
 * @param <T1> the type of the input to the function
 *
 * @see ThrowingFunction1
 * @see ThrowingToIntFunctionN
 * @see ToIntFunction1
 * @since 1.0.0
 */
public interface ThrowingToIntFunction1<T1> extends AbstractThrowingToIntFunction1<T1, ThrowingFunction1<T1, Integer>, AbstractThrowingToIntFunction1.Handler<T1>> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @param <T1> the type of the input to the function
     * @return a throwing instance of the original type
     *
     * @see ToIntFunction1
     */
    static <T1> ThrowingToIntFunction1<T1> from(final ToIntFunction1<T1> function) {
        return function::applyAsInt;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<T1, Integer> boxResult() {
        return this::applyAsInt;
    }

    @Override
    default ToIntFunction1<T1> swallow() {
        return this.handle((t, t1) -> 0);
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToIntFunction1<V> compose(final Function1<? super V, ? extends T1> before) {
        return (ThrowingToIntFunction1<V>) AbstractThrowingToIntFunction1.super.compose(before);
    }

    @Override
    default <V> ThrowingToIntFunction1<V> composeUnchecked(final Function1<? super V, ? extends T1> before) {
        return (final V v) -> this.applyAsInt(before.apply(v));
    }

    /**
     * @see ThrowingFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction1<T1, V> andThen(final Function1<? super Integer, ? extends V> after) {
        return (ThrowingFunction1<T1, V>) AbstractThrowingToIntFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default <V> ThrowingFunction1<T1, V> andThenUnchecked(final Function1<? super Integer, ? extends V> after) {
        return (final T1 t1) -> after.apply(this.applyAsInt(t1));
    }
}
