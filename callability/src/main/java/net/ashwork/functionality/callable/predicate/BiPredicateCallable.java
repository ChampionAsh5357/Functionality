/*
 * Callability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.callable.predicate;

import java.util.Objects;
import java.util.function.BiPredicate;

/**
 * Represents a predicate (boolean-valued function) of two arguments and
 * may throw an exception. This is the two-arity specialization of {@link PredicateCallable}.
 *
 * <p>This is a functional interface whose functional method is {@link #test(Object, Object)}.
 *
 * @param <T> The type of the first argument to the predicate
 * @param <U> The type of the second argument to the predicate
 *
 * @see PredicateCallable
 * @since 2.0.0
 */
@FunctionalInterface
public interface BiPredicateCallable<T, U> {

    /**
     * Evaluates this predicate on the given arguments,
     * ot throws an exception if unable to do so.
     *
     * @param t The first input argument
     * @param u The second input argument
     * @return {@code true} if the input arguments matches the predicate,
     *         otherwise {@code false}
     * @throws Exception If unable to compute the result
     */
    boolean test(final T t, final U u) throws Exception;

    /**
     * Returns a predicate that handles the exception thrown if this
     * predicate was unable to compute a result.
     *
     * @param onException The predicate to apply if an exception was
     *                    thrown which returns a result
     * @return A predicate which handles any exception thrown by this
     *         predicate
     * @see BiPredicate
     */
    default BiPredicate<T, U> handle(final ExceptionHandler<? super T, ? super U> onException) {
        return (final T t, final U u) -> {
            try {
                return this.test(t, u);
            } catch (final Exception e) {
                return onException.handle(t, u, e);
            }
        };
    }

    /**
     * Returns a predicate that swallows the exception thrown if this
     * predicate was unable to compute a result by returning false.
     *
     * @return A predicate which swallows any exception thrown by this
     *         predicate by returning false
     * @see BiPredicate
     */
    default BiPredicate<T, U> swallow() { return this.handle((t, u, e) -> false); }

    /**
     * Returns a composed predicate that represents a short-circuiting logical AND
     * of this predicate and another. When evaluating the composed predicate, if
     * this predicate is {@code false}, then the {@code other} predicate is not evaluated.
     *
     * @param other A predicate that will be logically-ANDed with this predicate
     * @return A composed predicate that represents the short-circuiting logical AND of
     *         this predicate and the {@code other} predicate
     * @throws NullPointerException If {@code other} is null
     */
    default BiPredicateCallable<T, U> and(final BiPredicateCallable<? super T, ? super U> other) {
        Objects.requireNonNull(other, "The ANDed predicate cannot be null.");
        return (final T t, final U u) -> this.test(t, u) && other.test(t, u);
    }

    /**
     * Returns a predicate that represents the logical negation of this predicate.
     *
     * @return A predicate that represents the logical negation of this predicate
     */
    default BiPredicateCallable<T, U> negate() {
        return (final T t, final U u) -> !this.test(t, u);
    }

    /**
     * Returns a composed predicate that represents a short-circuiting logical OR of
     * this predicate and another. When evaluating the composed predicate, if this
     * predicate is {@code true}, then the {@code other} predicate is not evaluated.
     *
     * @param other A predicate that will be logically-ORed with this predicate
     * @return A composed predicate that represents the short-circuiting logical
     *        OR of this predicate and the {@code other} predicate
     * @throws NullPointerException If {@code other} is null
     */
    default BiPredicateCallable<T, U> or(final BiPredicateCallable<? super T, ? super U> other) {
        Objects.requireNonNull(other, "The ORed predicate cannot be null.");
        return (final T t, final U u) -> this.test(t, u) || other.test(t, u);
    }

    /**
     * Represents a handler that takes in the outer callable's parameters and
     * the thrown exception and returns a result safely.
     *
     * @param <T> The type of the first argument to the outer callable
     * @param <U> The type of the second argument to the outer callable
     */
    @FunctionalInterface
    interface ExceptionHandler<T, U> {

        /**
         * Handles an exception thrown by the outer callable and returns safely.
         * This should never throw an exception.
         *
         * @param t The first callable argument
         * @param u The second callable argument
         * @param e The thrown exception
         * @return The handled callable result
         */
        boolean handle(final T t, final U u, final Exception e);
    }
}
