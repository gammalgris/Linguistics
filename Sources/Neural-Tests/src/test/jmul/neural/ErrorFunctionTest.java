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

package test.jmul.neural;


import java.util.List;

import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.UnitTest;

import jmul.util.data.Data;
import jmul.util.data.DataEntry;
import jmul.util.data.DataHelper;
import jmul.util.data.DataSet;
import jmul.util.data.Descriptors;

import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * This test suite tests calculating error functions.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class ErrorFunctionTest {

    /**
     * Tests calculating the mean absolute error.
     */
    @Test
    public void testCalculatingMeanAbsoluteError() {

        int base = 10;

        DataSet dataSet =
            new DataSet(new Data(new DataEntry(Descriptors.INPUT, createNumber(base, "0.2")),
                                 new DataEntry(Descriptors.OUTPUT, createNumber(base, "0.04")),
                                 new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "0"))),
                        new Data(new DataEntry(Descriptors.INPUT, createNumber(base, "0.3")),
                                 new DataEntry(Descriptors.OUTPUT, createNumber(base, "0.09")),
                                 new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "0.2"))),
                        new Data(new DataEntry(Descriptors.INPUT, createNumber(base, "0.4")),
                                 new DataEntry(Descriptors.OUTPUT, createNumber(base, "0.16")),
                                 new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "0.4"))),
                        new Data(new DataEntry(Descriptors.INPUT, createNumber(base, "0.7")),
                                 new DataEntry(Descriptors.OUTPUT, createNumber(base, "0.49")),
                                 new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"))),
                        new Data(new DataEntry(Descriptors.INPUT, createNumber(base, "0.95")),
                                 new DataEntry(Descriptors.OUTPUT, createNumber(base, "0.9025")),
                                 new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "0.2"))),
                        new Data(new DataEntry(Descriptors.INPUT, createNumber(base, "1.3")),
                                 new DataEntry(Descriptors.OUTPUT, createNumber(base, "1.69")),
                                 new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"))),
                        new Data(new DataEntry(Descriptors.INPUT, createNumber(base, "1.6")),
                                 new DataEntry(Descriptors.OUTPUT, createNumber(base, "2.56")),
                                 new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "2"))),
                        new Data(new DataEntry(Descriptors.INPUT, createNumber(base, "1.8")),
                                 new DataEntry(Descriptors.OUTPUT, createNumber(base, "3.24")),
                                 new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "3"))),
                        new Data(new DataEntry(Descriptors.INPUT, createNumber(base, "1.95")),
                                 new DataEntry(Descriptors.OUTPUT, createNumber(base, "3.8025")),
                                 new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "3"))));

        DataEntry expectedResult = new DataEntry(Descriptors.AVERAGE_MEAN_ERROR, createNumber(base, "0.4327777777345"));
        DataEntry actualResult = DataHelper.calculateMeanAbsoluteError(dataSet);

        List<String> list = DataHelper.checkData(expectedResult, actualResult);
        assertTrue(list.toString(), list.isEmpty());
        assertTrue(list.toString(), DataHelper.compareData(expectedResult, actualResult));
    }

}
