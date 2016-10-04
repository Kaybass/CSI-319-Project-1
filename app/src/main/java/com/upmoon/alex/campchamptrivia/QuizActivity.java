package com.upmoon.alex.campchamptrivia;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends AppCompatActivity {

    private String mAnswers1[] = {"asdsad","asdasdd","asdasd","asdads"};
    private String mAnswers2[] = {"asdasdsad","asdasasddd","asdaasdsd","asdasdads"};
    private String mAnswers3[] = {"asdsad","asdasdd","asdasd","asdads"};

    private Question mQuestions[] = {
            new MultChoiceQuestion("tendies","poo",mAnswers1,2),
            new MultChoiceQuestion("ree","poo", mAnswers2,2),
            new MultChoiceQuestion("hahahah","poo",mAnswers3,3),
    };

    private static final String TAG = "QUIZ ACTIVITY";

    private static final String EXTRA_QUIZ_NUMBER =
            "com.upmoon.alex.campchamptrivia.quiz_number";

    private ImageView mQuestionImage;

    private TextView mQuestionText;

    private RadioButton mAnswers[] = new RadioButton[4];

    private Button mNext;

    private int mQuizID, mQuestionsCorrectCounter;

    public static Intent newIntent(Context packageContext, int quizNum){
        Intent i = new Intent(packageContext, QuizActivity.class);
        i.putExtra(EXTRA_QUIZ_NUMBER,quizNum);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Log.d(TAG, "onCreate() called");

        mQuizID = getIntent().getIntExtra(EXTRA_QUIZ_NUMBER,0);

        Log.d(TAG,Integer.toString(mQuizID));

        mQuestionText  = (TextView)findViewById(R.id.textView2);

        mAnswers[0] = (RadioButton) findViewById(R.id.radioButton7);
        mAnswers[1] = (RadioButton) findViewById(R.id.radioButton8);
        mAnswers[2] = (RadioButton) findViewById(R.id.radioButton9);
        mAnswers[3] = (RadioButton) findViewById(R.id.radioButton10);

        mNext = (Button)findViewById(R.id.button5);

        //Set up first question

        mQuestionText.setText(mQuestions[0].getText());

        //TODO add hint

        for(int i = 0; i < 4; ++i){
            mAnswers[i].setText(mQuestions[0].getAnswer(i));
        }

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAnswers[0].isChecked() || mAnswers[1].isChecked() ||
                        mAnswers[2].isChecked() || mAnswers[3].isChecked()){


                }
                else{
                    Toast.makeText(QuizActivity.this,"Pick a hecking answer dummy",Toast.LENGTH_LONG).show();
                }
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
