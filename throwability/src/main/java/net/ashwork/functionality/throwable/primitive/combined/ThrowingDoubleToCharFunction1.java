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
import net.ashwork.functionality.primitive.combined.DoubleToCharFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingDoubleToCharFunction1;
import net.ashwork.functionality.throwable.primitive.chars.ThrowingToCharFunction1;
import net.ashwork.functionality.throwable.primitive.chars.ThrowingToCharFunctionN;
import net.ashwork.functionality.throwable.primitive.doubles.ThrowingDoubleFunction1;

/**
 * Represents a function that accepts a {@code double}-valued argument and produces a {@code char}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToCharFunctionN}.
 * This is the {@code double}-consuming primitive specialization of {@link ThrowingToCharFunction1}.
 * This is the {@code char}-producing primitive specialization of {@link ThrowingDoubleFunction1}.
 * This is the throwing variation of {@link DoubleToCharFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsChar(double)}.
 *
 * @see ThrowingDoubleFunction1
 * @see ThrowingToCharFunction1
 * @see ThrowingToCharFunctionN
 * @see DoubleToCharFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingDoubleToCharFunction1 extends AbstractThrowingDoubleToCharFunction1<AbstractThrowingDoubleToCharFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see DoubleToCharFunction1
     */
    static ThrowingDoubleToCharFunction1 from(final DoubleToCharFunction1 function) {
        return function::applyAsChar;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Double, Character> box() {
        return this::applyAsChar;
    }

    /**
     * @see ThrowingToCharFunction1
     */
    @Override
    default ThrowingToCharFunction1<Double> boxInput() {
        return this::applyAsChar;
    }

    /**
     * @see ThrowingDoubleFunction1
     */
    @Override
    default ThrowingDoubleFunction1<Character> boxResult() {
        return this::applyAsChar;
    }

    @Override
    default DoubleToCharFunction1 swallow() {
        return this.handle((t, value) -> '\u0000');
    }

    /**
     * @see ThrowingToCharFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToCharFunction1<V> compose(final Function1<? super V, ? extends Double> before) {
        return (ThrowingToCharFunction1<V>) AbstractThrowingDoubleToCharFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToCharFunction1
     */
    @Override
    default <V> ThrowingToCharFunction1<V> composeUnchecked(final Function1<? super V, ? extends Double> before) {
        return (final V v) -> this.applyAsChar(before.apply(v));
    }

    /**
     * @see ThrowingDoubleFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingDoubleFunction1<V> andThen(final Function1<? super Character, ? extends V> after) {
        return (ThrowingDoubleFunction1<V>) AbstractThrowingDoubleToCharFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingDoubleFunction1
     */
    @Override
    default <V> ThrowingDoubleFunction1<V> andThenUnchecked(final Function1<? super Character, ? extends V> after) {
        return (final double value) -> after.apply(this.applyAsChar(value));
    }
}
