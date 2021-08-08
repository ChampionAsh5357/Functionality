/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.primitive.combined;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedAll;
import net.ashwork.functionality.primitive.bytes.ByteFunction1;
import net.ashwork.functionality.primitive.longs.ToLongFunction1;
import net.ashwork.functionality.primitive.longs.ToLongFunctionN;

/**
 * Represents a function that accepts a {@code byte}-valued argument and produces a {@code long}-valued result.
 * This is the one-arity specialization of {@link ToLongFunctionN}.
 * This is the {@code byte}-consuming primitive specialization of {@link ToLongFunction1}.
 * This is the {@code long}-producing primitive specialization of {@link ByteFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsLong(byte)}.
 *
 * @see ByteFunction1
 * @see ToLongFunction1
 * @see ToLongFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface ByteToLongFunction1 extends ToLongFunctionN, InputChainableInput<Byte>, UnboxedAll<Function1<Byte, Long>, ToLongFunction1<Byte>, ByteFunction1<Long>> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    long applyAsLong(final byte value);

    @Override
    default long applyAllAsLongUnchecked(final Object... args) {
        return this.applyAsLong((byte) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<Byte, Long> box() {
        return this::applyAsLong;
    }

    /**
     * @see ToLongFunction1
     */
    @Override
    default ToLongFunction1<Byte> boxInput() {
        return this::applyAsLong;
    }

    /**
     * @see ByteFunction1
     */
    @Override
    default ByteFunction1<Long> boxResult() {
        return this::applyAsLong;
    }

    /**
     * @see ToLongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToLongFunction1<V> compose(final Function1<? super V, ? extends Byte> before) {
        return (ToLongFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToLongFunction1
     */
    @Override
    default <V> ToLongFunction1<V> composeUnchecked(final Function1<? super V, ? extends Byte> before) {
        return (final V v) -> this.applyAsLong(before.apply(v));
    }

    /**
     * @see ByteFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ByteFunction1<V> andThen(final Function1<? super Long, ? extends V> after) {
        return (ByteFunction1<V>) ToLongFunctionN.super.andThen(after);
    }

    /**
     * @see ByteFunction1
     */
    @Override
    default <V> ByteFunction1<V> andThenUnchecked(final Function1<? super Long, ? extends V> after) {
        return (final byte value) -> after.apply(this.applyAsLong(value));
    }
}
