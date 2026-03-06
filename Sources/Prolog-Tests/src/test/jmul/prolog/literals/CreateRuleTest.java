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

package test.jmul.prolog.literals;


import java.util.ArrayList;
import java.util.List;

import jmul.prolog.literals.Operation;
import jmul.prolog.literals.Operations;
import jmul.prolog.literals.Rule;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suite tests creating rules.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class CreateRuleTest {

    /**
     * Tests creating a rule with one reference.
     */
    @Test
    public void testCreateRuleWithOneReference() {

        String functionSymbol = "is_animal";
        Operation operation = Operations.DISJUNCTION;
        String referenceSymbol = "is_dog";

        List<String> referenceSymbols = new ArrayList<>();
        referenceSymbols.add(referenceSymbol);

        Rule rule = new Rule(functionSymbol, operation, referenceSymbol);

        assertEquals("#function symbol", functionSymbol, rule.functionSymbol);
        assertEquals("#function symbol", functionSymbol, rule.functionSymbol());
        assertEquals("#operation", operation, rule.operation);
        assertEquals("#reference symbols", referenceSymbols, rule.referenceSymbols);
        assertEquals("#string", "is_animal(X) :- is_dog(X).", rule.toString());
    }

    /**
     * Tests creating a rule with two references.
     */
    @Test
    public void testCreateRuleWithTwoReferences() {

        String functionSymbol = "is_animal";
        Operation operation = Operations.DISJUNCTION;
        String referenceSymbol1 = "is_dog";
        String referenceSymbol2 = "is_cat";

        List<String> referenceSymbols = new ArrayList<>();
        referenceSymbols.add(referenceSymbol1);
        referenceSymbols.add(referenceSymbol2);

        Rule rule = new Rule(functionSymbol, operation, referenceSymbol1, referenceSymbol2);

        assertEquals("#function symbol", functionSymbol, rule.functionSymbol);
        assertEquals("#function symbol", functionSymbol, rule.functionSymbol());
        assertEquals("#operation", operation, rule.operation);
        assertEquals("#reference symbols", referenceSymbols, rule.referenceSymbols);
        assertEquals("#string", "is_animal(X) :- is_dog(X); is_cat(X).", rule.toString());
    }

    /**
     * Tests creating a rule with two references.
     */
    @Test
    public void testCreateRuleWithTwoReferences_Variant2() {

        String functionSymbol = "is_animal";
        Operation operation = Operations.CONJUNCTION;
        String referenceSymbol1 = "is_dog";
        String referenceSymbol2 = "is_cat";

        List<String> referenceSymbols = new ArrayList<>();
        referenceSymbols.add(referenceSymbol1);
        referenceSymbols.add(referenceSymbol2);

        Rule rule = new Rule(functionSymbol, operation, referenceSymbol1, referenceSymbol2);

        assertEquals("#function symbol", functionSymbol, rule.functionSymbol);
        assertEquals("#function symbol", functionSymbol, rule.functionSymbol());
        assertEquals("#operation", operation, rule.operation);
        assertEquals("#reference symbols", referenceSymbols, rule.referenceSymbols);
        assertEquals("#string", "is_animal(X) :- is_dog(X), is_cat(X).", rule.toString());
    }

    /**
     * Tests creating a rule with invalid parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateRuleWithNullFunctionSymbol() {

        String functionSymbol = null;
        Operation operation = Operations.DISJUNCTION;
        String referenceSymbol = "is_dog";

        List<String> referenceSymbols = new ArrayList<>();
        referenceSymbols.add(referenceSymbol);

        new Rule(functionSymbol, operation, referenceSymbol);
    }

    /**
     * Tests creating a rule with invalid parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateRuleWithEmptyFunctionSymbol() {

        String functionSymbol = " ";
        Operation operation = Operations.DISJUNCTION;
        String referenceSymbol = "is_dog";

        List<String> referenceSymbols = new ArrayList<>();
        referenceSymbols.add(referenceSymbol);

        new Rule(functionSymbol, operation, referenceSymbol);
    }

    /**
     * Tests creating a rule with invalid parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateRuleWithNullOperation() {

        String functionSymbol = "is_animal";
        Operation operation = null;
        String referenceSymbol = "is_dog";

        List<String> referenceSymbols = new ArrayList<>();
        referenceSymbols.add(referenceSymbol);

        new Rule(functionSymbol, operation, referenceSymbol);
    }

    /**
     * Tests creating a rule with invalid parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateRuleWithNullReferences() {

        String functionSymbol = "is_animal";
        Operation operation = Operations.DISJUNCTION;
        String[] referenceSymbols = null;

        new Rule(functionSymbol, operation, referenceSymbols);
    }

}
