/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.operator.primitive.chars;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.operator.primitive.chars.CharOperator1;
import net.ashwork.functionality.throwable.abstracts.operator.primitive.chars.AbstractThrowingCharOperator1;
import net.ashwork.functionality.throwable.operator.ThrowingOperator1;
import net.ashwork.functionality.throwable.operator.ThrowingOperatorN;
import net.ashwork.functionality.throwable.primitive.chars.ThrowingCharFunction1;
import net.ashwork.functionality.throwable.primitive.chars.ThrowingToCharFunction1;

/**
 * Represents an operation that accepts a {@code char}-valued operand and produces a result of the same type as its operand or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingOperatorN}.
 * This is the {@code char}-consuming primitive specialization of {@link ThrowingToCharFunction1}.
 * This is the {@code char}-producing primitive specialization of {@link ThrowingCharFunction1}.
 * This is the throwing variation of {@link CharOperator1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsChar(char)}.
 *
 * @see ThrowingOperatorN
 * @see ThrowingToCharFunction1
 * @see ThrowingCharFunction1
 * @see CharOperator1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingCharOperator1 extends AbstractThrowingCharOperator1<AbstractThrowingCharOperator1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param operator the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see CharOperator1
     */
    static ThrowingCharOperator1 from(final CharOperator1 operator) {
        return operator::applyAsChar;
    }

    /**
     * @see ThrowingOperator1
     */
    @Override
    default ThrowingOperator1<Character> box() {
        return this::applyAsChar;
    }

    /**
     * @see ThrowingToCharFunction1
     */
    @Override
    default ThrowingToCharFunction1<Character> boxInput() {
        return this::applyAsChar;
    }

    /**
     * @see CharOperator1
     */
    @Override
    default ThrowingCharFunction1<Character> boxResult() {
        return this::applyAsChar;
    }

    /**
     * @see CharOperator1
     */
    @Override
    default CharOperator1 swallow() {
        return this.handle((t, value) -> '\u0000');
    }

    /**
     * @see ThrowingToCharFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToCharFunction1<V> compose(final Function1<? super V, ? extends Character> before) {
        return (ThrowingToCharFunction1<V>) AbstractThrowingCharOperator1.super.compose(before);
    }

    /**
     * @see ThrowingToCharFunction1
     */
    @Override
    default <V> ThrowingToCharFunction1<V> composeUnchecked(final Function1<? super V, ? extends Character> before) {
        return (final V v) -> this.applyAsChar(before.apply(v));
    }

    /**
     * @see ThrowingCharFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingCharFunction1<V> andThen(final Function1<? super Character, ? extends V> after) {
        return (ThrowingCharFunction1<V>) AbstractThrowingCharOperator1.super.andThen(after);
    }

    /**
     * @see ThrowingCharFunction1
     */
    @Override
    default <V> ThrowingCharFunction1<V> andThenUnchecked(final Function1<? super Character, ? extends V> after) {
        return (final char value) -> after.apply(this.applyAsChar(value));
    }
}
