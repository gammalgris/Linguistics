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


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jmul.prolog.literals.Fact;
import jmul.prolog.literals.Literal;


/**
 * An implementation of a parser for literals (i.e. facts).
 *
 * @author Kristian Kutin
 */
public class FactParser implements Parser {

    /**
     * The name of a captchuring group.
     */
    private static final String FUNCTION_SYMBOL_GROUP_NAME;

    /**
     * The name of a captchuring group.
     */
    private static final String CONSTANT_SYMBOL_GROUP_NAME;

    /**
     * The name of a captchuring group.
     */
    private static final String BOOLEAN_GROUP_NAME;

    /**
     * A regular expression matching facts.
     */
    private static final String FACT_PATTERN_STRING;

    /*
     * The static initializer.
     */
    static {

        FUNCTION_SYMBOL_GROUP_NAME = "functionSymbol";
        CONSTANT_SYMBOL_GROUP_NAME = "constantSymbol";
        BOOLEAN_GROUP_NAME = "boolean";

        FACT_PATTERN_STRING =
            String.format("^(?<%s>[^(]+)[(](?<%s>[^)]+)[)][ ][:][-][ ](?<%s>(true|false))[.]$",
                          FUNCTION_SYMBOL_GROUP_NAME, CONSTANT_SYMBOL_GROUP_NAME, BOOLEAN_GROUP_NAME);
    }

    /**
     * A pattern matcher.
     */
    private final Pattern pattern;

    /**
     * The default constructor.
     */
    public FactParser() {

        super();

        this.pattern = Pattern.compile(FACT_PATTERN_STRING);
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

        Matcher matcher = pattern.matcher(line);

        if (!matcher.matches()) {

            String message = String.format("The specified string (\"%s\") contains no fact!", line);
            throw new ParsingException(message);
        }

        String functionSymbol = matcher.group(FUNCTION_SYMBOL_GROUP_NAME);
        String constantSymbol = matcher.group(CONSTANT_SYMBOL_GROUP_NAME);
        String booleanString = matcher.group(BOOLEAN_GROUP_NAME);
        boolean value = Boolean.parseBoolean(booleanString);

        Fact fact = new Fact(functionSymbol, constantSymbol, value);

        return fact;
    }

}
