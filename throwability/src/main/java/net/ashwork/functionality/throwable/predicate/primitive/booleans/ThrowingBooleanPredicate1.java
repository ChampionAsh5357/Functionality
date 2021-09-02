package net.ashwork.functionality.throwable.predicate.primitive.booleans;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.predicate.primitive.booleans.BooleanPredicate1;
import net.ashwork.functionality.throwable.abstracts.predicate.primitive.booleans.AbstractThrowingBooleanPredicate1;
import net.ashwork.functionality.throwable.operator.primitive.booleans.ThrowingBooleanOperator1;
import net.ashwork.functionality.throwable.predicate.ThrowingPredicate1;
import net.ashwork.functionality.throwable.predicate.ThrowingPredicateN;
import net.ashwork.functionality.throwable.primitive.booleans.ThrowingBooleanFunction1;

/**
 * Represents a predicate that accepts a {@code boolean}-valued argument and produces a {@code boolean}-valued result or throws a throwable.
 * This is the one-arity specialization for {@link ThrowingPredicateN}.
 * This is the predicate specialization for {@link ThrowingBooleanOperator1}.
 * This is the {@code boolean}-consuming primitive specialization of {@link ThrowingPredicate1}.
 * This is the throwing variation of {@link BooleanPredicate1}.
 *
 * <p>This is a functional interface whose functional method is {@link #test(boolean)}.
 *
 * @see ThrowingPredicateN
 * @see ThrowingBooleanOperator1
 * @see ThrowingPredicate1
 * @see BooleanPredicate1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingBooleanPredicate1 extends AbstractThrowingBooleanPredicate1<AbstractThrowingBooleanPredicate1.Handler, ThrowingBooleanPredicate1> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param predicate the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see BooleanPredicate1
     */
    static ThrowingBooleanPredicate1 from(final BooleanPredicate1 predicate) {
        return predicate::test;
    }

    /**
     * Creates an instance of this object from its {@link ThrowingBooleanOperator1} function variant.
     *
     * @param operator the function variant of this object
     * @return an instance of this object
     *
     * @see ThrowingBooleanOperator1
     */
    static ThrowingBooleanPredicate1 fromFunctionVariant(final ThrowingBooleanOperator1 operator) {
        return operator::applyAsBoolean;
    }

    /**
     * @see ThrowingBooleanOperator1
     */
    @Override
    default ThrowingBooleanOperator1 toFunctionVariant() {
        return this::test;
    }

    /**
     * @see ThrowingPredicate1
     */
    @Override
    default ThrowingPredicate1<Boolean> boxInput() {
        return this::test;
    }

    /**
     * @see BooleanPredicate1
     */
    @Override
    default BooleanPredicate1 handle(final Handler handler) {
        return (final boolean value) -> {
            try {
                return this.test(value);
            } catch (final Throwable t) {
                return handler.testThrown(t, value);
            }
        };
    }

    /**
     * @see BooleanPredicate1
     */
    @Override
    default BooleanPredicate1 swallow() {
        return this.handle((t, value) -> false);
    }

    /**
     * @see ThrowingPredicate1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingPredicate1<V> compose(final Function1<? super V, ? extends Boolean> before) {
        return (ThrowingPredicate1<V>) AbstractThrowingBooleanPredicate1.super.compose(before);
    }

    /**
     * @see ThrowingPredicate1
     */
    @Override
    default <V> ThrowingPredicate1<V> composeUnchecked(final Function1<? super V, ? extends Boolean> before) {
        return (final V v) -> this.test(before.apply(v));
    }

    /**
     * @see ThrowingBooleanFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingBooleanFunction1<V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (ThrowingBooleanFunction1<V>) AbstractThrowingBooleanPredicate1.super.andThen(after);
    }

    /**
     * @see ThrowingBooleanFunction1
     */
    @Override
    default <V> ThrowingBooleanFunction1<V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final boolean value) -> after.apply(this.test(value));
    }

    @Override
    default ThrowingBooleanPredicate1 not() {
        return (final boolean value) -> !this.test(value);
    }

    @Override
    default ThrowingBooleanPredicate1 and(final ThrowingBooleanPredicate1 other) {
        return (final boolean value) -> this.test(value) && other.test(value);
    }

    @Override
    default ThrowingBooleanPredicate1 or(final ThrowingBooleanPredicate1 other) {
        return (final boolean value) -> this.test(value) || other.test(value);
    }

    @Override
    default ThrowingBooleanPredicate1 xor(final ThrowingBooleanPredicate1 other) {
        return (ThrowingBooleanPredicate1) AbstractThrowingBooleanPredicate1.super.xor(other);
    }

    @Override
    default ThrowingBooleanPredicate1 sub(final ThrowingBooleanPredicate1 other) {
        return (ThrowingBooleanPredicate1) AbstractThrowingBooleanPredicate1.super.sub(other);
    }

    @Override
    default ThrowingBooleanPredicate1 nand(final ThrowingBooleanPredicate1 other) {
        return (ThrowingBooleanPredicate1) AbstractThrowingBooleanPredicate1.super.nand(other);
    }

    @Override
    default ThrowingBooleanPredicate1 nor(final ThrowingBooleanPredicate1 other) {
        return (ThrowingBooleanPredicate1) AbstractThrowingBooleanPredicate1.super.nor(other);
    }

    @Override
    default ThrowingBooleanPredicate1 xnor(final ThrowingBooleanPredicate1 other) {
        return (ThrowingBooleanPredicate1) AbstractThrowingBooleanPredicate1.super.xnor(other);
    }

    @Override
    default ThrowingBooleanPredicate1 orNot(final ThrowingBooleanPredicate1 other) {
        return (ThrowingBooleanPredicate1) AbstractThrowingBooleanPredicate1.super.orNot(other);
    }
}
