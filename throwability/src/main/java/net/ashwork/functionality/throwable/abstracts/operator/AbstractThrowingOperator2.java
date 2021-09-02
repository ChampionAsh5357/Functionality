/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.operator;

import net.ashwork.functionality.operator.Operator2;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction2;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents an operation that accepts two operands and produces a result of the same type as its operands.
 * This is the two-arity specialization of {@link AbstractThrowingOperatorN}.
 * This is a specialization of {@link AbstractThrowingFunction2} where the operands and result are of the same type.
 * This is the throwing variation of {@link Operator2}.
 *
 * @apiNote
 * This is an abstract operator and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <T> the type of the operands and result of the operator
 * @param <H> the type of the handler to safely call the operator
 *
 * @see AbstractThrowingOperatorN
 * @see AbstractThrowingFunction2
 * @see Operator2
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingOperator2<T, H extends AbstractThrowingFunction2.Handler<T, T, T>> extends AbstractThrowingOperatorN<T, H>, AbstractThrowingFunction2<T, T, T, H> {

    /**
     * @see Operator2
     */
    @Override
    default Operator2<T> handle(final H handler) {
        return (final T t1, final T t2) -> {
            try {
                return this.apply(t1, t2);
            } catch (final Throwable t) {
                return handler.onThrown(t, t1, t2);
            }
        };
    }

    /**
     * @see Operator2
     */
    @Override
    Operator2<T> swallow();
}
