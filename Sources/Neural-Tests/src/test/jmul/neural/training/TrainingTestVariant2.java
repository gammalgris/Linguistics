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

package test.jmul.neural.training;


import jmul.math.intervals.BoundaryTypes;
import jmul.math.intervals.Interval;
import jmul.math.intervals.IntervalHelper;
import jmul.math.intervals.IntervalImpl;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.neural.network.Layout;
import jmul.neural.network.Network;
import jmul.neural.network.NetworkHelper;
import jmul.neural.processing.DataSetProcessorImpl;
import jmul.neural.processing.Processor;
import jmul.neural.training.RandomEvolutionTrainerImpl;
import jmul.neural.training.Trainer;

import jmul.test.classification.UnitTest;

import jmul.util.data.Data;
import jmul.util.data.DataEntry;
import jmul.util.data.DataHelper;
import jmul.util.data.DataSet;
import jmul.util.data.Descriptors;

import jmul.util.data.denormalization.DataSetDenormalizerImpl;
import jmul.util.data.denormalization.Denormalizer;
import jmul.util.data.normalization.DataEntryNormalizerImpl;
import jmul.util.data.normalization.DataSetNormalizerImpl;
import jmul.util.data.normalization.Normalizer;

import org.junit.Test;


@UnitTest
public class TrainingTestVariant2 {

    @Test
    public void testRandomEvolutionTrainer() {

        // prepare network

        // 26 letters * 2 (lower case and upper case)
        // + 10 digits
        // -> base 62

        int base = 62;

        Layout layout = new Layout(1, 8, 4, 2, 1);
        Network network = NetworkHelper.createNetwork(base, layout);


        // prepare trainer

        Number iterations = createNumber(base, "10");
        Number learningRate = (createNumber(base, "1")).halving();
        Trainer trainer = new RandomEvolutionTrainerImpl(network, learningRate);


        // prepare data

        DataSet input = new DataSet(
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "the")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "a")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "car")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "0"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "cat")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "0"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "area")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "0"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "bear")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "0"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "day")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "0"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "voyage")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "0"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "she")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "0"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "he")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "0"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "it")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "0"))
            ),

            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "zombie")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "0"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "an")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "xerox")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "satellite")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "k")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "render")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "height")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "taint")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "quartz")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "gold")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"))
            ),

            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "silver")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "men")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "women")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "law")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "more")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "pain")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "here")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "is")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "jump")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "oral")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"))
            )
        );

        Interval intervalFrom = IntervalHelper.createInterval(createNumber(base, "0"), BoundaryTypes.CLOSED_BOUNDARY, createNumber(base, "satellite"), BoundaryTypes.CLOSED_BOUNDARY);
        Interval intervalTo = IntervalHelper.createInterval(createNumber(base, "0"), BoundaryTypes.CLOSED_BOUNDARY, createNumber(base, "1"), BoundaryTypes.CLOSED_BOUNDARY);
        Normalizer<DataSet> normalizer = new DataSetNormalizerImpl(intervalFrom, intervalTo);
        Denormalizer<DataSet> denormalizer = new DataSetDenormalizerImpl(intervalTo, intervalFrom);

        DataSet normalizedInput = normalizer.normalize(input);


        //train
        
        Number error = trainer.train(normalizedInput, iterations);
        Network trainedNetwork = trainer.network();
        
        Processor<DataSet> processor = new DataSetProcessorImpl(normalizer, denormalizer, trainedNetwork);
        DataSet output = processor.process(input);
        System.out.println(output);
    }

}
