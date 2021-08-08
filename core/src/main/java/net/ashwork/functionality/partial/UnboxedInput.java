/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.partial;

/**
 * Represents an object whose input is a primitive of some kind.
 *
 * @param <V> the type of the boxed version of this object with a boxed input
 *
 * @since 3.0.0
 */
public interface UnboxedInput<V> {

    /**
     * Returns the boxed version of this object with a boxed input.
     *
     * @return the boxed version of this object with a boxed input
     */
    V boxInput();
}
