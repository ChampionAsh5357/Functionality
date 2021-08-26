/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.test;

import net.ashwork.functionality.primitive.shorts.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;

/**
 * A testing class that tests all {@code short}-based functions.
 */
public final class ShortFunctionTests {

    // Standardized Functions for Testing
    public static final Function<Object[], Short> FUNCTION = o -> FunctionTests.SHORT.apply(FunctionTests.FUNCTION.apply(o));
    public static final Function<List<Object>, Short> LIST_FUNCTION = l -> FUNCTION.apply(FunctionTests.LIST.apply(l));
    public static final Function<Short, String> SHORT = Object::toString;

    /**
     * Tests the n-arity {@code short} function.
     */
    @Test
    public void n() {
        ToShortFunctionN function = FUNCTION::apply;
        toShortCFT(r -> r.nextInt(100), a -> function);
        arityToShortCFT(r -> r.nextInt(100), a -> new ToShortFunctionN.Instance(a, function::applyAllUnchecked));
    }

    /**
     * Tests the zero-arity {@code short} function.
     */
    @Test
    public void zero() {
        ToShortFunction0 function = () -> FUNCTION.apply(new Object[]{});
        arityToShortCFT(0, a -> function);
    }

    /**
     * Tests the one-arity {@code short} function.
     */
    @Test
    public void one() {
        ToShortFunction1<Object> function = o1 -> FUNCTION.apply(new Object[]{o1});
        toShort1CFT(a -> function);
        ShortFunction1<String> bFunction = SHORT::apply;
        shortObject1CFT(a -> bFunction);
    }

    /**
     * Tests the two-arity {@code short} function.
     */
    @Test
    public void two() {
        ToShortFunction2<Object, Object> function = (o1, o2) -> FUNCTION.apply(new Object[]{o1, o2});
        arityToShortCFT(2, a -> function);
    }

    /**
     * Runs a common function test for a generic {@link ShortFunction1} implementation.
     *
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ShortFunction1<String>> void shortObject1CFT(final IntFunction<F> functionFactory) {
        FunctionTests.objectCFT(null, functionFactory, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, b -> o -> f.apply(b).apply((short) o), o1 -> FUNCTION.apply(new Object[]{o1}), t, i, e));
        FunctionTests.objectCFT(null, functionFactory, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, b -> o -> f.apply(b).boxInput().apply((short) o), o1 -> FUNCTION.apply(new Object[]{o1}), t, i, e));
    }

    /**
     * Runs a common function test for a generic {@link ToShortFunction1} implementation.
     *
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ToShortFunction1<Object>> void toShort1CFT(final IntFunction<F> functionFactory) {
        toShortCFT(null, functionFactory, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> f.apply(i0)::applyAsShort, FunctionTests.OBJECT, t, i, e));
        toShortCFT(null, functionFactory, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> f.apply(i0).boxResult()::apply, FunctionTests.OBJECT, t, i, e));
    }

    /**
     * Runs a common function test for a generic {@link ToShortFunctionN} implementation with defined arity.
     *
     * @param arity the arity of the function
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ToShortFunctionN> void arityToShortCFT(final int arity,
                                                                        final IntFunction<F> functionFactory) {
        arityToShortCFT(r -> arity, functionFactory);
    }

    /**
     * Runs a common function test for a generic {@link ToShortFunctionN} implementation with defined arity.
     *
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ToShortFunctionN> void arityToShortCFT(final ToIntFunction<Random> arityFunction,
                                                                        final IntFunction<F> functionFactory) {
        toShortCFT(arityFunction, functionFactory, FunctionTests::testArityFunction);
    }

    /**
     * Runs a common function test for a generic {@link ToShortFunctionN} implementation.
     *
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ToShortFunctionN> void toShortCFT(final ToIntFunction<Random> arityFunction,
                                                                   final IntFunction<F> functionFactory) {
        toShortCFT(arityFunction, functionFactory, FunctionTests::testFunction);
    }

    /**
     * Runs a common function test for a generic {@link ToShortFunctionN} implementation.
     *
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param testCase a test case to test the function implementation
     * @param <F> the type of the function
     */
    public static <F extends ToShortFunctionN> void toShortCFT(final ToIntFunction<Random> arityFunction,
                                                                   final IntFunction<F> functionFactory,
                                                                   final ToShortCFT<F> testCase) {
        testCase.test(FunctionTests.RANDOM,
                arityFunction,
                functionFactory,
                t -> (short) ~t,
                FunctionTests.GENERATOR,
                LIST_FUNCTION);
    }

    /**
     * A common function test to verify all working parts of a {@code short} function implementation.
     *
     * @param <F> the type of the function
     */
    @FunctionalInterface
    public interface ToShortCFT<F extends ToShortFunctionN> extends ToShortCommonFunctionTest<Object, Short, F> {}

    /**
     * A common function test to verify all working parts of a {@code short} function implementation.
     *
     * @param <I> the type of the input(s) to the function
     * @param <V> the type of the result of the chained function via {@code andThen}
     * @param <F> the type of the function
     */
    @FunctionalInterface
    public interface ToShortCommonFunctionTest<I, V, F extends ToShortFunctionN> extends FunctionTests.CommonFunctionTest<I, Short, V, F> {}
}
