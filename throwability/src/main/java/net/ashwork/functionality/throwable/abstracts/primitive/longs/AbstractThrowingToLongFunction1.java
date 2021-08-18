/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.primitive.longs;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedResult;
import net.ashwork.functionality.primitive.longs.ToLongFunction1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts one argument and produces a {@code long}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingToLongFunctionN}.
 * This is the {@code long}-producing primitive specialization of {@link AbstractThrowingFunction1}.
 * This is the throwing variation of {@link ToLongFunction1}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <T1> the type of the input to the function
 * @param <U> the type of the function which unboxes the {@code long} result
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingFunction1
 * @see AbstractThrowingToLongFunctionN
 * @see ToLongFunction1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingToLongFunction1<T1, U extends AbstractThrowingFunction1<T1, Long, ?>, H extends AbstractThrowingToLongFunction1.Handler<T1>> extends AbstractThrowingToLongFunctionN<H>, InputChainableInput<T1>, UnboxedResult<U> {

    /**
     * Applies this function to the given argument or throws a throwable.
     *
     * @param t1 the function argument
     * @return the function result
     */
    long applyAsLong(final T1 t1) throws Throwable;

    @SuppressWarnings("unchecked")
    @Override
    default long applyAllAsLongUnchecked(final Object... args) throws Throwable {
        return this.applyAsLong((T1) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see ToLongFunction1
     */
    @Override
    default ToLongFunction1<T1> handle(final H handler) {
        return (final T1 t1) -> {
            try {
                return this.applyAsLong(t1);
            } catch (final Throwable t) {
                return handler.onThrownAsLong(t, t1);
            }
        };
    }

    /**
     * @see ToLongFunction1
     */
    @Override
    ToLongFunction1<T1> swallow();

    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingToLongFunction1<V, ?, ?> compose(final Function1<? super V, ? extends T1> before) {
        return (AbstractThrowingToLongFunction1<V, ?, ?>) InputChainableInput.super.compose(before);
    }

    @Override
    <V> AbstractThrowingToLongFunction1<V, ?, ?> composeUnchecked(final Function1<? super V, ? extends T1> before);

    /**
     * @see AbstractThrowingFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingFunction1<T1, V, ?> andThen(final Function1<? super Long, ? extends V> after) {
        return (AbstractThrowingFunction1<T1, V, ?>) AbstractThrowingToLongFunctionN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingFunction1
     */
    @Override
    <V> AbstractThrowingFunction1<T1, V, ?> andThenUnchecked(final Function1<? super Long, ? extends V> after);

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     *
     * @param <T1> the type of the input to the function
     */
    @FunctionalInterface
    interface Handler<T1> extends AbstractThrowingToLongFunctionN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param t1 the function argument
         * @return the handled result
         */
        long onThrownAsLong(final Throwable t, final T1 t1);

        @SuppressWarnings("unchecked")
        @Override
        default long onThrownAsLongUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsLong(t, (T1) args[0]);
        }
    }
}
