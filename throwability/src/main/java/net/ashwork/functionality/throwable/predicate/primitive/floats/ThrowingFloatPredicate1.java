package net.ashwork.functionality.throwable.predicate.primitive.floats;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.predicate.primitive.floats.FloatPredicate1;
import net.ashwork.functionality.throwable.abstracts.predicate.primitive.floats.AbstractThrowingFloatPredicate1;
import net.ashwork.functionality.throwable.predicate.ThrowingPredicate1;
import net.ashwork.functionality.throwable.predicate.ThrowingPredicateN;
import net.ashwork.functionality.throwable.primitive.floats.ThrowingFloatFunction1;
import net.ashwork.functionality.throwable.primitive.combined.ThrowingFloatToBooleanFunction1;

/**
 * Represents a predicate that accepts a {@code float}-valued argument and produces a {@code boolean}-valued result or throws a throwable.
 * This is the one-arity specialization for {@link ThrowingPredicateN}.
 * This is the predicate specialization for {@link ThrowingFloatToBooleanFunction1}.
 * This is the {@code float}-consuming primitive specialization of {@link ThrowingPredicate1}.
 * This is the throwing variation of {@link FloatPredicate1}.
 *
 * <p>This is a functional interface whose functional method is {@link #test(float)}.
 *
 * @see ThrowingPredicateN
 * @see ThrowingFloatToBooleanFunction1
 * @see ThrowingPredicate1
 * @see FloatPredicate1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingFloatPredicate1 extends AbstractThrowingFloatPredicate1<AbstractThrowingFloatPredicate1.Handler, ThrowingFloatPredicate1> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param predicate the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see FloatPredicate1
     */
    static ThrowingFloatPredicate1 from(final FloatPredicate1 predicate) {
        return predicate::test;
    }

    /**
     * Creates an instance of this object from its {@link ThrowingFloatToBooleanFunction1} function variant.
     *
     * @param function the function variant of this object
     * @return an instance of this object
     *
     * @see ThrowingFloatToBooleanFunction1
     */
    static ThrowingFloatPredicate1 fromFunctionVariant(final ThrowingFloatToBooleanFunction1 function) {
        return function::applyAsBoolean;
    }

    /**
     * @see ThrowingFloatToBooleanFunction1
     */
    @Override
    default ThrowingFloatToBooleanFunction1 toFunctionVariant() {
        return this::test;
    }

    /**
     * @see ThrowingPredicate1
     */
    @Override
    default ThrowingPredicate1<Float> boxInput() {
        return this::test;
    }

    /**
     * @see FloatPredicate1
     */
    @Override
    default FloatPredicate1 handle(final Handler handler) {
        return (final float value) -> {
            try {
                return this.test(value);
            } catch (final Throwable t) {
                return handler.testThrown(t, value);
            }
        };
    }

    /**
     * @see FloatPredicate1
     */
    @Override
    default FloatPredicate1 swallow() {
        return this.handle((t, value) -> false);
    }

    /**
     * @see ThrowingPredicate1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingPredicate1<V> compose(final Function1<? super V, ? extends Float> before) {
        return (ThrowingPredicate1<V>) AbstractThrowingFloatPredicate1.super.compose(before);
    }

    /**
     * @see ThrowingPredicate1
     */
    @Override
    default <V> ThrowingPredicate1<V> composeUnchecked(final Function1<? super V, ? extends Float> before) {
        return (final V v) -> this.test(before.apply(v));
    }

    /**
     * @see ThrowingFloatFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingFloatFunction1<V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (ThrowingFloatFunction1<V>) AbstractThrowingFloatPredicate1.super.andThen(after);
    }

    /**
     * @see ThrowingFloatFunction1
     */
    @Override
    default <V> ThrowingFloatFunction1<V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final float value) -> after.apply(this.test(value));
    }

    @Override
    default ThrowingFloatPredicate1 not() {
        return (final float value) -> !this.test(value);
    }

    @Override
    default ThrowingFloatPredicate1 and(final ThrowingFloatPredicate1 other) {
        return (final float value) -> this.test(value) && other.test(value);
    }

    @Override
    default ThrowingFloatPredicate1 or(final ThrowingFloatPredicate1 other) {
        return (final float value) -> this.test(value) || other.test(value);
    }

    @Override
    default ThrowingFloatPredicate1 xor(final ThrowingFloatPredicate1 other) {
        return (ThrowingFloatPredicate1) AbstractThrowingFloatPredicate1.super.xor(other);
    }

    @Override
    default ThrowingFloatPredicate1 sub(final ThrowingFloatPredicate1 other) {
        return (ThrowingFloatPredicate1) AbstractThrowingFloatPredicate1.super.sub(other);
    }

    @Override
    default ThrowingFloatPredicate1 nand(final ThrowingFloatPredicate1 other) {
        return (ThrowingFloatPredicate1) AbstractThrowingFloatPredicate1.super.nand(other);
    }

    @Override
    default ThrowingFloatPredicate1 nor(final ThrowingFloatPredicate1 other) {
        return (ThrowingFloatPredicate1) AbstractThrowingFloatPredicate1.super.nor(other);
    }

    @Override
    default ThrowingFloatPredicate1 xnor(final ThrowingFloatPredicate1 other) {
        return (ThrowingFloatPredicate1) AbstractThrowingFloatPredicate1.super.xnor(other);
    }

    @Override
    default ThrowingFloatPredicate1 orNot(final ThrowingFloatPredicate1 other) {
        return (ThrowingFloatPredicate1) AbstractThrowingFloatPredicate1.super.orNot(other);
    }
}
