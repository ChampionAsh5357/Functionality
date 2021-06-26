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
import java.util.function.Function;

/**
 * Represents a function that accepts one argument, produces a result,
 * and may throw an exception.
 *
 * <p>This is a functional interface whose functional method is {@link #apply(Object)}.
 *
 * @param <T> The type of the input to the function
 * @param <R> The type of the result of the function
 *
 * @since 1.1.0
 */
@FunctionalInterface
public interface FunctionCallable<T, R> {

    /**
     * Creates a callable instance from a non-callable type.
     *
     * @param function The non-callable type
     * @param <T> The type of the input
     * @param <R> The type of the result
     * @return A callable instance of the original type
     */
    static <T, R> FunctionCallable<T, R> from(final Function<T, R> function) { return function::apply; }

    /**
     * Applies this function to the given argument,
     * or throws an exception if unable to do so.
     *
     * @param t The function argument
     * @return The function result
     * @throws Exception If unable to compute a result
     */
    R apply(final T t) throws Exception;

    /**
     * Returns a function that handles the exception thrown if this
     * function was unable to compute a result.
     *
     * @param onException The function to apply if an exception was
     *                    thrown which returns a result
     * @return A function which handles any exception thrown by this
     *         function
     * @see Function
     */
    default Function<T, R> handle(final ExceptionHandler<? super T, ? extends R> onException) {
        return (final T t) -> {
            try {
                return this.apply(t);
            } catch (final Exception e) {
                return onException.handle(t, e);
            }
        };
    }

    /**
     * Returns a function that swallows the exception thrown if this
     * function was unable to compute a result by returning null.
     *
     * @return A function which swallows any exception thrown by this
     *         function by returning null
     * @see Function
     */
    default Function<T, R> swallow() {
        return this.handle((t, e) -> null);
    }

    /**
     * Returns a composed function that first applies the {@code before}
     * function to its input, and then applies this function to the result.
     *
     * @param before The function to apply before this function is applied
     * @param <V> The input type of the {@code before} function, and of the
     *            composed function
     * @return A composed function that first applies the {@code before}
     *         function and then applies this function.
     * @throws NullPointerException If {@code before} is null
     */
    default <V> FunctionCallable<V, R> compose(final FunctionCallable<? super V, ? extends T> before) {
        Objects.requireNonNull(before, "The composed function cannot be null.");
        return (final V v) -> this.apply(before.apply(v));
    }

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
    default <V> FunctionCallable<T, V> andThen(final FunctionCallable<? super R, ? extends V> after) {
        Objects.requireNonNull(after, "The applied function cannot be null.");
        return (final T t) -> after.apply(this.apply(t));
    }

    /**
     * Returns a function that always returns its input argument.
     *
     * @param <T> The type of the input and output objects to the function
     * @return A function that always returns its input argument
     */
    static <T> FunctionCallable<T, T> identity() { return t -> t; }

    /**
     * Represents a handler that takes in the outer callable's parameters and
     * the thrown exception and returns a result safely.
     *
     * @param <T> The type of the input to the outer callable
     * @param <R> The type of the result of the outer callable
     */
    @FunctionalInterface
    interface ExceptionHandler<T, R> {

        /**
         * Handles an exception thrown by the outer callable and returns safely.
         * This should never throw an exception.
         *
         * @param t The callable argument
         * @param e The thrown exception
         * @return The handled callable result
         */
        R handle(final T t, final Exception e);
    }
}
