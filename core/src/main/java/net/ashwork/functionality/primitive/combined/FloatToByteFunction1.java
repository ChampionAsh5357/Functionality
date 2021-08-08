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
import net.ashwork.functionality.primitive.floats.FloatFunction1;

/**
 * Represents a function that accepts a {@code float}-valued argument and produces a {@code byte}-valued result.
 * This is the one-arity specialization of {@link ToByteFunctionN}.
 * This is the {@code float}-consuming primitive specialization of {@link ToByteFunction1}.
 * This is the {@code byte}-producing primitive specialization of {@link FloatFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsByte(float)}.
 *
 * @see FloatFunction1
 * @see ToByteFunction1
 * @see ToByteFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface FloatToByteFunction1 extends ToByteFunctionN, InputChainableInput<Float>, UnboxedAll<Function1<Float, Byte>, ToByteFunction1<Float>, FloatFunction1<Byte>> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    byte applyAsByte(final float value);

    @Override
    default byte applyAllAsByteUnchecked(final Object... args) {
        return this.applyAsByte((float) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<Float, Byte> box() {
        return this::applyAsByte;
    }

    /**
     * @see ToByteFunction1
     */
    @Override
    default ToByteFunction1<Float> boxInput() {
        return this::applyAsByte;
    }

    /**
     * @see FloatFunction1
     */
    @Override
    default FloatFunction1<Byte> boxResult() {
        return this::applyAsByte;
    }

    /**
     * @see ToByteFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToByteFunction1<V> compose(final Function1<? super V, ? extends Float> before) {
        return (ToByteFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToByteFunction1
     */
    @Override
    default <V> ToByteFunction1<V> composeUnchecked(final Function1<? super V, ? extends Float> before) {
        return (final V v) -> this.applyAsByte(before.apply(v));
    }

    /**
     * @see FloatFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> FloatFunction1<V> andThen(final Function1<? super Byte, ? extends V> after) {
        return (FloatFunction1<V>) ToByteFunctionN.super.andThen(after);
    }

    /**
     * @see FloatFunction1
     */
    @Override
    default <V> FloatFunction1<V> andThenUnchecked(final Function1<? super Byte, ? extends V> after) {
        return (final float value) -> after.apply(this.applyAsByte(value));
    }
}
