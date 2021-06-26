/*
 * Callability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.callable.function.primitive;

import net.ashwork.functionality.callable.function.FunctionCallable;

import java.util.function.IntFunction;

/**
 * Represents a function that accepts an {@code int}-valued argument, produces a
 * result, and may throw an exception. This is the {@code int}-consuming
 * primitive specialization for {@link FunctionCallable}.
 *
 * <p>This is a functional interface whose functional method is {@link #apply(int)}.
 *
 * @param <R> The type of the result of the function
 *
 * @see FunctionCallable
 * @since 1.1.0
 */
@FunctionalInterface
public interface IntFunctionCallable<R> {

    /**
     * Creates a callable instance from a non-callable type.
     *
     * @param function The non-callable type
     * @param <R> The type of the result
     * @return A callable instance of the original type
     */
    static <R> IntFunctionCallable<R> from(final IntFunction<R> function) { return function::apply; }

    /**
     * Applies this function to the given argument,
     * or throws an exception if unable to do so.
     *
     * @param value The function argument
     * @return The function result
     * @throws Exception If unable to compute a result
     */
    R apply(int value) throws Exception;

    /**
     * Returns a function that handles the exception thrown if this
     * function was unable to compute a result.
     *
     * @param onException The function to apply if an exception was
     *                    thrown which returns a result
     * @return A function which handles any exception thrown by this
     *         function
     * @see IntFunction
     */
    default IntFunction<R> handle(final ExceptionHandler<? extends R> onException) {
        return (final int value) -> {
            try {
                return this.apply(value);
            } catch (final Exception e) {
                return onException.handle(value, e);
            }
        };
    }

    /**
     * Returns a function that swallows the exception thrown if this
     * function was unable to compute a result by returning null.
     *
     * @return A function which swallows any exception thrown by this
     *         function by returning null
     * @see IntFunction
     */
    default IntFunction<R> swallow() { return this.handle((value, e) -> null); }

    /**
     * Represents a handler that takes in the outer callable's parameters and
     * the thrown exception and returns a result safely.
     *
     * @param <R> The type of the result of the outer callable
     */
    @FunctionalInterface
    interface ExceptionHandler<R> {

        /**
         * Handles an exception thrown by the outer callable and returns safely.
         * This should never throw an exception.
         *
         * @param value The callable argument
         * @param e The thrown exception
         * @return The handled callable result
         */
        R handle(final int value, final Exception e);
    }
}
