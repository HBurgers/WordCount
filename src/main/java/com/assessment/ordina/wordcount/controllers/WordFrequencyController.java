package com.assessment.ordina.wordcount.controllers;

import com.assessment.ordina.wordcount.models.WordFrequency;
import com.assessment.ordina.wordcount.services.WordFrequencyAnalyzer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(
            summary = "Returns the word with the highest frequency in the text provided.",
            parameters = @Parameter(name = "text", description = "The text to be counted", example = "The sun shines over the lake"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "A successful response returning the highest frequency in the text.")
            }
    )
    public int getHighestFrequency(@RequestParam final String text) {
        return wordFrequencyAnalyzer.calculateHighestFrequency(text);
    }

    @GetMapping("frequency")
    @Operation(
            summary = "Returns the frequency of a provided word in the text provided.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "A successful response returning the highest frequency in the text.")
            }
    )
    public int getFrequencyForWord(@RequestParam final String text, @RequestParam final String word) {
        return wordFrequencyAnalyzer.calculateFrequencyForWord(text, word);
    }

    @GetMapping("nth/frequency")
    @Operation(
            summary = "Returns the n most frequent words in the text provided.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "A successful response containing n most frequent words")
            }
    )
    public WordFrequency[] getHighestNFrequentWords(@RequestParam final String text, @RequestParam final int n) {
        return wordFrequencyAnalyzer.calculateMostFrequentNWords(text, n);
    }
}
