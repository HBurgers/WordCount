package com.assessment.ordina.wordcount.services.implementations;

import com.assessment.ordina.wordcount.models.WordFrequency;
import com.assessment.ordina.wordcount.services.WordFrequencyAnalyzer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class WordFrequencyAnalyzerService implements WordFrequencyAnalyzer {

    private final String SPLIT_REGEX = "([^a-zA-Z])+";

    @Override
    public int calculateHighestFrequency(String text) {
        if (StringUtils.isBlank(text)) {
            return 0;
        }

        Optional<Map.Entry<String, Integer>> highestEntry = toCountedMap(text).entrySet().stream().max(Map.Entry.comparingByValue());

        return highestEntry.get();
    }

    @Override
    public int calculateFrequencyForWord(String text, String word) {
        return 0;
    }

    @Override
    public WordFrequency[] calculateMostFrequentNWords(String text, int n) {
        return new WordFrequency[0];
    }

//    private List splitText(String text) {
//        if(StringUtils.isBlank(text)) {
//            return new ArrayList<>();
//        }
//
//        return Arrays.stream(text.split(SPLIT_REGEX)).filter(word -> StringUtils.isNotBlank(word)).collect(Collectors.toList());
//    }

    private  Map<String, Integer> toCountedMap(String inputText) {
        if(StringUtils.isBlank(inputText)) {
            return Collections.EMPTY_MAP;
        }

        HashMap<String, Integer> countedMap = new HashMap<>();

        Arrays.stream(inputText.split(SPLIT_REGEX)).map(String::toUpperCase).forEach(filteredWord -> {
            if (countedMap.containsKey(filteredWord)) {
                countedMap.replace(filteredWord, countedMap.get(filteredWord) + 1);
            } else {
                countedMap.put(filteredWord, 1);
            }
        });

        return countedMap;
    }
}
