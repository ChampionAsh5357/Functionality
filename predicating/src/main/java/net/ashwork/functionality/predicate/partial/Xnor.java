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
 * An object which can be disjunctively unioned with another object to produce the
 * complement of a combined result.
 *
 * @param <T> the type of this object
 *
 * @since 1.0.0
 */
public interface Xnor<T extends Xnor<T>> extends Xor<T> {

    /**
     * Disjunctively unions this object with another object to produce the complement of a combined result.
     * This is the equivalent of calling {@code ~(A ^ B)} or a logical {@code XNOR}.
     *
     * @param other the disjunctively unioned object
     * @return a combined object that represents the complement of the symmetric
     *         difference of this object and the {@code other} object
     */
    default Xnor<T> xnor(final T other) {
        return this.xor(other).not();
    }

    @Override
    default Xnor<T> xor(final T other) {
        return (Xnor<T>) Xor.super.xor(other);
    }

    @Override
    Xnor<T> not();

    @Override
    Xnor<T> and(final T other);

    @Override
    Xnor<T> or(final T other);

    @Override
    default Xnor<T> sub(final T other) {
        return (Xnor<T>) Xor.super.sub(other);
    }
}
