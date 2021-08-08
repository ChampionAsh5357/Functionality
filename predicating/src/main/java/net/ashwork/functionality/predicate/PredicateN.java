/*
 * Predicating (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.predicate;

import net.ashwork.functionality.predicate.abstracts.AbstractPredicateN;
import net.ashwork.functionality.primitive.booleans.ToBooleanFunctionN;

/**
 * Represents a predicate that accepts {@code n} arguments and produces a {@code boolean}-valued result.
 * This is the predicate specialization for {@link ToBooleanFunctionN}.
 * All {@code boolean}-producing predicates are derived from this {@code n}-arity specialization.
 *
 * <p>This is a functional interface whose functional method is {@link #testAllUnchecked(Object...)}.
 *
 * @see ToBooleanFunctionN
 * @since 1.0.0
 */
@FunctionalInterface
public interface PredicateN extends AbstractPredicateN<PredicateN> {

    @Override
    default PredicateN not() {
        return (final Object[] args) -> !this.applyAllUnchecked(args);
    }

    @Override
    default PredicateN and(final PredicateN other) {
        return (final Object[] args) -> this.applyAllUnchecked(args) && other.applyAllUnchecked(args);
    }

    @Override
    default PredicateN or(final PredicateN other) {
        return (final Object[] args) -> this.applyAllUnchecked(args) || other.applyAllUnchecked(args);
    }

    @Override
    default PredicateN xor(final PredicateN other) {
        return (PredicateN) AbstractPredicateN.super.xor(other);
    }

    @Override
    default PredicateN sub(final PredicateN other) {
        return (PredicateN) AbstractPredicateN.super.sub(other);
    }

    @Override
    default PredicateN nand(final PredicateN other) {
        return (PredicateN) AbstractPredicateN.super.nand(other);
    }

    @Override
    default PredicateN nor(final PredicateN other) {
        return (PredicateN) AbstractPredicateN.super.nor(other);
    }

    @Override
    default PredicateN xnor(final PredicateN other) {
        return (PredicateN) AbstractPredicateN.super.xnor(other);
    }

    @Override
    default PredicateN orNot(final PredicateN other) {
        return (PredicateN) AbstractPredicateN.super.orNot(other);
    }

    /**
     * An instance of {@link AbstractPredicateN} which properly defines the
     * arity and type of that particular predicate.
     *
     * @see AbstractPredicateN
     */
    class Instance implements AbstractPredicateN<Instance> {

        private final int arity;
        private final Predicate1<Object[]> predicate;

        /**
         * Constructs an instance of the predicate.
         *
         * @param arity     the number of arguments of the predicate
         * @param predicate the predicate to be applied
         */
        public Instance(final int arity, final Predicate1<Object[]> predicate) {
            this.arity = arity;
            this.predicate = predicate;
        }

        @Override
        public int arity() {
            return this.arity;
        }

        @Override
        public boolean testAllUnchecked(final Object... args) {
            return this.predicate.test(args);
        }

        @Override
        public PredicateN.Instance not() {
            return new PredicateN.Instance(this.arity(), (final Object[] args) -> !this.applyAllUnchecked(args));
        }

        @Override
        public PredicateN.Instance and(final PredicateN.Instance other) {
            return new PredicateN.Instance(this.arity(), (final Object[] args) -> this.applyAllUnchecked(args) && other.applyAllUnchecked(args));
        }

        @Override
        public PredicateN.Instance or(final PredicateN.Instance other) {
            return new PredicateN.Instance(this.arity(), (final Object[] args) -> this.applyAllUnchecked(args) || other.applyAllUnchecked(args));
        }

        @Override
        public PredicateN.Instance xor(final PredicateN.Instance other) {
            return (PredicateN.Instance) AbstractPredicateN.super.xor(other);
        }

        @Override
        public PredicateN.Instance sub(final PredicateN.Instance other) {
            return (PredicateN.Instance) AbstractPredicateN.super.sub(other);
        }

        @Override
        public PredicateN.Instance nand(final PredicateN.Instance other) {
            return (PredicateN.Instance) AbstractPredicateN.super.nand(other);
        }

        @Override
        public PredicateN.Instance nor(final PredicateN.Instance other) {
            return (PredicateN.Instance) AbstractPredicateN.super.nor(other);
        }

        @Override
        public PredicateN.Instance xnor(final PredicateN.Instance other) {
            return (PredicateN.Instance) AbstractPredicateN.super.xnor(other);
        }

        @Override
        public PredicateN.Instance orNot(final PredicateN.Instance other) {
            return (PredicateN.Instance) AbstractPredicateN.super.orNot(other);
        }
    }
}
