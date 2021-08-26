/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.primitive.floats;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.primitive.floats.ToFloatFunction0;
import net.ashwork.functionality.throwable.ThrowingFunction0;
import net.ashwork.functionality.throwable.abstracts.primitive.floats.AbstractThrowingToFloatFunction0;

/**
 * Represents a function that accepts no arguments and produces a {@code float}-valued result or throws a throwable.
 * This is the zero-arity specialization of {@link ThrowingToFloatFunctionN}.
 * This is the {@code float}-producing primitive specialization of {@link ThrowingFunction0}.
 * This is the throwing variation of {@link ToFloatFunction0}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsFloat()}.
 *
 * @see ThrowingFunction0
 * @see ThrowingToFloatFunctionN
 * @see ToFloatFunction0
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingToFloatFunction0 extends AbstractThrowingToFloatFunction0<AbstractThrowingToFloatFunction0.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ToFloatFunction0
     */
    static ThrowingToFloatFunction0 from(final ToFloatFunction0 function) {
        return function::applyAsFloat;
    }

    /**
     * @see ThrowingFunction0
     */
    @Override
    default ThrowingFunction0<Float> boxResult() {
        return this::applyAsFloat;
    }

    @Override
    default ToFloatFunction0 swallow() {
        return this.handle(t -> 0.0f);
    }

    /**
     * @see ThrowingFunction0
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction0<V> andThen(final Function1<? super Float, ? extends V> after) {
        return (ThrowingFunction0<V>) AbstractThrowingToFloatFunction0.super.andThen(after);
    }

    /**
     * @see ThrowingFunction0
     */
    @Override
    default <V> ThrowingFunction0<V> andThenUnchecked(final Function1<? super Float, ? extends V> after) {
        return () -> after.apply(this.applyAsFloat());
    }
}
