/*
 * Aritiality (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.arity.predicate;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Represents a predicate (boolean-valued function) of three arguments.  This is
 * the three-arity specialization of {@link Predicate}.
 *
 * <p>This is a functional interface whose functional method is
 * {@link #test(Object, Object, Object)}.
 *
 * @param <T1> The type of the first argument to the predicate
 * @param <T2> The type of the second argument to the predicate
 * @param <T3> The type of the third argument to the predicate
 *
 * @see Predicate
 * @since 1.0.0
 */
@FunctionalInterface
public interface TriPredicate<T1, T2, T3> {

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param t1 The first input argument
     * @param t2 The second input argument
     * @param t3 The third input argument
     * @return {@code true} if the input arguments matches the predicate,
     *         otherwise {@code false}
     */
    boolean test(final T1 t1, final T2 t2, final T3 t3);

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * AND of this predicate and another.  When evaluating the composed
     * predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     *
     * <p>Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other A predicate that will be logically-ANDed with this
     *              predicate
     * @return A composed predicate that represents the short-circuiting logical
     *         AND of this predicate and the {@code other} predicate
     * @throws NullPointerException If other is null
     */
    default TriPredicate<T1, T2, T3> and(final TriPredicate<? super T1, ? super T2, ? super T3> other) {
        Objects.requireNonNull(other, "The ANDed predicate cannot be null.");
        return (final T1 t1, final T2 t2, final T3 t3) -> this.test(t1, t2, t3) && other.test(t1, t2, t3);
    }

    /**
     * Returns a predicate that represents the logical negation of this
     * predicate.
     *
     * @return A predicate that represents the logical negation of this
     *         predicate
     */
    default TriPredicate<T1, T2, T3> negate() {
        return (final T1 t1, final T2 t2, final T3 t3) -> !this.test(t1, t2, t3);
    }

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * OR of this predicate and another.  When evaluating the composed
     * predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     *
     * <p>Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other A predicate that will be logically-ORed with this
     *              predicate
     * @return A composed predicate that represents the short-circuiting logical
     *         OR of this predicate and the {@code other} predicate
     * @throws NullPointerException If other is null
     */
    default TriPredicate<T1, T2, T3> or(final TriPredicate<? super T1, ? super T2, ? super T3> other) {
        Objects.requireNonNull(other, "The ORed predicate cannot be null.");
        return (final T1 t1, final T2 t2, final T3 t3) -> this.test(t1, t2, t3) || other.test(t1, t2, t3);
    }
}
