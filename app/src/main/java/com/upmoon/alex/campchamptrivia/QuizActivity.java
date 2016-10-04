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

/*  private String mAnswers1[] = {"asdsad","asdasdd","asdasd","asdads"};
    private String mAnswers2[] = {"asdasdsad","asdasasddd","asdaasdsd","asdasdads"};
    private String mAnswers3[] = {"asdsad","asdasdd","asdasd","asdads"};

    private Question mQuestions[] = {
            new MultChoiceQuestion("tendies","poo",mAnswers1,2),
            new MultChoiceQuestion("ree","poo", mAnswers2,2),
            new MultChoiceQuestion("hahahah","poo",mAnswers3,3),
    };*/

    private Question[] mQuestions;
    //private String[] mAnswers;

    private static final String TAG = "QUIZ ACTIVITY";

    private static final String EXTRA_QUIZ_NUMBER =
            "com.upmoon.alex.campchamptrivia.quiz_number";

    private ImageView mQuestionImage;

    private TextView mQuestionText;

    private RadioButton mAnswers[] = new RadioButton[4];

    private Button mNext, mHintButton;

    private int mQuizID, mQuestionsCorrectCounter;

    private String mHint;

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

        // Load Quiz #EXTRA_QUIZ_NUMBER's questions into mQuestions array.
        for(int i = 0; i < 10; i++) {
            mQuestions[i] = getQuestionByID(mQuizID + 1, i + 1);

        }

        Log.d(TAG,Integer.toString(mQuizID));

        mQuestionText  = (TextView)findViewById(R.id.textView2);

        mAnswers[0] = (RadioButton) findViewById(R.id.radioButton7);
        mAnswers[1] = (RadioButton) findViewById(R.id.radioButton8);
        mAnswers[2] = (RadioButton) findViewById(R.id.radioButton9);
        mAnswers[3] = (RadioButton) findViewById(R.id.radioButton10);

        mNext = (Button)findViewById(R.id.button5);

        //Set up first question

        mQuestionText.setText(mQuestions[0].getText());

        mHintButton = (Button)findViewById(R.id.button6);
        mHint = mQuestions[0].getHint();
        mHintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(QuizActivity.this,mHint,Toast.LENGTH_SHORT).show();
            }
        });

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
                    Toast.makeText(QuizActivity.this,"Pick a hecking answer dummy",Toast.LENGTH_SHORT).show();
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

    private Question getQuestionByID(int quizNumber, int questionNumber) {
        String[] mQuestionData;
        String[] mQuestionAnswers;
        String packageName = getPackageName();

        String questionName = "q" + quizNumber + "q" + questionNumber;

        Log.d(TAG,packageName);

        String answerName = questionName + "_answers";

        Log.d(TAG,answerName);

        int resID = QuizActivity.this.getResources().getIdentifier(answerName,"array",  packageName);

        Log.d(TAG,Integer.toString(resID));

        mQuestionAnswers = this.getResources().getStringArray(resID);

        Log.d(TAG,mQuestionAnswers[0]);

        resID = this.getResources().getIdentifier(questionName, "array", packageName);
        mQuestionData = this.getResources().getStringArray(resID);

        Log.d(TAG,mQuestionData[3]);

        return new MultChoiceQuestion(mQuestionData[0], mQuestionData[1], mQuestionAnswers, Integer.parseInt(mQuestionData[3]));
    }
}
