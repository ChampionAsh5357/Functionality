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
import net.ashwork.functionality.primitive.booleans.ToBooleanFunction0;
import net.ashwork.functionality.throwable.ThrowingFunction0;
import net.ashwork.functionality.throwable.abstracts.primitive.booleans.AbstractThrowingToBooleanFunction0;

/**
 * Represents a function that accepts no arguments and produces a {@code boolean}-valued result or throws a throwable.
 * This is the zero-arity specialization of {@link ThrowingToBooleanFunctionN}.
 * This is the {@code boolean}-producing primitive specialization of {@link ThrowingFunction0}.
 * This is the throwing variation of {@link ToBooleanFunction0}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsBoolean()}.
 *
 * @see ThrowingFunction0
 * @see ThrowingToBooleanFunctionN
 * @see ToBooleanFunction0
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingToBooleanFunction0 extends AbstractThrowingToBooleanFunction0<ThrowingFunction0<Boolean>, AbstractThrowingToBooleanFunction0.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ToBooleanFunction0
     */
    static ThrowingToBooleanFunction0 from(final ToBooleanFunction0 function) {
        return function::applyAsBoolean;
    }

    /**
     * @see ThrowingFunction0
     */
    @Override
    default ThrowingFunction0<Boolean> boxResult() {
        return this::applyAsBoolean;
    }

    @Override
    default ToBooleanFunction0 swallow() {
        return this.handle(t -> false);
    }

    /**
     * @see ThrowingFunction0
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction0<V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (ThrowingFunction0<V>) AbstractThrowingToBooleanFunction0.super.andThen(after);
    }

    /**
     * @see ThrowingFunction0
     */
    @Override
    default <V> ThrowingFunction0<V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return () -> after.apply(this.applyAsBoolean());
    }
}
