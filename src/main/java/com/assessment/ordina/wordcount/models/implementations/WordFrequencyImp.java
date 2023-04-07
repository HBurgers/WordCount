package com.assessment.ordina.wordcount.models.implementations;

import com.assessment.ordina.wordcount.models.WordFrequency;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class WordFrequencyImp implements WordFrequency {

    @Getter
    private final String word;
    @Getter
    private int frequency;

    public WordFrequencyImp(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    public void incrementFrequency() {
        this.frequency++;
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, frequency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WordFrequencyImp that)) return false;
        return frequency == that.frequency && word.equals(that.word);
    }

    @Override
    public String toString() {
        return "WordFrequencyImp{" +
                "word='" + word + '\'' +
                ", frequency=" + frequency +
                '}';
    }
}
