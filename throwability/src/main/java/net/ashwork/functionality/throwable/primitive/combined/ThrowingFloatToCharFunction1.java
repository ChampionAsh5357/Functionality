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
import net.ashwork.functionality.primitive.combined.FloatToCharFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingFloatToCharFunction1;
import net.ashwork.functionality.throwable.primitive.chars.ThrowingToCharFunction1;
import net.ashwork.functionality.throwable.primitive.chars.ThrowingToCharFunctionN;
import net.ashwork.functionality.throwable.primitive.floats.ThrowingFloatFunction1;

/**
 * Represents a function that accepts a {@code float}-valued argument and produces a {@code char}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToCharFunctionN}.
 * This is the {@code float}-consuming primitive specialization of {@link ThrowingToCharFunction1}.
 * This is the {@code char}-producing primitive specialization of {@link ThrowingFloatFunction1}.
 * This is the throwing variation of {@link FloatToCharFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsChar(float)}.
 *
 * @see ThrowingFloatFunction1
 * @see ThrowingToCharFunction1
 * @see ThrowingToCharFunctionN
 * @see FloatToCharFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingFloatToCharFunction1 extends AbstractThrowingFloatToCharFunction1<AbstractThrowingFloatToCharFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see FloatToCharFunction1
     */
    static ThrowingFloatToCharFunction1 from(final FloatToCharFunction1 function) {
        return function::applyAsChar;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Float, Character> box() {
        return this::applyAsChar;
    }

    /**
     * @see ThrowingToCharFunction1
     */
    @Override
    default ThrowingToCharFunction1<Float> boxInput() {
        return this::applyAsChar;
    }

    /**
     * @see ThrowingFloatFunction1
     */
    @Override
    default ThrowingFloatFunction1<Character> boxResult() {
        return this::applyAsChar;
    }

    @Override
    default FloatToCharFunction1 swallow() {
        return this.handle((t, value) -> '\u0000');
    }

    /**
     * @see ThrowingToCharFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToCharFunction1<V> compose(final Function1<? super V, ? extends Float> before) {
        return (ThrowingToCharFunction1<V>) AbstractThrowingFloatToCharFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToCharFunction1
     */
    @Override
    default <V> ThrowingToCharFunction1<V> composeUnchecked(final Function1<? super V, ? extends Float> before) {
        return (final V v) -> this.applyAsChar(before.apply(v));
    }

    /**
     * @see ThrowingFloatFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFloatFunction1<V> andThen(final Function1<? super Character, ? extends V> after) {
        return (ThrowingFloatFunction1<V>) AbstractThrowingFloatToCharFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingFloatFunction1
     */
    @Override
    default <V> ThrowingFloatFunction1<V> andThenUnchecked(final Function1<? super Character, ? extends V> after) {
        return (final float value) -> after.apply(this.applyAsChar(value));
    }
}
