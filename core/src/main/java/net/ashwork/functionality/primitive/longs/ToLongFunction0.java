/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.primitive.longs;

import net.ashwork.functionality.Function0;
import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.UnboxedResult;
import net.ashwork.functionality.partial.Variant;

import java.util.function.LongSupplier;

/**
 * Represents a function that accepts no arguments and produces a {@code long}-valued result.
 * This is the zero-arity specialization of {@link ToLongFunctionN}.
 * This is the {@code long}-producing primitive specialization of {@link Function0}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsLong()}.
 *
 * @see Function0
 * @see ToLongFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface ToLongFunction0 extends ToLongFunctionN, UnboxedResult<Function0<Long>>, Variant<LongSupplier> {

    /**
     * Applies this function.
     *
     * @return the function result
     */
    long applyAsLong();

    @Override
    default long applyAllAsLongUnchecked(final Object... args) {
        return this.applyAsLong();
    }

    @Override
    default int arity() {
        return 0;
    }

    /**
     * Creates an instance of this object from its {@link LongSupplier} variant.
     *
     * @param supplier the variant of this object
     * @return an instance of this object
     *
     * @see LongSupplier
     */
    static ToLongFunction0 fromVariant(final LongSupplier supplier) {
        return supplier::getAsLong;
    }

    @Override
    default LongSupplier toVariant() {
        return this::applyAsLong;
    }

    /**
     * @see Function0
     */
    @Override
    default Function0<Long> boxResult() {
        return this::applyAsLong;
    }

    /**
     * @see Function0
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Function0<V> andThen(final Function1<? super Long, ? extends V> after) {
        return (Function0<V>) ToLongFunctionN.super.andThen(after);
    }

    /**
     * @see Function0
     */
    @Override
    default <V> Function0<V> andThenUnchecked(final Function1<? super Long, ? extends V> after) {
        return () -> after.apply(this.applyAsLong());
    }
}
