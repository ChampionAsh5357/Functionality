/*
 * Callability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.test.callable;

import net.ashwork.functionality.callable.consumer.BiConsumerCallable;
import net.ashwork.functionality.callable.consumer.ConsumerCallable;
import net.ashwork.functionality.test.util.CallabilityTestUtil;
import net.ashwork.functionality.test.util.RandomUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * All tests associated with {@code net.ashwork.functionality.callable.consumer}.
 */
public final class ConsumerTests {

    /**
     * Tested:
     * <ul>
     *     <li>{@link ConsumerCallable#from(Consumer)}</li>
     *     <li>{@link ConsumerCallable#accept(Object)}</li>
     *     <li>{@link ConsumerCallable#andThen(ConsumerCallable)}</li>
     *     <li>{@link ConsumerCallable#handle(ConsumerCallable.ExceptionHandler)}</li>
     *     <li>{@link ConsumerCallable#swallow()}</li>
     * </ul>
     */
    @Test
    public void one() {
        final Integer[] inputs = RandomUtil.current().ints(1).distinct().boxed().toArray(Integer[]::new);
        CallabilityTestUtil.consumerCallableTest((Integer testValue) -> ConsumerCallable.from((i) -> Assertions.assertEquals(testValue, i)),
                callable -> i -> callable.call(), (consumer, values) -> consumer.accept(values[0]),
                (consumer, values) -> consumer.andThen(i -> Assertions.assertTrue(true)).accept(values[0]),
                (consumer, value) -> consumer.handle((i, e) -> Assertions.assertEquals(value, i)), ConsumerCallable::swallow,
                (consumer, values) -> consumer.accept(values[0]), inputs, inputs[0]);
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link BiConsumerCallable#from(BiConsumer)}</li>
     *     <li>{@link BiConsumerCallable#accept(Object, Object)}</li>
     *     <li>{@link BiConsumerCallable#andThen(BiConsumerCallable)}</li>
     *     <li>{@link BiConsumerCallable#handle(BiConsumerCallable.ExceptionHandler)}</li>
     *     <li>{@link BiConsumerCallable#swallow()}</li>
     * </ul>
     */
    @Test
    public void two() {
        final Integer[] inputs = RandomUtil.current().ints(2).distinct().boxed().toArray(Integer[]::new);
        CallabilityTestUtil.consumerCallableTest((Integer testValue) -> BiConsumerCallable.from((Integer i1, Integer i2) -> Assertions.assertEquals(testValue, i1 + i2)),
                callable -> (i1, i2) -> callable.call(), (consumer, values) -> consumer.accept(values[0], values[1]),
                (consumer, values) -> consumer.andThen((i1, i2) -> Assertions.assertTrue(true)).accept(values[0], values[1]),
                (consumer, value) -> consumer.handle((i1, i2, e) -> Assertions.assertEquals(value, i1 + i2)), BiConsumerCallable::swallow,
                (consumer, values) -> consumer.accept(values[0], values[1]), inputs, inputs[0] + inputs[1]);
    }
}
