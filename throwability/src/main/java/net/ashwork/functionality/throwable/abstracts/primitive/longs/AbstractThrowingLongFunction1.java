/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.primitive.longs;

import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedInput;
import net.ashwork.functionality.primitive.longs.LongFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunctionN;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts a {@code long}-valued argument and produces a result or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingToLongFunctionN}.
 * This is the {@code long}-consuming primitive specialization of {@link AbstractThrowingFunction1}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <R> the type of the result of the function
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingFunction1
 * @see AbstractThrowingToLongFunctionN
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingLongFunction1<R, H extends AbstractThrowingLongFunction1.Handler<R>> extends AbstractThrowingFunctionN<R, H>, InputChainableInput<Long>, UnboxedInput<ThrowingFunction1<Long, R>> {

    /**
     * Applies this function to the given argument or throws a throwable.
     *
     * @param value the function argument
     * @return the function result
     */
    R apply(final long value) throws Throwable;

    @Override
    default R applyAllUnchecked(final Object... args) throws Throwable {
        return this.apply((long) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Long, R> boxInput() {
        return this::apply;
    }

    /**
     * @see LongFunction1
     */
    @Override
    default LongFunction1<R> handle(final H handler) {
        return (final long value) -> {
            try {
                return this.apply(value);
            } catch (final Throwable t) {
                return handler.onThrown(t, value);
            }
        };
    }

    /**
     * @see LongFunction1
     */
    @Override
    LongFunction1<R> swallow();

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
        R onThrown(final Throwable t, final long value);

        @Override
        default R onThrownUnchecked(final Throwable t, final Object... args) {
            return this.onThrown(t, (long) args[0]);
        }
    }
}
