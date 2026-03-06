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
 * This interface defines a literal (see
 * <a href="https://en.wikipedia.org/wiki/Prolog">Prolog</a>,
 * <a href="https://en.wikipedia.org/wiki/Prolog#Syntax_and_semantics">Prolog - Syntax and Semantics</a>,
 * <a href="https://en.wikipedia.org/wiki/Horn_clause">Horn clause"</a>,
 * <a href="https://en.wikipedia.org/wiki/Literal_(mathematical_logic)">Literal</a>
 * ). Classes that implement this interface adhere to a prolog notation.<br>
 * <br>
 * <i>Note:<br>
 * In this context only a subset of prolog literals is implemented. Add missing implementations as needed.</i><br>
 * <br>
 * <i>Note:<br>
 * When exporting the literals, these should be usable (i.e. importable) with a prolog interpreter. This makes
 * it easier to test the literals.</i>
 *
 * @author Kristian Kutin
 */
public interface Literal extends Comparable<Literal> {

    /**
     * Returns the function symbol for this literal.
     *
     * @return a function symbol
     */
    String functionSymbol();

}
