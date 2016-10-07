package com.upmoon.alex.campchamptrivia;

/**
 * Created by Alex on 9/30/2016.
 */

public class MultChoiceQuestion extends Question{

    private String mQuestionText, mQuestionHint;

    private String mAnswers[] = new String[4];

    private int mCorrectAnswerIndex, mImageResID;

    public MultChoiceQuestion(String questionText, String questionHint, String[] answers, int index, int res){
        mQuestionText = questionText;
        mQuestionHint = questionHint;
        mAnswers = answers;
        mCorrectAnswerIndex = index;
        mImageResID = res;
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
    public int getAnswerIndex() {
        return mCorrectAnswerIndex;
    }

    @Override
    public int getResID() { return mImageResID; }

    @Override
    public boolean checkAnswer(int index){

        if(mCorrectAnswerIndex == index){
            return true;
        }

        return false;
    }
}
