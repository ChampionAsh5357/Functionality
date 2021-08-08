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
 * An object which can be unioned with another object to produce the
 * complement of a combined result.
 *
 * @param <T> the type of this object
 *
 * @since 1.0.0
 */
public interface Nor<T extends Nor<T>> extends Not, Or<T> {

    /**
     * Unions this object with another object to produce the complement of a combined result.
     * This is the equivalent of calling {@code ~(A | B)} or a logical {@code NOR}.
     *
     * @param other the unioned object
     * @return a combined object that represents the complement of the union
     *         of this object and the {@code other} object
     */
    default Nor<T> nor(final T other) {
        return this.or(other).not();
    }

    @Override
    Nor<T> not();

    @Override
    Nor<T> or(final T other);
}
