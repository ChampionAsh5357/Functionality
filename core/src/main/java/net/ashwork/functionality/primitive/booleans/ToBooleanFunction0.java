/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.primitive.booleans;

import net.ashwork.functionality.Function0;
import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.UnboxedResult;
import net.ashwork.functionality.partial.Variant;

import java.util.function.BooleanSupplier;

/**
 * Represents a function that accepts no arguments and produces a {@code boolean}-valued result.
 * This is the zero-arity specialization of {@link ToBooleanFunctionN}.
 * This is the {@code boolean}-producing primitive specialization of {@link Function0}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsBoolean()}.
 *
 * @see Function0
 * @see ToBooleanFunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface ToBooleanFunction0 extends ToBooleanFunctionN, UnboxedResult<Function0<Boolean>>, Variant<BooleanSupplier> {

    /**
     * Applies this function.
     *
     * @return the function result
     */
    boolean applyAsBoolean();

    @Override
    default boolean applyAllAsBooleanUnchecked(final Object... args) {
        return this.applyAsBoolean();
    }

    @Override
    default int arity() {
        return 0;
    }

    /**
     * Creates an instance of this object from its {@link BooleanSupplier} variant.
     *
     * @param supplier the variant of this object
     * @return an instance of this object
     *
     * @see BooleanSupplier
     */
    static ToBooleanFunction0 fromVariant(final BooleanSupplier supplier) {
        return supplier::getAsBoolean;
    }

    @Override
    default BooleanSupplier toVariant() {
        return this::applyAsBoolean;
    }

    /**
     * @see Function0
     */
    @Override
    default Function0<Boolean> boxResult() {
        return this::applyAsBoolean;
    }

    /**
     * @see Function0
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Function0<V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (Function0<V>) ToBooleanFunctionN.super.andThen(after);
    }

    /**
     * @see Function0
     */
    @Override
    default <V> Function0<V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return () -> after.apply(this.applyAsBoolean());
    }
}
