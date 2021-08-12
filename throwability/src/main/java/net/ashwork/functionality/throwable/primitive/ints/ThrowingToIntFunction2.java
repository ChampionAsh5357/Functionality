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
import net.ashwork.functionality.primitive.ints.ToIntFunction2;
import net.ashwork.functionality.throwable.ThrowingFunction2;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction2;
import net.ashwork.functionality.throwable.abstracts.primitive.ints.AbstractThrowingToIntFunction2;
import net.ashwork.functionality.throwable.abstracts.primitive.ints.AbstractThrowingToIntFunctionN;

/**
 * Represents a function that accepts two arguments and produces an {@code int}-valued result or throws a throwable.
 * This is the two-arity specialization of {@link AbstractThrowingToIntFunctionN}.
 * This is the {@code int}-producing primitive specialization of {@link AbstractThrowingFunction2}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsInt(Object, Object)}.
 *
 * @param <T1> the type of the first argument to the function
 * @param <T2> the type of the second argument to the function
 *
 * @see AbstractThrowingFunction2
 * @see AbstractThrowingToIntFunctionN
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingToIntFunction2<T1, T2> extends AbstractThrowingToIntFunction2<T1, T2, AbstractThrowingToIntFunction2.Handler<T1, T2>> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @param <T1> the type of the first argument to the function
     * @param <T2> the type of the second argument to the function
     * @return a throwing instance of the original type
     *
     * @see ToIntFunction2
     */
    static <T1, T2> ThrowingToIntFunction2<T1, T2> from(final ToIntFunction2<T1, T2> function) {
        return function::applyAsInt;
    }

    @Override
    default ToIntFunction2<T1, T2> swallow() {
        return this.handle((t, t1, t2) -> 0);
    }


    /**
     * @see ThrowingFunction2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction2<T1, T2, V> andThen(final Function1<? super Integer, ? extends V> after) {
        return (ThrowingFunction2<T1, T2, V>) AbstractThrowingToIntFunction2.super.andThen(after);
    }

    /**
     * @see ThrowingFunction2
     */
    @Override
    default <V> ThrowingFunction2<T1, T2, V> andThenUnchecked(final Function1<? super Integer, ? extends V> after) {
        return (final T1 t1, final T2 t2) -> after.apply(this.applyAsInt(t1, t2));
    }
}
