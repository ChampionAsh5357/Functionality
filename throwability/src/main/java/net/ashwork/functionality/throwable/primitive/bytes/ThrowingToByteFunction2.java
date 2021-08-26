/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.primitive.bytes;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.primitive.bytes.ToByteFunction2;
import net.ashwork.functionality.throwable.ThrowingFunction2;
import net.ashwork.functionality.throwable.abstracts.primitive.bytes.AbstractThrowingToByteFunction2;

/**
 * Represents a function that accepts two arguments and produces a {@code byte}-valued result or throws a throwable.
 * This is the two-arity specialization of {@link ThrowingToByteFunctionN}.
 * This is the {@code byte}-producing primitive specialization of {@link ThrowingFunction2}.
 * This is the throwing variation of {@link ToByteFunction2}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsByte(Object, Object)}.
 *
 * @param <T1> the type of the first argument to the function
 * @param <T2> the type of the second argument to the function
 *
 * @see ThrowingFunction2
 * @see ThrowingToByteFunctionN
 * @see ToByteFunction2
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingToByteFunction2<T1, T2> extends AbstractThrowingToByteFunction2<T1, T2, AbstractThrowingToByteFunction2.Handler<T1, T2>> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @param <T1> the type of the first argument to the function
     * @param <T2> the type of the second argument to the function
     * @return a throwing instance of the original type
     *
     * @see ToByteFunction2
     */
    static <T1, T2> ThrowingToByteFunction2<T1, T2> from(final ToByteFunction2<T1, T2> function) {
        return function::applyAsByte;
    }

    /**
     * @see ThrowingFunction2
     */
    @Override
    default ThrowingFunction2<T1, T2, Byte> boxResult() {
        return this::applyAsByte;
    }

    @Override
    default ToByteFunction2<T1, T2> swallow() {
        return this.handle((t, t1, t2) -> (byte) 0);
    }


    /**
     * @see ThrowingFunction2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction2<T1, T2, V> andThen(final Function1<? super Byte, ? extends V> after) {
        return (ThrowingFunction2<T1, T2, V>) AbstractThrowingToByteFunction2.super.andThen(after);
    }

    /**
     * @see ThrowingFunction2
     */
    @Override
    default <V> ThrowingFunction2<T1, T2, V> andThenUnchecked(final Function1<? super Byte, ? extends V> after) {
        return (final T1 t1, final T2 t2) -> after.apply(this.applyAsByte(t1, t2));
    }
}
