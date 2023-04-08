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
public class CalculateMostFrequentNWordsTests {
    final String DEFAULT_MIX_TEXT = "The sun\nshines123over@!#$the\tlake";

    private final WordFrequencyAnalyzer wordFrequencyAnalyzer;

    @Autowired
    public CalculateMostFrequentNWordsTests(WordFrequencyAnalyzer wordFrequencyAnalyzer) {
        this.wordFrequencyAnalyzer = wordFrequencyAnalyzer;
    }

    @Test
    public void calculateMostFrequentNWordsTest_defaultCase() {
        WordFrequency[] expectedResult = new WordFrequency[]{
                new WordFrequencyImp("the", 2),
                new WordFrequencyImp("lake", 1),
                new WordFrequencyImp("over", 1)
        };


        WordFrequency[] result = wordFrequencyAnalyzer.calculateMostFrequentNWords(DEFAULT_MIX_TEXT, 3);

        Assertions.assertTrue(ArrayUtils.isNotEmpty(result));
        Assertions.assertEquals(3, result.length);

        Assertions.assertTrue(ArrayUtils.isEquals(expectedResult, result));
    }

    @Test
    public void calculateMostFrequentNWordsTest_nLargerThanLengthCase() {
        WordFrequency[] expectedResult = new WordFrequency[]{
                new WordFrequencyImp("the", 2),
                new WordFrequencyImp("lake", 1),
                new WordFrequencyImp("over", 1),
                new WordFrequencyImp("shines", 1),
                new WordFrequencyImp("sun", 1)
        };

        WordFrequency[] result = wordFrequencyAnalyzer.calculateMostFrequentNWords(DEFAULT_MIX_TEXT, 10);

        Assertions.assertTrue(ArrayUtils.isNotEmpty(result));
        Assertions.assertEquals(5, result.length);

        Assertions.assertTrue(ArrayUtils.isEquals(expectedResult, result));
    }

    @Test
    public void calculateMostFrequentNWordsTest_zeroNCase() {
        Assertions.assertTrue(ArrayUtils.isEmpty(wordFrequencyAnalyzer.calculateMostFrequentNWords(DEFAULT_MIX_TEXT, 0)));
    }

    @Test
    public void calculateMostFrequentNWordsTest_negativeNCase() {
        Assertions.assertTrue(ArrayUtils.isEmpty(wordFrequencyAnalyzer.calculateMostFrequentNWords(DEFAULT_MIX_TEXT, -1)));
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
