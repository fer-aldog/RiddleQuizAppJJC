package org.pattersonclippers.riddlequizappjjc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.color.utilities.Score;

public class MainActivity extends AppCompatActivity {

    TextView  questionTV;
    EditText answerET;
    Button enterBTN, hintBTN;
    String showHint, myAnswer;
    int score, hintsUsed, currentIndex;
    Question q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, currentQ;
    Question[] questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTV = (TextView) findViewById(R.id.questionTV);
        answerET = (EditText) findViewById(R.id.answerET);
        enterBTN = (Button) findViewById(R.id.enterBTN);
        hintBTN = (Button) findViewById(R.id.hintBTN);
        showHint = "";
        myAnswer = "";
        currentIndex = 0;
        score = 0;
        hintsUsed = 0;
        q1 = new Question(getString(R.string.q1text), getString(R.string.q1Ans));
        q2 = new Question(getString(R.string.q2text), getString(R.string.q2Ans));
        q3 = new Question(getString(R.string.q3text), getString(R.string.q3Ans));
        q4 = new Question(getString(R.string.q4text), getString(R.string.q4Ans));
        q5 = new Question(getString(R.string.q5text), getString(R.string.q5Ans));
        q6 = new Question(getString(R.string.q6text), getString(R.string.q6Ans));
        q7 = new Question(getString(R.string.q7text), getString(R.string.q7Ans));
        q8 = new Question(getString(R.string.q8text), getString(R.string.q8Ans));
        q9 = new Question(getString(R.string.q9text), getString(R.string.q9Ans));
        q10 = new Question(getString(R.string.q10text), getString(R.string.q10Ans));
        questions = new Question[] {q1, q2, q3, q4, q5, q6, q7, q8, q9, q10};
        currentQ = questions[currentIndex];

        enterBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAnswer = answerET.getText().toString();
                int duration = Toast.LENGTH_SHORT;
                if (currentQ.getCorrectAnswer().equalsIgnoreCase(myAnswer)) {
                    Toast t = Toast.makeText(getApplicationContext(), getString(R.string.correctMsg), duration);
                    t.show();
                    score = score + 1;
                }
                else {
                    Toast t = Toast.makeText(getApplicationContext(), getString(R.string.wrongMsg), duration);
                    t.show();
                }
                currentIndex++;
                if(currentIndex < 10) {
                    currentQ = questions[currentIndex];
                    questionTV.setText(currentQ.getQText());
                    answerET.setText("");
                }
                else {
                    Intent myIntent = new Intent(MainActivity.this, ScoreActivity.class);
                    myIntent.putExtra("score", score);
                    myIntent.putExtra("hintsUsed", hintsUsed);
                    startActivity(myIntent);
                }
            }
        });

        hintBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHint = getString(R.string.hintText) + " '" + currentQ.getCorrectAnswer() + "'.";
                int duration = Toast.LENGTH_SHORT;
                Toast t = Toast.makeText(getApplicationContext(), showHint, duration);
                t.show();
                hintsUsed = hintsUsed + 1;
            }
        });
    }
}