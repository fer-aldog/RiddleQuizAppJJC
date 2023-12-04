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
import android.widget.LinearLayout;
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
    String showHint, myAnswer, theme, initialName;
    int score, hintsUsed, currentIndex;
    LinearLayout mainbackgroundLL, qtextbgLL;
    Question q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, currentQ;
    Question[] questions;
    MediaPlayer toBePlayed;
    private SharedPreferences mySharedPreferences;
    private String spFilename = "org.pattersonclippers.riddlequizappjjc.QuizScore";
    private final String COLOR_KEY = "color";
    private final String UN_KEY = "username";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialise shared preferences
        mySharedPreferences = getSharedPreferences(spFilename, MODE_PRIVATE);

        //read initial value for color from last time
        theme = mySharedPreferences.getString(COLOR_KEY, "riddle");
        initialName = mySharedPreferences.getString(UN_KEY, "player");

        questionTV = (TextView) findViewById(R.id.questionTV);
        answerET = (EditText) findViewById(R.id.answerET);
        enterBTN = (Button) findViewById(R.id.enterBTN);
        hintBTN = (Button) findViewById(R.id.hintBTN);
        mainbackgroundLL = (LinearLayout) findViewById(R.id.mainbackgroundLL);
        qtextbgLL = (LinearLayout) findViewById(R.id.qtextbgLL);
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

        if(theme.equals("gloomy")) {
            mainbackgroundLL.setBackgroundColor(getResources().getColor(R.color.gloomy_bg));
            qtextbgLL.setBackgroundColor(getResources().getColor(R.color.gloomy_questionbg));
            questionTV.setTextColor(getResources().getColor(R.color.gloomy_qtextcolor));
            answerET.setHintTextColor(getResources().getColor(R.color.gloomy_edittext));
            hintBTN.setBackgroundColor(getResources().getColor(R.color.gloomy_hintbtn));
            enterBTN.setBackgroundColor(getResources().getColor(R.color.gloomy_enterbtn));
        }
        if(theme.equals("riddle")) {
            mainbackgroundLL.setBackgroundColor(getResources().getColor(R.color.riddle_bg));
            qtextbgLL.setBackgroundColor(getResources().getColor(R.color.riddle_questionbg));
            questionTV.setTextColor(getResources().getColor(R.color.riddle_qtextcolor));
            answerET.setHintTextColor(getResources().getColor(R.color.riddle_edittext));
            hintBTN.setBackgroundColor(getResources().getColor(R.color.riddle_hintbtn));
            enterBTN.setBackgroundColor(getResources().getColor(R.color.riddle_enterbtn));
        }

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