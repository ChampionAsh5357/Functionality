/*
 * Consumability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.consumer.primitive.floats;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.consumer.Consumer1;
import net.ashwork.functionality.consumer.ConsumerN;
import net.ashwork.functionality.consumer.abstracts.AbstractFloatConsumer1;

/**
 * Represents an operation that accepts a {@code float}-valued argument and returns no result.
 * This is the one-arity specialization of {@link ConsumerN}.
 * This is the non-producing specialization of {@link Function1}.
 * This is the {@code float}-consuming primitive specialization of {@link Consumer1}.
 *
 * <p>This is a functional interface whose functional method is {@link #accept(float)}.
 *
 * @see ConsumerN
 * @see Function1
 * @see Consumer1
 * @since 1.0.0
 */
@FunctionalInterface
public interface FloatConsumer1 extends AbstractFloatConsumer1<Consumer1<Float>, FloatConsumer1> {

    /**
     * @see Consumer1
     */
    @Override
    default Consumer1<Float> boxInput() {
        return this::accept;
    }

    @Override
    default FloatConsumer1 andThen(final FloatConsumer1 after) {
        return (FloatConsumer1) AbstractFloatConsumer1.super.andThen(after);
    }

    @Override
    default FloatConsumer1 andThenUnchecked(final FloatConsumer1 after) {
        return (final float value) -> {
            this.accept(value);
            after.accept(value);
        };
    }
}
