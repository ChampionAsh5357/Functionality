package net.ashwork.functionality.throwable.predicate;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.predicate.Predicate0;
import net.ashwork.functionality.throwable.ThrowingFunction0;
import net.ashwork.functionality.throwable.abstracts.predicate.AbstractThrowingPredicate0;
import net.ashwork.functionality.throwable.primitive.booleans.ThrowingToBooleanFunction0;

/**
 * Represents a predicate that accepts no arguments and produces a {@code boolean}-valued result or throws a throwable.
 * This is the zero-arity specialization for {@link ThrowingPredicateN}.
 * This is the predicate specialization for {@link ThrowingToBooleanFunction0}.
 * This is the throwing variation of {@link Predicate0}.
 *
 * <p>This is a functional interface whose functional method is {@link #test()}.
 *
 * @see ThrowingPredicateN
 * @see ThrowingToBooleanFunction0
 * @see Predicate0
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingPredicate0 extends AbstractThrowingPredicate0<AbstractThrowingPredicate0.Handler, ThrowingPredicate0> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param predicate the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see Predicate0
     */
    static ThrowingPredicate0 from(final Predicate0 predicate) {
        return predicate::test;
    }

    /**
     * Creates an instance of this object from its {@link ThrowingToBooleanFunction0} function variant.
     *
     * @param function the function variant of this object
     * @return an instance of this object
     *
     * @see ThrowingToBooleanFunction0
     */
    static ThrowingPredicate0 fromFunctionVariant(final ThrowingToBooleanFunction0 function) {
        return function::applyAsBoolean;
    }

    /**
     * @see ThrowingToBooleanFunction0
     */
    @Override
    default ThrowingToBooleanFunction0 toFunctionVariant() {
        return this::test;
    }

    /**
     * @see Predicate0
     */
    @Override
    default Predicate0 handle(final Handler handler) {
        return () -> {
            try {
                return this.test();
            } catch (final Throwable t) {
                return handler.testThrown(t);
            }
        };
    }

    /**
     * @see Predicate0
     */
    @Override
    default Predicate0 swallow() {
        return this.handle(t -> false);
    }

    /**
     * @see ThrowingFunction0
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFunction0<V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (ThrowingFunction0<V>) AbstractThrowingPredicate0.super.andThen(after);
    }

    /**
     * @see ThrowingFunction0
     */
    @Override
    default <V> ThrowingFunction0<V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return () -> after.apply(this.test());
    }

    @Override
    default ThrowingPredicate0 not() {
        return () -> !this.test();
    }

    @Override
    default ThrowingPredicate0 and(final ThrowingPredicate0 other) {
        return () -> this.test() && other.test();
    }

    @Override
    default ThrowingPredicate0 or(final ThrowingPredicate0 other) {
        return () -> this.test() || other.test();
    }

    @Override
    default ThrowingPredicate0 xor(final ThrowingPredicate0 other) {
        return (ThrowingPredicate0) AbstractThrowingPredicate0.super.xor(other);
    }

    @Override
    default ThrowingPredicate0 sub(final ThrowingPredicate0 other) {
        return (ThrowingPredicate0) AbstractThrowingPredicate0.super.sub(other);
    }

    @Override
    default ThrowingPredicate0 nand(final ThrowingPredicate0 other) {
        return (ThrowingPredicate0) AbstractThrowingPredicate0.super.nand(other);
    }

    @Override
    default ThrowingPredicate0 nor(final ThrowingPredicate0 other) {
        return (ThrowingPredicate0) AbstractThrowingPredicate0.super.nor(other);
    }

    @Override
    default ThrowingPredicate0 xnor(final ThrowingPredicate0 other) {
        return (ThrowingPredicate0) AbstractThrowingPredicate0.super.xnor(other);
    }

    @Override
    default ThrowingPredicate0 orNot(final ThrowingPredicate0 other) {
        return (ThrowingPredicate0) AbstractThrowingPredicate0.super.orNot(other);
    }
}
