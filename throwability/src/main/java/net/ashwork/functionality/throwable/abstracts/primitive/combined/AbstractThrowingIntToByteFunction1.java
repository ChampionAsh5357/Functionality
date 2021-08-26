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
import net.ashwork.functionality.primitive.combined.IntToByteFunction1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.bytes.AbstractThrowingToByteFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.bytes.AbstractThrowingToByteFunctionN;
import net.ashwork.functionality.throwable.abstracts.primitive.ints.AbstractThrowingIntFunction1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts an {@code int}-valued argument and produces a {@code byte}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingToByteFunctionN}.
 * This is the {@code int}-consuming primitive specialization of {@link AbstractThrowingToByteFunction1}.
 * This is the {@code byte}-producing primitive specialization of {@link AbstractThrowingIntFunction1}.
 * This is the throwing variation of {@link IntToByteFunction1}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingIntFunction1
 * @see AbstractThrowingToByteFunction1
 * @see AbstractThrowingToByteFunctionN
 * @see IntToByteFunction1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingIntToByteFunction1<H extends AbstractThrowingIntToByteFunction1.Handler> extends AbstractThrowingToByteFunctionN<H>, InputChainableInput<Integer>, UnboxedAll<AbstractThrowingFunction1<Integer, Byte, ?>, AbstractThrowingToByteFunction1<Integer, ?>, AbstractThrowingIntFunction1<Byte, ?>> {

    /**
     * Applies this function to the given argument or throws a throwable.
     *
     * @param value the function argument
     * @return the function result
     */
    byte applyAsByte(final int value) throws Throwable;

    @Override
    default byte applyAllAsByteUnchecked(final Object... args) throws Throwable {
        return this.applyAsByte((int) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see AbstractThrowingFunction1
     */
    @Override
    AbstractThrowingFunction1<Integer, Byte, ?> box();

    /**
     * @see AbstractThrowingToByteFunction1
     */
    @Override
    AbstractThrowingToByteFunction1<Integer, ?> boxInput();

    /**
     * @see AbstractThrowingIntFunction1
     */
    @Override
    AbstractThrowingIntFunction1<Byte, ?> boxResult();

    /**
     * @see IntToByteFunction1
     */
    @Override
    default IntToByteFunction1 handle(final H handler) {
        return (final int value) -> {
            try {
                return this.applyAsByte(value);
            } catch (final Throwable t) {
                return handler.onThrownAsByte(t, value);
            }
        };
    }

    /**
     * @see IntToByteFunction1
     */
    @Override
    IntToByteFunction1 swallow();

    /**
     * @see AbstractThrowingToByteFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingToByteFunction1<V, ?> compose(final Function1<? super V, ? extends Integer> before) {
        return (AbstractThrowingToByteFunction1<V, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractThrowingToByteFunction1
     */
    @Override
    <V> AbstractThrowingToByteFunction1<V, ?> composeUnchecked(final Function1<? super V, ? extends Integer> before);

    /**
     * @see AbstractThrowingIntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingIntFunction1<V, ?> andThen(final Function1<? super Byte, ? extends V> after) {
        return (AbstractThrowingIntFunction1<V, ?>) AbstractThrowingToByteFunctionN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingIntFunction1
     */
    @Override
    <V> AbstractThrowingIntFunction1<V, ?> andThenUnchecked(final Function1<? super Byte, ? extends V> after);

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
        byte onThrownAsByte(final Throwable t, final int value);

        @Override
        default byte onThrownAsByteUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsByte(t, (int) args[0]);
        }
    }
}
