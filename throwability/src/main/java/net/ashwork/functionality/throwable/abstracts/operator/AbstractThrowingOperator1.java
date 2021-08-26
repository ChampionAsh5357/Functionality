/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.operator;

import net.ashwork.functionality.operator.Operator1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents an operation that accepts an operand and produces a result of the same type as its operand or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingOperatorN}.
 * This is a specialization of {@link AbstractThrowingFunction1} where the operand and result are of the same type.
 * This is the throwing variation of {@link Operator1}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <T> the type of the operand and result of the operator
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingOperatorN
 * @see AbstractThrowingFunction1
 * @see Operator1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingOperator1<T, H extends AbstractThrowingFunction1.Handler<T, T>> extends AbstractThrowingOperatorN<T, H>, AbstractThrowingFunction1<T, T, H> {

    /**
     * @see Operator1
     */
    @Override
    default Operator1<T> handle(final H handler) {
        return (final T t1) -> {
            try {
                return this.apply(t1);
            } catch (final Throwable t) {
                return handler.onThrown(t, t1);
            }
        };
    }

    /**
     * @see Operator1
     */
    @Override
    Operator1<T> swallow();
}
