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
import net.ashwork.functionality.primitive.combined.CharToDoubleFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingCharToDoubleFunction1;
import net.ashwork.functionality.throwable.primitive.chars.ThrowingCharFunction1;
import net.ashwork.functionality.throwable.primitive.doubles.ThrowingToDoubleFunction1;
import net.ashwork.functionality.throwable.primitive.doubles.ThrowingToDoubleFunctionN;

/**
 * Represents a function that accepts a {@code char}-valued argument and produces a {@code double}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToDoubleFunctionN}.
 * This is the {@code char}-consuming primitive specialization of {@link ThrowingToDoubleFunction1}.
 * This is the {@code double}-producing primitive specialization of {@link ThrowingCharFunction1}.
 * This is the throwing variation of {@link CharToDoubleFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsDouble(char)}.
 *
 * @see ThrowingCharFunction1
 * @see ThrowingToDoubleFunction1
 * @see ThrowingToDoubleFunctionN
 * @see CharToDoubleFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingCharToDoubleFunction1 extends AbstractThrowingCharToDoubleFunction1<AbstractThrowingCharToDoubleFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see CharToDoubleFunction1
     */
    static ThrowingCharToDoubleFunction1 from(final CharToDoubleFunction1 function) {
        return function::applyAsDouble;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Character, Double> box() {
        return this::applyAsDouble;
    }

    /**
     * @see ThrowingToDoubleFunction1
     */
    @Override
    default ThrowingToDoubleFunction1<Character> boxInput() {
        return this::applyAsDouble;
    }

    /**
     * @see ThrowingCharFunction1
     */
    @Override
    default ThrowingCharFunction1<Double> boxResult() {
        return this::applyAsDouble;
    }

    @Override
    default CharToDoubleFunction1 swallow() {
        return this.handle((t, value) -> 0.0d);
    }

    /**
     * @see ThrowingToDoubleFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToDoubleFunction1<V> compose(final Function1<? super V, ? extends Character> before) {
        return (ThrowingToDoubleFunction1<V>) AbstractThrowingCharToDoubleFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToDoubleFunction1
     */
    @Override
    default <V> ThrowingToDoubleFunction1<V> composeUnchecked(final Function1<? super V, ? extends Character> before) {
        return (final V v) -> this.applyAsDouble(before.apply(v));
    }

    /**
     * @see ThrowingCharFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingCharFunction1<V> andThen(final Function1<? super Double, ? extends V> after) {
        return (ThrowingCharFunction1<V>) AbstractThrowingCharToDoubleFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingCharFunction1
     */
    @Override
    default <V> ThrowingCharFunction1<V> andThenUnchecked(final Function1<? super Double, ? extends V> after) {
        return (final char value) -> after.apply(this.applyAsDouble(value));
    }
}
