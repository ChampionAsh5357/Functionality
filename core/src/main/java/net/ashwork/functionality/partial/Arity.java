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
 * Defines an object that takes in a certain number of arguments.
 *
 * @since 3.0.0
 */
public interface Arity {

    /**
     * Returns the number of arguments of the object.
     *
     * @return the number of arguments of the object
     */
    int arity();
}
