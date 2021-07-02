/*
 * Callability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.test.callable;

import net.ashwork.functionality.callable.operator.BinaryOperatorCallable;
import net.ashwork.functionality.callable.operator.UnaryOperatorCallable;
import net.ashwork.functionality.test.util.CallabilityTestUtil;
import net.ashwork.functionality.test.util.RandomUtil;
import org.junit.jupiter.api.Test;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

/**
 * All tests associated with {@code net.ashwork.functionality.callable.operator}.
 */
public final class OperatorTests {

    /**
     * Tested:
     * <ul>
     *     <li>{@link UnaryOperatorCallable#from(UnaryOperator)}</li>
     *     <li>{@link UnaryOperatorCallable#identity()}</li>
     *     <li>{@link UnaryOperatorCallable#apply(Object)}</li>
     *     <li>{@link UnaryOperatorCallable#handle(UnaryOperatorCallable.ExceptionHandler)}</li>
     *     <li>{@link UnaryOperatorCallable#swallow()}</li>
     * </ul>
     */
    @Test
    public void one() {
        final Integer[] inputs = RandomUtil.current().ints(1).distinct().boxed().toArray(Integer[]::new);
        CallabilityTestUtil.returnCallableTest(() -> UnaryOperatorCallable.from((Integer i) -> i / 2),
                callable -> i -> callable.call(), (operator, values) -> operator.apply(values[0]),
                (operator, values) -> operator.compose(UnaryOperatorCallable.identity()).andThen(i -> i * 2).apply(values[0]),
                (operator, value) -> operator.handle((i, e) -> value), UnaryOperatorCallable::swallow,
                (UnaryOperator<Integer> function, Integer[] values) -> function.apply(values[0]),
                inputs, inputs[0] / 2 * 2, RandomUtil.current().nextInt());
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link BinaryOperatorCallable#from(BinaryOperator)}</li>
     *     <li>{@link BinaryOperatorCallable#apply(Object, Object)}</li>
     *     <li>{@link BinaryOperatorCallable#handle(BinaryOperatorCallable.ExceptionHandler)}</li>
     *     <li>{@link BinaryOperatorCallable#swallow()}</li>
     * </ul>
     */
    @Test
    public void two() {
        final Integer[] inputs = RandomUtil.current().ints(2).distinct().boxed().toArray(Integer[]::new);
        CallabilityTestUtil.returnCallableTest(() -> BinaryOperatorCallable.from(Integer::sum),
                callable -> (i1, i2) -> callable.call(), (operator, values) -> operator.apply(values[0], values[1]),
                (operator, values) -> operator.apply(values[0], values[1]),
                (operator, value) -> operator.handle((i1, i2, e) -> value), BinaryOperatorCallable::swallow,
                ( BinaryOperator<Integer> function, Integer[] values) -> function.apply(values[0], values[1]),
                inputs, inputs[0] + inputs[1], RandomUtil.current().nextInt());
    }
}
