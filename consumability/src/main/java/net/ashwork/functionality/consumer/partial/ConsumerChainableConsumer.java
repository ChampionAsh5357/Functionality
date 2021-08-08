/*
 * Consumability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.consumer.partial;

/**
 * Represents a {@link ConsumerChainable} to which the chained consumer is also a
 * chainable object.
 *
 * @param <C> the type of this consumer
 *
 * @see ConsumerChainable
 * @since 1.0.0
 */
public interface ConsumerChainableConsumer<C extends ConsumerChainableConsumer<C>> extends ConsumerChainable<C> {

    @SuppressWarnings("unchecked")
    @Override
    default ConsumerChainableConsumer<C> andThen(final C after) {
        return (ConsumerChainableConsumer<C>) ConsumerChainable.super.andThen(after);
    }

    @Override
    ConsumerChainableConsumer<C> andThenUnchecked(final C after);
}
