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

package jmul.prolog.serialization;


/**
 * A helepr class with string utilities.<br>
 * <br>
 * <i>Note:<br>
 * The utility methods don't check parameters. Be careful how you use them. The invoking code should be testet
 * accordingly.</i>
 *
 * @author Kristian Kutin
 */
public final class StringHelper {

    /**
     * The default constructor.
     */
    private StringHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Checks if the specified character sequence ends with the specified end sequence.
     *
     * @param sequence
     *        a char sequence
     * @param end
     *        a char sequence
     *
     * @return <code>true</code> if the specified character sequence ends with the specified end sequence,
     *         else <code>false</code>
     */
    public static boolean endsWith(CharSequence sequence, CharSequence end) {

        int length = sequence.length();
        int endPatternLength = end.length();

        if (length < endPatternLength) {

            return false;
        }

        int startIndex1 = length - endPatternLength;
        int startIndex2 = 0;

        for (int offset = 0; offset < endPatternLength; offset++) {

            int index1 = startIndex1 + offset;
            int index2 = startIndex2 + offset;

            char a = sequence.charAt(index1);
            char b = end.charAt(index2);

            if (a != b) {

                return false;
            }
        }

        return true;
    }

    /**
     * Returns a new character sequence where the specified number of characters is removed from the end.
     *
     * @param sequence
     *        a char sequence
     * @param charactersToRemove
     *        the number of characters to remove
     *
     * @return a string with the characters removed at the end
     */
    public static CharSequence removeEnd(CharSequence sequence, int charactersToRemove) {

        return sequence.subSequence(0, sequence.length() - charactersToRemove);
    }

}
