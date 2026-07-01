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


/**
 * An implementation of a backpropagation algorithm for training a neural network.
 *
 * @author Kristian Kutin
 */
public class BackpropagationTrainerImpl extends TrainerBase implements Trainer {

    /**
     * A neural network.
     */
    private Network network;

    /**
     * The current learnign rate for training the network.
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
    public BackpropagationTrainerImpl(Network network, Number learningRate) {

        super(network, learningRate);

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
     * Trains a neural network with the specified training data and returns the average network error according to the
     * last training iteration.
     *
     * @param dataSet
     *        a data set (i.e. data which contains input and expected output)
     * @param iterations
     *        an iteration depth
     *
     * @return the average network error
     */
    @Override
    public Number train(DataSet dataSet, Number iterations) {

        checkAndReturnParameter(dataSet);
        checkAndReturnParameter(iterations);

        Number averageNetworkError = null;
        Number remainingIterations = iterations;

        while (!remainingIterations.isZero()) {

            averageNetworkError = trainingCycle(dataSet);

            remainingIterations = remainingIterations.dec();
        }

        return averageNetworkError;
    }

    /**
     * Checks and returns the specified the specified parameter.
     *
     * @param dataSet
     *        a data set
     *
     * @return the specified parameter
     */
    private static DataSet checkAndReturnParameter(DataSet dataSet) {

        if (dataSet == null) {

            throw new IllegalArgumentException("No data sets (null) have been specified!");
        }

        return dataSet;
    }

    /**
     * Checks and returns the specified the specified parameter.
     *
     * @param iterations
     *        an iteration depth
     *
     * @return the specified parameter
     */
    private static Number checkAndReturnParameter(Number iterations) {

        if (iterations == null) {

            throw new IllegalArgumentException("No iteration depth (null) was specified!");
        }

        if (iterations.isNegative()) {

            throw new IllegalArgumentException("An invalid iteration depth (negative number) was specified!");
        }

        return iterations;
    }

    /**
     * Performs a single training cycle according to the specified training data.
     *
     * @param dataSet
     *        a data set (i.e. data which contains input and expected output)
     *
     * @return the average network error
     */
    private Number trainingCycle(DataSet dataSet) {

        //TODO

        return null;
    }

    private Number trainLayer(DataSet dataSet) {

        //TODO

        return null;
    }

    private Number trainNeuron(DataSet dataSet) {

        //TODO

        return null;
    }

}
