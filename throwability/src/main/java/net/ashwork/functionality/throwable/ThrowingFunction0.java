/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable;

import net.ashwork.functionality.Function0;
import net.ashwork.functionality.Function1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction0;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunctionN;

import java.util.concurrent.Callable;

/**
 * Represents a function that accepts no arguments and produces a result or throws a throwable.
 * This is the zero-arity specialization of {@link AbstractThrowingFunctionN}.
 * This is the throwing variation of {@link Function0}.
 *
 * <p>This is a functional interface whose functional method is {@link #apply()}.
 *
 * @param <R> the type of the result of the function
 *
 * @see AbstractThrowingFunctionN
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingFunction0<R> extends AbstractThrowingFunction0<R, AbstractThrowingFunction0.Handler<R>> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @param <R> the type of the result of the function
     * @return a throwing instance of the original type
     *
     * @see Function0
     */
    static <R> ThrowingFunction0<R> from(final Function0<R> function) {
        return function::apply;
    }

    /**
     * Creates an instance of this object from its {@link Callable} variant.
     *
     * @param callable the variant of this object
     * @param <R> the type of the result of the function
     * @return an instance of this object
     *
     * @see Callable
     */
    static <R> ThrowingFunction0<R> fromVariant(final Callable<R> callable) {
        return callable::call;
    }

    @Override
    default Function0<R> swallow() {
        return this.handle(t -> null);
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction0<V> andThen(final Function1<? super R, ? extends V> after) {
        return (ThrowingFunction0<V>) AbstractThrowingFunction0.super.andThen(after);
    }

    @Override
    default <V> ThrowingFunction0<V> andThenUnchecked(final Function1<? super R, ? extends V> after) {
        return () -> after.apply(this.apply());
    }
}
