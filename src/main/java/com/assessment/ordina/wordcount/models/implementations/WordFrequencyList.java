package com.assessment.ordina.wordcount.models.implementations;

import com.assessment.ordina.wordcount.models.WordFrequency;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * @implNote This is a list extending ArrayList of WordFrequency. This was created to allow a custom implementation of the contains and indexOf methods.
 */
public class WordFrequencyList extends ArrayList<WordFrequency> {

    public WordFrequencyList() {
        super();
    }

    public WordFrequencyList(Collection<? extends WordFrequency> c) {
        super(c);
    }

    /**
     * @param o The string to compare to.
     * @return true if the list contains a WordFrequency object with a word matching the provided String.
     * @implNote Checks if the current list contains a WordFrequency object with a word matching the provided String.
     */
    public boolean contains(String o) {
        if (StringUtils.isBlank(o)) {
            return false;
        }

        AtomicBoolean found = new AtomicBoolean(false);

        this.forEach(wordFrequency -> {
            if (wordFrequency.getWord().equalsIgnoreCase(o)) {
                found.set(true);
            }
        });
        return found.get();
    }

    /**
     * @param o The string to compare to WordFrequency words in the list.
     * @return the index of the WordFrequency object in the list whose word matches the String provided.
     * @implNote This method finds the index of the WordFrequency object in the list whose word matches the String provided.
     */
    public int indexOf(String o) {
        if (o == null) {
            for (int i = 0; i < size(); i++) {
                if (get(i).getWord() == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size(); i++) {
                if (o.equalsIgnoreCase(get(i).getWord())) {
                    return i;
                }
            }
        }
        return -1;
    }
}
