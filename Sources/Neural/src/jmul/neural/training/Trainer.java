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
 * This interface describes an entity which trains a neural network.
 *
 * @author Kristian Kutin
 */
public interface Trainer {

    /**
     * Returns the trained network.
     *
     * @return a trained network
     */
    Network network();

    /**
     * Returns the current learning rate.
     *
     * @return a learning rate
     */
    Number learningRate();

    /**
     * Updates the learning rate.
     *
     * @param learningRate
     *        a new learning rate
     */
    void updateLearningRate(Number learningRate);

    /**
     * Trains a neural network with the specified training data and returns the average network error according to the
     * last training iteration.
     *
     * @param dataSet
     *        a data set (i.e. data which contains input and expected output)
     *
     * @return the average network error
     */
    Number train(DataSet dataSet, Number iterations);

}
