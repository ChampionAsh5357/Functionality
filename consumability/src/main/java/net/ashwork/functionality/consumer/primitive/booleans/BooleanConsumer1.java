/*
 * Consumability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.consumer.primitive.booleans;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.consumer.Consumer1;
import net.ashwork.functionality.consumer.ConsumerN;
import net.ashwork.functionality.consumer.abstracts.AbstractBooleanConsumer1;

/**
 * Represents an operation that accepts a {@code boolean}-valued argument and returns no result.
 * This is the one-arity specialization of {@link ConsumerN}.
 * This is the non-producing specialization of {@link Function1}.
 * This is the {@code boolean}-consuming primitive specialization of {@link Consumer1}.
 *
 * <p>This is a functional interface whose functional method is {@link #accept(boolean)}.
 *
 * @see ConsumerN
 * @see Function1
 * @see Consumer1
 * @since 1.0.0
 */
@FunctionalInterface
public interface BooleanConsumer1 extends AbstractBooleanConsumer1<Consumer1<Boolean>, BooleanConsumer1> {

    /**
     * @see Consumer1
     */
    @Override
    default Consumer1<Boolean> boxInput() {
        return this::accept;
    }

    @Override
    default BooleanConsumer1 andThen(final BooleanConsumer1 after) {
        return (BooleanConsumer1) AbstractBooleanConsumer1.super.andThen(after);
    }

    @Override
    default BooleanConsumer1 andThenUnchecked(final BooleanConsumer1 after) {
        return (final boolean value) -> {
            this.accept(value);
            after.accept(value);
        };
    }
}
