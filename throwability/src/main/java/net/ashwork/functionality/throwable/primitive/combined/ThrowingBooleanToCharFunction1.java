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
import net.ashwork.functionality.primitive.combined.BooleanToCharFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingBooleanToCharFunction1;
import net.ashwork.functionality.throwable.primitive.booleans.ThrowingBooleanFunction1;
import net.ashwork.functionality.throwable.primitive.chars.ThrowingToCharFunction1;
import net.ashwork.functionality.throwable.primitive.chars.ThrowingToCharFunctionN;

/**
 * Represents a function that accepts a {@code boolean}-valued argument and produces a {@code char}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToCharFunctionN}.
 * This is the {@code boolean}-consuming primitive specialization of {@link ThrowingToCharFunction1}.
 * This is the {@code char}-producing primitive specialization of {@link ThrowingBooleanFunction1}.
 * This is the throwing variation of {@link BooleanToCharFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsChar(boolean)}.
 *
 * @see ThrowingBooleanFunction1
 * @see ThrowingToCharFunction1
 * @see ThrowingToCharFunctionN
 * @see BooleanToCharFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingBooleanToCharFunction1 extends AbstractThrowingBooleanToCharFunction1<AbstractThrowingBooleanToCharFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see BooleanToCharFunction1
     */
    static ThrowingBooleanToCharFunction1 from(final BooleanToCharFunction1 function) {
        return function::applyAsChar;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Boolean, Character> box() {
        return this::applyAsChar;
    }

    /**
     * @see ThrowingToCharFunction1
     */
    @Override
    default ThrowingToCharFunction1<Boolean> boxInput() {
        return this::applyAsChar;
    }

    /**
     * @see ThrowingBooleanFunction1
     */
    @Override
    default ThrowingBooleanFunction1<Character> boxResult() {
        return this::applyAsChar;
    }

    @Override
    default BooleanToCharFunction1 swallow() {
        return this.handle((t, value) -> '\u0000');
    }

    /**
     * @see ThrowingToCharFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToCharFunction1<V> compose(final Function1<? super V, ? extends Boolean> before) {
        return (ThrowingToCharFunction1<V>) AbstractThrowingBooleanToCharFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToCharFunction1
     */
    @Override
    default <V> ThrowingToCharFunction1<V> composeUnchecked(final Function1<? super V, ? extends Boolean> before) {
        return (final V v) -> this.applyAsChar(before.apply(v));
    }

    /**
     * @see ThrowingBooleanFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingBooleanFunction1<V> andThen(final Function1<? super Character, ? extends V> after) {
        return (ThrowingBooleanFunction1<V>) AbstractThrowingBooleanToCharFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingBooleanFunction1
     */
    @Override
    default <V> ThrowingBooleanFunction1<V> andThenUnchecked(final Function1<? super Character, ? extends V> after) {
        return (final boolean value) -> after.apply(this.applyAsChar(value));
    }
}
