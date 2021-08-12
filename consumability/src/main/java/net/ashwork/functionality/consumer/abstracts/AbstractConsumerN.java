/*
 * Consumability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.consumer.abstracts;

import net.ashwork.functionality.FunctionN;
import net.ashwork.functionality.consumer.partial.ConsumerChainableConsumer;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents an operation that accepts {@code n} arguments and returns no result.
 * All consumers are derived from this {@code n}-arity specialization.
 * This is the non-producing specialization of {@link FunctionN}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <C> the type of this consumer
 *
 * @see FunctionN
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractConsumerN<C extends AbstractConsumerN<C>> extends FunctionN<Void>, ConsumerChainableConsumer<C> {

    /**
     * Performs this operation on the given arguments. Makes no assumptions
     * of the arguments passed in and whether the operation will
     * perform successfully.
     *
     * @param args the input arguments
     */
    void acceptAllUnchecked(final Object... args);

    /**
     * Performs this operation on the given arguments. If the number of
     * arguments do not match the {@link #arity()} of this consumer,
     * an exception will be thrown.
     *
     * @param args the number of arguments of the consumer
     * @throws FunctionSizeException if the number of arguments of the
     *                               consumer is not equal to its arity
     */
    default void sizedAcceptAllUnchecked(final Object... args) {
        this.acceptAllUnchecked(FunctionN.checkSize(this.arity(), args));
    }

    @Override
    default Void applyAllUnchecked(final Object... args) {
        this.acceptAllUnchecked(args);
        return null;
    }

    @Override
    default AbstractConsumerN<C> andThen(final C after) {
        return (AbstractConsumerN<C>) ConsumerChainableConsumer.super.andThen(after);
    }

    @Override
    default AbstractConsumerN<C> andThenUnchecked(final C after) {
        return (final Object[] args) -> {
            this.acceptAllUnchecked(args);
            after.acceptAllUnchecked(args);
        };
    }
}
