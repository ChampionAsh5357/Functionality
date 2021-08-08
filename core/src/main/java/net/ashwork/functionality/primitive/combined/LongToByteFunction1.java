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
import net.ashwork.functionality.primitive.bytes.ToByteFunction1;
import net.ashwork.functionality.primitive.bytes.ToByteFunctionN;
import net.ashwork.functionality.primitive.longs.LongFunction1;

/**
 * Represents a function that accepts a {@code long}-valued argument and produces a {@code byte}-valued result.
 * This is the one-arity specialization of {@link ToByteFunctionN}.
 * This is the {@code long}-consuming primitive specialization of {@link ToByteFunction1}.
 * This is the {@code byte}-producing primitive specialization of {@link LongFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsByte(long)}.
 *
 * @see LongFunction1
 * @see ToByteFunction1
 * @see ToByteFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface LongToByteFunction1 extends ToByteFunctionN, InputChainableInput<Long>, UnboxedAll<Function1<Long, Byte>, ToByteFunction1<Long>, LongFunction1<Byte>> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    byte applyAsByte(final long value);

    @Override
    default byte applyAllAsByteUnchecked(final Object... args) {
        return this.applyAsByte((long) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<Long, Byte> box() {
        return this::applyAsByte;
    }

    /**
     * @see ToByteFunction1
     */
    @Override
    default ToByteFunction1<Long> boxInput() {
        return this::applyAsByte;
    }

    /**
     * @see LongFunction1
     */
    @Override
    default LongFunction1<Byte> boxResult() {
        return this::applyAsByte;
    }

    /**
     * @see ToByteFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToByteFunction1<V> compose(final Function1<? super V, ? extends Long> before) {
        return (ToByteFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToByteFunction1
     */
    @Override
    default <V> ToByteFunction1<V> composeUnchecked(final Function1<? super V, ? extends Long> before) {
        return (final V v) -> this.applyAsByte(before.apply(v));
    }

    /**
     * @see LongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> LongFunction1<V> andThen(final Function1<? super Byte, ? extends V> after) {
        return (LongFunction1<V>) ToByteFunctionN.super.andThen(after);
    }

    /**
     * @see LongFunction1
     */
    @Override
    default <V> LongFunction1<V> andThenUnchecked(final Function1<? super Byte, ? extends V> after) {
        return (final long value) -> after.apply(this.applyAsByte(value));
    }
}
