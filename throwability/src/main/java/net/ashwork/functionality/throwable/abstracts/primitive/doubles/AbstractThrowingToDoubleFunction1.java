/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.primitive.doubles;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedResult;
import net.ashwork.functionality.primitive.doubles.ToDoubleFunction1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts one argument and produces a {@code double}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingToDoubleFunctionN}.
 * This is the {@code double}-producing primitive specialization of {@link AbstractThrowingFunction1}.
 * This is the throwing variation of {@link ToDoubleFunction1}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <T1> the type of the input to the function
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingFunction1
 * @see AbstractThrowingToDoubleFunctionN
 * @see ToDoubleFunction1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingToDoubleFunction1<T1, H extends AbstractThrowingToDoubleFunction1.Handler<T1>> extends AbstractThrowingToDoubleFunctionN<H>, InputChainableInput<T1>, UnboxedResult<AbstractThrowingFunction1<T1, Double, ?>> {

    /**
     * Applies this function to the given argument or throws a throwable.
     *
     * @param t1 the function argument
     * @return the function result
     * @throws Throwable if the function cannot be computed
     */
    double applyAsDouble(final T1 t1) throws Throwable;

    @SuppressWarnings("unchecked")
    @Override
    default double applyAllAsDoubleUnchecked(final Object... args) throws Throwable {
        return this.applyAsDouble((T1) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see AbstractThrowingFunction1
     */
    @Override
    AbstractThrowingFunction1<T1, Double, ?> boxResult();

    /**
     * @see ToDoubleFunction1
     */
    @Override
    default ToDoubleFunction1<T1> handle(final H handler) {
        return (final T1 t1) -> {
            try {
                return this.applyAsDouble(t1);
            } catch (final Throwable t) {
                return handler.onThrownAsDouble(t, t1);
            }
        };
    }

    /**
     * @see ToDoubleFunction1
     */
    @Override
    ToDoubleFunction1<T1> swallow();

    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingToDoubleFunction1<V, ?> compose(final Function1<? super V, ? extends T1> before) {
        return (AbstractThrowingToDoubleFunction1<V, ?>) InputChainableInput.super.compose(before);
    }

    @Override
    <V> AbstractThrowingToDoubleFunction1<V, ?> composeUnchecked(final Function1<? super V, ? extends T1> before);

    /**
     * @see AbstractThrowingFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingFunction1<T1, V, ?> andThen(final Function1<? super Double, ? extends V> after) {
        return (AbstractThrowingFunction1<T1, V, ?>) AbstractThrowingToDoubleFunctionN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingFunction1
     */
    @Override
    <V> AbstractThrowingFunction1<T1, V, ?> andThenUnchecked(final Function1<? super Double, ? extends V> after);

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     *
     * @param <T1> the type of the input to the function
     */
    @FunctionalInterface
    interface Handler<T1> extends AbstractThrowingToDoubleFunctionN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param t1 the function argument
         * @return the handled result
         */
        double onThrownAsDouble(final Throwable t, final T1 t1);

        @SuppressWarnings("unchecked")
        @Override
        default double onThrownAsDoubleUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsDouble(t, (T1) args[0]);
        }
    }
}
