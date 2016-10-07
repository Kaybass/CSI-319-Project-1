package com.upmoon.alex.campchamptrivia;

/**
 * Created by Alex on 9/27/2016.
 */

public abstract class Question {

    private String mQuestionText, mQuestionHint;

    private String mAnswers[] = new String[4];

    private int mCorrectAnswerIndex, mImageResID;

    public Question() {

    }

    public String getText(){ return mQuestionText; }

    public String getHint(){ return mQuestionHint; }

    public String getAnswer(int index){
        return mAnswers[index];
    }
    public int getAnswerIndex() {
        return mCorrectAnswerIndex;
    }
    public int getResID(){ return mImageResID; }

    public boolean checkAnswer(int index) {
        if (index == mCorrectAnswerIndex){
            return true;
        }

        return false;
    }
}
