/*
 * Operating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.operator;

import net.ashwork.functionality.Function2;

import java.util.function.BinaryOperator;

/**
 * Represents an operation that accepts two operands and produces a result of the same type as its operands.
 * This is the two-arity specialization of {@link OperatorN}.
 * This is a specialization of {@link Function2} where the operands and result are of the same type.
 *
 * <p>This is a functional interface whose functional method is {@link #apply(Object, Object)}.
 *
 * @param <T> the type of the operands and result of the operator
 *
 * @see OperatorN
 * @see Function2
 * @since 1.0.0
 */
@FunctionalInterface
public interface Operator2<T> extends OperatorN<T>, Function2<T, T, T> {

    /**
     * Creates an instance of this object from its {@link BinaryOperator} variant.
     *
     * @param operator the variant of this object
     * @param <T> the type of the operands and result of the operator
     * @return an instance of this object
     *
     * @see BinaryOperator
     */
    static <T> Operator2<T> fromVariant(final BinaryOperator<T> operator) {
        return operator::apply;
    }

    /**
     * @see BinaryOperator
     */
    @Override
    default BinaryOperator<T> toVariant() {
        return this::apply;
    }
}
