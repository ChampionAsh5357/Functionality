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
import net.ashwork.functionality.operator.primitive.chars.CharOperator2;
import net.ashwork.functionality.throwable.ThrowingFunction2;
import net.ashwork.functionality.throwable.abstracts.operator.primitive.chars.AbstractThrowingCharOperator2;
import net.ashwork.functionality.throwable.operator.ThrowingOperator2;
import net.ashwork.functionality.throwable.operator.ThrowingOperatorN;
import net.ashwork.functionality.throwable.primitive.chars.ThrowingToCharFunction2;

/**
 * Represents an operation that accepts two {@code char}-valued operands and produces a result of the same type as its operands or throws a throwable.
 * This is the two-arity specialization of {@link ThrowingOperatorN}.
 * This is the {@code char}-consuming primitive specialization of {@link ThrowingToCharFunction2}.
 * This is the throwing variation of {@link CharOperator2}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @see ThrowingOperatorN
 * @see ThrowingToCharFunction2
 * @see CharOperator2
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingCharOperator2 extends AbstractThrowingCharOperator2<AbstractThrowingCharOperator2.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param operator the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see CharOperator2
     */
    static ThrowingCharOperator2 from(final CharOperator2 operator) {
        return operator::applyAsChar;
    }

    /**
     * @see ThrowingOperator2
     */
    @Override
    default ThrowingOperator2<Character> box() {
        return this::applyAsChar;
    }

    /**
     * @see ThrowingToCharFunction2
     */
    @Override
    default ThrowingToCharFunction2<Character, Character> boxInput() {
        return this::applyAsChar;
    }

    /**
     * @see CharOperator2
     */
    @Override
    default CharOperator2 swallow() {
        return this.handle((t, value1, value2) -> '\u0000');
    }

    /**
     * @see ThrowingFunction2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction2<Character, Character, V> andThen(final Function1<? super Character, ? extends V> after) {
        return (ThrowingFunction2<Character, Character, V>) AbstractThrowingCharOperator2.super.andThen(after);
    }

    /**
     * @see ThrowingFunction2
     */
    @Override
    default <V> ThrowingFunction2<Character, Character, V> andThenUnchecked(final Function1<? super Character, ? extends V> after) {
        return (final Character value1, final Character value2) -> after.apply(this.applyAsChar(value1, value2));
    }
}
