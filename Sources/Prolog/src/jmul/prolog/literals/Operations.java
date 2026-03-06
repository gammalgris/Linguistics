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


/**
 * This enumeration contains various logical operations.
 *
 * @author Kristian Kutin
 */
public enum Operations implements Operation {


    /**
     * Conjunction corresponds to the logical operation AND.
     */
    CONJUNCTION(","),

    /**
     * Disjunction corresponds to the logical operation OR.
     */
    DISJUNCTION(";");


    /**
     * A symbol for this operation.
     */
    private final String symbol;

    /**
     * Creates a new enumeration element according to the specified parameter.
     *
     * @param symbol
     *        a symbol for this operation
     */
    private Operations(String symbol) {

        if (symbol == null) {

            throw new IllegalArgumentException();
        }

        this.symbol = symbol;
    }

    /**
     * Returns the operation symbol.
     *
     * @return an operation symbol
     */
    @Override
    public String symbol() {

        return symbol;
    }

    /**
     * Looks for an enumeration entry with a mathing operation symbol.
     *
     * @param s
     *        a string (i.e. an operation symbol)
     *
     * @return a matching enumeration element
     */
    public static Operation parseString(String s) {

        if (s == null) {

            throw new IllegalArgumentException("No string (null) was specified!");
        }

        for (Operation operation : values()) {

            String actualSymbol = operation.symbol();

            if (actualSymbol.equals(s)) {

                return operation;
            }
        }

        String message = String.format("An unknown operation symbol (\"%s\") was specified!", s);
        throw new IllegalArgumentException(message);
    }

}
