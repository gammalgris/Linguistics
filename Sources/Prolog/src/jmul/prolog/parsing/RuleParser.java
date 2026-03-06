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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jmul.prolog.literals.Literal;
import jmul.prolog.literals.Operation;
import jmul.prolog.literals.Operations;
import jmul.prolog.literals.Rule;


/**
 * An implementation of a parser for literals (i.e. rules).
 *
 * @author Kristian Kutin
 */
public class RuleParser implements Parser {

    /**
     * The name of a captchuring group.
     */
    private static final String HEAD_GROUP_NAME;

    /**
     * The name of a captchuring group.
     */
    private static final String FUNCTION_SYMBOL_GROUP_NAME;

    /**
     * The name of a captchuring group.
     */
    private static final String VARIABLE_SYMBOL_GROUP_NAME;

    /**
     * The name of a captchuring group.
     */
    private static final String TAIL_GROUP_NAME;

    /**
     * A regular expression matching rules.
     */
    private static final String RULE_PATTERN_STRING;

    /**
     * A separator character sequence that separates the two parts of a rule.
     */
    private static final String SEPARATOR;

    /**
     * A character sequence representing the end of a string.
     */
    private static final String END;

    /**
     * A semicolon character.
     */
    private static final String SEMICOLON;

    /**
     * A comma character.
     */
    private static final String COMMA;

    /*
     * The static initializer.
     */
    static {

        HEAD_GROUP_NAME = "head";
        FUNCTION_SYMBOL_GROUP_NAME = "functionSymbol";
        VARIABLE_SYMBOL_GROUP_NAME = "variableSymbol";
        TAIL_GROUP_NAME = "tail";

        RULE_PATTERN_STRING =
            String.format("^(?<%s>(?<%s>[^(]+)([(](?<%s>[^)]+)[)]))(?<%s>.+)$", HEAD_GROUP_NAME,
                          FUNCTION_SYMBOL_GROUP_NAME, VARIABLE_SYMBOL_GROUP_NAME, TAIL_GROUP_NAME);

        SEPARATOR = " :- ";
        END = ".";

        SEMICOLON = ";";
        COMMA = ",";
    }

    /**
     * A pattern matcher.
     */
    private final Pattern pattern;

    /**
     * The default constructor.
     */
    public RuleParser() {

        super();

        this.pattern = Pattern.compile(RULE_PATTERN_STRING);
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

            String message = String.format("The specified string (\"%s\") contains no rule!", line);
            throw new ParsingException(message);
        }

        String head = matcher.group(HEAD_GROUP_NAME);
        String functionSymbol = matcher.group(FUNCTION_SYMBOL_GROUP_NAME);
        String variableSymbol = matcher.group(VARIABLE_SYMBOL_GROUP_NAME);
        String tail = matcher.group(TAIL_GROUP_NAME);

        String remainder = tail;
        if (remainder.startsWith(SEPARATOR)) {

            remainder = remainder.substring(SEPARATOR.length());
        }

        String operatorSymbol = null;
        List<String> functionSymbols = new ArrayList<>();
        while (true) {

            matcher = pattern.matcher(remainder);
            if (!matcher.matches()) {

                String message = String.format("The specified string (\"%s\") contains no rule!", line);
                throw new ParsingException(message);
            }

            head = matcher.group(HEAD_GROUP_NAME);
            String nextFunctionSymbol = matcher.group(FUNCTION_SYMBOL_GROUP_NAME);
            String nextVariableSymbol = matcher.group(VARIABLE_SYMBOL_GROUP_NAME);
            tail = matcher.group(TAIL_GROUP_NAME);

            if (!variableSymbol.equals(nextVariableSymbol)) {

                String message = String.format("The specified string (\"%s\") contains no rule!", line);
                throw new ParsingException(message);
            }

            functionSymbols.add(nextFunctionSymbol);

            if (tail.equals(END)) {

                break;
            }

            if (tail.startsWith(COMMA)) {

                if (operatorSymbol == null ) {

                    operatorSymbol = COMMA;
                }
                remainder = tail.substring(1);

            } else if (tail.startsWith(SEMICOLON)) {

                if (operatorSymbol == null) {
                    
                    operatorSymbol = SEMICOLON;
                }
                remainder = tail.substring(1);

            } else {

                String message = String.format("The specified string (\"%s\") contains no rule!", line);
                throw new ParsingException(message);
            }

            remainder = remainder.trim();
        }

        if (operatorSymbol == null) {

            operatorSymbol = COMMA;
        }

        String[] functionSymbolArray = { };
        functionSymbolArray = functionSymbols.toArray(functionSymbolArray);

        Operation operation = Operations.parseString(operatorSymbol);

        Rule rule = new Rule(functionSymbol, operation, functionSymbolArray);

        return rule;
    }

}
