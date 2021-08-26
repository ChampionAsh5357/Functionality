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
import net.ashwork.functionality.primitive.doubles.ToDoubleFunction2;
import net.ashwork.functionality.throwable.ThrowingFunction2;
import net.ashwork.functionality.throwable.abstracts.primitive.doubles.AbstractThrowingToDoubleFunction2;

/**
 * Represents a function that accepts two arguments and produces a {@code double}-valued result or throws a throwable.
 * This is the two-arity specialization of {@link ThrowingToDoubleFunctionN}.
 * This is the {@code double}-producing primitive specialization of {@link ThrowingFunction2}.
 * This is the throwing variation of {@link ToDoubleFunction2}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsDouble(Object, Object)}.
 *
 * @param <T1> the type of the first argument to the function
 * @param <T2> the type of the second argument to the function
 *
 * @see ThrowingFunction2
 * @see ThrowingToDoubleFunctionN
 * @see ToDoubleFunction2
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingToDoubleFunction2<T1, T2> extends AbstractThrowingToDoubleFunction2<T1, T2, AbstractThrowingToDoubleFunction2.Handler<T1, T2>> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @param <T1> the type of the first argument to the function
     * @param <T2> the type of the second argument to the function
     * @return a throwing instance of the original type
     *
     * @see ToDoubleFunction2
     */
    static <T1, T2> ThrowingToDoubleFunction2<T1, T2> from(final ToDoubleFunction2<T1, T2> function) {
        return function::applyAsDouble;
    }

    /**
     * @see ThrowingFunction2
     */
    @Override
    default ThrowingFunction2<T1, T2, Double> boxResult() {
        return this::applyAsDouble;
    }

    @Override
    default ToDoubleFunction2<T1, T2> swallow() {
        return this.handle((t, t1, t2) -> 0.0d);
    }


    /**
     * @see ThrowingFunction2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction2<T1, T2, V> andThen(final Function1<? super Double, ? extends V> after) {
        return (ThrowingFunction2<T1, T2, V>) AbstractThrowingToDoubleFunction2.super.andThen(after);
    }

    /**
     * @see ThrowingFunction2
     */
    @Override
    default <V> ThrowingFunction2<T1, T2, V> andThenUnchecked(final Function1<? super Double, ? extends V> after) {
        return (final T1 t1, final T2 t2) -> after.apply(this.applyAsDouble(t1, t2));
    }
}
