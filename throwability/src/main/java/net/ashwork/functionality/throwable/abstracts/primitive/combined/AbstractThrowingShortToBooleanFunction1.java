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
import net.ashwork.functionality.primitive.combined.ShortToBooleanFunction1;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.booleans.AbstractThrowingToBooleanFunction1;
import net.ashwork.functionality.throwable.abstracts.primitive.booleans.AbstractThrowingToBooleanFunctionN;
import net.ashwork.functionality.throwable.abstracts.primitive.shorts.AbstractThrowingShortFunction1;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents a function that accepts a {@code short}-valued argument and produces a {@code boolean}-valued result or throws a throwable.
 * This is the one-arity specialization of {@link AbstractThrowingToBooleanFunctionN}.
 * This is the {@code short}-consuming primitive specialization of {@link AbstractThrowingToBooleanFunction1}.
 * This is the {@code boolean}-producing primitive specialization of {@link AbstractThrowingShortFunction1}.
 * This is the throwing variation of {@link ShortToBooleanFunction1}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the function
 *
 * @see AbstractThrowingShortFunction1
 * @see AbstractThrowingToBooleanFunction1
 * @see AbstractThrowingToBooleanFunctionN
 * @see ShortToBooleanFunction1
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingShortToBooleanFunction1<H extends AbstractThrowingShortToBooleanFunction1.Handler> extends AbstractThrowingToBooleanFunctionN<H>, InputChainableInput<Short>, UnboxedAll<AbstractThrowingFunction1<Short, Boolean, ?>, AbstractThrowingToBooleanFunction1<Short, ?>, AbstractThrowingShortFunction1<Boolean, ?>> {

    /**
     * Applies this function to the given argument or throws a throwable.
     *
     * @param value the function argument
     * @return the function result
     */
    boolean applyAsBoolean(final short value) throws Throwable;

    @Override
    default boolean applyAllAsBooleanUnchecked(final Object... args) throws Throwable {
        return this.applyAsBoolean((short) args[0]);
    }

    @Override
    default int arity() {
        return 1;
    }

    /**
     * @see AbstractThrowingFunction1
     */
    @Override
    AbstractThrowingFunction1<Short, Boolean, ?> box();

    /**
     * @see AbstractThrowingToBooleanFunction1
     */
    @Override
    AbstractThrowingToBooleanFunction1<Short, ?> boxInput();

    /**
     * @see AbstractThrowingShortFunction1
     */
    @Override
    AbstractThrowingShortFunction1<Boolean, ?> boxResult();

    /**
     * @see ShortToBooleanFunction1
     */
    @Override
    default ShortToBooleanFunction1 handle(final H handler) {
        return (final short value) -> {
            try {
                return this.applyAsBoolean(value);
            } catch (final Throwable t) {
                return handler.onThrownAsBoolean(t, value);
            }
        };
    }

    /**
     * @see ShortToBooleanFunction1
     */
    @Override
    ShortToBooleanFunction1 swallow();

    /**
     * @see AbstractThrowingToBooleanFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingToBooleanFunction1<V, ?> compose(final Function1<? super V, ? extends Short> before) {
        return (AbstractThrowingToBooleanFunction1<V, ?>) InputChainableInput.super.compose(before);
    }

    /**
     * @see AbstractThrowingToBooleanFunction1
     */
    @Override
    <V> AbstractThrowingToBooleanFunction1<V, ?> composeUnchecked(final Function1<? super V, ? extends Short> before);

    /**
     * @see AbstractThrowingShortFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> AbstractThrowingShortFunction1<V, ?> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (AbstractThrowingShortFunction1<V, ?>) AbstractThrowingToBooleanFunctionN.super.andThen(after);
    }

    /**
     * @see AbstractThrowingShortFunction1
     */
    @Override
    <V> AbstractThrowingShortFunction1<V, ?> andThenUnchecked(final Function1<? super Boolean, ? extends V> after);

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and returns a result safely.
     */
    @FunctionalInterface
    interface Handler extends AbstractThrowingToBooleanFunctionN.Handler {

        /**
         * Handles a throwable thrown by the outer throwable and returns safely.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param value the function argument
         * @return the handled result
         */
        boolean onThrownAsBoolean(final Throwable t, final short value);

        @Override
        default boolean onThrownAsBooleanUnchecked(final Throwable t, final Object... args) {
            return this.onThrownAsBoolean(t, (short) args[0]);
        }
    }
}
