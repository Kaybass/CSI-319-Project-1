package com.upmoon.alex.campchamptrivia;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class HighScoreActivity extends AppCompatActivity {

    private int[][] mHighScores = new int[3][10];

    private String[][] mHighScoreOwners = new String[3][10];

    private int[][] mHighScoreQuizNumbers = new int[3][10];

    private int mPlayerScore, mQuizID;

    private static final String TAG = "HIGH SCORE ACTIVITY";

    private static final String EXTRA_QUIZ_NUMBER =
            "com.upmoon.alex.campchamptrivia.hsquiz_number";

    private static final String EXTRA_PLAYER_SCORE =
            "com.upmoon.alex.campchamptrivia.player_score";

    private EditText mNameField;

    private Button mShareScore, mResetScores, mSubmitScore;

    private TextView mHighScoreLabel, mHighScoreSubmitted;

    public static Intent quizIntent(Context packageContext, int quizNum, int playerScore){
        Log.d(TAG,"CALLED");

        Intent i = new Intent(packageContext, HighScoreActivity.class);
        i.putExtra(EXTRA_QUIZ_NUMBER,quizNum);
        i.putExtra(EXTRA_PLAYER_SCORE, playerScore);
        return i;
    }

    public static Intent choiceIntent(Context packageContext, int quizNum){
        Log.d(TAG,"CALLED");
        Intent i = new Intent(packageContext, HighScoreActivity.class);
        i.putExtra(EXTRA_QUIZ_NUMBER,quizNum);
        i.putExtra(EXTRA_PLAYER_SCORE,-1); // Set playerScore = 0 if HighScoreActivity is called from ChoiceActivity, so share score button will be hidden.
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

        mPlayerScore = getIntent().getIntExtra(EXTRA_PLAYER_SCORE,0);
        mQuizID = getIntent().getIntExtra(EXTRA_QUIZ_NUMBER,0);
        Log.d(TAG, Integer.toString(mQuizID));

        mHighScoreSubmitted = (TextView) findViewById(R.id.newScore);

        String newLabel = "Quiz #" + Integer.toString(mQuizID) + " High Scores";
        mHighScoreLabel = (TextView) findViewById(R.id.labelHighScore);
        mHighScoreLabel.setText(newLabel);

        getExistingHighScores();

        if (mQuizID != -1) { // If mPlayerScore is -1, then the activity has been started from ChoiceActivity and there is no need to create a way for the user to share their high score

            // Populate GridView with mHighScores
            populateGridView();

            mShareScore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String mShareMessage = "I just scored a " + Integer.toString(mPlayerScore) + " on quiz " + Integer.toString(mQuizID) + " in CampChampTrivia!";
                    Intent share = new Intent(Intent.ACTION_SEND);
                    share.setType("text/plain");
                    share.putExtra(Intent.EXTRA_STREAM, mShareMessage);
                    startActivity(Intent.createChooser(share, "share"));
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
                    mHighScoreOwners[mQuizID][i] = "Empty.";
                    mHighScores[mQuizID][i] = 0;
                }
                recreate();
            }
        });

        mSubmitScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userReport;
                if(checkScoreIsHigh()) {
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
        String FILENAME = "highScores";

        // Try to open highScores.txt in a FileInputStream
        try {
            FileInputStream fis = openFileInput(FILENAME);

            if(fis != null) {
                Log.d(TAG, "Success: File Input Stream is open.");

                // Open fis in a DataInputStream to use readInt()
                DataInputStream dis = new DataInputStream(fis);
                Log.d(TAG, "Success: Data Input Stream is open.");

                // Load high scores from memory.
                for(int i = 0; i < 3; i++) {
                    for(int j = 0; j < 10; j++) {
                        try {
                            mHighScoreOwners[i][j] = dis.readLine();
                            mHighScoreQuizNumbers[i][j] = dis.readInt();
                            mHighScores[i][j] = dis.readInt();
                        } catch (Exception exception) {
                            Log.d(TAG, "Unable to read data value, init with 0.");
                            mHighScoreOwners[i][j] = "Empty.";
                            mHighScoreQuizNumbers[i][j] = 0;
                            mHighScores[i][j] = 0;
                        }
                        Log.d(TAG, "Another one! Read from memory.");
                    }
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
            for (int i = 0; i < 3; i++) {
                for(int j = 0; j < 10; j++) {
                    mHighScoreOwners[i][j] = "Empty.";
                    mHighScoreQuizNumbers[i][j] = 0;
                    mHighScores[i][j] = 0;
                }
            }
        }
        return;
    }

    private void setHighScores() {
        Log.d(TAG, "setHighScores() called");

        // High scores are stored in a file highScores.txt
        String FILENAME = "highScores";

        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            DataOutputStream dos = new DataOutputStream(fos);
            for (int i = 0; i < 3; i++) {
                for(int j = 0; j < 10; j++) {
                    // write scores[i][j] to memory.
                    dos.writeBytes(mHighScoreOwners[i][j]);
                    dos.writeInt(mHighScoreQuizNumbers[i][j]);
                    dos.writeInt(mHighScores[i][j]);
                }
            }
            dos.flush();
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

    private boolean checkScoreIsHigh(int score, int quizID, String userName) {
        Log.d(TAG, "checkScoreIsHigh has been called. -------------");
        boolean highScore = false;
        int tempScore, tempQuizNumber;
        String tempName;
        for(int i = 0; i < 10; i++) {
            // If playerScore made the highScores list, bump the rest of the high scores down the list.
            if (score >= mHighScores[quizID][i]) {
                if(highScore == false) { highScore = true; }

                tempScore = mHighScores[quizID][i];
                tempName = mHighScoreOwners[quizID][i];
                tempQuizNumber = mHighScoreQuizNumbers[quizID][i];

                mHighScores[quizID][i] = score;
                mHighScoreOwners[quizID][i] = userName;
                mHighScoreQuizNumbers[quizID][i] = quizID;

                score = tempScore;
                userName = tempName;
                quizID = tempQuizNumber;
            }
        }
        return highScore;
    }

    private void populateGridView(){
        GridView highScoreView = (GridView) findViewById(R.id.listHighScores);

        // Load highScores array into String array of text values to be used in GridView
        String[] highScoreLabels = new String[10];
        for(int i = 0; i < 10; i++) {
            highScoreLabels[i] = Integer.toString((i+1)) + ": " + mHighScoreOwners[mQuizID][i] + Integer.toString(mHighScores[mQuizID][i]);
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
