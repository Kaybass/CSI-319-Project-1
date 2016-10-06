package com.upmoon.alex.campchamptrivia;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class HighScoreActivity extends AppCompatActivity {

    private int[] mHighScores = new int[10];

    private int mNewScore, mQuizID;

    private static final String TAG = "HIGH SCORE ACTIVITY";

    private static final String EXTRA_QUIZ_NUMBER =
            "com.upmoon.alex.campchamptrivia.quiz_number";

    private static final String PLAYER_SCORE =
            "com.upmoon.alex.campchamptrivia.quiz_number";

    private Button mShareScore;

    public static Intent newIntent(Context packageContext, int quizNum, int newScore){
        Intent i = new Intent(packageContext, QuizActivity.class);
        i.putExtra(EXTRA_QUIZ_NUMBER,quizNum);
        i.putExtra(PLAYER_SCORE, newScore);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        Log.d(TAG, "HighScoreActivity onCreate() called");

        mShareScore = (Button) findViewById(R.id.shareButton);



        mShareScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mShareMessage = "I just scored a " + Integer.toString(mNewScore) + " on quiz " + Integer.toString(mQuizID) + " in CampChampTrivia!";
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain"); // might be text, sound, whatever
                share.putExtra(Intent.EXTRA_STREAM, mShareMessage);
                startActivity(Intent.createChooser(share, "share"));
            }
        });
    }
}
