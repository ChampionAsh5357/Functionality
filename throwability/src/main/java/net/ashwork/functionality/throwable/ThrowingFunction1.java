/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.FunctionN;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;

/**
 * Represents a function that accepts one argument and produces a result or throws a throwable.
 * This is the one-arity specialization of {@link FunctionN}.
 * This is the throwing variation of {@link Function1}.
 *
 * <p>This is a functional interface whose functional method is {@link #apply(Object)}.
 *
 * @param <T1> the type of the input to the function
 * @param <R> the type of the result of the function
 *
 * @see FunctionN
 * @see Function1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingFunction1<T1, R> extends AbstractThrowingFunction1<T1, R, AbstractThrowingFunction1.Handler<T1, R>> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @param <T1> the type of the input to the function
     * @param <R> the type of the result of the function
     * @return a throwing instance of the original type
     *
     * @see Function1
     */
    static <T1, R> ThrowingFunction1<T1, R> from(final Function1<T1, R> function) {
        return function::apply;
    }

    @Override
    default Function1<T1, R> swallow() {
        return this.handle((t, t1) -> null);
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction1<V, R> compose(final Function1<? super V, ? extends T1> before) {
        return (ThrowingFunction1<V, R>) AbstractThrowingFunction1.super.compose(before);
    }

    @Override
    default <V> ThrowingFunction1<V, R> composeUnchecked(final Function1<? super V, ? extends T1> before) {
        return (final V v) -> this.apply(before.apply(v));
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction1<T1, V> andThen(final Function1<? super R, ? extends V> after) {
        return (ThrowingFunction1<T1, V>) AbstractThrowingFunction1.super.andThen(after);
    }

    @Override
    default <V> ThrowingFunction1<T1, V> andThenUnchecked(final Function1<? super R, ? extends V> after) {
        return (final T1 t1) -> after.apply(this.apply(t1));
    }
}
