package com.upmoon.alex.campchamptrivia;

/**
 * Created by Alex on 9/30/2016.
 */

public class MultChoiceQuestion extends Question{

    private String mQuestionText, mQuestionHint;

    private String mAnswers[] = new String[4];

    private int mCorrectIndex;

    public MultChoiceQuestion(String questionText, String questionHint, String[] answers, int index){
        mQuestionText = questionText;
        mQuestionHint = questionHint;
        mAnswers = answers;
        mCorrectIndex = index;
    }

    public String getText(){ return mQuestionText; }

    public String getHint(){ return mQuestionHint; }

    @Override
    public String getAnswer(int index){
        if(index < mAnswers.length && index >= 0){
            return mAnswers[index];
        }
        return "";
    }

    @Override
    public boolean checkAnswer(int index){

        if(mCorrectIndex == index){
            return true;
        }

        return false;
    }
}
