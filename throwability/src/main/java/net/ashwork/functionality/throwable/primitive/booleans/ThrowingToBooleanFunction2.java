/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.primitive.booleans;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.primitive.booleans.ToBooleanFunction2;
import net.ashwork.functionality.throwable.ThrowingFunction2;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction2;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingToBooleanFunction2;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingToBooleanFunctionN;

/**
 * Represents a function that accepts two arguments and produces a {@code boolean}-valued result or throws a throwable.
 * This is the two-arity specialization of {@link AbstractThrowingToBooleanFunctionN}.
 * This is the {@code boolean}-producing primitive specialization of {@link AbstractThrowingFunction2}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsBoolean(Object, Object)}.
 *
 * @param <T1> the type of the first argument to the function
 * @param <T2> the type of the second argument to the function
 *
 * @see AbstractThrowingFunction2
 * @see AbstractThrowingToBooleanFunctionN
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingToBooleanFunction2<T1, T2> extends AbstractThrowingToBooleanFunction2<T1, T2, AbstractThrowingToBooleanFunction2.Handler<T1, T2>> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @param <T1> the type of the first argument to the function
     * @param <T2> the type of the second argument to the function
     * @return a throwing instance of the original type
     *
     * @see ToBooleanFunction2
     */
    static <T1, T2> ThrowingToBooleanFunction2<T1, T2> from(final ToBooleanFunction2<T1, T2> function) {
        return function::applyAsBoolean;
    }

    @Override
    default ToBooleanFunction2<T1, T2> swallow() {
        return this.handle((t, t1, t2) -> false);
    }


    /**
     * @see ThrowingFunction2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction2<T1, T2, V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (ThrowingFunction2<T1, T2, V>) AbstractThrowingToBooleanFunction2.super.andThen(after);
    }

    /**
     * @see ThrowingFunction2
     */
    @Override
    default <V> ThrowingFunction2<T1, T2, V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final T1 t1, final T2 t2) -> after.apply(this.applyAsBoolean(t1, t2));
    }
}
