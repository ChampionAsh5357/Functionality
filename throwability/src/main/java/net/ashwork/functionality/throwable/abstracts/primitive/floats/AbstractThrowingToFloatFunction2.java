/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.primitive.floats;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.UnboxedResult;
import net.ashwork.functionality.primitive.floats.ToFloatFunction2;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction2;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts two arguments and produces a {@code float}-valued result or throws a throwable.
 * This is the two-arity specialization of {@link AbstractThrowingToFloatFunctionN}.
 * This is the {@code float}-producing primitive specialization of {@link AbstractThrowingFunction2}.
 * This is the throwing variation of {@link ToFloatFunction2}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <T1> the type of the first argument to the function
 * @param <T2> the type of the second argument to the function
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingFunction2
 * @see AbstractThrowingToFloatFunctionN
 * @see ToFloatFunction2
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingToFloatFunction2<T1, T2, H extends AbstractThrowingToFloatFunction2.Handler<T1, T2>> extends AbstractThrowingToFloatFunctionN<H>, UnboxedResult<AbstractThrowingFunction2<T1, T2, Float, ?>> {

    /**
     * Applies this function to the given argument or throws a throwable.
     *
     * @param t1 the first function argument
     * @param t2 the second function argument
     * @return the function result
     * @throws Throwable if the function cannot be computed
     */
    float applyAsFloat(final T1 t1, final T2 t2) throws Throwable;

    @SuppressWarnings("unchecked")
    @Override
    default float applyAllAsFloatUnchecked(final Object... args) throws Throwable {
        return this.applyAsFloat((T1) args[0], (T2) args[1]);
    }

    @Override
    default int arity() {
        return 2;
    }

    /**
     * @see AbstractThrowingFunction2
     */
    @Override
    AbstractThrowingFunction2<T1, T2, Float, ?> boxResult();

    /**
     * @see ToFloatFunction2
     */
    @Override
    default ToFloatFunction2<T1, T2> handle(final H handler) {
        return (final T1 t1, final T2 t2) -> {
            try {
                return this.applyAsFloat(t1, t2);
            } catch (final Throwable t) {
                return handler.onThrownAsFloat(t, t1, t2);
            }
        };
    }

    /**
     * @see ToFloatFunction2
     */
    @Override
    ToFloatFunction2<T1, T2> swallow();

    /**
     * @see AbstractThrowingFunction2
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingFunction2<T1, T2, V, ?> andThen(final Function1<? super Float, ? extends V> after) {
        return (AbstractThrowingFunction2<T1, T2, V, ?>) AbstractThrowingToFloatFunctionN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingFunction2
     */
    @Override
    <V> AbstractThrowingFunction2<T1, T2, V, ?> andThenUnchecked(final Function1<? super Float, ? extends V> after);

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     *
     * @param <T1> the type of the first argument to the function
     * @param <T2> the type of the second argument to the function
     */
    @FunctionalInterface
    interface Handler<T1, T2> extends AbstractThrowingToFloatFunctionN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param t1 the first function argument
         * @param t2 the second function argument
         * @return the handled result
         */
        float onThrownAsFloat(final Throwable t, final T1 t1, final T2 t2);

        @SuppressWarnings("unchecked")
        @Override
        default float onThrownAsFloatUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsFloat(t, (T1) args[0], (T2) args[1]);
        }
    }
}
