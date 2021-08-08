/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.primitive.floats;

import net.ashwork.functionality.Function0;
import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.UnboxedResult;

/**
 * Represents a function that accepts no arguments and produces a {@code float}-valued result.
 * This is the zero-arity specialization of {@link ToFloatFunctionN}.
 * This is the {@code float}-producing primitive specialization of {@link Function0}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsFloat()}.
 *
 * @see Function0
 * @see ToFloatFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface ToFloatFunction0 extends ToFloatFunctionN, UnboxedResult<Function0<Float>> {

    /**
     * Applies this function.
     *
     * @return the function result
     */
    float applyAsFloat();

    @Override
    default float applyAllAsFloatUnchecked(final Object... args) {
        return this.applyAsFloat();
    }

    @Override
    default int arity() {
        return 0;
    }

    /**
     * @see Function0
     */
    @Override
    default Function0<Float> boxResult() {
        return this::applyAsFloat;
    }

    /**
     * @see Function0
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Function0<V> andThen(final Function1<? super Float, ? extends V> after) {
        return (Function0<V>) ToFloatFunctionN.super.andThen(after);
    }

    /**
     * @see Function0
     */
    @Override
    default <V> Function0<V> andThenUnchecked(final Function1<? super Float, ? extends V> after) {
        return () -> after.apply(this.applyAsFloat());
    }
}
