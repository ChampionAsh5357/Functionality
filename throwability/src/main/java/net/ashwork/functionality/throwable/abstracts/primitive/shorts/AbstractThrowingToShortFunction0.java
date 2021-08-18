/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.primitive.shorts;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.UnboxedResult;
import net.ashwork.functionality.primitive.shorts.ToShortFunction0;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction0;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts no arguments and produces a {@code short}-valued result or throws a throwable.
 * This is the zero-arity specialization of {@link AbstractThrowingToShortFunctionN}.
 * This is the {@code short}-producing primitive specialization of {@link AbstractThrowingFunction0}.
 * This is the throwing variation of {@link ToShortFunction0}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <U> the type of the function which unboxes the {@code short} result
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingFunction0
 * @see AbstractThrowingToShortFunctionN
 * @see ToShortFunction0
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingToShortFunction0<U extends AbstractThrowingFunction0<Short, ?>, H extends AbstractThrowingToShortFunction0.Handler> extends AbstractThrowingToShortFunctionN<H>, UnboxedResult<U> {

    /**
     * Applies this function or throws a throwable.
     *
     * @return the function result
     */
    short applyAsShort() throws Throwable;

    @Override
    default short applyAllAsShortUnchecked(final Object... args) throws Throwable {
        return this.applyAsShort();
    }

    @Override
    default int arity() {
        return 0;
    }

    /**
     * @see ToShortFunction0
     */
    @Override
    default ToShortFunction0 handle(final H handler) {
        return () -> {
            try {
                return this.applyAsShort();
            } catch (final Throwable t) {
                return handler.onThrownAsShort(t);
            }
        };
    }

    /**
     * @see ToShortFunction0
     */
    @Override
    ToShortFunction0 swallow();

    /**
     * @see AbstractThrowingFunction0
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingFunction0<V, ?> andThen(final Function1<? super Short, ? extends V> after) {
        return (AbstractThrowingFunction0<V, ?>) AbstractThrowingToShortFunctionN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingFunction0
     */
    @Override
    <V> AbstractThrowingFunction0<V, ?> andThenUnchecked(final Function1<? super Short, ? extends V> after);

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
         * @return the handled result
         */
        short onThrownAsShort(final Throwable t);

        @Override
        default short onThrownAsShortUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsShort(t);
        }
    }
}
