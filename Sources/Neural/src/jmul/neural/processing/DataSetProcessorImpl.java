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

package jmul.neural.processing;


import java.util.ArrayList;
import java.util.List;

import jmul.neural.network.Network;

import jmul.util.data.Data;
import jmul.util.data.DataSet;
import jmul.util.data.denormalization.Denormalizer;
import jmul.util.data.normalization.Normalizer;


/**
 * An implementation of a data processor for data sets.
 *
 * @author Kristian Kutin
 */
public class DataSetProcessorImpl implements Processor<DataSet> {

    /**
     * A data normalizer or <code>null</code> if no normalization has to be done.
     */
    private final Normalizer<DataSet> normalizer;

    /**
     * A data denormalizer or <code>null</code> if no denormalization has to be done.
     */
    private final Denormalizer<DataSet> denormalizer;

    /**
     * A processor for data entries.
     */
    private final Processor<Data> processor;

    /**
     * Creates a new data processor according to the specified parameters.
     *
     * @param normalizer
     *        a data normalizer or <code>null</code> if no normalization has to be done
     * @param denormalizer
     *        a data denormalizer or <code>null</code> if no denormalization has to be done
     * @param network
     *        a neural network
     */
    public DataSetProcessorImpl(Normalizer<DataSet> normalizer, Denormalizer<DataSet> denormalizer, Network network) {

        super();

        this.normalizer = normalizer;
        this.denormalizer = denormalizer;
        this.processor = new DataProcessorImpl(null, null, network);
    }

    /**
     * Processes the specified data entry and returns the result.
     *
     * @param input
     *        some input data
     *
     * @return some output
     */
    @Override
    public DataSet process(DataSet input) {

        checkAndReturnParameter(input);

        // normalize data

        DataSet normalizedInput = normalize(input);

        // process the data - feed the neural network

        List<Data> results = new ArrayList<>();

        for (Data data : input) {

            Data result = processor.process(data);
            results.add(result);
        }

        normalizedInput = new DataSet(results);

        // denormalize data

        return denormalize(normalizedInput);
    }

    /**
     * Checks the specified parameter and returns it.
     *
     * @param input
     *        a data entry
     *
     * @return the specified parameter
     */
    private static DataSet checkAndReturnParameter(DataSet input) {

        if (input == null) {

            throw new IllegalArgumentException("No data set (null) was specified!");
        }

        if (!input.iterator().hasNext()) {

            throw new IllegalArgumentException("No data set (empty set) was specified!");
        }

        return input;
    }

    /**
     * Normalizes the specified data.
     *
     * @param data
     *        some data
     *
     * @return normalized data
     */
    private DataSet normalize(DataSet data) {

        if (normalizer == null) {

            return data;
        }

        return normalizer.normalize(data);
    }

    /**
     * Denormalizes the specified data.
     *
     * @param data
     *        some data
     *
     * @return denormalized data
     */
    private DataSet denormalize(DataSet data) {

        if (denormalizer == null) {

            return data;
        }

        return denormalizer.denormalize(data);
    }

}
