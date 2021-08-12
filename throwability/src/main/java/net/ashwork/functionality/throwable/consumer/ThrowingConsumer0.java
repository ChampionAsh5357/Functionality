/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.consumer;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.consumer.Consumer0;
import net.ashwork.functionality.throwable.ThrowingFunction0;
import net.ashwork.functionality.throwable.abstracts.AbstractThrowingFunction0;
import net.ashwork.functionality.throwable.abstracts.consumer.AbstractThrowingConsumer0;
import net.ashwork.functionality.throwable.abstracts.consumer.AbstractThrowingConsumerN;

/**
 * Represents an operation that accepts no arguments and returns no result or throws a throwable.
 * This is the zero-arity specialization of {@link AbstractThrowingConsumerN}.
 * This is the non-producing specialization of {@link AbstractThrowingFunction0}.
 * This is the throwing variation of {@link Consumer0}.
 *
 * <p>This is a functional interface whose functional method is {@link #accept()}.
 *
 * @see AbstractThrowingConsumerN
 * @see AbstractThrowingFunction0
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingConsumer0 extends AbstractThrowingConsumer0<AbstractThrowingConsumer0.Handler, ThrowingConsumer0> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param consumer the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see Consumer0
     */
    static ThrowingConsumer0 from(final Consumer0 consumer) {
        return consumer::accept;
    }

    /**
     * @see Consumer0
     */
    @Override
    default Consumer0 handle(final Handler handler) {
        return () -> {
            try {
                this.accept();
            } catch (final Throwable t) {
                handler.acceptThrown(t);
            }
        };
    }

    /**
     * @see Consumer0
     */
    @Override
    default Consumer0 swallow() {
        return this.handle(t -> {});
    }

    @Override
    default ThrowingConsumer0 andThen(final ThrowingConsumer0 after) {
        return (ThrowingConsumer0) AbstractThrowingConsumer0.super.andThen(after);
    }

    @Override
    default ThrowingConsumer0 andThenUnchecked(final ThrowingConsumer0 after) {
        return () -> {
            this.accept();
            after.accept();
        };
    }

    /**
     * @see ThrowingFunction0
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction0<V> andThen(final Function1<? super Void, ? extends V> after) {
        return (ThrowingFunction0<V>) AbstractThrowingConsumer0.super.andThen(after);
    }

    /**
     * @see ThrowingFunction0
     */
    @Override
    default <V> ThrowingFunction0<V> andThenUnchecked(final Function1<? super Void, ? extends V> after) {
        return () -> {
            this.accept();
            return after.apply(null);
        };
    }
}
