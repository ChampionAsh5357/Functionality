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
import net.ashwork.functionality.primitive.combined.CharToBooleanFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingCharToBooleanFunction1;
import net.ashwork.functionality.throwable.primitive.booleans.ThrowingToBooleanFunction1;
import net.ashwork.functionality.throwable.primitive.booleans.ThrowingToBooleanFunctionN;
import net.ashwork.functionality.throwable.primitive.chars.ThrowingCharFunction1;

/**
 * Represents a function that accepts a {@code char}-valued argument and produces a {@code boolean}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToBooleanFunctionN}.
 * This is the {@code char}-consuming primitive specialization of {@link ThrowingToBooleanFunction1}.
 * This is the {@code boolean}-producing primitive specialization of {@link ThrowingCharFunction1}.
 * This is the throwing variation of {@link CharToBooleanFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsBoolean(char)}.
 *
 * @see ThrowingCharFunction1
 * @see ThrowingToBooleanFunction1
 * @see ThrowingToBooleanFunctionN
 * @see CharToBooleanFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingCharToBooleanFunction1 extends AbstractThrowingCharToBooleanFunction1<ThrowingFunction1<Character, Boolean>, ThrowingToBooleanFunction1<Character>, ThrowingCharFunction1<Boolean>, AbstractThrowingCharToBooleanFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see CharToBooleanFunction1
     */
    static ThrowingCharToBooleanFunction1 from(final CharToBooleanFunction1 function) {
        return function::applyAsBoolean;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Character, Boolean> box() {
        return this::applyAsBoolean;
    }

    /**
     * @see ThrowingToBooleanFunction1
     */
    @Override
    default ThrowingToBooleanFunction1<Character> boxInput() {
        return this::applyAsBoolean;
    }

    /**
     * @see ThrowingCharFunction1
     */
    @Override
    default ThrowingCharFunction1<Boolean> boxResult() {
        return this::applyAsBoolean;
    }

    @Override
    default CharToBooleanFunction1 swallow() {
        return this.handle((t, value) -> false);
    }

    /**
     * @see ThrowingToBooleanFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToBooleanFunction1<V> compose(final Function1<? super V, ? extends Character> before) {
        return (ThrowingToBooleanFunction1<V>) AbstractThrowingCharToBooleanFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToBooleanFunction1
     */
    @Override
    default <V> ThrowingToBooleanFunction1<V> composeUnchecked(final Function1<? super V, ? extends Character> before) {
        return (final V v) -> this.applyAsBoolean(before.apply(v));
    }

    /**
     * @see ThrowingCharFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingCharFunction1<V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (ThrowingCharFunction1<V>) AbstractThrowingCharToBooleanFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingCharFunction1
     */
    @Override
    default <V> ThrowingCharFunction1<V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final char value) -> after.apply(this.applyAsBoolean(value));
    }
}
