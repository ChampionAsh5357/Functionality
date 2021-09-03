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
import net.ashwork.functionality.Function2;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts two arguments and produces a result or throws a throwable.
 * This is the two-arity specialization of {@link AbstractThrowingFunctionN}.
 * This is the throwing variation of {@link Function2}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <T1> the type of the first argument to the function
 * @param <T2> the type of the second argument to the function
 * @param <R> the type of the result of the function
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingFunctionN
 * @see Function2
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingFunction2<T1, T2, R, H extends AbstractThrowingFunction2.Handler<T1, T2, R>> extends AbstractThrowingFunctionN<R, H> {

    /**
     * Applies this function to the given arguments or throws a throwable.
     *
     * @param t1 the first function argument
     * @param t2 the second function argument
     * @return the function result
     * @throws Throwable if the function cannot be computed
     */
    R apply(final T1 t1, final T2 t2) throws Throwable;

    @SuppressWarnings("unchecked")
    @Override
    default R applyAllUnchecked(final Object... args) throws Throwable {
        return this.apply((T1) args[0], (T2) args[1]);
    }

    @Override
    default int arity() {
        return 2;
    }

    /**
     * @see Function2
     */
    @Override
    default Function2<T1, T2, R> handle(final H handler) {
        return (final T1 t1, final T2 t2) -> {
            try {
                return this.apply(t1, t2);
            } catch (final Throwable t) {
                return handler.onThrown(t, t1, t2);
            }
        };
    }

    /**
     * @see Function2
     */
    @Override
    Function2<T1, T2, R> swallow();

    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingFunction2<T1, T2, V, ?> andThen(final Function1<? super R, ? extends V> after) {
        return (AbstractThrowingFunction2<T1, T2, V, ?>) AbstractThrowingFunctionN.super.andThen(after);
    }

    @Override
    <V> AbstractThrowingFunction2<T1, T2, V, ?> andThenUnchecked(final Function1<? super R, ? extends V> after);

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     *
     * @param <T1> the type of the first argument to the function
     * @param <T2> the type of the second argument to the function
     * @param <R> the type of the result of the function
     */
    @FunctionalInterface
    interface Handler<T1, T2, R> extends AbstractThrowingFunctionN.Handler<R> {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param t1 the first function argument
         * @param t2 the second function argument
         * @return the handled result
         */
        R onThrown(final Throwable t, final T1 t1, final T2 t2);

        @SuppressWarnings("unchecked")
        @Override
        default R onThrownUnchecked(final Throwable t, final Object... args) {
            return this.onThrown(t, (T1) args[0], (T2) args[1]);
        }
    }
}
