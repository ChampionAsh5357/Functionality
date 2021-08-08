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
 * Represents an object whose input and result is a primitive of some kind and can be
 * separated.
 *
 * @param <V> the type of the boxed version of this object with a boxed input and result
 * @param <I> the type of the boxed version of this object with a boxed input
 * @param <R> the type of the boxed version of this object with a boxed result
 *
 * @see Unboxed
 * @see UnboxedInput
 * @see UnboxedResult
 * @since 3.0.0
 */
public interface UnboxedAll<V, I, R> extends Unboxed<V>, UnboxedInput<I>, UnboxedResult<R> {
}
