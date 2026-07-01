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

package jmul.util.data.normalization;


import java.util.ArrayList;
import java.util.List;

import jmul.math.intervals.Interval;

import jmul.util.data.Data;
import jmul.util.data.DataSet;


/**
 * An implementation for a normalizer for a data set.
 *
 * @author Kristian Kutin
 */
public class DataSetNormalizerImpl implements Normalizer<DataSet> {

    /**
     * The actual normalizer.
     */
    private final Normalizer<Data> dataNormalizer;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param intervalFrom
     *        an interval
     * @param intervalTo
     *        an interval
     */
    public DataSetNormalizerImpl(Interval intervalFrom, Interval intervalTo) {

        super();

        this.dataNormalizer = new DataNormalizerImpl(intervalFrom, intervalTo);
    }

    /**
     * Normalizes the specified data set.
     *
     * @param dataSet
     *        a data set
     *
     * @return a normalized data set
     */
    @Override
    public DataSet normalize(DataSet dataSet) {

        List<Data> results = new ArrayList<>();

        for (Data data : dataSet) {

            Data result = dataNormalizer.normalize(data);
            results.add(result);
        }

        return new DataSet(results);
    }

}
