/*
 * Aritiality (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.test.arity;

import net.ashwork.functionality.arity.predicate.*;
import net.ashwork.functionality.test.util.TestUtil;
import org.junit.jupiter.api.Test;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * All tests associated with {@code net.ashwork.functionality.arity.predicate}.
 */
public final class PredicateTests {

    /**
     * Tested:
     * <ul>
     *     <li>{@link Predicate1#test(Object)}</li>
     *     <li>{@link Predicate1#negate()}</li>
     *     <li>{@link Predicate1#and(Predicate)}</li>
     *     <li>{@link Predicate1#or(Predicate)}</li>
     *     <li>{@link Predicate1#boxed()}</li>
     * </ul>
     */
    @Test
    public void one() {
        TestUtil.runPredicateTest(1, () -> Predicate1.wrap((Integer i) -> i < 0),
                () -> i -> i >= 0,
                (predicate, inputs) -> predicate.test(inputs[0]),
                Predicate1::negate, Predicate1::and, Predicate1::or, Predicate1::boxed,
                (function, inputs) -> function.apply(inputs[0]));
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link Predicate2#test(Object, Object)}</li>
     *     <li>{@link Predicate2#negate()}</li>
     *     <li>{@link Predicate2#and(BiPredicate)}</li>
     *     <li>{@link Predicate2#or(BiPredicate)}</li>
     *     <li>{@link Predicate2#boxed()}</li>
     * </ul>
     */
    @Test
    public void two() {
        TestUtil.runPredicateTest(2, () -> Predicate2.wrap((Integer i1, Integer i2) -> i1 + i2 < 0),
                () -> (i1, i2) -> i1 + i2 >= 0,
                (predicate, inputs) -> predicate.test(inputs[0], inputs[1]),
                Predicate2::negate, Predicate2::and, Predicate2::or, Predicate2::boxed,
                (function, inputs) -> function.apply(inputs[0], inputs[1]));
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link Predicate3#test(Object, Object, Object)}</li>
     *     <li>{@link Predicate3#negate()}</li>
     *     <li>{@link Predicate3#and(Predicate3)}</li>
     *     <li>{@link Predicate3#or(Predicate3)}</li>
     *     <li>{@link Predicate3#boxed()}</li>
     * </ul>
     */
    @Test
    public void three() {
        TestUtil.runPredicateTest(3, () -> ((Predicate3<Integer, Integer, Integer>) (i1, i2, i3) -> i1 + i2 + i3 < 0),
                () -> (i1, i2, i3) -> i1 + i2 + i3 >= 0,
                (predicate, inputs) -> predicate.test(inputs[0], inputs[1], inputs[2]),
                Predicate3::negate, Predicate3::and, Predicate3::or, Predicate3::boxed,
                (function, inputs) -> function.apply(inputs[0], inputs[1], inputs[2]));
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link Predicate4#test(Object, Object, Object, Object)}</li>
     *     <li>{@link Predicate4#negate()}</li>
     *     <li>{@link Predicate4#and(Predicate4)}</li>
     *     <li>{@link Predicate4#or(Predicate4)}</li>
     *     <li>{@link Predicate4#boxed()}</li>
     * </ul>
     */
    @Test
    public void four() {
        TestUtil.runPredicateTest(4, () -> ((Predicate4<Integer, Integer, Integer, Integer>) (i1, i2, i3, i4) -> i1 + i2 + i3 + i4 < 0),
                () -> (i1, i2, i3, i4) -> i1 + i2 + i3 + i4 >= 0,
                (predicate, inputs) -> predicate.test(inputs[0], inputs[1], inputs[2], inputs[3]),
                Predicate4::negate, Predicate4::and, Predicate4::or, Predicate4::boxed,
                (function, inputs) -> function.apply(inputs[0], inputs[1], inputs[2], inputs[3]));
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link Predicate5#test(Object, Object, Object, Object, Object)}</li>
     *     <li>{@link Predicate5#negate()}</li>
     *     <li>{@link Predicate5#and(Predicate5)}</li>
     *     <li>{@link Predicate5#or(Predicate5)}</li>
     *     <li>{@link Predicate5#boxed()}</li>
     * </ul>
     */
    @Test
    public void five() {
        TestUtil.runPredicateTest(5, () -> ((Predicate5<Integer, Integer, Integer, Integer, Integer>) (i1, i2, i3, i4, i5) -> i1 + i2 + i3 + i4 + i5 < 0),
                () -> (i1, i2, i3, i4, i5) -> i1 + i2 + i3 + i4 + i5 >= 0,
                (predicate, inputs) -> predicate.test(inputs[0], inputs[1], inputs[2], inputs[3], inputs[4]),
                Predicate5::negate, Predicate5::and, Predicate5::or, Predicate5::boxed,
                (function, inputs) -> function.apply(inputs[0], inputs[1], inputs[2], inputs[3], inputs[4]));
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link Predicate6#test(Object, Object, Object, Object, Object, Object)}</li>
     *     <li>{@link Predicate6#negate()}</li>
     *     <li>{@link Predicate6#and(Predicate6)}</li>
     *     <li>{@link Predicate6#or(Predicate6)}</li>
     *     <li>{@link Predicate6#boxed()}</li>
     * </ul>
     */
    @Test
    public void six() {
        TestUtil.runPredicateTest(6, () -> ((Predicate6<Integer, Integer, Integer, Integer, Integer, Integer>) (i1, i2, i3, i4, i5, i6) -> i1 + i2 + i3 + i4 + i5 + i6 < 0),
                () -> (i1, i2, i3, i4, i5, i6) -> i1 + i2 + i3 + i4 + i5 + i6 >= 0,
                (predicate, inputs) -> predicate.test(inputs[0], inputs[1], inputs[2], inputs[3], inputs[4], inputs[5]),
                Predicate6::negate, Predicate6::and, Predicate6::or, Predicate6::boxed,
                (function, inputs) -> function.apply(inputs[0], inputs[1], inputs[2], inputs[3], inputs[4], inputs[5]));
    }
}
