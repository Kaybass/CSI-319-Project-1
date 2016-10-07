package com.upmoon.alex.campchamptrivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ChoiceActivity extends AppCompatActivity {

    private static final String TAG = "CHOICE ACTIVITY";

    //

    private Button mButtons[] = new Button[3], mHighScoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        Log.d(TAG, "onCreate() called");

        mButtons[0] = (Button)findViewById(R.id.button1);
        mButtons[0].setText(getString(R.string.Quiz1));
        mButtons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = QuizActivity.newIntent(ChoiceActivity.this,1);
                startActivity(i);
            }
        });
        mButtons[1] = (Button)findViewById(R.id.button2);
        mButtons[1].setText(getString(R.string.Quiz2));
        mButtons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = QuizActivity.newIntent(ChoiceActivity.this,2);
                startActivity(i);
            }
        });
        mButtons[2] = (Button)findViewById(R.id.button3);
        mButtons[2].setText(getString(R.string.Quiz3));
        mButtons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = QuizActivity.newIntent(ChoiceActivity.this,3);
                startActivity(i);
            }
        });

        mHighScoreButton = (Button)findViewById(R.id.button10);

        mHighScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent d = HighScoreActivity.choiceIntent(ChoiceActivity.this);
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
