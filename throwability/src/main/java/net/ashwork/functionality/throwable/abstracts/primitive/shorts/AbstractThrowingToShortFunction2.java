/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.primitive.shorts;

import net.ashwork.functionality.partial.UnboxedResult;
import net.ashwork.functionality.primitive.shorts.ToShortFunction2;
import net.ashwork.functionality.throwable.ThrowingFunction2;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction2;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts two arguments and produces a {@code short}-valued result or throws a throwable.
 * This is the two-arity specialization of {@link AbstractThrowingToShortFunctionN}.
 * This is the {@code short}-producing primitive specialization of {@link AbstractThrowingFunction2}.
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
 * @see AbstractThrowingToShortFunctionN
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingToShortFunction2<T1, T2, H extends AbstractThrowingToShortFunction2.Handler<T1, T2>> extends AbstractThrowingToShortFunctionN<H>, UnboxedResult<ThrowingFunction2<T1, T2, Short>> {

    /**
     * Applies this function to the given argument or throws a throwable.
     *
     * @param t1 the first function argument
     * @param t2 the second function argument
     * @return the function result
     */
    short applyAsShort(final T1 t1, final T2 t2) throws Throwable;

    @SuppressWarnings("unchecked")
    @Override
    default short applyAllAsShortUnchecked(final Object... args) throws Throwable {
        return this.applyAsShort((T1) args[0], (T2) args[1]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see ThrowingFunction2
     */
    @Override
    default ThrowingFunction2<T1, T2, Short> boxResult() {
        return this::applyAsShort;
    }

    /**
     * @see ToShortFunction2
     */
    @Override
    default ToShortFunction2<T1, T2> handle(final H handler) {
        return (final T1 t1, final T2 t2) -> {
            try {
                return this.applyAsShort(t1, t2);
            } catch (final Throwable t) {
                return handler.onThrownAsShort(t, t1, t2);
            }
        };
    }

    /**
     * @see ToShortFunction2
     */
    @Override
    ToShortFunction2<T1, T2> swallow();

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     *
     * @param <T1> the type of the first argument to the function
     * @param <T2> the type of the second argument to the function
     */
    @FunctionalInterface
    interface Handler<T1, T2> extends AbstractThrowingToShortFunctionN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param t1 the first function argument
         * @param t2 the second function argument
         * @return the handled result
         */
        short onThrownAsShort(final Throwable t, final T1 t1, final T2 t2);

        @SuppressWarnings("unchecked")
        @Override
        default short onThrownAsShortUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsShort(t, (T1) args[0], (T2) args[1]);
        }
    }
}
