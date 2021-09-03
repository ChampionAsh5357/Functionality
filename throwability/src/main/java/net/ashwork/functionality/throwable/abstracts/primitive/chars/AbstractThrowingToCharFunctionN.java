/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.primitive.chars;

import net.ashwork.functionality.primitive.chars.ToCharFunctionN;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunctionN;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts {@code n} arguments and produces a {@code char}-valued result or throws a throwable.
 * This is the {@code char}-producing primitive specialization for {@link AbstractThrowingFunctionN}.
 * All {@code char}-producing functions are derived from this {@code n}-arity specialization.
 * This is the throwing variation of {@link ToCharFunctionN}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingFunctionN
 * @see ToCharFunctionN
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingToCharFunctionN<H extends AbstractThrowingToCharFunctionN.Handler> extends AbstractThrowingFunctionN<Character, H> {

    /**
     * Applies this function to the given arguments or throws a throwable. Makes no assumptions
     * of the arguments passed in and whether a value will compute
     * successfully.
     *
     * @param args the function arguments
     * @return the function result
     * @throws Throwable if the function cannot be computed
     */
    char applyAllAsCharUnchecked(final Object... args) throws Throwable;

    @Override
    default Character applyAllUnchecked(final Object... args) throws Throwable {
        return this.applyAllAsCharUnchecked(args);
    }

    /**
     * @see ToCharFunctionN
     */
    @Override
    default ToCharFunctionN handle(final H handler) {
        return (final Object[] args) -> {
            try {
                return this.applyAllAsCharUnchecked(args);
            } catch (final Throwable t) {
                return handler.onThrownAsCharUnchecked(t, args);
            }
        };
    }

    /**
     * @see ToCharFunctionN
     */
    @Override
    ToCharFunctionN swallow();

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     */
    @FunctionalInterface
    interface Handler extends AbstractThrowingFunctionN.Handler<Character> {

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
        char onThrownAsCharUnchecked(final Throwable t, final Object... args);

        @Override
        default Character onThrownUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsCharUnchecked(t, args);
        }
    }
}
