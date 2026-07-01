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

package test.jmul.neural.signals;


import jmul.math.numbers.Number;
import jmul.math.numbers.NumberHelper;

import jmul.neural.cells.Cell;
import jmul.neural.signals.Signal;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;


/**
 * This test suite tests creating signals.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class CreateSignalTest {

    /**
     * Tests creating a signal with valid parameters.
     */
    @Test
    public void testCreateSignalWithoutSender() {

        int base = 10;
        Number number = NumberHelper.createNumber(base, "1");

        Signal signal = new Signal(null, number);

        assertNull("#sender", signal.sender);
        assertEquals("#value", number, signal.value);
    }

    /**
     * Tests creating a signal with valid parameters.
     */
    @Test
    public void testCreateSignalWithSender() {

        int base = 10;
        Number number = NumberHelper.createNumber(base, "1");
        Cell cell = new DummyCell();

        Signal signal = new Signal(cell, number);

        assertEquals("#sender", cell, signal.sender);
        assertEquals("#value", number, signal.value);
    }

}


/**
 * A dummy implementation of a cell which is used for testing purposes.
 *
 * @author Kristian Kutin.
 */
class DummyCell implements Cell {

}
