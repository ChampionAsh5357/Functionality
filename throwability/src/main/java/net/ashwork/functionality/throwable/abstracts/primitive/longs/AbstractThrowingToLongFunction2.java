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
import net.ashwork.functionality.partial.UnboxedResult;
import net.ashwork.functionality.primitive.longs.ToLongFunction2;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction2;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts two arguments and produces a {@code long}-valued result or throws a throwable.
 * This is the two-arity specialization of {@link AbstractThrowingToLongFunctionN}.
 * This is the {@code long}-producing primitive specialization of {@link AbstractThrowingFunction2}.
 * This is the throwing variation of {@link ToLongFunction2}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <T1> the type of the first argument to the function
 * @param <T2> the type of the second argument to the function
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingFunction2
 * @see AbstractThrowingToLongFunctionN
 * @see ToLongFunction2
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingToLongFunction2<T1, T2, H extends AbstractThrowingToLongFunction2.Handler<T1, T2>> extends AbstractThrowingToLongFunctionN<H>, UnboxedResult<AbstractThrowingFunction2<T1, T2, Long, ?>> {

    /**
     * Applies this function to the given argument or throws a throwable.
     *
     * @param t1 the first function argument
     * @param t2 the second function argument
     * @return the function result
     * @throws Throwable if the function cannot be computed
     */
    long applyAsLong(final T1 t1, final T2 t2) throws Throwable;

    @SuppressWarnings("unchecked")
    @Override
    default long applyAllAsLongUnchecked(final Object... args) throws Throwable {
        return this.applyAsLong((T1) args[0], (T2) args[1]);
    }

    @Override
    default int arity() {
        return 2;
    }

    /**
     * @see AbstractThrowingFunction2
     */
    @Override
    AbstractThrowingFunction2<T1, T2, Long, ?> boxResult();

    /**
     * @see ToLongFunction2
     */
    @Override
    default ToLongFunction2<T1, T2> handle(final H handler) {
        return (final T1 t1, final T2 t2) -> {
            try {
                return this.applyAsLong(t1, t2);
            } catch (final Throwable t) {
                return handler.onThrownAsLong(t, t1, t2);
            }
        };
    }

    /**
     * @see ToLongFunction2
     */
    @Override
    ToLongFunction2<T1, T2> swallow();

    /**
     * @see AbstractThrowingFunction2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingFunction2<T1, T2, V, ?> andThen(final Function1<? super Long, ? extends V> after) {
        return (AbstractThrowingFunction2<T1, T2, V, ?>) AbstractThrowingToLongFunctionN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingFunction2
     */
    @Override
    <V> AbstractThrowingFunction2<T1, T2, V, ?> andThenUnchecked(final Function1<? super Long, ? extends V> after);

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     *
     * @param <T1> the type of the first argument to the function
     * @param <T2> the type of the second argument to the function
     */
    @FunctionalInterface
    interface Handler<T1, T2> extends AbstractThrowingToLongFunctionN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param t1 the first function argument
         * @param t2 the second function argument
         * @return the handled result
         */
        long onThrownAsLong(final Throwable t, final T1 t1, final T2 t2);

        @SuppressWarnings("unchecked")
        @Override
        default long onThrownAsLongUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsLong(t, (T1) args[0], (T2) args[1]);
        }
    }
}
