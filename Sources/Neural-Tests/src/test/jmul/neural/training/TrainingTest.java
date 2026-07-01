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


import jmul.neural.network.Layout;
import jmul.math.numbers.Number;
import jmul.neural.network.Network;
import jmul.neural.network.NetworkHelper;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.neural.training.RandomEvolutionTrainerImpl;

import jmul.neural.training.Trainer;

import jmul.test.classification.UnitTest;

import jmul.util.data.Data;
import jmul.util.data.DataEntry;
import jmul.util.data.DataSet;

import jmul.util.data.Descriptors;

import org.junit.Test;


@UnitTest
public class TrainingTest {

    @Test
    public void testRandomEvolutionTrainer() {

        // prepare network

        int base = 10;

        Layout layout = new Layout(3, 4, 5, 4, 3);
        Network network = NetworkHelper.createNetwork(base, layout);


        // prepare trainer

        Number iterations = createNumber(base, "12");
        Number learningRate = createNumber(base, "0.5");
        Trainer trainer = new RandomEvolutionTrainerImpl(network, learningRate);


        // prepare data

        DataSet input = new DataSet(
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "1"), createNumber(base, "1"), createNumber(base, "1")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"), createNumber(base, "1"), createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "0.9"), createNumber(base, "1"), createNumber(base, "1")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"), createNumber(base, "0.9"), createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "0.8"), createNumber(base, "1"), createNumber(base, "1")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"), createNumber(base, "0.8"), createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "0.7"), createNumber(base, "1"), createNumber(base, "1")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"), createNumber(base, "0.7"), createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "0.6"), createNumber(base, "1"), createNumber(base, "1")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"), createNumber(base, "0.6"), createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "0.5"), createNumber(base, "1"), createNumber(base, "1")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"), createNumber(base, "0.5"), createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "0.4"), createNumber(base, "1"), createNumber(base, "1")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"), createNumber(base, "0.4"), createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "0.3"), createNumber(base, "1"), createNumber(base, "1")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"), createNumber(base, "0.3"), createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "0.2"), createNumber(base, "1"), createNumber(base, "1")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"), createNumber(base, "0.2"), createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "0.1"), createNumber(base, "1"), createNumber(base, "1")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"), createNumber(base, "0.1"), createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "0"), createNumber(base, "1"), createNumber(base, "1")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"), createNumber(base, "0"), createNumber(base, "1"))
            ),

            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "0.9"), createNumber(base, "0.9"), createNumber(base, "1")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"), createNumber(base, "0.9"), createNumber(base, "0.9"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "0.8"), createNumber(base, "0.8"), createNumber(base, "1")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"), createNumber(base, "0.8"), createNumber(base, "0.8"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "0.7"), createNumber(base, "0.7"), createNumber(base, "1")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"), createNumber(base, "0.7"), createNumber(base, "0.7"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "0.6"), createNumber(base, "0.6"), createNumber(base, "1")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"), createNumber(base, "0.6"), createNumber(base, "0.6"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "0.5"), createNumber(base, "0.5"), createNumber(base, "1")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"), createNumber(base, "0.5"), createNumber(base, "0.5"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "0.4"), createNumber(base, "0.4"), createNumber(base, "1")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"), createNumber(base, "0.4"), createNumber(base, "0.4"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "0.3"), createNumber(base, "0.3"), createNumber(base, "1")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"), createNumber(base, "0.3"), createNumber(base, "0.3"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "0.2"), createNumber(base, "0.2"), createNumber(base, "1")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"), createNumber(base, "0.2"), createNumber(base, "0.2"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "0.1"), createNumber(base, "0.1"), createNumber(base, "1")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"), createNumber(base, "0.1"), createNumber(base, "0.1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "0"), createNumber(base, "0"), createNumber(base, "1")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"), createNumber(base, "0"), createNumber(base, "0"))
            ),

            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "0.9"), createNumber(base, "1"), createNumber(base, "0.9")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "0.9"), createNumber(base, "0.9"), createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "0.8"), createNumber(base, "1"), createNumber(base, "0.8")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "0.8"), createNumber(base, "0.8"), createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "0.7"), createNumber(base, "1"), createNumber(base, "0.7")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "0.7"), createNumber(base, "0.7"), createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "0.6"), createNumber(base, "1"), createNumber(base, "0.6")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "0.6"), createNumber(base, "0.6"), createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "0.5"), createNumber(base, "1"), createNumber(base, "0.5")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "0.5"), createNumber(base, "0.5"), createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "0.4"), createNumber(base, "1"), createNumber(base, "0.4")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "0.4"), createNumber(base, "0.4"), createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "0.3"), createNumber(base, "1"), createNumber(base, "0.3")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "0.3"), createNumber(base, "0.3"), createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "0.2"), createNumber(base, "1"), createNumber(base, "0.2")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"), createNumber(base, "0.2"), createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "0.1"), createNumber(base, "1"), createNumber(base, "0.1")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "1"), createNumber(base, "0.1"), createNumber(base, "1"))
            ),
            new Data(
                new DataEntry(Descriptors.INPUT, createNumber(base, "0"), createNumber(base, "1"), createNumber(base, "0")),
                new DataEntry(Descriptors.EXPECTED_OUTPUT, createNumber(base, "0"), createNumber(base, "0"), createNumber(base, "1"))
            )
        );


        //train
        
        Number error = trainer.train(input, iterations);
    }

}
