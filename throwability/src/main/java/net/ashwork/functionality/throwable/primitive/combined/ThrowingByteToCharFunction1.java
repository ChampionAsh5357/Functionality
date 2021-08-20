/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.primitive.combined;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.primitive.combined.ByteToCharFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingByteToCharFunction1;
import net.ashwork.functionality.throwable.primitive.bytes.ThrowingByteFunction1;
import net.ashwork.functionality.throwable.primitive.chars.ThrowingToCharFunction1;
import net.ashwork.functionality.throwable.primitive.chars.ThrowingToCharFunctionN;

/**
 * Represents a function that accepts a {@code byte}-valued argument and produces a {@code char}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToCharFunctionN}.
 * This is the {@code byte}-consuming primitive specialization of {@link ThrowingToCharFunction1}.
 * This is the {@code char}-producing primitive specialization of {@link ThrowingByteFunction1}.
 * This is the throwing variation of {@link ByteToCharFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsChar(byte)}.
 *
 * @see ThrowingByteFunction1
 * @see ThrowingToCharFunction1
 * @see ThrowingToCharFunctionN
 * @see ByteToCharFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingByteToCharFunction1 extends AbstractThrowingByteToCharFunction1<ThrowingFunction1<Byte, Character>, ThrowingToCharFunction1<Byte>, ThrowingByteFunction1<Character>, AbstractThrowingByteToCharFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ByteToCharFunction1
     */
    static ThrowingByteToCharFunction1 from(final ByteToCharFunction1 function) {
        return function::applyAsChar;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Byte, Character> box() {
        return this::applyAsChar;
    }

    /**
     * @see ThrowingToCharFunction1
     */
    @Override
    default ThrowingToCharFunction1<Byte> boxInput() {
        return this::applyAsChar;
    }

    /**
     * @see ThrowingByteFunction1
     */
    @Override
    default ThrowingByteFunction1<Character> boxResult() {
        return this::applyAsChar;
    }

    @Override
    default ByteToCharFunction1 swallow() {
        return this.handle((t, value) -> '\u0000');
    }

    /**
     * @see ThrowingToCharFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToCharFunction1<V> compose(final Function1<? super V, ? extends Byte> before) {
        return (ThrowingToCharFunction1<V>) AbstractThrowingByteToCharFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToCharFunction1
     */
    @Override
    default <V> ThrowingToCharFunction1<V> composeUnchecked(final Function1<? super V, ? extends Byte> before) {
        return (final V v) -> this.applyAsChar(before.apply(v));
    }

    /**
     * @see ThrowingByteFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingByteFunction1<V> andThen(final Function1<? super Character, ? extends V> after) {
        return (ThrowingByteFunction1<V>) AbstractThrowingByteToCharFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingByteFunction1
     */
    @Override
    default <V> ThrowingByteFunction1<V> andThenUnchecked(final Function1<? super Character, ? extends V> after) {
        return (final byte value) -> after.apply(this.applyAsChar(value));
    }
}
