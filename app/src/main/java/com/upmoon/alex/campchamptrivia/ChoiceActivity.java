package com.upmoon.alex.campchamptrivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ChoiceActivity extends AppCompatActivity {

    private static final String TAG = "CHOICE ACTIVITY";

    private static final int sQUIZONE  = 1,
                            sQUIZTWO   = 2,
                            sQUIZTHREE = 3;

    private Button mButtons[] = new Button[3], mHighScoreButtons[] = new Button[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        Log.d(TAG, "onCreate() called");

        mButtons[sQUIZONE - 1] = (Button)findViewById(R.id.button1);
        mButtons[sQUIZONE - 1].setText(getString(R.string.Quiz1));
        mButtons[sQUIZONE - 1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = QuizActivity.newIntent(ChoiceActivity.this,sQUIZONE);
                startActivity(i);
            }
        });
        mButtons[sQUIZTWO - 1] = (Button)findViewById(R.id.button2);
        mButtons[sQUIZTWO - 1].setText(getString(R.string.Quiz2));
        mButtons[sQUIZTWO - 1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = QuizActivity.newIntent(ChoiceActivity.this,sQUIZTWO);
                startActivity(i);
            }
        });
        mButtons[sQUIZTHREE - 1] = (Button)findViewById(R.id.button3);
        mButtons[sQUIZTHREE - 1].setText(getString(R.string.Quiz3));
        mButtons[sQUIZTHREE - 1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = QuizActivity.newIntent(ChoiceActivity.this,sQUIZTHREE);
                startActivity(i);
            }
        });

        mHighScoreButtons[sQUIZONE - 1] = (Button)findViewById(R.id.button10);
        mHighScoreButtons[sQUIZONE - 1].setText("Q1 HS");
        mHighScoreButtons[sQUIZONE - 1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent d = HighScoreActivity.choiceIntent(ChoiceActivity.this, sQUIZONE);
                startActivity(d);
            }
        });
        mHighScoreButtons[sQUIZTWO - 1] = (Button)findViewById(R.id.button11);
        mHighScoreButtons[sQUIZTWO - 1].setText("Q2 HS");
        mHighScoreButtons[sQUIZTWO - 1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent d = HighScoreActivity.choiceIntent(ChoiceActivity.this, sQUIZTWO);
                startActivity(d);
            }
        });
        mHighScoreButtons[sQUIZTHREE - 1] = (Button)findViewById(R.id.button12);
        mHighScoreButtons[sQUIZTHREE - 1].setText("Q3 HS");
        mHighScoreButtons[sQUIZTHREE - 1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent d = HighScoreActivity.choiceIntent(ChoiceActivity.this, sQUIZTHREE);
                startActivity(d);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
}
