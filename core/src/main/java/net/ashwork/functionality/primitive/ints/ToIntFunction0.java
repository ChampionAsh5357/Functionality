/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.primitive.ints;

import net.ashwork.functionality.Function0;
import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.UnboxedResult;
import net.ashwork.functionality.partial.Variant;

import java.util.function.IntSupplier;

/**
 * Represents a function that accepts no arguments and produces an {@code int}-valued result.
 * This is the zero-arity specialization of {@link ToIntFunctionN}.
 * This is the {@code int}-producing primitive specialization of {@link Function0}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsInt()}.
 *
 * @see Function0
 * @see ToIntFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface ToIntFunction0 extends ToIntFunctionN, UnboxedResult<Function0<Integer>>, Variant<IntSupplier> {

    /**
     * Applies this function.
     *
     * @return the function result
     */
    int applyAsInt();

    @Override
    default int applyAllAsIntUnchecked(final Object... args) {
        return this.applyAsInt();
    }

    @Override
    default int arity() {
        return 0;
    }

    /**
     * Creates an instance of this object from its {@link IntSupplier} variant.
     *
     * @param supplier the variant of this object
     * @return an instance of this object
     *
     * @see IntSupplier
     */
    static ToIntFunction0 fromVariant(final IntSupplier supplier) {
        return supplier::getAsInt;
    }

    @Override
    default IntSupplier toVariant() {
        return this::applyAsInt;
    }

    /**
     * @see Function0
     */
    @Override
    default Function0<Integer> boxResult() {
        return this::applyAsInt;
    }

    /**
     * @see Function0
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Function0<V> andThen(final Function1<? super Integer, ? extends V> after) {
        return (Function0<V>) ToIntFunctionN.super.andThen(after);
    }

    /**
     * @see Function0
     */
    @Override
    default <V> Function0<V> andThenUnchecked(final Function1<? super Integer, ? extends V> after) {
        return () -> after.apply(this.applyAsInt());
    }
}
