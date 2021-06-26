/*
 * Aritiality (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.arity.operator;

import net.ashwork.functionality.arity.function.TriFunction;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * Represents an operation upon three operands of the same type, producing a result
 * of the same type as the operands.  This is a specialization of
 * {@link TriFunction} for the case where the operands and the result are all of
 * the same type.
 *
 * <p>This is a functional interface whose functional method is
 * {@link #apply(Object, Object, Object)}.
 *
 * @param <T> The type of the operands and result of the operator
 *
 * @see TriFunction
 * @see UnaryOperator
 * @since 1.0.0
 */
@FunctionalInterface
public interface TernaryOperator<T> extends TriFunction<T, T, T, T> {

    /**
     * Returns an operator which returns either the second or third argument based
     * on whether the {@code predicate} returns {@code true} or {@code false} on the
     * first argument respectively.
     *
     * @param predicate A {@link Predicate} for testing a specific value
     * @param <T> The type of the input argument of the predicate
     * @return An operator which returns either the second or third argument based
     *         on the {@code predicate}.
     * @throws NullPointerException If the {@code predicate} is null
     */
    static <T> TernaryOperator<T> conditional(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate, "The predicate cannot be null.");
        return (final T a, final T b, final T c) -> predicate.test(a) ? b : c;
    }
}
