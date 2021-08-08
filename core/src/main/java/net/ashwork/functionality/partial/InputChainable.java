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
 * Represents a chainable object to which its input can be chained to
 * take in a different value.
 *
 * @param <T> the original type of the input to the chainable object
 *
 * @since 3.0.0
 */
public interface InputChainable<T> {

    /**
     * Returns a chained object that applies the {@code before} function to its
     * input, and then applies this chainable object to the result. If evaluation
     * of either throws an exception, it is relayed to the caller of the chained
     * object.
     *
     * @param before the function to apply before this chainable object is applied
     * @param <V> the type of the input to the {@code before} function, and to
     *            then chained object
     * @return a chained object that first applies the {@code before} function
     *         and then applies this chainable object
     * @throws NullPointerException if {@code before} is null
     */
    default <V> Object compose(final Function1<? super V, ? extends T> before) {
        Objects.requireNonNull(before, "The composed function cannot be null.");
        return this.composeUnchecked(before);
    }

    /**
     * Returns a chained object that applies the {@code before} function to its
     * input, and then applies this chainable object to the result. If evaluation
     * of either throws an exception, it is relayed to the caller of the chained
     * object. This makes no assumptions on the arguments of this method.
     *
     * @param before the function to apply before this chainable object is applied
     * @param <V> the type of the input to the {@code before} function, and to
     *            then chained object
     * @return a chained object that first applies the {@code before} function
     *         and then applies this chainable object
     * @throws NullPointerException if {@code before} is null
     */
    <V> Object composeUnchecked(final Function1<? super V, ? extends T> before);
}
