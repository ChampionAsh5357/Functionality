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
import net.ashwork.functionality.primitive.shorts.ToShortFunction1;
import net.ashwork.functionality.primitive.shorts.ToShortFunctionN;

/**
 * Represents a function that accepts a {@code byte}-valued argument and produces a {@code short}-valued result.
 * This is the one-arity specialization of {@link ToShortFunctionN}.
 * This is the {@code byte}-consuming primitive specialization of {@link ToShortFunction1}.
 * This is the {@code short}-producing primitive specialization of {@link ByteFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsShort(byte)}.
 *
 * @see ByteFunction1
 * @see ToShortFunction1
 * @see ToShortFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface ByteToShortFunction1 extends ToShortFunctionN, InputChainableInput<Byte>, UnboxedAll<Function1<Byte, Short>, ToShortFunction1<Byte>, ByteFunction1<Short>> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    short applyAsShort(final byte value);

    @Override
    default short applyAllAsShortUnchecked(final Object... args) {
        return this.applyAsShort((byte) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<Byte, Short> box() {
        return this::applyAsShort;
    }

    /**
     * @see ToShortFunction1
     */
    @Override
    default ToShortFunction1<Byte> boxInput() {
        return this::applyAsShort;
    }

    /**
     * @see ByteFunction1
     */
    @Override
    default ByteFunction1<Short> boxResult() {
        return this::applyAsShort;
    }

    /**
     * @see ToShortFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToShortFunction1<V> compose(final Function1<? super V, ? extends Byte> before) {
        return (ToShortFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToShortFunction1
     */
    @Override
    default <V> ToShortFunction1<V> composeUnchecked(final Function1<? super V, ? extends Byte> before) {
        return (final V v) -> this.applyAsShort(before.apply(v));
    }

    /**
     * @see ByteFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ByteFunction1<V> andThen(final Function1<? super Short, ? extends V> after) {
        return (ByteFunction1<V>) ToShortFunctionN.super.andThen(after);
    }

    /**
     * @see ByteFunction1
     */
    @Override
    default <V> ByteFunction1<V> andThenUnchecked(final Function1<? super Short, ? extends V> after) {
        return (final byte value) -> after.apply(this.applyAsShort(value));
    }
}
