/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.test;

import net.ashwork.functionality.primitive.chars.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;

/**
 * A testing class that tests all {@code char}-based functions.
 */
public final class CharFunctionTests {

    // Standardized Functions for Testing
    public static final Function<Object[], Character> FUNCTION = o -> FunctionTests.CHAR.apply(FunctionTests.FUNCTION.apply(o));
    public static final Function<List<Object>, Character> LIST_FUNCTION = l -> FUNCTION.apply(FunctionTests.LIST.apply(l));
    public static final Function<Character, String> CHAR = Object::toString;

    /**
     * Tests the n-arity {@code char} function.
     */
    @Test
    public void n() {
        ToCharFunctionN function = FUNCTION::apply;
        toCharCFT(r -> r.nextInt(100), a -> function);
        arityToCharCFT(r -> r.nextInt(100), a -> new ToCharFunctionN.Instance(a, function::applyAllUnchecked));
    }

    /**
     * Tests the zero-arity {@code char} function.
     */
    @Test
    public void zero() {
        ToCharFunction0 function = () -> FUNCTION.apply(new Object[]{});
        arityToCharCFT(0, a -> function);
    }

    /**
     * Tests the one-arity {@code char} function.
     */
    @Test
    public void one() {
        ToCharFunction1<Object> function = o1 -> FUNCTION.apply(new Object[]{o1});
        toChar1CFT(a -> function);
        CharFunction1<String> bFunction = CHAR::apply;
        charObject1CFT(a -> bFunction);
    }

    /**
     * Tests the two-arity {@code char} function.
     */
    @Test
    public void two() {
        ToCharFunction2<Object, Object> function = (o1, o2) -> FUNCTION.apply(new Object[]{o1, o2});
        arityToCharCFT(2, a -> function);
    }

    /**
     * Runs a common function test for a generic {@link CharFunction1} implementation.
     *
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends CharFunction1<String>> void charObject1CFT(final IntFunction<F> functionFactory) {
        FunctionTests.objectCFT(null, functionFactory, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, b -> o -> f.apply(b).apply((char) o), o1 -> FUNCTION.apply(new Object[]{o1}), t, i, e));
    }

    /**
     * Runs a common function test for a generic {@link ToCharFunction1} implementation.
     *
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ToCharFunction1<Object>> void toChar1CFT(final IntFunction<F> functionFactory) {
        toCharCFT(null, functionFactory, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> f.apply(i0)::applyAsChar, FunctionTests.OBJECT, t, i, e));
    }

    /**
     * Runs a common function test for a generic {@link ToCharFunctionN} implementation with defined arity.
     *
     * @param arity the arity of the function
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ToCharFunctionN> void arityToCharCFT(final int arity,
                                                                        final IntFunction<F> functionFactory) {
        arityToCharCFT(r -> arity, functionFactory);
    }

    /**
     * Runs a common function test for a generic {@link ToCharFunctionN} implementation with defined arity.
     *
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ToCharFunctionN> void arityToCharCFT(final ToIntFunction<Random> arityFunction,
                                                                        final IntFunction<F> functionFactory) {
        toCharCFT(arityFunction, functionFactory, FunctionTests::testArityFunction);
    }

    /**
     * Runs a common function test for a generic {@link ToCharFunctionN} implementation.
     *
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ToCharFunctionN> void toCharCFT(final ToIntFunction<Random> arityFunction,
                                                                   final IntFunction<F> functionFactory) {
        toCharCFT(arityFunction, functionFactory, FunctionTests::testFunction);
    }

    /**
     * Runs a common function test for a generic {@link ToCharFunctionN} implementation.
     *
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param testCase a test case to test the function implementation
     * @param <F> the type of the function
     */
    public static <F extends ToCharFunctionN> void toCharCFT(final ToIntFunction<Random> arityFunction,
                                                                   final IntFunction<F> functionFactory,
                                                                   final ToCharCFT<F> testCase) {
        testCase.test(FunctionTests.RANDOM,
                arityFunction,
                functionFactory,
                t -> (char) ~t,
                FunctionTests.GENERATOR,
                LIST_FUNCTION);
    }

    /**
     * A common function test to verify all working parts of a {@code char} function implementation.
     *
     * @param <F> the type of the function
     */
    @FunctionalInterface
    public interface ToCharCFT<F extends ToCharFunctionN> extends ToCharCommonFunctionTest<Object, Character, F> {}

    /**
     * A common function test to verify all working parts of a {@code char} function implementation.
     *
     * @param <I> the type of the input(s) to the function
     * @param <V> the type of the result of the chained function via {@code andThen}
     * @param <F> the type of the function
     */
    @FunctionalInterface
    public interface ToCharCommonFunctionTest<I, V, F extends ToCharFunctionN> extends FunctionTests.CommonFunctionTest<I, Character, V, F> {}
}
