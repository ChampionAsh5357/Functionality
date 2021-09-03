/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.primitive.combined;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedAll;
import net.ashwork.functionality.primitive.combined.CharToShortFunction1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.shorts.AbstractThrowingToShortFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.shorts.AbstractThrowingToShortFunctionN;
import net.ashwork.functionality.throwable.abstracts.primitive.chars.AbstractThrowingCharFunction1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts a {@code char}-valued argument and produces a {@code short}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingToShortFunctionN}.
 * This is the {@code char}-consuming primitive specialization of {@link AbstractThrowingToShortFunction1}.
 * This is the {@code short}-producing primitive specialization of {@link AbstractThrowingCharFunction1}.
 * This is the throwing variation of {@link CharToShortFunction1}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingCharFunction1
 * @see AbstractThrowingToShortFunction1
 * @see AbstractThrowingToShortFunctionN
 * @see CharToShortFunction1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingCharToShortFunction1<H extends AbstractThrowingCharToShortFunction1.Handler> extends AbstractThrowingToShortFunctionN<H>, InputChainableInput<Character>, UnboxedAll<AbstractThrowingFunction1<Character, Short, ?>, AbstractThrowingToShortFunction1<Character, ?>, AbstractThrowingCharFunction1<Short, ?>> {

    /**
     * Applies this function to the given argument or throws a throwable.
     *
     * @param value the function argument
     * @return the function result
     * @throws Throwable if the function cannot be computed
     */
    short applyAsShort(final char value) throws Throwable;

    @Override
    default short applyAllAsShortUnchecked(final Object... args) throws Throwable {
        return this.applyAsShort((char) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see AbstractThrowingFunction1
     */
    @Override
    AbstractThrowingFunction1<Character, Short, ?> box();

    /**
     * @see AbstractThrowingToShortFunction1
     */
    @Override
    AbstractThrowingToShortFunction1<Character, ?> boxInput();

    /**
     * @see AbstractThrowingCharFunction1
     */
    @Override
    AbstractThrowingCharFunction1<Short, ?> boxResult();

    /**
     * @see CharToShortFunction1
     */
    @Override
    default CharToShortFunction1 handle(final H handler) {
        return (final char value) -> {
            try {
                return this.applyAsShort(value);
            } catch (final Throwable t) {
                return handler.onThrownAsShort(t, value);
            }
        };
    }

    /**
     * @see CharToShortFunction1
     */
    @Override
    CharToShortFunction1 swallow();

    /**
     * @see AbstractThrowingToShortFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingToShortFunction1<V, ?> compose(final Function1<? super V, ? extends Character> before) {
        return (AbstractThrowingToShortFunction1<V, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractThrowingToShortFunction1
     */
    @Override
    <V> AbstractThrowingToShortFunction1<V, ?> composeUnchecked(final Function1<? super V, ? extends Character> before);

    /**
     * @see AbstractThrowingCharFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingCharFunction1<V, ?> andThen(final Function1<? super Short, ? extends V> after) {
        return (AbstractThrowingCharFunction1<V, ?>) AbstractThrowingToShortFunctionN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingCharFunction1
     */
    @Override
    <V> AbstractThrowingCharFunction1<V, ?> andThenUnchecked(final Function1<? super Short, ? extends V> after);

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     */
    @FunctionalInterface
    interface Handler extends AbstractThrowingToShortFunctionN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param value the function argument
         * @return the handled result
         */
        short onThrownAsShort(final Throwable t, final char value);

        @Override
        default short onThrownAsShortUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsShort(t, (char) args[0]);
        }
    }
}
