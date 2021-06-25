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
import java.util.function.Predicate;

/**
 * Represents a predicate (boolean-valued function) of one argument and
 * may throw an exception.
 *
 * <p>This is a functional interface whose functional method is {@link #test(Object)}.
 *
 * @param <T> The type of the input to the predicate
 *
 * @since 1.1.0
 */
@FunctionalInterface
public interface PredicateCallable<T> {

    /**
     * Evaluates this predicate on the given argument,
     * ot throws an exception if unable to do so.
     *
     * @param t The input argument
     * @return {@code true} if the input argument matches the predicate,
     *         otherwise {@code false}
     * @throws Exception If unable to compute the result
     */
    boolean test(final T t) throws Exception;

    /**
     * Returns a predicate that handles the exception thrown if this
     * predicate was unable to compute a result.
     *
     * @param onException The predicate to apply if an exception was
     *                    thrown which returns a result
     * @return A predicate which handles any exception thrown by this
     *         predicate
     * @see Predicate
     */
    default Predicate<T> handle(final ExceptionHandler<? super T> onException) {
        return (final T t) -> {
            try {
                return this.test(t);
            } catch (final Exception e) {
                return onException.handle(t, e);
            }
        };
    }

    /**
     * Returns a predicate that swallows the exception thrown if this
     * predicate was unable to compute a result by returning false.
     *
     * @return A predicate which swallows any exception thrown by this
     *         predicate by returning false
     * @see Predicate
     */
    default Predicate<T> swallow() {
        return this.handle((t, e) -> false);
    }

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
    default PredicateCallable<T> and(final PredicateCallable<? super T> other) {
        Objects.requireNonNull(other, "The ANDed predicate cannot be null.");
        return (final T t) -> this.test(t) && other.test(t);
    }

    /**
     * Returns a predicate that represents the logical negation of this predicate.
     *
     * @return A predicate that represents the logical negation of this predicate
     */
    default PredicateCallable<T> negate() {
        return (final T t) -> !this.test(t);
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
    default PredicateCallable<T> or(final PredicateCallable<? super T> other) {
        Objects.requireNonNull(other, "The ORed predicate cannot be null.");
        return (final T t) -> this.test(t) || other.test(t);
    }

    /**
     * Returns a predicate that tests if two arguments are equal according to
     * {@link Objects#equals(Object, Object)}.
     *
     * @param targetRef The object reference with which to compare for equality,
     *                  which may be {@code null}
     * @param <T> The type of arguments to the predicate
     * @return A predicate that tests if two arguments are equal according to
     *         {@link Objects#equals(Object, Object)}
     */
    static <T> PredicateCallable<T> isEqual(final Object targetRef) {
        return (null == targetRef) ? Objects::isNull : targetRef::equals;
    }

    /**
     * Returns a predicate that is the negation of the supplied predicate. This is
     * accomplished by returning result of the calling {@link #negate()}.
     *
     * @param target The predicate to negate
     * @param <T> The type of arguments to the specified predicate
     * @return A predicate that negates the results of the supplied predicate
     * @throws NullPointerException If {@code target} is null
     */
    static <T> PredicateCallable<T> not(final PredicateCallable<? super T> target) {
        Objects.requireNonNull(target, "The NOTted predicate cannot be null.");
        return (PredicateCallable<T>) target.negate();
    }

    /**
     * Represents a handler that takes in the outer callable's parameters and
     * the thrown exception and returns a result safely.
     *
     * @param <T> The type of the input to the outer callable
     */
    @FunctionalInterface
    interface ExceptionHandler<T> {

        /**
         * Handles an exception thrown by the outer callable and returns safely.
         * This should never throw an exception.
         *
         * @param t The callable argument
         * @param e The thrown exception
         * @return The handled callable result
         */
        boolean handle(final T t, final Exception e);
    }
}
