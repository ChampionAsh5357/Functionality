/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.abstracts.consumer;

import net.ashwork.functionality.FunctionN;
import net.ashwork.functionality.consumer.abstracts.AbstractConsumerN;
import net.ashwork.functionality.consumer.partial.ConsumerChainableConsumer;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunctionN;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents an operation that accepts {@code n} arguments and returns no result or throws a throwable.
 * All throwing consumers are derived from this {@code n}-arity specialization.
 * This is the non-producing specialization of {@link AbstractThrowingFunctionN}.
 * This is the throwing variation of {@link AbstractConsumerN}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <H> the type of the handler to safely call the consumer
 * @param <C> the type of this consumer
 *
 * @see AbstractThrowingFunctionN
 * @see AbstractConsumerN
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractThrowingConsumerN<H extends AbstractThrowingConsumerN.Handler, C extends AbstractThrowingConsumerN<H, C>> extends AbstractThrowingFunctionN<Void, H>, ConsumerChainableConsumer<C> {

    /**
     * Performs this operation on the given arguments or throws a throwable. Makes no assumptions
     * of the arguments passed in and whether the operation will
     * perform successfully.
     *
     * @param args the input arguments
     */
    void acceptAllUnchecked(final Object... args) throws Throwable;

    @Override
    default Void applyAllUnchecked(final Object... args) throws Throwable {
        this.acceptAllUnchecked(args);
        return null;
    }

    /**
     * Performs this operation on the given arguments or throws a throwable. If the number of
     * arguments do not match the {@link #arity()} of this consumer,
     * an exception will be thrown.
     *
     * @param args the number of arguments of the consumer
     * @throws FunctionN.FunctionSizeException if the number of arguments of the
     *                               consumer is not equal to its arity
     */
    default void sizedAcceptAllUnchecked(final Object... args) throws Throwable {
        this.acceptAllUnchecked(FunctionN.checkSize(this.arity(), args));
    }

    /**
     * @see AbstractConsumerN
     */
    @Override
    default AbstractConsumerN<?> handle(final H handler) {
        return (AbstractConsumerN<?>) AbstractThrowingFunctionN.super.handle(handler);
    }

    /**
     * @see AbstractConsumerN
     */
    @Override
    AbstractConsumerN<?> swallow();

    @Override
    default AbstractThrowingConsumerN<H, C> andThen(final C after) {
        return (AbstractThrowingConsumerN<H, C>) ConsumerChainableConsumer.super.andThen(after);
    }

    @Override
    AbstractThrowingConsumerN<H, C> andThenUnchecked(final C after);

    /**
     * Represents a handler that takes in the outer throwable's parameters and
     * the throwable and operates safely.
     */
    @FunctionalInterface
    interface Handler extends AbstractThrowingFunctionN.Handler<Void> {

        /**
         * Handles a throwable thrown by the outer throwable and operates safely.
         * Makes no assumptions of the arguments passed in and whether
         * the operation performs successfully.
         * This should never throw an exception.
         *
         * @param t the thrown throwable
         * @param args the function arguments
         */
        void acceptThrownUnchecked(final Throwable t, final Object... args);

        @Override
        default Void onThrownUnchecked(final Throwable t, final Object... args) {
            this.acceptThrownUnchecked(t, args);
            return null;
        }
    }
}
