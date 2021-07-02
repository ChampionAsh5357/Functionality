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
import net.ashwork.functionality.test.util.TestUtil;
import org.junit.jupiter.api.Test;

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
        TestUtil.runConsumerTest(3, assertion -> ((Consumer3<Integer, Integer, Integer>) (i1, i2, i3) -> assertion.accept(i1 + i2 + i3)),
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
        TestUtil.runConsumerTest(4, assertion -> ((Consumer4<Integer, Integer, Integer, Integer>) (i1, i2, i3, i4) -> assertion.accept(i1 + i2 + i3 + i4)),
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
        TestUtil.runConsumerTest(5, assertion -> ((Consumer5<Integer, Integer, Integer, Integer, Integer>) (i1, i2, i3, i4, i5) -> assertion.accept(i1 + i2 + i3 + i4 + i5)),
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
        TestUtil.runConsumerTest(6, assertion -> ((Consumer6<Integer, Integer, Integer, Integer, Integer, Integer>) (i1, i2, i3, i4, i5, i6) -> assertion.accept(i1 + i2 + i3 + i4 + i5 + i6)),
                (consumer, inputs) -> consumer.accept(inputs[0], inputs[1], inputs[2], inputs[3], inputs[4], inputs[5]),
                (consumer, runnable) -> consumer.andThen((i1, i2, i3, i4, i5, i6) -> runnable.run()));
    }
}
