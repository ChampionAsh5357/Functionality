/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.test;

import net.ashwork.functionality.primitive.doubles.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;

/**
 * A testing class that tests all {@code double}-based functions.
 */
public final class DoubleFunctionTests {

    // Standardized Functions for Testing
    public static final Function<Object[], Double> FUNCTION = o -> FunctionTests.DOUBLE.apply(FunctionTests.FUNCTION.apply(o));
    public static final Function<List<Object>, Double> LIST_FUNCTION = l -> FUNCTION.apply(FunctionTests.LIST.apply(l));
    public static final Function<Double, String> DOUBLE = Object::toString;

    /**
     * Tests the n-arity {@code double} function.
     */
    @Test
    public void n() {
        ToDoubleFunctionN function = FUNCTION::apply;
        toDoubleCFT(r -> r.nextInt(100), a -> function);
        arityToDoubleCFT(r -> r.nextInt(100), a -> new ToDoubleFunctionN.Instance(a, function::applyAllUnchecked));
    }

    /**
     * Tests the zero-arity {@code double} function.
     */
    @Test
    public void zero() {
        ToDoubleFunction0 function = () -> FUNCTION.apply(new Object[]{});
        arityToDoubleCFT(0, a -> function);
    }

    /**
     * Tests the one-arity {@code double} function.
     */
    @Test
    public void one() {
        ToDoubleFunction1<Object> function = o1 -> FUNCTION.apply(new Object[]{o1});
        toDouble1CFT(a -> function);
        DoubleFunction1<String> bFunction = DOUBLE::apply;
        doubleObject1CFT(a -> bFunction);
    }

    /**
     * Tests the two-arity {@code double} function.
     */
    @Test
    public void two() {
        ToDoubleFunction2<Object, Object> function = (o1, o2) -> FUNCTION.apply(new Object[]{o1, o2});
        arityToDoubleCFT(2, a -> function);
    }

    /**
     * Runs a common function test for a generic {@link DoubleFunction1} implementation.
     *
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends DoubleFunction1<String>> void doubleObject1CFT(final IntFunction<F> functionFactory) {
        FunctionTests.objectCFT(null, functionFactory, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, b -> o -> f.apply(b).apply((double) o), o1 -> FUNCTION.apply(new Object[]{o1}), t, i, e));
        FunctionTests.objectCFT(null, functionFactory, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, b -> o -> f.apply(b).boxInput().apply((double) o), o1 -> FUNCTION.apply(new Object[]{o1}), t, i, e));
    }

    /**
     * Runs a common function test for a generic {@link ToDoubleFunction1} implementation.
     *
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ToDoubleFunction1<Object>> void toDouble1CFT(final IntFunction<F> functionFactory) {
        toDoubleCFT(null, functionFactory, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> f.apply(i0)::applyAsDouble, FunctionTests.OBJECT, t, i, e));
        toDoubleCFT(null, functionFactory, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> f.apply(i0).boxResult()::apply, FunctionTests.OBJECT, t, i, e));
    }

    /**
     * Runs a common function test for a generic {@link ToDoubleFunctionN} implementation with defined arity.
     *
     * @param arity the arity of the function
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ToDoubleFunctionN> void arityToDoubleCFT(final int arity,
                                                                        final IntFunction<F> functionFactory) {
        arityToDoubleCFT(r -> arity, functionFactory);
    }

    /**
     * Runs a common function test for a generic {@link ToDoubleFunctionN} implementation with defined arity.
     *
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ToDoubleFunctionN> void arityToDoubleCFT(final ToIntFunction<Random> arityFunction,
                                                                        final IntFunction<F> functionFactory) {
        toDoubleCFT(arityFunction, functionFactory, FunctionTests::testArityFunction);
    }

    /**
     * Runs a common function test for a generic {@link ToDoubleFunctionN} implementation.
     *
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ToDoubleFunctionN> void toDoubleCFT(final ToIntFunction<Random> arityFunction,
                                                                   final IntFunction<F> functionFactory) {
        toDoubleCFT(arityFunction, functionFactory, FunctionTests::testFunction);
    }

    /**
     * Runs a common function test for a generic {@link ToDoubleFunctionN} implementation.
     *
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param testCase a test case to test the function implementation
     * @param <F> the type of the function
     */
    public static <F extends ToDoubleFunctionN> void toDoubleCFT(final ToIntFunction<Random> arityFunction,
                                                                   final IntFunction<F> functionFactory,
                                                                   final ToDoubleCFT<F> testCase) {
        testCase.test(FunctionTests.RANDOM,
                arityFunction,
                functionFactory,
                t -> t * 5,
                FunctionTests.GENERATOR,
                LIST_FUNCTION);
    }

    /**
     * A common function test to verify all working parts of a {@code double} function implementation.
     *
     * @param <F> the type of the function
     */
    @FunctionalInterface
    public interface ToDoubleCFT<F extends ToDoubleFunctionN> extends ToDoubleCommonFunctionTest<Object, Double, F> {}

    /**
     * A common function test to verify all working parts of a {@code double} function implementation.
     *
     * @param <I> the type of the input(s) to the function
     * @param <V> the type of the result of the chained function via {@code andThen}
     * @param <F> the type of the function
     */
    @FunctionalInterface
    public interface ToDoubleCommonFunctionTest<I, V, F extends ToDoubleFunctionN> extends FunctionTests.CommonFunctionTest<I, Double, V, F> {}
}
