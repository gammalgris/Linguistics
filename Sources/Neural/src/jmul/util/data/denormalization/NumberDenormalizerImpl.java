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


import jmul.math.intervals.Interval;
import jmul.math.intervals.IntervalHelper;
import jmul.math.intervals.Translator;
import jmul.math.numbers.Number;


/**
 * An implementation for a denormalizer for numbers.
 *
 * @author Kristian Kutin
 */
public class NumberDenormalizerImpl implements Denormalizer<Number> {

    /**
     * A translator.
     */
    private final Translator translator;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param intervalFrom
     *        an interval
     * @param intervalTo
     *        an interval
     */
    public NumberDenormalizerImpl(Interval intervalFrom, Interval intervalTo) {

        super();

        checkAndReturnParameter(intervalFrom);
        checkAndReturnParameter(intervalTo);

        this.translator = IntervalHelper.createTranslator(intervalFrom, intervalTo);
    }

    /**
     * Checks the specified parameter and returns it.
     *
     * @param interval
     *        an interval
     *
     * @return the specified parameter
     */
    private static Interval checkAndReturnParameter(Interval interval) {

        if (interval == null) {

            throw new IllegalArgumentException("No interval (null) was specified!");
        }

        return interval;
    }

    /**
     * Denormalizes the specified number.
     *
     * @param n
     *        a number
     *
     * @return a denormalized number
     */
    @Override
    public Number denormalize(Number n) {

        return translator.translate(n);
    }

}
