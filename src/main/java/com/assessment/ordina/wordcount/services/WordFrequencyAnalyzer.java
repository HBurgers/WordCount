package com.assessment.ordina.wordcount.services;

import com.assessment.ordina.wordcount.models.WordFrequency;

public interface WordFrequencyAnalyzer {
    int calculateHighestFrequency(String text);
    int calculateFrequencyForWord (String text, String word);
    WordFrequency[] calculateMostFrequentNWords (String text, int n);
}
