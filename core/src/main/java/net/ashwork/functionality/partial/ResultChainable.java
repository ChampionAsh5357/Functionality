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

import java.util.Objects;

/**
 * Represents a chainable object to which its result can be chained to
 * return a different value.
 *
 * @param <R> the original type of the result of the chainable object
 *
 * @since 3.0.0
 */
public interface ResultChainable<R> {

    /**
     * Returns a chained object that applies this chainable object, and then
     * applies the {@code after} function to ths result. If evaluation of either
     * throws an exception, it is relayed to the caller of the chained object.
     *
     * @param after the function to apply after this chainable object is applied
     * @param <V> the type of the output of the {@code after} function, and of
     *            the chained object
     * @return a chained object that applies this chainable object and then
     *         applies the {@code after} function
     * @throws NullPointerException if {@code after} is null
     */
    default <V> Object andThen(final Function1<? super R, ? extends V> after) {
        Objects.requireNonNull(after, "The applied function cannot be null.");
        return this.andThenUnchecked(after);
    }

    /**
     * Returns a chained object that applies this chainable object, and then
     * applies the {@code after} function to ths result. If evaluation of either
     * throws an exception, it is relayed to the caller of the chained object.
     * This makes no assumptions on the arguments of this method.
     *
     * @param after the function to apply after this chainable object is applied
     * @param <V> the type of the output of the {@code after} function, and of
     *            the chained object
     * @return a chained object that applies this chainable object and then
     *         applies the {@code after} function
     */
    <V> Object andThenUnchecked(final Function1<? super R, ? extends V> after);
}
