/*
 * Functionality
 * Copyright (c) 2021-2021 ChampionAsh5357.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package net.ashwork.functionality.test.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A general utility for creating random instances.
 */
public final class RandomUtil {

    /**
     * A random instance.
     */
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    /**
     * @return Gets a cached {@link ThreadLocalRandom} instance
     */
    public static ThreadLocalRandom current() {
        return RANDOM;
    }
}
