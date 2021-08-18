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
import net.ashwork.functionality.Function2;
import net.ashwork.functionality.FunctionN;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction2;

/**
 * Represents a function that accepts two arguments and produces a result or throws a throwable.
 * This is the two-arity specialization of {@link FunctionN}.
 * This is the throwing variation of {@link Function2}.
 *
 * <p>This is a functional interface whose functional method is {@link #apply(Object, Object)}.
 *
 * @param <T1> the type of the first argument to the function
 * @param <T2> the type of the second argument to the function
 * @param <R> the type of the result of the function
 *
 * @see FunctionN
 * @see Function2
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingFunction2<T1, T2, R> extends AbstractThrowingFunction2<T1, T2, R, AbstractThrowingFunction2.Handler<T1, T2, R>> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @param <T1> the type of the first argument to the function
     * @param <T2> the type of the second argument to the function
     * @param <R> the type of the result of the function
     * @return a throwing instance of the original type
     *
     * @see Function2
     */
    static <T1, T2, R> ThrowingFunction2<T1, T2, R> from(final Function2<T1, T2, R> function) {
        return function::apply;
    }

    @Override
    default Function2<T1, T2, R> swallow() {
        return this.handle((t, t1, t2) -> null);
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction2<T1, T2, V> andThen(final Function1<? super R, ? extends V> after) {
        return (ThrowingFunction2<T1, T2, V>) AbstractThrowingFunction2.super.andThen(after);
    }

    @Override
    default <V> ThrowingFunction2<T1, T2, V> andThenUnchecked(final Function1<? super R, ? extends V> after) {
        return (final T1 t1, final T2 t2) -> after.apply(this.apply(t1, t2));
    }
}
