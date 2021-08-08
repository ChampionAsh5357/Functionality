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
import net.ashwork.functionality.primitive.floats.ToFloatFunction1;
import net.ashwork.functionality.primitive.floats.ToFloatFunctionN;

/**
 * Represents a function that accepts a {@code byte}-valued argument and produces a {@code float}-valued result.
 * This is the one-arity specialization of {@link ToFloatFunctionN}.
 * This is the {@code byte}-consuming primitive specialization of {@link ToFloatFunction1}.
 * This is the {@code float}-producing primitive specialization of {@link ByteFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsFloat(byte)}.
 *
 * @see ByteFunction1
 * @see ToFloatFunction1
 * @see ToFloatFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface ByteToFloatFunction1 extends ToFloatFunctionN, InputChainableInput<Byte>, UnboxedAll<Function1<Byte, Float>, ToFloatFunction1<Byte>, ByteFunction1<Float>> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    float applyAsFloat(final byte value);

    @Override
    default float applyAllAsFloatUnchecked(final Object... args) {
        return this.applyAsFloat((byte) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<Byte, Float> box() {
        return this::applyAsFloat;
    }

    /**
     * @see ToFloatFunction1
     */
    @Override
    default ToFloatFunction1<Byte> boxInput() {
        return this::applyAsFloat;
    }

    /**
     * @see ByteFunction1
     */
    @Override
    default ByteFunction1<Float> boxResult() {
        return this::applyAsFloat;
    }

    /**
     * @see ToFloatFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToFloatFunction1<V> compose(final Function1<? super V, ? extends Byte> before) {
        return (ToFloatFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToFloatFunction1
     */
    @Override
    default <V> ToFloatFunction1<V> composeUnchecked(final Function1<? super V, ? extends Byte> before) {
        return (final V v) -> this.applyAsFloat(before.apply(v));
    }

    /**
     * @see ByteFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ByteFunction1<V> andThen(final Function1<? super Float, ? extends V> after) {
        return (ByteFunction1<V>) ToFloatFunctionN.super.andThen(after);
    }

    /**
     * @see ByteFunction1
     */
    @Override
    default <V> ByteFunction1<V> andThenUnchecked(final Function1<? super Float, ? extends V> after) {
        return (final byte value) -> after.apply(this.applyAsFloat(value));
    }
}
