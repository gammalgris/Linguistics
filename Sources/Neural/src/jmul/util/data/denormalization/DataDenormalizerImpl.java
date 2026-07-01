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

package jmul.util.data.denormalization;


import java.util.ArrayList;
import java.util.List;

import jmul.math.intervals.Interval;

import jmul.util.data.Data;
import jmul.util.data.DataEntry;


/**
 * An implementation for a denormalizer for data.
 *
 * @author Kristian Kutin
 */
public class DataDenormalizerImpl implements Denormalizer<Data> {

    /**
     * The actual normalizer.
     */
    private final Denormalizer<DataEntry> dataEntryDenormalizer;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param intervalFrom
     *        an interval
     * @param intervalTo
     *        an interval
     */
    public DataDenormalizerImpl(Interval intervalFrom, Interval intervalTo) {

        super();

        this.dataEntryDenormalizer = new DataEntryDenormalizerImpl(intervalFrom, intervalTo);
    }

    /**
     * Denormalizes the specified data.
     *
     * @param data
     *        some data
     *
     * @return some denormalized data
     */
    @Override
    public Data denormalize(Data data) {

        List<DataEntry> results = new ArrayList<>();

        for (DataEntry dataEntry : data) {

            DataEntry result = dataEntryDenormalizer.denormalize(dataEntry);
            results.add(result);
        }

        return new Data(results);
    }

}
