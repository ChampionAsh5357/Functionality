package net.ashwork.functionality.throwable.predicate.primitive.doubles;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.predicate.primitive.doubles.DoublePredicate1;
import net.ashwork.functionality.throwable.abstracts.predicate.primitive.doubles.AbstractThrowingDoublePredicate1;
import net.ashwork.functionality.throwable.predicate.ThrowingPredicate1;
import net.ashwork.functionality.throwable.predicate.ThrowingPredicateN;
import net.ashwork.functionality.throwable.primitive.doubles.ThrowingDoubleFunction1;
import net.ashwork.functionality.throwable.primitive.combined.ThrowingDoubleToBooleanFunction1;

/**
 * Represents a predicate that accepts a {@code double}-valued argument and produces a {@code boolean}-valued result or throws a throwable.
 * This is the one-arity specialization for {@link ThrowingPredicateN}.
 * This is the predicate specialization for {@link ThrowingDoubleToBooleanFunction1}.
 * This is the {@code double}-consuming primitive specialization of {@link ThrowingPredicate1}.
 * This is the throwing variation of {@link DoublePredicate1}.
 *
 * <p>This is a functional interface whose functional method is {@link #test(double)}.
 *
 * @see ThrowingPredicateN
 * @see ThrowingDoubleToBooleanFunction1
 * @see ThrowingPredicate1
 * @see DoublePredicate1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingDoublePredicate1 extends AbstractThrowingDoublePredicate1<AbstractThrowingDoublePredicate1.Handler, ThrowingDoublePredicate1> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param predicate the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see DoublePredicate1
     */
    static ThrowingDoublePredicate1 from(final DoublePredicate1 predicate) {
        return predicate::test;
    }

    /**
     * Creates an instance of this object from its {@link ThrowingDoubleToBooleanFunction1} function variant.
     *
     * @param function the function variant of this object
     * @return an instance of this object
     *
     * @see ThrowingDoubleToBooleanFunction1
     */
    static ThrowingDoublePredicate1 fromFunctionVariant(final ThrowingDoubleToBooleanFunction1 function) {
        return function::applyAsBoolean;
    }

    /**
     * @see ThrowingDoubleToBooleanFunction1
     */
    @Override
    default ThrowingDoubleToBooleanFunction1 toFunctionVariant() {
        return this::test;
    }

    /**
     * @see ThrowingPredicate1
     */
    @Override
    default ThrowingPredicate1<Double> boxInput() {
        return this::test;
    }

    /**
     * @see DoublePredicate1
     */
    @Override
    default DoublePredicate1 handle(final Handler handler) {
        return (final double value) -> {
            try {
                return this.test(value);
            } catch (final Throwable t) {
                return handler.testThrown(t, value);
            }
        };
    }

    /**
     * @see DoublePredicate1
     */
    @Override
    default DoublePredicate1 swallow() {
        return this.handle((t, value) -> false);
    }

    /**
     * @see ThrowingPredicate1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingPredicate1<V> compose(final Function1<? super V, ? extends Double> before) {
        return (ThrowingPredicate1<V>) AbstractThrowingDoublePredicate1.super.compose(before);
    }

    /**
     * @see ThrowingPredicate1
     */
    @Override
    default <V> ThrowingPredicate1<V> composeUnchecked(final Function1<? super V, ? extends Double> before) {
        return (final V v) -> this.test(before.apply(v));
    }

    /**
     * @see ThrowingDoubleFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingDoubleFunction1<V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (ThrowingDoubleFunction1<V>) AbstractThrowingDoublePredicate1.super.andThen(after);
    }

    /**
     * @see ThrowingDoubleFunction1
     */
    @Override
    default <V> ThrowingDoubleFunction1<V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final double value) -> after.apply(this.test(value));
    }

    @Override
    default ThrowingDoublePredicate1 not() {
        return (final double value) -> !this.test(value);
    }

    @Override
    default ThrowingDoublePredicate1 and(final ThrowingDoublePredicate1 other) {
        return (final double value) -> this.test(value) && other.test(value);
    }

    @Override
    default ThrowingDoublePredicate1 or(final ThrowingDoublePredicate1 other) {
        return (final double value) -> this.test(value) || other.test(value);
    }

    @Override
    default ThrowingDoublePredicate1 xor(final ThrowingDoublePredicate1 other) {
        return (ThrowingDoublePredicate1) AbstractThrowingDoublePredicate1.super.xor(other);
    }

    @Override
    default ThrowingDoublePredicate1 sub(final ThrowingDoublePredicate1 other) {
        return (ThrowingDoublePredicate1) AbstractThrowingDoublePredicate1.super.sub(other);
    }

    @Override
    default ThrowingDoublePredicate1 nand(final ThrowingDoublePredicate1 other) {
        return (ThrowingDoublePredicate1) AbstractThrowingDoublePredicate1.super.nand(other);
    }

    @Override
    default ThrowingDoublePredicate1 nor(final ThrowingDoublePredicate1 other) {
        return (ThrowingDoublePredicate1) AbstractThrowingDoublePredicate1.super.nor(other);
    }

    @Override
    default ThrowingDoublePredicate1 xnor(final ThrowingDoublePredicate1 other) {
        return (ThrowingDoublePredicate1) AbstractThrowingDoublePredicate1.super.xnor(other);
    }

    @Override
    default ThrowingDoublePredicate1 orNot(final ThrowingDoublePredicate1 other) {
        return (ThrowingDoublePredicate1) AbstractThrowingDoublePredicate1.super.orNot(other);
    }
}
