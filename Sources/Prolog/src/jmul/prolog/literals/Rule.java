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

package jmul.prolog.literals;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import jmul.math.hash.HashHelper;


/**
 * This class implements a rule (i.e. a literal in prolog notation, see
 * <a href="https://en.wikipedia.org/wiki/Prolog#Rules_and_facts">Prolog - Rules and Facts</a>,
 * <a href="https://en.wikipedia.org/wiki/Literal_(mathematical_logic)">Literal</a>
 * ).
 *
 * @author Kristian Kutin
 */
public final class Rule extends AbstractLiteral {

    /**
     * The operation which is applied on the results.
     */
    public final Operation operation;

    /**
     * A list of reference symbols (i.e. function symbols).
     */
    public final List<String> referenceSymbols;

    /**
     * Creates a new Rule according to the specified parameters.
     *
     * @param functionSymbol
     *        a function symbol (i.e. string)
     * @param operation
     *        an operation
     * @param referenceSymbols
     *        an array of function symbols (i.e. strings)
     */
    public Rule(String functionSymbol, Operation operation, String... referenceSymbols) {

        super(functionSymbol);

        if (operation == null) {

            throw new IllegalArgumentException("No operation (null) was specified!");
        }

        if (referenceSymbols == null) {

            throw new IllegalArgumentException("No reference symbols (null array) was specified!");
        }

        if (referenceSymbols.length == 0) {

            throw new IllegalArgumentException("No reference symbols (empty array) was specified!");
        }

        this.operation = operation;
        this.referenceSymbols = Collections.unmodifiableList(Arrays.asList(referenceSymbols));
    }

    /**
     * Returns a prolog notation of this fact.
     *
     * @return a prolog notation
     */
    @Override
    public String toString() {

        StringBuilder buffer = new StringBuilder();

        final String variableSymbol = "X";

        buffer.append(functionSymbol);
        buffer.append("(");
        buffer.append(variableSymbol);
        buffer.append(") :-");

        boolean first = true;

        for (String referenceSymbol : referenceSymbols) {

            if (first) {

                first = false;

            } else {

                buffer.append(operation.symbol());
            }

            buffer.append(" ");
            buffer.append(referenceSymbol);
            buffer.append("(");
            buffer.append(variableSymbol);
            buffer.append(")");
        }

        buffer.append(".");

        return buffer.toString();
    }

    /**
     * Compares this object with the specified object.
     *
     * @param o
     *        another object
     *
     * @return <code>true</code> if this object and the specified object are considered to be equal,
     *         else <code>false</code>
     */
    @Override
    public boolean equals(Object o) {

        if (null == o) {

            return false;
        }

        if (this == o) {

            return true;
        }

        if (o instanceof Rule) {

            Rule other = (Rule) o;

            return this.functionSymbol.equals(other.functionSymbol) && this.operation.equals(other.operation) &&
                   (this.referenceSymbols.equals(other.referenceSymbols));
        }

        return false;
    }

    /**
     * Caclulates a hash value for this literal.
     *
     * @return a hash value
     */
    @Override
    public int hashCode() {

        return HashHelper.calculateHashCode(Literal.class, functionSymbol, operation, referenceSymbols);
    }

    /**
     * Compares this literal with the specified literal.
     *
     * @param o
     *        a literal
     *
     * @return -1, 0, 1 if this literal is lesser, equal or greater than the specified literal
     */
    @Override
    public int compareTo(Literal o) {

        if (o == null) {

            throw new IllegalArgumentException("No literal (null) was specified!");
        }

        if (this.functionSymbol().equals(o.functionSymbol())) {

            String thisName = this.getClass().getName();
            String otherName = o.getClass().getName();

            return thisName.compareTo(otherName);
        }

        return this.functionSymbol().compareTo(o.functionSymbol());
    }

}
