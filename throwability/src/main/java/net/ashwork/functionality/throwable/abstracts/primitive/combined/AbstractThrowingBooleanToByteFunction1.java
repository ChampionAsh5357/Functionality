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
import net.ashwork.functionality.primitive.combined.BooleanToByteFunction1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.booleans.AbstractThrowingBooleanFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.bytes.AbstractThrowingToByteFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.bytes.AbstractThrowingToByteFunctionN;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts a {@code boolean}-valued argument and produces a {@code byte}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingToByteFunctionN}.
 * This is the {@code boolean}-consuming primitive specialization of {@link AbstractThrowingToByteFunction1}.
 * This is the {@code byte}-producing primitive specialization of {@link AbstractThrowingBooleanFunction1}.
 * This is the throwing variation of {@link BooleanToByteFunction1}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <A> the type of the function which unboxes the {@code boolean} input and {@code byte} result
 * @param <I> the type of the function which unboxes the {@code boolean} input
 * @param <R> the type of the function which unboxes the {@code byte} result
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingBooleanFunction1
 * @see AbstractThrowingToByteFunction1
 * @see AbstractThrowingToByteFunctionN
 * @see BooleanToByteFunction1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingBooleanToByteFunction1<A extends AbstractThrowingFunction1<Boolean, Byte, ?>, I extends AbstractThrowingToByteFunction1<Boolean, A, ?>, R extends AbstractThrowingBooleanFunction1<Byte, A, ?>, H extends AbstractThrowingBooleanToByteFunction1.Handler> extends AbstractThrowingToByteFunctionN<H>, InputChainableInput<Boolean>, UnboxedAll<A, I, R> {

    /**
     * Applies this function to the given argument or throws a throwable.
     *
     * @param value the function argument
     * @return the function result
     */
    byte applyAsByte(final boolean value) throws Throwable;

    @Override
    default byte applyAllAsByteUnchecked(final Object... args) throws Throwable {
        return this.applyAsByte((boolean) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see BooleanToByteFunction1
     */
    @Override
    default BooleanToByteFunction1 handle(final H handler) {
        return (final boolean value) -> {
            try {
                return this.applyAsByte(value);
            } catch (final Throwable t) {
                return handler.onThrownAsByte(t, value);
            }
        };
    }

    /**
     * @see BooleanToByteFunction1
     */
    @Override
    BooleanToByteFunction1 swallow();

    /**
     * @see AbstractThrowingToByteFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingToByteFunction1<V, ?, ?> compose(final Function1<? super V, ? extends Boolean> before) {
        return (AbstractThrowingToByteFunction1<V, ?, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractThrowingToByteFunction1
     */
    @Override
    <V> AbstractThrowingToByteFunction1<V, ?, ?> composeUnchecked(final Function1<? super V, ? extends Boolean> before);

    /**
     * @see AbstractThrowingBooleanFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingBooleanFunction1<V, ?, ?> andThen(final Function1<? super Byte, ? extends V> after) {
        return (AbstractThrowingBooleanFunction1<V, ?, ?>) AbstractThrowingToByteFunctionN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingBooleanFunction1
     */
    @Override
    <V> AbstractThrowingBooleanFunction1<V, ?, ?> andThenUnchecked(final Function1<? super Byte, ? extends V> after);

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
        byte onThrownAsByte(final Throwable t, final boolean value);

        @Override
        default byte onThrownAsByteUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsByte(t, (boolean) args[0]);
        }
    }
}
