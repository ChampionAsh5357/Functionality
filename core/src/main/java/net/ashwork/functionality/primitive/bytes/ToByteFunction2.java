/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.primitive.bytes;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.Function2;
import net.ashwork.functionality.partial.UnboxedResult;

/**
 * Represents a function that accepts two arguments and produces a {@code byte}-valued result.
 * This is the two-arity specialization of {@link ToByteFunctionN}.
 * This is the {@code byte}-producing primitive specialization of {@link Function2}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsByte(Object, Object)}.
 *
 * @param <T1> the type of the input to the function
 * @param <T2> the type of the second argument to the function
 *
 * @see Function2
 * @see ToByteFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface ToByteFunction2<T1, T2> extends ToByteFunctionN, UnboxedResult<Function2<T1, T2, Byte>> {

    /**
     * Applies this function to the given arguments.
     *
     * @param t1 the first function argument
     * @param t2 the second function argument
     * @return the function result
     */
    byte applyAsByte(final T1 t1, final T2 t2);

    @SuppressWarnings("unchecked")
    @Override
    default byte applyAllAsByteUnchecked(final Object... args) {
        return this.applyAsByte((T1) args[0], (T2) args[1]);
    }

    @Override
    default int arity() {
        return 2;
    }

    /**
     * @see Function2
     */
    @Override
    default Function2<T1, T2, Byte> boxResult() {
        return this::applyAsByte;
    }

    /**
     * @see Function2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Function2<T1, T2, V> andThen(final Function1<? super Byte, ? extends V> after) {
        return (Function2<T1, T2, V>) ToByteFunctionN.super.andThen(after);
    }

    /**
     * @see Function2
     */
    @Override
    default <V> Function2<T1, T2, V> andThenUnchecked(final Function1<? super Byte, ? extends V> after) {
        return (final T1 t1, final T2 t2) -> after.apply(this.applyAsByte(t1, t2));
    }
}
