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
    Button enterBTN, hintBTN, scoreBTN;
    String showAnswer, actualAnswer, myAnswer;
    int score, hintsUsed;

    @Override+
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTV = (TextView) findViewById(R.id.questionTV);
        answerET = (EditText) findViewById(R.id.answerET);
        enterBTN = (Button) findViewById(R.id.enterBTN);
        hintBTN = (Button) findViewById(R.id.hintBTN);
        scoreBTN = (Button) findViewById(R.id.scoreBTN);
        showAnswer = "";
        actualAnswer = "";
        myAnswer = "";
        score = 0;
        hintsUsed = 0;

        enterBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualAnswer = "A coat of paint";
                myAnswer = answerET.getText().toString();
                int duration = Toast.LENGTH_SHORT;
                if (myAnswer.equals(actualAnswer)) {
                    Toast t = Toast.makeText(getApplicationContext(), "You got it right!", duration);
                    t.show();
                    score = score + 1;
                }
                else {
                    Toast t = Toast.makeText(getApplicationContext(), "You got it wrong :(", duration);
                    t.show();
                }
            }
        });

        hintBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAnswer = "The answer is 'A coat of paint'.";
                int duration = Toast.LENGTH_SHORT;
                Toast t = Toast.makeText(getApplicationContext(), showAnswer, duration);
                t.show();
                hintsUsed = hintsUsed + 1;
            }
        });

        scoreBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, ScoreActivity.class);
                myIntent.putExtra("score", score);
                myIntent.putExtra("hintsUsed", hintsUsed);
                startActivity(myIntent);
            }
        });
    }
}