/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.primitive.chars;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.primitive.chars.CharFunction1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunctionN;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts a {@code char}-valued argument and produces a result or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingToCharFunctionN}.
 * This is the {@code char}-consuming primitive specialization of {@link AbstractThrowingFunction1}.
 * This is the throwing variation of {@link CharFunction1}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <R> the type of the result of the function
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingFunction1
 * @see AbstractThrowingToCharFunctionN
 * @see CharFunction1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingCharFunction1<R, H extends AbstractThrowingCharFunction1.Handler<R>> extends AbstractThrowingFunctionN<R, H>, InputChainableInput<Character>, UnboxedInput<AbstractThrowingFunction1<Character, R, ?>> {

    /**
     * Applies this function to the given argument or throws a throwable.
     *
     * @param value the function argument
     * @return the function result
     * @throws Throwable if the function cannot be computed
     */
    R apply(final char value) throws Throwable;

    @Override
    default R applyAllUnchecked(final Object... args) throws Throwable {
        return this.apply((char) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see AbstractThrowingFunction1
     */
    @Override
    AbstractThrowingFunction1<Character, R, ?> boxInput();

    /**
     * @see CharFunction1
     */
    @Override
    default CharFunction1<R> handle(final H handler) {
        return (final char value) -> {
            try {
                return this.apply(value);
            } catch (final Throwable t) {
                return handler.onThrown(t, value);
            }
        };
    }

    /**
     * @see CharFunction1
     */
    @Override
    CharFunction1<R> swallow();

    /**
     * @see AbstractThrowingFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingFunction1<V, R, ?> compose(final Function1<? super V, ? extends Character> before) {
        return (AbstractThrowingFunction1<V, R, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractThrowingFunction1
     */
    @Override
    <V> AbstractThrowingFunction1<V, R, ?> composeUnchecked(final Function1<? super V, ? extends Character> before);

    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingCharFunction1<V, ?> andThen(final Function1<? super R, ? extends V> after) {
        return (AbstractThrowingCharFunction1<V, ?>) AbstractThrowingFunctionN.super.andThen(after);
    }

    @Override
    <V> AbstractThrowingCharFunction1<V, ?> andThenUnchecked(final Function1<? super R, ? extends V> after);

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     *
     * @param <R> the type of the result of the function
     */
    @FunctionalInterface
    interface Handler<R> extends AbstractThrowingFunctionN.Handler<R> {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param value the function argument
         * @return the handled result
         */
        R onThrown(final Throwable t, final char value);

        @Override
        default R onThrownUnchecked(final Throwable t, final Object... args) {
            return this.onThrown(t, (char) args[0]);
        }
    }
}
