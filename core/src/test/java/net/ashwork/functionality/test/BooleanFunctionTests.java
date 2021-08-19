/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.test;

import net.ashwork.functionality.primitive.booleans.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;

/**
 * A testing class that tests all {@code boolean}-based functions.
 */
public final class BooleanFunctionTests {

    // Standardized Functions for Testing
    public static final Function<Object[], Boolean> FUNCTION = o -> FunctionTests.BOOLEAN.apply(FunctionTests.FUNCTION.apply(o));
    public static final Function<List<Object>, Boolean> LIST_FUNCTION = l -> FUNCTION.apply(FunctionTests.LIST.apply(l));
    public static final Function<Boolean, String> BOOLEAN = Object::toString;

    /**
     * Tests the n-arity {@code boolean} function.
     */
    @Test
    public void n() {
        ToBooleanFunctionN function = FUNCTION::apply;
        toBooleanCFT(r -> r.nextInt(100), a -> function);
        arityToBooleanCFT(r -> r.nextInt(100), a -> new ToBooleanFunctionN.Instance(a, function::applyAllUnchecked));
    }

    /**
     * Tests the zero-arity {@code boolean} function.
     */
    @Test
    public void zero() {
        ToBooleanFunction0 function = () -> FUNCTION.apply(new Object[]{});
        arityToBooleanCFT(0, a -> function);
    }

    /**
     * Tests the one-arity {@code boolean} function.
     */
    @Test
    public void one() {
        ToBooleanFunction1<Object> function = o1 -> FUNCTION.apply(new Object[]{o1});
        toBoolean1CFT(a -> function);
        BooleanFunction1<String> bFunction = BOOLEAN::apply;
        booleanObject1CFT(a -> bFunction);
    }

    /**
     * Tests the two-arity {@code boolean} function.
     */
    @Test
    public void two() {
        ToBooleanFunction2<Object, Object> function = (o1, o2) -> FUNCTION.apply(new Object[]{o1, o2});
        arityToBooleanCFT(2, a -> function);
    }

    /**
     * Runs a common function test for a generic {@link BooleanFunction1} implementation.
     *
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends BooleanFunction1<String>> void booleanObject1CFT(final IntFunction<F> functionFactory) {
        FunctionTests.objectCFT(null, functionFactory, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, b -> o -> f.apply(b).apply((boolean) o), o1 -> FUNCTION.apply(new Object[]{o1}), t, i, e));
    }

    /**
     * Runs a common function test for a generic {@link ToBooleanFunction1} implementation.
     *
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ToBooleanFunction1<Object>> void toBoolean1CFT(final IntFunction<F> functionFactory) {
        toBooleanCFT(null, functionFactory, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> f.apply(i0)::applyAsBoolean, FunctionTests.OBJECT, t, i, e));
    }

    /**
     * Runs a common function test for a generic {@link ToBooleanFunctionN} implementation with defined arity.
     *
     * @param arity the arity of the function
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ToBooleanFunctionN> void arityToBooleanCFT(final int arity,
                                                                        final IntFunction<F> functionFactory) {
        arityToBooleanCFT(r -> arity, functionFactory);
    }

    /**
     * Runs a common function test for a generic {@link ToBooleanFunctionN} implementation with defined arity.
     *
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ToBooleanFunctionN> void arityToBooleanCFT(final ToIntFunction<Random> arityFunction,
                                                                        final IntFunction<F> functionFactory) {
        toBooleanCFT(arityFunction, functionFactory, FunctionTests::testArityFunction);
    }

    /**
     * Runs a common function test for a generic {@link ToBooleanFunctionN} implementation.
     *
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ToBooleanFunctionN> void toBooleanCFT(final ToIntFunction<Random> arityFunction,
                                                                   final IntFunction<F> functionFactory) {
        toBooleanCFT(arityFunction, functionFactory, FunctionTests::testFunction);
    }

    /**
     * Runs a common function test for a generic {@link ToBooleanFunctionN} implementation.
     *
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param testCase a test case to test the function implementation
     * @param <F> the type of the function
     */
    public static <F extends ToBooleanFunctionN> void toBooleanCFT(final ToIntFunction<Random> arityFunction,
                                                                   final IntFunction<F> functionFactory,
                                                                   final ToBooleanCFT<F> testCase) {
        testCase.test(FunctionTests.RANDOM,
                arityFunction,
                functionFactory,
                t -> !t,
                FunctionTests.GENERATOR,
                LIST_FUNCTION);
    }

    /**
     * A common function test to verify all working parts of a {@code boolean} function implementation.
     *
     * @param <F> the type of the function
     */
    @FunctionalInterface
    public interface ToBooleanCFT<F extends ToBooleanFunctionN> extends ToBooleanCommonFunctionTest<Object, Boolean, F> {}

    /**
     * A common function test to verify all working parts of a {@code boolean} function implementation.
     *
     * @param <I> the type of the input(s) to the function
     * @param <V> the type of the result of the chained function via {@code andThen}
     * @param <F> the type of the function
     */
    @FunctionalInterface
    public interface ToBooleanCommonFunctionTest<I, V, F extends ToBooleanFunctionN> extends FunctionTests.CommonFunctionTest<I, Boolean, V, F> {}
}
