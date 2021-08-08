/*
 * Predicating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.predicate;

import net.ashwork.functionality.predicate.abstracts.AbstractPredicate0;
import net.ashwork.functionality.primitive.booleans.ToBooleanFunction0;

/**
 * Represents a predicate that accepts no arguments and produces a {@code boolean}-valued result.
 * This is the zero-arity specialization for {@link Predicate0}.
 * This is the predicate specialization for {@link ToBooleanFunction0}.
 *
 * <p>This is a functional interface whose functional method is {@link #test()}.
 *
 * @see Predicate0
 * @see ToBooleanFunction0
 * @since 1.0.0
 */
@FunctionalInterface
public interface Predicate0 extends AbstractPredicate0<Predicate0> {

    /**
     * Creates an instance of this object from its {@link ToBooleanFunction0} function variant.
     *
     * @param function the function variant of this object
     * @return an instance of this object
     *
     * @see ToBooleanFunction0
     */
    static Predicate0 fromFunctionVariant(final ToBooleanFunction0 function) {
        return function::applyAsBoolean;
    }

    @Override
    default Predicate0 not() {
        return () -> !this.test();
    }

    @Override
    default Predicate0 and(final Predicate0 other) {
        return () -> this.test() && other.test();
    }

    @Override
    default Predicate0 or(final Predicate0 other) {
        return () -> this.test() || other.test();
    }

    @Override
    default Predicate0 xor(final Predicate0 other) {
        return (Predicate0) AbstractPredicate0.super.xor(other);
    }

    @Override
    default Predicate0 sub(final Predicate0 other) {
        return (Predicate0) AbstractPredicate0.super.sub(other);
    }

    @Override
    default Predicate0 nand(final Predicate0 other) {
        return (Predicate0) AbstractPredicate0.super.nand(other);
    }

    @Override
    default Predicate0 nor(final Predicate0 other) {
        return (Predicate0) AbstractPredicate0.super.nor(other);
    }

    @Override
    default Predicate0 xnor(final Predicate0 other) {
        return (Predicate0) AbstractPredicate0.super.xnor(other);
    }

    @Override
    default Predicate0 orNot(final Predicate0 other) {
        return (Predicate0) AbstractPredicate0.super.orNot(other);
    }
}
