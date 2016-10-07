package com.upmoon.alex.campchamptrivia;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HighScoreActivity extends AppCompatActivity {

    private int[] mHighScores = new int[10];

    private int mPlayerScore, mQuizID;

    private static final String TAG = "HIGH SCORE ACTIVITY";

    private static final String EXTRA_QUIZ_NUMBER =
            "com.upmoon.alex.campchamptrivia.hsquiz_number";

    private static final String EXTRA_PLAYER_SCORE =
            "com.upmoon.alex.campchamptrivia.player_score";

    private Button mShareScore, mResetScores;

    public static Intent quizIntent(Context packageContext, int quizNum, int playerScore){
        Log.d(TAG,"CALLED");

        Intent i = new Intent(packageContext, HighScoreActivity.class);
        i.putExtra(EXTRA_QUIZ_NUMBER,quizNum);
        i.putExtra(EXTRA_PLAYER_SCORE, playerScore);
        return i;
    }

    public static Intent choiceIntent(Context packageContext){
        Log.d(TAG,"CALLED");
        Intent i = new Intent(packageContext, HighScoreActivity.class);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        Log.d(TAG, "HighScoreActivity onCreate() called");

        mShareScore = (Button) findViewById(R.id.shareButton);
        mResetScores = (Button) findViewById(R.id.resetButton);

        mPlayerScore = getIntent().getIntExtra(EXTRA_PLAYER_SCORE,0);
        mQuizID = getIntent().getIntExtra(EXTRA_QUIZ_NUMBER,0);
        Log.d(TAG, Integer.toString(mQuizID));

        mHighScores = getExistingHighScores();

        if (mQuizID != 0) { // If mQuizID is 0, then the activity has been started from ChoiceActivity and there is no need to create a way for the user to share their high score
            // Check if the player score has made the high scores list
            mHighScores = checkScoreIsHigh(mHighScores, mPlayerScore);

            // Populate GridView with mHighScores
            populateGridView(mHighScores);

            mShareScore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String mShareMessage = "I just scored a " + Integer.toString(mPlayerScore) + " on quiz " + Integer.toString(mQuizID) + " in CampChampTrivia!";
                    Intent share = new Intent(Intent.ACTION_SEND);
                    share.setType("text/plain"); // might be text, sound, whatever
                    share.putExtra(Intent.EXTRA_STREAM, mShareMessage);
                    startActivity(Intent.createChooser(share, "share"));
                }
            });
        } else {
            mShareScore.setVisibility(View.GONE);

            // Populate GridView with mHighScores
            populateGridView(mHighScores);
        }

        mResetScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 10; i++) {
                    mHighScores[i] = 0;
                }
                recreate();
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
        setHighScores(mHighScores);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private int[] getExistingHighScores() {
        Log.d(TAG, "getExistingHighScores() called");
        int[] highScores = new int[10];

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
                for(int i = 0; i < 10; i++) {
                    try {
                        highScores[i] = dis.readInt();
                    } catch(Exception exception) {
                        Log.d(TAG, "Unable to read data value, init with 0.");
                        highScores[i] = 0;
                    }
                    Log.d(TAG, "Another one! Read from memory. SUCCESS!");
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
                highScores[i] = 0;
            }
        }

        // Return highScores array
        return highScores;
    }

    private void setHighScores(int[] scores) {
        Log.d(TAG, "setHighScores() called");

        // High scores are stored in a file highScores.txt
        String FILENAME = "highScores";

        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            DataOutputStream dos = new DataOutputStream(fos);
            for (int i = 0; i < 10; i++) {
                // write scores[i] to memory.
                dos.writeInt(scores[i]);
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

    private int[] checkScoreIsHigh(int[] highScoreArray, int score) {
        Log.d(TAG, "checkScoreIsHigh has been called. -------------");
        int temp = 0;
        for(int i = 0; i < 10; i++) {
            // If playerScore made the highScores list, bump the rest of the high scores down the list.
            if(score >= highScoreArray[i]){

                temp = highScoreArray[i];

                highScoreArray[i] = score;

                score = temp;
            }
        }
        return highScoreArray;
    }

    private void populateGridView(int[] highScores){
        GridView highScoreView = (GridView) findViewById(R.id.listHighScores);

        // Load highScores array into String array of text values to be used in GridView
        String[] highScoreLabels = new String[10];
        for(int i = 0; i < 10; i++) {
            highScoreLabels[i] = Integer.toString((i+1)) + ": " + Integer.toString(highScores[i]);
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
