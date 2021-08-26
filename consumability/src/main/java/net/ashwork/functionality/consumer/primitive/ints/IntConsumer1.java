/*
 * Consumability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.consumer.primitive.ints;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.consumer.Consumer1;
import net.ashwork.functionality.consumer.ConsumerN;
import net.ashwork.functionality.consumer.abstracts.primitive.ints.AbstractIntConsumer1;

import java.util.function.IntConsumer;

/**
 * Represents an operation that accepts an {@code int}-valued argument and returns no result.
 * This is the one-arity specialization of {@link ConsumerN}.
 * This is the non-producing specialization of {@link Function1}.
 * This is the {@code int}-consuming primitive specialization of {@link Consumer1}.
 *
 * <p>This is a functional interface whose functional method is {@link #accept(int)}.
 *
 * @see ConsumerN
 * @see Function1
 * @see Consumer1
 * @since 1.0.0
 */
@FunctionalInterface
public interface IntConsumer1 extends AbstractIntConsumer1<IntConsumer1> {

    /**
     * Creates an instance of this object from its {@link IntConsumer} variant.
     *
     * @param consumer the variant of this object
     * @return an instance of this object
     *
     * @see IntConsumer
     */
    static IntConsumer1 fromVariant(final IntConsumer consumer) {
        return consumer::accept;
    }

    /**
     * @see Consumer1
     */
    @Override
    default Consumer1<Integer> boxInput() {
        return this::accept;
    }

    @Override
    default IntConsumer1 andThen(final IntConsumer1 after) {
        return (IntConsumer1) AbstractIntConsumer1.super.andThen(after);
    }

    @Override
    default IntConsumer1 andThenUnchecked(final IntConsumer1 after) {
        return (final int value) -> {
            this.accept(value);
            after.accept(value);
        };
    }
}
