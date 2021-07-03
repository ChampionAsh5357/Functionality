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
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * Represents a predicate (boolean-valued function) of two arguments. This is
 * the two-arity specialization of {@link Predicate}. This is an extension over
 * the current {@link BiPredicate} to allow boxing of the interface.
 *
 * <p>This is a functional interface whose functional method is
 * {@link #test(Object, Object)}.
 *
 * @param <T1> The type of the first argument to the predicate
 * @param <T2> The type of the second argument the predicate
 *
 * @see Predicate
 * @see BiPredicate
 * @since 2.2.0
 */
public interface Predicate2<T1, T2> extends BiPredicate<T1, T2> {

    /**
     * Wraps a predicate instance to provide library features for {@link BiPredicate}.
     *
     * @param predicate The predicate instance to be wrapped
     * @param <T1> The type of the first argument to the predicate
     * @param <T2> The type of the second argument to the predicate
     * @return The wrapped predicate as {@link Predicate2}
     */
    static <T1, T2> Predicate2<T1, T2> wrap(final BiPredicate<T1, T2> predicate) {
        return predicate::test;
    }

    @Override
    default Predicate2<T1, T2> and(BiPredicate<? super T1, ? super T2> other) {
        Objects.requireNonNull(other, "The ANDed predicate cannot be null.");
        return (final T1 t1, final T2 t2) -> this.test(t1, t2) && other.test(t1, t2);
    }

    @Override
    default Predicate2<T1, T2> negate() {
        return (final T1 t1, final T2 t2) -> !this.test(t1, t2);
    }

    @Override
    default Predicate2<T1, T2> or(BiPredicate<? super T1, ? super T2> other) {
        Objects.requireNonNull(other, "The ORed predicate cannot be null.");
        return (final T1 t1, final T2 t2) -> this.test(t1, t2) || other.test(t1, t2);
    }

    /**
     * Returns a {@link BiFunction} with the result boxed to a {@link Boolean}.
     *
     * @return A {@link BiFunction} with the result boxed to a {@link Boolean}
     *
     * @see BiFunction
     */
    default BiFunction<T1, T2, Boolean> boxed() { return this::test; }
}
