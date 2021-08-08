/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts;

import net.ashwork.functionality.FunctionN;
import net.ashwork.functionality.partial.Arity;
import net.ashwork.functionality.partial.ResultChainableResult;
import net.ashwork.functionality.throwable.partial.ThrowableHandler;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts {@code n} arguments and produces a result or throws a throwable.
 * All throwing functions are derived from this {@code n}-arity specialization.
 * This is the throwing variation of {@link FunctionN}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <R> the type of the result of the function
 *
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingFunctionN<R, H extends AbstractThrowingFunctionN.Handler<R>> extends ResultChainableResult<R>, Arity, ThrowableHandler<H, FunctionN<R>> {

    /**
     * Applies this function to the given arguments or throws a throwable. Makes no assumptions
     * of the arguments passed in and whether a value will compute
     * successfully.
     *
     * @param args the function arguments
     * @return the function result
     */
    R applyAllUnchecked(final Object... args) throws Throwable;

    /**
     * Returns the number of arguments of the function. If used as a
     * functional interface with a non-specific number of arguments,
     * this will return {@code -1}.
     *
     * @return the number of arguments of the function
     */
    @Override
    default int arity() {
        return -1;
    }

    /**
     * Applies this function to the given arguments or throws a throwable. If the number of
     * arguments do not match the {@link #arity()} of this function,
     * an exception will be thrown.
     *
     * @param args the number of arguments of the function
     * @return the function result
     * @throws FunctionN.FunctionSizeException if the number of arguments of the
     *                               function is not equal to its arity
     */
    default R sizedApplyAllUnchecked(final Object... args) throws Throwable {
        return this.applyAllUnchecked(FunctionN.checkSize(this.arity(), args));
    }

    /**
     * @see FunctionN
     */
    @Override
    default FunctionN<R> handle(final H handler) {
        return (final Object[] args) -> {
            try {
                return this.applyAllUnchecked(args);
            } catch (final Throwable t) {
                return handler.onThrownUnchecked(t, args);
            }
        };
    }

    /**
     * @see FunctionN
     */
    @Override
    FunctionN<R> swallow();

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     *
     * @param <R> the type of the result of the function
     */
    @FunctionalInterface
    interface Handler<R> {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * Makes no assumptions of the arguments passed in and whether
         * a value will compute successfully.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param args the function arguments
         * @return the handled result
         */
        R onThrownUnchecked(final Throwable t, final Object... args);
    }
}
