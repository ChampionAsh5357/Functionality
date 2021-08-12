/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.primitive.ints;

import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.primitive.ints.IntFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunctionN;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts an {@code int}-valued argument and produces a result or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingToIntFunctionN}.
 * This is the {@code int}-consuming primitive specialization of {@link AbstractThrowingFunction1}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <R> the type of the result of the function
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingFunction1
 * @see AbstractThrowingToIntFunctionN
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingIntFunction1<R, H extends AbstractThrowingIntFunction1.Handler<R>> extends AbstractThrowingFunctionN<R, H>, InputChainableInput<Integer>, UnboxedInput<ThrowingFunction1<Integer, R>> {

    /**
     * Applies this function to the given argument or throws a throwable.
     *
     * @param value the function argument
     * @return the function result
     */
    R apply(final int value) throws Throwable;

    @Override
    default R applyAllUnchecked(final Object... args) throws Throwable {
        return this.apply((int) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Integer, R> boxInput() {
        return this::apply;
    }

    /**
     * @see IntFunction1
     */
    @Override
    default IntFunction1<R> handle(final H handler) {
        return (final int value) -> {
            try {
                return this.apply(value);
            } catch (final Throwable t) {
                return handler.onThrown(t, value);
            }
        };
    }

    /**
     * @see IntFunction1
     */
    @Override
    IntFunction1<R> swallow();

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
        R onThrown(final Throwable t, final int value);

        @Override
        default R onThrownUnchecked(final Throwable t, final Object... args) {
            return this.onThrown(t, (int) args[0]);
        }
    }
}
