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
import net.ashwork.functionality.primitive.combined.CharToLongFunction1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.longs.AbstractThrowingToLongFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.longs.AbstractThrowingToLongFunctionN;
import net.ashwork.functionality.throwable.abstracts.primitive.chars.AbstractThrowingCharFunction1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts a {@code char}-valued argument and produces a {@code long}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingToLongFunctionN}.
 * This is the {@code char}-consuming primitive specialization of {@link AbstractThrowingToLongFunction1}.
 * This is the {@code long}-producing primitive specialization of {@link AbstractThrowingCharFunction1}.
 * This is the throwing variation of {@link CharToLongFunction1}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingCharFunction1
 * @see AbstractThrowingToLongFunction1
 * @see AbstractThrowingToLongFunctionN
 * @see CharToLongFunction1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingCharToLongFunction1<H extends AbstractThrowingCharToLongFunction1.Handler> extends AbstractThrowingToLongFunctionN<H>, InputChainableInput<Character>, UnboxedAll<AbstractThrowingFunction1<Character, Long, ?>, AbstractThrowingToLongFunction1<Character, ?>, AbstractThrowingCharFunction1<Long, ?>> {

    /**
     * Applies this function to the given argument or throws a throwable.
     *
     * @param value the function argument
     * @return the function result
     */
    long applyAsLong(final char value) throws Throwable;

    @Override
    default long applyAllAsLongUnchecked(final Object... args) throws Throwable {
        return this.applyAsLong((char) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see AbstractThrowingFunction1
     */
    @Override
    AbstractThrowingFunction1<Character, Long, ?> box();

    /**
     * @see AbstractThrowingToLongFunction1
     */
    @Override
    AbstractThrowingToLongFunction1<Character, ?> boxInput();

    /**
     * @see AbstractThrowingCharFunction1
     */
    @Override
    AbstractThrowingCharFunction1<Long, ?> boxResult();

    /**
     * @see CharToLongFunction1
     */
    @Override
    default CharToLongFunction1 handle(final H handler) {
        return (final char value) -> {
            try {
                return this.applyAsLong(value);
            } catch (final Throwable t) {
                return handler.onThrownAsLong(t, value);
            }
        };
    }

    /**
     * @see CharToLongFunction1
     */
    @Override
    CharToLongFunction1 swallow();

    /**
     * @see AbstractThrowingToLongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingToLongFunction1<V, ?> compose(final Function1<? super V, ? extends Character> before) {
        return (AbstractThrowingToLongFunction1<V, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractThrowingToLongFunction1
     */
    @Override
    <V> AbstractThrowingToLongFunction1<V, ?> composeUnchecked(final Function1<? super V, ? extends Character> before);

    /**
     * @see AbstractThrowingCharFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingCharFunction1<V, ?> andThen(final Function1<? super Long, ? extends V> after) {
        return (AbstractThrowingCharFunction1<V, ?>) AbstractThrowingToLongFunctionN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingCharFunction1
     */
    @Override
    <V> AbstractThrowingCharFunction1<V, ?> andThenUnchecked(final Function1<? super Long, ? extends V> after);

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     */
    @FunctionalInterface
    interface Handler extends AbstractThrowingToLongFunctionN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param value the function argument
         * @return the handled result
         */
        long onThrownAsLong(final Throwable t, final char value);

        @Override
        default long onThrownAsLongUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsLong(t, (char) args[0]);
        }
    }
}
