/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.primitive.combined;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.InputChainableInput;
import net.ashwork.functionality.partial.UnboxedAll;
import net.ashwork.functionality.primitive.combined.LongToFloatFunction1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.floats.AbstractThrowingToFloatFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.floats.AbstractThrowingToFloatFunctionN;
import net.ashwork.functionality.throwable.abstracts.primitive.longs.AbstractThrowingLongFunction1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts a {@code long}-valued argument and produces a {@code float}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingToFloatFunctionN}.
 * This is the {@code long}-consuming primitive specialization of {@link AbstractThrowingToFloatFunction1}.
 * This is the {@code float}-producing primitive specialization of {@link AbstractThrowingLongFunction1}.
 * This is the throwing variation of {@link LongToFloatFunction1}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingLongFunction1
 * @see AbstractThrowingToFloatFunction1
 * @see AbstractThrowingToFloatFunctionN
 * @see LongToFloatFunction1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingLongToFloatFunction1<H extends AbstractThrowingLongToFloatFunction1.Handler> extends AbstractThrowingToFloatFunctionN<H>, InputChainableInput<Long>, UnboxedAll<AbstractThrowingFunction1<Long, Float, ?>, AbstractThrowingToFloatFunction1<Long, ?>, AbstractThrowingLongFunction1<Float, ?>> {

    /**
     * Applies this function to the given argument or throws a throwable.
     *
     * @param value the function argument
     * @return the function result
     */
    float applyAsFloat(final long value) throws Throwable;

    @Override
    default float applyAllAsFloatUnchecked(final Object... args) throws Throwable {
        return this.applyAsFloat((long) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see AbstractThrowingFunction1
     */
    @Override
    AbstractThrowingFunction1<Long, Float, ?> box();

    /**
     * @see AbstractThrowingToFloatFunction1
     */
    @Override
    AbstractThrowingToFloatFunction1<Long, ?> boxInput();

    /**
     * @see AbstractThrowingLongFunction1
     */
    @Override
    AbstractThrowingLongFunction1<Float, ?> boxResult();

    /**
     * @see LongToFloatFunction1
     */
    @Override
    default LongToFloatFunction1 handle(final H handler) {
        return (final long value) -> {
            try {
                return this.applyAsFloat(value);
            } catch (final Throwable t) {
                return handler.onThrownAsFloat(t, value);
            }
        };
    }

    /**
     * @see LongToFloatFunction1
     */
    @Override
    LongToFloatFunction1 swallow();

    /**
     * @see AbstractThrowingToFloatFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingToFloatFunction1<V, ?> compose(final Function1<? super V, ? extends Long> before) {
        return (AbstractThrowingToFloatFunction1<V, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractThrowingToFloatFunction1
     */
    @Override
    <V> AbstractThrowingToFloatFunction1<V, ?> composeUnchecked(final Function1<? super V, ? extends Long> before);

    /**
     * @see AbstractThrowingLongFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingLongFunction1<V, ?> andThen(final Function1<? super Float, ? extends V> after) {
        return (AbstractThrowingLongFunction1<V, ?>) AbstractThrowingToFloatFunctionN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingLongFunction1
     */
    @Override
    <V> AbstractThrowingLongFunction1<V, ?> andThenUnchecked(final Function1<? super Float, ? extends V> after);

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
         * @param value the function argument
         * @return the handled result
         */
        float onThrownAsFloat(final Throwable t, final long value);

        @Override
        default float onThrownAsFloatUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsFloat(t, (long) args[0]);
        }
    }
}
