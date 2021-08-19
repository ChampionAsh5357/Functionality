/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.test;

import net.ashwork.functionality.primitive.combined.*;
import org.junit.jupiter.api.Test;

/**
 * A testing class that tests all combined primitive functions.
 */
public final class CombinedFunctionTests {

    /**
     * Tests the {@code boolean}-resulting function.
     */
    @Test
    public void booleans() {
        ByteToBooleanFunction1 byteBoolean = o -> BooleanFunctionTests.FUNCTION.apply(new Object[]{o});
        BooleanFunctionTests.toBooleanCFT(null, a -> byteBoolean, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsBoolean((byte) o), o1 -> ByteFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        CharToBooleanFunction1 charBoolean = o -> BooleanFunctionTests.FUNCTION.apply(new Object[]{o});
        BooleanFunctionTests.toBooleanCFT(null, a -> charBoolean, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsBoolean((char) o), o1 -> CharFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        DoubleToBooleanFunction1 doubleBoolean = o -> BooleanFunctionTests.FUNCTION.apply(new Object[]{o});
        BooleanFunctionTests.toBooleanCFT(null, a -> doubleBoolean, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsBoolean((double) o), o1 -> DoubleFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        FloatToBooleanFunction1 floatBoolean = o -> BooleanFunctionTests.FUNCTION.apply(new Object[]{o});
        BooleanFunctionTests.toBooleanCFT(null, a -> floatBoolean, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsBoolean((float) o), o1 -> FloatFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        IntToBooleanFunction1 intBoolean = o -> BooleanFunctionTests.FUNCTION.apply(new Object[]{o});
        BooleanFunctionTests.toBooleanCFT(null, a -> intBoolean, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsBoolean((int) o), o1 -> IntFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        LongToBooleanFunction1 longBoolean = o -> BooleanFunctionTests.FUNCTION.apply(new Object[]{o});
        BooleanFunctionTests.toBooleanCFT(null, a -> longBoolean, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsBoolean((long) o), o1 -> LongFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        ShortToBooleanFunction1 shortBoolean = o -> BooleanFunctionTests.FUNCTION.apply(new Object[]{o});
        BooleanFunctionTests.toBooleanCFT(null, a -> shortBoolean, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsBoolean((short) o), o1 -> ShortFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));
    }

    /**
     * Tests the {@code byte}-resulting function.
     */
    @Test
    public void bytes() {
        BooleanToByteFunction1 booleanByte = o -> ByteFunctionTests.FUNCTION.apply(new Object[]{o});
        ByteFunctionTests.toByteCFT(null, a -> booleanByte, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsByte((boolean) o), o1 -> BooleanFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        CharToByteFunction1 charByte = o -> ByteFunctionTests.FUNCTION.apply(new Object[]{o});
        ByteFunctionTests.toByteCFT(null, a -> charByte, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsByte((char) o), o1 -> CharFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        DoubleToByteFunction1 doubleByte = o -> ByteFunctionTests.FUNCTION.apply(new Object[]{o});
        ByteFunctionTests.toByteCFT(null, a -> doubleByte, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsByte((double) o), o1 -> DoubleFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        FloatToByteFunction1 floatByte = o -> ByteFunctionTests.FUNCTION.apply(new Object[]{o});
        ByteFunctionTests.toByteCFT(null, a -> floatByte, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsByte((float) o), o1 -> FloatFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        IntToByteFunction1 intByte = o -> ByteFunctionTests.FUNCTION.apply(new Object[]{o});
        ByteFunctionTests.toByteCFT(null, a -> intByte, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsByte((int) o), o1 -> IntFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        LongToByteFunction1 longByte = o -> ByteFunctionTests.FUNCTION.apply(new Object[]{o});
        ByteFunctionTests.toByteCFT(null, a -> longByte, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsByte((long) o), o1 -> LongFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        ShortToByteFunction1 shortByte = o -> ByteFunctionTests.FUNCTION.apply(new Object[]{o});
        ByteFunctionTests.toByteCFT(null, a -> shortByte, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsByte((short) o), o1 -> ShortFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));
    }

    /**
     * Tests the {@code char}-resulting function.
     */
    @Test
    public void chars() {
        ByteToCharFunction1 byteChar = o -> CharFunctionTests.FUNCTION.apply(new Object[]{o});
        CharFunctionTests.toCharCFT(null, a -> byteChar, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsChar((byte) o), o1 -> ByteFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        BooleanToCharFunction1 booleanChar = o -> CharFunctionTests.FUNCTION.apply(new Object[]{o});
        CharFunctionTests.toCharCFT(null, a -> booleanChar, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsChar((boolean) o), o1 -> BooleanFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        DoubleToCharFunction1 doubleChar = o -> CharFunctionTests.FUNCTION.apply(new Object[]{o});
        CharFunctionTests.toCharCFT(null, a -> doubleChar, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsChar((double) o), o1 -> DoubleFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        FloatToCharFunction1 floatChar = o -> CharFunctionTests.FUNCTION.apply(new Object[]{o});
        CharFunctionTests.toCharCFT(null, a -> floatChar, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsChar((float) o), o1 -> FloatFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        IntToCharFunction1 intChar = o -> CharFunctionTests.FUNCTION.apply(new Object[]{o});
        CharFunctionTests.toCharCFT(null, a -> intChar, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsChar((int) o), o1 -> IntFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        LongToCharFunction1 longChar = o -> CharFunctionTests.FUNCTION.apply(new Object[]{o});
        CharFunctionTests.toCharCFT(null, a -> longChar, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsChar((long) o), o1 -> LongFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        ShortToCharFunction1 shortChar = o -> CharFunctionTests.FUNCTION.apply(new Object[]{o});
        CharFunctionTests.toCharCFT(null, a -> shortChar, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsChar((short) o), o1 -> ShortFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));
    }

    /**
     * Tests the {@code double}-resulting function.
     */
    @Test
    public void doubles() {
        ByteToDoubleFunction1 byteDouble = o -> DoubleFunctionTests.FUNCTION.apply(new Object[]{o});
        DoubleFunctionTests.toDoubleCFT(null, a -> byteDouble, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsDouble((byte) o), o1 -> ByteFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        CharToDoubleFunction1 charDouble = o -> DoubleFunctionTests.FUNCTION.apply(new Object[]{o});
        DoubleFunctionTests.toDoubleCFT(null, a -> charDouble, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsDouble((char) o), o1 -> CharFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        BooleanToDoubleFunction1 booleanDouble = o -> DoubleFunctionTests.FUNCTION.apply(new Object[]{o});
        DoubleFunctionTests.toDoubleCFT(null, a -> booleanDouble, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsDouble((boolean) o), o1 -> BooleanFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        FloatToDoubleFunction1 floatDouble = o -> DoubleFunctionTests.FUNCTION.apply(new Object[]{o});
        DoubleFunctionTests.toDoubleCFT(null, a -> floatDouble, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsDouble((float) o), o1 -> FloatFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        IntToDoubleFunction1 intDouble = o -> DoubleFunctionTests.FUNCTION.apply(new Object[]{o});
        DoubleFunctionTests.toDoubleCFT(null, a -> intDouble, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsDouble((int) o), o1 -> IntFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        LongToDoubleFunction1 longDouble = o -> DoubleFunctionTests.FUNCTION.apply(new Object[]{o});
        DoubleFunctionTests.toDoubleCFT(null, a -> longDouble, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsDouble((long) o), o1 -> LongFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        ShortToDoubleFunction1 shortDouble = o -> DoubleFunctionTests.FUNCTION.apply(new Object[]{o});
        DoubleFunctionTests.toDoubleCFT(null, a -> shortDouble, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsDouble((short) o), o1 -> ShortFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));
    }

    /**
     * Tests the {@code float}-resulting function.
     */
    @Test
    public void floats() {
        ByteToFloatFunction1 byteFloat = o -> FloatFunctionTests.FUNCTION.apply(new Object[]{o});
        FloatFunctionTests.toFloatCFT(null, a -> byteFloat, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsFloat((byte) o), o1 -> ByteFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        CharToFloatFunction1 charFloat = o -> FloatFunctionTests.FUNCTION.apply(new Object[]{o});
        FloatFunctionTests.toFloatCFT(null, a -> charFloat, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsFloat((char) o), o1 -> CharFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        DoubleToFloatFunction1 doubleFloat = o -> FloatFunctionTests.FUNCTION.apply(new Object[]{o});
        FloatFunctionTests.toFloatCFT(null, a -> doubleFloat, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsFloat((double) o), o1 -> DoubleFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        BooleanToFloatFunction1 booleanFloat = o -> FloatFunctionTests.FUNCTION.apply(new Object[]{o});
        FloatFunctionTests.toFloatCFT(null, a -> booleanFloat, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsFloat((boolean) o), o1 -> BooleanFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        IntToFloatFunction1 intFloat = o -> FloatFunctionTests.FUNCTION.apply(new Object[]{o});
        FloatFunctionTests.toFloatCFT(null, a -> intFloat, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsFloat((int) o), o1 -> IntFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        LongToFloatFunction1 longFloat = o -> FloatFunctionTests.FUNCTION.apply(new Object[]{o});
        FloatFunctionTests.toFloatCFT(null, a -> longFloat, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsFloat((long) o), o1 -> LongFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        ShortToFloatFunction1 shortFloat = o -> FloatFunctionTests.FUNCTION.apply(new Object[]{o});
        FloatFunctionTests.toFloatCFT(null, a -> shortFloat, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsFloat((short) o), o1 -> ShortFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));
    }

    /**
     * Tests the {@code int}-resulting function.
     */
    @Test
    public void ints() {
        ByteToIntFunction1 byteInt = o -> IntFunctionTests.FUNCTION.apply(new Object[]{o});
        IntFunctionTests.toIntCFT(null, a -> byteInt, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsInt((byte) o), o1 -> ByteFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        CharToIntFunction1 charInt = o -> IntFunctionTests.FUNCTION.apply(new Object[]{o});
        IntFunctionTests.toIntCFT(null, a -> charInt, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsInt((char) o), o1 -> CharFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        DoubleToIntFunction1 doubleInt = o -> IntFunctionTests.FUNCTION.apply(new Object[]{o});
        IntFunctionTests.toIntCFT(null, a -> doubleInt, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsInt((double) o), o1 -> DoubleFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        FloatToIntFunction1 floatInt = o -> IntFunctionTests.FUNCTION.apply(new Object[]{o});
        IntFunctionTests.toIntCFT(null, a -> floatInt, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsInt((float) o), o1 -> FloatFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        BooleanToIntFunction1 booleanInt = o -> IntFunctionTests.FUNCTION.apply(new Object[]{o});
        IntFunctionTests.toIntCFT(null, a -> booleanInt, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsInt((boolean) o), o1 -> BooleanFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        LongToIntFunction1 longInt = o -> IntFunctionTests.FUNCTION.apply(new Object[]{o});
        IntFunctionTests.toIntCFT(null, a -> longInt, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsInt((long) o), o1 -> LongFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        ShortToIntFunction1 shortInt = o -> IntFunctionTests.FUNCTION.apply(new Object[]{o});
        IntFunctionTests.toIntCFT(null, a -> shortInt, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsInt((short) o), o1 -> ShortFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));
    }

    /**
     * Tests the {@code long}-resulting function.
     */
    @Test
    public void longs() {
        ByteToLongFunction1 byteLong = o -> LongFunctionTests.FUNCTION.apply(new Object[]{o});
        LongFunctionTests.toLongCFT(null, a -> byteLong, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsLong((byte) o), o1 -> ByteFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        CharToLongFunction1 charLong = o -> LongFunctionTests.FUNCTION.apply(new Object[]{o});
        LongFunctionTests.toLongCFT(null, a -> charLong, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsLong((char) o), o1 -> CharFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        DoubleToLongFunction1 doubleLong = o -> LongFunctionTests.FUNCTION.apply(new Object[]{o});
        LongFunctionTests.toLongCFT(null, a -> doubleLong, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsLong((double) o), o1 -> DoubleFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        FloatToLongFunction1 floatLong = o -> LongFunctionTests.FUNCTION.apply(new Object[]{o});
        LongFunctionTests.toLongCFT(null, a -> floatLong, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsLong((float) o), o1 -> FloatFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        IntToLongFunction1 intLong = o -> LongFunctionTests.FUNCTION.apply(new Object[]{o});
        LongFunctionTests.toLongCFT(null, a -> intLong, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsLong((int) o), o1 -> IntFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        BooleanToLongFunction1 booleanLong = o -> LongFunctionTests.FUNCTION.apply(new Object[]{o});
        LongFunctionTests.toLongCFT(null, a -> booleanLong, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsLong((boolean) o), o1 -> BooleanFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        ShortToLongFunction1 shortLong = o -> LongFunctionTests.FUNCTION.apply(new Object[]{o});
        LongFunctionTests.toLongCFT(null, a -> shortLong, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsLong((short) o), o1 -> ShortFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));
    }

    /**
     * Tests the {@code short}-resulting function.
     */
    @Test
    public void shorts() {
        ByteToShortFunction1 byteShort = o -> ShortFunctionTests.FUNCTION.apply(new Object[]{o});
        ShortFunctionTests.toShortCFT(null, a -> byteShort, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsShort((byte) o), o1 -> ByteFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        CharToShortFunction1 charShort = o -> ShortFunctionTests.FUNCTION.apply(new Object[]{o});
        ShortFunctionTests.toShortCFT(null, a -> charShort, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsShort((char) o), o1 -> CharFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        DoubleToShortFunction1 doubleShort = o -> ShortFunctionTests.FUNCTION.apply(new Object[]{o});
        ShortFunctionTests.toShortCFT(null, a -> doubleShort, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsShort((double) o), o1 -> DoubleFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        FloatToShortFunction1 floatShort = o -> ShortFunctionTests.FUNCTION.apply(new Object[]{o});
        ShortFunctionTests.toShortCFT(null, a -> floatShort, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsShort((float) o), o1 -> FloatFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        IntToShortFunction1 intShort = o -> ShortFunctionTests.FUNCTION.apply(new Object[]{o});
        ShortFunctionTests.toShortCFT(null, a -> intShort, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsShort((int) o), o1 -> IntFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        LongToShortFunction1 longShort = o -> ShortFunctionTests.FUNCTION.apply(new Object[]{o});
        ShortFunctionTests.toShortCFT(null, a -> longShort, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsShort((long) o), o1 -> LongFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));

        BooleanToShortFunction1 booleanShort = o -> ShortFunctionTests.FUNCTION.apply(new Object[]{o});
        ShortFunctionTests.toShortCFT(null, a -> booleanShort, (r, a, f, t, i, e) -> FunctionTests.testFunction1(r, i0 -> o -> f.apply(i0).applyAsShort((boolean) o), o1 -> BooleanFunctionTests.FUNCTION.apply(new Object[]{o1}), t, i, e));
    }
}
