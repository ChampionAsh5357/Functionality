/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.operator;

import net.ashwork.functionality.operator.OperatorN;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunctionN;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents an operation that accepts {@code n} operands and produces a result of the same type as its operands or throws a throwable.
 * All operators are derived from this {@code n}-arity specialization.
 * This is a specialization of {@link AbstractThrowingFunctionN} where the operands and result are of the same type.
 * This is the throwing variation of {@link OperatorN}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <T> the type of the operands and result of the operator
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingFunctionN
 * @see OperatorN
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingOperatorN<T, H extends AbstractThrowingFunctionN.Handler<T>> extends AbstractThrowingFunctionN<T, H> {

    /**
     * @see OperatorN
     */
    @Override
    default OperatorN<T> handle(final H handler) {
        return (final Object[] args) -> {
            try {
                return this.applyAllUnchecked(args);
            } catch (final Throwable t) {
                return handler.onThrownUnchecked(t, args);
            }
        };
    }

    /**
     * @see OperatorN
     */
    @Override
    OperatorN<T> swallow();
}
