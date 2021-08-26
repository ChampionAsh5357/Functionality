/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.test;

import net.ashwork.functionality.primitive.floats.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;

/**
 * A testing class that tests all {@code float}-based functions.
 */
public final class FloatFunctionTests {

    // Standardized Functions for Testing
    public static final Function<Object[], Float> FUNCTION = o -> FunctionTests.FLOAT.apply(FunctionTests.FUNCTION.apply(o));
    public static final Function<List<Object>, Float> LIST_FUNCTION = l -> FUNCTION.apply(FunctionTests.LIST.apply(l));
    public static final Function<Float, String> FLOAT = Object::toString;

    /**
     * Tests the n-arity {@code float} function.
     */
    @Test
    public void n() {
        ToFloatFunctionN function = FUNCTION::apply;
        toFloatCFT(r -> r.nextInt(100), a -> function);
        arityToFloatCFT(r -> r.nextInt(100), a -> new ToFloatFunctionN.Instance(a, function::applyAllUnchecked));
    }

    /**
     * Tests the zero-arity {@code float} function.
     */
    @Test
    public void zero() {
        ToFloatFunction0 function = () -> FUNCTION.apply(new Object[]{});
        arityToFloatCFT(0, a -> function);
    }

    /**
     * Tests the one-arity {@code float} function.
     */
    @Test
    public void one() {
        ToFloatFunction1<Object> function = o1 -> FUNCTION.apply(new Object[]{o1});
        toFloat1CFT(a -> function);
        FloatFunction1<String> bFunction = FLOAT::apply;
        floatObject1CFT(a -> bFunction);
    }

    /**
     * Tests the two-arity {@code float} function.
     */
    @Test
    public void two() {
        ToFloatFunction2<Object, Object> function = (o1, o2) -> FUNCTION.apply(new Object[]{o1, o2});
        arityToFloatCFT(2, a -> function);
    }

    /**
     * Runs a common function test for a generic {@link FloatFunction1} implementation.
     *
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends FloatFunction1<String>> void floatObject1CFT(final IntFunction<F> functionFactory) {
        FunctionTests.objectCFT(null, functionFactory, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, b -> o -> f.apply(b).apply((float) o), o1 -> FUNCTION.apply(new Object[]{o1}), t, i, e));
        FunctionTests.objectCFT(null, functionFactory, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, b -> o -> f.apply(b).boxInput().apply((float) o), o1 -> FUNCTION.apply(new Object[]{o1}), t, i, e));
    }

    /**
     * Runs a common function test for a generic {@link ToFloatFunction1} implementation.
     *
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ToFloatFunction1<Object>> void toFloat1CFT(final IntFunction<F> functionFactory) {
        toFloatCFT(null, functionFactory, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> f.apply(i0)::applyAsFloat, FunctionTests.OBJECT, t, i, e));
        toFloatCFT(null, functionFactory, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> f.apply(i0).boxResult()::apply, FunctionTests.OBJECT, t, i, e));
    }

    /**
     * Runs a common function test for a generic {@link ToFloatFunctionN} implementation with defined arity.
     *
     * @param arity the arity of the function
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ToFloatFunctionN> void arityToFloatCFT(final int arity,
                                                                        final IntFunction<F> functionFactory) {
        arityToFloatCFT(r -> arity, functionFactory);
    }

    /**
     * Runs a common function test for a generic {@link ToFloatFunctionN} implementation with defined arity.
     *
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ToFloatFunctionN> void arityToFloatCFT(final ToIntFunction<Random> arityFunction,
                                                                        final IntFunction<F> functionFactory) {
        toFloatCFT(arityFunction, functionFactory, FunctionTests::testArityFunction);
    }

    /**
     * Runs a common function test for a generic {@link ToFloatFunctionN} implementation.
     *
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ToFloatFunctionN> void toFloatCFT(final ToIntFunction<Random> arityFunction,
                                                                   final IntFunction<F> functionFactory) {
        toFloatCFT(arityFunction, functionFactory, FunctionTests::testFunction);
    }

    /**
     * Runs a common function test for a generic {@link ToFloatFunctionN} implementation.
     *
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param testCase a test case to test the function implementation
     * @param <F> the type of the function
     */
    public static <F extends ToFloatFunctionN> void toFloatCFT(final ToIntFunction<Random> arityFunction,
                                                                   final IntFunction<F> functionFactory,
                                                                   final ToFloatCFT<F> testCase) {
        testCase.test(FunctionTests.RANDOM,
                arityFunction,
                functionFactory,
                t -> t * 5,
                FunctionTests.GENERATOR,
                LIST_FUNCTION);
    }

    /**
     * A common function test to verify all working parts of a {@code float} function implementation.
     *
     * @param <F> the type of the function
     */
    @FunctionalInterface
    public interface ToFloatCFT<F extends ToFloatFunctionN> extends ToFloatCommonFunctionTest<Object, Float, F> {}

    /**
     * A common function test to verify all working parts of a {@code float} function implementation.
     *
     * @param <I> the type of the input(s) to the function
     * @param <V> the type of the result of the chained function via {@code andThen}
     * @param <F> the type of the function
     */
    @FunctionalInterface
    public interface ToFloatCommonFunctionTest<I, V, F extends ToFloatFunctionN> extends FunctionTests.CommonFunctionTest<I, Float, V, F> {}
}
