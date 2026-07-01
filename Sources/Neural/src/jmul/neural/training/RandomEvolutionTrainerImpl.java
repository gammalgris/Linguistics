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


import java.util.Collection;

import jmul.math.Math;
import jmul.math.numbers.Number;
import jmul.math.random.Die;
import jmul.math.random.DieImpl;
import jmul.math.random.StandardDice;
import jmul.math.vectors.Vector;

import jmul.metainfo.annotations.Modified;

import jmul.neural.cells.Synapse;
import jmul.neural.network.Layer;
import jmul.neural.network.Network;
import jmul.neural.network.NetworkHelper;
import jmul.neural.processing.DataSetProcessorImpl;
import jmul.neural.processing.Processor;

import jmul.util.data.DataHelper;
import jmul.util.data.DataSet;
import jmul.util.genetic.Population;
import jmul.util.genetic.PopulationImpl;


public class RandomEvolutionTrainerImpl extends TrainerBase {

    /**
     * An index value.
     */
    private static final int FIRST_ELEMENT_INDEX;

    /*
     * The static initializer.
     */
    static {

        FIRST_ELEMENT_INDEX = 0;
    }

    /**
     * A population of individual networks.
     */
    private Population<Network> population;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param network
     *        a neural network
     * @param learningRate
     *        the learning rate for training the neural network
     */
    public RandomEvolutionTrainerImpl(Network network, Number learningRate) {

        super(network, learningRate);

        this.population = null;
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

        Number remainingIterations = iterations;

        Number preTrainingError = evaluateNeuralNetwork(network, dataSet);
        Number currentError = preTrainingError;
        System.out.println("DEBUG::currentErrorThreshold=" + currentError);

        while (!remainingIterations.isZero()) {

            currentError = trainingCycle(dataSet, currentError);
            System.out.println("DEBUG::currentErrorThreshold=" + currentError);

            remainingIterations = remainingIterations.dec();
        }

        return currentError;
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
     * @param globalErrorThreshold
     *        a global error threshold
     *
     * @return the average network error
     */
    private Number trainingCycle(DataSet dataSet, Number globalErrorThreshold) {

        int base = dataSet.base();

        final int populationSize = 40;
        final int generations = 6;


        // prepare the population for the initial training round

        if (population == null) {

            population = new PopulationImpl<>(base);

            for (int index = 0; index < populationSize; index++) {

                Network clone = NetworkHelper.cloneNetwork(network);
                population.add(clone);
            }
        }


        // mutate the population over several generations.

        for (int generation = 1; generation <= generations; generation++) {

            System.out.println("DEBUG::generation=" + generation);
            for (Network individual : population) {

                mutateNeuralNetwork(individual, learningRate());
            }
        }


        // rank the population

        for (Network individual : population) {

            Number localError = evaluateNeuralNetwork(individual, dataSet);
            population.updateRanking(individual, localError);
        }

        network = population.getBestIndividual();


        // take the best results from the population

        Number averageLocalError = population.averageRanking();
        Number errorThreshold = Math.min(globalErrorThreshold, averageLocalError);

        Collection<Network> subset = population.getIndividuals(errorThreshold);
        if (subset.isEmpty()) {

            subset.add(network);
            errorThreshold = globalErrorThreshold;
        }


        // replenish population

        population = new PopulationImpl<>(base);
        population.add(subset);

        boolean loop = true;
        while (loop) {

            for (Network network : subset) {

                Network clone = NetworkHelper.cloneNetwork(network);
                population.add(clone);

                if (population.size() >= populationSize) {

                    loop = false;
                    break;
                }
            }
        }

        return errorThreshold;
    }

    /**
     * Evaluates the speicified neural network.
     *
     * @param network
     *        a neural network
     * @param dataSet
     *        a data set to evaluate weights and biases
     *
     * @return the average error
     */
    private static Number evaluateNeuralNetwork(Network network, DataSet dataSet) {

        Processor<DataSet> processor = new DataSetProcessorImpl(NO_NORMALIZATION, NO_DENORMALIZATION, network);

        DataSet output = processor.process(dataSet);
        Vector errorVector = DataHelper.calculateMeanAbsoluteErrorVector(output);
        Number error = errorVector.length();

        return error;
    }

    private static void mutateNeuralNetwork(@Modified Network network, Number learningRate) {

        Die die = StandardDice.D2;

        int result = die.roll();

        if (result == 1) {

            mutateWeight(network, learningRate);

        } else {

            mutateBias(network, learningRate);
        }
    }

    private static void mutateWeight(@Modified Network network, Number learningRate) {

        Die die;

        int layers = network.synapseLayers();
        die = new DieImpl(layers);

        int synapseLayerIndex = die.roll() - 1;

        int synapses = network.synapsesInLayer(synapseLayerIndex).size();
        die = new DieImpl(synapses);

        int synapseIndex = die.roll() - 1;

        Synapse synapse = network.synapsesInLayer(synapseLayerIndex).get(synapseIndex);

        Number newWeight = calculateNewValue(synapse.weight(), learningRate);
        synapse.updateWeight(newWeight);
    }

    private static Number calculateNewValue(Number value, Number learningRate) {

        int sign = randomSign();
        Number change = learningRate;

        if (sign < 0) {

            change = change.negate();
        }

        Number newValue = value.add(change);

        return newValue;
    }

    private static int randomSign() {

        int result = StandardDice.D2.roll();

        if (result == 1) {

            return -1;

        } else {

            return 1;
        }
    }

    private static void mutateBias(@Modified Network network, Number learningRate) {

        Die die;

        int layers = network.neuronLayers();
        die = new DieImpl(layers);

        int index = 0;
        while ((index == 0) || (index == layers - 1)) {

            index = die.roll() - 1;
        }

        Layer layer = network.layer(index);
        Number newBias = calculateNewValue(layer.bias(), learningRate);

        layer.updateBias(newBias);
    }

}


