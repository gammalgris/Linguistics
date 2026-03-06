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

package jmul.prolog;


import java.util.Set;

import jmul.prolog.literals.Literal;


/**
 * This interface describes a repository for literals.
 *
 * @author Kristian Kutin
 */
public interface LiteralRepository extends Iterable<String> {

    /**
     * Returns the size of this repository (i.e. number of literals).
     *
     * @return the size of this repository (i.e. number of literals)
     */
    int size();

    /**
     * Clears this literal repository (i.e. deletes all literals).
     */
    void clear();

    /**
     * Exports all literals to the specified file. The literals are exported in a way to be parsable by a prolog
     * interpreter. Overrides existing files.
     *
     * @param filename
     *        a file path
     */
    void exportLiterals(String filename);

    /**
     * Imports literals from the specified file into this literal repository. Only a subset of the prolog language is
     * supported (i.e. rules and facts). Literals that are not currently supported will be ignored.
     *
     * @param filename
     *        a file path
     */
    void importLiterals(String filename);

    /**
     * Adds the specified literal to this literal repository.
     *
     * @param literal
     *        a literal (i.e. a rule or fact)
     */
    void addLiteral(Literal literal);

    /**
     * Removes tthe specified literal from this literal repository.
     *
     * @param literal
     *        a literal (i.e. a rule or fact)
     */
    void removeLiteral(Literal literal);

    /**
     * Looks up all literals which match the specified function symbol and returns a list of these literals.
     *
     * @param functionSymbol
     *        a function symbol
     *
     * @return list of matching literals
     */
    Set<Literal> lookUp(String functionSymbol);

}
