package com.upmoon.alex.campchamptrivia;

import android.content.Context;

/**
 * Created by Alex on 10/3/2016.
 */

public class QuizLoader {

    private Quiz mQuizArray[];

    private Question mQuestionArray[];

    public QuizLoader(Context context) {
        // Allocate space for quizzes
        mQuizArray[0] = new Quiz(context.getString(R.string.Quiz1));
        mQuizArray[1] = new Quiz(context.getString(R.string.Quiz2));
        mQuizArray[2] = new Quiz(context.getString(R.string.Quiz3));

        // Load questions from XML Files and assign the arrays to the corresponding place in mQuizArray;

        // Initialize temporary variables for loading quiz data
        String[] mQuestionDataArray;
        String[] mAnswers = context.getResources().getStringArray(R.array.quiz1_Answers);
        String[] mTempAnswerArray;

        /*
        String mCurrentQuestion;

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 10; j++) {
                TODO: Figure out how to cycle through the q1q1 value to q3q10 in the next line, then uncomment and use this method
                mQuestionDataArray = context.getResources().getStringArray(R.array.q1q1);
                mAnswers = context.getResources().getStringArray(R.array.quiz1_Answers);
                mAnswers[Integer.parseInt(mQuestionDataArray[2])];
                mNumAnswers = Integer.parseInt(mQuestionDataArray[2])
                mQuestionArray[j] = Question(mQuestionDataArray[0], mQuestionDataArray[1], , int numAnswers)
            }
            mQuizArray[i].setQuestions(mQuestionArray);
        } */

        // Temporary solution
        mQuestionDataArray = context.getResources().getStringArray(R.array.q1q1);
        mTempAnswerArray[0] = mAnswers[Integer.parseInt(mQuestionDataArray[2])];
        mQuestionArray[0] = new Question(mQuestionDataArray[0], mQuestionDataArray[1], mTempAnswerArray, Integer.parseInt(mQuestionDataArray[3]));

        mQuestionDataArray = context.getResources().getStringArray(R.array.q1q2);
        mTempAnswerArray[0] = mAnswers[Integer.parseInt(mQuestionDataArray[2])];
        mQuestionArray[0] = new Question(mQuestionDataArray[0], mQuestionDataArray[1], mTempAnswerArray, Integer.parseInt(mQuestionDataArray[3]));

        mQuestionDataArray = context.getResources().getStringArray(R.array.q1q3);
        mTempAnswerArray[0] = mAnswers[Integer.parseInt(mQuestionDataArray[2])];
        mQuestionArray[0] = new Question(mQuestionDataArray[0], mQuestionDataArray[1], mTempAnswerArray, Integer.parseInt(mQuestionDataArray[3]));

        mQuestionDataArray = context.getResources().getStringArray(R.array.q1q4);
        mTempAnswerArray[0] = mAnswers[Integer.parseInt(mQuestionDataArray[2])];
        mQuestionArray[0] = new Question(mQuestionDataArray[0], mQuestionDataArray[1], mTempAnswerArray, Integer.parseInt(mQuestionDataArray[3]));

        mQuestionDataArray = context.getResources().getStringArray(R.array.q1q5);
        mTempAnswerArray[0] = mAnswers[Integer.parseInt(mQuestionDataArray[2])];
        mQuestionArray[0] = new Question(mQuestionDataArray[0], mQuestionDataArray[1], mTempAnswerArray, Integer.parseInt(mQuestionDataArray[3]));

        mQuestionDataArray = context.getResources().getStringArray(R.array.q1q6);
        mTempAnswerArray[0] = mAnswers[Integer.parseInt(mQuestionDataArray[2])];
        mQuestionArray[0] = new Question(mQuestionDataArray[0], mQuestionDataArray[1], mTempAnswerArray, Integer.parseInt(mQuestionDataArray[3]));

        mQuestionDataArray = context.getResources().getStringArray(R.array.q1q7);
        mTempAnswerArray[0] = mAnswers[Integer.parseInt(mQuestionDataArray[2])];
        mQuestionArray[0] = new Question(mQuestionDataArray[0], mQuestionDataArray[1], mTempAnswerArray, Integer.parseInt(mQuestionDataArray[3]));

        mQuestionDataArray = context.getResources().getStringArray(R.array.q1q8);
        mTempAnswerArray[0] = mAnswers[Integer.parseInt(mQuestionDataArray[2])];
        mQuestionArray[0] = new Question(mQuestionDataArray[0], mQuestionDataArray[1], mTempAnswerArray, Integer.parseInt(mQuestionDataArray[3]));

        mQuestionDataArray = context.getResources().getStringArray(R.array.q1q9);
        mTempAnswerArray[0] = mAnswers[Integer.parseInt(mQuestionDataArray[2])];
        mQuestionArray[0] = new Question(mQuestionDataArray[0], mQuestionDataArray[1], mTempAnswerArray, Integer.parseInt(mQuestionDataArray[3]));

        mQuestionDataArray = context.getResources().getStringArray(R.array.q1q10);
        mTempAnswerArray[0] = mAnswers[Integer.parseInt(mQuestionDataArray[2])];
        mQuestionArray[0] = new Question(mQuestionDataArray[0], mQuestionDataArray[1], mTempAnswerArray, Integer.parseInt(mQuestionDataArray[3]));

        mQuestionDataArray = context.getResources().getStringArray(R.array.q2q1);
        mTempAnswerArray[0] = mAnswers[Integer.parseInt(mQuestionDataArray[2])];
        mQuestionArray[0] = new Question(mQuestionDataArray[0], mQuestionDataArray[1], mTempAnswerArray, Integer.parseInt(mQuestionDataArray[3]));

        mQuestionDataArray = context.getResources().getStringArray(R.array.q2q2);
        mTempAnswerArray[0] = mAnswers[Integer.parseInt(mQuestionDataArray[2])];
        mQuestionArray[0] = new Question(mQuestionDataArray[0], mQuestionDataArray[1], mTempAnswerArray, Integer.parseInt(mQuestionDataArray[3]));

        mQuestionDataArray = context.getResources().getStringArray(R.array.q2q3);
        mTempAnswerArray[0] = mAnswers[Integer.parseInt(mQuestionDataArray[2])];
        mQuestionArray[0] = new Question(mQuestionDataArray[0], mQuestionDataArray[1], mTempAnswerArray, Integer.parseInt(mQuestionDataArray[3]));

        mQuestionDataArray = context.getResources().getStringArray(R.array.q2q4);
        mTempAnswerArray[0] = mAnswers[Integer.parseInt(mQuestionDataArray[2])];
        mQuestionArray[0] = new Question(mQuestionDataArray[0], mQuestionDataArray[1], mTempAnswerArray, Integer.parseInt(mQuestionDataArray[3]));

        mQuestionDataArray = context.getResources().getStringArray(R.array.q2q5);
        mTempAnswerArray[0] = mAnswers[Integer.parseInt(mQuestionDataArray[2])];
        mQuestionArray[0] = new Question(mQuestionDataArray[0], mQuestionDataArray[1], mTempAnswerArray, Integer.parseInt(mQuestionDataArray[3]));

        mQuestionDataArray = context.getResources().getStringArray(R.array.q2q6);
        mTempAnswerArray[0] = mAnswers[Integer.parseInt(mQuestionDataArray[2])];
        mQuestionArray[0] = new Question(mQuestionDataArray[0], mQuestionDataArray[1], mTempAnswerArray, Integer.parseInt(mQuestionDataArray[3]));

        mQuestionDataArray = context.getResources().getStringArray(R.array.q2q7);
        mTempAnswerArray[0] = mAnswers[Integer.parseInt(mQuestionDataArray[2])];
        mQuestionArray[0] = new Question(mQuestionDataArray[0], mQuestionDataArray[1], mTempAnswerArray, Integer.parseInt(mQuestionDataArray[3]));

        mQuestionDataArray = context.getResources().getStringArray(R.array.q2q8);
        mTempAnswerArray[0] = mAnswers[Integer.parseInt(mQuestionDataArray[2])];
        mQuestionArray[0] = new Question(mQuestionDataArray[0], mQuestionDataArray[1], mTempAnswerArray, Integer.parseInt(mQuestionDataArray[3]));

        mQuestionDataArray = context.getResources().getStringArray(R.array.q2q9);
        mTempAnswerArray[0] = mAnswers[Integer.parseInt(mQuestionDataArray[2])];
        mQuestionArray[0] = new Question(mQuestionDataArray[0], mQuestionDataArray[1], mTempAnswerArray, Integer.parseInt(mQuestionDataArray[3]));

        mQuestionDataArray = context.getResources().getStringArray(R.array.q2q10);
        mTempAnswerArray[0] = mAnswers[Integer.parseInt(mQuestionDataArray[2])];
        mQuestionArray[0] = new Question(mQuestionDataArray[0], mQuestionDataArray[1], mTempAnswerArray, Integer.parseInt(mQuestionDataArray[3]));

        mQuestionDataArray = context.getResources().getStringArray(R.array.q3q1);
        mTempAnswerArray[0] = mAnswers[Integer.parseInt(mQuestionDataArray[2])];
        mQuestionArray[0] = new Question(mQuestionDataArray[0], mQuestionDataArray[1], mTempAnswerArray, Integer.parseInt(mQuestionDataArray[3]));

        mQuestionDataArray = context.getResources().getStringArray(R.array.q3q2);
        mTempAnswerArray[0] = mAnswers[Integer.parseInt(mQuestionDataArray[2])];
        mQuestionArray[0] = new Question(mQuestionDataArray[0], mQuestionDataArray[1], mTempAnswerArray, Integer.parseInt(mQuestionDataArray[3]));

        mQuestionDataArray = context.getResources().getStringArray(R.array.q3q3);
        mTempAnswerArray[0] = mAnswers[Integer.parseInt(mQuestionDataArray[2])];
        mQuestionArray[0] = new Question(mQuestionDataArray[0], mQuestionDataArray[1], mTempAnswerArray, Integer.parseInt(mQuestionDataArray[3]));

        mQuestionDataArray = context.getResources().getStringArray(R.array.q3q4);
        mTempAnswerArray[0] = mAnswers[Integer.parseInt(mQuestionDataArray[2])];
        mQuestionArray[0] = new Question(mQuestionDataArray[0], mQuestionDataArray[1], mTempAnswerArray, Integer.parseInt(mQuestionDataArray[3]));

        mQuestionDataArray = context.getResources().getStringArray(R.array.q3q5);
        mTempAnswerArray[0] = mAnswers[Integer.parseInt(mQuestionDataArray[2])];
        mQuestionArray[0] = new Question(mQuestionDataArray[0], mQuestionDataArray[1], mTempAnswerArray, Integer.parseInt(mQuestionDataArray[3]));

        mQuestionDataArray = context.getResources().getStringArray(R.array.q3q6);
        mTempAnswerArray[0] = mAnswers[Integer.parseInt(mQuestionDataArray[2])];
        mQuestionArray[0] = new Question(mQuestionDataArray[0], mQuestionDataArray[1], mTempAnswerArray, Integer.parseInt(mQuestionDataArray[3]));

        mQuestionDataArray = context.getResources().getStringArray(R.array.q3q7);
        mTempAnswerArray[0] = mAnswers[Integer.parseInt(mQuestionDataArray[2])];
        mQuestionArray[0] = new Question(mQuestionDataArray[0], mQuestionDataArray[1], mTempAnswerArray, Integer.parseInt(mQuestionDataArray[3]));

        mQuestionDataArray = context.getResources().getStringArray(R.array.q3q8);
        mTempAnswerArray[0] = mAnswers[Integer.parseInt(mQuestionDataArray[2])];
        mQuestionArray[0] = new Question(mQuestionDataArray[0], mQuestionDataArray[1], mTempAnswerArray, Integer.parseInt(mQuestionDataArray[3]));

        mQuestionDataArray = context.getResources().getStringArray(R.array.q3q9);
        mTempAnswerArray[0] = mAnswers[Integer.parseInt(mQuestionDataArray[2])];
        mQuestionArray[0] = new Question(mQuestionDataArray[0], mQuestionDataArray[1], mTempAnswerArray, Integer.parseInt(mQuestionDataArray[3]));

        mQuestionDataArray = context.getResources().getStringArray(R.array.q3q10);
        mTempAnswerArray[0] = mAnswers[Integer.parseInt(mQuestionDataArray[2])];
        mQuestionArray[0] = new Question(mQuestionDataArray[0], mQuestionDataArray[1], mTempAnswerArray, Integer.parseInt(mQuestionDataArray[3]));

    }

    public Quiz getQuiz(int index) {
        return mQuizArray[index];
    }

    public Quiz[] getQuizArray() {
        return mQuizArray;
    }
}

/*
   mQuestionDataArray
   index   value
     0   Question Text
     1   Question Hint
     2   Index value for the correct answer, followed by the incorrect answers, in the answers array
     3   Number of Answers
 */