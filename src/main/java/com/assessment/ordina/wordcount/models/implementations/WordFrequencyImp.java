package com.assessment.ordina.wordcount.models.implementations;

import com.assessment.ordina.wordcount.models.WordFrequency;
import lombok.Getter;

public class WordFrequencyImp implements WordFrequency {

    @Getter
    private final String word;
    @Getter
    private int frequency;

    /**
     * @param word
     * @param frequency
     */
    public WordFrequencyImp(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    /**
     * @implNote This method increments the current frequency. Frequency will never be null, since there is no default constructor and cannot be set to null.
     */
    public void incrementFrequency() {
        this.frequency++;
    }

    /**
     * @param o The object to compare to.
     * @return true if the provided object is equal to the current object or false if they differ.
     * @implNote This method compares two WordFrequency objects. If they have the same word and frequency, they are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WordFrequency that)) return false;
        return frequency == that.getFrequency() && word.equals(that.getWord());
    }

    /**
     * @return the current object as a string.
     */
    @Override
    public String toString() {
        return "WordFrequencyImp{" +
                "word='" + word + '\'' +
                ", frequency=" + frequency +
                '}';
    }
}
