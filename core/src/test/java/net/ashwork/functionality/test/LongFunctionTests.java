/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.test;

import net.ashwork.functionality.primitive.longs.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;

/**
 * A testing class that tests all {@code long}-based functions.
 */
public final class LongFunctionTests {

    // Standardized Functions for Testing
    public static final Function<Object[], Long> FUNCTION = o -> FunctionTests.LONG.apply(FunctionTests.FUNCTION.apply(o));
    public static final Function<List<Object>, Long> LIST_FUNCTION = l -> FUNCTION.apply(FunctionTests.LIST.apply(l));
    public static final Function<Long, String> LONG = Object::toString;

    /**
     * Tests the n-arity {@code long} function.
     */
    @Test
    public void n() {
        ToLongFunctionN function = FUNCTION::apply;
        toLongCFT(r -> r.nextInt(100), a -> function);
        arityToLongCFT(r -> r.nextInt(100), a -> new ToLongFunctionN.Instance(a, function::applyAllUnchecked));
    }

    /**
     * Tests the zero-arity {@code long} function.
     */
    @Test
    public void zero() {
        ToLongFunction0 function = () -> FUNCTION.apply(new Object[]{});
        arityToLongCFT(0, a -> function);
    }

    /**
     * Tests the one-arity {@code long} function.
     */
    @Test
    public void one() {
        ToLongFunction1<Object> function = o1 -> FUNCTION.apply(new Object[]{o1});
        toLong1CFT(a -> function);
        LongFunction1<String> bFunction = LONG::apply;
        longObject1CFT(a -> bFunction);
    }

    /**
     * Tests the two-arity {@code long} function.
     */
    @Test
    public void two() {
        ToLongFunction2<Object, Object> function = (o1, o2) -> FUNCTION.apply(new Object[]{o1, o2});
        arityToLongCFT(2, a -> function);
    }

    /**
     * Runs a common function test for a generic {@link LongFunction1} implementation.
     *
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends LongFunction1<String>> void longObject1CFT(final IntFunction<F> functionFactory) {
        FunctionTests.objectCFT(null, functionFactory, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, b -> o -> f.apply(b).apply((long) o), o1 -> FUNCTION.apply(new Object[]{o1}), t, i, e));
    }

    /**
     * Runs a common function test for a generic {@link ToLongFunction1} implementation.
     *
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ToLongFunction1<Object>> void toLong1CFT(final IntFunction<F> functionFactory) {
        toLongCFT(null, functionFactory, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> f.apply(i0)::applyAsLong, FunctionTests.OBJECT, t, i, e));
    }

    /**
     * Runs a common function test for a generic {@link ToLongFunctionN} implementation with defined arity.
     *
     * @param arity the arity of the function
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ToLongFunctionN> void arityToLongCFT(final int arity,
                                                                        final IntFunction<F> functionFactory) {
        arityToLongCFT(r -> arity, functionFactory);
    }

    /**
     * Runs a common function test for a generic {@link ToLongFunctionN} implementation with defined arity.
     *
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ToLongFunctionN> void arityToLongCFT(final ToIntFunction<Random> arityFunction,
                                                                        final IntFunction<F> functionFactory) {
        toLongCFT(arityFunction, functionFactory, FunctionTests::testArityFunction);
    }

    /**
     * Runs a common function test for a generic {@link ToLongFunctionN} implementation.
     *
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ToLongFunctionN> void toLongCFT(final ToIntFunction<Random> arityFunction,
                                                                   final IntFunction<F> functionFactory) {
        toLongCFT(arityFunction, functionFactory, FunctionTests::testFunction);
    }

    /**
     * Runs a common function test for a generic {@link ToLongFunctionN} implementation.
     *
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param testCase a test case to test the function implementation
     * @param <F> the type of the function
     */
    public static <F extends ToLongFunctionN> void toLongCFT(final ToIntFunction<Random> arityFunction,
                                                                   final IntFunction<F> functionFactory,
                                                                   final ToLongCFT<F> testCase) {
        testCase.test(FunctionTests.RANDOM,
                arityFunction,
                functionFactory,
                t -> ~t,
                FunctionTests.GENERATOR,
                LIST_FUNCTION);
    }

    /**
     * A common function test to verify all working parts of a {@code long} function implementation.
     *
     * @param <F> the type of the function
     */
    @FunctionalInterface
    public interface ToLongCFT<F extends ToLongFunctionN> extends ToLongCommonFunctionTest<Object, Long, F> {}

    /**
     * A common function test to verify all working parts of a {@code long} function implementation.
     *
     * @param <I> the type of the input(s) to the function
     * @param <V> the type of the result of the chained function via {@code andThen}
     * @param <F> the type of the function
     */
    @FunctionalInterface
    public interface ToLongCommonFunctionTest<I, V, F extends ToLongFunctionN> extends FunctionTests.CommonFunctionTest<I, Long, V, F> {}
}
