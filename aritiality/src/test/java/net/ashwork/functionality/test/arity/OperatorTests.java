/*
 * Aritiality (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.test.arity;

import net.ashwork.functionality.arity.operator.Operator3;
import net.ashwork.functionality.arity.operator.Operator4;
import net.ashwork.functionality.arity.operator.Operator5;
import net.ashwork.functionality.arity.operator.Operator6;
import net.ashwork.functionality.test.util.RandomUtil;
import net.ashwork.functionality.test.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

/**
 * All tests associated with {@code net.ashwork.functionality.arity.operator}.
 */
public final class OperatorTests {

    /**
     * Tested:
     * <ul>
     *     <li>{@link Operator3#apply(Object, Object, Object)}</li>
     *     <li>{@link Operator3#conditional(Predicate)}</li>
     * </ul>
     */
    @Test
    public void three() {
        final int[] randoms = RandomUtil.current().ints(3).distinct().toArray();
        final Operator3<Integer> op = Operator3.conditional(i -> i % 2 == 0);
        Assertions.assertEquals(randoms[0] % 2 == 0 ? randoms[1] : randoms[2], op.apply(randoms[0], randoms[1], randoms[2]));
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link Operator4#apply(Object, Object, Object, Object)}</li>
     * </ul>
     */
    @Test
    public void four() {
        TestUtil.runOperatorTest(4, () -> ((Operator4<Integer>) (i1, i2, i3, i4) -> i1 + i2 + i3 + i4),
                (operator, inputs) -> operator.apply(inputs[0], inputs[1], inputs[2], inputs[3]));
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link Operator5#apply(Object, Object, Object, Object, Object)}</li>
     * </ul>
     */
    @Test
    public void five() {
        TestUtil.runOperatorTest(5, () -> ((Operator5<Integer>) (i1, i2, i3, i4, i5) -> i1 + i2 + i3 + i4 + i5),
                (operator, inputs) -> operator.apply(inputs[0], inputs[1], inputs[2], inputs[3], inputs[4]));
    }

    /**
     * Tested:
     * <ul>
     *     <li>{@link Operator6#apply(Object, Object, Object, Object, Object, Object)}</li>
     * </ul>
     */
    @Test
    public void six() {
        TestUtil.runOperatorTest(6, () -> ((Operator6<Integer>) (i1, i2, i3, i4, i5, i6) -> i1 + i2 + i3 + i4 + i5 + i6),
                (operator, inputs) -> operator.apply(inputs[0], inputs[1], inputs[2], inputs[3], inputs[4], inputs[5]));
    }
}
