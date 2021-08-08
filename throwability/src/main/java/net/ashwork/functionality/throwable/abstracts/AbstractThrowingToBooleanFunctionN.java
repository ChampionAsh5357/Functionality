/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts;

import net.ashwork.functionality.primitive.booleans.ToBooleanFunctionN;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts {@code n} arguments and produces a {@code boolean}-valued result or throws a throwable.
 * This is the {@code boolean}-producing primitive specialization for {@link AbstractThrowingFunctionN}.
 * All {@code boolean}-producing functions are derived from this {@code n}-arity specialization.
 * This is the throwing variation of {@link ToBooleanFunctionN}.
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
public interface AbstractThrowingToBooleanFunctionN<H extends AbstractThrowingToBooleanFunctionN.Handler> extends AbstractThrowingFunctionN<Boolean, H> {

    /**
     * Applies this function to the given arguments or throws a throwable. Makes no assumptions
     * of the arguments passed in and whether a value will compute
     * successfully.
     *
     * @param args the function arguments
     * @return the function result
     */
    boolean applyAllAsBooleanUnchecked(final Object... args) throws Throwable;

    @Override
    default Boolean applyAllUnchecked(final Object... args) throws Throwable {
        return this.applyAllAsBooleanUnchecked(args);
    }

    /**
     * @see ToBooleanFunctionN
     */
    @Override
    default ToBooleanFunctionN handle(final H handler) {
        return (final Object[] args) -> {
            try {
                return this.applyAllAsBooleanUnchecked(args);
            } catch (final Throwable t) {
                return handler.onThrownAsBooleanUnchecked(t, args);
            }
        };
    }

    /**
     * @see ToBooleanFunctionN
     */
    @Override
    ToBooleanFunctionN swallow();

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     */
    @FunctionalInterface
    interface Handler extends AbstractThrowingFunctionN.Handler<Boolean> {

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
        boolean onThrownAsBooleanUnchecked(final Throwable t, final Object... args);

        @Override
        default Boolean onThrownUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsBooleanUnchecked(t, args);
        }
    }
}
