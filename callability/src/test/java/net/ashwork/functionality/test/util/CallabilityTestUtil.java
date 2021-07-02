/*
 * Callability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.test.util;

import net.ashwork.functionality.callable.consumer.BiConsumerCallable;
import net.ashwork.functionality.callable.function.BiFunctionCallable;
import net.ashwork.functionality.callable.function.FunctionCallable;
import org.junit.jupiter.api.Assertions;

import java.util.concurrent.Callable;
import java.util.function.*;

/**
 * A general utility for executing throwing tests.
 */
public final class CallabilityTestUtil {

    /**
     * Creates a primitive callable test.
     *
     * @param factory The factory that constructs the callable
     * @param exceptionFactory The factory that constructs the callable with an error
     * @param call The method that the callable uses to compute a result
     * @param handle The method that the callable uses to handle the exception thrown
     * @param swallow The method that the callable uses to swallow the exception thrown
     * @param get The method that the non-throwing callable counterpart uses to compute a result
     * @param boxed The method that the callable uses to box the primitives into a callable type
     * @param testValue The initial test value being passed into the callable
     * @param handledValue The handled value being passed into the handle method
     * @param swallowedValue The swallowed value from the swallow method
     * @param <C> The type of the callable
     * @param <N> The type of the non-throwing callable counterpart
     * @param <R> The type of the result
     */
    public static <C, N, R> void suppliedPrimitiveCallableTest(final Function<R, C> factory, final Function<Callable<R>, C> exceptionFactory, final FunctionCallable<C, R> call, final BiFunction<C, R, N> handle, final Function<C, N> swallow, final Function<N, R> get, final Function<C, Callable<R>> boxed, final R testValue, final R handledValue, final R swallowedValue) {
        suppliedCallableTest(factory, exceptionFactory, call, callable -> boxed.apply(callable).call(), handle, swallow, get, testValue, handledValue, swallowedValue);
    }


    /**
     * Creates a callable test that returns a value.
     *
     * @param factory The factory that constructs the callable
     * @param exceptionFactory The factory that constructs the callable with an error
     * @param call The method that the callable uses to compute a result
     * @param handle The method that the callable uses to handle the exception thrown
     * @param swallow The method that the callable uses to swallow the exception thrown
     * @param get The method that the non-throwing callable counterpart uses to compute a result
     * @param testValue The initial test value being passed into the callable
     * @param handledValue The handled value being passed into the handle method
     * @param <C> The type of the callable
     * @param <N> The type of the non-throwing callable counterpart
     * @param <R> The type of the result
     */
    public static <C, N, R> void suppliedCallableTest(final Function<R, C> factory, final Function<Callable<R>, C> exceptionFactory, final FunctionCallable<C, R> call, final BiFunction<C, R, N> handle, final Function<C, N> swallow, final Function<N, R> get, final R testValue, final R handledValue) {
        suppliedCallableTest(factory, exceptionFactory, call, call, handle, swallow, get, testValue, handledValue, null);
    }

    /**
     * Creates a callable test that returns a value.
     *
     * @param factory The factory that constructs the callable
     * @param exceptionFactory The factory that constructs the callable with an error
     * @param call The method that the callable uses to compute a result
     * @param normalCall The method that the callable uses to compute a result when an error is guaranteed not to be thrown
     * @param handle The method that the callable uses to handle the exception thrown
     * @param swallow The method that the callable uses to swallow the exception thrown
     * @param get The method that the non-throwing callable counterpart uses to compute a result
     * @param testValue The initial test value being passed into the callable
     * @param handledValue The handled value being passed into the handle method
     * @param swallowedValue The swallowed value from the swallow method
     * @param <C> The type of the callable
     * @param <N> The type of the non-throwing callable counterpart
     * @param <R> The type of the result
     */
    private static <C, N, R> void suppliedCallableTest(final Function<R, C> factory, final Function<Callable<R>, C> exceptionFactory, final FunctionCallable<C, R> call, final FunctionCallable<C, R> normalCall, final BiFunction<C, R, N> handle, final Function<C, N> swallow, final Function<N, R> get, final R testValue, final R handledValue, final R swallowedValue) {
        suppliedCallableTest(factory, exceptionFactory, call, normalCall, Assertions::assertEquals, handle, swallow, get, testValue, handledValue, swallowedValue);
    }

    /**
     * Creates a generic callable test.
     *
     * @param factory The factory that constructs the callable
     * @param exceptionFactory The factory that constructs the callable with an error
     * @param call The method that the callable uses to compute a result
     * @param normalCall The method that the callable uses to compute a result when an error is guaranteed not to be thrown
     * @param assertionTest The consumer that handles the assertion to make when executed
     * @param handle The method that the callable uses to handle the exception thrown
     * @param swallow The method that the callable uses to swallow the exception thrown
     * @param get The method that the non-throwing callable counterpart uses to compute a result
     * @param testValue The initial test value being passed into the callable
     * @param handledValue The handled value being passed into the handle method
     * @param swallowedValue The swallowed value from the swallow method
     * @param <C> The type of the callable
     * @param <N> The type of the non-throwing callable counterpart
     * @param <R> The type of the result
     */
    public static <C, N, R> void suppliedCallableTest(final Function<R, C> factory, final Function<Callable<R>, C> exceptionFactory, final FunctionCallable<C, R> call, final FunctionCallable<C, R> normalCall, final BiConsumer<R, R> assertionTest, final BiFunction<C, R, N> handle, final Function<C, N> swallow, final Function<N, R> get, final R testValue, final R handledValue, final R swallowedValue) {
        callableTest(() -> factory.apply(testValue), exceptionFactory, (callable, ins) -> call.apply(callable), (callable, ins) -> normalCall.apply(callable), assertionTest, handle, swallow, (noncallable, ins) -> get.apply(noncallable), null, testValue, handledValue, swallowedValue);
    }

    /**
     * Creates a generic callable test for a function.
     *
     * @param factory The factory that constructs the callable
     * @param exceptionFactory The factory that constructs the callable with an error
     * @param call The method that the callable uses to compute a result
     * @param normalCall The method that the callable uses to compute a result when an error is guaranteed not to be thrown
     * @param handle The method that the callable uses to handle the exception thrown
     * @param swallow The method that the callable uses to swallow the exception thrown
     * @param get The method that the non-throwing callable counterpart uses to compute a result
     * @param inputs The inputs passed into the callable
     * @param testValue The initial test value being passed into the callable
     * @param handledValue The handled value being passed into the handle method
     * @param <C> The type of the callable
     * @param <N> The type of the non-throwing callable counterpart
     * @param <T> The type of the input(s)
     * @param <R> The type of the result
     */
    public static <C, N, T, R> void returnCallableTest(final Supplier<C> factory, final Function<Callable<R>, C> exceptionFactory, final BiFunctionCallable<C, T[], R> call, final BiFunctionCallable<C, T[], R> normalCall, final BiFunction<C, R, N> handle, final Function<C, N> swallow, final BiFunction<N, T[], R> get, final T[] inputs, final R testValue, final R handledValue) {
        callableTest(factory, exceptionFactory, call, normalCall, Assertions::assertEquals, handle, swallow, get, inputs, testValue, handledValue, null);
    }

    /**
     * Creates a generic callable test for a predicate.
     *
     * @param factory The factory that constructs the callable
     * @param combinedFactory The factory that constructs a different callable for combining (AND, OR)
     * @param exceptionFactory The factory that constructs the callable with an error
     * @param call The method that the callable uses to compute a result
     * @param negate The negate method that the callable uses to flip the output
     * @param and The and method that the callable uses to logically AND two callables
     * @param or The or method that the callable uses to logically OR two callables
     * @param boxed The boxed method used to convert a callable to its non-primitive callable
     * @param boxedTest The test method that the callable uses to apply the input
     * @param handle The method that the callable uses to handle the exception thrown
     * @param swallow The method that the callable uses to swallow the exception thrown
     * @param get The method that the non-throwing callable counterpart uses to compute a result
     * @param inputs The inputs passed into the callable
     * @param testValue The initial test value being passed into the callable
     * @param combinedValue The initial combined value being passed into the combined callable
     * @param handledValue The handled value being passed into the handle method
     * @param <C> The type of the callable
     * @param <N> The type of the non-throwing callable counterpart
     * @param <T> The type of the input(s)
     * @param <F> The type of the boxed callable
     */
    public static <C, N, T, F> void predicateCallableTest(final Supplier<C> factory, final Supplier<C> combinedFactory, final Function<Callable<Boolean>, C> exceptionFactory, final BiFunctionCallable<C, T[], Boolean> call, final UnaryOperator<C> negate, final BinaryOperator<C> and, final BinaryOperator<C> or, final Function<C, F> boxed, final BiFunctionCallable<F, T[], Boolean> boxedTest, final BiFunction<C, Boolean, N> handle, final Function<C, N> swallow, final BiFunction<N, T[], Boolean> get, final T[] inputs, final Boolean testValue, final Boolean combinedValue, final Boolean handledValue) {
        callableTest(factory, exceptionFactory, call, call, Assertions::assertEquals, handle, swallow, get, inputs, testValue, handledValue, false);
        Assertions.assertDoesNotThrow(() -> {
            final C callable = factory.get(),
                    combinedCallable = combinedFactory.get();
            Assertions.assertEquals(!testValue, call.apply(negate.apply(callable), inputs));
            Assertions.assertEquals(testValue && combinedValue, boxedTest.apply(boxed.apply(and.apply(callable, combinedCallable)), inputs));
            Assertions.assertEquals(testValue || combinedValue, boxedTest.apply(boxed.apply(or.apply(callable, combinedCallable)), inputs));
        });
    }

    /**
     * Creates a generic callable test for a consumer.
     *
     * @param factory The factory that constructs the callable
     * @param exceptionFactory The factory that constructs the callable with an error
     * @param call The method that the callable uses to compute a result
     * @param normalCall The method that the callable uses to compute a result when an error is guaranteed not to be thrown
     * @param handle The method that the callable uses to handle the exception thrown
     * @param swallow The method that the callable uses to swallow the exception thrown
     * @param get The method that the non-throwing callable counterpart uses to compute a result
     * @param inputs The inputs passed into the callable
     * @param testValue The initial test value being passed into the callable
     * @param <C> The type of the callable
     * @param <N> The type of the non-throwing callable counterpart
     * @param <T> The type of the input(s)
     * @param <R> The type of the result
     */
    public static <C, N, T, R> void consumerCallableTest(final Function<R, C> factory, final Function<Callable<R>, C> exceptionFactory, final BiConsumerCallable<C, T[]> call, final BiConsumerCallable<C, T[]> normalCall, final BiFunction<C, R, N> handle, final Function<C, N> swallow, final BiConsumer<N, T[]> get, final T[] inputs, final R testValue) {
        callableTest(() -> factory.apply(testValue), exceptionFactory, (callable, ins) -> { call.accept(callable, ins); return testValue; }, (callable, ins) -> { normalCall.accept(callable, ins); return testValue; }, (expected, actual) -> {}, handle, swallow, (noncallable, ins) -> { get.accept(noncallable, ins); return testValue; }, inputs, testValue, testValue, null);
    }

    /**
     * Creates a generic callable test for a function.
     *
     * @param factory The factory that constructs the callable
     * @param exceptionFactory The factory that constructs the callable with an error
     * @param call The method that the callable uses to compute a result
     * @param normalCall The method that the callable uses to compute a result when an error is guaranteed not to be thrown
     * @param assertionTest The consumer that handles the assertion to make when executed
     * @param handle The method that the callable uses to handle the exception thrown
     * @param swallow The method that the callable uses to swallow the exception thrown
     * @param get The method that the non-throwing callable counterpart uses to compute a result
     * @param inputs The inputs passed into the callable
     * @param testValue The initial test value being passed into the callable
     * @param handledValue The handled value being passed into the handle method
     * @param swallowedValue The swallowed value from the swallow method
     * @param <C> The type of the callable
     * @param <N> The type of the non-throwing callable counterpart
     * @param <T> The type of the input(s)
     * @param <R> The type of the result
     */
    public static <C, N, T, R> void callableTest(final Supplier<C> factory, final Function<Callable<R>, C> exceptionFactory, final BiFunctionCallable<C, T[], R> call, final BiFunctionCallable<C, T[], R> normalCall, final BiConsumer<R, R> assertionTest, final BiFunction<C, R, N> handle, final Function<C, N> swallow, final BiFunction<N, T[], R> get, final T[] inputs, final R testValue, final R handledValue, final R swallowedValue) {
        final C exceptionCallable = exceptionFactory.apply(() -> {
           throw new Exception();
        });
        Assertions.assertThrows(Exception.class, () -> call.apply(exceptionCallable, inputs));
        Assertions.assertDoesNotThrow(() -> {
            assertionTest.accept(handledValue, get.apply(handle.apply(exceptionCallable, handledValue), inputs));
            assertionTest.accept(swallowedValue, get.apply(swallow.apply(exceptionCallable), inputs));

            final C callable = factory.get();
            assertionTest.accept(testValue, normalCall.apply(callable, inputs));
        });
    }
}
