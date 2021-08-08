/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.test;

import net.ashwork.functionality.Function0;
import net.ashwork.functionality.Function1;
import net.ashwork.functionality.Function2;
import net.ashwork.functionality.FunctionN;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A testing class that tests all object-based functions.
 */
public final class FunctionTests {

    /**
     * Tests the n-arity function.
     */
    @Test
    public void n() {
        FunctionN<String> function = args -> Arrays.stream(args).reduce("", (s, o) -> s + o.toString(), (s1, s2) -> s1 + s2);
        commonFunctionTest(r -> r.nextInt(100), a -> function);
        commonArityFunctionTest(r -> r.nextInt(100), a -> new FunctionN.Instance<>(a, function::applyAllUnchecked));
    }

    /**
     * Tests the zero-arity function.
     */
    @Test
    public void zero() {
        Function0<String> function = () -> "";
        commonArityFunctionTest(0, a -> function);
    }

    /**
     * Tests the one-arity function.
     */
    @Test
    public void one() {
        Function1<Object, String> function = Object::toString;
        commonFunction1Test(a -> function);
    }

    /**
     * Tests the two-arity function.
     */
    @Test
    public void two() {
        Function2<Object, Object, String> function = (t1, t2) -> t1.toString() + t2.toString();
        commonArityFunctionTest(2, a -> function);
    }

    /**
     * Runs a common function test for a generic {@link Function1} implementation.
     *
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends Function1<Object, String>> void commonFunction1Test(final IntFunction<F> functionFactory) {
        commonFunctionTest(null, functionFactory, (r, a, f, t, i, e) -> testFunction1(r, f, s -> s + " ", t, i, e));
    }

    /**
     * Runs a common function test for a generic {@link FunctionN} implementation with defined arity.
     *
     * @param arity the arity of the function
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends FunctionN<String>> void commonArityFunctionTest(final int arity,
                                                                             final IntFunction<F> functionFactory) {
        commonArityFunctionTest(r -> arity, functionFactory);
    }

    /**
     * Runs a common function test for a generic {@link FunctionN} implementation with defined arity.
     *
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends FunctionN<String>> void commonArityFunctionTest(final ToIntFunction<Random> arityFunction,
                                                                        final IntFunction<F> functionFactory) {
        commonFunctionTest(arityFunction, functionFactory, FunctionTests::testArityFunction);
    }

    /**
     * Runs a common function test for a generic {@link FunctionN} implementation.
     *
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends FunctionN<String>> void commonFunctionTest(final ToIntFunction<Random> arityFunction,
                                                                        final IntFunction<F> functionFactory) {
        commonFunctionTest(arityFunction, functionFactory, FunctionTests::testFunction);
    }

    /**
     * Runs a common function test for a generic {@link FunctionN} implementation.
     *
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param testCase a test case to test the function implementation
     * @param <F> the type of the function
     */
    public static <F extends FunctionN<String>> void commonFunctionTest(final ToIntFunction<Random> arityFunction,
                                                                        final IntFunction<F> functionFactory,
                                                                        final CommonFunctionTest<F> testCase) {
        testCase.test(new Random(),
                arityFunction,
                functionFactory,
                t -> t.length() % 2 == 0,
                r -> r.nextBoolean() ? Integer.valueOf(r.nextInt(100)) : Double.valueOf(r.nextDouble()),
                l -> l.stream().reduce("", (s, o) -> s + o.toString(), (s1, s2) -> s1 + s2));
    }

    /**
     * Test all parts of a basic {@link Function1} implementation.
     * Assertion tests are defaulted to {@link Assertions#assertEquals(Object, Object)}.
     *
     * @param random a random instance
     * @param functionFactory the supplied function instance
     * @param composeFunction the function to pass into the {@code compose} method
     * @param andThenFunction the function to pass into the {@code andThen} method
     * @param inputFactory the factory to construct an input
     * @param expectedResultFactory the factory to construct the output
     * @param <I> the type of the input(s) to the function
     * @param <I0> the type of the input(s) to the chained function via {@code compose}
     * @param <R> the type of the result of the function
     * @param <R0> the type of the result of the chained function via {@code andThen}
     * @param <F> the type of the function
     */
    public static <I, I0, R, R0, F extends Function1<I, R>> void testFunction1(final Random random,
                                                                               final IntFunction<F> functionFactory,
                                                                               final Function<I0, I> composeFunction,
                                                                               final Function<R, R0> andThenFunction,
                                                                               final Function<Random, I0> inputFactory,
                                                                               final Function<List<I>, R> expectedResultFactory) {
        testFunction1(random, functionFactory, composeFunction, andThenFunction, inputFactory, expectedResultFactory, Assertions::assertEquals, Assertions::assertEquals);
    }

    /**
     * Test all parts of a basic {@link Function1} implementation.
     *
     * @param random a random instance
     * @param functionFactory the supplied function instance
     * @param composeFunction the function to pass into the {@code compose} method
     * @param andThenFunction the function to pass into the {@code andThen} method
     * @param inputFactory the factory to construct an input
     * @param expectedResultFactory the factory to construct the output
     * @param assertionTest the equality test of the expected and actual results
     * @param andThenAssertionTest the equality test of the expected and actual results of the chained function via {@code andThen}
     * @param <I> the type of the input(s) to the function
     * @param <I0> the type of the input(s) to the chained function via {@code compose}
     * @param <R> the type of the result of the function
     * @param <R0> the type of the result of the chained function via {@code andThen}
     * @param <F> the type of the function
     */
    public static <I, I0, R, R0, F extends Function1<I, R>> void testFunction1(final Random random,
                                                                               final IntFunction<F> functionFactory,
                                                                               final Function<I0, I> composeFunction,
                                                                               final Function<R, R0> andThenFunction,
                                                                               final Function<Random, I0> inputFactory,
                                                                               final Function<List<I>, R> expectedResultFactory,
                                                                               final BiConsumer<R, R> assertionTest,
                                                                               final BiConsumer<R0, R0> andThenAssertionTest) {
        testArityFunction(random, r -> 1, a -> functionFactory.apply(a).compose(composeFunction::apply), andThenFunction, inputFactory, l -> expectedResultFactory.apply(l.stream().map(composeFunction).collect(Collectors.toList())), assertionTest, andThenAssertionTest);
    }

    /**
     * Test all parts of a basic {@link FunctionN} implementation with defined arity.
     * Assertion tests are defaulted to {@link Assertions#assertEquals(Object, Object)}.
     *
     * @param random a random instance
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param andThenFunction the function to pass into the {@code andThen} method
     * @param inputFactory the factory to construct an input
     * @param expectedResultFactory the factory to construct the output
     * @param <I> the type of the input(s) to the function
     * @param <R> the type of the result of the function
     * @param <R0> the type of the result of the chained function via {@code andThen}
     * @param <F> the type of the function
     */
    public static <I, R, R0, F extends FunctionN<R>> void testArityFunction(final Random random,
                                                                            final ToIntFunction<Random> arityFunction,
                                                                            final IntFunction<F> functionFactory,
                                                                            final Function<R, R0> andThenFunction,
                                                                            final Function<Random, I> inputFactory,
                                                                            final Function<List<I>, R> expectedResultFactory) {
        testArityFunction(random, arityFunction, functionFactory, andThenFunction, inputFactory, expectedResultFactory, Assertions::assertEquals, Assertions::assertEquals);
    }

    /**
     * Test all parts of a basic {@link FunctionN} implementation with defined arity.
     *
     * @param random a random instance
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param andThenFunction the function to pass into the {@code andThen} method
     * @param inputFactory the factory to construct an input
     * @param expectedResultFactory the factory to construct the output
     * @param assertionTest the equality test of the expected and actual results
     * @param andThenAssertionTest the equality test of the expected and actual results of the chained function via {@code andThen}
     * @param <I> the type of the input(s) to the function
     * @param <R> the type of the result of the function
     * @param <R0> the type of the result of the chained function via {@code andThen}
     * @param <F> the type of the function
     */
    @SuppressWarnings("unchecked")
    public static <I, R, R0, F extends FunctionN<R>> void testArityFunction(final Random random,
                                                                            final ToIntFunction<Random> arityFunction,
                                                                            final IntFunction<F> functionFactory,
                                                                            final Function<R, R0> andThenFunction,
                                                                            final Function<Random, I> inputFactory,
                                                                            final Function<List<I>, R> expectedResultFactory,
                                                                            final BiConsumer<R, R> assertionTest,
                                                                            final BiConsumer<R0, R0> andThenAssertionTest) {
        Object[] metadata = testFunction(random, arityFunction, functionFactory, andThenFunction, inputFactory, expectedResultFactory, assertionTest, andThenAssertionTest);
        checkArity((F) metadata[2], (List<I>) metadata[0]);
    }

    /**
     * Tests all parts of a basic {@link FunctionN} implementation.
     * Assertion tests are defaulted to {@link Assertions#assertEquals(Object, Object)}.
     *
     * @param random a random instance
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param andThenFunction the function to pass into the {@code andThen} method
     * @param inputFactory the factory to construct an input
     * @param expectedResultFactory the factory to construct the output
     * @param <I> the type of the input(s) to the function
     * @param <R> the type of the result of the function
     * @param <R0> the type of the result of the chained function via {@code andThen}
     * @param <F> the type of the function
     */
    public static <I, R, R0, F extends FunctionN<R>> void testFunction(final Random random,
                                                                       final ToIntFunction<Random> arityFunction,
                                                                       final IntFunction<F> functionFactory,
                                                                       final Function<R, R0> andThenFunction,
                                                                       final Function<Random, I> inputFactory,
                                                                       final Function<List<I>, R> expectedResultFactory) {
        testFunction(random, arityFunction, functionFactory, andThenFunction, inputFactory, expectedResultFactory, Assertions::assertEquals, Assertions::assertEquals);
    }

    /**
     * Tests all parts of a basic {@link FunctionN} implementation.
     *
     * @param random a random instance
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param andThenFunction the function to pass into the {@code andThen} method
     * @param inputFactory the factory to construct an input
     * @param expectedResultFactory the factory to construct the output
     * @param assertionTest the equality test of the expected and actual results
     * @param andThenAssertionTest the equality test of the expected and actual results of the chained function via {@code andThen}
     * @param <I> the type of the input(s) to the function
     * @param <R> the type of the result of the function
     * @param <R0> the type of the result of the chained function via {@code andThen}
     * @param <F> the type of the function
     * @return metadata associated with the function in the form of inputs, result, and the function used
     */
    @SuppressWarnings("unchecked")
    public static <I, R, R0, F extends FunctionN<R>> Object[] testFunction(final Random random,
                                                                           final ToIntFunction<Random> arityFunction,
                                                                           final IntFunction<F> functionFactory,
                                                                           final Function<R, R0> andThenFunction,
                                                                           final Function<Random, I> inputFactory,
                                                                           final Function<List<I>, R> expectedResultFactory,
                                                                           final BiConsumer<R, R> assertionTest,
                                                                           final BiConsumer<R0, R0> andThenAssertionTest) {
        Object[] metadata = testBaseFunction(random, arityFunction, functionFactory, inputFactory, expectedResultFactory, assertionTest);
        testAndThen((F) metadata[2], andThenFunction, (List<I>) metadata[0], andThenFunction.apply((R) metadata[1]), andThenAssertionTest);
        return metadata;
    }

    /**
     * Tests a basic {@link FunctionN} implementation with {@code apply}.
     *
     * @param random a random instance
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param inputFactory the factory to construct an input
     * @param expectedResultFactory the factory to construct the output
     * @param assertionTest the equality test of the expected and actual results
     * @param <I> the type of the input(s) to the function
     * @param <R> the type of the result of the function
     * @param <F> the type of the function
     * @return metadata associated with the function in the form of inputs, result, and the function used
     */
    public static <I, R, F extends FunctionN<R>> Object[] testBaseFunction(final Random random,
                                                                           final ToIntFunction<Random> arityFunction,
                                                                           final IntFunction<F> functionFactory,
                                                                           final Function<Random, I> inputFactory,
                                                                           final Function<List<I>, R> expectedResultFactory,
                                                                           final BiConsumer<R, R> assertionTest) {
        int arity = arityFunction.applyAsInt(random);
        List<I> randomInputs = IntStream.range(0, arity).mapToObj(i -> inputFactory.apply(random)).collect(Collectors.toList());
        R expectedResult = expectedResultFactory.apply(randomInputs);
        F function = functionFactory.apply(arity);
        testApply(function, randomInputs, expectedResult, assertionTest);
        return new Object[] { randomInputs, expectedResult, function };
    }

    /**
     * Tests the {@code apply} method of a particular function.
     *
     * @param function the function being tested
     * @param inputs the inputs to the function
     * @param expectedResult the expected result of the function
     * @param assertionTest the equality test of the expected and actual results
     * @param <I> the type of the input(s) to the function
     * @param <R> the type of the result of the function
     * @param <F> the type of the function
     */
    public static <I, R, F extends FunctionN<R>> void testApply(final F function,
                                                                final List<I> inputs,
                                                                final R expectedResult,
                                                                final BiConsumer<R, R> assertionTest) {
        assertionTest.accept(expectedResult, function.applyAllUnchecked(inputs.toArray()));
    }

    /**
     * Tests the {@code andThen} method of a particular function.
     *
     * @param function the function being tested
     * @param andThenFunction the function's {@code andThen} method
     * @param inputs the inputs to the function
     * @param expectedResult the expected result of the function
     * @param assertionTest the equality test of the expected and actual results
     * @param <I> the type of the input(s) to the function
     * @param <R> the type of the result of the function
     * @param <V> the type of the result of the chained function via {@code andThen}
     * @param <F> the type of the function
     */
    public static <I, R, V, F extends FunctionN<R>> void testAndThen(final F function,
                                                                     final Function<R, V> andThenFunction,
                                                                     final List<I> inputs,
                                                                     final V expectedResult,
                                                                     final BiConsumer<V, V> assertionTest) {
        testApply(function.andThen(andThenFunction::apply), inputs, expectedResult, assertionTest);
    }

    /**
     * Tests the arity of a particular function.
     *
     * @param function the function being tested
     * @param inputs the inputs to the function
     * @param <I> the type of the input(s) to the function
     * @param <R> the type of the result of the function
     * @param <F> the type of the function
     */
    public static <I, R, F extends FunctionN<R>> void checkArity(final F function,
                                                                 final List<I> inputs) {
        Assertions.assertDoesNotThrow(() -> function.sizedApplyAllUnchecked(inputs.toArray()));
    }

    /**
     * A common function test to verify all working parts of a {@link FunctionN} implementation.
     *
     * @param <F>
     */
    @FunctionalInterface
    public interface CommonFunctionTest<F extends FunctionN<String>> {

        /**
         * Runs a common function test for a {@link FunctionN} implementation.
         *
         * @param random a random instance
         * @param arityFunction the arity of the function
         * @param functionFactory the supplied function instance
         * @param andThenFunction the function to pass into the {@code andThen} method
         * @param inputFactory the factory to construct an input
         * @param expectedResultFactory the factory to construct the output
         */
        void test(final Random random,
                  final ToIntFunction<Random> arityFunction,
                  final IntFunction<F> functionFactory,
                  final Function<String, Boolean> andThenFunction,
                  final Function<Random, Object> inputFactory,
                  final Function<List<Object>, String> expectedResultFactory);
    }
}
