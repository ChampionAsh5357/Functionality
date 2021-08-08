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
import net.ashwork.functionality.primitive.booleans.ToBooleanFunction1;
import net.ashwork.functionality.primitive.booleans.ToBooleanFunctionN;

/**
 * Represents a function that accepts a {@code byte}-valued argument and produces a {@code boolean}-valued result.
 * This is the one-arity specialization of {@link ToBooleanFunctionN}.
 * This is the {@code byte}-consuming primitive specialization of {@link ToBooleanFunction1}.
 * This is the {@code boolean}-producing primitive specialization of {@link ByteFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsBoolean(byte)}.
 *
 * @see ByteFunction1
 * @see ToBooleanFunction1
 * @see ToBooleanFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface ByteToBooleanFunction1 extends ToBooleanFunctionN, InputChainableInput<Byte>, UnboxedAll<Function1<Byte, Boolean>, ToBooleanFunction1<Byte>, ByteFunction1<Boolean>> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    boolean applyAsBoolean(final byte value);

    @Override
    default boolean applyAllAsBooleanUnchecked(final Object... args) {
        return this.applyAsBoolean((byte) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<Byte, Boolean> box() {
        return this::applyAsBoolean;
    }

    /**
     * @see ToBooleanFunction1
     */
    @Override
    default ToBooleanFunction1<Byte> boxInput() {
        return this::applyAsBoolean;
    }

    /**
     * @see ByteFunction1
     */
    @Override
    default ByteFunction1<Boolean> boxResult() {
        return this::applyAsBoolean;
    }

    /**
     * @see ToBooleanFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToBooleanFunction1<V> compose(final Function1<? super V, ? extends Byte> before) {
        return (ToBooleanFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToBooleanFunction1
     */
    @Override
    default <V> ToBooleanFunction1<V> composeUnchecked(final Function1<? super V, ? extends Byte> before) {
        return (final V v) -> this.applyAsBoolean(before.apply(v));
    }

    /**
     * @see ByteFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ByteFunction1<V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (ByteFunction1<V>) ToBooleanFunctionN.super.andThen(after);
    }

    /**
     * @see ByteFunction1
     */
    @Override
    default <V> ByteFunction1<V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final byte value) -> after.apply(this.applyAsBoolean(value));
    }
}
