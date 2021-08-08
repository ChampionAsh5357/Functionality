/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.primitive.bytes;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.FunctionN;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedInput;

/**
 * Represents a function that accepts a {@code byte}-valued argument and produces a result.
 * This is the one-arity specialization of {@link FunctionN}.
 * This is the {@code byte}-consuming primitive specialization of {@link Function1}.
 *
 * <p>This is a functional interface whose functional method is {@link #apply(byte)}.
 *
 * @param <R> the type of the result of the function
 *
 * @see Function1
 * @see FunctionN
 * @since 3.0.0
 */
@FunctionalInterface
public interface ByteFunction1<R> extends FunctionN<R>, InputChainableInput<Byte>, UnboxedInput<Function1<Byte, R>> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    R apply(final byte value);

    @Override
    default R applyAllUnchecked(final Object... args) {
        return this.apply((byte) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see Function1
     */
    @Override
    default Function1<Byte, R> boxInput() {
        return this::apply;
    }

    /**
     * @see Function1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Function1<V, R> compose(final Function1<? super V, ? extends Byte> before) {
        return (Function1<V, R>) InputChainableInput.super.compose(before);
    }

    /**
     * @see Function1
     */
    @Override
    default <V> Function1<V, R> composeUnchecked(final Function1<? super V, ? extends Byte> before) {
        return (final V v) -> this.apply(before.apply(v));
    }

    @SuppressWarnings("unchecked")
    @Override
    default <V> ByteFunction1<V> andThen(final Function1<? super R, ? extends V> after) {
        return (ByteFunction1<V>) FunctionN.super.andThen(after);
    }

    @Override
    default <V> ByteFunction1<V> andThenUnchecked(final Function1<? super R, ? extends V> after) {
        return (final byte value) -> after.apply(this.apply(value));
    }
}
