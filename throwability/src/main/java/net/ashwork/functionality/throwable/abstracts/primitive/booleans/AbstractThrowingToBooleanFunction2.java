/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.primitive.booleans;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.UnboxedResult;
import net.ashwork.functionality.primitive.booleans.ToBooleanFunction2;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction2;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts two arguments and produces a {@code boolean}-valued result or throws a throwable.
 * This is the two-arity specialization of {@link AbstractThrowingToBooleanFunctionN}.
 * This is the {@code boolean}-producing primitive specialization of {@link AbstractThrowingFunction2}.
 * This is the throwing variation of {@link ToBooleanFunction2}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <T1> the type of the first argument to the function
 * @param <T2> the type of the second argument to the function
 * @param <U> the type of the function which unboxes the {@code boolean} result
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingFunction2
 * @see AbstractThrowingToBooleanFunctionN
 * @see ToBooleanFunction2
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingToBooleanFunction2<T1, T2, U extends AbstractThrowingFunction2<T1, T2, Boolean, ?>, H extends AbstractThrowingToBooleanFunction2.Handler<T1, T2>> extends AbstractThrowingToBooleanFunctionN<H>, UnboxedResult<U> {

    /**
     * Applies this function to the given argument or throws a throwable.
     *
     * @param t1 the first function argument
     * @param t2 the second function argument
     * @return the function result
     */
    boolean applyAsBoolean(final T1 t1, final T2 t2) throws Throwable;

    @SuppressWarnings("unchecked")
    @Override
    default boolean applyAllAsBooleanUnchecked(final Object... args) throws Throwable {
        return this.applyAsBoolean((T1) args[0], (T2) args[1]);
    }

    @Override
    default int arity() {
        return 2;
    }

    /**
     * @see ToBooleanFunction2
     */
    @Override
    default ToBooleanFunction2<T1, T2> handle(final H handler) {
        return (final T1 t1, final T2 t2) -> {
            try {
                return this.applyAsBoolean(t1, t2);
            } catch (final Throwable t) {
                return handler.onThrownAsBoolean(t, t1, t2);
            }
        };
    }

    /**
     * @see ToBooleanFunction2
     */
    @Override
    ToBooleanFunction2<T1, T2> swallow();

    /**
     * @see AbstractThrowingFunction2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingFunction2<T1, T2, V, ?> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (AbstractThrowingFunction2<T1, T2, V, ?>) AbstractThrowingToBooleanFunctionN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingFunction2
     */
    @Override
    <V> AbstractThrowingFunction2<T1, T2, V, ?> andThenUnchecked(final Function1<? super Boolean, ? extends V> after);

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     *
     * @param <T1> the type of the first argument to the function
     * @param <T2> the type of the second argument to the function
     */
    @FunctionalInterface
    interface Handler<T1, T2> extends AbstractThrowingToBooleanFunctionN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param t1 the first function argument
         * @param t2 the second function argument
         * @return the handled result
         */
        boolean onThrownAsBoolean(final Throwable t, final T1 t1, final T2 t2);

        @SuppressWarnings("unchecked")
        @Override
        default boolean onThrownAsBooleanUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsBoolean(t, (T1) args[0], (T2) args[1]);
        }
    }
}
