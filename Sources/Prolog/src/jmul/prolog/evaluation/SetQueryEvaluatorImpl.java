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


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jmul.math.collections.Set;

import jmul.prolog.LiteralRepository;
import static jmul.prolog.evaluation.CollectionHelper.emptySet;
import static jmul.prolog.evaluation.CollectionHelper.newSet;
import jmul.prolog.literals.Fact;
import jmul.prolog.literals.Literal;
import jmul.prolog.literals.Operations;
import jmul.prolog.literals.Rule;
import jmul.prolog.questions.SetQuery;


/**
 * An implementation of an evaluator of set queries.<br>
 * <br>
 * <i>Note:<br>
 * Maybe this article will help implement a suitable algorithm, see
 * <a href="https://en.wikipedia.org/wiki/Backtracking">Backtracking</a>.</i>
 *
 * @author Kristian Kutin
 */
public class SetQueryEvaluatorImpl implements SetQueryEvaluator {

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
    public SetQueryEvaluatorImpl(LiteralRepository literalRepository) {

        super();

        if (literalRepository == null) {

            throw new IllegalArgumentException("No literal repository (null) was specified!");
        }

        this.literalRepository = literalRepository;
    }

    /**
     * Evaluates the specified question and a set of constant symbols that match the question. A question consists
     * of a function symbol (i.e. for looking up a fact or rule).
     *
     * @param question
     *        a question
     *
     * @return a set of constant symbols
     */
    @Override
    public Set<String> evaluate(SetQuery question) {

        if (question == null) {

            throw new IllegalArgumentException("No question (null) was specified!");
        }

        java.util.Set<Literal> literals = literalRepository.lookUp(question.functionSymbol);
        Set<String> result = evaluateLiterals(question, literals);

        return result;
    }

    /**
     * Evaluates all literals in context of the specified question.
     *
     * @param question
     *        a set query
     * @param literals
     *        a set of literals
     *
     * @return a set of constant symbols
     */
    private Set<String> evaluateLiterals(SetQuery question, java.util.Set<Literal> literals) {

        Set<String> result = emptySet();

        for (Literal literal : literals) {

            Set<String> subresult = evaluateLiteral(question, literal);
            result = result.union(subresult);
        }

        return result;
    }

    /**
     * Evaluates the literal in context of the specified question.
     *
     * @param question
     *        a set query
     * @param literal
     *        a literal
     *
     * @return a set of constant symbols
     */
    private Set<String> evaluateLiteral(SetQuery question, Literal literal) {

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
     * Takes the question and compares it to the specified fact (i.e. compares the function symbol and
     * takes the constant symbol and returns a set with the constant symbol).
     *
     * @param question
     *        a set query
     * @param fact
     *        a fact
     *
     * @returna set of constant symbols
     */
    private Set<String> evaluateFact(SetQuery question, Fact fact) {

        if (fact.functionResult) {

            return newSet(fact.constantSymbol);
        }

        return emptySet();
    }

    /**
     * Takes the question and applies it to the specified rule (i.e. compares the function symbol and then evaluates
     * the rule and looks up the remaining function symbols).
     *
     * @param question
     *        a set query
     * @param rule
     *        a rule
     *
     * @return set of constant symbols
     */
    private Set<String> evaluateRule(SetQuery question, Rule rule) {

        if (!Operations.CONJUNCTION.equals(rule.operation) && !Operations.DISJUNCTION.equals(rule.operation)) {

            String message = String.format("The specified operation (%s) is not supported!", rule.operation);
            throw new UnsupportedOperationException(message);
        }

        List<String> referenceSymbols = rule.referenceSymbols;

        int index = 0;
        Map<Integer, Set<String>> results = new HashMap<>();

        for (String referenceSymbol : referenceSymbols) {

            Set<String> result = emptySet();
            java.util.Set<Literal> literals = literalRepository.lookUp(referenceSymbol);

            for (Literal literal : literals) {

                Set<String> subresult = evaluateLiteral(question, literal);
                result = result.union(subresult);
            }

            results.put(index, result);
            index++;
        }

        Set<String> result = results.get(0);

        if (Operations.CONJUNCTION.equals(rule.operation)) {

            for (index = 1; index < results.size(); index++) {

                Set<String> subresult = results.get(index);
                result = result.intersection(subresult);
            }

        } else if (Operations.DISJUNCTION.equals(rule.operation)) {

            for (index = 1; index < results.size(); index++) {

                Set<String> subresult = results.get(index);
                result = result.union(subresult);
            }
        }

        return result;
    }

}
