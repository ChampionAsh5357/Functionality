/*
 * Consumability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.consumer.primitive.longs;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.consumer.Consumer1;
import net.ashwork.functionality.consumer.ConsumerN;
import net.ashwork.functionality.consumer.abstracts.longs.AbstractLongConsumer1;

import java.util.function.LongConsumer;

/**
 * Represents an operation that accepts a {@code long}-valued argument and returns no result.
 * This is the one-arity specialization of {@link ConsumerN}.
 * This is the non-producing specialization of {@link Function1}.
 * This is the {@code long}-consuming primitive specialization of {@link Consumer1}.
 *
 * <p>This is a functional interface whose functional method is {@link #accept(long)}.
 *
 * @see ConsumerN
 * @see Function1
 * @see Consumer1
 * @since 1.0.0
 */
@FunctionalInterface
public interface LongConsumer1 extends AbstractLongConsumer1<Consumer1<Long>, LongConsumer1> {

    /**
     * Creates an instance of this object from its {@link LongConsumer} variant.
     *
     * @param consumer the variant of this object
     * @return an instance of this object
     *
     * @see LongConsumer
     */
    static LongConsumer1 fromVariant(final LongConsumer consumer) {
        return consumer::accept;
    }
    
    /**
     * @see Consumer1
     */
    @Override
    default Consumer1<Long> boxInput() {
        return this::accept;
    }

    @Override
    default LongConsumer1 andThen(final LongConsumer1 after) {
        return (LongConsumer1) AbstractLongConsumer1.super.andThen(after);
    }

    @Override
    default LongConsumer1 andThenUnchecked(final LongConsumer1 after) {
        return (final long value) -> {
            this.accept(value);
            after.accept(value);
        };
    }
}
