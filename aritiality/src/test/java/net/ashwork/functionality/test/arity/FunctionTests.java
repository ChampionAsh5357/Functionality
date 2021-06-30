/*
 * Aritiality (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.test.arity;

import net.ashwork.functionality.arity.function.Function3;
import net.ashwork.functionality.arity.function.Function4;
import net.ashwork.functionality.arity.function.Function5;
import net.ashwork.functionality.arity.function.Function6;
import net.ashwork.functionality.test.util.RandomUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.*;

/**
 * All tests associated with {@code net.ashwork.functionality.arity.function}.
 */
public final class FunctionTests {

    /**
     * Tested:
     * <ul>
     *     <li>{@link Function3#apply(Object, Object, Object)}</li>
     *     <li>{@link Function3#andThen(Function)}</li>
     * </ul>
     */
    @Test
    public void three() {
        runFunctionTest(3, () -> ((Function3<Integer, Integer, Integer, Integer>) (i1, i2, i3) -> i1 + i2 + i3),
                (function, inputs) -> function.apply(inputs[0], inputs[1], inputs[2]),
                (function, inputs) -> function.andThen(res -> String.valueOf(res).contains("n"))
                        .apply(inputs[0], inputs[1], inputs[2]));
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link Function4#apply(Object, Object, Object, Object)}</li>
     *     <li>{@link Function4#andThen(Function)}</li>
     * </ul>
     */
    @Test
    public void four() {
        runFunctionTest(4, () -> ((Function4<Integer, Integer, Integer, Integer, Integer>) (i1, i2, i3, i4) -> i1 + i2 + i3 + i4),
                (function, inputs) -> function.apply(inputs[0], inputs[1], inputs[2], inputs[3]),
                (function, inputs) -> function.andThen(res -> String.valueOf(res).contains("n"))
                        .apply(inputs[0], inputs[1], inputs[2], inputs[3]));
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link Function5#apply(Object, Object, Object, Object, Object)}</li>
     *     <li>{@link Function5#andThen(Function)}</li>
     * </ul>
     */
    @Test
    public void five() {
        runFunctionTest(5, () -> ((Function5<Integer, Integer, Integer, Integer, Integer, Integer>) (i1, i2, i3, i4, i5) -> i1 + i2 + i3 + i4 + i5),
                (function, inputs) -> function.apply(inputs[0], inputs[1], inputs[2], inputs[3], inputs[4]),
                (function, inputs) -> function.andThen(res -> String.valueOf(res).contains("n"))
                        .apply(inputs[0], inputs[1], inputs[2], inputs[3], inputs[4]));
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link Function6#apply(Object, Object, Object, Object, Object, Object)}</li>
     *     <li>{@link Function6#andThen(Function)}</li>
     * </ul>
     */
    @Test
    public void six() {
        runFunctionTest(6, () -> ((Function6<Integer, Integer, Integer, Integer, Integer, Integer, Integer>) (i1, i2, i3, i4, i5, i6) -> i1 + i2 + i3 + i4 + i5 + i6),
                (function, inputs) -> function.apply(inputs[0], inputs[1], inputs[2], inputs[3], inputs[4], inputs[5]),
                (function, inputs) -> function.andThen(res -> String.valueOf(res).contains("n"))
                        .apply(inputs[0], inputs[1], inputs[2], inputs[3], inputs[4], inputs[5]));
    }

    /**
     * Creates a n-arity function test.
     *
     * @param elements The number of elements the function takes in
     * @param functionSupplier The supplied function
     * @param apply The result method that the function uses to apply the input
     * @param andThen The method used to apply a function to the result of this one
     * @param <T> The type of the function
     */
    private static <T> void runFunctionTest(final int elements, final Supplier<T> functionSupplier, final BiFunction<T, int[], Integer> apply, final BiFunction<T, int[], Boolean> andThen) {
        final int[] randoms = RandomUtil.current().ints(elements).distinct().toArray();
        final int sum = Arrays.stream(randoms).sum();
        final T function = functionSupplier.get();
        Assertions.assertEquals(sum, apply.apply(function, randoms));
        Assertions.assertFalse(andThen.apply(function, randoms));
    }
}
