/*
 * Callability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.test.callable;

import net.ashwork.functionality.callable.predicate.BiPredicateCallable;
import net.ashwork.functionality.callable.predicate.PredicateCallable;
import net.ashwork.functionality.test.util.CallabilityTestUtil;
import net.ashwork.functionality.test.util.RandomUtil;
import org.junit.jupiter.api.Test;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * All tests associated with {@code net.ashwork.functionality.callable.predicate}.
 */
public final class PredicateTests {

    /**
     * Tested:
     * <ul>
     *     <li>{@link PredicateCallable#from(Predicate)}</li>
     *     <li>{@link PredicateCallable#not(PredicateCallable)}</li>
     *     <li>{@link PredicateCallable#isEqual(Object)}</li>
     *     <li>{@link PredicateCallable#test(Object)}</li>
     *     <li>{@link PredicateCallable#negate()}</li>
     *     <li>{@link PredicateCallable#and(PredicateCallable)}</li>
     *     <li>{@link PredicateCallable#or(PredicateCallable)}</li>
     *     <li>{@link PredicateCallable#boxed()}</li>
     *     <li>{@link PredicateCallable#handle(PredicateCallable.ExceptionHandler)}</li>
     *     <li>{@link PredicateCallable#swallow()}</li>
     * </ul>
     */
    @Test
    public void one() {
        final Integer[] inputs = RandomUtil.current().ints(1).distinct().boxed().toArray(Integer[]::new);
        CallabilityTestUtil.predicateCallableTest(() -> PredicateCallable.not(PredicateCallable.from((Integer i) -> i % 2 == 0)),
                () -> PredicateCallable.isEqual(RandomUtil.current().nextInt()).and(i -> i < 0),
                callable -> i -> callable.call(), (predicate, values) -> predicate.test(values[0]),
                PredicateCallable::negate, PredicateCallable::and, PredicateCallable::or, PredicateCallable::boxed,
                (function, values) -> function.apply(values[0]), (predicate, value) -> predicate.handle((i, e) -> value),
                PredicateCallable::swallow, (predicate, values) -> predicate.test(values[0]), inputs,
                inputs[0] % 2 != 0, false, true);
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link BiPredicateCallable#from(BiPredicate)}</li>
     *     <li>{@link BiPredicateCallable#test(Object,Object)}</li>
     *     <li>{@link BiPredicateCallable#negate()}</li>
     *     <li>{@link BiPredicateCallable#and(BiPredicateCallable)}</li>
     *     <li>{@link BiPredicateCallable#or(BiPredicateCallable)}</li>
     *     <li>{@link BiPredicateCallable#boxed()}</li>
     *     <li>{@link BiPredicateCallable#handle(BiPredicateCallable.ExceptionHandler)}</li>
     *     <li>{@link BiPredicateCallable#swallow()}</li>
     * </ul>
     */
    @Test
    public void two() {
        final Integer[] inputs = RandomUtil.current().ints(2).distinct().boxed().toArray(Integer[]::new);
        CallabilityTestUtil.predicateCallableTest(() -> BiPredicateCallable.from((Integer i1, Integer i2) -> (i1 + i2) % 2 == 0),
                () -> (i1, i2) -> (i1 + i2) % 3 == 0,
                callable -> (i1, i2) -> callable.call(), (predicate, values) -> predicate.test(values[0], values[1]),
                BiPredicateCallable::negate, BiPredicateCallable::and, BiPredicateCallable::or, BiPredicateCallable::boxed,
                (function, values) -> function.apply(values[0], values[1]), (predicate, value) -> predicate.handle((i1, i2, e) -> value),
                BiPredicateCallable::swallow, (predicate, values) -> predicate.test(values[0], values[1]), inputs,
                (inputs[0] + inputs[1]) % 2 == 0, (inputs[0] + inputs[1]) % 3 == 0, true);
    }
}
