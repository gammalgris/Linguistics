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


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.signs.Signs;


/**
 * An implementation of a population.
 *
 * @param <T>
 *        the type of individuals
 *
 * @author Kristian Kutin
 */
public class PopulationImpl<T> implements Population<T> {

    /**
     * A number base.
     */
    private final int base;

    /**
     * All individuals of this population.
     */
    private List<T> individuals;

    /**
     * A ranking for the individuals.
     */
    private Map<T, Number> rankings;

    /**
     * Creates a new instance according to the specified parameter.
     *
     * @param base
     *        a number base
     */
    public PopulationImpl(int base) {

        super();

        this.base = base;
        this.individuals = new ArrayList<>();
        this.rankings = new HashMap<>();
    }

    /**
     * Returns a collection containing all individuals.<br>
     * <br>
     * <i>Note:<br>
     * Be careful how you manipulate the collection. A reference to the actual data structure that cintains all
     * individuals is returned.</i>
     *
     * @return a collection containing all individuals
     */
    @Override
    public Collection<T> individuals() {

        return individuals;
    }

    /**
     * Returns an iterator for this population.
     *
     * @return an iterator
     */
    @Override
    public Iterator<T> iterator() {

        return individuals.iterator();
    }

    /**
     * Returns a string representation for this population.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return individuals.toString();
    }

    /**
     * Adds the specified individual to this population.
     *
     * @param individual
     *        an individual
     */
    @Override
    public void add(T individual) {

        checkAndReturnParameter(individual);

        individuals.add(individual);
    }

    /**
     * Checks the specified parameter.
     *
     * @param individual
     *        an individual
     *
     * @return the specified individual
     */
    private T checkAndReturnParameter(T individual) {

        if (individual == null) {

            throw new IllegalArgumentException("No individual (null) was specified!");
        }

        return individual;
    }

    /**
     /**
     * Adds the specified population to this population.
     *
     * @param population
     *        a population
     */
    @Override
    public void add(Collection<T> population) {

        checkAndReturnParameter(population);

        individuals.addAll(population);
    }

    /**
     * Checks the specified parameter.
     *
     * @param population
     *        a population
     *
     * @return the specified individual
     */
    private Collection<T> checkAndReturnParameter(Collection<T> population) {

        if (population == null) {

            throw new IllegalArgumentException("No population (null) was specified!");
        }

        if (population.isEmpty()) {

            throw new IllegalArgumentException("No population (empty collection) was specified!");
        }

        return population;
    }

    /**
     * Returns the size of this population.
     *
     * @return the size of this population
     */
    @Override
    public int size() {

        return individuals.size();
    }

    /**
     * Resets the ranking of individuals.
     */
    @Override
    public void resetRankings() {

        rankings.clear();
    }

    /**
     * Updates the ranking for the specified individual.
     *
     * @param individual
     *        an individual
     * @param rank
     *        a rank
     */
    @Override
    public void updateRanking(T individual, Number rank) {

        checkAndReturnParameter(individual);
        checkAndReturnParameter(rank);

        if (!individuals.contains(individual)) {

            throw new IllegalArgumentException("An unknown individual was specified!");
        }

        if (rank.base() != base) {

            throw new IllegalArgumentException("The specified rank is in a different number base!");
        }

        rankings.put(individual, rank);
    }

    /**
     * Checks the specified parameter.
     *
     * @param rank
     *        a rank
     *
     * @return the specified parameter
     */
    private Number checkAndReturnParameter(Number rank) {

        if (rank == null) {

            throw new IllegalArgumentException("No rank (null) was specified!");
        }

        return rank;
    }

    /**
     * Calculates the average ranking for this population.
     *
     * @return an average ranking
     */
    @Override
    public Number averageRanking() {

        final Number ZERO = createNumber(base, Signs.POSITIVE, 0);

        Number counter = ZERO;
        Number sum = ZERO;

        for (T individual : rankings.keySet()) {

            Number ranking = rankings.get(individual);
            sum = sum.add(ranking);
            counter = counter.inc();
        }

        if (counter.isZero()) {

            return sum;
        }

        Number average = sum.divide(counter);

        return average;
    }

    /**
     * Returns a subset of this population that is below the specified rank threshold.
     *
     * @param rankThreshold
     *        a rank threshold
     *
     * @return a subset of this population
     */
    @Override
    public Collection<T> getIndividuals(Number rankThreshold) {

        Collection<T> subset = new ArrayList<>();

        for (T individual : rankings.keySet()) {

            Number ranking = rankings.get(individual);

            if (ranking.isLesser(rankThreshold)) {

                subset.add(individual);
            }
        }

        return subset;
    }

    /**
     * Checks if this population has any individuals.
     *
     * @return <code>true</code> if this population has individuals, else <code>false</code>
     */
    @Override
    public boolean isEmpty() {

        return individuals.isEmpty();
    }

    /**
     * Returns the best individual.
     *
     * @return the best individual
     */
    @Override
    public T getBestIndividual() {

        Number bestRank = null;
        T bestIndividual = null;

        for (T individual : rankings.keySet()) {

            Number rank = rankings.get(individual);

            if (bestIndividual == null) {

                bestRank = rank;
                bestIndividual = individual;

            } else {

                if (rank.isLesser(bestRank)) {

                    bestRank = rank;
                    bestIndividual = individual;
                }
            }
        }

        return bestIndividual;
    }

    /**
     * Returns the rank associated with the specified individual.
     *
     * @param individual
     *        an individual
     *
     * @return a rank or <code>null</code> if no rank was associated with the specified individual
     */
    @Override
    public Number getRanking(T individual) {

        checkAndReturnParameter(individual);

        if (!individuals.contains(individual)) {

            throw new IllegalArgumentException("An unknown individual was specified!");
        }

        return rankings.get(individual);
    }

}
