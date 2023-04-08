package com.assessment.ordina.wordcount.controllers;

import com.assessment.ordina.wordcount.models.WordFrequency;
import com.assessment.ordina.wordcount.services.WordFrequencyAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/word")
public class WordFrequencyController {

    private final WordFrequencyAnalyzer wordFrequencyAnalyzer;

    @Autowired
    public WordFrequencyController(WordFrequencyAnalyzer wordFrequencyAnalyzer) {
        this.wordFrequencyAnalyzer = wordFrequencyAnalyzer;
    }

    @GetMapping("highest/frequency")
    public int getHighestFrequency(@RequestParam final String text) {
        return wordFrequencyAnalyzer.calculateHighestFrequency(text);
    }

    @GetMapping("frequency")
    public int getFrequencyForWord(@RequestParam final String text, @RequestParam final String word) {
        return wordFrequencyAnalyzer.calculateFrequencyForWord(text, word);
    }

    @GetMapping("nth/frequency")
    public WordFrequency[] getHighestNFrequentWords(@RequestParam final String text, @RequestParam final int n) {
        return wordFrequencyAnalyzer.calculateMostFrequentNWords(text, n);
    }
}
