/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality;

import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.Variant;

import java.util.function.Function;

/**
 * Represents a function that accepts one argument and produces a result.
 * This is the one-arity specialization of {@link FunctionN}.
 *
 * <p>This is a functional interface whose functional method is {@link #apply(Object)}.
 *
 * @param <T1> the type of the input to the function
 * @param <R> the type of the result of the function
 *
 * @see FunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface Function1<T1, R> extends FunctionN<R>, Variant<Function<T1, R>>, InputChainableInput<T1> {

    /**
     * Applies this function to the given argument.
     *
     * @param t1 the function argument
     * @return the function result
     */
    R apply(final T1 t1);

    @SuppressWarnings("unchecked")
    @Override
    default R applyAllUnchecked(final Object... args) {
        return this.apply((T1) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * Creates an instance of this object from its {@link Function} variant.
     *
     * @param function the variant of this object
     * @param <T1> the type of the input to the function
     * @param <R> the type of the result of the function
     * @return an instance of this object
     *
     * @see Function
     */
    static <T1, R> Function1<T1, R> fromVariant(final Function<? super T1, ? extends R> function) {
        return function::apply;
    }

    /**
     * @see Function
     */
    @Override
    default Function<T1, R> toVariant() {
        return this::apply;
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> Function1<V, R> compose(final Function1<? super V, ? extends T1> before) {
        return (Function1<V, R>) InputChainableInput.super.compose(before);
    }

    @Override
    default <V> Function1<V, R> composeUnchecked(final Function1<? super V, ? extends T1> before) {
        return (final V v) -> this.apply(before.apply(v));
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> Function1<T1, V> andThen(final Function1<? super R, ? extends V> after) {
        return (Function1<T1, V>) FunctionN.super.andThen(after);
    }

    @Override
    default <V> Function1<T1, V> andThenUnchecked(final Function1<? super R, ? extends V> after) {
        return (final T1 t1) -> after.apply(this.apply(t1));
    }
}
