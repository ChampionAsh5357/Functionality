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
import net.ashwork.functionality.primitive.chars.ToCharFunction1;
import net.ashwork.functionality.primitive.chars.ToCharFunctionN;

/**
 * Represents a function that accepts a {@code byte}-valued argument and produces a {@code char}-valued result.
 * This is the one-arity specialization of {@link ToCharFunctionN}.
 * This is the {@code byte}-consuming primitive specialization of {@link ToCharFunction1}.
 * This is the {@code char}-producing primitive specialization of {@link ByteFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsChar(byte)}.
 *
 * @see ByteFunction1
 * @see ToCharFunction1
 * @see ToCharFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface ByteToCharFunction1 extends ToCharFunctionN, InputChainableInput<Byte>, UnboxedAll<Function1<Byte, Character>, ToCharFunction1<Byte>, ByteFunction1<Character>> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    char applyAsChar(final byte value);

    @Override
    default char applyAllAsCharUnchecked(final Object... args) {
        return this.applyAsChar((byte) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<Byte, Character> box() {
        return this::applyAsChar;
    }

    /**
     * @see ToCharFunction1
     */
    @Override
    default ToCharFunction1<Byte> boxInput() {
        return this::applyAsChar;
    }

    /**
     * @see ByteFunction1
     */
    @Override
    default ByteFunction1<Character> boxResult() {
        return this::applyAsChar;
    }

    /**
     * @see ToCharFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToCharFunction1<V> compose(final Function1<? super V, ? extends Byte> before) {
        return (ToCharFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToCharFunction1
     */
    @Override
    default <V> ToCharFunction1<V> composeUnchecked(final Function1<? super V, ? extends Byte> before) {
        return (final V v) -> this.applyAsChar(before.apply(v));
    }

    /**
     * @see ByteFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ByteFunction1<V> andThen(final Function1<? super Character, ? extends V> after) {
        return (ByteFunction1<V>) ToCharFunctionN.super.andThen(after);
    }

    /**
     * @see ByteFunction1
     */
    @Override
    default <V> ByteFunction1<V> andThenUnchecked(final Function1<? super Character, ? extends V> after) {
        return (final byte value) -> after.apply(this.applyAsChar(value));
    }
}
