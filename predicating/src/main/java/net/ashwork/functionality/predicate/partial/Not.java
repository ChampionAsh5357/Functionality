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
 * An object which can provide its complement.
 *
 * @since 1.0.0
 */
public interface Not {

    /**
     * Gets the complement of this object.
     * This is the equivalent of calling {@code ~A} or a logical {@code NOT}.
     *
     * @return a complement of this object
     */
    Not not();
}
