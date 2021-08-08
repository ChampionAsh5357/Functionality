/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.primitive.floats;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.Function2;
import net.ashwork.functionality.partial.UnboxedResult;

/**
 * Represents a function that accepts two arguments and produces a {@code float}-valued result.
 * This is the two-arity specialization of {@link ToFloatFunctionN}.
 * This is the {@code float}-producing primitive specialization of {@link Function2}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsFloat(Object, Object)}.
 *
 * @param <T1> the type of the input to the function
 * @param <T2> the type of the second argument to the function
 *
 * @see Function2
 * @see ToFloatFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface ToFloatFunction2<T1, T2> extends ToFloatFunctionN, UnboxedResult<Function2<T1, T2, Float>> {

    /**
     * Applies this function to the given arguments.
     *
     * @param t1 the first function argument
     * @param t2 the second function argument
     * @return the function result
     */
    float applyAsFloat(final T1 t1, final T2 t2);

    @SuppressWarnings("unchecked")
    @Override
    default float applyAllAsFloatUnchecked(final Object... args) {
        return this.applyAsFloat((T1) args[0], (T2) args[1]);
    }

    @Override
    default int arity() {
        return 2;
    }

    /**
     * @see Function2
     */
    @Override
    default Function2<T1, T2, Float> boxResult() {
        return this::applyAsFloat;
    }

    /**
     * @see Function2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Function2<T1, T2, V> andThen(final Function1<? super Float, ? extends V> after) {
        return (Function2<T1, T2, V>) ToFloatFunctionN.super.andThen(after);
    }

    /**
     * @see Function2
     */
    @Override
    default <V> Function2<T1, T2, V> andThenUnchecked(final Function1<? super Float, ? extends V> after) {
        return (final T1 t1, final T2 t2) -> after.apply(this.applyAsFloat(t1, t2));
    }
}
