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

import jmul.linguistic.splitter.TextSplitter;
import jmul.linguistic.splitter.TextToSentenceSplitterImpl;

import jmul.string.Constants;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suite tests splitting text into sentences.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class SplitTextTest {

    /**
     * Tests splitting a text without line breaks into sentences.
     */
    @Test
    public void testSplitText() {

        StringBuilder text = new StringBuilder();

        text.append("Archive formats are used for backups, mobility, and archiving. Many archive formats losslessly compress the data to ");
        text.append("consume less storage space and result in faster transfer times as the same data is represented by fewer ");
        text.append("bytes. Another benefit is that files are combined into one archive file which has less overhead for ");
        text.append("managing or transferring. Many compression algorithms are available to losslessly compress archived ");
        text.append("data; some algorithms are designed to work better (smaller archive or faster compression) with some ");
        text.append("data types. Archive formats are used by Unix-like and Windows operating systems to package software ");
        text.append("for easier distributing and installing than binary executables.");

        List<String> sentences = new ArrayList<>();

        sentences.add("Archive formats are used for backups, mobility, and archiving.");
        sentences.add("Many archive formats losslessly compress the data to consume less storage space and result in faster transfer times as the same data is represented by fewer bytes.");
        sentences.add("Another benefit is that files are combined into one archive file which has less overhead for managing or transferring.");
        sentences.add("Many compression algorithms are available to losslessly compress archived data; some algorithms are designed to work better (smaller archive or faster compression) with some data types.");
        sentences.add("Archive formats are used by Unix-like and Windows operating systems to package software for easier distributing and installing than binary executables.");

        TextSplitter splitter = new TextToSentenceSplitterImpl();

        List<String> actualSentences = splitter.split(text);

        assertEquals("#sentences", sentences.size(), actualSentences.size());
        assertEquals("#result", sentences, actualSentences);
    }

    /**
     * Tests splitting a text with windows style line breaks into sentences.
     */
    @Test
    public void testSplitMultilineText_Windows() {

        StringBuilder text = new StringBuilder();

        text.append("Archive formats are used for backups, mobility, and archiving. Many archive formats losslessly compress the data to");
        text.append(Constants.NEW_LINE_WINDOWS);
        text.append("consume less storage space and result in faster transfer times as the same data is represented by fewer");
        text.append(Constants.NEW_LINE_WINDOWS);
        text.append("bytes. Another benefit is that files are combined into one archive file which has less overhead for");
        text.append(Constants.NEW_LINE_WINDOWS);
        text.append("managing or transferring. Many compression algorithms are available to losslessly compress archived");
        text.append(Constants.NEW_LINE_WINDOWS);
        text.append("data; some algorithms are designed to work better (smaller archive or faster compression) with some");
        text.append(Constants.NEW_LINE_WINDOWS);
        text.append("data types. Archive formats are used by Unix-like and Windows operating systems to package software");
        text.append(Constants.NEW_LINE_WINDOWS);
        text.append("for easier distributing and installing than binary executables.");
        text.append(Constants.NEW_LINE_WINDOWS);

        List<String> sentences = new ArrayList<>();

        sentences.add("Archive formats are used for backups, mobility, and archiving.");
        sentences.add("Many archive formats losslessly compress the data to consume less storage space and result in faster transfer times as the same data is represented by fewer bytes.");
        sentences.add("Another benefit is that files are combined into one archive file which has less overhead for managing or transferring.");
        sentences.add("Many compression algorithms are available to losslessly compress archived data; some algorithms are designed to work better (smaller archive or faster compression) with some data types.");
        sentences.add("Archive formats are used by Unix-like and Windows operating systems to package software for easier distributing and installing than binary executables.");

        TextSplitter splitter = new TextToSentenceSplitterImpl();

        List<String> actualSentences = splitter.split(text);

        assertEquals("#sentences", sentences.size(), actualSentences.size());
        assertEquals("#result", sentences, actualSentences);
    }

    /**
     * Tests splitting a text with unix style line breaks into sentences.
     */
    @Test
    public void testSplitMultilineText_Unix() {

        StringBuilder text = new StringBuilder();

        text.append("Archive formats are used for backups, mobility, and archiving. Many archive formats losslessly compress the data to");
        text.append(Constants.NEW_LINE_UNIX);
        text.append("consume less storage space and result in faster transfer times as the same data is represented by fewer");
        text.append(Constants.NEW_LINE_UNIX);
        text.append("bytes. Another benefit is that files are combined into one archive file which has less overhead for");
        text.append(Constants.NEW_LINE_UNIX);
        text.append("managing or transferring. Many compression algorithms are available to losslessly compress archived");
        text.append(Constants.NEW_LINE_UNIX);
        text.append("data; some algorithms are designed to work better (smaller archive or faster compression) with some");
        text.append(Constants.NEW_LINE_UNIX);
        text.append("data types. Archive formats are used by Unix-like and Windows operating systems to package software");
        text.append(Constants.NEW_LINE_UNIX);
        text.append("for easier distributing and installing than binary executables.");
        text.append(Constants.NEW_LINE_UNIX);

        List<String> sentences = new ArrayList<>();

        sentences.add("Archive formats are used for backups, mobility, and archiving.");
        sentences.add("Many archive formats losslessly compress the data to consume less storage space and result in faster transfer times as the same data is represented by fewer bytes.");
        sentences.add("Another benefit is that files are combined into one archive file which has less overhead for managing or transferring.");
        sentences.add("Many compression algorithms are available to losslessly compress archived data; some algorithms are designed to work better (smaller archive or faster compression) with some data types.");
        sentences.add("Archive formats are used by Unix-like and Windows operating systems to package software for easier distributing and installing than binary executables.");

        TextSplitter splitter = new TextToSentenceSplitterImpl();

        List<String> actualSentences = splitter.split(text);

        assertEquals("#sentences", sentences.size(), actualSentences.size());
        assertEquals("#result", sentences, actualSentences);
    }

    /**
     * Tests splitting a text with windows style line breaks into sentences.
     */
    @Test
    public void testSplitEnumeration_Windows() {

        StringBuilder text = new StringBuilder();

        text.append("This example uses a list format to enumerate the same points:");
        text.append(Constants.NEW_LINE_WINDOWS);
        text.append("* Enhanced career prospects: Opens up new job opportunities in a global market.");
        text.append(Constants.NEW_LINE_WINDOWS);
        text.append("* Improved cognitive abilities: Boost problem solving skills and memory.");
        text.append(Constants.NEW_LINE_WINDOWS);
        text.append("* Deeper cultural connection: Allows for a more profound understanding of");
        text.append(Constants.NEW_LINE_WINDOWS);
        text.append("  different cultures.");
        text.append(Constants.NEW_LINE_WINDOWS);

        List<String> sentences = new ArrayList<>();

        sentences.add("This example uses a list format to enumerate the same points:");
        sentences.add("* Enhanced career prospects:");
        sentences.add("Opens up new job opportunities in a global market.");
        sentences.add("* Improved cognitive abilities:");
        sentences.add("Boost problem solving skills and memory.");
        sentences.add("* Deeper cultural connection:");
        sentences.add("Allows for a more profound understanding of different cultures.");

        TextSplitter splitter = new TextToSentenceSplitterImpl();

        List<String> actualSentences = splitter.split(text);

        assertEquals("#sentences", sentences.size(), actualSentences.size());
        assertEquals("#result", sentences, actualSentences);
    }

    /**
     * Tests splitting a text with unix style line breaks into sentences.
     */
    @Test
    public void testSplitEnumeration_Unix() {

        StringBuilder text = new StringBuilder();

        text.append("This example uses a list format to enumerate the same points:");
        text.append(Constants.NEW_LINE_UNIX);
        text.append("* Enhanced career prospects: Opens up new job opportunities in a global market.");
        text.append(Constants.NEW_LINE_UNIX);
        text.append("* Improved cognitive abilities: Boost problem solving skills and memory.");
        text.append(Constants.NEW_LINE_UNIX);
        text.append("* Deeper cultural connection: Allows for a more profound understanding of");
        text.append(Constants.NEW_LINE_UNIX);
        text.append("  different cultures.");
        text.append(Constants.NEW_LINE_UNIX);

        List<String> sentences = new ArrayList<>();

        sentences.add("This example uses a list format to enumerate the same points:");
        sentences.add("* Enhanced career prospects:");
        sentences.add("Opens up new job opportunities in a global market.");
        sentences.add("* Improved cognitive abilities:");
        sentences.add("Boost problem solving skills and memory.");
        sentences.add("* Deeper cultural connection:");
        sentences.add("Allows for a more profound understanding of different cultures.");

        TextSplitter splitter = new TextToSentenceSplitterImpl();

        List<String> actualSentences = splitter.split(text);

        assertEquals("#sentences", sentences.size(), actualSentences.size());
        assertEquals("#result", sentences, actualSentences);
    }

}
