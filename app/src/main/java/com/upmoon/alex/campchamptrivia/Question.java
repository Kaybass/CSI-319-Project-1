package com.upmoon.alex.campchamptrivia;

/**
 * Created by Alex on 9/27/2016.
 */

public class Question {

    private String mQuestionText;
    private String mQuestionHint;
    private String mQuestionAnswerArray[];
    private int mNumAnswers;

    private int mQuestionImageID;

    public Question(String questionText, String questionHint, String[] questionAnswers, int numAnswers) {
        mQuestionText = questionText;
        mQuestionHint = questionHint;
        mNumAnswers = numAnswers;
        for (int i = 0; i < numAnswers; i++) {
            mQuestionAnswerArray[i] = questionAnswers[i];
        }
    }

    public String getQuestionText() {
        return mQuestionText;
    }

    public int getNumAnswers() {
        return mNumAnswers;
    }

    public String[] getAnswers() {
        return mQuestionAnswerArray;
    }

    public String getQuestionHint() {
        return mQuestionHint;
    }

    public int checkAnswer() {
        return 0;
    }
}
