/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality;

import net.ashwork.functionality.partial.Variant;

import java.util.function.BiFunction;

/**
 * Represents a function that accepts two arguments and produces a result.
 * This is the two-arity specialization of {@link FunctionN}.
 *
 * <p>This is a functional interface whose functional method is {@link #apply(Object, Object)}.
 *
 * @param <T1> the type of the first argument to the function
 * @param <T2> the type of the second argument to the function
 * @param <R> the type of the result of the function
 *
 * @see FunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface Function2<T1, T2, R> extends FunctionN<R>, Variant<BiFunction<T1, T2, R>> {

    /**
     * Applies this function to the given arguments.
     *
     * @param t1 the first function argument
     * @param t2 the second function argument
     * @return the function result
     */
    R apply(final T1 t1, final T2 t2);

    @SuppressWarnings("unchecked")
    @Override
    default R applyAllUnchecked(final Object... args) {
        return this.apply((T1) args[0], (T2) args[1]);
    }

    @Override
    default int arity() {
        return 2;
    }

    /**
     * Creates an instance of this object from its {@link BiFunction} variant.
     *
     * @param function the variant of this object
     * @param <T1> the type of the first argument to the function
     * @param <T2> the type of the second argument to the function
     * @param <R> the type of the result of the function
     * @return an instance of this object
     *
     * @see BiFunction
     */
    static <T1, T2, R> Function2<T1, T2, R> fromVariant(final BiFunction<? super T1, ? super T2, ? extends R> function) {
        return function::apply;
    }

    /**
     * @see BiFunction
     */
    @Override
    default BiFunction<T1, T2, R> toVariant() {
        return this::apply;
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> Function2<T1, T2, V> andThen(final Function1<? super R, ? extends V> after) {
        return (Function2<T1, T2, V>) FunctionN.super.andThen(after);
    }

    @Override
    default <V> Function2<T1, T2, V> andThenUnchecked(final Function1<? super R, ? extends V> after) {
        return (final T1 t1, final T2 t2) -> after.apply(this.apply(t1, t2));
    }
}
