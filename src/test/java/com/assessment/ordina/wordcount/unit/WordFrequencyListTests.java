package com.assessment.ordina.wordcount.unit;

import com.assessment.ordina.wordcount.models.implementations.WordFrequencyImp;
import com.assessment.ordina.wordcount.models.implementations.WordFrequencyList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WordFrequencyListTests {
    private final WordFrequencyList testWordFrequencyList = new WordFrequencyList();
    private final WordFrequencyImp containedWord = new WordFrequencyImp("TestWord", 2);

    @BeforeEach
    public void cleanSetup() {
        testWordFrequencyList.add(containedWord);
        testWordFrequencyList.add(new WordFrequencyImp("DifferentWord", 2));
        testWordFrequencyList.add(new WordFrequencyImp("NotTheSame", 2));
        testWordFrequencyList.add(new WordFrequencyImp("TestWord", 1));
    }

    @Test
    public void containsTest_doesContain() {
        Assertions.assertTrue(testWordFrequencyList.contains(containedWord.getWord()));
    }

    @Test
    public void containsTest_doesNotContain() {
        Assertions.assertFalse(testWordFrequencyList.contains("ARandomNotContainedWord"));
    }

    @Test
    public void containsTest_nullValue() {
        Assertions.assertFalse(testWordFrequencyList.contains(null));
    }

    @Test
    public void containsTest_emptyValue() {
        Assertions.assertFalse(testWordFrequencyList.contains(""));
    }

    @Test
    public void indexOfTest_doesContain() {
        Assertions.assertEquals(0, testWordFrequencyList.indexOf(containedWord.getWord()));
    }

    @Test
    public void indexOfTest_doesNotContain() {
        Assertions.assertEquals(-1, testWordFrequencyList.indexOf("ARandomNotContainedWord"));
    }

    @Test
    public void indexOfTest_nullValue_doesNotContain() {
        Assertions.assertEquals(-1, testWordFrequencyList.indexOf(null));
    }

    @Test
    public void indexOfTest_nullValue_doesContain() {
        testWordFrequencyList.add(new WordFrequencyImp(null, 1));
        Assertions.assertEquals(4, testWordFrequencyList.indexOf(null));
    }

    @Test
    public void indexOfTest_emptyValue() {
        Assertions.assertEquals(-1, testWordFrequencyList.indexOf(""));
    }

}
