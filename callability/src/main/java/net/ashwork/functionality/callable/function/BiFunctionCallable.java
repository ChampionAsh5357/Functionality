/*
 * Callability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.callable.function;

import java.util.Objects;
import java.util.function.BiFunction;

/**
 * Represents a function that accepts two arguments, produces a result,
 * and may throw an exception. This is the two-arity specialization of
 * {@link FunctionCallable}.
 *
 * <p>This is a functional interface whose functional method is {@link #apply(Object, Object)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @param <R> The type of the result of the function
 *
 * @see FunctionCallable
 * @since 1.1.0
 */
@FunctionalInterface
public interface BiFunctionCallable<T, U, R> {

    /**
     * Creates a callable instance from a non-callable type.
     *
     * @param function The non-callable type
     * @param <T> The type of the first argument
     * @param <U> The type of the second argument
     * @param <R> The type of the result
     * @return A callable instance of the original type
     */
    static <T, U, R> BiFunctionCallable<T, U, R> from(final BiFunction<T, U, R> function) { return function::apply; }

    /**
     * Applies this function to the given arguments,
     * or throws an exception if unable to do so.
     *
     * @param t The first function argument
     * @param u The second function argument
     * @return The function result
     * @throws Exception If unable to compute a result
     */
    R apply(final T t, final U u) throws Exception;

    /**
     * Returns a function that handles the exception thrown if this
     * function was unable to compute a result.
     *
     * @param onException The function to apply if an exception was
     *                    thrown which returns a result
     * @return A function which handles any exception thrown by this
     *         function
     * @see BiFunction
     */
    default BiFunction<T, U, R> handle(final ExceptionHandler<? super T, ? super U, ? extends R> onException) {
        return (final T t, final U u) -> {
            try {
                return this.apply(t, u);
            } catch (final Exception e) {
                return onException.handle(t, u, e);
            }
        };
    }

    /**
     * Returns a function that swallows the exception thrown if this
     * function was unable to compute a result by returning null.
     *
     * @return A function which swallows any exception thrown by this
     *         function by returning null
     * @see BiFunction
     */
    default BiFunction<T, U, R> swallow() { return this.handle((t, u, e) -> null); }

    /**
     * Returns a composed function that first applies this function to its
     * input, and then applies the {@code after} function to the result.
     *
     * @param after The function to apply after this function is applied
     * @param <V> The output type of the {@code after} function, and of the
     *            composed function
     * @return A composed function that first applies this function
     *         and then applies the {@code after} function
     * @throws NullPointerException If {@code after} is null
     */
    default <V> BiFunctionCallable<T, U, V> andThen(final FunctionCallable<? super R, ? extends V> after) {
        Objects.requireNonNull(after, "The applied function cannot be null.");
        return (final T t, final U u) -> after.apply(this.apply(t, u));
    }

    /**
     * Represents a handler that takes in the outer callable's parameters and
     * the thrown exception and returns a result safely.
     *
     * @param <T> The type of the first argument to the outer callable
     * @param <U> The type of the second argument to the outer callable
     * @param <R> The type of the result of the outer callable
     */
    @FunctionalInterface
    interface ExceptionHandler<T, U, R> {

        /**
         * Handles an exception thrown by the outer callable and returns safely.
         * This should never throw an exception.
         *
         * @param t The first callable argument
         * @param u The second callable argument
         * @param e The thrown exception
         * @return The handled callable result
         */
        R handle(final T t, final U u, final Exception e);
    }
}
