/*
 * Consumability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.consumer;

import net.ashwork.functionality.FunctionN;
import net.ashwork.functionality.consumer.abstracts.AbstractConsumerN;

/**
 * Represents an operation that accepts {@code n} arguments and returns no result.
 * All consumers are derived from this {@code n}-arity specialization.
 * This is the non-producing specialization of {@link FunctionN}.
 *
 * <p>This is a functional interface whose functional method is {@link #acceptAllUnchecked(Object...)}.
 *
 * @see FunctionN
 * @since 1.0.0
 */
@FunctionalInterface
public interface ConsumerN extends AbstractConsumerN<ConsumerN> {

    @Override
    default ConsumerN andThen(final ConsumerN after) {
        return (ConsumerN) AbstractConsumerN.super.andThen(after);
    }

    @Override
    default ConsumerN andThenUnchecked(final ConsumerN after) {
        return (final Object[] args) -> {
            this.sizedAcceptAllUnchecked(args);
            after.sizedAcceptAllUnchecked(args);
        };
    }

    /**
     * An instance of {@link AbstractConsumerN} which properly defines the
     * arity of that particular consumer.
     *
     * @see AbstractConsumerN
     */
    class Instance implements AbstractConsumerN<Instance> {

        private final int arity;
        private final Consumer1<Object[]> consumer;

        /**
         * Constructs an instance of the consumer.
         *
         * @param arity    the number of arguments of the consumer
         * @param consumer the consumer to be applied
         */
        public Instance(final int arity, final Consumer1<Object[]> consumer) {
            this.arity = arity;
            this.consumer = consumer;
        }

        @Override
        public int arity() {
            return this.arity;
        }

        @Override
        public void acceptAllUnchecked(Object... args) {
            this.consumer.accept(args);
        }

        @Override
        public ConsumerN.Instance andThen(final ConsumerN.Instance after) {
            return (ConsumerN.Instance) AbstractConsumerN.super.andThen(after);
        }

        @Override
        public ConsumerN.Instance andThenUnchecked(final ConsumerN.Instance after) {
            return new ConsumerN.Instance(this.arity(), (final Object[] args) -> {
                this.sizedAcceptAllUnchecked(args);
                after.sizedAcceptAllUnchecked(args);
            });
        }
    }
}
