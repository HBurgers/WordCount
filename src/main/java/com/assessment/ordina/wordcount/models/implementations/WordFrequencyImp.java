package com.assessment.ordina.wordcount.models.implementations;

import com.assessment.ordina.wordcount.models.WordFrequency;
import lombok.Getter;

public class WordFrequencyImp implements WordFrequency {

    @Getter
    private String word;
    @Getter
    private int frequency;
}
