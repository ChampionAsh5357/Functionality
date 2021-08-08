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
 * Represents an {@link InputChainable} to which the chained input is also a
 * chainable object.
 *
 * @param <T> the original type of the input to the chainable object
 *
 * @see InputChainable
 * @since 3.0.0
 */
public interface InputChainableInput<T> extends InputChainable<T> {

    @SuppressWarnings("unchecked")
    default <V> InputChainableInput<V> compose(final Function1<? super V, ? extends T> before) {
        return (InputChainableInput<V>) InputChainable.super.compose(before);
    }

    @Override
    <V> InputChainableInput<V> composeUnchecked(final Function1<? super V, ? extends T> before);
}
