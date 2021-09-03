/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.primitive.ints;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.UnboxedResult;
import net.ashwork.functionality.primitive.ints.ToIntFunction0;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction0;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts no arguments and produces an {@code int}-valued result or throws a throwable.
 * This is the zero-arity specialization of {@link AbstractThrowingToIntFunctionN}.
 * This is the {@code int}-producing primitive specialization of {@link AbstractThrowingFunction0}.
 * This is the throwing variation of {@link ToIntFunction0}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingFunction0
 * @see AbstractThrowingToIntFunctionN
 * @see ToIntFunction0
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingToIntFunction0<H extends AbstractThrowingToIntFunction0.Handler> extends AbstractThrowingToIntFunctionN<H>, UnboxedResult<AbstractThrowingFunction0<Integer, ?>> {

    /**
     * Applies this function or throws a throwable.
     *
     * @return the function result
     * @throws Throwable if the function cannot be computed
     */
    int applyAsInt() throws Throwable;

    @Override
    default int applyAllAsIntUnchecked(final Object... args) throws Throwable {
        return this.applyAsInt();
    }

    @Override
    default int arity() {
        return 0;
    }

    /**
     * @see AbstractThrowingFunction0
     */
    @Override
    AbstractThrowingFunction0<Integer, ?> boxResult();

    /**
     * @see ToIntFunction0
     */
    @Override
    default ToIntFunction0 handle(final H handler) {
        return () -> {
            try {
                return this.applyAsInt();
            } catch (final Throwable t) {
                return handler.onThrownAsInt(t);
            }
        };
    }

    /**
     * @see ToIntFunction0
     */
    @Override
    ToIntFunction0 swallow();

    /**
     * @see AbstractThrowingFunction0
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingFunction0<V, ?> andThen(final Function1<? super Integer, ? extends V> after) {
        return (AbstractThrowingFunction0<V, ?>) AbstractThrowingToIntFunctionN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingFunction0
     */
    @Override
    <V> AbstractThrowingFunction0<V, ?> andThenUnchecked(final Function1<? super Integer, ? extends V> after);

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     */
    @FunctionalInterface
    interface Handler extends AbstractThrowingToIntFunctionN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @return the handled result
         */
        int onThrownAsInt(final Throwable t);

        @Override
        default int onThrownAsIntUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsInt(t);
        }
    }
}
