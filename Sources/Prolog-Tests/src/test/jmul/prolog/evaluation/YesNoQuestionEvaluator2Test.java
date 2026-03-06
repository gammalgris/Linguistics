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

package test.jmul.prolog.evaluation;


import jmul.prolog.LiteralRepository;
import jmul.prolog.LiteralRepositoryImpl;
import jmul.prolog.evaluation.YesNoQuestionEvaluator;
import jmul.prolog.evaluation.YesNoQuestionEvaluatorImpl;
import jmul.prolog.questions.YesNoQuestion;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


/**
 * This test suite tests evaluating yes-no questions in the context of a specific literal repository (i.e.
 * a repository with relation rules).
 *
 * @author Kristian Kutin
 */
@UnitTest
public class YesNoQuestionEvaluator2Test {

    /**
     * A file path to a set of test data.
     */
    private static final String TESTDATA_PATH;

    /*
     * The static initializer.
     */
    static {

        TESTDATA_PATH = "./testdata/example-relations.pl";
    }

    /**
     * A literal repository containing various literals.
     */
    private LiteralRepository literalRepository;

    /**
     * An evaluator for yes-no questions.
     */
    private YesNoQuestionEvaluator questionEvaluator;

    /**
     * Prepares everything before a test.
     */
    @Before
    public void setUp() {

        literalRepository = new LiteralRepositoryImpl();
        literalRepository.importLiterals(TESTDATA_PATH);

        questionEvaluator = new YesNoQuestionEvaluatorImpl(literalRepository);
    }

    /**
     * Cleans up after a test.
     */
    @After
    public void tearDown() {

        literalRepository = null;
        questionEvaluator = null;
    }

    /**
     * Evaluates a simple yes-no question which is answered by looking up rules.
     */
    @Test
    public void testYesNoQuestion() {

        YesNoQuestion question = new YesNoQuestion("is_mother", "Mary");

        boolean expectedAnswer = false;
        boolean actualAnswer = questionEvaluator.evaluate(question);

        assertEquals("#answer", expectedAnswer, actualAnswer);
    }

    /**
     * Evaluates a simple yes-no question which is answered by looking up rules.
     */
    @Test
    public void testYesNoQuestionVariant2() {

        YesNoQuestion question = new YesNoQuestion("is_mother", "John");

        boolean expectedAnswer = false;
        boolean actualAnswer = questionEvaluator.evaluate(question);

        assertEquals("#answer", expectedAnswer, actualAnswer);
    }

    /**
     * Evaluates a simple yes-no question which is answered by looking up rules.
     */
    @Test
    public void testYesNoQuestion3() {

        YesNoQuestion question = new YesNoQuestion("is_father", "Mary");

        boolean expectedAnswer = false;
        boolean actualAnswer = questionEvaluator.evaluate(question);

        assertEquals("#answer", expectedAnswer, actualAnswer);
    }

    /**
     * Evaluates a simple yes-no question which is answered by looking up rules.
     */
    @Test
    public void testYesNoQuestionVariant4() {

        YesNoQuestion question = new YesNoQuestion("is_father", "John");

        boolean expectedAnswer = true;
        boolean actualAnswer = questionEvaluator.evaluate(question);

        assertEquals("#answer", expectedAnswer, actualAnswer);
    }

}
