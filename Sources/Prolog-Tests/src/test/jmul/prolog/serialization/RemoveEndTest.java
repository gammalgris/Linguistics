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

package test.jmul.prolog.serialization;


import jmul.prolog.serialization.StringHelper;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suite testsremoving characters from the end of a string.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class RemoveEndTest {

    /**
     * Tests removing a certain number of characters from the end of a string.
     */
    @Test
    public void testEndsWith() {

        String s = "Wello World!";
        int characters = 2;

        CharSequence expectedResult = "Wello Worl";
        CharSequence actualResult = StringHelper.removeEnd(s, characters);

        assertEquals("#check", expectedResult, actualResult);
    }

    /**
     * Tests removing zero characters from the end of a string.
     */
    @Test
    public void testEndsWith_Variant2() {

        String s = "Wello World!";
        int characters = 0;

        CharSequence expectedResult = "Wello World!";
        CharSequence actualResult = StringHelper.removeEnd(s, characters);

        assertEquals("#check", expectedResult, actualResult);
    }

    /**
     * Tests removing characters from the end of a string. The string is <code>null</code>.
     */
    @Test(expected = NullPointerException.class)
    public void testEndsWith_Variant3() {

        String s = null;
        int characters = 1;

        StringHelper.removeEnd(s, characters);
    }

    /**
     * Tests removing characters from the end of a string. An negative number of characters is specified.
     */
    @Test(expected = StringIndexOutOfBoundsException.class)
    public void testEndsWith_Variant4() {

        String s = "Wello World!";
        int characters = -1;

        StringHelper.removeEnd(s, characters);
    }

}
