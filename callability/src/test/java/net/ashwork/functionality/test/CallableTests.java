/*
 * Callability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.test;

import net.ashwork.functionality.callable.RunnableCallable;
import net.ashwork.functionality.callable.consumer.BiConsumerCallable;
import net.ashwork.functionality.callable.consumer.ConsumerCallable;
import net.ashwork.functionality.callable.function.BiFunctionCallable;
import net.ashwork.functionality.callable.function.FunctionCallable;
import net.ashwork.functionality.callable.function.primitive.IntFunctionCallable;
import net.ashwork.functionality.callable.operator.BinaryOperatorCallable;
import net.ashwork.functionality.callable.operator.UnaryOperatorCallable;
import net.ashwork.functionality.callable.predicate.BiPredicateCallable;
import net.ashwork.functionality.callable.predicate.PredicateCallable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

/**
 * All tests associated with {@code net.ashwork.functionality.callable}.
 */
public final class CallableTests {

    /**
     * A random instance.
     */
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    /**
     * Tested:
     * <ul>
     *     <li>{@link FunctionCallable#apply(Object)}</li>
     *     <li>{@link FunctionCallable#andThen(FunctionCallable)}</li>
     *     <li>{@link FunctionCallable#compose(FunctionCallable)}</li>
     *     <li>{@link FunctionCallable#identity()}</li>
     *     <li>{@link FunctionCallable#handle(FunctionCallable.ExceptionHandler)}</li>
     *     <li>{@link FunctionCallable#swallow()}</li>
     * </ul>
     */
    @Test
    public void function() {
        final FunctionCallable<Integer, String> applyFunc = i -> {
            throw new Exception(String.valueOf(i));
        };
        final FunctionCallable<Integer, String> normalFunc = String::valueOf;
        Assertions.assertThrows(Exception.class, () -> applyFunc.apply(RANDOM.nextInt(1000)));
        Assertions.assertDoesNotThrow(() -> {
            final int random = RANDOM.nextInt(1000);
            Assertions.assertFalse(normalFunc.andThen(str -> str.startsWith("n")).apply(random));
            Assertions.assertEquals(normalFunc.compose(FunctionCallable.identity()).apply(random), String.valueOf(random));
        });
        Assertions.assertEquals(applyFunc.handle((i, e) -> "null").apply(RANDOM.nextInt(1000)), "null");
        Assertions.assertNull(applyFunc.swallow().apply(RANDOM.nextInt(1000)));
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link IntFunctionCallable#apply(int)}</li>
     *     <li>{@link IntFunctionCallable#handle(IntFunctionCallable.ExceptionHandler)}</li>
     *     <li>{@link IntFunctionCallable#swallow()}</li>
     * </ul>
     */
    @Test
    public void intFunction() {
        final IntFunctionCallable<String> applyFunc = i -> {
            throw new Exception(String.valueOf(i));
        };
        final IntFunctionCallable<String> normalFunc = String::valueOf;
        Assertions.assertThrows(Exception.class, () -> applyFunc.apply(RANDOM.nextInt(1000)));
        Assertions.assertDoesNotThrow(() -> normalFunc.apply(RANDOM.nextInt(1000)));
        Assertions.assertEquals(applyFunc.handle((i, e) -> "null").apply(RANDOM.nextInt(1000)), "null");
        Assertions.assertNull(applyFunc.swallow().apply(RANDOM.nextInt(1000)));
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link BiFunctionCallable#apply(Object, Object)}</li>
     *     <li>{@link BiFunctionCallable#handle(BiFunctionCallable.ExceptionHandler)}</li>
     *     <li>{@link BiFunctionCallable#swallow()}</li>
     *     <li>{@link BiFunctionCallable#andThen(FunctionCallable)}</li>
     * </ul>
     */
    @Test
    public void biFunction() {
        final BiFunctionCallable<Double, Integer, String> applyFunc = (d, i) -> {
            throw new Exception(String.valueOf(d + i));
        };
        final BiFunctionCallable<Double, Integer, String> normalFunc = (d, i) -> String.valueOf(d + i);
        Assertions.assertThrows(Exception.class, () -> applyFunc.apply(RANDOM.nextDouble(1000), RANDOM.nextInt(1000)));
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(normalFunc.andThen(s -> s.contains("n")).apply(RANDOM.nextDouble(1000), RANDOM.nextInt(1000))));
        Assertions.assertEquals(applyFunc.handle((d, i, e) -> "null").apply(RANDOM.nextDouble(1000), RANDOM.nextInt(1000)), "null");
        Assertions.assertNull(applyFunc.swallow().apply(RANDOM.nextDouble(1000), RANDOM.nextInt(1000)));
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link RunnableCallable#run()}</li>
     *     <li>{@link RunnableCallable#handle(RunnableCallable.ExceptionHandler)}</li>
     *     <li>{@link RunnableCallable#swallow()}</li>
     * </ul>
     */
    @Test
    public void runnable() {
        final RunnableCallable runnable = () -> {
            throw new Exception();
        };
        final RunnableCallable normalRunnable = () -> Assertions.assertTrue(true);
        Assertions.assertThrows(Exception.class, runnable::run);
        Assertions.assertDoesNotThrow(normalRunnable::run);
        runnable.handle((e) -> Assertions.assertTrue(true)).run();
        runnable.swallow().run();
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link ConsumerCallable#accept(Object)}</li>
     *     <li>{@link ConsumerCallable#handle(ConsumerCallable.ExceptionHandler)}</li>
     *     <li>{@link ConsumerCallable#swallow()}</li>
     *     <li>{@link ConsumerCallable#andThen(ConsumerCallable)}</li>
     * </ul>
     */
    @Test
    public void consumer() {
        final ConsumerCallable<Integer> consumer = i -> {
            throw new Exception();
        };
        final int random = RANDOM.nextInt(1000);
        final ConsumerCallable<Integer> normalConsumer = i -> Assertions.assertEquals(i, random);
        Assertions.assertThrows(Exception.class, () -> consumer.accept(RANDOM.nextInt()));
        Assertions.assertDoesNotThrow(() -> normalConsumer.andThen(i -> Assertions.assertTrue(true)).accept(random));
        final int random2 = RANDOM.nextInt(1000);
        consumer.handle((i, e) -> Assertions.assertEquals(i, random2)).accept(random2);
        consumer.swallow().accept(RANDOM.nextInt());
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link PredicateCallable#test(Object)}</li>
     *     <li>{@link PredicateCallable#handle(PredicateCallable.ExceptionHandler)}</li>
     *     <li>{@link PredicateCallable#swallow()}</li>
     *     <li>{@link PredicateCallable#and(PredicateCallable)}</li>
     *     <li>{@link PredicateCallable#negate()}</li>
     *     <li>{@link PredicateCallable#or(PredicateCallable)}</li>
     *     <li>{@link PredicateCallable#isEqual(Object)}</li>
     *     <li>{@link PredicateCallable#not(PredicateCallable)}</li>
     * </ul>
     */
    @Test
    public void predicate() {
        final PredicateCallable<Integer> predicate = i -> {
            throw new Exception();
        };
        final PredicateCallable<Integer> normalPredicate = i -> i % 2 == 0;
        Assertions.assertThrows(Exception.class, () -> predicate.test(RANDOM.nextInt(1000)));
        Assertions.assertDoesNotThrow(() -> {
            final int random = RANDOM.nextInt(1000) * 2;
            Assertions.assertTrue(normalPredicate.test(random));
            Assertions.assertFalse(normalPredicate.negate().test(random));
            Assertions.assertFalse(normalPredicate.and(i -> i % 2 == 1).test(random));
            Assertions.assertTrue(normalPredicate.or(i -> i % 2 == 1).test(random));
            Assertions.assertTrue(PredicateCallable.isEqual(random).test(random));
            Assertions.assertFalse(PredicateCallable.not(normalPredicate).test(random));
        });
        Assertions.assertTrue(predicate.handle((i, e) -> true).test(RANDOM.nextInt(1000)));
        Assertions.assertFalse(predicate.swallow().test(RANDOM.nextInt(1000)));
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link UnaryOperatorCallable#apply(Object)}</li>
     *     <li>{@link UnaryOperatorCallable#handle(UnaryOperatorCallable.ExceptionHandler)}</li>
     *     <li>{@link UnaryOperatorCallable#swallow()}</li>
     *     <li>{@link UnaryOperatorCallable#identity()}</li>
     * </ul>
     */
    @Test
    public void unaryOperator() {
        final UnaryOperatorCallable<Integer> operator = i -> {
            throw new Exception();
        };
        final int random = RANDOM.nextInt(1000);
        final UnaryOperatorCallable<Integer> normalPredicate = i -> i + 2;
        Assertions.assertThrows(Exception.class, () -> operator.apply(RANDOM.nextInt(1000)));
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals(normalPredicate.andThen(UnaryOperatorCallable.identity()).apply(random), random + 2));
        Assertions.assertEquals(operator.handle((i, e) -> 0).apply(RANDOM.nextInt(1000)), 0);
        Assertions.assertNull(operator.swallow().apply(RANDOM.nextInt(1000)));
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link BiConsumerCallable#accept(Object, Object)}</li>
     *     <li>{@link BiConsumerCallable#handle(BiConsumerCallable.ExceptionHandler)}</li>
     *     <li>{@link BiConsumerCallable#swallow()}</li>
     *     <li>{@link BiConsumerCallable#andThen(BiConsumerCallable)}</li>
     * </ul>
     */
    @Test
    public void biConsumer() {
        final BiConsumerCallable<Integer, String> consumer = (i, s) -> {
            throw new Exception();
        };
        final int random = RANDOM.nextInt(1000);
        final String str = "no";
        final BiConsumerCallable<Integer, String> normalConsumer = (i, s) -> Assertions.assertEquals(i + s, random + str);
        Assertions.assertThrows(Exception.class, () -> consumer.accept(RANDOM.nextInt(), str));
        Assertions.assertDoesNotThrow(() -> normalConsumer.andThen((i, s) -> Assertions.assertTrue(true)).accept(random, str));
        final int random2 = RANDOM.nextInt(1000);
        consumer.handle((i, s, e) -> Assertions.assertEquals(i + s, random2 + str)).accept(random2, str);
        consumer.swallow().accept(RANDOM.nextInt(), str);
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link BiPredicateCallable#test(Object,Object)}</li>
     *     <li>{@link BiPredicateCallable#handle(BiPredicateCallable.ExceptionHandler)}</li>
     *     <li>{@link BiPredicateCallable#swallow()}</li>
     *     <li>{@link BiPredicateCallable#and(BiPredicateCallable)}</li>
     *     <li>{@link BiPredicateCallable#negate()}</li>
     *     <li>{@link BiPredicateCallable#or(BiPredicateCallable)}</li>
     * </ul>
     */
    @Test
    public void biPredicate() {
        final BiPredicateCallable<Integer, String> predicate = (i, s) -> {
            throw new Exception();
        };
        final BiPredicateCallable<Integer, String> normalPredicate = (i, s) -> i % 2 == 0 || s.contains("n");
        Assertions.assertThrows(Exception.class, () -> predicate.test(RANDOM.nextInt(1000), "y"));
        Assertions.assertDoesNotThrow(() -> {
            final String str = "y";
            final int random = RANDOM.nextInt(1000) * 2;
            Assertions.assertTrue(normalPredicate.test(random, str));
            Assertions.assertFalse(normalPredicate.negate().test(random, str));
            Assertions.assertFalse(normalPredicate.and((i, s) -> i % 2 == 1 && s.contains("y")).test(random, str));
            Assertions.assertTrue(normalPredicate.or((i, s) -> i % 2 == 1 && s.contains("y")).test(random, str));
        });
        Assertions.assertTrue(predicate.handle((i, s, e) -> true).test(RANDOM.nextInt(1000), "y"));
        Assertions.assertFalse(predicate.swallow().test(RANDOM.nextInt(1000), "y"));
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link BinaryOperatorCallable#apply(Object, Object)}</li>
     *     <li>{@link BinaryOperatorCallable#handle(BinaryOperatorCallable.ExceptionHandler)}</li>
     *     <li>{@link BinaryOperatorCallable#swallow()}</li>
     * </ul>
     */
    @Test
    public void binaryOperator() {
        final BinaryOperatorCallable<Integer> op = (i1, i2) -> {
            throw new Exception();
        };
        final int[] randoms = RANDOM.ints(2).distinct().toArray();
        final BinaryOperatorCallable<Integer> normalOp = Integer::sum;
        Assertions.assertThrows(Exception.class, () -> op.apply(RANDOM.nextInt(1000), RANDOM.nextInt(1000)));
        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals(normalOp.apply(randoms[0], randoms[1]), randoms[0] + randoms[1]));
        Assertions.assertEquals(op.handle((i1, i2, e) -> 0).apply(RANDOM.nextInt(1000), RANDOM.nextInt(1000)), 0);
        Assertions.assertNull(op.swallow().apply(RANDOM.nextInt(1000), RANDOM.nextInt(1000)));
    }
}
