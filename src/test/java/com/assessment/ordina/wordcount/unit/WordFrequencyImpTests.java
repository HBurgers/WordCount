package com.assessment.ordina.wordcount.unit;

import com.assessment.ordina.wordcount.models.implementations.WordFrequencyImp;
import com.assessment.ordina.wordcount.models.implementations.WordFrequencyList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WordFrequencyImpTests {

    private WordFrequencyImp testWord;
    private WordFrequencyImp expectedWord;
    private WordFrequencyImp differentWordWord;
    private WordFrequencyImp differentFreqWord;

    @BeforeEach
    public void cleanSetup () {

        testWord = new WordFrequencyImp("TestWord", 2);
        expectedWord = new WordFrequencyImp("TestWord", 2);
        differentWordWord = new WordFrequencyImp("NotTheSame", 2);
        differentFreqWord = new WordFrequencyImp("TestWord", 1);
    }

    @Test
    public void incrementFrequency() {
        testWord.incrementFrequency();
        Assertions.assertEquals(3, testWord.getFrequency());
    }

    @Test
    public void equalsTest_isTheSame() {
        Assertions.assertTrue(testWord.equals(expectedWord));
        Assertions.assertTrue(testWord.equals(testWord));
    }

    @Test
    public void equalsTest_isNotTheSame() {
        Assertions.assertFalse(testWord.equals(differentWordWord));
        Assertions.assertFalse(testWord.equals(differentFreqWord));
    }

    @Test
    public void equalsTest_isNotTheSameObject() {
        Assertions.assertFalse(testWord.equals(new WordFrequencyList()));
    }

    @Test
    public void toStringTest() {
        Assertions.assertEquals(expectedWord.toString(), testWord.toString());
    }
}
