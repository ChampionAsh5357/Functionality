/*
 * Consumability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.consumer.primitive.doubles;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.consumer.Consumer1;
import net.ashwork.functionality.consumer.ConsumerN;
import net.ashwork.functionality.consumer.abstracts.AbstractDoubleConsumer1;

import java.util.function.DoubleConsumer;

/**
 * Represents an operation that accepts a {@code double}-valued argument and returns no result.
 * This is the one-arity specialization of {@link ConsumerN}.
 * This is the non-producing specialization of {@link Function1}.
 * This is the {@code double}-consuming primitive specialization of {@link Consumer1}.
 *
 * <p>This is a functional interface whose functional method is {@link #accept(double)}.
 *
 * @see ConsumerN
 * @see Function1
 * @see Consumer1
 * @since 1.0.0
 */
@FunctionalInterface
public interface DoubleConsumer1 extends AbstractDoubleConsumer1<Consumer1<Double>, DoubleConsumer1> {

    /**
     * Creates an instance of this object from its {@link DoubleConsumer} variant.
     *
     * @param consumer the variant of this object
     * @return an instance of this object
     *
     * @see DoubleConsumer
     */
    static DoubleConsumer1 fromVariant(final DoubleConsumer consumer) {
        return consumer::accept;
    }

    /**
     * @see Consumer1
     */
    @Override
    default Consumer1<Double> boxInput() {
        return this::accept;
    }

    @Override
    default DoubleConsumer1 andThen(final DoubleConsumer1 after) {
        return (DoubleConsumer1) AbstractDoubleConsumer1.super.andThen(after);
    }

    @Override
    default DoubleConsumer1 andThenUnchecked(final DoubleConsumer1 after) {
        return (final double value) -> {
            this.accept(value);
            after.accept(value);
        };
    }
}
