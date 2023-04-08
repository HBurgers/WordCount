package com.assessment.ordina.wordcount.unit;

import com.assessment.ordina.wordcount.services.WordFrequencyAnalyzer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculateHighestFrequencyTests {
    final String DEFAULT_TEXT = "The sun shines over the lake";
    final String MIX_CAPS_TEXT = "The sun shines over tHE lake near THE shore";
    final String MIX_SEPARATORS_TEXT = "The sun\nshines123over@!#$the\tlake";
    final String DEFAULT_TEXT_LEADING_SEPARATORS = " @ \t \nThe sun shines over the lake";
    final String DEFAULT_TEXT_TRAILING_SEPARATORS = "The sun shines over the lake @ \t \n";
    final String DEFAULT_TEXT_RANDOM_SEPARATORS = "     The   sun shines over \tthe lake @ \t \n";

    private final WordFrequencyAnalyzer wordFrequencyAnalyzer;

    @Autowired
    public CalculateHighestFrequencyTests(WordFrequencyAnalyzer wordFrequencyAnalyzer) {
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
        Assertions.assertEquals(0, wordFrequencyAnalyzer.calculateHighestFrequency("@#$"));
        Assertions.assertEquals(0, wordFrequencyAnalyzer.calculateHighestFrequency("231"));
    }
}
