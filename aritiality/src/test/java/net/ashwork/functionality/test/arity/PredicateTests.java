/*
 * Aritiality (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.test.arity;

import net.ashwork.functionality.arity.predicate.Predicate3;
import net.ashwork.functionality.arity.predicate.Predicate4;
import net.ashwork.functionality.arity.predicate.Predicate5;
import net.ashwork.functionality.arity.predicate.Predicate6;
import net.ashwork.functionality.test.util.RandomUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.*;

/**
 * All tests associated with {@code net.ashwork.functionality.arity.predicate}.
 */
public final class PredicateTests {

    /**
     * Tested:
     * <ul>
     *     <li>{@link Predicate3#test(Object, Object, Object)}</li>
     *     <li>{@link Predicate3#negate()}</li>
     *     <li>{@link Predicate3#and(Predicate3)}</li>
     *     <li>{@link Predicate3#or(Predicate3)}</li>
     *     <li>{@link Predicate3#boxed()}</li>
     * </ul>
     */
    @Test
    public void three() {
        runPredicateTest(3, () -> ((Predicate3<Integer, Integer, Integer>) (i1, i2, i3) -> i1 + i2 + i3 < 0),
                () -> (i1, i2, i3) -> i1 + i2 + i3 >= 0,
                (predicate, inputs) -> predicate.test(inputs[0], inputs[1], inputs[2]),
                Predicate3::negate, Predicate3::and, Predicate3::or, Predicate3::boxed,
                (function, inputs) -> function.apply(inputs[0], inputs[1], inputs[2]));
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link Predicate4#test(Object, Object, Object, Object)}</li>
     *     <li>{@link Predicate4#negate()}</li>
     *     <li>{@link Predicate4#and(Predicate4)}</li>
     *     <li>{@link Predicate4#or(Predicate4)}</li>
     *     <li>{@link Predicate4#boxed()}</li>
     * </ul>
     */
    @Test
    public void four() {
        runPredicateTest(4, () -> ((Predicate4<Integer, Integer, Integer, Integer>) (i1, i2, i3, i4) -> i1 + i2 + i3 + i4 < 0),
                () -> (i1, i2, i3, i4) -> i1 + i2 + i3 + i4 >= 0,
                (predicate, inputs) -> predicate.test(inputs[0], inputs[1], inputs[2], inputs[3]),
                Predicate4::negate, Predicate4::and, Predicate4::or, Predicate4::boxed,
                (function, inputs) -> function.apply(inputs[0], inputs[1], inputs[2], inputs[3]));
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link Predicate5#test(Object, Object, Object, Object, Object)}</li>
     *     <li>{@link Predicate5#negate()}</li>
     *     <li>{@link Predicate5#and(Predicate5)}</li>
     *     <li>{@link Predicate5#or(Predicate5)}</li>
     *     <li>{@link Predicate5#boxed()}</li>
     * </ul>
     */
    @Test
    public void five() {
        runPredicateTest(5, () -> ((Predicate5<Integer, Integer, Integer, Integer, Integer>) (i1, i2, i3, i4, i5) -> i1 + i2 + i3 + i4 + i5 < 0),
                () -> (i1, i2, i3, i4, i5) -> i1 + i2 + i3 + i4 + i5 >= 0,
                (predicate, inputs) -> predicate.test(inputs[0], inputs[1], inputs[2], inputs[3], inputs[4]),
                Predicate5::negate, Predicate5::and, Predicate5::or, Predicate5::boxed,
                (function, inputs) -> function.apply(inputs[0], inputs[1], inputs[2], inputs[3], inputs[4]));
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link Predicate6#test(Object, Object, Object, Object, Object, Object)}</li>
     *     <li>{@link Predicate6#negate()}</li>
     *     <li>{@link Predicate6#and(Predicate6)}</li>
     *     <li>{@link Predicate6#or(Predicate6)}</li>
     *     <li>{@link Predicate6#boxed()}</li>
     * </ul>
     */
    @Test
    public void six() {
        runPredicateTest(6, () -> ((Predicate6<Integer, Integer, Integer, Integer, Integer, Integer>) (i1, i2, i3, i4, i5, i6) -> i1 + i2 + i3 + i4 + i5 + i6 < 0),
                () -> (i1, i2, i3, i4, i5, i6) -> i1 + i2 + i3 + i4 + i5 + i6 >= 0,
                (predicate, inputs) -> predicate.test(inputs[0], inputs[1], inputs[2], inputs[3], inputs[4], inputs[5]),
                Predicate6::negate, Predicate6::and, Predicate6::or, Predicate6::boxed,
                (function, inputs) -> function.apply(inputs[0], inputs[1], inputs[2], inputs[3], inputs[4], inputs[5]));
    }

    /**
     * Creates a n-arity predicate test.
     *
     * @param elements The number of elements the predicate takes in
     * @param predicateSupplier The supplied predicate
     * @param inverseSupplier The inverse of the supplied predicate (should be a separate predicate and not {@code #negate})
     * @param test The test method that the predicate uses to apply the input
     * @param negate The negate method that the predicate uses to flip the output
     * @param and The and method that the predicate uses to logically AND two predicates
     * @param or The or method that the predicate uses to logically OR two predicates
     * @param boxed The boxed method used to convert a predicate to its non-primitive function
     * @param boxedTest The test method that the function uses to apply the input
     * @param <T> The type of the predicate
     * @param <F> The type of the non-primitive function
     */
    private static <T, F> void runPredicateTest(final int elements, final Supplier<T> predicateSupplier, final Supplier<T> inverseSupplier, final BiFunction<T, int[], Boolean> test, final UnaryOperator<T> negate, final BinaryOperator<T> and, final BinaryOperator<T> or, final Function<T, F> boxed, final BiFunction<F, int[], Boolean> boxedTest) {
        final int[] randoms = RandomUtil.current().ints(elements, Integer.MIN_VALUE, Integer.MAX_VALUE).distinct().toArray();
        final boolean lessThanZero = Arrays.stream(randoms).sum() < 0;
        final T predicate = predicateSupplier.get();
        Assertions.assertEquals(lessThanZero, test.apply(predicate, randoms));
        Assertions.assertEquals(!lessThanZero, test.apply(negate.apply(predicate), randoms));
        final T inversePredicate = inverseSupplier.get();
        Assertions.assertFalse(boxedTest.apply(boxed.apply(and.apply(predicate, inversePredicate)), randoms));
        Assertions.assertTrue(boxedTest.apply(boxed.apply(or.apply(predicate, inversePredicate)), randoms));
    }
}
