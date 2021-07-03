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
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Represents a predicate (boolean-valued function) of one argument. This
 * is an extension over the current {@link Predicate} to allow boxing of
 * the interface.
 *
 * <p>This is a functional interface whose functional method is
 * {@link #test(Object)}.
 *
 * @param <T> The type of the input to the predicate
 *
 * @see Predicate
 * @since 2.2.0
 */
public interface Predicate1<T> extends Predicate<T> {

    /**
     * Wraps a predicate instance to provide library features for {@link Predicate}.
     *
     * @param predicate The predicate instance to be wrapped
     * @param <T> The type of the input to the predicate
     * @return The wrapped predicate as {@link Predicate1}
     */
    static <T> Predicate1<T> wrap(final Predicate<T> predicate) {
        return predicate::test;
    }

    @Override
    default Predicate1<T> and(Predicate<? super T> other) {
        Objects.requireNonNull(other, "The ANDed predicate cannot be null.");
        return (final T t) -> this.test(t) && other.test(t);
    }

    @Override
    default Predicate1<T> negate() {
        return (final T t) -> !this.test(t);
    }

    @Override
    default Predicate1<T> or(Predicate<? super T> other) {
        Objects.requireNonNull(other, "The ORed predicate cannot be null.");
        return (final T t) -> this.test(t) || other.test(t);
    }

    /**
     * Returns a {@link Function} with the result boxed to a {@link Boolean}.
     *
     * @return A {@link Function} with the result boxed to a {@link Boolean}
     *
     * @see Function
     */
    default Function<T, Boolean> boxed() { return this::test; }
}
