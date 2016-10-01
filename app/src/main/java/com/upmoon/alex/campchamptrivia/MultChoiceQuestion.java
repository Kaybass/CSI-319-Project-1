package com.upmoon.alex.campchamptrivia;

/**
 * Created by Alex on 9/30/2016.
 */

public class MultChoiceQuestion extends Question{

    private String mAnswers[] = new String[4];

    public MultChoiceQuestion(){

    }

    @Override
    public int checkAnswer(){

        return 0;
    }
}
