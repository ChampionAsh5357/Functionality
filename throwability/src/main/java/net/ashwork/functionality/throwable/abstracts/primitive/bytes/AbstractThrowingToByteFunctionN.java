/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.primitive.bytes;

import net.ashwork.functionality.primitive.bytes.ToByteFunctionN;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunctionN;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts {@code n} arguments and produces a {@code byte}-valued result or throws a throwable.
 * This is the {@code byte}-producing primitive specialization for {@link AbstractThrowingFunctionN}.
 * All {@code byte}-producing functions are derived from this {@code n}-arity specialization.
 * This is the throwing variation of {@link ToByteFunctionN}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingFunctionN
 * @see ToByteFunctionN
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingToByteFunctionN<H extends AbstractThrowingToByteFunctionN.Handler> extends AbstractThrowingFunctionN<Byte, H> {

    /**
     * Applies this function to the given arguments or throws a throwable. Makes no assumptions
     * of the arguments passed in and whether a value will compute
     * successfully.
     *
     * @param args the function arguments
     * @return the function result
     * @throws Throwable if the function cannot be computed
     */
    byte applyAllAsByteUnchecked(final Object... args) throws Throwable;

    @Override
    default Byte applyAllUnchecked(final Object... args) throws Throwable {
        return this.applyAllAsByteUnchecked(args);
    }

    /**
     * @see ToByteFunctionN
     */
    @Override
    default ToByteFunctionN handle(final H handler) {
        return (final Object[] args) -> {
            try {
                return this.applyAllAsByteUnchecked(args);
            } catch (final Throwable t) {
                return handler.onThrownAsByteUnchecked(t, args);
            }
        };
    }

    /**
     * @see ToByteFunctionN
     */
    @Override
    ToByteFunctionN swallow();

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     */
    @FunctionalInterface
    interface Handler extends AbstractThrowingFunctionN.Handler<Byte> {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * Makes no assumptions of the arguments passed in and whether
         * a value will compute successfully.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param args the function arguments
         * @return the handled result
         */
        byte onThrownAsByteUnchecked(final Throwable t, final Object... args);

        @Override
        default Byte onThrownUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsByteUnchecked(t, args);
        }
    }
}
