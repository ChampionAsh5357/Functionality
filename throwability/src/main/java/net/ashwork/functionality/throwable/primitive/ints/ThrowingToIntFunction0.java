/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.primitive.ints;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.primitive.ints.ToIntFunction0;
import net.ashwork.functionality.throwable.ThrowingFunction0;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction0;
import net.ashwork.functionality.throwable.abstracts.primitive.ints.AbstractThrowingToIntFunction0;
import net.ashwork.functionality.throwable.abstracts.primitive.ints.AbstractThrowingToIntFunctionN;

/**
 * Represents a function that accepts no arguments and produces an {@code int}-valued result or throws a throwable.
 * This is the zero-arity specialization of {@link AbstractThrowingToIntFunctionN}.
 * This is the {@code int}-producing primitive specialization of {@link AbstractThrowingFunction0}.
 * This is the throwing variation of {@link ToIntFunction0}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsInt()}.
 *
 * @see AbstractThrowingFunction0
 * @see AbstractThrowingToIntFunctionN
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingToIntFunction0 extends AbstractThrowingToIntFunction0<AbstractThrowingToIntFunction0.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ToIntFunction0
     */
    static ThrowingToIntFunction0 from(final ToIntFunction0 function) {
        return function::applyAsInt;
    }

    @Override
    default ToIntFunction0 swallow() {
        return this.handle(t -> 0);
    }

    /**
     * @see ThrowingFunction0
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction0<V> andThen(final Function1<? super Integer, ? extends V> after) {
        return (ThrowingFunction0<V>) AbstractThrowingToIntFunction0.super.andThen(after);
    }

    /**
     * @see ThrowingFunction0
     */
    @Override
    default <V> ThrowingFunction0<V> andThenUnchecked(final Function1<? super Integer, ? extends V> after) {
        return () -> after.apply(this.applyAsInt());
    }
}
