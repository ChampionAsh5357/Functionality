/*
 * Consumability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.consumer.partial;

import java.util.Objects;

/**
 * Represents a chainable object to which this operation can be chained to have
 * another operation execute directly after this.
 *
 * @param <C> the type of this consumer
 *
 * @since 1.0.0
 */
public interface ConsumerChainable<C extends ConsumerChainable<C>> {

    /**
     * Returns a chained object that performs, in sequence, this operation followed
     * by the {@code after} operation. If performing either operation throws an
     * exception, it is relayed to the caller of the composed operation. If performing
     * this operation throws an exception, the {@code after} operation will not be
     * performed.
     *
     * @param after the operation to perform after this operation
     * @return a chained object that performs, in sequence, this operation followed
     *         by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    default Object andThen(final C after) {
        Objects.requireNonNull(after, "The applied function cannot be null.");
        return this.andThenUnchecked(after);
    }

    /**
     * Returns a chained object that performs, in sequence, this operation followed
     * by the {@code after} operation. If performing either operation throws an
     * exception, it is relayed to the caller of the composed operation. If performing
     * this operation throws an exception, the {@code after} operation will not be
     * performed. This makes no assumptions on the arguments of this method.
     *
     * @param after the operation to perform after this operation
     * @return a chained object that performs, in sequence, this operation followed
     *         by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    Object andThenUnchecked(final C after);
}
