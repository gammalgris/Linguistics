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


import jmul.math.hash.HashHelper;


/**
 * This class implements a fact (i.e. a literal in prolog notation, see
 * <a href="https://en.wikipedia.org/wiki/Prolog#Rules_and_facts">Prolog - Rules and Facts</a>,
 * <a href="https://en.wikipedia.org/wiki/Literal_(mathematical_logic)">Literal</a>
 * ).
 *
 * @author Kristian Kutin
 */
public final class Fact extends AbstractLiteral {

    /**
     * A constant symbol.
     */
    public final String constantSymbol;

    /**
     * The function result.
     */
    public final boolean functionResult;

    /**
     * Creates a new fact according to the specified parameters.
     *
     * @param functionSymbol
     *        a function symbol (i.e. string)
     * @param constantSymbol
     *        a constant symbol (i.e. a string)
     * @param functionResult
     *        the function result
     */
    public Fact(String functionSymbol, String constantSymbol, boolean functionResult) {

        super(functionSymbol);

        if (constantSymbol == null) {

            throw new IllegalArgumentException("No constant symbol (null) was specified!");
        }

        if (constantSymbol.trim().isEmpty()) {

            throw new IllegalArgumentException("No constant symbol (empty string) was specified!");
        }

        this.constantSymbol = constantSymbol;
        this.functionResult = functionResult;
    }

    /**
     * Creates a new fact according to the specified parameters.
     *
     * @param functionSymbol
     *        a function symbol (i.e. string)
     * @param constantSymbol
     *        a constant symbol (i.e. a string)
     */
    public Fact(String functionSymbol, String constantSymbol) {

        this(functionSymbol, constantSymbol, true);
    }

    /**
     * Returns a prolog notation of this fact.
     *
     * @return a prolog notation
     */
    @Override
    public String toString() {

        return String.format("%s(%s) :- %b.", functionSymbol, constantSymbol, functionResult);
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

        if (o instanceof Fact) {

            Fact other = (Fact) o;

            return this.functionSymbol.equals(other.functionSymbol) &&
                   this.constantSymbol.equals(other.constantSymbol) && (this.functionResult == other.functionResult);
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

        return HashHelper.calculateHashCode(Literal.class, functionSymbol, constantSymbol, functionResult);
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

        boolean equalFunctionSymbols = this.functionSymbol().equals(o.functionSymbol());

        if (o instanceof Fact) {

            Fact other = (Fact) o;

            if (equalFunctionSymbols) {

                return this.constantSymbol.compareTo(other.constantSymbol);
            }
        }

        if (equalFunctionSymbols) {

            String thisName = this.getClass().getName();
            String otherName = o.getClass().getName();

            return thisName.compareTo(otherName);
        }

        return this.functionSymbol().compareTo(o.functionSymbol());
    }

}
