/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2025  Kristian Kutin
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

package jmul.linguistic.splitter;


import java.util.ArrayList;
import java.util.List;


/**
 * An implementation of an algorithm for splitting sentences into words.
 *
 * @author Kristian Kutin
 */
public class SentenceToWordSplitterImpl implements TextSplitter {

    /**
     * A constant representing a 'space' character.
     */
    private static final char SPACE;

    /**
     * The string contains various punctuation mark characters.
     */
    private static final String PUNCTUATION_MARKS;

    /*
     * The static initializer.
     */
    static {

        SPACE = ' ';
        PUNCTUATION_MARKS = ".,!?:;";
    }

    /**
     * The default constructor.
     */
    public SentenceToWordSplitterImpl() {

        super();
    }

    /**
     * Splits the specified sentence into words. The punctuation marks are retained as individual word tokens.
     *
     * @param sentence
     *        a sentence
     *
     * @return a list of words (i.e. word tokens)
     */
    @Override
    public List<String> split(CharSequence sentence) {

        List<String> result = new ArrayList<>();

        int length = sentence.length();
        int lastIndex = length - 1;

        int start = 0;
        int end = 0;

        char previous = 0;

        for (int index = 0; index < length; index++) {

            char current = sentence.charAt(index);

            int previousIndex = index - 1;
            if (previousIndex >= 0) {

                previous = sentence.charAt(previousIndex);
            }

            if ((previous == SPACE) && (current != SPACE)) {

                start = index;

            } else if ((previous != SPACE) && (current == SPACE)) {

                end = index;

                CharSequence word = sentence.subSequence(start, end);
                result.add(word.toString());

                start = end;

            } else if (isPunctuationMark(current)) {

                CharSequence word;

                end = index;

                word = sentence.subSequence(start, end);
                result.add(word.toString());

                start = end;

                if (index == lastIndex) {

                    end = index + 1;

                    word = sentence.subSequence(start, end);
                    result.add(word.toString());
                }

            } else if (index == lastIndex) {

                end = index + 1;

                CharSequence word = sentence.subSequence(start, end);
                result.add(word.toString());

                start = end;
            }
        }

        return result;
    }

    /**
     * Checks if the specified character represents a punctuation mark.
     *
     * @param c
     *        a character
     *
     * @return <code>true</code> if the specified character represents a punctuation mark, else <code>false</code>
     */
    private boolean isPunctuationMark(char c) {

        int length = PUNCTUATION_MARKS.length();

        for (int index = 0; index < length; index++) {

            char d = PUNCTUATION_MARKS.charAt(index);

            if (c == d) {

                return true;
            }
        }

        return false;
    }

}
