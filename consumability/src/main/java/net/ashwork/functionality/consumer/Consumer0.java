/*
 * Consumability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.consumer;

import net.ashwork.functionality.Function0;
import net.ashwork.functionality.consumer.abstracts.AbstractConsumer0;

/**
 * Represents an operation that accepts no arguments and returns no result.
 * This is the zero-arity specialization of {@link ConsumerN}.
 * This is the non-producing specialization of {@link Function0}.
 *
 * <p>This is a functional interface whose functional method is {@link #accept()}.
 *
 * @see ConsumerN
 * @see Function0
 * @since 1.0.0
 */
@FunctionalInterface
public interface Consumer0 extends AbstractConsumer0<Consumer0> {

    /**
     * Creates an instance of this object from its {@link Runnable} variant.
     *
     * @param runnable the variant of this object
     * @return an instance of this object
     *
     * @see Runnable
     */
    static Consumer0 fromVariant(final Runnable runnable) {
        return runnable::run;
    }

    @Override
    default Consumer0 andThen(final Consumer0 after) {
        return (Consumer0) AbstractConsumer0.super.andThen(after);
    }

    @Override
    default Consumer0 andThenUnchecked(final Consumer0 after) {
        return () -> {
            this.accept();
            after.accept();
        };
    }
}
