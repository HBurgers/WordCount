package com.assessment.ordina.wordcount.services.implementations;

import com.assessment.ordina.wordcount.models.WordFrequency;
import com.assessment.ordina.wordcount.models.implementations.WordFrequencyImp;
import com.assessment.ordina.wordcount.models.implementations.WordFrequencyList;
import com.assessment.ordina.wordcount.services.WordFrequencyAnalyzer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class WordFrequencyAnalyzerService implements WordFrequencyAnalyzer {

    private final String SPLIT_REGEX = "([^a-zA-Z])+";

    @Override
    public int calculateHighestFrequency(final String text) {
        log.debug(String.format("Running calculateHighestFrequency with the following text: %s", text));

        if (StringUtils.isBlank(text)) {
            //No text provided. Highest frequency will thus be 0;
            log.debug("Exiting and returning 0 since text is blank.");

            return 0;
        }

        return calculateHighestFrequencyFromList(toSortedWordFrequencyList(text));
    }

    @Override
    public int calculateFrequencyForWord(final String text, final String word) {
        log.debug(String.format("Running calculateFrequencyForWord with the text: %s and the word: %s", text, word));

        if (StringUtils.isBlank(text) || StringUtils.isBlank(word)) {
            //Either text is blank or word is blank. Either way, word does not appear in text thus return 0
            log.debug("Exiting and returning 0 since text or word is blank.");

            return 0;
        }

        final WordFrequencyList frequencyList = new WordFrequencyList(toSortedWordFrequencyList(text));

        return frequencyList.contains(word) ? frequencyList.get(frequencyList.indexOf(word)).getFrequency() : 0;
    }

    @Override
    public WordFrequency[] calculateMostFrequentNWords(final String text, final int n) {
        log.debug(String.format("Running calculateMostFrequentNWords with the text: %s and the n value: %d", text, n));

        if (StringUtils.isBlank(text) || n < 0) {
            //Either no text has been provided or an invalid value for n is provided.
            log.debug("Exiting and returning 0 since text is blank or n value is less than 0.");

            return new WordFrequency[0];
        }

        return mapNElementsToWordFrequencyArray(toSortedWordFrequencyList(text), n);
    }

    private List<WordFrequency> toSortedWordFrequencyList(String inputText) {
        if (StringUtils.isBlank(inputText)) {
            return new ArrayList<>();
        }

        WordFrequencyList wordFrequencyList = new WordFrequencyList();

        Arrays.stream(inputText.split(SPLIT_REGEX))
                .filter(StringUtils::isNotBlank)
                .map(String::toLowerCase)
                .forEach(filteredWord -> {
                    if (wordFrequencyList.contains(filteredWord)) {
                        ((WordFrequencyImp) wordFrequencyList.get(wordFrequencyList.indexOf(filteredWord))).incrementFrequency();
                    } else {
                        wordFrequencyList.add(new WordFrequencyImp(filteredWord, 1));
                    }
                });

        return wordFrequencyList.stream()
                .sorted(Comparator.comparingInt(WordFrequency::getFrequency).reversed().thenComparing(WordFrequency::getWord))
                .collect(Collectors.toList());
    }

    private int calculateHighestFrequencyFromList(final List<WordFrequency> frequencyList) {
        return frequencyList.isEmpty() ? 0 : frequencyList.get(0).getFrequency();
    }

    private WordFrequency[] mapNElementsToWordFrequencyArray(final List<WordFrequency> wordFrequencyList, final int n) {
        if (n >= wordFrequencyList.size()) {
            return wordFrequencyList.toArray(WordFrequency[]::new);
        }

        return wordFrequencyList.stream().limit(n).toArray(WordFrequency[]::new);
    }

}
