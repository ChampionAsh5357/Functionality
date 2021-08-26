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
import net.ashwork.functionality.primitive.bytes.ToByteFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.bytes.AbstractThrowingToByteFunction1;

/**
 * Represents a function that accepts one argument and produces a {@code byte}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToByteFunctionN}.
 * This is the {@code byte}-producing primitive specialization of {@link ThrowingFunction1}.
 * This is the throwing variation of {@link ToByteFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsByte(Object)}.
 *
 * @param <T1> the type of the input to the function
 *
 * @see ThrowingFunction1
 * @see ThrowingToByteFunctionN
 * @see ToByteFunction1
 * @since 1.0.0
 */
public interface ThrowingToByteFunction1<T1> extends AbstractThrowingToByteFunction1<T1, AbstractThrowingToByteFunction1.Handler<T1>> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @param <T1> the type of the input to the function
     * @return a throwing instance of the original type
     *
     * @see ToByteFunction1
     */
    static <T1> ThrowingToByteFunction1<T1> from(final ToByteFunction1<T1> function) {
        return function::applyAsByte;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<T1, Byte> boxResult() {
        return this::applyAsByte;
    }

    @Override
    default ToByteFunction1<T1> swallow() {
        return this.handle((t, t1) -> (byte) 0);
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToByteFunction1<V> compose(final Function1<? super V, ? extends T1> before) {
        return (ThrowingToByteFunction1<V>) AbstractThrowingToByteFunction1.super.compose(before);
    }

    @Override
    default <V> ThrowingToByteFunction1<V> composeUnchecked(final Function1<? super V, ? extends T1> before) {
        return (final V v) -> this.applyAsByte(before.apply(v));
    }

    /**
     * @see ThrowingFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction1<T1, V> andThen(final Function1<? super Byte, ? extends V> after) {
        return (ThrowingFunction1<T1, V>) AbstractThrowingToByteFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default <V> ThrowingFunction1<T1, V> andThenUnchecked(final Function1<? super Byte, ? extends V> after) {
        return (final T1 t1) -> after.apply(this.applyAsByte(t1));
    }
}
