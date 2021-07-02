/*
 * Callability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.test.callable;

import net.ashwork.functionality.callable.function.BiFunctionCallable;
import net.ashwork.functionality.callable.function.FunctionCallable;
import net.ashwork.functionality.callable.function.primitive.IntFunctionCallable;
import net.ashwork.functionality.test.util.CallabilityTestUtil;
import net.ashwork.functionality.test.util.RandomUtil;
import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;

/**
 * All tests associated with {@code net.ashwork.functionality.callable.function}.
 */
public final class FunctionTests {

    /**
     * Tested:
     * <ul>
     *     <li>{@link FunctionCallable#from(Function)}</li>
     *     <li>{@link FunctionCallable#identity()}</li>
     *     <li>{@link FunctionCallable#apply(Object)}</li>
     *     <li>{@link FunctionCallable#andThen(FunctionCallable)}</li>
     *     <li>{@link FunctionCallable#compose(FunctionCallable)}</li>
     *     <li>{@link FunctionCallable#handle(FunctionCallable.ExceptionHandler)}</li>
     *     <li>{@link FunctionCallable#swallow()}</li>
     * </ul>
     */
    @Test
    public void one() {
        final Integer[] inputs = RandomUtil.current().ints(1).distinct().boxed().toArray(Integer[]::new);
        CallabilityTestUtil.returnCallableTest(() -> FunctionCallable.from((Integer i) -> i % 2 == 0),
                callable -> i -> callable.call(), (function, values) -> function.apply(values[0]),
                (function, values) -> function.compose(FunctionCallable.identity()).andThen(b -> !b).apply(values[0]),
                (function, value) -> function.handle((i, e) -> value), FunctionCallable::swallow,
                (Function<Integer, Boolean> function, Integer[] values) -> function.apply(values[0]),
                inputs, inputs[0] % 2 != 0, RandomUtil.current().nextBoolean());
    }

    /**)
     * Tested:
     * <ul>
     *     <li>{@link BiFunctionCallable#from(BiFunction)}</li>
     *     <li>{@link BiFunctionCallable#apply(Object, Object)}</li>
     *     <li>{@link BiFunctionCallable#andThen(FunctionCallable)}</li>
     *     <li>{@link BiFunctionCallable#handle(BiFunctionCallable.ExceptionHandler)}</li>
     *     <li>{@link BiFunctionCallable#swallow()}</li>
     * </ul>
     */
    @Test
    public void two() {
        final Integer[] inputs = RandomUtil.current().ints(2).distinct().boxed().toArray(Integer[]::new);
        CallabilityTestUtil.returnCallableTest(() -> BiFunctionCallable.from((Integer i1, Integer i2) -> (i1 + i2) % 2 == 0),
                callable -> (i1, i2) -> callable.call(), (function, values) -> function.apply(values[0], values[1]),
                (function, values) -> function.andThen(b -> !b).apply(values[0], values[1]),
                (function, value) -> function.handle((i1, i2, e) -> value), BiFunctionCallable::swallow,
                (BiFunction<Integer, Integer, Boolean> function, Integer[] values) -> function.apply(values[0], values[1]),
                inputs, (inputs[0] + inputs[1]) % 2 != 0, RandomUtil.current().nextBoolean());
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link IntFunctionCallable#from(IntFunction)}</li>
     *     <li>{@link IntFunctionCallable#apply(int)}</li>
     *     <li>{@link IntFunctionCallable#boxed()}</li>
     *     <li>{@link IntFunctionCallable#handle(IntFunctionCallable.ExceptionHandler)}</li>
     *     <li>{@link IntFunctionCallable#swallow()}</li>
     * </ul>
     */
    @Test
    public void oneInt() {
        final Integer[] inputs = RandomUtil.current().ints(1).distinct().boxed().toArray(Integer[]::new);
        CallabilityTestUtil.returnCallableTest(() -> IntFunctionCallable.from(i -> i % 2 == 0),
                callable -> i -> callable.call(), (function, values) -> function.apply(values[0]),
                (function, values) -> function.boxed().apply(values[0]),
                (function, value) -> function.handle((i, e) -> value), IntFunctionCallable::swallow,
                (IntFunction<Boolean> function, Integer[] values) -> function.apply(values[0]),
                inputs, inputs[0] % 2 == 0, RandomUtil.current().nextBoolean());
    }
}
