/*
 * Aritiality (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.arity.consumer;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Represents an operation that accepts four input arguments and returns no
 * result. This is the four-arity specialization of {@link Consumer}.
 * Unlike most other functional interfaces, {@link Consumer4} is expected
 * to operate via side-effects.
 *
 * <p>This is a functional interface whose functional method is
 * {@link #accept(Object, Object, Object, Object)}.
 *
 * @param <T1> The type of the first argument to the operation
 * @param <T2> The type of the second argument to the operation
 * @param <T3> The type of the third argument to the operation
 * @param <T4> The type of the fourth argument to the operation
 *
 * @see Consumer
 * @since 2.0.0
 */
@FunctionalInterface
public interface Consumer4<T1, T2, T3, T4> {

    /**
     * Performs this operation on the given arguments.
     *
     * @param t1 The first input argument
     * @param t2 The second input argument
     * @param t3 The third input argument
     * @param t4 The fourth input argument
     */
    void accept(final T1 t1, final T2 t2, final T3 t3, final T4 t4);

    /**
     * Returns a composed consumer that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation. If performing this operation throws an exception,
     * the {@code after} operation will not be performed.
     *
     * @param after The operation to perform after this operation
     * @return A composed consumer that performs in sequence this
     *         operation followed by the {@code after} operation
     * @throws NullPointerException If {@code after} is null
     */
    default Consumer4<T1, T2, T3, T4> andThen(final Consumer4<? super T1, ? super T2, ? super T3, ? super T4> after) {
        Objects.requireNonNull(after, "The applied function cannot be null.");
        return (final T1 t1, final T2 t2, final T3 t3, final T4 t4) -> {
            this.accept(t1, t2, t3, t4);
            after.accept(t1, t2, t3, t4);
        };
    }
}
