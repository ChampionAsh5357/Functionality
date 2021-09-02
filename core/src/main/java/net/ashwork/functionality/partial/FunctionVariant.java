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
 * An object which can be represented the same but in a different function data type.
 *
 * @param <V> the other representable data type of the object
 *
 * @since 3.0.0
 */
public interface FunctionVariant<R, V extends ResultChainableResult<R>> {

    /**
     * Returns the function variant of this object.
     *
     * @return the function variant of this object
     */
    V toFunctionVariant();
}
