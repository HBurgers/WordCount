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

    /**
     * @implNote This method calculates the frequency of the most frequent word in the text provided.
     * @param text The text to consider.
     * @return and int value representing the frequency of the most frequent word.
     */
    @Override
    public int calculateHighestFrequency(final String text) {
        log.debug(String.format("Running calculateHighestFrequency with the following text: %s", text));

        if (StringUtils.isBlank(text)) {
            //No text provided. Highest frequency will thus be 0;
            log.debug("Exiting and returning 0 since text is blank.");

            return 0;
        }

        return returnFrequencyOfFirstElementOfList(toSortedWordFrequencyList(text));
    }

    /**
     * @implNote This method calculates the frequency of the word provided.
     * @param text the text to consider.
     * @param word the word whose frequency needs to be determined.
     * @return and int value representing the frequency of the requested word.
     */
    @Override
    public int calculateFrequencyForWord(final String text, String word) {
        log.debug(String.format("Running calculateFrequencyForWord with the text: %s and the word: %s", text, word));

        if (StringUtils.isBlank(text) || StringUtils.isBlank(word)) {
            //Either text is blank or word is blank. Either way, word does not appear in text thus return 0
            log.debug("Exiting and returning 0 since text or word is blank.");

            return 0;
        }

        List<WordFrequency> wordAsList = toSortedWordFrequencyList(word);
        if (wordAsList.isEmpty()) {
            log.debug("Exiting and returning 0 since no word has been provided.");

            //Word consisted of non-alphabetic characters. Is thus same as blank.
            return 0;
        }

        if (wordAsList.size() > 1) {
            log.debug("Exiting and returning 0 since more than one word has been provided.");

            //Word consists of more than one word. For now return as not found. Maybe add additional implementation for list of words.
            return 0;
        }

        word = wordAsList.get(0).getWord();

        final WordFrequencyList frequencyList = new WordFrequencyList(toSortedWordFrequencyList(text));

        return frequencyList.contains(word) ? frequencyList.get(frequencyList.indexOf(word)).getFrequency() : 0;
    }

    /**
     * @implNote This method calculates n most frequent words in the provided.
     * @param text the text to consider.
     * @param n the number of most frequent words to return.
     * @return an array of the most frequent WordFrequeny objects. This array is first sorted in a descending manner according to frequency and then alphabetically in an accessing manner according to word.
     */
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

    /**
     * @implNote This method splits the provided text into a list of words and sorts them according to frequency and word.
     * @param inputText the text to consider.
     * @return a list of WordFrequeny objects. This list is first sorted in a descending manner according to frequency and then alphabetically in an accessing manner according to word.
     */
    private List<WordFrequency> toSortedWordFrequencyList(String inputText) {
        log.debug(String.format("Running toSortedWordFrequencyList with the text: %s", inputText));

        if (StringUtils.isBlank(inputText)) {
            log.debug("The inputText provided is empty. Returning and empty array.");

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

    /**
     * @implNote This method simply returns the frequency first element of the list, if it isn't empty.
     * @param frequencyList The list to consider.
     * @return The frequency of the first element in the list. It will return 0 if the list is empty.
     */
    private int returnFrequencyOfFirstElementOfList(final List<WordFrequency> frequencyList) {
        return frequencyList.isEmpty() ? 0 : frequencyList.get(0).getFrequency();
    }

    /**
     * @implNote This method converts a List of WordFrequency objects to an array of WordFrequency objects. This will only do it for the first n elements according to the n provided.
     * @param wordFrequencyList The list to be converted.
     * @param n The n value indicating how many elements should be converted.
     * @return An array of WordFrequency objects containing n amount of objects.
     */
    private WordFrequency[] mapNElementsToWordFrequencyArray(final List<WordFrequency> wordFrequencyList, final int n) {
        log.debug(String.format("Running mapNElementsToWordFrequencyArray with a list of size %d and a n value of: %d", wordFrequencyList.size(), n));

        if (n >= wordFrequencyList.size()) {
            log.debug("The n value provided is larger than the size of the list. Returning the entire list.");

            return wordFrequencyList.toArray(WordFrequency[]::new);
        }

        return wordFrequencyList.stream().limit(n).toArray(WordFrequency[]::new);
    }
}
