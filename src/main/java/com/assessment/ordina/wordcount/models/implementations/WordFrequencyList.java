package com.assessment.ordina.wordcount.models.implementations;

import com.assessment.ordina.wordcount.models.WordFrequency;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;


public class WordFrequencyList extends ArrayList<WordFrequency> {

    public WordFrequencyList() {
        super();
    }

    public WordFrequencyList(Collection<? extends WordFrequency> c) {
        super(c);
    }

    public boolean contains(String o) {
        if(StringUtils.isBlank(o)) {
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
