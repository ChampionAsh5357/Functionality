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
import net.ashwork.functionality.partial.UnboxedResult;
import net.ashwork.functionality.primitive.chars.ToCharFunction0;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction0;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts no arguments and produces a {@code char}-valued result or throws a throwable.
 * This is the zero-arity specialization of {@link AbstractThrowingToCharFunctionN}.
 * This is the {@code char}-producing primitive specialization of {@link AbstractThrowingFunction0}.
 * This is the throwing variation of {@link ToCharFunction0}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <U> the type of the function which unboxes the {@code char} result
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingFunction0
 * @see AbstractThrowingToCharFunctionN
 * @see ToCharFunction0
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingToCharFunction0<U extends AbstractThrowingFunction0<Character, ?>, H extends AbstractThrowingToCharFunction0.Handler> extends AbstractThrowingToCharFunctionN<H>, UnboxedResult<U> {

    /**
     * Applies this function or throws a throwable.
     *
     * @return the function result
     */
    char applyAsChar() throws Throwable;

    @Override
    default char applyAllAsCharUnchecked(final Object... args) throws Throwable {
        return this.applyAsChar();
    }

    @Override
    default int arity() {
        return 0;
    }

    /**
     * @see ToCharFunction0
     */
    @Override
    default ToCharFunction0 handle(final H handler) {
        return () -> {
            try {
                return this.applyAsChar();
            } catch (final Throwable t) {
                return handler.onThrownAsChar(t);
            }
        };
    }

    /**
     * @see ToCharFunction0
     */
    @Override
    ToCharFunction0 swallow();

    /**
     * @see AbstractThrowingFunction0
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingFunction0<V, ?> andThen(final Function1<? super Character, ? extends V> after) {
        return (AbstractThrowingFunction0<V, ?>) AbstractThrowingToCharFunctionN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingFunction0
     */
    @Override
    <V> AbstractThrowingFunction0<V, ?> andThenUnchecked(final Function1<? super Character, ? extends V> after);

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
         * @return the handled result
         */
        char onThrownAsChar(final Throwable t);

        @Override
        default char onThrownAsCharUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsChar(t);
        }
    }
}
