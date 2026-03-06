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

import jmul.prolog.literals.Fact;
import jmul.prolog.literals.Literal;
import jmul.prolog.literals.Operation;
import jmul.prolog.literals.Operations;
import jmul.prolog.literals.Rule;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;


/**
 * This test suite tests the equality of literals.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class LiteralEqualityTest {

    /**
     * Tests creating and comparing facts.
     */
    @Test
    public void createAndCompareSameFacts() {

        String functionSymbol = "is_animal";
        String constantSymbol = "dog";
        boolean result = true;

        Literal fact1 = new Fact(functionSymbol, constantSymbol, result);


        String functionSymbol2 = "is_animal";
        String constantSymbol2 = "cat";
        boolean result2 = true;

        Literal fact2 = new Fact(functionSymbol2, constantSymbol2, result2);


        assertEquals("#equals", false, fact1.equals(fact2));
        assertEquals("#equals", false, fact2.equals(fact1));
        assertNotEquals("#hash", fact1.hashCode(), fact2.hashCode());
    }

    /**
     * Tests creating and comparing facts.
     */
    @Test
    public void createAndCompareDifferentFacts() {

        String functionSymbol = "is_animal";
        String constantSymbol = "cat";
        boolean result = true;

        Literal fact1 = new Fact(functionSymbol, constantSymbol, result);


        String functionSymbol2 = "is_animal";
        String constantSymbol2 = "cat";
        boolean result2 = true;

        Literal fact2 = new Fact(functionSymbol2, constantSymbol2, result2);


        assertEquals("#equals", true, fact1.equals(fact2));
        assertEquals("#equals", true, fact2.equals(fact1));
        assertEquals("#hash", fact1.hashCode(), fact2.hashCode());
    }

    /**
     * Tests creating and comparing rules.
     */
    @Test
    public void createAndCompareDifferentRules() {

        String functionSymbol1 = "is_animal";
        Operation operation1 = Operations.DISJUNCTION;
        String referenceSymbol1 = "is_dog";

        List<String> referenceSymbols1 = new ArrayList<>();
        referenceSymbols1.add(referenceSymbol1);

        Literal rule1 = new Rule(functionSymbol1, operation1, referenceSymbol1);


        String functionSymbol2 = "is_pet";
        Operation operation2 = Operations.DISJUNCTION;
        String referenceSymbol2 = "is_fish";

        List<String> referenceSymbols2 = new ArrayList<>();
        referenceSymbols2.add(referenceSymbol2);

        Literal rule2 = new Rule(functionSymbol2, operation2, referenceSymbol2);


        assertEquals("#equals", false, rule1.equals(rule2));
        assertEquals("#equals", false, rule2.equals(rule1));
        assertNotEquals("#hash", rule1.hashCode(), rule2.hashCode());
    }

    /**
     * Tests creating and comparing rules.
     */
    @Test
    public void createAndCompareSameRules() {

        String functionSymbol1 = "is_animal";
        Operation operation1 = Operations.DISJUNCTION;
        String referenceSymbol1 = "is_dog";

        List<String> referenceSymbols1 = new ArrayList<>();
        referenceSymbols1.add(referenceSymbol1);

        Literal rule = new Rule(functionSymbol1, operation1, referenceSymbol1);


        String functionSymbol2 = "is_animal";
        Operation operation2 = Operations.DISJUNCTION;
        String referenceSymbol2 = "is_dog";

        List<String> referenceSymbols2 = new ArrayList<>();
        referenceSymbols2.add(referenceSymbol2);

        Literal rule2 = new Rule(functionSymbol2, operation2, referenceSymbol2);


        assertEquals("#equals", true, rule.equals(rule2));
        assertEquals("#equals", true, rule2.equals(rule));
        assertEquals("#hash", rule.hashCode(), rule2.hashCode());
    }

    /**
     * Tests creating and comparing afact and a rule.
     */
    @Test
    public void createAndCompareFactAndRule() {

        String functionSymbol1 = "is_animal";
        String constantSymbol1 = "dog";
        boolean result1 = true;

        Literal fact1 = new Fact(functionSymbol1, constantSymbol1, result1);


        String functionSymbol2 = "is_animal";
        Operation operation2 = Operations.DISJUNCTION;
        String referenceSymbol2 = "is_dog";

        List<String> referenceSymbols2 = new ArrayList<>();
        referenceSymbols2.add(referenceSymbol2);

        Literal rule2 = new Rule(functionSymbol2, operation2, referenceSymbol2);


        assertEquals("#equals", false, fact1.equals(rule2));
        assertEquals("#equals", false, rule2.equals(fact1));
        assertNotEquals("#hash", fact1.hashCode(), rule2.hashCode());
    }

}
