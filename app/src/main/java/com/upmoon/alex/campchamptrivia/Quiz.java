package com.upmoon.alex.campchamptrivia;

/**
 * Created by Alex on 9/27/2016.
 */


public class Quiz {

    private Question mQuestions[];

    //private int mAssetID;

    private String mQuizName;

    public Quiz(String name){

        mQuizName = name;
    }

    public void setQuestions(Question questions[]) {

        mQuestions = questions;

    }

    public Question getQuestion(int index){

        return mQuestions[index];
    }

    public String getName(){
        return mQuizName;
    }
}
