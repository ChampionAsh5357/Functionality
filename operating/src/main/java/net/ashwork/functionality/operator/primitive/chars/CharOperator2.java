/*
 * Operating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.operator.primitive.chars;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.Function2;
import net.ashwork.functionality.operator.Operator2;
import net.ashwork.functionality.operator.OperatorN;
import net.ashwork.functionality.partial.Unboxed;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.primitive.chars.ToCharFunction2;
import net.ashwork.functionality.primitive.chars.ToCharFunctionN;

/**
 * Represents an operation that accepts two {@code char}-valued operands and produces a result of the same type as its operands.
 * This is the two-arity specialization of {@link OperatorN}.
 * This is the {@code char}-consuming primitive specialization of {@link ToCharFunction2}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsChar(char, char)}.
 *
 * @see OperatorN
 * @see ToCharFunction2
 * @since 1.0.0
 */
@FunctionalInterface
public interface CharOperator2 extends OperatorN<Character>, ToCharFunctionN, Unboxed<Operator2<Character>>, UnboxedInput<ToCharFunction2<Character, Character>> {

    /**
     * Applies this operator to the given operands.
     *
     * @param value1 the first operand
     * @param value2 the second operand
     * @return the operator result
     */
    char applyAsChar(final char value1, final char value2);

    @Override
    default char applyAllAsCharUnchecked(final Object... args) {
        return this.applyAsChar((char) args[0], (char) args[1]);
    }

    @Override
    default int arity() {
        return 2;
    }

    /**
     * @see Operator2
     */
    @Override
    default Operator2<Character> box() {
        return this::applyAsChar;
    }

    /**
     * @see ToCharFunction2
     */
    @Override
    default ToCharFunction2<Character, Character> boxInput() {
        return this::applyAsChar;
    }

    /**
     * @see Function2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Function2<Character, Character, V> andThen(final Function1<? super Character, ? extends V> after) {
        return (Function2<Character, Character, V>) ToCharFunctionN.super.andThen(after);
    }

    /**
     * @see Function2
     */
    @Override
    default <V> Function2<Character, Character, V> andThenUnchecked(final Function1<? super Character, ? extends V> after) {
        return (final Character value1, final Character value2) -> after.apply(this.applyAsChar(value1, value2));
    }
}
