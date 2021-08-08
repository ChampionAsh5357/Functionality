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
 * An object which can be disjunctively unioned with another object to produce a
 * combined result.
 *
 * @param <T> the type of this object
 *
 * @since 1.0.0
 */
public interface Xor<T extends Xor<T>> extends Sub<T>, Or<T> {

    /**
     * Disjunctively unions this object with another object to produce a combined result.
     * This is the equivalent of calling {@code A ^ B} or a logical {@code XOR}.
     *
     * @param other the disjunctively unioned object
     * @return a combined object that represents the symmetric difference of this object and the
     *         {@code other} object
     */
    @SuppressWarnings("unchecked")
    default Xor<T> xor(final T other) {
        return this.sub(other).or((T) other.sub((T) this));
    }

    @Override
    default Xor<T> sub(final T other) {
        return (Xor<T>) Sub.super.sub(other);
    }

    @Override
    Xor<T> or(final T other);

    @Override
    Xor<T> and(final T other);

    @Override
    Xor<T> not();
}
