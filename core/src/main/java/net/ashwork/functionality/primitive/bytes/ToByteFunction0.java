/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.primitive.bytes;

import net.ashwork.functionality.Function0;
import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.UnboxedResult;

/**
 * Represents a function that accepts no arguments and produces a {@code byte}-valued result.
 * This is the zero-arity specialization of {@link ToByteFunctionN}.
 * This is the {@code byte}-producing primitive specialization of {@link Function0}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsByte()}.
 *
 * @see Function0
 * @see ToByteFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface ToByteFunction0 extends ToByteFunctionN, UnboxedResult<Function0<Byte>> {

    /**
     * Applies this function.
     *
     * @return the function result
     */
    byte applyAsByte();

    @Override
    default byte applyAllAsByteUnchecked(final Object... args) {
        return this.applyAsByte();
    }

    @Override
    default int arity() {
        return 0;
    }

    /**
     * @see Function0
     */
    @Override
    default Function0<Byte> boxResult() {
        return this::applyAsByte;
    }

    /**
     * @see Function0
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Function0<V> andThen(final Function1<? super Byte, ? extends V> after) {
        return (Function0<V>) ToByteFunctionN.super.andThen(after);
    }

    /**
     * @see Function0
     */
    @Override
    default <V> Function0<V> andThenUnchecked(final Function1<? super Byte, ? extends V> after) {
        return () -> after.apply(this.applyAsByte());
    }
}
