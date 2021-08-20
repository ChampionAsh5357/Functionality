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
import net.ashwork.functionality.primitive.combined.ShortToByteFunction1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.shorts.AbstractThrowingShortFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.bytes.AbstractThrowingToByteFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.bytes.AbstractThrowingToByteFunctionN;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts a {@code short}-valued argument and produces a {@code byte}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingToByteFunctionN}.
 * This is the {@code short}-consuming primitive specialization of {@link AbstractThrowingToByteFunction1}.
 * This is the {@code byte}-producing primitive specialization of {@link AbstractThrowingShortFunction1}.
 * This is the throwing variation of {@link ShortToByteFunction1}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <A> the type of the function which unboxes the {@code short} input and {@code byte} result
 * @param <I> the type of the function which unboxes the {@code short} input
 * @param <R> the type of the function which unboxes the {@code byte} result
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingShortFunction1
 * @see AbstractThrowingToByteFunction1
 * @see AbstractThrowingToByteFunctionN
 * @see ShortToByteFunction1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingShortToByteFunction1<A extends AbstractThrowingFunction1<Short, Byte, ?>, I extends AbstractThrowingToByteFunction1<Short, A, ?>, R extends AbstractThrowingShortFunction1<Byte, A, ?>, H extends AbstractThrowingShortToByteFunction1.Handler> extends AbstractThrowingToByteFunctionN<H>, InputChainableInput<Short>, UnboxedAll<A, I, R> {

    /**
     * Applies this function to the given argument or throws a throwable.
     *
     * @param value the function argument
     * @return the function result
     */
    byte applyAsByte(final short value) throws Throwable;

    @Override
    default byte applyAllAsByteUnchecked(final Object... args) throws Throwable {
        return this.applyAsByte((short) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see ShortToByteFunction1
     */
    @Override
    default ShortToByteFunction1 handle(final H handler) {
        return (final short value) -> {
            try {
                return this.applyAsByte(value);
            } catch (final Throwable t) {
                return handler.onThrownAsByte(t, value);
            }
        };
    }

    /**
     * @see ShortToByteFunction1
     */
    @Override
    ShortToByteFunction1 swallow();

    /**
     * @see AbstractThrowingToByteFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingToByteFunction1<V, ?, ?> compose(final Function1<? super V, ? extends Short> before) {
        return (AbstractThrowingToByteFunction1<V, ?, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractThrowingToByteFunction1
     */
    @Override
    <V> AbstractThrowingToByteFunction1<V, ?, ?> composeUnchecked(final Function1<? super V, ? extends Short> before);

    /**
     * @see AbstractThrowingShortFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingShortFunction1<V, ?, ?> andThen(final Function1<? super Byte, ? extends V> after) {
        return (AbstractThrowingShortFunction1<V, ?, ?>) AbstractThrowingToByteFunctionN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingShortFunction1
     */
    @Override
    <V> AbstractThrowingShortFunction1<V, ?, ?> andThenUnchecked(final Function1<? super Byte, ? extends V> after);

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
        byte onThrownAsByte(final Throwable t, final short value);

        @Override
        default byte onThrownAsByteUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsByte(t, (short) args[0]);
        }
    }
}
