/*
 * Callability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.test;

import net.ashwork.functionality.callable.CallableFunction;
import net.ashwork.functionality.callable.CallableIntFunction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiFunction;

/**
 * All tests associated with {@code net.ashwork.functionality.callable}.
 */
public final class CallableTests {

    /**
     * A random instance.
     */
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    /**
     * Tested:
     * <ul>
     *     <li>{@link CallableFunction#apply(Object)}</li>
     *     <li>{@link CallableFunction#andThen(CallableFunction)}</li>
     *     <li>{@link CallableFunction#compose(CallableFunction)}</li>
     *     <li>{@link CallableFunction#identity()}</li>
     *     <li>{@link CallableFunction#handle(BiFunction)}</li>
     *     <li>{@link CallableFunction#swallow()}</li>
     * </ul>
     */
    @Test
    public void function() {
        final CallableFunction<Integer, String> applyFunc = i -> {
            throw new RuntimeException(String.valueOf(i));
        };
        final CallableFunction<Integer, String> normalFunc = String::valueOf;
        Assertions.assertThrows(RuntimeException.class, () -> applyFunc.apply(RANDOM.nextInt(0, 1000)));
        Assertions.assertDoesNotThrow(() -> {
            final int random = RANDOM.nextInt(0, 1000);
            Assertions.assertEquals(normalFunc.andThen(str -> str.startsWith("n")).apply(random), false);
            Assertions.assertEquals(normalFunc.compose(CallableFunction.identity()).apply(random), String.valueOf(random));
        });
        Assertions.assertEquals(applyFunc.handle((i, e) -> "null").apply(RANDOM.nextInt(0, 1000)), "null");
        Assertions.assertNull(applyFunc.swallow().apply(RANDOM.nextInt(0, 1000)));
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link CallableIntFunction#apply(int)}</li>
     *     <li>{@link CallableIntFunction#handle(BiFunction)}</li>
     *     <li>{@link CallableIntFunction#swallow()}</li>
     * </ul>
     */
    @Test
    public void intFunction() {
        final CallableIntFunction<String> applyFunc = i -> {
            throw new RuntimeException(String.valueOf(i));
        };
        final CallableIntFunction<String> normalFunc = String::valueOf;
        Assertions.assertThrows(RuntimeException.class, () -> applyFunc.apply(RANDOM.nextInt(0, 1000)));
        Assertions.assertDoesNotThrow(() -> normalFunc.apply(RANDOM.nextInt(0, 1000)));
        Assertions.assertEquals(applyFunc.handle((i, e) -> "null").apply(RANDOM.nextInt(0, 1000)), "null");
        Assertions.assertNull(applyFunc.swallow().apply(RANDOM.nextInt(0, 1000)));
    }
}
