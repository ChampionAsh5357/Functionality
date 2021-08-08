/*
 * Predicating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.predicate.partial;

/**
 * Represents an object that can be combined with another object such that the
 * relation produces another logically operable object.
 *
 * @param <T> the type of this object
 *
 * @since 1.0.0
 */
public interface LogicalOperator<T extends LogicalOperator<T>> extends Nand<T>, Nor<T>, OrNot<T>, Xnor<T> {

    @Override
    LogicalOperator<T> not();

    @Override
    LogicalOperator<T> and(final T other);

    @Override
    LogicalOperator<T> or(final T other);

    @Override
    default LogicalOperator<T> xor(final T other) {
        return (LogicalOperator<T>) Xnor.super.xor(other);
    }

    @Override
    default LogicalOperator<T> sub(final T other) {
        return (LogicalOperator<T>) Xnor.super.sub(other);
    }

    @Override
    default LogicalOperator<T> nand(final T other) {
        return (LogicalOperator<T>) Nand.super.nand(other);
    }

    @Override
    default LogicalOperator<T> nor(final T other) {
        return (LogicalOperator<T>) Nor.super.nor(other);
    }

    @Override
    default LogicalOperator<T> xnor(final T other) {
        return (LogicalOperator<T>) Xnor.super.xnor(other);
    }

    @Override
    default LogicalOperator<T> orNot(final T other) {
        return (LogicalOperator<T>) OrNot.super.orNot(other);
    }
}
