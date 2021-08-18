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
import net.ashwork.functionality.consumer.ConsumerN;
import net.ashwork.functionality.throwable.ThrowingFunctionN;
import net.ashwork.functionality.throwable.abstracts.consumer.AbstractThrowingConsumerN;

/**
 * Represents an operation that accepts {@code n} arguments and returns no result or throws a throwable.
 * All consumers are derived from this {@code n}-arity specialization.
 * This is the non-producing specialization of {@link ThrowingFunctionN}.
 * This is the throwing variation of {@link ConsumerN}.
 *
 * <p>This is a functional interface whose functional method is {@link #acceptAllUnchecked(Object...)}.
 *
 * @see ThrowingFunctionN
 * @see ConsumerN
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingConsumerN extends AbstractThrowingConsumerN<AbstractThrowingConsumerN.Handler, ThrowingConsumerN> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param consumer the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see ConsumerN
     */
    static ThrowingConsumerN from(final ConsumerN consumer) {
        return consumer::acceptAllUnchecked;
    }

    /**
     * @see ConsumerN
     */
    @Override
    default ConsumerN handle(final Handler handler) {
        return (final Object[] args) -> {
            try {
                this.acceptAllUnchecked(args);
            } catch (final Throwable t) {
                handler.acceptThrownUnchecked(t, args);
            }
        };
    }

    /**
     * @see ConsumerN
     */
    @Override
    default ConsumerN swallow() {
        return this.handle((t, args) -> {});
    }

    @Override
    default ThrowingConsumerN andThen(final ThrowingConsumerN after) {
        return (ThrowingConsumerN) AbstractThrowingConsumerN.super.andThen(after);
    }

    @Override
    default ThrowingConsumerN andThenUnchecked(final ThrowingConsumerN after) {
        return (final Object[] args) -> {
            this.acceptAllUnchecked(args);
            after.acceptAllUnchecked(args);
        };
    }

    /**
     * @see ThrowingFunctionN
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunctionN<V> andThen(final Function1<? super Void, ? extends V> after) {
        return (ThrowingFunctionN<V>) AbstractThrowingConsumerN.super.andThen(after);
    }

    /**
     * @see ThrowingFunctionN
     */
    @Override
    default <V> ThrowingFunctionN<V> andThenUnchecked(final Function1<? super Void, ? extends V> after) {
        return (final Object[] args) -> {
            this.acceptAllUnchecked(args);
            return after.apply(null);
        };
    }

    /**
     * An instance of {@link AbstractThrowingConsumerN} which properly defines the
     * arity of that particular consumer.
     *
     * @see AbstractThrowingConsumerN
     */
    class Instance implements AbstractThrowingConsumerN<Handler, Instance> {

        private final int arity;
        private final ThrowingConsumer1<Object[]> consumer;

        /**
         * Constructs an instance of the consumer.
         *
         * @param consumer the consumer instance to be applied
         */
        public Instance(final ConsumerN.Instance consumer) {
            this(consumer.arity(), consumer::acceptAllUnchecked);
        }

        /**
         * Constructs an instance of the consumer.
         *
         * @param arity    the number of arguments of the consumer
         * @param consumer the consumer to be applied
         */
        public Instance(final int arity, final ThrowingConsumer1<Object[]> consumer) {
            this.arity = arity;
            this.consumer = consumer;
        }

        @Override
        public int arity() {
            return this.arity;
        }

        @Override
        public void acceptAllUnchecked(final Object... args) throws Throwable {
            this.consumer.accept(args);
        }

        /**
         * @see ConsumerN.Instance
         */
        @Override
        public ConsumerN.Instance handle(Handler handler) {
            return new ConsumerN.Instance(this.arity(), (final Object[] args) -> {
                try {
                    this.acceptAllUnchecked(args);
                } catch (final Throwable t) {
                    handler.acceptThrownUnchecked(t, args);
                }
            });
        }

        /**
         * @see ConsumerN.Instance
         */
        @Override
        public ConsumerN.Instance swallow() {
            return this.handle((t, args) -> {});
        }

        @Override
        public Instance andThen(Instance after) {
            return (Instance) AbstractThrowingConsumerN.super.andThen(after);
        }

        @Override
        public Instance andThenUnchecked(Instance after) {
            return new Instance(this.arity(), (final Object[] args) -> {
                this.acceptAllUnchecked(args);
                after.acceptAllUnchecked(args);
            });
        }

        /**
         * @see ThrowingFunctionN.Instance
         */
        @SuppressWarnings("unchecked")
        @Override
        public <V> ThrowingFunctionN.Instance<V> andThen(Function1<? super Void, ? extends V> after) {
            return (ThrowingFunctionN.Instance<V>) AbstractThrowingConsumerN.super.andThen(after);
        }

        /**
         * @see ThrowingFunctionN.Instance
         */
        @Override
        public <V> ThrowingFunctionN.Instance<V> andThenUnchecked(Function1<? super Void, ? extends V> after) {
            return new ThrowingFunctionN.Instance<>(this.arity(), (final Object[] args) -> {
                this.acceptAllUnchecked(args);
                return after.apply(null);
            });
        }
    }
}
