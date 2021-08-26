/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.primitive.bytes;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.primitive.bytes.ToByteFunction0;
import net.ashwork.functionality.throwable.ThrowingFunction0;
import net.ashwork.functionality.throwable.abstracts.primitive.bytes.AbstractThrowingToByteFunction0;

/**
 * Represents a function that accepts no arguments and produces a {@code byte}-valued result or throws a throwable.
 * This is the zero-arity specialization of {@link ThrowingToByteFunctionN}.
 * This is the {@code byte}-producing primitive specialization of {@link ThrowingFunction0}.
 * This is the throwing variation of {@link ToByteFunction0}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsByte()}.
 *
 * @see ThrowingFunction0
 * @see ThrowingToByteFunctionN
 * @see ToByteFunction0
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingToByteFunction0 extends AbstractThrowingToByteFunction0<AbstractThrowingToByteFunction0.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ToByteFunction0
     */
    static ThrowingToByteFunction0 from(final ToByteFunction0 function) {
        return function::applyAsByte;
    }

    /**
     * @see ThrowingFunction0
     */
    @Override
    default ThrowingFunction0<Byte> boxResult() {
        return this::applyAsByte;
    }

    @Override
    default ToByteFunction0 swallow() {
        return this.handle(t -> (byte) 0);
    }

    /**
     * @see ThrowingFunction0
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction0<V> andThen(final Function1<? super Byte, ? extends V> after) {
        return (ThrowingFunction0<V>) AbstractThrowingToByteFunction0.super.andThen(after);
    }

    /**
     * @see ThrowingFunction0
     */
    @Override
    default <V> ThrowingFunction0<V> andThenUnchecked(final Function1<? super Byte, ? extends V> after) {
        return () -> after.apply(this.applyAsByte());
    }
}
