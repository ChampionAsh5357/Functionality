package net.ashwork.functionality.throwable.predicate.primitive.chars;

import net.ashwork.functionality.Function1;
import net.ashwork.functionality.predicate.primitive.chars.CharPredicate1;
import net.ashwork.functionality.throwable.abstracts.predicate.primitive.chars.AbstractThrowingCharPredicate1;
import net.ashwork.functionality.throwable.predicate.ThrowingPredicate1;
import net.ashwork.functionality.throwable.predicate.ThrowingPredicateN;
import net.ashwork.functionality.throwable.primitive.chars.ThrowingCharFunction1;
import net.ashwork.functionality.throwable.primitive.combined.ThrowingCharToBooleanFunction1;

/**
 * Represents a predicate that accepts a {@code char}-valued argument and produces a {@code boolean}-valued result or throws a throwable.
 * This is the one-arity specialization for {@link ThrowingPredicateN}.
 * This is the predicate specialization for {@link ThrowingCharToBooleanFunction1}.
 * This is the {@code char}-consuming primitive specialization of {@link ThrowingPredicate1}.
 * This is the throwing variation of {@link CharPredicate1}.
 *
 * <p>This is a functional interface whose functional method is {@link #test(char)}.
 *
 * @see ThrowingPredicateN
 * @see ThrowingCharToBooleanFunction1
 * @see ThrowingPredicate1
 * @see CharPredicate1
 * @since 1.0.0
 */
@FunctionalInterface
public interface ThrowingCharPredicate1 extends AbstractThrowingCharPredicate1<AbstractThrowingCharPredicate1.Handler, ThrowingCharPredicate1> {

    /**
     * Creates a throwing instance from a non-throwable type.
     *
     * @param predicate the non-throwing type
     * @return a throwing instance of the original type
     *
     * @see CharPredicate1
     */
    static ThrowingCharPredicate1 from(final CharPredicate1 predicate) {
        return predicate::test;
    }

    /**
     * Creates an instance of this object from its {@link ThrowingCharToBooleanFunction1} function variant.
     *
     * @param function the function variant of this object
     * @return an instance of this object
     *
     * @see ThrowingCharToBooleanFunction1
     */
    static ThrowingCharPredicate1 fromFunctionVariant(final ThrowingCharToBooleanFunction1 function) {
        return function::applyAsBoolean;
    }

    /**
     * @see ThrowingCharToBooleanFunction1
     */
    @Override
    default ThrowingCharToBooleanFunction1 toFunctionVariant() {
        return this::test;
    }

    /**
     * @see ThrowingPredicate1
     */
    @Override
    default ThrowingPredicate1<Character> boxInput() {
        return this::test;
    }

    /**
     * @see CharPredicate1
     */
    @Override
    default CharPredicate1 handle(final Handler handler) {
        return (final char value) -> {
            try {
                return this.test(value);
            } catch (final Throwable t) {
                return handler.testThrown(t, value);
            }
        };
    }

    /**
     * @see CharPredicate1
     */
    @Override
    default CharPredicate1 swallow() {
        return this.handle((t, value) -> false);
    }

    /**
     * @see ThrowingPredicate1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingPredicate1<V> compose(final Function1<? super V, ? extends Character> before) {
        return (ThrowingPredicate1<V>) AbstractThrowingCharPredicate1.super.compose(before);
    }

    /**
     * @see ThrowingPredicate1
     */
    @Override
    default <V> ThrowingPredicate1<V> composeUnchecked(final Function1<? super V, ? extends Character> before) {
        return (final V v) -> this.test(before.apply(v));
    }

    /**
     * @see ThrowingCharFunction1
     */
    @SuppressWarnings("unchecked")
    @Override
    default <V> ThrowingCharFunction1<V> andThen(final Function1<? super Boolean, ? extends V> after) {
        return (ThrowingCharFunction1<V>) AbstractThrowingCharPredicate1.super.andThen(after);
    }

    /**
     * @see ThrowingCharFunction1
     */
    @Override
    default <V> ThrowingCharFunction1<V> andThenUnchecked(final Function1<? super Boolean, ? extends V> after) {
        return (final char value) -> after.apply(this.test(value));
    }

    @Override
    default ThrowingCharPredicate1 not() {
        return (final char value) -> !this.test(value);
    }

    @Override
    default ThrowingCharPredicate1 and(final ThrowingCharPredicate1 other) {
        return (final char value) -> this.test(value) && other.test(value);
    }

    @Override
    default ThrowingCharPredicate1 or(final ThrowingCharPredicate1 other) {
        return (final char value) -> this.test(value) || other.test(value);
    }

    @Override
    default ThrowingCharPredicate1 xor(final ThrowingCharPredicate1 other) {
        return (ThrowingCharPredicate1) AbstractThrowingCharPredicate1.super.xor(other);
    }

    @Override
    default ThrowingCharPredicate1 sub(final ThrowingCharPredicate1 other) {
        return (ThrowingCharPredicate1) AbstractThrowingCharPredicate1.super.sub(other);
    }

    @Override
    default ThrowingCharPredicate1 nand(final ThrowingCharPredicate1 other) {
        return (ThrowingCharPredicate1) AbstractThrowingCharPredicate1.super.nand(other);
    }

    @Override
    default ThrowingCharPredicate1 nor(final ThrowingCharPredicate1 other) {
        return (ThrowingCharPredicate1) AbstractThrowingCharPredicate1.super.nor(other);
    }

    @Override
    default ThrowingCharPredicate1 xnor(final ThrowingCharPredicate1 other) {
        return (ThrowingCharPredicate1) AbstractThrowingCharPredicate1.super.xnor(other);
    }

    @Override
    default ThrowingCharPredicate1 orNot(final ThrowingCharPredicate1 other) {
        return (ThrowingCharPredicate1) AbstractThrowingCharPredicate1.super.orNot(other);
    }
}
