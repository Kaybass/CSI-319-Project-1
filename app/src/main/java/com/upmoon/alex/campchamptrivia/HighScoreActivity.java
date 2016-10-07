package com.upmoon.alex.campchamptrivia;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HighScoreActivity extends AppCompatActivity {

    private int[] mHighScores = new int[10];

    private String[] mHighScoreOwners = new String[10];

    private int mPlayerScore, mQuizID;

    private static final String TAG = "HIGH SCORE ACTIVITY";

    private static final String EXTRE_QUIZ_NUMBER =
            "com.upmoon.alex.campchamptrivia.hsquiz_number";

    private static final String EXTRA_PLAYER_SCORE =
            "com.upmoon.alex.campchamptrivia.player_score";

    private EditText mNameField;

    private Button mShareScore, mResetScores, mSubmitScore;

    private TextView mHighScoreLabel, mHighScoreSubmitted;
    private Random mGen = new Random(SystemClock.elapsedRealtimeNanos());

    private String mPlayerName = "Player" + Integer.toString(mGen.nextInt(101));

    public static Intent quizIntent(Context packageContext, int quizNum, int playerScore){
        Log.d(TAG,"CALLED");

        Intent i = new Intent(packageContext, HighScoreActivity.class);
        i.putExtra(EXTRE_QUIZ_NUMBER,quizNum);
        i.putExtra(EXTRA_PLAYER_SCORE, playerScore);
        return i;
    }

    public static Intent choiceIntent(Context packageContext, int quizNum){
        Log.d(TAG,"CALLED");
        Intent i = new Intent(packageContext, HighScoreActivity.class);
        i.putExtra(EXTRE_QUIZ_NUMBER,quizNum);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        Log.d(TAG, "HighScoreActivity onCreate() called");

        mNameField = (EditText) findViewById(R.id.nameField);

        mShareScore = (Button) findViewById(R.id.shareButton);
        mResetScores = (Button) findViewById(R.id.resetButton);
        mSubmitScore = (Button) findViewById(R.id.submitButton);

        mPlayerScore = getIntent().getIntExtra(EXTRA_PLAYER_SCORE,-1);
        mQuizID = getIntent().getIntExtra(EXTRE_QUIZ_NUMBER,0);
        Log.d(TAG, Integer.toString(mQuizID));

        mHighScoreSubmitted = (TextView) findViewById(R.id.newScore);

        String newLabel = "Quiz #" + Integer.toString(mQuizID) + " High Scores";
        mHighScoreLabel = (TextView) findViewById(R.id.labelHighScore);
        mHighScoreLabel.setText(newLabel);

        getExistingHighScores();

        if (mPlayerScore != -1) { // If mPlayerScore is -1, then the activity has been started from ChoiceActivity and there is no need to create a way for the user to share their high score

            // Populate GridView with mHighScores
            populateGridView();

            mShareScore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String mShareMessage = "I just scored a " + Integer.toString(mPlayerScore) + " on quiz " + Integer.toString(mQuizID) + " in CampChampTrivia!";
                    Intent share = new Intent(Intent.ACTION_SEND);
                    share.setType("text/plain");
                    share.putExtra(Intent.EXTRA_TEXT, mShareMessage);
                    startActivity(Intent.createChooser(share, "Share my score!"));
                }
            });
        } else {
            mShareScore.setVisibility(View.GONE);

            mSubmitScore.setVisibility(View.GONE);

            mNameField.setVisibility(View.GONE);

            // Populate GridView with mHighScores
            populateGridView();
        }

        mResetScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 10; i++) {
                    mHighScoreOwners[i] = "Empty.";
                    mHighScores[i] = 0;
                }
                recreate();
            }
        });

        mNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPlayerName = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSubmitScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userReport;

                if(checkScoreIsHigh(mPlayerScore,mQuizID,mPlayerName)) {
                    userReport = "Great job, You got a new high score!";
                } else {
                    userReport = "You didn't beat any high score, study your CampChamp trivia!";
                }
                mNameField.setVisibility(View.GONE);
                mSubmitScore.setVisibility(View.GONE);
                mHighScoreSubmitted.setText(userReport);
                populateGridView();
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
        setHighScores();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private void getExistingHighScores() {
        Log.d(TAG, "getExistingHighScores() called");

        // High scores are stored in a file highScores.txt
        String FILENAME = "highScores" + Integer.toString(mQuizID);

        // Try to open highScores.txt in a FileInputStream
        try {
            FileInputStream fis = openFileInput(FILENAME);

            if(fis != null) {
                Log.d(TAG, "Success: File Input Stream is open.");

                // Open fis in a DataInputStream to use readInt()
                DataInputStream dis = new DataInputStream(fis);
                Log.d(TAG, "Success: Data Input Stream is open.");

                // Load high scores from memory.
                for(int i = 0; i < 10; i++) {

                    try {
                        mHighScoreOwners[i]= dis.readLine();
                        mHighScores[i] = dis.readInt();
                    } catch (Exception exception) {
                        Log.d(TAG, "Unable to read data value, init with 0.");
                        mHighScoreOwners[i] = "Empty.";
                        mHighScores[i] = 0;
                    }
                    Log.d(TAG, "Another one! Read from memory.");

                }

                // Close DataInputStream
                dis.close();
                Log.d(TAG, "Success: Data Input Stream is closed.");
            } else {
                Log.d(TAG, "Error: File Input Stream is null.");
            }

            // Close inputFileStream
            fis.close();
        } catch (Exception exception) {
            String error = "Error: Unable to open file, " + FILENAME;
            Log.d(TAG, error);
            // First Time, initialize zeros array.
            Log.d(TAG, "Initializing with zeros array");
            for (int i = 0; i < 10; i++) {
                mHighScoreOwners[i] = "Empty";
                mHighScores[i] = 0;
            }
        }
        return;
    }

    private void setHighScores() {
        Log.d(TAG, "setHighScores() called");

        // High scores are stored in a file highScores.txt
        String FILENAME = "highScores" + Integer.toString(mQuizID);

        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            DataOutputStream dos = new DataOutputStream(fos);
            for (int i = 0; i < 10; i++) {
                dos.writeBytes(mHighScoreOwners[i]);
                dos.writeChar('\n');
                dos.writeInt(mHighScores[i]);
                dos.flush();
            }

            dos.close();
            Log.d(TAG, "Success: Data Output Stream is closed.");

            fos.flush();
            fos.close();
            Log.d(TAG, "Success: File Output Stream is closed.");

            Log.d(TAG, "Success writing scores");
        } catch (Exception exception) {
            Log.d(TAG, "Error: Unable to save high scores.");
        }

        return;
    }

    private Boolean checkScoreIsHigh(int score, int quizID, String userName) {
        Log.d(TAG, "checkScoreIsHigh has been called. -------------");
        int tempScore;
        String tempName;
        Boolean change = false;
        for(int i = 0; i < 10; i++) {
            // If playerScore made the highScores list, bump the rest of the high scores down the list.
            if (score >= mHighScores[i]) {
                change = true;

                tempScore = mHighScores[i];
                tempName = mHighScoreOwners[i];

                mHighScores[i] = score;
                mHighScoreOwners[i] = userName;

                score = tempScore;
                userName = tempName;
            }
        }
        return change;
    }

    private void populateGridView(){
        GridView highScoreView = (GridView) findViewById(R.id.listHighScores);

        // Load highScores array into String array of text values to be used in GridView
        String[] highScoreLabels = new String[10];
        for(int i = 0; i < 10; i++) {
            highScoreLabels[i] = mHighScoreOwners[i] + ": " + Integer.toString(mHighScores[i]);
        }

        // Populate a List from string array elements
        final List<String> highScoreLabelsList = new ArrayList<String>(Arrays.asList(highScoreLabels));

        // Create a new ArrayAdapter to control GridView
        final ArrayAdapter<String> gridViewArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, highScoreLabelsList);

        // Data bind GridView with ArrayAdapter
        highScoreView.setAdapter(gridViewArrayAdapter);

        return;
    }
}
