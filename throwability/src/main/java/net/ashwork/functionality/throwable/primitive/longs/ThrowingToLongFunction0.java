/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.primitive.longs;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.primitive.longs.ToLongFunction0;
import net.ashwork.functionality.throwable.ThrowingFunction0;
import net.ashwork.functionality.throwable.abstracts.primitive.longs.AbstractThrowingToLongFunction0;

/**
 * Represents a function that accepts no arguments and produces a {@code long}-valued result or throws a throwable.
 * This is the zero-arity specialization of {@link ThrowingToLongFunctionN}.
 * This is the {@code long}-producing primitive specialization of {@link ThrowingFunction0}.
 * This is the throwing variation of {@link ToLongFunction0}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsLong()}.
 *
 * @see ThrowingFunction0
 * @see ThrowingToLongFunctionN
 * @see ToLongFunction0
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingToLongFunction0 extends AbstractThrowingToLongFunction0<AbstractThrowingToLongFunction0.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ToLongFunction0
     */
    static ThrowingToLongFunction0 from(final ToLongFunction0 function) {
        return function::applyAsLong;
    }

    /**
     * @see ThrowingFunction0
     */
    @Override
    default ThrowingFunction0<Long> boxResult() {
        return this::applyAsLong;
    }

    @Override
    default ToLongFunction0 swallow() {
        return this.handle(t -> 0L);
    }

    /**
     * @see ThrowingFunction0
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction0<V> andThen(final Function1<? super Long, ? extends V> after) {
        return (ThrowingFunction0<V>) AbstractThrowingToLongFunction0.super.andThen(after);
    }

    /**
     * @see ThrowingFunction0
     */
    @Override
    default <V> ThrowingFunction0<V> andThenUnchecked(final Function1<? super Long, ? extends V> after) {
        return () -> after.apply(this.applyAsLong());
    }
}
