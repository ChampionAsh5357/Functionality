/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts;

import net.ashwork.functionality.Function0;
import net.ashwork.functionality.Function1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts no arguments and produces a result or throws a throwable.
 * This is the zero-arity specialization of {@link AbstractThrowingFunctionN}.
 * This is the throwing variation of {@link Function0}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <R> the type of the result of the function
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingFunctionN
 * @see Function0
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingFunction0<R, H extends AbstractThrowingFunction0.Handler<R>> extends AbstractThrowingFunctionN<R, H> {

    /**
     * Applies this function or throws a throwable.
     *
     * @return the function result
     */
    R apply() throws Throwable;

    @Override
    default R applyAllUnchecked(final Object... args) throws Throwable {
        return this.apply();
    }

    @Override
    default int arity() {
        return 0;
    }

    /**
     * @see Function0
     */
    @Override
    default Function0<R> handle(final H handler) {
        return () -> {
            try {
                return this.apply();
            } catch (final Throwable t) {
                return handler.onThrown(t);
            }
        };
    }

    /**
     * @see Function0
     */
    @Override
    Function0<R> swallow();

    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingFunction0<V, ?> andThen(final Function1<? super R, ? extends V> after) {
        return (AbstractThrowingFunction0<V, ?>) AbstractThrowingFunctionN.super.andThen(after);
    }

    @Override
    <V> AbstractThrowingFunction0<V, ?> andThenUnchecked(final Function1<? super R, ? extends V> after);

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
         * @return the handled result
         */
        R onThrown(final Throwable t);

        @Override
        default R onThrownUnchecked(final Throwable t, final Object... args) {
            return this.onThrown(t);
        }
    }
}
