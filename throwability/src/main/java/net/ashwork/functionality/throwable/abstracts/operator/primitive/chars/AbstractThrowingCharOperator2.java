/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.operator.primitive.chars;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.operator.primitive.chars.CharOperator2;
import net.ashwork.functionality.partial.Unboxed;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction2;
import net.ashwork.functionality.throwable.abstracts.operator.AbstractThrowingOperator2;
import net.ashwork.functionality.throwable.abstracts.operator.AbstractThrowingOperatorN;
import net.ashwork.functionality.throwable.abstracts.primitive.chars.AbstractThrowingToCharFunction2;
import net.ashwork.functionality.throwable.abstracts.primitive.chars.AbstractThrowingToCharFunctionN;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents an operation that accepts two {@code char}-valued operands and produces a result of the same type as its operands or throws a throwable.
 * This is the two-arity specialization of {@link AbstractThrowingOperatorN}.
 * This is the {@code char}-consuming primitive specialization of {@link AbstractThrowingToCharFunction2}.
 * This is the throwing variation of {@link CharOperator2}.
 *
 * @apiNote
 * This is an abstract operator and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the operator
 *
 * @see AbstractThrowingOperatorN
 * @see AbstractThrowingToCharFunction2
 * @see CharOperator2
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingCharOperator2<H extends AbstractThrowingCharOperator2.Handler> extends AbstractThrowingOperatorN<Character, H>, AbstractThrowingToCharFunctionN<H>, Unboxed<AbstractThrowingOperator2<Character, ?>>, UnboxedInput<AbstractThrowingToCharFunction2<Character, Character, ?>> {

    /**
     * Applies this operator to the given operands or throws a throwable.
     *
     * @param value1 the first operand
     * @param value2 the second operand
     * @return the operator result
     * @throws Throwable if the operation cannot be computed
     */
    char applyAsChar(final char value1, final char value2) throws Throwable;

    @Override
    default char applyAllAsCharUnchecked(final Object... args) throws Throwable {
        return this.applyAsChar((char) args[0], (char) args[1]);
    }

    @Override
    default int arity() {
        return 2;
    }

    /**
     * @see AbstractThrowingOperator2
     */
    @Override
    AbstractThrowingOperator2<Character, ?> box();

    /**
     * @see AbstractThrowingToCharFunction2
     */
    @Override
    AbstractThrowingToCharFunction2<Character, Character, ?> boxInput();

    /**
     * @see CharOperator2
     */
    @Override
    default CharOperator2 handle(final H handler) {
        return (final char value1, final char value2) -> {
            try {
                return this.applyAsChar(value1, value2);
            } catch (final Throwable t) {
                return handler.onThrown(t, value1, value2);
            }
        };
    }

    /**
     * @see CharOperator2
     */
    @Override
    CharOperator2 swallow();

    /**
     * @see AbstractThrowingFunction2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingFunction2<Character, Character, V, ?> andThen(final Function1<? super Character, ? extends V> after) {
        return (AbstractThrowingFunction2<Character, Character, V, ?>) AbstractThrowingOperatorN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingFunction2
     */
    @Override
    <V> AbstractThrowingFunction2<Character, Character, V, ?> andThenUnchecked(final Function1<? super Character, ? extends V> after);

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     */
    @FunctionalInterface
    interface Handler extends AbstractThrowingToCharFunctionN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param value1 the first operand
         * @param value2 the second operand
         * @return the handled result
         */
        char onThrown(final Throwable t, final char value1, final char value2);

        @Override
        default char onThrownAsCharUnchecked(final Throwable t, final Object... args) {
            return this.onThrown(t, (char) args[0], (char) args[1]);
        }
    }
}
