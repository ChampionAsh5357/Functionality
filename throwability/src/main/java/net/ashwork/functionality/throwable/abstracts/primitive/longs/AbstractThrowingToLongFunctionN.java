/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.primitive.longs;

import net.ashwork.functionality.primitive.longs.ToLongFunctionN;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunctionN;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts {@code n} arguments and produces a {@code long}-valued result or throws a throwable.
 * This is the {@code long}-producing primitive specialization for {@link AbstractThrowingFunctionN}.
 * All {@code long}-producing functions are derived from this {@code n}-arity specialization.
 * This is the throwing variation of {@link ToLongFunctionN}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingFunctionN
 * @see ToLongFunctionN
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingToLongFunctionN<H extends AbstractThrowingToLongFunctionN.Handler> extends AbstractThrowingFunctionN<Long, H> {

    /**
     * Applies this function to the given arguments or throws a throwable. Makes no assumptions
     * of the arguments passed in and whether a value will compute
     * successfully.
     *
     * @param args the function arguments
     * @return the function result
     * @throws Throwable if the function cannot be computed
     */
    long applyAllAsLongUnchecked(final Object... args) throws Throwable;

    @Override
    default Long applyAllUnchecked(final Object... args) throws Throwable {
        return this.applyAllAsLongUnchecked(args);
    }

    /**
     * @see ToLongFunctionN
     */
    @Override
    default ToLongFunctionN handle(final H handler) {
        return (final Object[] args) -> {
            try {
                return this.applyAllAsLongUnchecked(args);
            } catch (final Throwable t) {
                return handler.onThrownAsLongUnchecked(t, args);
            }
        };
    }

    /**
     * @see ToLongFunctionN
     */
    @Override
    ToLongFunctionN swallow();

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     */
    @FunctionalInterface
    interface Handler extends AbstractThrowingFunctionN.Handler<Long> {

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
        long onThrownAsLongUnchecked(final Throwable t, final Object... args);

        @Override
        default Long onThrownUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsLongUnchecked(t, args);
        }
    }
}
