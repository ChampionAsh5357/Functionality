/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.primitive.combined;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.primitive.combined.BooleanToByteFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingBooleanToByteFunction1;
import net.ashwork.functionality.throwable.primitive.booleans.ThrowingBooleanFunction1;
import net.ashwork.functionality.throwable.primitive.bytes.ThrowingToByteFunction1;
import net.ashwork.functionality.throwable.primitive.bytes.ThrowingToByteFunctionN;

/**
 * Represents a function that accepts a {@code boolean}-valued argument and produces a {@code byte}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToByteFunctionN}.
 * This is the {@code boolean}-consuming primitive specialization of {@link ThrowingToByteFunction1}.
 * This is the {@code byte}-producing primitive specialization of {@link ThrowingBooleanFunction1}.
 * This is the throwing variation of {@link BooleanToByteFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsByte(boolean)}.
 *
 * @see ThrowingBooleanFunction1
 * @see ThrowingToByteFunction1
 * @see ThrowingToByteFunctionN
 * @see BooleanToByteFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingBooleanToByteFunction1 extends AbstractThrowingBooleanToByteFunction1<ThrowingFunction1<Boolean, Byte>, ThrowingToByteFunction1<Boolean>, ThrowingBooleanFunction1<Byte>, AbstractThrowingBooleanToByteFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see BooleanToByteFunction1
     */
    static ThrowingBooleanToByteFunction1 from(final BooleanToByteFunction1 function) {
        return function::applyAsByte;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Boolean, Byte> box() {
        return this::applyAsByte;
    }

    /**
     * @see ThrowingToByteFunction1
     */
    @Override
    default ThrowingToByteFunction1<Boolean> boxInput() {
        return this::applyAsByte;
    }

    /**
     * @see ThrowingBooleanFunction1
     */
    @Override
    default ThrowingBooleanFunction1<Byte> boxResult() {
        return this::applyAsByte;
    }

    @Override
    default BooleanToByteFunction1 swallow() {
        return this.handle((t, value) -> (byte) 0);
    }

    /**
     * @see ThrowingToByteFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToByteFunction1<V> compose(final Function1<? super V, ? extends Boolean> before) {
        return (ThrowingToByteFunction1<V>) AbstractThrowingBooleanToByteFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToByteFunction1
     */
    @Override
    default <V> ThrowingToByteFunction1<V> composeUnchecked(final Function1<? super V, ? extends Boolean> before) {
        return (final V v) -> this.applyAsByte(before.apply(v));
    }

    /**
     * @see ThrowingBooleanFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingBooleanFunction1<V> andThen(final Function1<? super Byte, ? extends V> after) {
        return (ThrowingBooleanFunction1<V>) AbstractThrowingBooleanToByteFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingBooleanFunction1
     */
    @Override
    default <V> ThrowingBooleanFunction1<V> andThenUnchecked(final Function1<? super Byte, ? extends V> after) {
        return (final boolean value) -> after.apply(this.applyAsByte(value));
    }
}
