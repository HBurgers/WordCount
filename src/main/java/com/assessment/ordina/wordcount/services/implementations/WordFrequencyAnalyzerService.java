package com.assessment.ordina.wordcount.services.implementations;

import com.assessment.ordina.wordcount.models.WordFrequency;
import com.assessment.ordina.wordcount.models.implementations.WordFrequencyImp;
import com.assessment.ordina.wordcount.services.WordFrequencyAnalyzer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordFrequencyAnalyzerService implements WordFrequencyAnalyzer {

    private final String SPLIT_REGEX = "([^a-zA-Z])+";

    @Override
    public int calculateHighestFrequency(final String text) {
        if (StringUtils.isBlank(text)) {
            //No text provided. Highest frequency will thus be 0;
            return 0;
        }

        return calculateHighestFrequencyFromList(toSortedWordFrequencyList(text));
    }

    @Override
    public int calculateFrequencyForWord(final String text, final String word) {

        if (StringUtils.isBlank(text) || StringUtils.isBlank(word)) {
            //Either text is blank or word is blank. Either way, word does not appear in text thus return 0
            return 0;
        }

        final List<WordFrequencyImp> frequencyList = toSortedWordFrequencyList(text);

        return frequencyList.contains(word) ? frequencyList.get(frequencyList.indexOf(word)).getFrequency() : 0;
    }

    @Override
    public WordFrequency[] calculateMostFrequentNWords(final String text, final int n) {

        if (StringUtils.isBlank(text) || n < 0) {
            //Either no text has been provided or an invalid value for n is provided.
            return new WordFrequency[0];
        }

        return mapNElementsToWordFrequencyArray(toSortedWordFrequencyList(text), n);
    }

    private List<WordFrequencyImp> toSortedWordFrequencyList(String inputText) {
        if (StringUtils.isBlank(inputText)) {
            return new ArrayList<>();
        }

        List<WordFrequencyImp> wordFrequencyList = new ArrayList<>();

        Arrays.stream(inputText.split(SPLIT_REGEX))
                .filter(StringUtils::isNotBlank)
                .map(String::toLowerCase)
                .forEach(filteredWord -> {
                    if (wordFrequencyList.contains(filteredWord)) {
                        wordFrequencyList.get(wordFrequencyList.indexOf(filteredWord)).incrementFrequency();
                    } else {
                        wordFrequencyList.add(new WordFrequencyImp(filteredWord, 1));
                    }
                });

        return wordFrequencyList.stream().sorted((o1, o2) -> Integer.compare(o2.getFrequency(), o1.getFrequency())).collect(Collectors.toList());
    }

    private int calculateHighestFrequencyFromList(final List<WordFrequencyImp> frequencyList) {
        return frequencyList.isEmpty() ? 0 : frequencyList.get(0).getFrequency();
    }

    private WordFrequency[] mapNElementsToWordFrequencyArray(final List<WordFrequencyImp> wordFrequencyList, final int n) {
        if (n >= wordFrequencyList.size()) {
            return wordFrequencyList.toArray(WordFrequency[]::new);
        }

        return wordFrequencyList.stream().limit(n).toArray(WordFrequency[]::new);
    }

}
