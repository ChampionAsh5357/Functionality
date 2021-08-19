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
     * A random instance.
     */
    public static final Random RANDOM = new Random();

    // Standardized Functions for Testing
    public static final Function<Random, Object> GENERATOR = r -> r.nextBoolean() ? Integer.valueOf(r.nextInt(100)) : Double.valueOf(r.nextDouble());
    public static final Function<List<Object>, Object[]> LIST = List::toArray;
    public static final Function<Object[], String> FUNCTION = args -> Arrays.stream(args).reduce("", (s, o) -> s + o.toString(), (s1, s2) -> s1 + s2);
    public static final Function<List<Object>, String> LIST_FUNCTION = l -> FUNCTION.apply(LIST.apply(l));
    public static final Function<Object, Object> OBJECT = o -> o + " ";
    public static final Function<String, Character> CHAR = s -> (char) s.chars().findFirst().orElse(0);
    public static final Function<String, Integer> INT = String::length;
    public static final Function<String, Boolean> BOOLEAN = s -> INT.apply(s) % 2 == 0;
    public static final Function<String, Double> DOUBLE = s -> INT.apply(s) / 3.0;
    public static final Function<String, Float> FLOAT = s -> INT.apply(s) / 3.0f;
    public static final Function<String, Byte> BYTE = s -> INT.apply(s).byteValue();
    public static final Function<String, Short> SHORT = s -> INT.apply(s).shortValue();
    public static final Function<String, Long> LONG = s -> INT.apply(s).longValue();

    /**
     * Tests the n-arity function.
     */
    @Test
    public void n() {
        FunctionN<String> function = FUNCTION::apply;
        objectCFT(r -> r.nextInt(100), a -> function);
        arityObjectCFT(r -> r.nextInt(100), a -> new FunctionN.Instance<>(a, function::applyAllUnchecked));
    }

    /**
     * Tests the zero-arity function.
     */
    @Test
    public void zero() {
        Function0<String> function = () -> FUNCTION.apply(new Object[]{});
        arityObjectCFT(0, a -> function);
    }

    /**
     * Tests the one-arity function.
     */
    @Test
    public void one() {
        Function1<Object, String> function = o1 -> FUNCTION.apply(new Object[]{o1});
        object1CFT(a -> function);
    }

    /**
     * Tests the two-arity function.
     */
    @Test
    public void two() {
        Function2<Object, Object, String> function = (o1, o2) -> FUNCTION.apply(new Object[]{o1, o2});
        arityObjectCFT(2, a -> function);
    }

    /**
     * Runs a common function test for a generic {@link Function1} implementation.
     *
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends Function1<Object, String>> void object1CFT(final IntFunction<F> functionFactory) {
        objectCFT(null, functionFactory, (r, a, f, t, i, e) -> testFunction1(r, f, OBJECT, t, i, e));
    }

    /**
     * Runs a common function test for a generic {@link FunctionN} implementation with defined arity.
     *
     * @param arity the arity of the function
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends FunctionN<String>> void arityObjectCFT(final int arity,
                                                                    final IntFunction<F> functionFactory) {
        arityObjectCFT(r -> arity, functionFactory);
    }

    /**
     * Runs a common function test for a generic {@link FunctionN} implementation with defined arity.
     *
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends FunctionN<String>> void arityObjectCFT(final ToIntFunction<Random> arityFunction,
                                                                    final IntFunction<F> functionFactory) {
        objectCFT(arityFunction, functionFactory, FunctionTests::testArityFunction);
    }

    /**
     * Runs a common function test for a generic {@link FunctionN} implementation.
     *
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends FunctionN<String>> void objectCFT(final ToIntFunction<Random> arityFunction,
                                                               final IntFunction<F> functionFactory) {
        objectCFT(arityFunction, functionFactory, FunctionTests::testFunction);
    }

    /**
     * Runs a common function test for a generic {@link FunctionN} implementation.
     *
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param testCase a test case to test the function implementation
     * @param <F> the type of the function
     */
    public static <F extends FunctionN<String>> void objectCFT(final ToIntFunction<Random> arityFunction,
                                                               final IntFunction<F> functionFactory,
                                                               final ObjectCFT<F> testCase) {
        testCase.test(RANDOM,
                arityFunction,
                functionFactory,
                t -> t.length() % 2 == 0,
                GENERATOR,
                LIST_FUNCTION);
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
     * A common function test to verify all working parts of an object based function implementation.
     *
     * @param <F> the type of the function
     */
    @FunctionalInterface
    public interface ObjectCFT<F extends FunctionN<String>> extends CommonFunctionTest<Object, String, Boolean, F> {}

    /**
     * A common function test to verify all working parts of a function implementation.
     *
     * @param <I> the type of the input(s) to the function
     * @param <R> the type of the result of the function
     * @param <V> the type of the result of the chained function via {@code andThen}
     * @param <F> the type of the function
     */
    @FunctionalInterface
    public interface CommonFunctionTest<I, R, V, F extends FunctionN<R>> {
        
        /**
         * Runs a common function test for a function implementation.
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
                  final Function<R, V> andThenFunction,
                  final Function<Random, I> inputFactory,
                  final Function<List<I>, R> expectedResultFactory);
    }
}
