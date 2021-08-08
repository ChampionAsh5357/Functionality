/*
 * Consumability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.consumer.primitive.chars;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.consumer.Consumer1;
import net.ashwork.functionality.consumer.ConsumerN;
import net.ashwork.functionality.consumer.abstracts.AbstractCharConsumer1;

/**
 * Represents an operation that accepts a {@code char}-valued argument and returns no result.
 * This is the one-arity specialization of {@link ConsumerN}.
 * This is the non-producing specialization of {@link Function1}.
 * This is the {@code char}-consuming primitive specialization of {@link Consumer1}.
 *
 * <p>This is a functional interface whose functional method is {@link #accept(char)}.
 *
 * @see ConsumerN
 * @see Function1
 * @see Consumer1
 * @since 1.0.0
 */
@FunctionalInterface
public interface CharConsumer1 extends AbstractCharConsumer1<Consumer1<Character>, CharConsumer1> {

    /**
     * @see Consumer1
     */
    @Override
    default Consumer1<Character> boxInput() {
        return this::accept;
    }

    @Override
    default CharConsumer1 andThen(final CharConsumer1 after) {
        return (CharConsumer1) AbstractCharConsumer1.super.andThen(after);
    }

    @Override
    default CharConsumer1 andThenUnchecked(final CharConsumer1 after) {
        return (final char value) -> {
            this.accept(value);
            after.accept(value);
        };
    }
}
