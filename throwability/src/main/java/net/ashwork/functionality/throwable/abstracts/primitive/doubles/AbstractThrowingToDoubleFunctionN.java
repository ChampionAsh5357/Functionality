/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.primitive.doubles;

import net.ashwork.functionality.primitive.doubles.ToDoubleFunctionN;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunctionN;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts {@code n} arguments and produces a {@code double}-valued result or throws a throwable.
 * This is the {@code double}-producing primitive specialization for {@link AbstractThrowingFunctionN}.
 * All {@code double}-producing functions are derived from this {@code n}-arity specialization.
 * This is the throwing variation of {@link ToDoubleFunctionN}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingFunctionN
 * @see ToDoubleFunctionN
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingToDoubleFunctionN<H extends AbstractThrowingToDoubleFunctionN.Handler> extends AbstractThrowingFunctionN<Double, H> {

    /**
     * Applies this function to the given arguments or throws a throwable. Makes no assumptions
     * of the arguments passed in and whether a value will compute
     * successfully.
     *
     * @param args the function arguments
     * @return the function result
     */
    double applyAllAsDoubleUnchecked(final Object... args) throws Throwable;

    @Override
    default Double applyAllUnchecked(final Object... args) throws Throwable {
        return this.applyAllAsDoubleUnchecked(args);
    }

    /**
     * @see ToDoubleFunctionN
     */
    @Override
    default ToDoubleFunctionN handle(final H handler) {
        return (final Object[] args) -> {
            try {
                return this.applyAllAsDoubleUnchecked(args);
            } catch (final Throwable t) {
                return handler.onThrownAsDoubleUnchecked(t, args);
            }
        };
    }

    /**
     * @see ToDoubleFunctionN
     */
    @Override
    ToDoubleFunctionN swallow();

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     */
    @FunctionalInterface
    interface Handler extends AbstractThrowingFunctionN.Handler<Double> {

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
        double onThrownAsDoubleUnchecked(final Throwable t, final Object... args);

        @Override
        default Double onThrownUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsDoubleUnchecked(t, args);
        }
    }
}
