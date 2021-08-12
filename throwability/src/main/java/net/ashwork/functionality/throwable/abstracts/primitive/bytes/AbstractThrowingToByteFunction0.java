/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.primitive.bytes;

import net.ashwork.functionality.partial.UnboxedResult;
import net.ashwork.functionality.primitive.bytes.ToByteFunction0;
import net.ashwork.functionality.throwable.ThrowingFunction0;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction0;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts no arguments and produces a {@code byte}-valued result or throws a throwable.
 * This is the zero-arity specialization of {@link AbstractThrowingToByteFunctionN}.
 * This is the {@code byte}-producing primitive specialization of {@link AbstractThrowingFunction0}.
 * This is the throwing variation of {@link ToByteFunction0}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingFunction0
 * @see AbstractThrowingToByteFunctionN
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingToByteFunction0<H extends AbstractThrowingToByteFunction0.Handler> extends AbstractThrowingToByteFunctionN<H>, UnboxedResult<ThrowingFunction0<Byte>> {

    /**
     * Applies this function or throws a throwable.
     *
     * @return the function result
     */
    byte applyAsByte() throws Throwable;

    @Override
    default byte applyAllAsByteUnchecked(final Object... args) throws Throwable {
        return this.applyAsByte();
    }

    @Override
    default int arity() {
        return 0;
    }

    /**
     * @see ThrowingFunction0
     */
    @Override
    default ThrowingFunction0<Byte> boxResult() {
        return this::applyAsByte;
    }

    /**
     * @see ToByteFunction0
     */
    @Override
    default ToByteFunction0 handle(final H handler) {
        return () -> {
            try {
                return this.applyAsByte();
            } catch (final Throwable t) {
                return handler.onThrownAsByte(t);
            }
        };
    }

    /**
     * @see ToByteFunction0
     */
    @Override
    ToByteFunction0 swallow();

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
         * @return the handled result
         */
        byte onThrownAsByte(final Throwable t);

        @Override
        default byte onThrownAsByteUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsByte(t);
        }
    }
}
