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

package test.jmul.util.data;


import java.util.List;

import jmul.math.intervals.BoundaryTypes;
import jmul.math.intervals.Interval;
import jmul.math.intervals.IntervalHelper;
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.UnitTest;

import jmul.util.data.Data;
import jmul.util.data.DataEntry;
import jmul.util.data.DataHelper;
import jmul.util.data.DataSet;
import jmul.util.data.Descriptors;
import jmul.util.data.denormalization.DataDenormalizerImpl;
import jmul.util.data.denormalization.DataEntryDenormalizerImpl;
import jmul.util.data.denormalization.DataSetDenormalizerImpl;
import jmul.util.data.denormalization.Denormalizer;
import jmul.util.data.normalization.DataEntryNormalizerImpl;
import jmul.util.data.normalization.DataNormalizerImpl;
import jmul.util.data.normalization.DataSetNormalizerImpl;
import jmul.util.data.normalization.Normalizer;

import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * This test suite tests normalizing and denormalizing data sets.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class NormalizationAndDenormalizationTest {

    /**
     * Tests the normalization of a data entry.
     */
    @Test
    public void testNormalizeDataEntry() {

        int base = 10;

        DataEntry input = new DataEntry(Descriptors.INPUT, createNumber(base, "0.15"));
        DataEntry expectedOutput = new DataEntry(Descriptors.INPUT, createNumber(base, "-0.94"));

        Interval intervalFrom =
            IntervalHelper.createInterval(createNumber(base, "0"), BoundaryTypes.CLOSED_BOUNDARY,
                                          createNumber(base, "5"), BoundaryTypes.CLOSED_BOUNDARY);
        Interval intervalTo =
            IntervalHelper.createInterval(createNumber(base, "-1"), BoundaryTypes.CLOSED_BOUNDARY,
                                          createNumber(base, "1"), BoundaryTypes.CLOSED_BOUNDARY);

        Normalizer<DataEntry> normalizer = new DataEntryNormalizerImpl(intervalFrom, intervalTo);

        DataEntry actualOutput = normalizer.normalize(input);

        List<String> list = DataHelper.checkData(expectedOutput, actualOutput);
        assertTrue(list.toString(), list.isEmpty());
        assertTrue(list.toString(), DataHelper.compareData(expectedOutput, actualOutput));
    }

    /**
     * Tests the denormalization of a data entry.
     */
    @Test
    public void testDenormalizeDataEntry() {

        int base = 10;

        DataEntry input = new DataEntry(Descriptors.INPUT, createNumber(base, "-0.94"));
        DataEntry expectedOutput = new DataEntry(Descriptors.INPUT, createNumber(base, "0.15"));

        Interval intervalFrom =
            IntervalHelper.createInterval(createNumber(base, "0"), BoundaryTypes.CLOSED_BOUNDARY,
                                          createNumber(base, "5"), BoundaryTypes.CLOSED_BOUNDARY);
        Interval intervalTo =
            IntervalHelper.createInterval(createNumber(base, "-1"), BoundaryTypes.CLOSED_BOUNDARY,
                                          createNumber(base, "1"), BoundaryTypes.CLOSED_BOUNDARY);

        Denormalizer<DataEntry> denormalizer = new DataEntryDenormalizerImpl(intervalTo, intervalFrom);

        DataEntry actualOutput = denormalizer.denormalize(input);

        List<String> list = DataHelper.checkData(expectedOutput, actualOutput);
        assertTrue(list.toString(), list.isEmpty());
        assertTrue(list.toString(), DataHelper.compareData(expectedOutput, actualOutput));
    }

    /**
     * Tests the normalization of data.
     */
    @Test
    public void testNormalizeData() {

        int base = 10;

        Data input = new Data(new DataEntry(Descriptors.INPUT, createNumber(base, "0.15")));
        Data expectedOutput = new Data(new DataEntry(Descriptors.INPUT, createNumber(base, "-0.94")));

        Interval intervalFrom =
            IntervalHelper.createInterval(createNumber(base, "0"), BoundaryTypes.CLOSED_BOUNDARY,
                                          createNumber(base, "5"), BoundaryTypes.CLOSED_BOUNDARY);
        Interval intervalTo =
            IntervalHelper.createInterval(createNumber(base, "-1"), BoundaryTypes.CLOSED_BOUNDARY,
                                          createNumber(base, "1"), BoundaryTypes.CLOSED_BOUNDARY);

        Normalizer<Data> normalizer = new DataNormalizerImpl(intervalFrom, intervalTo);

        Data actualOutput = normalizer.normalize(input);

        List<String> list = DataHelper.checkData(expectedOutput, actualOutput);
        assertTrue(list.toString(), list.isEmpty());
        assertTrue(list.toString(), DataHelper.compareData(expectedOutput, actualOutput));
    }

    /**
     * Tests the denormalization of data.
     */
    @Test
    public void testDenormalizeData() {

        int base = 10;

        Data input = new Data(new DataEntry(Descriptors.INPUT, createNumber(base, "-0.94")));
        Data expectedOutput = new Data(new DataEntry(Descriptors.INPUT, createNumber(base, "0.15")));

        Interval intervalFrom =
            IntervalHelper.createInterval(createNumber(base, "0"), BoundaryTypes.CLOSED_BOUNDARY,
                                          createNumber(base, "5"), BoundaryTypes.CLOSED_BOUNDARY);
        Interval intervalTo =
            IntervalHelper.createInterval(createNumber(base, "-1"), BoundaryTypes.CLOSED_BOUNDARY,
                                          createNumber(base, "1"), BoundaryTypes.CLOSED_BOUNDARY);

        Denormalizer<Data> denormalizer = new DataDenormalizerImpl(intervalTo, intervalFrom);

        Data actualOutput = denormalizer.denormalize(input);

        List<String> list = DataHelper.checkData(expectedOutput, actualOutput);
        assertTrue(list.toString(), list.isEmpty());
        assertTrue(list.toString(), DataHelper.compareData(expectedOutput, actualOutput));
    }

    /**
     * Tests the normalization of data.
     */
    @Test
    public void testNormalizeDataSet() {

        int base = 10;

        DataSet input = new DataSet(new Data(new DataEntry(Descriptors.INPUT, createNumber(base, "0.15"))));
        DataSet expectedOutput = new DataSet(new Data(new DataEntry(Descriptors.INPUT, createNumber(base, "-0.94"))));

        Interval intervalFrom =
            IntervalHelper.createInterval(createNumber(base, "0"), BoundaryTypes.CLOSED_BOUNDARY,
                                          createNumber(base, "5"), BoundaryTypes.CLOSED_BOUNDARY);
        Interval intervalTo =
            IntervalHelper.createInterval(createNumber(base, "-1"), BoundaryTypes.CLOSED_BOUNDARY,
                                          createNumber(base, "1"), BoundaryTypes.CLOSED_BOUNDARY);

        Normalizer<DataSet> normalizer = new DataSetNormalizerImpl(intervalFrom, intervalTo);

        DataSet actualOutput = normalizer.normalize(input);

        List<String> list = DataHelper.checkData(expectedOutput, actualOutput);
        assertTrue(list.toString(), list.isEmpty());
        assertTrue(list.toString(), DataHelper.compareData(expectedOutput, actualOutput));
    }

    /**
     * Tests the denormalization of data.
     */
    @Test
    public void testDenormalizeDataSet() {

        int base = 10;

        DataSet input = new DataSet(new Data(new DataEntry(Descriptors.INPUT, createNumber(base, "-0.94"))));
        DataSet expectedOutput = new DataSet(new Data(new DataEntry(Descriptors.INPUT, createNumber(base, "0.15"))));

        Interval intervalFrom =
            IntervalHelper.createInterval(createNumber(base, "0"), BoundaryTypes.CLOSED_BOUNDARY,
                                          createNumber(base, "5"), BoundaryTypes.CLOSED_BOUNDARY);
        Interval intervalTo =
            IntervalHelper.createInterval(createNumber(base, "-1"), BoundaryTypes.CLOSED_BOUNDARY,
                                          createNumber(base, "1"), BoundaryTypes.CLOSED_BOUNDARY);

        Denormalizer<DataSet> denormalizer = new DataSetDenormalizerImpl(intervalTo, intervalFrom);

        DataSet actualOutput = denormalizer.denormalize(input);

        List<String> list = DataHelper.checkData(expectedOutput, actualOutput);
        assertTrue(list.toString(), list.isEmpty());
        assertTrue(list.toString(), DataHelper.compareData(expectedOutput, actualOutput));
    }

}
