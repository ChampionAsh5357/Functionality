/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.primitive.doubles;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.primitive.doubles.ToDoubleFunction0;
import net.ashwork.functionality.throwable.ThrowingFunction0;
import net.ashwork.functionality.throwable.abstracts.primitive.doubles.AbstractThrowingToDoubleFunction0;

/**
 * Represents a function that accepts no arguments and produces a {@code double}-valued result or throws a throwable.
 * This is the zero-arity specialization of {@link ThrowingToDoubleFunctionN}.
 * This is the {@code double}-producing primitive specialization of {@link ThrowingFunction0}.
 * This is the throwing variation of {@link ToDoubleFunction0}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsDouble()}.
 *
 * @see ThrowingFunction0
 * @see ThrowingToDoubleFunctionN
 * @see ToDoubleFunction0
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingToDoubleFunction0 extends AbstractThrowingToDoubleFunction0<ThrowingFunction0<Double>, AbstractThrowingToDoubleFunction0.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ToDoubleFunction0
     */
    static ThrowingToDoubleFunction0 from(final ToDoubleFunction0 function) {
        return function::applyAsDouble;
    }

    /**
     * @see ThrowingFunction0
     */
    @Override
    default ThrowingFunction0<Double> boxResult() {
        return this::applyAsDouble;
    }

    @Override
    default ToDoubleFunction0 swallow() {
        return this.handle(t -> 0.0d);
    }

    /**
     * @see ThrowingFunction0
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction0<V> andThen(final Function1<? super Double, ? extends V> after) {
        return (ThrowingFunction0<V>) AbstractThrowingToDoubleFunction0.super.andThen(after);
    }

    /**
     * @see ThrowingFunction0
     */
    @Override
    default <V> ThrowingFunction0<V> andThenUnchecked(final Function1<? super Double, ? extends V> after) {
        return () -> after.apply(this.applyAsDouble());
    }
}
