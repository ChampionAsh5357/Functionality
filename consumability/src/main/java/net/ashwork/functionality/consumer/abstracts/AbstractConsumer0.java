/*
 * Consumability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.consumer.abstracts;

import net.ashwork.functionality.Function0;
import net.ashwork.functionality.Function1;
import net.ashwork.functionality.partial.Variant;
import net.ashwork.functionality.util.InheritOnly;

/**
 * Represents an operation that accepts no arguments and returns no result.
 * This is the zero-arity specialization of {@link AbstractConsumerN}.
 * This is the non-producing specialization of {@link Function0}.
 *
 * @apiNote
 * This is an abstract consumer and should not be used directly. It should instead
 * be called by one of its subtypes.
 *
 * @param <C> the type of this consumer
 *
 * @see AbstractConsumerN
 * @see Function0
 * @since 1.0.0
 */
@InheritOnly
public interface AbstractConsumer0<C extends AbstractConsumer0<C>> extends AbstractConsumerN<C>, Variant<Runnable> {

    /**
     * Performs this operation.
     */
    void accept();

    @Override
    default void acceptAllUnchecked(final Object... args) {
        this.accept();
    }

    @Override
    default int arity() {
        return 0;
    }

    /**
     * @see Runnable
     */
    @Override
    default Runnable toVariant() { return this::accept; }

    @Override
    default AbstractConsumer0<C> andThen(final C after) {
        return (AbstractConsumer0<C>) AbstractConsumerN.super.andThen(after);
    }

    @Override
    default AbstractConsumer0<C> andThenUnchecked(final C after) {
        return () -> {
            this.accept();
            after.accept();
        };
    }

    /**
     * @see Function0
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> Function0<V> andThen(final Function1<? super Void, ? extends V> after) {
        return (Function0<V>) AbstractConsumerN.super.andThen(after);
    }

    /**
     * @see Function0
     */
    @Override
    default <V> Function0<V> andThenUnchecked(final Function1<? super Void, ? extends V> after) {
        return () -> {
            this.accept();
            return after.apply(null);
        };
    }
}
