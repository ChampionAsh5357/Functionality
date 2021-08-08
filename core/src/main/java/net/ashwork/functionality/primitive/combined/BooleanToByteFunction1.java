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
import net.ashwork.functionality.primitive.booleans.BooleanFunction1;

/**
 * Represents a function that accepts a {@code boolean}-valued argument and produces a {@code byte}-valued result.
 * This is the one-arity specialization of {@link ToByteFunctionN}.
 * This is the {@code boolean}-consuming primitive specialization of {@link ToByteFunction1}.
 * This is the {@code byte}-producing primitive specialization of {@link BooleanFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsByte(boolean)}.
 *
 * @see BooleanFunction1
 * @see ToByteFunction1
 * @see ToByteFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface BooleanToByteFunction1 extends ToByteFunctionN, InputChainableInput<Boolean>, UnboxedAll<Function1<Boolean, Byte>, ToByteFunction1<Boolean>, BooleanFunction1<Byte>> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    byte applyAsByte(final boolean value);

    @Override
    default byte applyAllAsByteUnchecked(final Object... args) {
        return this.applyAsByte((boolean) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<Boolean, Byte> box() {
        return this::applyAsByte;
    }

    /**
     * @see ToByteFunction1
     */
    @Override
    default ToByteFunction1<Boolean> boxInput() {
        return this::applyAsByte;
    }

    /**
     * @see BooleanFunction1
     */
    @Override
    default BooleanFunction1<Byte> boxResult() {
        return this::applyAsByte;
    }

    /**
     * @see ToByteFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToByteFunction1<V> compose(final Function1<? super V, ? extends Boolean> before) {
        return (ToByteFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToByteFunction1
     */
    @Override
    default <V> ToByteFunction1<V> composeUnchecked(final Function1<? super V, ? extends Boolean> before) {
        return (final V v) -> this.applyAsByte(before.apply(v));
    }

    /**
     * @see BooleanFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> BooleanFunction1<V> andThen(final Function1<? super Byte, ? extends V> after) {
        return (BooleanFunction1<V>) ToByteFunctionN.super.andThen(after);
    }

    /**
     * @see BooleanFunction1
     */
    @Override
    default <V> BooleanFunction1<V> andThenUnchecked(final Function1<? super Byte, ? extends V> after) {
        return (final boolean value) -> after.apply(this.applyAsByte(value));
    }
}
