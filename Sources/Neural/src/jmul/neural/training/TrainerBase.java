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

package jmul.neural.training;


import jmul.math.numbers.Number;

import jmul.neural.network.Network;

import jmul.util.data.DataSet;
import jmul.util.data.denormalization.Denormalizer;
import jmul.util.data.normalization.Normalizer;


abstract class TrainerBase implements Trainer {

    /**
     * A constant value.
     */
    protected static final Normalizer<DataSet> NO_NORMALIZATION;

    /**
     * A constant value.
     */
    protected static final Denormalizer<DataSet> NO_DENORMALIZATION;

    /*
     * The static initializer.
     */
    static {

        NO_NORMALIZATION = null;
        NO_DENORMALIZATION = null;
    }

    /**
     * A neural network.
     */
    protected Network network;

    /**
     * The current learning rate for training the network.
     */
    private Number learningRate;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param network
     *        a neural network
     * @param learningRate
     *        the learning rate for training the neural network
     */
    public TrainerBase(Network network, Number learningRate) {

        super();

        if (network == null) {

            throw new IllegalArgumentException("No neural network (null) was specified!");
        }

        if (learningRate == null) {

            throw new IllegalArgumentException("No learning rate (null) was specified!");
        }

        this.network = network;
        this.learningRate = learningRate;
    }

    /**
     * Returns the current learning rate.
     *
     * @return a learning rate
     */
    @Override
    public Number learningRate() {

        return learningRate;
    }

    /**
     * Updates the learning rate.
     *
     * @param learningRate
     *        a new learning rate
     */
    @Override
    public void updateLearningRate(Number learningRate) {

        if (learningRate == null) {

            throw new IllegalArgumentException("No learning rate (null) was specified!");
        }

        this.learningRate = learningRate;
    }

    /**
     * Returns the trained network.
     *
     * @return a trained network
     */
    @Override
    public Network network() {

        return network;
    }

}
