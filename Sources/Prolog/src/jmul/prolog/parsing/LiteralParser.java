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

package jmul.prolog.parsing;


import java.util.ArrayList;
import java.util.List;

import jmul.prolog.literals.Literal;


/**
 * An implementation of a parser for all supported literals (i.e. facts and rules).
 *
 * @author Kristian Kutin
 */
public class LiteralParser implements Parser {

    /**
     * A set of parsers which do the actual parsing.
     */
    private List<Parser> parsers;

    /**
     * The default constructor.
     */
    public LiteralParser() {

        super();

        parsers = new ArrayList<>();
        parsers.add(new FactParser());
        parsers.add(new FactVariant2Parser());
        parsers.add(new RuleParser());
    }

    /**
     * Parses the specified string and returns a literal.
     *
     * @param line
     *        a string
     *
     * @return a literal
     */
    @Override
    public Literal parse(String line) {

        if (line == null) {

            throw new IllegalArgumentException("No line (null) was specified!");
        }

        Literal literal = null;
        for (Parser parser : parsers) {

            try {

                literal = parser.parse(line);
                break;

            } catch (ParsingException e) {

                continue;
            }
        }

        return literal;
    }

}
