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
import net.ashwork.functionality.primitive.combined.ByteToBooleanFunction1;
import net.ashwork.functionality.throwable.ThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.combined.AbstractThrowingByteToBooleanFunction1;
import net.ashwork.functionality.throwable.primitive.booleans.ThrowingToBooleanFunction1;
import net.ashwork.functionality.throwable.primitive.booleans.ThrowingToBooleanFunctionN;
import net.ashwork.functionality.throwable.primitive.bytes.ThrowingByteFunction1;

/**
 * Represents a function that accepts a {@code byte}-valued argument and produces a {@code boolean}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link ThrowingToBooleanFunctionN}.
 * This is the {@code byte}-consuming primitive specialization of {@link ThrowingToBooleanFunction1}.
 * This is the {@code boolean}-producing primitive specialization of {@link ThrowingByteFunction1}.
 * This is the throwing variation of {@link ByteToBooleanFunction1}.
 *
 * <p>This is a functional interface whose functional method is {@link #applyAsBoolean(byte)}.
 *
 * @see ThrowingByteFunction1
 * @see ThrowingToBooleanFunction1
 * @see ThrowingToBooleanFunctionN
 * @see ByteToBooleanFunction1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingByteToBooleanFunction1 extends AbstractThrowingByteToBooleanFunction1<AbstractThrowingByteToBooleanFunction1.Handler> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param function the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ByteToBooleanFunction1
     */
    static ThrowingByteToBooleanFunction1 from(final ByteToBooleanFunction1 function) {
        return function::applyAsBoolean;
    }

    /**
     * @see ThrowingFunction1
     */
    @Override
    default ThrowingFunction1<Byte, Boolean> box() {
        return this::applyAsBoolean;
    }

    /**
     * @see ThrowingToBooleanFunction1
     */
    @Override
    default ThrowingToBooleanFunction1<Byte> boxInput() {
        return this::applyAsBoolean;
    }

    /**
     * @see ThrowingByteFunction1
     */
    @Override
    default ThrowingByteFunction1<Boolean> boxResult() {
        return this::applyAsBoolean;
    }

    @Override
    default ByteToBooleanFunction1 swallow() {
        return this.handle((t, value) -> false);
    }

    /**
     * @see ThrowingToBooleanFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingToBooleanFunction1<V> compose(final Function1<? super V, ? extends Byte> before) {
        return (ThrowingToBooleanFunction1<V>) AbstractThrowingByteToBooleanFunction1.super.compose(before);
    }

    /**
     * @see ThrowingToBooleanFunction1
     */
    @Override
    default <V> ThrowingToBooleanFunction1<V> composeUnchecked(final Function1<? super V, ? extends Byte> before) {
        return (final V v) -> this.applyAsBoolean(before.apply(v));
    }

    /**
     * @see ThrowingByteFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingByteFunction1<V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (ThrowingByteFunction1<V>) AbstractThrowingByteToBooleanFunction1.super.andThen(after);
    }

    /**
     * @see ThrowingByteFunction1
     */
    @Override
    default <V> ThrowingByteFunction1<V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final byte value) -> after.apply(this.applyAsBoolean(value));
    }
}
