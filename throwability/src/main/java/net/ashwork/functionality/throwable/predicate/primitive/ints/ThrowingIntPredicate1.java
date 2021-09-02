package net.ashwork.functionality.throwable.predicate.primitive.ints;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.predicate.primitive.ints.IntPredicate1;
import net.ashwork.functionality.throwable.abstracts.predicate.primitive.ints.AbstractThrowingIntPredicate1;
import net.ashwork.functionality.throwable.predicate.ThrowingPredicate1;
import net.ashwork.functionality.throwable.predicate.ThrowingPredicateN;
import net.ashwork.functionality.throwable.primitive.ints.ThrowingIntFunction1;
import net.ashwork.functionality.throwable.primitive.combined.ThrowingIntToBooleanFunction1;

/**
 * Represents a predicate that accepts an {@code int}-valued argument and produces a {@code boolean}-valued result or throws a throwable.
 * This is the one-arity specialization for {@link ThrowingPredicateN}.
 * This is the predicate specialization for {@link ThrowingIntToBooleanFunction1}.
 * This is the {@code int}-consuming primitive specialization of {@link ThrowingPredicate1}.
 * This is the throwing variation of {@link IntPredicate1}.
 *
 * <p>This is a functional interface whose functional method is {@link #test(int)}.
 *
 * @see ThrowingPredicateN
 * @see ThrowingIntToBooleanFunction1
 * @see ThrowingPredicate1
 * @see IntPredicate1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingIntPredicate1 extends AbstractThrowingIntPredicate1<AbstractThrowingIntPredicate1.Handler, ThrowingIntPredicate1> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param predicate the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see IntPredicate1
     */
    static ThrowingIntPredicate1 from(final IntPredicate1 predicate) {
        return predicate::test;
    }

    /**
     * Creates an instance of this object from its {@link ThrowingIntToBooleanFunction1} function variant.
     *
     * @param function the function variant of this object
     * @return an instance of this object
     *
     * @see ThrowingIntToBooleanFunction1
     */
    static ThrowingIntPredicate1 fromFunctionVariant(final ThrowingIntToBooleanFunction1 function) {
        return function::applyAsBoolean;
    }

    /**
     * @see ThrowingIntToBooleanFunction1
     */
    @Override
    default ThrowingIntToBooleanFunction1 toFunctionVariant() {
        return this::test;
    }

    /**
     * @see ThrowingPredicate1
     */
    @Override
    default ThrowingPredicate1<Integer> boxInput() {
        return this::test;
    }

    /**
     * @see IntPredicate1
     */
    @Override
    default IntPredicate1 handle(final Handler handler) {
        return (final int value) -> {
            try {
                return this.test(value);
            } catch (final Throwable t) {
                return handler.testThrown(t, value);
            }
        };
    }

    /**
     * @see IntPredicate1
     */
    @Override
    default IntPredicate1 swallow() {
        return this.handle((t, value) -> false);
    }

    /**
     * @see ThrowingPredicate1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingPredicate1<V> compose(final Function1<? super V, ? extends Integer> before) {
        return (ThrowingPredicate1<V>) AbstractThrowingIntPredicate1.super.compose(before);
    }

    /**
     * @see ThrowingPredicate1
     */
    @Override
    default <V> ThrowingPredicate1<V> composeUnchecked(final Function1<? super V, ? extends Integer> before) {
        return (final V v) -> this.test(before.apply(v));
    }

    /**
     * @see ThrowingIntFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingIntFunction1<V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (ThrowingIntFunction1<V>) AbstractThrowingIntPredicate1.super.andThen(after);
    }

    /**
     * @see ThrowingIntFunction1
     */
    @Override
    default <V> ThrowingIntFunction1<V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final int value) -> after.apply(this.test(value));
    }

    @Override
    default ThrowingIntPredicate1 not() {
        return (final int value) -> !this.test(value);
    }

    @Override
    default ThrowingIntPredicate1 and(final ThrowingIntPredicate1 other) {
        return (final int value) -> this.test(value) && other.test(value);
    }

    @Override
    default ThrowingIntPredicate1 or(final ThrowingIntPredicate1 other) {
        return (final int value) -> this.test(value) || other.test(value);
    }

    @Override
    default ThrowingIntPredicate1 xor(final ThrowingIntPredicate1 other) {
        return (ThrowingIntPredicate1) AbstractThrowingIntPredicate1.super.xor(other);
    }

    @Override
    default ThrowingIntPredicate1 sub(final ThrowingIntPredicate1 other) {
        return (ThrowingIntPredicate1) AbstractThrowingIntPredicate1.super.sub(other);
    }

    @Override
    default ThrowingIntPredicate1 nand(final ThrowingIntPredicate1 other) {
        return (ThrowingIntPredicate1) AbstractThrowingIntPredicate1.super.nand(other);
    }

    @Override
    default ThrowingIntPredicate1 nor(final ThrowingIntPredicate1 other) {
        return (ThrowingIntPredicate1) AbstractThrowingIntPredicate1.super.nor(other);
    }

    @Override
    default ThrowingIntPredicate1 xnor(final ThrowingIntPredicate1 other) {
        return (ThrowingIntPredicate1) AbstractThrowingIntPredicate1.super.xnor(other);
    }

    @Override
    default ThrowingIntPredicate1 orNot(final ThrowingIntPredicate1 other) {
        return (ThrowingIntPredicate1) AbstractThrowingIntPredicate1.super.orNot(other);
    }
}
