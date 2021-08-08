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
 * An object which can be subtracted with another object to produce a
 * combined result.
 *
 * @param <T> the type of this object
 *
 * @since 1.0.0
 */
public interface Sub<T extends Sub<T>> extends And<T>, Not {

    /**
     * Subtracts the other object from this object to produce a combined result.
     * This is the equivalent of calling {@code A - B} or the relative complement.
     *
     * @param other the subtrahend object
     * @return a combined object that represents the {@code other} object being
     *         subtracted from this object
     */
    @SuppressWarnings("unchecked")
    default Sub<T> sub(final T other) {
        return this.and((T) other.not());
    }

    @Override
    Sub<T> and(final T other);

    @Override
    Sub<T> not();
}
