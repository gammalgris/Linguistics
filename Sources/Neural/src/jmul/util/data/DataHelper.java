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

package jmul.util.data;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.signs.Signs;
import jmul.math.vectors.Vector;
import jmul.math.vectors.VectorHelper;


/**
 * A helper class for normalization and denormalization of training data.
 *
 * @author Kristian Kutin
 */
public final class DataHelper {

    /**
     * The default constructor.
     */
    private DataHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Calculates the normalized error for the specified data.
     *
     * @param data
     *        some data
     *
     * @return a vector with all normalized errors
     */
    private static Vector normalizedError(Data data) {

        if (data == null) {

            throw new IllegalArgumentException("No data (null) was specified!");
        }

        if (!data.containsDataEntry(Descriptors.OUTPUT)) {

            throw new IllegalArgumentException("The specified data doesn't contain output data!");
        }

        if (!data.containsDataEntry(Descriptors.EXPECTED_OUTPUT)) {

            throw new IllegalArgumentException("The specified data doesn't contain expected output data!");
        }

        int base = data.base();

        List<Number> results = new ArrayList<>();

        DataEntry outputDataEntry = data.get(Descriptors.OUTPUT);
        DataEntry expectedOutputDataEntry = data.get(Descriptors.EXPECTED_OUTPUT);

        Iterator<Number> iteratorOutput = outputDataEntry.iterator();
        Iterator<Number> iteratorExpectedOutput = expectedOutputDataEntry.iterator();

        while (iteratorOutput.hasNext() && iteratorExpectedOutput.hasNext()) {

            Number output = iteratorOutput.next();
            Number expectedOutput = iteratorExpectedOutput.next();

            Number normalizedError = (expectedOutput.subtract(output)).absoluteValue();
            results.add(normalizedError);
        }

        if (iteratorOutput.hasNext() != iteratorExpectedOutput.hasNext()) {

            throw new IllegalArgumentException("The specified data is inconsistent!");
        }

        return VectorHelper.createVector(base, results);
    }

    /**
     * Compares the specified data.
     *
     * @param data1
     *        a data entry
     * @param data2
     *        a data entry
     *
     * @return <code>true</code> if both data entries are considered equal, else <code>false</code>
     */
    public static boolean compareData(DataEntry data1, DataEntry data2) {

        if ((data1 == null) && (data2 == null)) {

            return true;
        }

        if (data1 == null) {

            return false;
        }

        if (data2 == null) {

            return false;
        }

        Iterator<Number> iterator1 = data1.iterator();
        Iterator<Number> iterator2 = data2.iterator();

        while (iterator1.hasNext() && iterator2.hasNext()) {

            Number number1 = iterator1.next();
            Number number2 = iterator2.next();

            if (!number1.equals(number2)) {

                return false;
            }
        }

        if (iterator1.hasNext() != iterator2.hasNext()) {

            return false;
        }

        return true;
    }

    /**
     * Compares the specified data.
     *
     * @param data1
     *        some data
     * @param data2
     *        some data
     *
     * @return <code>true</code> if the data is consdiered equal, else <code>false</code>
     */
    public static boolean compareData(Data data1, Data data2) {

        if ((data1 == null) && (data2 == null)) {

            return true;
        }

        if (data1 == null) {

            return false;
        }

        if (data2 == null) {

            return false;
        }

        for (Descriptor descriptor : Descriptors.values()) {

            if (!data1.containsDataEntry(descriptor) && !data2.containsDataEntry(descriptor)) {

                continue;
            }

            if (data1.containsDataEntry(descriptor) != data2.containsDataEntry(descriptor)) {

                return false;
            }

            DataEntry dataEntry1 = data1.get(descriptor);
            DataEntry dataEntry2 = data2.get(descriptor);

            if (!compareData(dataEntry1, dataEntry2)) {

                return false;
            }
        }

        return true;
    }

    /**
     * Compares the specified data sets.
     *
     * @param set1
     *        a data set
     * @param set2
     *        a data set
     *
     * @return <code>true</code> if the data sets are consdiered equal, else <code>false</code>
     */
    public static boolean compareData(DataSet set1, DataSet set2) {

        if ((set1 == null) && (set2 == null)) {

            return true;
        }

        if (set1 == null) {

            return false;
        }

        if (set2 == null) {

            return false;
        }

        Iterator<Data> iteratorData1 = set1.iterator();
        Iterator<Data> iteratorData2 = set2.iterator();

        while (iteratorData1.hasNext() && iteratorData2.hasNext()) {

            Data data1 = iteratorData1.next();
            Data data2 = iteratorData2.next();

            if (!compareData(data1, data2)) {

                return false;
            }
        }

        if (iteratorData1.hasNext() != iteratorData2.hasNext()) {

            return false;
        }

        return true;
    }

    /**
     * Compares the specified data.
     *
     * @param data1
     *        a data entry
     * @param data2
     *        a data entry
     *
     * @return a list of differences
     */
    public static List<String> checkData(DataEntry data1, DataEntry data2) {

        List<String> list = new ArrayList<>();

        if ((data1 == null) && (data2 == null)) {

            return list;
        }

        if (data1 == null) {

            list.add("No first data entry (null) was specified!");
            return list;
        }

        if (data2 == null) {

            list.add("No second data entry (null) was specified!");
            return list;
        }

        Iterator<Number> iterator1 = data1.iterator();
        Iterator<Number> iterator2 = data2.iterator();

        while (iterator1.hasNext() && iterator2.hasNext()) {

            Number number1 = iterator1.next();
            Number number2 = iterator2.next();

            if (!number1.equals(number2)) {

                list.add(String.format("%s != %s", number1, number2));
            }
        }

        if (iterator1.hasNext() != iterator2.hasNext()) {

            list.add("One data entry contains more numbers!");
        }

        return list;
    }

    /**
     * Compares the specified data.
     *
     * @param data1
     *        some data
     * @param data2
     *        some data
     *
     * @return a list of differences
     */
    public static List<String> checkData(Data data1, Data data2) {

        List<String> list = new ArrayList<>();

        if ((data1 == null) && (data2 == null)) {

            return list;
        }

        if (data1 == null) {

            list.add("No first data entry (null) was specified!");
            return list;
        }

        if (data2 == null) {

            list.add("No second data entry (null) was specified!");
            return list;
        }

        for (Descriptor descriptor : Descriptors.values()) {

            if (!data1.containsDataEntry(descriptor) && !data2.containsDataEntry(descriptor)) {

                continue;
            }

            if (data1.containsDataEntry(descriptor) != data2.containsDataEntry(descriptor)) {

                list.add(String.format("The \"%s\" data entry doesn't exist in both data entries!", descriptor));
                return list;
            }

            DataEntry dataEntry1 = data1.get(descriptor);
            DataEntry dataEntry2 = data2.get(descriptor);

            List<String> subList = checkData(dataEntry1, dataEntry2);
            if (!subList.isEmpty()) {

                list.addAll(subList);
                return list;
            }
        }

        return list;
    }

    /**
     * Compares the specified data sets.
     *
     * @param set1
     *        a data set
     * @param set2
     *        a data set
     *
     * @return a list of differences
     */
    public static List<String> checkData(DataSet set1, DataSet set2) {

        List<String> list = new ArrayList<>();

        if ((set1 == null) && (set2 == null)) {

            return list;
        }

        if (set1 == null) {

            list.add("No first data set (null) was specified!");
            return list;
        }

        if (set2 == null) {

            list.add("No second data set (null) was specified!");
            return list;
        }

        Iterator<Data> iteratorData1 = set1.iterator();
        Iterator<Data> iteratorData2 = set2.iterator();

        while (iteratorData1.hasNext() && iteratorData2.hasNext()) {

            Data data1 = iteratorData1.next();
            Data data2 = iteratorData2.next();

            List<String> subList = checkData(data1, data2);
            if (!subList.isEmpty()) {

                list.addAll(subList);
                return list;
            }
        }

        if (iteratorData1.hasNext() != iteratorData2.hasNext()) {

            list.add("One data set contains more numbers!");
            return list;
        }

        return list;
    }

    /**
     * Calculates the average mean error for the specified data set.
     *
     * @param set
     *        a data set
     *
     * @return the average mean error
     */
    public static DataEntry calculateMeanAbsoluteError(DataSet set) {

        Vector averageNormalizedError = calculateMeanAbsoluteErrorVector(set);

        return new DataEntry(Descriptors.AVERAGE_MEAN_ERROR, averageNormalizedError);
    }

    /**
     * Calculates the average mean error for the specified data set.
     *
     * @param set
     *        a data set
     *
     * @return the average mean error as vector
     */
    public static Vector calculateMeanAbsoluteErrorVector(DataSet set) {

        if (set == null) {

            throw new IllegalArgumentException("No data set (null) was specified!");
        }

        int base = set.base();

        Number errorCount = createNumber(base, Signs.POSITIVE, 0);
        Vector summedNormalizedErrors = null;

        for (Data data : set) {

            Vector result = normalizedError(data);

            if (summedNormalizedErrors == null) {

                summedNormalizedErrors = result;

            } else {

                summedNormalizedErrors = summedNormalizedErrors.add(result);
            }

            errorCount = errorCount.inc();
        }

        Number factor = errorCount.reciprocal().evaluate();
        Vector averageNormalizedError = summedNormalizedErrors.multiply(factor);

        return averageNormalizedError;
    }

}
