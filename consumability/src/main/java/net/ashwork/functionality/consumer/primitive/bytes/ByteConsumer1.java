/*
 * Consumability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.consumer.primitive.bytes;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.consumer.Consumer1;
import net.ashwork.functionality.consumer.ConsumerN;
import net.ashwork.functionality.consumer.abstracts.AbstractByteConsumer1;

/**
 * Represents an operation that accepts a {@code byte}-valued argument and returns no result.
 * This is the one-arity specialization of {@link ConsumerN}.
 * This is the non-producing specialization of {@link Function1}.
 * This is the {@code byte}-consuming primitive specialization of {@link Consumer1}.
 *
 * <p>This is a functional interface whose functional method is {@link #accept(byte)}.
 *
 * @see ConsumerN
 * @see Function1
 * @see Consumer1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ByteConsumer1 extends AbstractByteConsumer1<Consumer1<Byte>, ByteConsumer1> {

    /**
     * @see Consumer1
     */
    @Override
    default Consumer1<Byte> boxInput() {
        return this::accept;
    }

    @Override
    default ByteConsumer1 andThen(final ByteConsumer1 after) {
        return (ByteConsumer1) AbstractByteConsumer1.super.andThen(after);
    }

    @Override
    default ByteConsumer1 andThenUnchecked(final ByteConsumer1 after) {
        return (final byte value) -> {
            this.accept(value);
            after.accept(value);
        };
    }
}