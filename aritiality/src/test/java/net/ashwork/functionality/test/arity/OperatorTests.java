/*
 * Aritiality (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.test.arity;

import net.ashwork.functionality.arity.operator.Operator3;
import net.ashwork.functionality.arity.operator.Operator4;
import net.ashwork.functionality.arity.operator.Operator5;
import net.ashwork.functionality.arity.operator.Operator6;
import net.ashwork.functionality.test.util.RandomUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * All tests associated with {@code net.ashwork.functionality.arity.operator}.
 */
public final class OperatorTests {

    /**
     * Tested:
     * <ul>
     *     <li>{@link Operator3#apply(Object, Object, Object)}</li>
     *     <li>{@link Operator3#conditional(Predicate)}</li>
     * </ul>
     */
    @Test
    public void three() {
        final int[] randoms = RandomUtil.current().ints(3).distinct().toArray();
        final Operator3<Integer> op = Operator3.conditional(i -> i % 2 == 0);
        Assertions.assertEquals(randoms[0] % 2 == 0 ? randoms[1] : randoms[2], op.apply(randoms[0], randoms[1], randoms[2]));
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link Operator4#apply(Object, Object, Object, Object)}</li>
     * </ul>
     */
    @Test
    public void four() {
        runOperatorTest(4, () -> ((Operator4<Integer>) (i1, i2, i3, i4) -> i1 + i2 + i3 + i4),
                (operator, inputs) -> operator.apply(inputs[0], inputs[1], inputs[2], inputs[3]));
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link Operator5#apply(Object, Object, Object, Object, Object)}</li>
     * </ul>
     */
    @Test
    public void five() {
        runOperatorTest(5, () -> ((Operator5<Integer>) (i1, i2, i3, i4, i5) -> i1 + i2 + i3 + i4 + i5),
                (operator, inputs) -> operator.apply(inputs[0], inputs[1], inputs[2], inputs[3], inputs[4]));
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link Operator6#apply(Object, Object, Object, Object, Object, Object)}</li>
     * </ul>
     */
    @Test
    public void six() {
        runOperatorTest(6, () -> ((Operator6<Integer>) (i1, i2, i3, i4, i5, i6) -> i1 + i2 + i3 + i4 + i5 + i6),
                (operator, inputs) -> operator.apply(inputs[0], inputs[1], inputs[2], inputs[3], inputs[4], inputs[5]));
    }

    /**
     * Creates a n-arity operator test.
     *
     * @param elements The number of elements the operator takes in
     * @param functionSupplier The supplied operator
     * @param apply The result method that the operator uses to apply the input
     * @param <T> The type of the operator
     */
    private static <T> void runOperatorTest(final int elements, final Supplier<T> functionSupplier, final BiFunction<T, int[], Integer> apply) {
        final int[] randoms = RandomUtil.current().ints(elements).distinct().toArray();
        final int sum = Arrays.stream(randoms).sum();
        final T function = functionSupplier.get();
        Assertions.assertEquals(sum, apply.apply(function, randoms));
    }
}
