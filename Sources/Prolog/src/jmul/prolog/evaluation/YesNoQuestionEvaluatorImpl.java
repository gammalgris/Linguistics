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

package jmul.prolog.evaluation;


import java.util.List;
import java.util.Set;

import jmul.prolog.LiteralRepository;
import jmul.prolog.literals.Fact;
import jmul.prolog.literals.Literal;
import jmul.prolog.literals.Operations;
import jmul.prolog.literals.Rule;
import jmul.prolog.questions.YesNoQuestion;


/**
 * An implementation of an evaluator of yes-no questions.
 *
 * @author Kristian Kutin
 */
public class YesNoQuestionEvaluatorImpl implements YesNoQuestionEvaluator {

    /**
     * A reference to a literal repository.
     */
    private final LiteralRepository literalRepository;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param literalRepository
     *        a reference to a literal repository
     */
    public YesNoQuestionEvaluatorImpl(LiteralRepository literalRepository) {

        super();

        if (literalRepository == null) {

            throw new IllegalArgumentException("No literal repository (null) was specified!");
        }

        this.literalRepository = literalRepository;
    }

    /**
     * Evaluates the specified question. A question consists of a function symbol (i.e. for looking up a fact or rule)
     * and a constant symbol (i.e. to check facts).
     *
     * @param question
     *        a question
     *
     * @return <code>true</code> or <code>false</code> depending on the evaluation of the question
     */
    @Override
    public boolean evaluate(YesNoQuestion question) {

        if (question == null) {

            throw new IllegalArgumentException("No question (null) was specified!");
        }

        Set<Literal> literals = literalRepository.lookUp(question.functionSymbol);
        boolean result = evaluateLiterals(question, literals);

        return result;
    }

    /**
     * Evaluates all literals in context of the specified question.
     *
     * @param question
     *        a yes-no question
     * @param literals
     *        a set of literals
     *
     * @return <code>true</code> or <code>false</code> depending on the evaluation of the question
     */
    private boolean evaluateLiterals(YesNoQuestion question, Set<Literal> literals) {

        boolean result = false;

        for (Literal literal : literals) {

            EvaluationResult subresult = evaluateLiteral(question, literal);
            if (subresult.isApplicable) {

                result = subresult.result || result;
            }
        }

        return result;
    }

    /**
     *  Evaluates the literal in context of the specified question.
     *
     * @param question
     *        a yes-no question
     * @param literal
     *        a literal
     *
     * @return <code>true</code> or <code>false</code> depending on the evaluation of the question
     */
    private EvaluationResult evaluateLiteral(YesNoQuestion question, Literal literal) {

        if (literal instanceof Fact) {

            Fact fact = (Fact) literal;
            return evaluateFact(question, fact);

        } else if (literal instanceof Rule) {

            Rule rule = (Rule) literal;
            return evaluateRule(question, rule);

        } else {

            String message = String.format("Unknown literal (%s)!", literal);
            throw new UnsupportedOperationException(message);
        }
    }

    /**
     * Takes the question and compares it to the specified fact (i.e. compares the constant symbol and returns
     * the function result).
     *
     * @param question
     *        a yes-no question
     * @param fact
     *        a fact
     *
     * @return <code>true</code> or <code>false</code> depending on whether the question matches the fact and
     *         depending on the function result
     */
    private EvaluationResult evaluateFact(YesNoQuestion question, Fact fact) {

        if (fact.constantSymbol.equals(question.constantSymbol)) {

            return new EvaluationResult(fact.functionResult, true);
        }

        return new EvaluationResult(false, false);
    }

    /**
     * Takes the question and applies it to the specified rule (i.e. compares the function symbol and then evaluates
     * the rule and looks up the remaining function symbols).
     *
     * @param question
     *        a yes-no question
     * @param rule
     *        a rule
     *
     * @return <code>true</code> or <code>false</code> depending on evaluation the rule
     */
    private EvaluationResult evaluateRule(YesNoQuestion question, Rule rule) {

        List<String> referenceSymbols = rule.referenceSymbols;

        boolean result;
        if (Operations.CONJUNCTION.equals(rule.operation)) {

            result = true;

        } else if (Operations.DISJUNCTION.equals(rule.operation)) {

            result = false;

        } else {

            String message = String.format("The specified operation (%s) is not supported!", rule.operation);
            throw new UnsupportedOperationException(message);
        }

        for (String referenceSymbol : referenceSymbols) {

            Set<Literal> literals = literalRepository.lookUp(referenceSymbol);

            for (Literal literal : literals) {

                EvaluationResult subresult = evaluateLiteral(question, literal);

                if (subresult.isApplicable) {

                    if (Operations.CONJUNCTION.equals(rule.operation)) {

                        result = subresult.result && result;

                        if (result == false) {

                            break;
                        }

                    } else if (Operations.DISJUNCTION.equals(rule.operation)) {

                        result = subresult.result || result;

                        if (result == true) {

                            break;
                        }
                    }
                }
            }
        }

        return new EvaluationResult(result, true);
    }

}


/**
 * This class contains an evaluation result.
 *
 * @authorKristian Kutin
 */
final class EvaluationResult {

    /**
     * An evaluation result.
     */
    public final boolean result;

    /**
     * Indicates if a literal was applicable.
     */
    public final boolean isApplicable;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param result
     *        an evaluation result
     * @param isApplicable
     *        indicates if a literal was applicable
     */
    public EvaluationResult(boolean result, boolean isApplicable) {

        super();

        this.result = result;
        this.isApplicable = isApplicable;
    }

    /**
     * Returns a string representation for this result.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        if (!isApplicable) {

            return "not applicable";

        } else {

            return String.format("%s", result);
        }
    }

}
