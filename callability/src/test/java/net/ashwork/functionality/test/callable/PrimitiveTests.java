/*
 * Callability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.test.callable;

import net.ashwork.functionality.callable.*;
import net.ashwork.functionality.test.util.CallabilityTestUtil;
import net.ashwork.functionality.test.util.RandomUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.function.*;

/**
 * All tests associated with {@code net.ashwork.functionality.callable}.
 */
public final class PrimitiveTests {

    /**
     * Tested:
     * <ul>
     *     <li>{@link BooleanCallable#from(BooleanSupplier)}</li>
     *     <li>{@link BooleanCallable#callAsBoolean()}</li>
     *     <li>{@link BooleanCallable#boxed()}</li>
     *     <li>{@link BooleanCallable#handle(BooleanCallable.ExceptionHandler)}</li>
     *     <li>{@link BooleanCallable#swallow()}</li>
     * </ul>
     */
    @Test
    public void booleanCallable() {
        CallabilityTestUtil.suppliedPrimitiveCallableTest((Boolean value) -> BooleanCallable.from(() -> value),
                callable -> callable::call, BooleanCallable::callAsBoolean, (callable, value) -> callable.handle(e -> value),
                BooleanCallable::swallow, BooleanSupplier::getAsBoolean, BooleanCallable::boxed,
                RandomUtil.current().nextBoolean(), RandomUtil.current().nextBoolean(), false);
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link DoubleCallable#from(DoubleSupplier)}</li>
     *     <li>{@link DoubleCallable#callAsDouble()}</li>
     *     <li>{@link DoubleCallable#boxed()}</li>
     *     <li>{@link DoubleCallable#handle(DoubleCallable.ExceptionHandler)}</li>
     *     <li>{@link DoubleCallable#swallow()}</li>
     * </ul>
     */
    @Test
    public void doubleCallable() {
        CallabilityTestUtil.suppliedPrimitiveCallableTest((Double value) -> DoubleCallable.from(() -> value),
                callable -> callable::call, DoubleCallable::callAsDouble, (callable, value) -> callable.handle(e -> value),
                DoubleCallable::swallow, DoubleSupplier::getAsDouble, DoubleCallable::boxed,
                RandomUtil.current().nextDouble(), RandomUtil.current().nextDouble(), 0.0D);
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link IntCallable#from(IntSupplier)}</li>
     *     <li>{@link IntCallable#callAsInt()}</li>
     *     <li>{@link IntCallable#boxed()}</li>
     *     <li>{@link IntCallable#handle(IntCallable.ExceptionHandler)}</li>
     *     <li>{@link IntCallable#swallow()}</li>
     * </ul>
     */
    @Test
    public void intCallable() {
        CallabilityTestUtil.suppliedPrimitiveCallableTest((Integer value) -> IntCallable.from(() -> value),
                callable -> callable::call, IntCallable::callAsInt, (callable, value) -> callable.handle(e -> value),
                IntCallable::swallow, IntSupplier::getAsInt, IntCallable::boxed,
                RandomUtil.current().nextInt(), RandomUtil.current().nextInt(), 0);
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link LongCallable#from(LongSupplier)}</li>
     *     <li>{@link LongCallable#callAsLong()}</li>
     *     <li>{@link LongCallable#boxed()}</li>
     *     <li>{@link LongCallable#handle(LongCallable.ExceptionHandler)}</li>
     *     <li>{@link LongCallable#swallow()}</li>
     * </ul>
     */
    @Test
    public void longCallable() {
        CallabilityTestUtil.suppliedPrimitiveCallableTest((Long value) -> LongCallable.from(() -> value),
                callable -> callable::call, LongCallable::callAsLong, (callable, value) -> callable.handle(e -> value),
                LongCallable::swallow, LongSupplier::getAsLong, LongCallable::boxed,
                RandomUtil.current().nextLong(), RandomUtil.current().nextLong(), 0L);
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link CallabilityCallable#wrap(Callable)}</li>
     *     <li>{@link CallabilityCallable#from(Supplier)}</li>
     *     <li>{@link CallabilityCallable#call()}</li>
     *     <li>{@link CallabilityCallable#handle(CallabilityCallable.ExceptionHandler)}</li>
     *     <li>{@link CallabilityCallable#swallow()}</li>
     * </ul>
     */
    @Test
    public void callable() {
        CallabilityTestUtil.suppliedCallableTest((Integer value) -> CallabilityCallable.from(() -> value),
                CallabilityCallable::wrap, CallabilityCallable::call, (callable, value) -> callable.handle(e -> value),
                CallabilityCallable::swallow, (Function<Supplier<Integer>, Integer>) Supplier::get,
                RandomUtil.current().nextInt(), RandomUtil.current().nextInt());
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link RunnableCallable#from(Runnable)}</li>
     *     <li>{@link RunnableCallable#run()}</li>
     *     <li>{@link RunnableCallable#handle(RunnableCallable.ExceptionHandler)}</li>
     *     <li>{@link RunnableCallable#swallow()}</li>
     * </ul>
     */
    @Test
    public void runnable() {
        CallabilityTestUtil.suppliedCallableTest((Void v) -> RunnableCallable.from(() -> Assertions.assertTrue(true)),
                callable -> callable::call, runnable -> { runnable.run(); return null; }, runnable -> { runnable.run();return null; },
                (expected, actual) -> {}, (runnable, v) -> runnable.handle(e -> Assertions.assertTrue(true)), RunnableCallable::swallow,
                runnable -> { runnable.run(); return null; }, null, null, null);
    }
}
