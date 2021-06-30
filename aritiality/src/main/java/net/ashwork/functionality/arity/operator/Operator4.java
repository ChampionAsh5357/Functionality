/*
 * Aritiality (Functionality)
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.arity.operator;

import net.ashwork.functionality.arity.function.Function4;

import java.util.function.UnaryOperator;

/**
 * Represents an operation upon four operands of the same type, producing a result
 * of the same type as the operands. This is a specialization of {@link Function4}
 * for the case where the operands and the result are all of the same type.
 *
 * <p>This is a functional interface whose functional method is
 * {@link #apply(Object, Object, Object, Object)}.
 *
 * @param <T> The type of the operands and result of the operator
 *
 * @see Function4
 * @see UnaryOperator
 * @since 2.0.0
 */
@FunctionalInterface
public interface Operator4<T> extends Function4<T, T, T, T, T> {}
