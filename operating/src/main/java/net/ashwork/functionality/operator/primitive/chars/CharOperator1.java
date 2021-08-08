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
import net.ashwork.functionality.operator.Operator1;
import net.ashwork.functionality.operator.OperatorN;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedAll;
import net.ashwork.functionality.primitive.chars.CharFunction1;
import net.ashwork.functionality.primitive.chars.ToCharFunction1;
import net.ashwork.functionality.primitive.chars.ToCharFunctionN;

/**
 * Represents an operation that accepts a {@code char}-valued operand and produces a result of the same type as its operand.
 * This is the one-arity specialization of {@link OperatorN}.
 * This is the {@code char}-consuming primitive specialization of {@link ToCharFunction1}.
 * This is the {@code char}-producing primitive specialization of {@link CharFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsChar(char)}.
 *
 * @see OperatorN
 * @see ToCharFunction1
 * @see CharFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface CharOperator1 extends OperatorN<Character>, ToCharFunctionN, InputChainableInput<Character>, UnboxedAll<Operator1<Character>, ToCharFunction1<Character>, CharFunction1<Character>> {

    /**
     * Applies this operator to the given operand.
     *
     * @param value the operand
     * @return the operator result
     */
    char applyAsChar(final char value);

    @Override
    default char applyAllAsCharUnchecked(final Object... args) {
        return this.applyAsChar((char) args[0]);
    }

    /**
     * @see Operator1
     */
    @Override
    default int arity() {
        return 1;
    }

    @Override
    default Operator1<Character> box() {
        return this::applyAsChar;
    }

    /**
     * @see ToCharFunction1
     */
    @Override
    default ToCharFunction1<Character> boxInput() {
        return this::applyAsChar;
    }

    /**
     * @see CharFunction1
     */
    @Override
    default CharFunction1<Character> boxResult() {
        return this::applyAsChar;
    }

    /**
     * @see ToCharFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ToCharFunction1<V> compose(final Function1<? super V, ? extends Character> before) {
        return (ToCharFunction1<V>) InputChainableInput.super.compose(before);
    }

    /**
     * @see ToCharFunction1
     */
    @Override
    default <V> ToCharFunction1<V> composeUnchecked(final Function1<? super V, ? extends Character> before) {
        return (final V v) -> this.applyAsChar(before.apply(v));
    }

    /**
     * @see CharFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> CharFunction1<V> andThen(final Function1<? super Character, ? extends V> after) {
        return (CharFunction1<V>) ToCharFunctionN.super.andThen(after);
    }

    /**
     * @see CharFunction1
     */
    @Override
    default <V> CharFunction1<V> andThenUnchecked(final Function1<? super Character, ? extends V> after) {
        return (final char value) -> after.apply(this.applyAsChar(value));
    }
}
