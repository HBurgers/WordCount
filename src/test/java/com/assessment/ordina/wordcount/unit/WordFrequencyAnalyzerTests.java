package com.assessment.ordina.wordcount.unit;

import com.assessment.ordina.wordcount.models.WordFrequency;
import com.assessment.ordina.wordcount.models.implementations.WordFrequencyImp;
import com.assessment.ordina.wordcount.services.WordFrequencyAnalyzer;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WordFrequencyAnalyzerTests {
    final String DEFAULT_TEXT = "The sun shines over the lake";
    final String MIX_CAPS_TEXT = "The sun shines over tHE lake near THE shore";
    final String MIX_SEPARATORS_TEXT = "The sun\nshines123over@!#$the\tlake";
    final String DEFAULT_TEXT_LEADING_SEPARATORS = " @ \t \nThe sun shines over the lake";
    final String DEFAULT_TEXT_TRAILING_SEPARATORS = "The sun shines over the lake @ \t \n";
    final String DEFAULT_TEXT_RANDOM_SEPARATORS = "     The   sun shines over \tthe lake @ \t \n";

    private final WordFrequencyAnalyzer wordFrequencyAnalyzer;

    @Autowired
    public WordFrequencyAnalyzerTests(WordFrequencyAnalyzer wordFrequencyAnalyzer) {
        this.wordFrequencyAnalyzer = wordFrequencyAnalyzer;
    }


    //calculateHighestFrequencyTest
    @Test
    public void calculateHighestFrequencyTest_defaultCase() {
        Assertions.assertEquals(2, wordFrequencyAnalyzer.calculateHighestFrequency(DEFAULT_TEXT));
    }

    @Test
    public void calculateHighestFrequencyTest_mixCapsCase() {
        Assertions.assertEquals(3, wordFrequencyAnalyzer.calculateHighestFrequency(MIX_CAPS_TEXT));
    }

    @Test
    public void calculateHighestFrequencyTest_mixSeparatorsCase() {
        Assertions.assertEquals(2, wordFrequencyAnalyzer.calculateHighestFrequency(MIX_SEPARATORS_TEXT));
    }

    @Test
    public void calculateHighestFrequencyTest_leadingSeparatorsCase() {
        Assertions.assertEquals(2, wordFrequencyAnalyzer.calculateHighestFrequency(DEFAULT_TEXT_LEADING_SEPARATORS));
    }

    @Test
    public void calculateHighestFrequencyTest_trailingSeparatorsCase() {
        Assertions.assertEquals(2, wordFrequencyAnalyzer.calculateHighestFrequency(DEFAULT_TEXT_TRAILING_SEPARATORS));
    }

    @Test
    public void calculateHighestFrequencyTest_randomSeparatorsCase() {
        Assertions.assertEquals(2, wordFrequencyAnalyzer.calculateHighestFrequency(DEFAULT_TEXT_RANDOM_SEPARATORS));
    }

    @Test
    public void calculateHighestFrequencyTest_nullStringCase() {
        Assertions.assertEquals(0, wordFrequencyAnalyzer.calculateHighestFrequency(null));
    }

    @Test
    public void calculateHighestFrequencyTest_emptyStringCase() {
        Assertions.assertEquals(0, wordFrequencyAnalyzer.calculateHighestFrequency(""));
    }

    //calculateFrequencyForWordTests
    @Test
    public void calculateFrequencyForWordTest_defaultCase() {
        Assertions.assertEquals(2, wordFrequencyAnalyzer.calculateFrequencyForWord(DEFAULT_TEXT, "The"));
        Assertions.assertEquals(2, wordFrequencyAnalyzer.calculateFrequencyForWord(DEFAULT_TEXT, "tHe"));
        Assertions.assertEquals(1, wordFrequencyAnalyzer.calculateFrequencyForWord(DEFAULT_TEXT, "sun"));
        Assertions.assertEquals(1, wordFrequencyAnalyzer.calculateFrequencyForWord(DEFAULT_TEXT, "SUN"));
    }

    @Test
    public void calculateFrequencyForWordTest_mixCapsCase() {
        Assertions.assertEquals(3, wordFrequencyAnalyzer.calculateFrequencyForWord(MIX_CAPS_TEXT, "The"));
        Assertions.assertEquals(3, wordFrequencyAnalyzer.calculateFrequencyForWord(MIX_CAPS_TEXT, "tHe"));
        Assertions.assertEquals(1, wordFrequencyAnalyzer.calculateFrequencyForWord(MIX_CAPS_TEXT, "sun"));
        Assertions.assertEquals(1, wordFrequencyAnalyzer.calculateFrequencyForWord(MIX_CAPS_TEXT, "SUN"));
    }

    @Test
    public void calculateFrequencyForWordTest_mixSeparatorsCase() {
        Assertions.assertEquals(2, wordFrequencyAnalyzer.calculateFrequencyForWord(MIX_SEPARATORS_TEXT, "The"));
        Assertions.assertEquals(2, wordFrequencyAnalyzer.calculateFrequencyForWord(MIX_SEPARATORS_TEXT, "tHe"));
        Assertions.assertEquals(1, wordFrequencyAnalyzer.calculateFrequencyForWord(MIX_SEPARATORS_TEXT, "sun"));
        Assertions.assertEquals(1, wordFrequencyAnalyzer.calculateFrequencyForWord(MIX_SEPARATORS_TEXT, "SUN"));
    }

    @Test
    public void calculateFrequencyForWordTest_leadingSeparatorsCase() {
        Assertions.assertEquals(2, wordFrequencyAnalyzer.calculateFrequencyForWord(DEFAULT_TEXT_LEADING_SEPARATORS, "The"));
        Assertions.assertEquals(2, wordFrequencyAnalyzer.calculateFrequencyForWord(DEFAULT_TEXT_LEADING_SEPARATORS, "tHe"));
        Assertions.assertEquals(1, wordFrequencyAnalyzer.calculateFrequencyForWord(DEFAULT_TEXT_LEADING_SEPARATORS, "sun"));
        Assertions.assertEquals(1, wordFrequencyAnalyzer.calculateFrequencyForWord(DEFAULT_TEXT_LEADING_SEPARATORS, "SUN"));
    }

    @Test
    public void calculateFrequencyForWordTest_trailingSeparatorsCase() {
        Assertions.assertEquals(2, wordFrequencyAnalyzer.calculateFrequencyForWord(DEFAULT_TEXT_TRAILING_SEPARATORS, "The"));
        Assertions.assertEquals(2, wordFrequencyAnalyzer.calculateFrequencyForWord(DEFAULT_TEXT_TRAILING_SEPARATORS, "tHe"));
        Assertions.assertEquals(1, wordFrequencyAnalyzer.calculateFrequencyForWord(DEFAULT_TEXT_TRAILING_SEPARATORS, "sun"));
        Assertions.assertEquals(1, wordFrequencyAnalyzer.calculateFrequencyForWord(DEFAULT_TEXT_TRAILING_SEPARATORS, "SUN"));
    }

    @Test
    public void calculateFrequencyForWordTest_randomSeparatorsCase() {
        Assertions.assertEquals(2, wordFrequencyAnalyzer.calculateFrequencyForWord(DEFAULT_TEXT_RANDOM_SEPARATORS, "The"));
        Assertions.assertEquals(2, wordFrequencyAnalyzer.calculateFrequencyForWord(DEFAULT_TEXT_RANDOM_SEPARATORS, "tHe"));
        Assertions.assertEquals(1, wordFrequencyAnalyzer.calculateFrequencyForWord(DEFAULT_TEXT_RANDOM_SEPARATORS, "sun"));
        Assertions.assertEquals(1, wordFrequencyAnalyzer.calculateFrequencyForWord(DEFAULT_TEXT_RANDOM_SEPARATORS, "SUN"));
    }

    @Test
    public void calculateFrequencyForWordTest_nullStringCase() {
        Assertions.assertEquals(0, wordFrequencyAnalyzer.calculateFrequencyForWord(null, "The"));
        Assertions.assertEquals(0, wordFrequencyAnalyzer.calculateFrequencyForWord(DEFAULT_TEXT, null));
    }

    @Test
    public void calculateFrequencyForWordTest_emptyStringCase() {
        Assertions.assertEquals(0, wordFrequencyAnalyzer.calculateFrequencyForWord("", "The"));
        Assertions.assertEquals(0, wordFrequencyAnalyzer.calculateFrequencyForWord(DEFAULT_TEXT, ""));
    }

    //calculateMostFrequentNWords
    @Test
    public void calculateMostFrequentNWordsTest_defaultCase() {
        WordFrequency[] expectedResult = new WordFrequency[] {
                new WordFrequencyImp("the", 2),
                new WordFrequencyImp("lake", 1),
                new WordFrequencyImp("over", 1)
        };


        WordFrequency[] result = wordFrequencyAnalyzer.calculateMostFrequentNWords(DEFAULT_TEXT, 3);

        Assertions.assertTrue(ArrayUtils.isNotEmpty(result));
        Assertions.assertEquals(3, result.length);

        Assertions.assertTrue(ArrayUtils.isEquals(expectedResult, result));
    }

    @Test
    public void calculateMostFrequentNWordsTest_nLargerThanLengthCase() {
        WordFrequency[] expectedResult = new WordFrequency[] {
                new WordFrequencyImp("the", 2),
                new WordFrequencyImp("lake", 1),
                new WordFrequencyImp("over", 1),
                new WordFrequencyImp("shines", 1),
                new WordFrequencyImp("sun", 1)
        };

        WordFrequency[] result = wordFrequencyAnalyzer.calculateMostFrequentNWords(DEFAULT_TEXT, 10);

        Assertions.assertTrue(ArrayUtils.isNotEmpty(result));
        Assertions.assertEquals(5, result.length);

        Assertions.assertTrue(ArrayUtils.isEquals(expectedResult, result));
    }

    @Test
    public void calculateMostFrequentNWordsTest_zeroNCase() {
        Assertions.assertTrue(ArrayUtils.isEmpty(wordFrequencyAnalyzer.calculateMostFrequentNWords(DEFAULT_TEXT, 0)));
    }

    @Test
    public void calculateMostFrequentNWordsTest_negativeNCase() {
        Assertions.assertTrue(ArrayUtils.isEmpty(wordFrequencyAnalyzer.calculateMostFrequentNWords(DEFAULT_TEXT, -1)));
    }

    @Test
    public void calculateMostFrequentNWordsTest_nullStringCase() {
        Assertions.assertTrue(ArrayUtils.isEmpty(wordFrequencyAnalyzer.calculateMostFrequentNWords(null, 3)));
    }

    @Test
    public void calculateMostFrequentNWordsTest_emptyStringCase() {
        Assertions.assertTrue(ArrayUtils.isEmpty(wordFrequencyAnalyzer.calculateMostFrequentNWords("", 3)));
    }
}
