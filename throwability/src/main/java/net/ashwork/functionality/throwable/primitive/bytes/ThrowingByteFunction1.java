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
import net.ashwork.functionality.primitive.bytes.ByteFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.bytes.AbstractThrowingByteFunction1;

/**
 * Represents a function that accepts a {@code byte}-valued argument and produces a result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToByteFunctionN}.
 * This is the {@code byte}-consuming primitive specialization of {@link ThrowingFunction1}.
 * This is the throwing variation of {@link ByteFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #apply(byte)}.
 *
 * @param <R> the type of the result of the function
 *
 * @see ThrowingFunction1
 * @see ThrowingToByteFunctionN
 * @see ByteFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingByteFunction1<R> extends AbstractThrowingByteFunction1<R, ThrowingFunction1<Byte, R>, AbstractThrowingByteFunction1.Handler<R>> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @param <R> the type of the result of the function
     * @return a throwing instance of the original type
     *
     * @see ByteFunction1
     */
    static <R> ThrowingByteFunction1<R> from(final ByteFunction1<R> function) {
        return function::apply;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Byte, R> boxInput() {
        return this::apply;
    }

    @Override
    default ByteFunction1<R> swallow() {
        return this.handle((t, value) -> null);
    }

    /**
     * @see ThrowingFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction1<V, R> compose(final Function1<? super V, ? extends Byte> before) {
        return (ThrowingFunction1<V, R>) AbstractThrowingByteFunction1.super.compose(before);
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default <V> ThrowingFunction1<V, R> composeUnchecked(final Function1<? super V, ? extends Byte> before) {
        return (final V v) -> this.apply(before.apply(v));
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingByteFunction1<V> andThen(final Function1<? super R, ? extends V> after) {
        return (ThrowingByteFunction1<V>) AbstractThrowingByteFunction1.super.andThen(after);
    }

    @Override
    default <V> ThrowingByteFunction1<V> andThenUnchecked(final Function1<? super R, ? extends V> after) {
        return (final byte value) -> after.apply(this.apply(value));
    }
}
