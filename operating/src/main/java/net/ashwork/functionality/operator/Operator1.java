/*
 * Operating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.operator;

import net.ashwork.functionality.Function1;

import java.util.function.UnaryOperator;

/**
 * Represents an operation that accepts an operand and produces a result of the same type as its operand.
 * This is the one-arity specialization of {@link OperatorN}.
 * This is a specialization of {@link Function1} where the operand and result are of the same type.
 *
 * <p>This is a functional interface whose functional method is {@link #apply(Object)}.
 *
 * @param <T> the type of the operand and result of the operator
 *
 * @see OperatorN
 * @see Function1
 * @since 1.0.0
 */
@FunctionalInterface
public interface Operator1<T> extends OperatorN<T>, Function1<T, T> {

    /**
     * Creates an instance of this object from its {@link UnaryOperator} variant.
     *
     * @param operator the variant of this object
     * @param <T> the type of the operand and result of the operator
     * @return an instance of this object
     *
     * @see UnaryOperator
     */
    static <T> Operator1<T> fromVariant(final UnaryOperator<T> operator) {
        return operator::apply;
    }

    /**
     * @see UnaryOperator
     */
    @Override
    default UnaryOperator<T> toVariant() {
        return this::apply;
    }
}
