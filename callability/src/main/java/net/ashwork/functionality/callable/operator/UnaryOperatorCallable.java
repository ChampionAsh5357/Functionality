/*
 * Callability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.callable.operator;

import net.ashwork.functionality.callable.function.FunctionCallable;

import java.util.function.UnaryOperator;

/**
 * Represents an operation on a single operand that produces a
 * result of the same type as its operand and may throw an exception.
 * This is a specialization of {@link FunctionCallable} for the case
 * where the operand and result are of the same type.
 *
 * <p>This is a functional interface whose functional method is {@link #apply(Object)}.
 *
 * @param <T> The type of the operand and result of the operator
 *
 * @see FunctionCallable
 * @since 1.1.0
 */
@FunctionalInterface
public interface UnaryOperatorCallable<T> extends FunctionCallable<T, T> {

    @Override
    default UnaryOperator<T> handle(final ExceptionHandler<? super T, ? extends T> onException) {
        return (final T t) -> {
            try {
                return this.apply(t);
            } catch (final Exception e) {
                return onException.handle(t, e);
            }
        };
    }

    @Override
    default UnaryOperator<T> swallow() {
        return this.handle((t, e) -> null);
    }

    /**
     * Returns a unary operator that always returns its input argument.
     *
     * @param <T> The type of the input and output of the operator
     * @return A unary operator that always returns its input argument
     */
    static <T> UnaryOperatorCallable<T> identity() {
        return t -> t;
    }
}
