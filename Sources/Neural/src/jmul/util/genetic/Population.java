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

package jmul.util.genetic;


import java.util.Collection;

import jmul.math.numbers.Number;


/**
 * This itnerface descreibes a population.
 *
 * @param <T>
 *        the type of indidivuals
 *
 * @author Kristian Kutin
 */
public interface Population<T> extends Iterable<T> {

    /**
     * Returns a collection containing all individuals.
     *
     * @return a collection containing all individuals
     */
    Collection<T> individuals();

    /**
     * Adds the specified individual to this population.
     *
     * @param individual
     *        an individual
     */
    void add(T individual);

    /**
     * Checks if this population has any individuals.
     *
     * @return <code>true</code> if this population has individuals, else <code>false</code>
     */
    boolean isEmpty();

    /**
     * Adds the specified population to this population.
     *
     * @param population
     *        a population
     */
    void add(Collection<T> population);

    /**
     * Returns the size of this population.
     *
     * @return the size of this population
     */
    int size();

    /**
     * Resets the ranking of individuals.
     */
    void resetRankings();

    /**
     * Updates the ranking for the specified individual.
     *
     * @param individual
     *        an individual
     * @param rank
     *        a rank
     */
    void updateRanking(T individual, Number rank);

    /**
     * Calculates the average ranking for this population.
     *
     * @return an average ranking
     */
    Number averageRanking();

    /**
     * Returns a subset of this population that is below the specified rank threshold.
     *
     * @param rankThreshold
     *        a rank threshold
     *
     * @return a subset of this population
     */
    Collection<T> getIndividuals(Number rankThreshold);

    /**
     * Returns the best individual.
     *
     * @return the best individual
     */
    T getBestIndividual();

    /**
     * Returns the rank associated with the specified individual.
     *
     * @param individual
     *        an individual
     *
     * @return a rank or <code>null</code> if no rank was associated with the specified individual
     */
    Number getRanking(T individual);

}
