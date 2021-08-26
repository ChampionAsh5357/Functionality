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
import net.ashwork.functionality.primitive.combined.CharToByteFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingCharToByteFunction1;
import net.ashwork.functionality.throwable.primitive.bytes.ThrowingToByteFunction1;
import net.ashwork.functionality.throwable.primitive.bytes.ThrowingToByteFunctionN;
import net.ashwork.functionality.throwable.primitive.chars.ThrowingCharFunction1;

/**
 * Represents a function that accepts a {@code char}-valued argument and produces a {@code byte}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToByteFunctionN}.
 * This is the {@code char}-consuming primitive specialization of {@link ThrowingToByteFunction1}.
 * This is the {@code byte}-producing primitive specialization of {@link ThrowingCharFunction1}.
 * This is the throwing variation of {@link CharToByteFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsByte(char)}.
 *
 * @see ThrowingCharFunction1
 * @see ThrowingToByteFunction1
 * @see ThrowingToByteFunctionN
 * @see CharToByteFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingCharToByteFunction1 extends AbstractThrowingCharToByteFunction1<AbstractThrowingCharToByteFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see CharToByteFunction1
     */
    static ThrowingCharToByteFunction1 from(final CharToByteFunction1 function) {
        return function::applyAsByte;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Character, Byte> box() {
        return this::applyAsByte;
    }

    /**
     * @see ThrowingToByteFunction1
     */
    @Override
    default ThrowingToByteFunction1<Character> boxInput() {
        return this::applyAsByte;
    }

    /**
     * @see ThrowingCharFunction1
     */
    @Override
    default ThrowingCharFunction1<Byte> boxResult() {
        return this::applyAsByte;
    }

    @Override
    default CharToByteFunction1 swallow() {
        return this.handle((t, value) -> (byte) 0);
    }

    /**
     * @see ThrowingToByteFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToByteFunction1<V> compose(final Function1<? super V, ? extends Character> before) {
        return (ThrowingToByteFunction1<V>) AbstractThrowingCharToByteFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToByteFunction1
     */
    @Override
    default <V> ThrowingToByteFunction1<V> composeUnchecked(final Function1<? super V, ? extends Character> before) {
        return (final V v) -> this.applyAsByte(before.apply(v));
    }

    /**
     * @see ThrowingCharFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingCharFunction1<V> andThen(final Function1<? super Byte, ? extends V> after) {
        return (ThrowingCharFunction1<V>) AbstractThrowingCharToByteFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingCharFunction1
     */
    @Override
    default <V> ThrowingCharFunction1<V> andThenUnchecked(final Function1<? super Byte, ? extends V> after) {
        return (final char value) -> after.apply(this.applyAsByte(value));
    }
}
