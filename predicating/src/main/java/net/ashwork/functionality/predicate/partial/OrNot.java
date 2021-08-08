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
 * An object which can be subtracted with another object to produce the complement
 * of a combined result.
 *
 * @param <T> the type of this object
 *
 * @since 1.0.0
 */
public interface OrNot<T extends OrNot<T>> extends Not, Or<T> {

    /**
     * Subtracts the other object from this object to produce the complement of a combined result.
     * This is the equivalent of calling {@code ~(A - B)} or the complement of the relative complement.
     *
     * @param other the subtrahend object
     * @return a combined object that represents the complement of the {@code other}
     *         object being subtracted from this object
     */
    @SuppressWarnings("unchecked")
    default OrNot<T> orNot(final T other) {
        return this.or((T) other.not());
    }

    @Override
    OrNot<T> not();

    @Override
    OrNot<T> or(final T other);
}
