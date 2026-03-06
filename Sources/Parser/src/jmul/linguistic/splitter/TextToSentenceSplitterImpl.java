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
 * An implementation of an algorithm for splitting text into sentences.
 *
 * @author Kristian Kutin
 */
public class TextToSentenceSplitterImpl implements TextSplitter {

    /**
     * A constant representing a 'carriage return' character.
     */
    private static final char CR;

    /**
     * A constant representing a 'line feed' character.
     */
    private static final char LF;

    /**
     * A constant representing a 'space' character.
     */
    private static final char SPACE;

    /**
     * The string contains various characters that end a sentence.
     */
    private static final String SENTENCE_ENDINGS;

    /*
     * The static initializer.
     */
    static {

        CR = '\r';
        LF = '\n';
        SPACE = ' ';
        SENTENCE_ENDINGS = ".?!:";
    }

    /**
     * The default constructor.
     */
    public TextToSentenceSplitterImpl() {

        super();
    }

    /**
     * Splits the specified text into sentences.
     *
     * @param text
     *        a text
     *
     * @return a list of sentences
     */
    @Override
    public List<String> split(CharSequence text) {

        CharSequence normalizedText = normalizeNewLines(text);

        List<String> result = new ArrayList<>();

        int length = normalizedText.length();
        int start = 0;
        int end = 0;

        for (int index = 0; index < length; index++) {

            char c = normalizedText.charAt(index);

            if (isSentenceEnding(c)) {

                end = index + 1;

                CharSequence subSequence = normalizedText.subSequence(start, end);
                result.add(subSequence.toString().trim());

                start = end;
            }
        }

        return result;
    }

    /**
     * Checks if the specified character represents a sentence ending.
     *
     * @param c
     *        a character
     *
     * @return <code>true</code> if the specified character represents a sentence ending, else <code>false</code>
     */
    private boolean isSentenceEnding(char c) {

        int length = SENTENCE_ENDINGS.length();

        for (int index = 0; index < length; index++) {

            char d = SENTENCE_ENDINGS.charAt(index);

            if (c == d) {

                return true;
            }
        }

        return false;
    }

    /**
     * Normalizes the specified text (i.e. replaces the new lines).
     *
     * @param text
     *        a text
     *
     * @return a normalized text
     */
    private CharSequence normalizeNewLines(CharSequence text) {

        StringBuilder buffer = new StringBuilder();

        int length = text.length();

        char previous = 0;
        char next = 0;

        for (int index = 0; index < length; index++) {

            char current = text.charAt(index);

            int previousIndex = index - 1;
            if (previousIndex >= 0) {

                previous = text.charAt(previousIndex);
            }

            int nextIndex = index + 1;
            if (nextIndex < length) {

                next = text.charAt(nextIndex);
            }

            if ((current == CR) && (previous == LF)) {

                continue;

            } else if ((current == CR) && (next == LF)) {

                buffer.append(SPACE);
                continue;

            } else if ((current == SPACE) && (previous == LF)) {

                continue;

            } else if ((current == SPACE) && (previous == SPACE)) {

                continue;

            } else if ((current == LF) && (previous != CR)) {

                buffer.append(SPACE);
                continue;

            } else if (current == LF) {

                continue;

            } else {

                buffer.append(current);
            }
        }

        return buffer;
    }

}
