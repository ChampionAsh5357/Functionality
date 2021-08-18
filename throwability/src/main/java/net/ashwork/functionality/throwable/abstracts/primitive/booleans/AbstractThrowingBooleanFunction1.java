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
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.primitive.booleans.BooleanFunction1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunctionN;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts a {@code boolean}-valued argument and produces a result or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingToBooleanFunctionN}.
 * This is the {@code boolean}-consuming primitive specialization of {@link AbstractThrowingFunction1}.
 * This is the throwing variation of {@link BooleanFunction1}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <R> the type of the result of the function
 * @param <I> the type of the function which unboxes the {@code boolean} input
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingFunction1
 * @see AbstractThrowingToBooleanFunctionN
 * @see BooleanFunction1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingBooleanFunction1<R, I extends AbstractThrowingFunction1<Boolean, R, ?>, H extends AbstractThrowingBooleanFunction1.Handler<R>> extends AbstractThrowingFunctionN<R, H>, InputChainableInput<Boolean>, UnboxedInput<I> {

    /**
     * Applies this function to the given argument or throws a throwable.
     *
     * @param value the function argument
     * @return the function result
     */
    R apply(final boolean value) throws Throwable;

    @Override
    default R applyAllUnchecked(final Object... args) throws Throwable {
        return this.apply((boolean) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see BooleanFunction1
     */
    @Override
    default BooleanFunction1<R> handle(final H handler) {
        return (final boolean value) -> {
            try {
                return this.apply(value);
            } catch (final Throwable t) {
                return handler.onThrown(t, value);
            }
        };
    }

    /**
     * @see BooleanFunction1
     */
    @Override
    BooleanFunction1<R> swallow();

    /**
     * @see AbstractThrowingFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingFunction1<V, R, ?> compose(final Function1<? super V, ? extends Boolean> before) {
        return (AbstractThrowingFunction1<V, R, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractThrowingFunction1
     */
    @Override
    <V> AbstractThrowingFunction1<V, R, ?> composeUnchecked(final Function1<? super V, ? extends Boolean> before);

    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingBooleanFunction1<V, ?, ?> andThen(final Function1<? super R, ? extends V> after) {
        return (AbstractThrowingBooleanFunction1<V, ?, ?>) AbstractThrowingFunctionN.super.andThen(after);
    }

    @Override
    <V> AbstractThrowingBooleanFunction1<V, ?, ?> andThenUnchecked(final Function1<? super R, ? extends V> after);

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
         * @param value the function argument
         * @return the handled result
         */
        R onThrown(final Throwable t, final boolean value);

        @Override
        default R onThrownUnchecked(final Throwable t, final Object... args) {
            return this.onThrown(t, (boolean) args[0]);
        }
    }
}
