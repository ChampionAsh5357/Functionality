/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.primitive.shorts;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.primitive.shorts.ToShortFunction0;
import net.ashwork.functionality.throwable.ThrowingFunction0;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction0;
import net.ashwork.functionality.throwable.abstracts.primitive.shorts.AbstractThrowingToShortFunction0;
import net.ashwork.functionality.throwable.abstracts.primitive.shorts.AbstractThrowingToShortFunctionN;

/**
 * Represents a function that accepts no arguments and produces a {@code short}-valued result or throws a throwable.
 * This is the zero-arity specialization of {@link AbstractThrowingToShortFunctionN}.
 * This is the {@code short}-producing primitive specialization of {@link AbstractThrowingFunction0}.
 * This is the throwing variation of {@link ToShortFunction0}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsShort()}.
 *
 * @see AbstractThrowingFunction0
 * @see AbstractThrowingToShortFunctionN
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingToShortFunction0 extends AbstractThrowingToShortFunction0<AbstractThrowingToShortFunction0.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ToShortFunction0
     */
    static ThrowingToShortFunction0 from(final ToShortFunction0 function) {
        return function::applyAsShort;
    }

    @Override
    default ToShortFunction0 swallow() {
        return this.handle(t -> (short) 0);
    }

    /**
     * @see ThrowingFunction0
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction0<V> andThen(final Function1<? super Short, ? extends V> after) {
        return (ThrowingFunction0<V>) AbstractThrowingToShortFunction0.super.andThen(after);
    }

    /**
     * @see ThrowingFunction0
     */
    @Override
    default <V> ThrowingFunction0<V> andThenUnchecked(final Function1<? super Short, ? extends V> after) {
        return () -> after.apply(this.applyAsShort());
    }
}
