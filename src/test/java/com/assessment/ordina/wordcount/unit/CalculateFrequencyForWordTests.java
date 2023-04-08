package com.assessment.ordina.wordcount.unit;

import com.assessment.ordina.wordcount.services.WordFrequencyAnalyzer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculateFrequencyForWordTests {
    final String DEFAULT_TEXT = "The sun shines over the lake";
    final String MIX_CAPS_TEXT = "The sun shines over tHE lake near THE shore";
    final String MIX_SEPARATORS_TEXT = "The sun\nshines123over@!#$the\tlake";
    final String DEFAULT_TEXT_LEADING_SEPARATORS = " @ \t \nThe sun shines over the lake";
    final String DEFAULT_TEXT_TRAILING_SEPARATORS = "The sun shines over the lake @ \t \n";
    final String DEFAULT_TEXT_RANDOM_SEPARATORS = "     The   sun shines over \tthe lake @ \t \n";

    private final WordFrequencyAnalyzer wordFrequencyAnalyzer;

    @Autowired
    public CalculateFrequencyForWordTests(WordFrequencyAnalyzer wordFrequencyAnalyzer) {
        this.wordFrequencyAnalyzer = wordFrequencyAnalyzer;
    }

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
        Assertions.assertEquals(0, wordFrequencyAnalyzer.calculateFrequencyForWord("123345", "The"));
        Assertions.assertEquals(0, wordFrequencyAnalyzer.calculateFrequencyForWord("!@#$", "The"));
        Assertions.assertEquals(0, wordFrequencyAnalyzer.calculateFrequencyForWord(MIX_SEPARATORS_TEXT, ""));
        Assertions.assertEquals(0, wordFrequencyAnalyzer.calculateFrequencyForWord(MIX_SEPARATORS_TEXT, "!@#$"));
        Assertions.assertEquals(0, wordFrequencyAnalyzer.calculateFrequencyForWord(MIX_SEPARATORS_TEXT, "12343"));
    }

    @Test
    public void calculateFrequencyForWordTest_multipleWordsCase() {
        Assertions.assertEquals(0, wordFrequencyAnalyzer.calculateFrequencyForWord(MIX_SEPARATORS_TEXT, "The sun"));
        Assertions.assertEquals(0, wordFrequencyAnalyzer.calculateFrequencyForWord(MIX_SEPARATORS_TEXT, "The123sun"));
        Assertions.assertEquals(0, wordFrequencyAnalyzer.calculateFrequencyForWord(MIX_SEPARATORS_TEXT, "The\nsun"));
    }

    @Test
    public void calculateFrequencyForWordTest_wordLeadingSeparatorsCase() {
        Assertions.assertEquals(2, wordFrequencyAnalyzer.calculateFrequencyForWord(MIX_SEPARATORS_TEXT, " The"));
        Assertions.assertEquals(2, wordFrequencyAnalyzer.calculateFrequencyForWord(MIX_SEPARATORS_TEXT, "\tThe"));
        Assertions.assertEquals(2, wordFrequencyAnalyzer.calculateFrequencyForWord(MIX_SEPARATORS_TEXT, "\nThe"));
        Assertions.assertEquals(2, wordFrequencyAnalyzer.calculateFrequencyForWord(MIX_SEPARATORS_TEXT, "@The"));
        Assertions.assertEquals(2, wordFrequencyAnalyzer.calculateFrequencyForWord(MIX_SEPARATORS_TEXT, "12345The"));
    }

    @Test
    public void calculateFrequencyForWordTest_wordTrailingSeparatorsCase() {
        Assertions.assertEquals(2, wordFrequencyAnalyzer.calculateFrequencyForWord(MIX_SEPARATORS_TEXT, "The "));
        Assertions.assertEquals(2, wordFrequencyAnalyzer.calculateFrequencyForWord(MIX_SEPARATORS_TEXT, "The\t"));
        Assertions.assertEquals(2, wordFrequencyAnalyzer.calculateFrequencyForWord(MIX_SEPARATORS_TEXT, "The\n"));
        Assertions.assertEquals(2, wordFrequencyAnalyzer.calculateFrequencyForWord(MIX_SEPARATORS_TEXT, "The!@#"));
        Assertions.assertEquals(2, wordFrequencyAnalyzer.calculateFrequencyForWord(MIX_SEPARATORS_TEXT, "The12345"));
    }
}
