/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.primitive.floats;

import net.ashwork.functionality.primitive.floats.ToFloatFunctionN;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunctionN;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts {@code n} arguments and produces a {@code float}-valued result or throws a throwable.
 * This is the {@code float}-producing primitive specialization for {@link AbstractThrowingFunctionN}.
 * All {@code float}-producing functions are derived from this {@code n}-arity specialization.
 * This is the throwing variation of {@link ToFloatFunctionN}.
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
public interface AbstractThrowingToFloatFunctionN<H extends AbstractThrowingToFloatFunctionN.Handler> extends AbstractThrowingFunctionN<Float, H> {

    /**
     * Applies this function to the given arguments or throws a throwable. Makes no assumptions
     * of the arguments passed in and whether a value will compute
     * successfully.
     *
     * @param args the function arguments
     * @return the function result
     */
    float applyAllAsFloatUnchecked(final Object... args) throws Throwable;

    @Override
    default Float applyAllUnchecked(final Object... args) throws Throwable {
        return this.applyAllAsFloatUnchecked(args);
    }

    /**
     * @see ToFloatFunctionN
     */
    @Override
    default ToFloatFunctionN handle(final H handler) {
        return (final Object[] args) -> {
            try {
                return this.applyAllAsFloatUnchecked(args);
            } catch (final Throwable t) {
                return handler.onThrownAsFloatUnchecked(t, args);
            }
        };
    }

    /**
     * @see ToFloatFunctionN
     */
    @Override
    ToFloatFunctionN swallow();

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     */
    @FunctionalInterface
    interface Handler extends AbstractThrowingFunctionN.Handler<Float> {

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
        float onThrownAsFloatUnchecked(final Throwable t, final Object... args);

        @Override
        default Float onThrownUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsFloatUnchecked(t, args);
        }
    }
}
