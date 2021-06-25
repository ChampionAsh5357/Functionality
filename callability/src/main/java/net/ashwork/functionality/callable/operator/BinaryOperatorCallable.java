/*
 * Callability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.callable.operator;

import net.ashwork.functionality.callable.function.BiFunctionCallable;

import java.util.function.BinaryOperator;

/**
 * Represents an operation upon two operands that produces a
 * result of the same type as the operands and may throw an exception.
 * This is a specialization of {@link BiFunctionCallable} for the case
 * where the operands and result are of the same type.
 *
 * <p>This is a functional interface whose functional method is {@link #apply(Object, Object)}.
 *
 * @param <T> The type of the operands and result of the operator
 *
 * @see BiFunctionCallable
 * @see UnaryOperatorCallable
 * @since 2.0.0
 */
@FunctionalInterface
public interface BinaryOperatorCallable<T> extends BiFunctionCallable<T, T, T> {

    @Override
    default BinaryOperator<T> handle(final ExceptionHandler<? super T, ? super T, ? extends T> onException) {
        return (final T t1, final T t2) -> {
            try {
                return this.apply(t1, t2);
            } catch (final Exception e) {
                return onException.handle(t1, t2, e);
            }
        };
    }

    @Override
    default BinaryOperator<T> swallow() {
        return this.handle((t1, t2, e) -> null);
    }
}
