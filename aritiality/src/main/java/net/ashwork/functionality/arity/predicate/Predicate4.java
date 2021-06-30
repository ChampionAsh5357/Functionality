/*
 * Aritiality (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.arity.predicate;

import net.ashwork.functionality.arity.function.Function4;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Represents a predicate (boolean-valued function) of four arguments. This is
 * the four-arity specialization of {@link Predicate}.
 *
 * <p>This is a functional interface whose functional method is
 * {@link #test(Object, Object, Object, Object)}.
 *
 * @param <T1> The type of the first argument to the predicate
 * @param <T2> The type of the second argument to the predicate
 * @param <T3> The type of the third argument to the predicate
 * @param <T4> The type of the fourth argument to the predicate
 *
 * @see Predicate
 * @since 2.0.0
 */
@FunctionalInterface
public interface Predicate4<T1, T2, T3, T4> {

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param t1 The first input argument
     * @param t2 The second input argument
     * @param t3 The third input argument
     * @param t4 The fourth input argument
     * @return {@code true} if the input arguments matches the predicate,
     *         otherwise {@code false}
     */
    boolean test(final T1 t1, final T2 t2, final T3 t3, final T4 t4);

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
    default Predicate4<T1, T2, T3, T4> and(final Predicate4<? super T1, ? super T2, ? super T3, ? super T4> other) {
        Objects.requireNonNull(other, "The ANDed predicate cannot be null.");
        return (final T1 t1, final T2 t2, final T3 t3, final T4 t4)
                -> this.test(t1, t2, t3, t4) && other.test(t1, t2, t3, t4);
    }

    /**
     * Returns a predicate that represents the logical negation of this
     * predicate.
     *
     * @return A predicate that represents the logical negation of this
     *         predicate
     */
    default Predicate4<T1, T2, T3, T4> negate() {
        return (final T1 t1, final T2 t2, final T3 t3, final T4 t4) -> !this.test(t1, t2, t3, t4);
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
    default Predicate4<T1, T2, T3, T4> or(final Predicate4<? super T1, ? super T2, ? super T3, ? super T4> other) {
        Objects.requireNonNull(other, "The ORed predicate cannot be null.");
        return (final T1 t1, final T2 t2, final T3 t3, final T4 t4)
                -> this.test(t1, t2, t3, t4) || other.test(t1, t2, t3, t4);
    }

    /**
     * Returns a {@link Function4} with the result boxed to a {@link Boolean}.
     *
     * @return A {@link Function4} with the result boxed to a {@link Boolean}
     *
     * @see Function4
     */
    default Function4<T1, T2, T3, T4, Boolean> boxed() {
        return this::test;
    }
}
