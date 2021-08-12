/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.primitive.floats;

import net.ashwork.functionality.partial.UnboxedResult;
import net.ashwork.functionality.primitive.floats.ToFloatFunction0;
import net.ashwork.functionality.throwable.ThrowingFunction0;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction0;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts no arguments and produces a {@code float}-valued result or throws a throwable.
 * This is the zero-arity specialization of {@link AbstractThrowingToFloatFunctionN}.
 * This is the {@code float}-producing primitive specialization of {@link AbstractThrowingFunction0}.
 * This is the throwing variation of {@link ToFloatFunction0}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingFunction0
 * @see AbstractThrowingToFloatFunctionN
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingToFloatFunction0<H extends AbstractThrowingToFloatFunction0.Handler> extends AbstractThrowingToFloatFunctionN<H>, UnboxedResult<ThrowingFunction0<Float>> {

    /**
     * Applies this function or throws a throwable.
     *
     * @return the function result
     */
    float applyAsFloat() throws Throwable;

    @Override
    default float applyAllAsFloatUnchecked(final Object... args) throws Throwable {
        return this.applyAsFloat();
    }

    @Override
    default int arity() {
        return 0;
    }

    /**
     * @see ThrowingFunction0
     */
    @Override
    default ThrowingFunction0<Float> boxResult() {
        return this::applyAsFloat;
    }

    /**
     * @see ToFloatFunction0
     */
    @Override
    default ToFloatFunction0 handle(final H handler) {
        return () -> {
            try {
                return this.applyAsFloat();
            } catch (final Throwable t) {
                return handler.onThrownAsFloat(t);
            }
        };
    }

    /**
     * @see ToFloatFunction0
     */
    @Override
    ToFloatFunction0 swallow();

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     */
    @FunctionalInterface
    interface Handler extends AbstractThrowingToFloatFunctionN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @return the handled result
         */
        float onThrownAsFloat(final Throwable t);

        @Override
        default float onThrownAsFloatUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsFloat(t);
        }
    }
}
