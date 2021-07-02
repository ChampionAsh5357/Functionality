/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.test.util;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.function.*;

/**
 * A general utility for executing tests.
 */
public final class TestUtil {

    /**
     * Creates a n-arity function test.
     *
     * @param elements The number of elements the function takes in
     * @param functionSupplier The supplied function
     * @param apply The result method that the function uses to apply the input
     * @param andThen The method used to apply a function to the result of this one
     * @param <F> The type of the function
     */
    public static <F> void runFunctionTest(final int elements, final Supplier<F> functionSupplier, final BiFunction<F, int[], Integer> apply, final BiFunction<F, int[], Boolean> andThen) {
        final int[] randoms = RandomUtil.current().ints(elements).distinct().toArray();
        final int sum = Arrays.stream(randoms).sum();
        final F function = functionSupplier.get();
        Assertions.assertEquals(sum, apply.apply(function, randoms));
        Assertions.assertFalse(andThen.apply(function, randoms));
    }

    /**
     * Creates a n-arity consumer test.
     *
     * @param elements The number of elements the consumer takes in
     * @param consumerFactory The factory to construct the consumer
     * @param accept The void method that the consumer uses to accept the input
     * @param andThen The method used to accept another consumer after this one
     * @param <C> The type of the consumer
     */
    public static <C> void runConsumerTest(final int elements, final Function<Consumer<Integer>, C> consumerFactory, final BiConsumer<C, int[]> accept, final BiFunction<C, Runnable, C> andThen) {
        final int[] randoms = RandomUtil.current().ints(elements).distinct().toArray();
        final int sum = Arrays.stream(randoms).sum();
        final C consumer = consumerFactory.apply((calcSum) -> Assertions.assertEquals(sum, calcSum));
        accept.accept(consumer, randoms);
        accept.accept(andThen.apply(consumer, () -> Assertions.assertTrue(true)), randoms);
    }

    /**
     * Creates a n-arity operator test.
     *
     * @param elements The number of elements the operator takes in
     * @param functionSupplier The supplied operator
     * @param apply The result method that the operator uses to apply the input
     * @param <O> The type of the operator
     */
    public static <O> void runOperatorTest(final int elements, final Supplier<O> functionSupplier, final BiFunction<O, int[], Integer> apply) {
        final int[] randoms = RandomUtil.current().ints(elements).distinct().toArray();
        final int sum = Arrays.stream(randoms).sum();
        final O function = functionSupplier.get();
        Assertions.assertEquals(sum, apply.apply(function, randoms));
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
     * @param <P> The type of the predicate
     * @param <F> The type of the non-primitive function
     */
    public static <P, F> void runPredicateTest(final int elements, final Supplier<P> predicateSupplier, final Supplier<P> inverseSupplier, final BiFunction<P, int[], Boolean> test, final UnaryOperator<P> negate, final BinaryOperator<P> and, final BinaryOperator<P> or, final Function<P, F> boxed, final BiFunction<F, int[], Boolean> boxedTest) {
        final int[] randoms = RandomUtil.current().ints(elements, Integer.MIN_VALUE, Integer.MAX_VALUE).distinct().toArray();
        final boolean lessThanZero = Arrays.stream(randoms).sum() < 0;
        final P predicate = predicateSupplier.get();
        Assertions.assertEquals(lessThanZero, test.apply(predicate, randoms));
        Assertions.assertEquals(!lessThanZero, test.apply(negate.apply(predicate), randoms));
        final P inversePredicate = inverseSupplier.get();
        Assertions.assertFalse(boxedTest.apply(boxed.apply(and.apply(predicate, inversePredicate)), randoms));
        Assertions.assertTrue(boxedTest.apply(boxed.apply(or.apply(predicate, inversePredicate)), randoms));
    }
}
