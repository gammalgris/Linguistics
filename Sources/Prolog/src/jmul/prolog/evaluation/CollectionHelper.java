/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2026  Kristian Kutin
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * e-mail: kristian.kutin@arcor.de
 */

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.prolog.evaluation;


import java.util.Arrays;

import jmul.math.collections.Set;
import jmul.math.collections.SetImpl;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;


/**
 * A helper class for custom collections.
 *
 * @author Kristian Kutin
 */
public final class CollectionHelper {

    /**
     * A number base.
     */
    private static final int DEFAULT_NUMBER_BASE;

    /*
     * The static initializer.
     */
    static {

        DEFAULT_NUMBER_BASE = 10;
    }

    /**
     * The default constructor.
     */
    private CollectionHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Creates a set with the specified elements.
     *
     * @param elements
     *        an array containing any number of elements
     *
     * @return a set containing the specified elements
     */
    public static Set<String> newSet(String... elements) {

        Set<String> set = new SetImpl<String>(DEFAULT_NUMBER_BASE, elements);

        return new SetImpl<String>(DEFAULT_NUMBER_BASE, elements);
    }

    /**
     * Creates an empty set.
     *
     * @return an empty set
     */
    public static Set<String> emptySet() {

        return new SetImpl<String>(DEFAULT_NUMBER_BASE);
    }

    /**
     * Sorts the specified set.
     *
     * @param unsortedSet
     *        an unsorted set
     *
     * @return a sorted set
     */
    public static Set<String> sort(Set<String> unsortedSet) {

        final Number ZERO = createNumber(DEFAULT_NUMBER_BASE, "0");
        final Number MAX = unsortedSet.elements();

        int length = MAX.toPrimitiveInt();
        String[] array = new String[length];

        for (Number index = ZERO; index.isLesser(MAX); index = index.inc()) {

            int primitiveIndex = index.toPrimitiveInt();
            array[primitiveIndex] = unsortedSet.ordinal(index);
        }

        Arrays.sort(array);

        return newSet(array);
    }

    /**
     * Checks if the specified sets contain the same elements.
     *
     * @param set1
     *        a set
     * @param set2
     *        a set
     *
     * @return <code>true</code> if both sets contain the same elements, else <code>false</code>
     */
    public static boolean areEqualSets(Set<String> set1, Set<String> set2) {

        Set<String> symmetricDifference = set1.symmetricDifference(set2);

        return symmetricDifference.isEmpty();
    }

}
