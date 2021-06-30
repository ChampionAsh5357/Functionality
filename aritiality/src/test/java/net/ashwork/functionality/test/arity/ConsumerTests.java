/*
 * Aritiality (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.test.arity;

import net.ashwork.functionality.arity.consumer.Consumer3;
import net.ashwork.functionality.arity.consumer.Consumer4;
import net.ashwork.functionality.arity.consumer.Consumer5;
import net.ashwork.functionality.arity.consumer.Consumer6;
import net.ashwork.functionality.test.util.RandomUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * All tests associated with {@code net.ashwork.functionality.arity.consumer}.
 */
public final class ConsumerTests {

    /**
     * Tested:
     * <ul>
     *     <li>{@link Consumer3#accept(Object, Object, Object)}</li>
     *     <li>{@link Consumer3#andThen(Consumer3)}</li>
     * </ul>
     */
    @Test
    public void three() {
        runConsumerTest(3, assertion -> ((Consumer3<Integer, Integer, Integer>) (i1, i2, i3) -> assertion.accept(i1 + i2 + i3)),
                (consumer, inputs) -> consumer.accept(inputs[0], inputs[1], inputs[2]),
                (consumer, runnable) -> consumer.andThen((i1, i2, i3) -> runnable.run()));
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link Consumer4#accept(Object, Object, Object, Object)}</li>
     *     <li>{@link Consumer4#andThen(Consumer4)}</li>
     * </ul>
     */
    @Test
    public void four() {
        runConsumerTest(4, assertion -> ((Consumer4<Integer, Integer, Integer, Integer>) (i1, i2, i3, i4) -> assertion.accept(i1 + i2 + i3 + i4)),
                (consumer, inputs) -> consumer.accept(inputs[0], inputs[1], inputs[2], inputs[3]),
                (consumer, runnable) -> consumer.andThen((i1, i2, i3, i4) -> runnable.run()));
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link Consumer5#accept(Object, Object, Object, Object, Object)}</li>
     *     <li>{@link Consumer5#andThen(Consumer5)}</li>
     * </ul>
     */
    @Test
    public void five() {
        runConsumerTest(5, assertion -> ((Consumer5<Integer, Integer, Integer, Integer, Integer>) (i1, i2, i3, i4, i5) -> assertion.accept(i1 + i2 + i3 + i4 + i5)),
                (consumer, inputs) -> consumer.accept(inputs[0], inputs[1], inputs[2], inputs[3], inputs[4]),
                (consumer, runnable) -> consumer.andThen((i1, i2, i3, i4, i5) -> runnable.run()));
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link Consumer6#accept(Object, Object, Object, Object, Object, Object)}</li>
     *     <li>{@link Consumer6#andThen(Consumer6)}</li>
     * </ul>
     */
    @Test
    public void six() {
        runConsumerTest(6, assertion -> ((Consumer6<Integer, Integer, Integer, Integer, Integer, Integer>) (i1, i2, i3, i4, i5, i6) -> assertion.accept(i1 + i2 + i3 + i4 + i5 + i6)),
                (consumer, inputs) -> consumer.accept(inputs[0], inputs[1], inputs[2], inputs[3], inputs[4], inputs[5]),
                (consumer, runnable) -> consumer.andThen((i1, i2, i3, i4, i5, i6) -> runnable.run()));
    }

    /**
     * Creates a n-arity consumer test.
     *
     * @param elements The number of elements the consumer takes in
     * @param consumerFactory The factory to construct the consumer
     * @param accept The void method that the consumer uses to accept the input
     * @param andThen The method used to accept another consumer after this one
     * @param <T> The type of the consumer
     */
    private static <T> void runConsumerTest(final int elements, final Function<Consumer<Integer>, T> consumerFactory, final BiConsumer<T, int[]> accept, final BiFunction<T, Runnable, T> andThen) {
        final int[] randoms = RandomUtil.current().ints(elements).distinct().toArray();
        final int sum = Arrays.stream(randoms).sum();
        final T consumer = consumerFactory.apply((calcSum) -> Assertions.assertEquals(sum, calcSum));
        accept.accept(consumer, randoms);
        accept.accept(andThen.apply(consumer, () -> Assertions.assertTrue(true)), randoms);
    }
}
