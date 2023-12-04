package org.pattersonclippers.riddlequizappjjc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.color.utilities.Score;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView  questionTV;
    EditText answerET;
    Button enterBTN, hintBTN;
    String showHint, myAnswer;
    int score, hintsUsed, currentIndex;
    Question q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, currentQ;
    Question[] questions;
    MediaPlayer toBePlayed;
    FirebaseDatabase database;
    DatabaseReference myRef;

    final String TAG = "IAMATAGPLS";

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

        q1 = new Question(getString(R.string.q1text), getString(R.string.q1Ans), R.raw.bgm1);
        q2 = new Question(getString(R.string.q2text), getString(R.string.q2Ans), R.raw.bgm2);
        q3 = new Question(getString(R.string.q3text), getString(R.string.q3Ans), R.raw.bgm3);
        q4 = new Question(getString(R.string.q4text), getString(R.string.q4Ans), R.raw.bgm4);
        q5 = new Question(getString(R.string.q5text), getString(R.string.q5Ans), R.raw.bgm5);
        q6 = new Question(getString(R.string.q6text), getString(R.string.q6Ans), R.raw.bgm6);
        q7 = new Question(getString(R.string.q7text), getString(R.string.q7Ans), R.raw.bgm7);
        q8 = new Question(getString(R.string.q8text), getString(R.string.q8Ans), R.raw.bgm8);
        q9 = new Question(getString(R.string.q9text), getString(R.string.q9Ans), R.raw.bgm9);
        q10 = new Question(getString(R.string.q10text), getString(R.string.q10Ans), R.raw.bgm10);
        questions = new Question[] {q1, q2, q3, q4, q5, q6, q7, q8, q9, q10};
        currentQ = questions[currentIndex];

        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        toBePlayed = MediaPlayer.create(MainActivity.this, R.raw.bgm1);
        toBePlayed.start();
        enterBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAnswer = answerET.getText().toString();
                int duration = Toast.LENGTH_SHORT;

                toBePlayed.stop();

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

                    toBePlayed.release();
                    toBePlayed = MediaPlayer.create(MainActivity.this, currentQ.getBGM());
                }
                else {
                    Intent myIntent = new Intent(MainActivity.this, ScoreActivity.class);
                    myIntent.putExtra("score", score);
                    myIntent.putExtra("hintsUsed", hintsUsed);

                    startActivity(myIntent);
                }
                toBePlayed.start();
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