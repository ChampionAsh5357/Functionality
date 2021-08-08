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
 * An object which can be intersected with another object to produce the
 * complement of a combined result.
 *
 * @param <T> the type of this object
 *
 * @since 1.0.0
 */
public interface Nand<T extends Nand<T>> extends Not, And<T> {

    /**
     * Intersects this object with another object to produce the complement of a combined result.
     * This is the equivalent of calling {@code ~(A & B)} or a logical {@code NAND}.
     *
     * @param other the intersected object
     * @return a combined object that represents the complement of the intersection
     *         of this object and the {@code other} object
     */
    default Nand<T> nand(final T other) {
        return this.and(other).not();
    }

    @Override
    Nand<T> not();

    @Override
    Nand<T> and(final T other);
}
