/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.test;

import net.ashwork.functionality.primitive.ints.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;

/**
 * A testing class that tests all {@code int}-based functions.
 */
public final class IntFunctionTests {

    // Standardized Functions for Testing
    public static final Function<Object[], Integer> FUNCTION = o -> FunctionTests.INT.apply(FunctionTests.FUNCTION.apply(o));
    public static final Function<List<Object>, Integer> LIST_FUNCTION = l -> FUNCTION.apply(FunctionTests.LIST.apply(l));
    public static final Function<Integer, String> INT = Object::toString;

    /**
     * Tests the n-arity {@code int} function.
     */
    @Test
    public void n() {
        ToIntFunctionN function = FUNCTION::apply;
        toIntCFT(r -> r.nextInt(100), a -> function);
        arityToIntCFT(r -> r.nextInt(100), a -> new ToIntFunctionN.Instance(a, function::applyAllUnchecked));
    }

    /**
     * Tests the zero-arity {@code int} function.
     */
    @Test
    public void zero() {
        ToIntFunction0 function = () -> FUNCTION.apply(new Object[]{});
        arityToIntCFT(0, a -> function);
    }

    /**
     * Tests the one-arity {@code int} function.
     */
    @Test
    public void one() {
        ToIntFunction1<Object> function = o1 -> FUNCTION.apply(new Object[]{o1});
        toInt1CFT(a -> function);
        IntFunction1<String> bFunction = INT::apply;
        intObject1CFT(a -> bFunction);
    }

    /**
     * Tests the two-arity {@code int} function.
     */
    @Test
    public void two() {
        ToIntFunction2<Object, Object> function = (o1, o2) -> FUNCTION.apply(new Object[]{o1, o2});
        arityToIntCFT(2, a -> function);
    }

    /**
     * Runs a common function test for a generic {@link IntFunction1} implementation.
     *
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends IntFunction1<String>> void intObject1CFT(final IntFunction<F> functionFactory) {
        FunctionTests.objectCFT(null, functionFactory, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, b -> o -> f.apply(b).apply((int) o), o1 -> FUNCTION.apply(new Object[]{o1}), t, i, e));
        FunctionTests.objectCFT(null, functionFactory, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, b -> o -> f.apply(b).boxInput().apply((int) o), o1 -> FUNCTION.apply(new Object[]{o1}), t, i, e));
    }

    /**
     * Runs a common function test for a generic {@link ToIntFunction1} implementation.
     *
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ToIntFunction1<Object>> void toInt1CFT(final IntFunction<F> functionFactory) {
        toIntCFT(null, functionFactory, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> f.apply(i0)::applyAsInt, FunctionTests.OBJECT, t, i, e));
        toIntCFT(null, functionFactory, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> f.apply(i0).boxResult()::apply, FunctionTests.OBJECT, t, i, e));
    }

    /**
     * Runs a common function test for a generic {@link ToIntFunctionN} implementation with defined arity.
     *
     * @param arity the arity of the function
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ToIntFunctionN> void arityToIntCFT(final int arity,
                                                                        final IntFunction<F> functionFactory) {
        arityToIntCFT(r -> arity, functionFactory);
    }

    /**
     * Runs a common function test for a generic {@link ToIntFunctionN} implementation with defined arity.
     *
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ToIntFunctionN> void arityToIntCFT(final ToIntFunction<Random> arityFunction,
                                                                        final IntFunction<F> functionFactory) {
        toIntCFT(arityFunction, functionFactory, FunctionTests::testArityFunction);
    }

    /**
     * Runs a common function test for a generic {@link ToIntFunctionN} implementation.
     *
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ToIntFunctionN> void toIntCFT(final ToIntFunction<Random> arityFunction,
                                                                   final IntFunction<F> functionFactory) {
        toIntCFT(arityFunction, functionFactory, FunctionTests::testFunction);
    }

    /**
     * Runs a common function test for a generic {@link ToIntFunctionN} implementation.
     *
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param testCase a test case to test the function implementation
     * @param <F> the type of the function
     */
    public static <F extends ToIntFunctionN> void toIntCFT(final ToIntFunction<Random> arityFunction,
                                                                   final IntFunction<F> functionFactory,
                                                                   final ToIntCFT<F> testCase) {
        testCase.test(FunctionTests.RANDOM,
                arityFunction,
                functionFactory,
                t -> ~t,
                FunctionTests.GENERATOR,
                LIST_FUNCTION);
    }

    /**
     * A common function test to verify all working parts of a {@code int} function implementation.
     *
     * @param <F> the type of the function
     */
    @FunctionalInterface
    public interface ToIntCFT<F extends ToIntFunctionN> extends ToIntCommonFunctionTest<Object, Integer, F> {}

    /**
     * A common function test to verify all working parts of a {@code int} function implementation.
     *
     * @param <I> the type of the input(s) to the function
     * @param <V> the type of the result of the chained function via {@code andThen}
     * @param <F> the type of the function
     */
    @FunctionalInterface
    public interface ToIntCommonFunctionTest<I, V, F extends ToIntFunctionN> extends FunctionTests.CommonFunctionTest<I, Integer, V, F> {}
}
