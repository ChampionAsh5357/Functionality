/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.partial;

import net.ashwork.functionality.Function1;

/**
 * Represents a {@link ResultChainable} to which the chained result is also a
 * chainable object.
 *
 * @param <R> the original type of the result of the chainable object
 *
 * @see ResultChainable
 * @since 3.0.0
 */
public interface ResultChainableResult<R> extends ResultChainable<R> {

    @SuppressWarnings("unchecked")
    @Override
    default <V> ResultChainableResult<V> andThen(final Function1<? super R, ? extends V> after) {
        return (ResultChainableResult<V>) ResultChainable.super.andThen(after);
    }

    @Override
    <V> ResultChainableResult<V> andThenUnchecked(final Function1<? super R, ? extends V> after);
}
