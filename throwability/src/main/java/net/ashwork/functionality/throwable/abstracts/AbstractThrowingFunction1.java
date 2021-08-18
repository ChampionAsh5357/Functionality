/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts one argument and produces a result or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingFunctionN}.
 * This is the throwing variation of {@link Function1}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <T1> the type of the input to the function
 * @param <R> the type of the result of the function
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingFunctionN
 * @see Function1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingFunction1<T1, R, H extends AbstractThrowingFunction1.Handler<T1, R>> extends AbstractThrowingFunctionN<R, H>, InputChainableInput<T1> {

    /**
     * Applies this function to the given argument or throws a throwable.
     *
     * @param t1 the function argument
     * @return the function result
     */
    R apply(final T1 t1) throws Throwable;

    @SuppressWarnings("unchecked")
    @Override
    default R applyAllUnchecked(final Object... args) throws Throwable {
        return this.apply((T1) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<T1, R> handle(final H handler) {
        return (final T1 t1) -> {
            try {
                return this.apply(t1);
            } catch (final Throwable t) {
                return handler.onThrown(t, t1);
            }
        };
    }

    /**
     * @see Function1
     */
    @Override
    Function1<T1, R> swallow();

    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingFunction1<V, R, ?> compose(final Function1<? super V, ? extends T1> before) {
        return (AbstractThrowingFunction1<V, R, ?>) InputChainableInput.super.compose(before);
    }

    @Override
    <V> AbstractThrowingFunction1<V, R, ?> composeUnchecked(final Function1<? super V, ? extends T1> before);

    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingFunction1<T1, V, ?> andThen(final Function1<? super R, ? extends V> after) {
        return (AbstractThrowingFunction1<T1, V, ?>) AbstractThrowingFunctionN.super.andThen(after);
    }

    @Override
    <V> AbstractThrowingFunction1<T1, V, ?> andThenUnchecked(final Function1<? super R, ? extends V> after);

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     *
     * @param <T1> the type of the input to the function
     * @param <R> the type of the result of the function
     */
    @FunctionalInterface
    interface Handler<T1, R> extends AbstractThrowingFunctionN.Handler<R> {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param t1 the function argument
         * @return the handled result
         */
        R onThrown(final Throwable t, final T1 t1);

        @SuppressWarnings("unchecked")
        @Override
        default R onThrownUnchecked(final Throwable t, final Object... args) {
            return this.onThrown(t, (T1) args[0]);
        }
    }
}
