/*
 * Throwability (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.throwable.predicate;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.predicate.Predicate1;
import net.ashwork.functionality.predicate.PredicateN;
import net.ashwork.functionality.throwable.ThrowingFunctionN;
import net.ashwork.functionality.throwable.abstracts.predicate.AbstractThrowingPredicateN;
import net.ashwork.functionality.throwable.primitive.booleans.ThrowingToBooleanFunctionN;

/**
 * Represents a predicate that accepts {@code n} arguments and produces a {@code boolean}-valued result or throws a throwable.
 * This is the predicate specialization for {@link ThrowingToBooleanFunctionN}.
 * All {@code boolean}-producing predicates are derived from this {@code n}-arity specialization.
 * This is the throwing variation of {@link PredicateN}.
 *
 * <p>This is a functional interface whose functional method is {@link #testAllUnchecked(Object...)}.
 *
 * @see ThrowingToBooleanFunctionN
 * @see PredicateN
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingPredicateN extends AbstractThrowingPredicateN<AbstractThrowingPredicateN.Handler, ThrowingPredicateN> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param predicate the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see Predicate1
     */
    static ThrowingPredicateN from(final PredicateN predicate) {
        return predicate::testAllUnchecked;
    }

    /**
     * @see PredicateN
     */
    @Override
    default PredicateN handle(final Handler handler) {
        return (final Object[] args) -> {
            try {
                return this.testAllUnchecked(args);
            } catch (final Throwable t) {
                return handler.testThrownUnchecked(t, args);
            }
        };
    }

    /**
     * @see PredicateN
     */
    @Override
    default PredicateN swallow() {
        return this.handle((t, args) -> false);
    }

    /**
     * @see ThrowingFunctionN
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunctionN<V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (ThrowingFunctionN<V>) AbstractThrowingPredicateN.super.andThen(after);
    }

    /**
     * @see ThrowingFunctionN
     */
    @Override
    default <V> ThrowingFunctionN<V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final Object[] args) -> after.apply(this.testAllUnchecked(args));
    }

    @Override
    default ThrowingPredicateN not() {
        return (final Object[] args) -> !this.testAllUnchecked(args);
    }

    @Override
    default ThrowingPredicateN and(final ThrowingPredicateN other) {
        return (final Object[] args) -> this.testAllUnchecked(args) && other.testAllUnchecked(args);
    }

    @Override
    default ThrowingPredicateN or(final ThrowingPredicateN other) {
        return (final Object[] args) -> this.testAllUnchecked(args) || other.testAllUnchecked(args);
    }

    @Override
    default ThrowingPredicateN xor(final ThrowingPredicateN other) {
        return (ThrowingPredicateN) AbstractThrowingPredicateN.super.xor(other);
    }

    @Override
    default ThrowingPredicateN sub(final ThrowingPredicateN other) {
        return (ThrowingPredicateN) AbstractThrowingPredicateN.super.sub(other);
    }

    @Override
    default ThrowingPredicateN nand(final ThrowingPredicateN other) {
        return (ThrowingPredicateN) AbstractThrowingPredicateN.super.nand(other);
    }

    @Override
    default ThrowingPredicateN nor(final ThrowingPredicateN other) {
        return (ThrowingPredicateN) AbstractThrowingPredicateN.super.nor(other);
    }

    @Override
    default ThrowingPredicateN xnor(final ThrowingPredicateN other) {
        return (ThrowingPredicateN) AbstractThrowingPredicateN.super.xnor(other);
    }

    @Override
    default ThrowingPredicateN orNot(final ThrowingPredicateN other) {
        return (ThrowingPredicateN) AbstractThrowingPredicateN.super.orNot(other);
    }

    /**
     * An instance of {@link AbstractThrowingPredicateN} which properly defines the
     * arity of that particular predicate.
     *
     * @see AbstractThrowingPredicateN
     */
    class Instance implements AbstractThrowingPredicateN<AbstractThrowingPredicateN.Handler, Instance> {

        private final int arity;
        private final ThrowingPredicate1<Object[]> predicate;

        /**
         * Constructs an instance of the predicate.
         *
         * @param predicate the predicate instance to be applied
         */
        public Instance(final PredicateN.Instance predicate) {
            this(predicate.arity(), predicate::testAllUnchecked);
        }

        /**
         * Constructs an instance of the predicate.
         *
         * @param arity     the number of arguments of the predicate
         * @param predicate the predicate to be applied
         */
        public Instance(final int arity, final ThrowingPredicate1<Object[]> predicate) {
            this.arity = arity;
            this.predicate = predicate;
        }

        @Override
        public int arity() {
            return this.arity;
        }

        @Override
        public boolean testAllUnchecked(Object... args) throws Throwable {
            return this.predicate.test(args);
        }

        /**
         * @see PredicateN.Instance
         */
        @Override
        public PredicateN.Instance handle(Handler handler) {
            return new PredicateN.Instance(this.arity(), (final Object[] args) -> {
                try {
                    return this.testAllUnchecked(args);
                } catch (final Throwable t) {
                    return handler.testThrownUnchecked(t, args);
                }
            });
        }

        /**
         * @see PredicateN.Instance
         */
        @Override
        public PredicateN.Instance swallow() {
            return this.handle((t, args) -> false);
        }

        /**
         * @see ThrowingFunctionN.Instance
         */
        @SuppressWarnings("unchecked")
        @Override
        public <V> ThrowingFunctionN.Instance<V> andThen(Function1<? super Boolean, ? extends V> after) {
            return (ThrowingFunctionN.Instance<V>) AbstractThrowingPredicateN.super.andThen(after);
        }

        /**
         * @see ThrowingFunctionN.Instance
         */
        @Override
        public <V> ThrowingFunctionN.Instance<V> andThenUnchecked(Function1<? super Boolean, ? extends V> after) {
            return new ThrowingFunctionN.Instance<>(this.arity(), (final Object[] args) -> after.apply(this.testAllUnchecked(args)));
        }

        @Override
        public ThrowingPredicateN.Instance not() {
            return new ThrowingPredicateN.Instance(this.arity(), (final Object[] args) -> !this.testAllUnchecked(args));
        }

        @Override
        public ThrowingPredicateN.Instance and(final ThrowingPredicateN.Instance other) {
            return new ThrowingPredicateN.Instance(this.arity(), (final Object[] args) -> this.testAllUnchecked(args) && other.testAllUnchecked(args));
        }

        @Override
        public ThrowingPredicateN.Instance or(final ThrowingPredicateN.Instance other) {
            return new ThrowingPredicateN.Instance(this.arity(), (final Object[] args) -> this.testAllUnchecked(args) || other.testAllUnchecked(args));
        }

        @Override
        public ThrowingPredicateN.Instance xor(final ThrowingPredicateN.Instance other) {
            return (ThrowingPredicateN.Instance) AbstractThrowingPredicateN.super.xor(other);
        }

        @Override
        public ThrowingPredicateN.Instance sub(final ThrowingPredicateN.Instance other) {
            return (ThrowingPredicateN.Instance) AbstractThrowingPredicateN.super.sub(other);
        }

        @Override
        public ThrowingPredicateN.Instance nand(final ThrowingPredicateN.Instance other) {
            return (ThrowingPredicateN.Instance) AbstractThrowingPredicateN.super.nand(other);
        }

        @Override
        public ThrowingPredicateN.Instance nor(final ThrowingPredicateN.Instance other) {
            return (ThrowingPredicateN.Instance) AbstractThrowingPredicateN.super.nor(other);
        }

        @Override
        public ThrowingPredicateN.Instance xnor(final ThrowingPredicateN.Instance other) {
            return (ThrowingPredicateN.Instance) AbstractThrowingPredicateN.super.xnor(other);
        }

        @Override
        public ThrowingPredicateN.Instance orNot(final ThrowingPredicateN.Instance other) {
            return (ThrowingPredicateN.Instance) AbstractThrowingPredicateN.super.orNot(other);
        }
    }
}
