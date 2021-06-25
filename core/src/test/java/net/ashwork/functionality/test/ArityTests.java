/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.test;

import net.ashwork.functionality.arity.operator.TernaryOperator;
import net.ashwork.functionality.arity.consumer.TriConsumer;
import net.ashwork.functionality.arity.function.TriFunction;
import net.ashwork.functionality.arity.predicate.TriPredicate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * All tests associated with {@code net.ashwork.functionality.arity}.
 */
public final class ArityTests {

    /**
     * A random instance.
     */
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    /**
     * Tested:
     * <ul>
     *     <li>{@link TriFunction#apply(Object, Object, Object)}</li>
     *     <li>{@link TriFunction#andThen(Function)}</li>
     * </ul>
     */
    @Test
    public void triFunction() {
        final double[] randoms = RANDOM.doubles(3).distinct().toArray();
        final TriFunction<Double, Short, Integer, String> func = (d, s, i) -> String.valueOf(d + s + i);
        Assertions.assertEquals(func.apply(randoms[0], (short) randoms[1], (int) randoms[2]), String.valueOf(randoms[0] + (short) randoms[1] + (int) randoms[2]));
        Assertions.assertEquals(func.andThen(str -> str.contains("n")).apply(randoms[0], (short) randoms[1], (int) randoms[2]), false);
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link TriConsumer#accept(Object, Object, Object)}</li>
     *     <li>{@link TriConsumer#andThen(TriConsumer)}</li>
     * </ul>
     */
    @Test
    public void triConsumer() {
        final double[] randoms = RANDOM.doubles(3).distinct().toArray();
        final TriConsumer<Double, Short, Integer> cons = (d, s, i) -> Assertions.assertEquals(String.valueOf(d + s + i), String.valueOf(randoms[0] + (short) randoms[1] + (int) randoms[2]));
        cons.accept(randoms[0], (short) randoms[1], (int) randoms[2]);
        cons.andThen((d, s, i) -> Assertions.assertTrue(true)).accept(randoms[0], (short) randoms[1], (int) randoms[2]);
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link TriPredicate#test(Object, Object, Object)}</li>
     *     <li>{@link TriPredicate#and(TriPredicate)}</li>
     *     <li>{@link TriPredicate#negate()}</li>
     *     <li>{@link TriPredicate#or(TriPredicate)}</li>
     * </ul>
     */
    @Test
    public void triPredicate() {
        final double[] randoms = RANDOM.doubles(3).distinct().toArray();
        final TriPredicate<Double, Short, Integer> pred = (d, s, i) -> (d + s + i) < 0;
        Assertions.assertFalse(pred.test(randoms[0], (short) randoms[1], (int) randoms[2]));
        Assertions.assertTrue(pred.negate().test(randoms[0], (short) randoms[1], (int) randoms[2]));
        Assertions.assertFalse(pred.and((d, s, i) -> (d + s + i) >= 0).test(randoms[0], (short) randoms[1], (int) randoms[2]));
        Assertions.assertTrue(pred.or((d, s, i) -> (d + s + i) >= 0).test(randoms[0], (short) randoms[1], (int) randoms[2]));
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link TernaryOperator#apply(Object, Object, Object)}</li>
     *     <li>{@link TernaryOperator#conditional(Predicate)}</li>
     * </ul>
     */
    @Test
    public void ternaryOperator() {
        final int[] randoms = RANDOM.ints(3).distinct().toArray();
        final TernaryOperator<Integer> op = TernaryOperator.conditional(i -> i % 2 == 0);
        Assertions.assertEquals(randoms[0] % 2 == 0 ? randoms[1] : randoms[2], op.apply(randoms[0], randoms[1], randoms[2]));
    }
}
