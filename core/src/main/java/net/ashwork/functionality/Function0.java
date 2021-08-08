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

import java.util.function.Supplier;

/**
 * Represents a function that accepts no arguments and produces a result.
 * This is the zero-arity specialization of {@link FunctionN}.
 *
 * <p>This is a functional interface whose functional method is {@link #apply()}.
 *
 * @param <R> the type of the result of the function
 *
 * @see FunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface Function0<R> extends FunctionN<R>, Variant<Supplier<R>> {

    /**
     * Applies this function.
     *
     * @return the function result
     */
    R apply();

    @Override
    default R applyAllUnchecked(final Object... args) {
        return this.apply();
    }

    @Override
    default int arity() {
        return 0;
    }

    /**
     * Creates an instance of this object from its {@link Supplier} variant.
     *
     * @param supplier the variant of this object
     * @param <R> the type of the result of the function
     * @return an instance of this object
     *
     * @see Supplier
     */
    static <R> Function0<R> fromVariant(final Supplier<? extends R> supplier) {
        return supplier::get;
    }

    /**
     * @see Supplier
     */
    @Override
    default Supplier<R> toVariant() {
        return this::apply;
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> Function0<V> andThen(final Function1<? super R, ? extends V> after) {
        return (Function0<V>) FunctionN.super.andThen(after);
    }

    @Override
    default <V> Function0<V> andThenUnchecked(final Function1<? super R, ? extends V> after) {
        return () -> after.apply(this.apply());
    }
}
