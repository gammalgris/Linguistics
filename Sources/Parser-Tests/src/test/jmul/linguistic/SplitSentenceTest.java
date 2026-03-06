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

package test.jmul.linguistic;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import jmul.linguistic.splitter.SentenceToWordSplitterImpl;
import jmul.linguistic.splitter.TextSplitter;

import jmul.test.classification.UnitTest;

import org.junit.Test;


/**
 * This test suite tests splitting sentences into words.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class SplitSentenceTest {

    /**
     * Tests splitting a sentence without line breaks into words.
     */
    @Test
    public void testSplitSentence() {

        String sentence = "Archive formats are used for backups, mobility, and archiving.";

        List<String> words = new ArrayList<>();

        words.add("Archive");
        words.add("formats");
        words.add("are");
        words.add("used");
        words.add("for");
        words.add("backups");
        words.add(",");
        words.add("mobility");
        words.add(",");
        words.add("and");
        words.add("archiving");
        words.add(".");

        TextSplitter splitter = new SentenceToWordSplitterImpl();

        List<String> actualWords = splitter.split(sentence);

        assertEquals("#words", words.size(), actualWords.size());
        assertEquals("#result", words, actualWords);
    }

    /**
     * Tests splitting a sentence without line breaks into words.
     */
    @Test
    public void testSplitSentenceWithoutPunctuationMarks() {

        String sentence = "Archive formats are used for backups, mobility, and archiving";

        List<String> words = new ArrayList<>();

        words.add("Archive");
        words.add("formats");
        words.add("are");
        words.add("used");
        words.add("for");
        words.add("backups");
        words.add(",");
        words.add("mobility");
        words.add(",");
        words.add("and");
        words.add("archiving");

        TextSplitter splitter = new SentenceToWordSplitterImpl();

        List<String> actualWords = splitter.split(sentence);

        assertEquals("#words", words.size(), actualWords.size());
        assertEquals("#result", words, actualWords);
    }

    // test ""
    // test "Archive formats are used for backups, mobility, and archiving"

}
