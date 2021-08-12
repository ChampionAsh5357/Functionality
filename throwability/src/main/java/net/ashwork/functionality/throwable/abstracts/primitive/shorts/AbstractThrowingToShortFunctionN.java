/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.primitive.shorts;

import net.ashwork.functionality.primitive.shorts.ToShortFunctionN;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunctionN;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts {@code n} arguments and produces a {@code short}-valued result or throws a throwable.
 * This is the {@code short}-producing primitive specialization for {@link AbstractThrowingFunctionN}.
 * All {@code short}-producing functions are derived from this {@code n}-arity specialization.
 * This is the throwing variation of {@link ToShortFunctionN}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingFunctionN
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingToShortFunctionN<H extends AbstractThrowingToShortFunctionN.Handler> extends AbstractThrowingFunctionN<Short, H> {

    /**
     * Applies this function to the given arguments or throws a throwable. Makes no assumptions
     * of the arguments passed in and whether a value will compute
     * successfully.
     *
     * @param args the function arguments
     * @return the function result
     */
    short applyAllAsShortUnchecked(final Object... args) throws Throwable;

    @Override
    default Short applyAllUnchecked(final Object... args) throws Throwable {
        return this.applyAllAsShortUnchecked(args);
    }

    /**
     * @see ToShortFunctionN
     */
    @Override
    default ToShortFunctionN handle(final H handler) {
        return (final Object[] args) -> {
            try {
                return this.applyAllAsShortUnchecked(args);
            } catch (final Throwable t) {
                return handler.onThrownAsShortUnchecked(t, args);
            }
        };
    }

    /**
     * @see ToShortFunctionN
     */
    @Override
    ToShortFunctionN swallow();

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     */
    @FunctionalInterface
    interface Handler extends AbstractThrowingFunctionN.Handler<Short> {

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
        short onThrownAsShortUnchecked(final Throwable t, final Object... args);

        @Override
        default Short onThrownUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsShortUnchecked(t, args);
        }
    }
}
