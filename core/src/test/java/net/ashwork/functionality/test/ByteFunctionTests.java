/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.test;

import net.ashwork.functionality.primitive.bytes.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;

/**
 * A testing class that tests all {@code byte}-based functions.
 */
public final class ByteFunctionTests {

    // Standardized Functions for Testing
    public static final Function<Object[], Byte> FUNCTION = o -> FunctionTests.BYTE.apply(FunctionTests.FUNCTION.apply(o));
    public static final Function<List<Object>, Byte> LIST_FUNCTION = l -> FUNCTION.apply(FunctionTests.LIST.apply(l));
    public static final Function<Byte, String> BYTE = Object::toString;

    /**
     * Tests the n-arity {@code byte} function.
     */
    @Test
    public void n() {
        ToByteFunctionN function = FUNCTION::apply;
        toByteCFT(r -> r.nextInt(100), a -> function);
        arityToByteCFT(r -> r.nextInt(100), a -> new ToByteFunctionN.Instance(a, function::applyAllUnchecked));
    }

    /**
     * Tests the zero-arity {@code byte} function.
     */
    @Test
    public void zero() {
        ToByteFunction0 function = () -> FUNCTION.apply(new Object[]{});
        arityToByteCFT(0, a -> function);
    }

    /**
     * Tests the one-arity {@code byte} function.
     */
    @Test
    public void one() {
        ToByteFunction1<Object> function = o1 -> FUNCTION.apply(new Object[]{o1});
        toByte1CFT(a -> function);
        ByteFunction1<String> bFunction = BYTE::apply;
        byteObject1CFT(a -> bFunction);
    }

    /**
     * Tests the two-arity {@code byte} function.
     */
    @Test
    public void two() {
        ToByteFunction2<Object, Object> function = (o1, o2) -> FUNCTION.apply(new Object[]{o1, o2});
        arityToByteCFT(2, a -> function);
    }

    /**
     * Runs a common function test for a generic {@link ByteFunction1} implementation.
     *
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ByteFunction1<String>> void byteObject1CFT(final IntFunction<F> functionFactory) {
        FunctionTests.objectCFT(null, functionFactory, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, b -> o -> f.apply(b).apply((byte) o), o1 -> FUNCTION.apply(new Object[]{o1}), t, i, e));
        FunctionTests.objectCFT(null, functionFactory, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, b -> o -> f.apply(b).boxInput().apply((byte) o), o1 -> FUNCTION.apply(new Object[]{o1}), t, i, e));
    }

    /**
     * Runs a common function test for a generic {@link ToByteFunction1} implementation.
     *
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ToByteFunction1<Object>> void toByte1CFT(final IntFunction<F> functionFactory) {
        toByteCFT(null, functionFactory, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> f.apply(i0)::applyAsByte, FunctionTests.OBJECT, t, i, e));
        toByteCFT(null, functionFactory, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> f.apply(i0).boxResult()::apply, FunctionTests.OBJECT, t, i, e));
    }

    /**
     * Runs a common function test for a generic {@link ToByteFunctionN} implementation with defined arity.
     *
     * @param arity the arity of the function
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ToByteFunctionN> void arityToByteCFT(final int arity,
                                                                        final IntFunction<F> functionFactory) {
        arityToByteCFT(r -> arity, functionFactory);
    }

    /**
     * Runs a common function test for a generic {@link ToByteFunctionN} implementation with defined arity.
     *
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ToByteFunctionN> void arityToByteCFT(final ToIntFunction<Random> arityFunction,
                                                                        final IntFunction<F> functionFactory) {
        toByteCFT(arityFunction, functionFactory, FunctionTests::testArityFunction);
    }

    /**
     * Runs a common function test for a generic {@link ToByteFunctionN} implementation.
     *
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param <F> the type of the function
     */
    public static <F extends ToByteFunctionN> void toByteCFT(final ToIntFunction<Random> arityFunction,
                                                                   final IntFunction<F> functionFactory) {
        toByteCFT(arityFunction, functionFactory, FunctionTests::testFunction);
    }

    /**
     * Runs a common function test for a generic {@link ToByteFunctionN} implementation.
     *
     * @param arityFunction the arity of the function
     * @param functionFactory the supplied function instance
     * @param testCase a test case to test the function implementation
     * @param <F> the type of the function
     */
    public static <F extends ToByteFunctionN> void toByteCFT(final ToIntFunction<Random> arityFunction,
                                                                   final IntFunction<F> functionFactory,
                                                                   final ToByteCFT<F> testCase) {
        testCase.test(FunctionTests.RANDOM,
                arityFunction,
                functionFactory,
                t -> (byte) ~t,
                FunctionTests.GENERATOR,
                LIST_FUNCTION);
    }

    /**
     * A common function test to verify all working parts of a {@code byte} function implementation.
     *
     * @param <F> the type of the function
     */
    @FunctionalInterface
    public interface ToByteCFT<F extends ToByteFunctionN> extends ToByteCommonFunctionTest<Object, Byte, F> {}

    /**
     * A common function test to verify all working parts of a {@code byte} function implementation.
     *
     * @param <I> the type of the input(s) to the function
     * @param <V> the type of the result of the chained function via {@code andThen}
     * @param <F> the type of the function
     */
    @FunctionalInterface
    public interface ToByteCommonFunctionTest<I, V, F extends ToByteFunctionN> extends FunctionTests.CommonFunctionTest<I, Byte, V, F> {}
}
