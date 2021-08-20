/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.primitive.combined;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedAll;
import net.ashwork.functionality.primitive.combined.FloatToByteFunction1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.floats.AbstractThrowingFloatFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.bytes.AbstractThrowingToByteFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.bytes.AbstractThrowingToByteFunctionN;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts a {@code float}-valued argument and produces a {@code byte}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingToByteFunctionN}.
 * This is the {@code float}-consuming primitive specialization of {@link AbstractThrowingToByteFunction1}.
 * This is the {@code byte}-producing primitive specialization of {@link AbstractThrowingFloatFunction1}.
 * This is the throwing variation of {@link FloatToByteFunction1}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <A> the type of the function which unboxes the {@code float} input and {@code byte} result
 * @param <I> the type of the function which unboxes the {@code float} input
 * @param <R> the type of the function which unboxes the {@code byte} result
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingFloatFunction1
 * @see AbstractThrowingToByteFunction1
 * @see AbstractThrowingToByteFunctionN
 * @see FloatToByteFunction1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingFloatToByteFunction1<A extends AbstractThrowingFunction1<Float, Byte, ?>, I extends AbstractThrowingToByteFunction1<Float, A, ?>, R extends AbstractThrowingFloatFunction1<Byte, A, ?>, H extends AbstractThrowingFloatToByteFunction1.Handler> extends AbstractThrowingToByteFunctionN<H>, InputChainableInput<Float>, UnboxedAll<A, I, R> {

    /**
     * Applies this function to the given argument or throws a throwable.
     *
     * @param value the function argument
     * @return the function result
     */
    byte applyAsByte(final float value) throws Throwable;

    @Override
    default byte applyAllAsByteUnchecked(final Object... args) throws Throwable {
        return this.applyAsByte((float) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see FloatToByteFunction1
     */
    @Override
    default FloatToByteFunction1 handle(final H handler) {
        return (final float value) -> {
            try {
                return this.applyAsByte(value);
            } catch (final Throwable t) {
                return handler.onThrownAsByte(t, value);
            }
        };
    }

    /**
     * @see FloatToByteFunction1
     */
    @Override
    FloatToByteFunction1 swallow();

    /**
     * @see AbstractThrowingToByteFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingToByteFunction1<V, ?, ?> compose(final Function1<? super V, ? extends Float> before) {
        return (AbstractThrowingToByteFunction1<V, ?, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractThrowingToByteFunction1
     */
    @Override
    <V> AbstractThrowingToByteFunction1<V, ?, ?> composeUnchecked(final Function1<? super V, ? extends Float> before);

    /**
     * @see AbstractThrowingFloatFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingFloatFunction1<V, ?, ?> andThen(final Function1<? super Byte, ? extends V> after) {
        return (AbstractThrowingFloatFunction1<V, ?, ?>) AbstractThrowingToByteFunctionN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingFloatFunction1
     */
    @Override
    <V> AbstractThrowingFloatFunction1<V, ?, ?> andThenUnchecked(final Function1<? super Byte, ? extends V> after);

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     */
    @FunctionalInterface
    interface Handler extends AbstractThrowingToByteFunctionN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param value the function argument
         * @return the handled result
         */
        byte onThrownAsByte(final Throwable t, final float value);

        @Override
        default byte onThrownAsByteUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsByte(t, (float) args[0]);
        }
    }
}
