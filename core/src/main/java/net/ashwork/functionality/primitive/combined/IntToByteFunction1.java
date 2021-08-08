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
import net.ashwork.functionality.primitive.ints.IntFunction1;

/**
 * Represents a function that accepts an {@code int}-valued argument and produces a {@code byte}-valued result.
 * This is the one-arity specialization of {@link ToByteFunctionN}.
 * This is the {@code int}-consuming primitive specialization of {@link ToByteFunction1}.
 * This is the {@code byte}-producing primitive specialization of {@link IntFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsByte(int)}.
 *
 * @see IntFunction1
 * @see ToByteFunction1
 * @see ToByteFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface IntToByteFunction1 extends ToByteFunctionN, InputChainableInput<Integer>, UnboxedAll<Function1<Integer, Byte>, ToByteFunction1<Integer>, IntFunction1<Byte>> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    byte applyAsByte(final int value);

    @Override
    default byte applyAllAsByteUnchecked(final Object... args) {
        return this.applyAsByte((int) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<Integer, Byte> box() {
        return this::applyAsByte;
    }

    /**
     * @see ToByteFunction1
     */
    @Override
    default ToByteFunction1<Integer> boxInput() {
        return this::applyAsByte;
    }

    /**
     * @see IntFunction1
     */
    @Override
    default IntFunction1<Byte> boxResult() {
        return this::applyAsByte;
    }

    /**
     * @see ToByteFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToByteFunction1<V> compose(final Function1<? super V, ? extends Integer> before) {
        return (ToByteFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToByteFunction1
     */
    @Override
    default <V> ToByteFunction1<V> composeUnchecked(final Function1<? super V, ? extends Integer> before) {
        return (final V v) -> this.applyAsByte(before.apply(v));
    }

    /**
     * @see IntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> IntFunction1<V> andThen(final Function1<? super Byte, ? extends V> after) {
        return (IntFunction1<V>) ToByteFunctionN.super.andThen(after);
    }

    /**
     * @see IntFunction1
     */
    @Override
    default <V> IntFunction1<V> andThenUnchecked(final Function1<? super Byte, ? extends V> after) {
        return (final int value) -> after.apply(this.applyAsByte(value));
    }
}
