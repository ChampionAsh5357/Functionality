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
import net.ashwork.functionality.primitive.booleans.ToBooleanFunction0;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction0;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts no arguments and produces a {@code boolean}-valued result or throws a throwable.
 * This is the zero-arity specialization of {@link AbstractThrowingToBooleanFunctionN}.
 * This is the {@code boolean}-producing primitive specialization of {@link AbstractThrowingFunction0}.
 * This is the throwing variation of {@link ToBooleanFunction0}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingFunction0
 * @see AbstractThrowingToBooleanFunctionN
 * @see ToBooleanFunction0
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingToBooleanFunction0<H extends AbstractThrowingToBooleanFunction0.Handler> extends AbstractThrowingToBooleanFunctionN<H>, UnboxedResult<AbstractThrowingFunction0<Boolean, ?>> {

    /**
     * Applies this function or throws a throwable.
     *
     * @return the function result
     * @throws Throwable if the function cannot be computed
     */
    boolean applyAsBoolean() throws Throwable;

    @Override
    default boolean applyAllAsBooleanUnchecked(final Object... args) throws Throwable {
        return this.applyAsBoolean();
    }

    @Override
    default int arity() {
        return 0;
    }

    /**
     * @see AbstractThrowingFunction0
     */
    @Override
    AbstractThrowingFunction0<Boolean, ?> boxResult();

    /**
     * @see ToBooleanFunction0
     */
    @Override
    default ToBooleanFunction0 handle(final H handler) {
        return () -> {
            try {
                return this.applyAsBoolean();
            } catch (final Throwable t) {
                return handler.onThrownAsBoolean(t);
            }
        };
    }

    /**
     * @see ToBooleanFunction0
     */
    @Override
    ToBooleanFunction0 swallow();

    /**
     * @see AbstractThrowingFunction0
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingFunction0<V, ?> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (AbstractThrowingFunction0<V, ?>) AbstractThrowingToBooleanFunctionN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingFunction0
     */
    @Override
    <V> AbstractThrowingFunction0<V, ?> andThenUnchecked(final Function1<? super Boolean, ? extends V> after);

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     */
    @FunctionalInterface
    interface Handler extends AbstractThrowingToBooleanFunctionN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @return the handled result
         */
        boolean onThrownAsBoolean(final Throwable t);

        @Override
        default boolean onThrownAsBooleanUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsBoolean(t);
        }
    }
}
