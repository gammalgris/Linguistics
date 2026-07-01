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

import jmul.math.numbers.Number;
import jmul.math.intervals.Interval;

import jmul.util.data.DataEntry;


/**
 * An implementation for a normalizer for data entries.
 *
 * @author Kristian Kutin
 */
public class DataEntryNormalizerImpl implements Normalizer<DataEntry> {

    /**
     * The actual normalizer.
     */
    private final Normalizer<Number> numberNormalizer;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param intervalFrom
     *        an interval
     * @param intervalTo
     *        an interval
     */
    public DataEntryNormalizerImpl(Interval intervalFrom, Interval intervalTo) {

        super();

        this.numberNormalizer = new NumberNormalizerImpl(intervalFrom, intervalTo);
    }

    /**
     * Normalizes the specified data entry.
     *
     * @param dataEntry
     *        a data entry
     *
     * @return a normalized data entry
     */
    @Override
    public DataEntry normalize(DataEntry dataEntry) {

        List<Number> results = new ArrayList<>();

        for (Number number : dataEntry) {

            Number result = numberNormalizer.normalize(number);
            results.add(result);
        }

        return new DataEntry(dataEntry.descriptor, results);
    }

}
