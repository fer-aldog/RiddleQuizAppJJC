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
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Intent incomingIntent;
    TextView  questionTV;
    EditText answerET;
    Button enterBTN, hintBTN;
    String showHint, myAnswer, theme, initialName;
    int score, totalQs, ibeforetotal, currentIndex;
    LinearLayout mainbackgroundLL, qtextbgLL;
    Question q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, q13, q14, q15, q16, q17, q18, q19, q20, currentQ;
    ArrayList<Question> unusedQuestions, toBeAnswered;
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
        ibeforetotal = 0;
        score = 0;

        incomingIntent = getIntent();
        totalQs = incomingIntent.getIntExtra("totalQs", 0);

        q1 = new Question(getString(R.string.q1text), getString(R.string.q1Ans), getString(R.string.q1hint), R.raw.bgm1);
        q2 = new Question(getString(R.string.q2text), getString(R.string.q2Ans), getString(R.string.q2hint), R.raw.bgm2);
        q3 = new Question(getString(R.string.q3text), getString(R.string.q3Ans), getString(R.string.q3hint), R.raw.bgm3);
        q4 = new Question(getString(R.string.q4text), getString(R.string.q4Ans), getString(R.string.q4hint), R.raw.bgm4);
        q5 = new Question(getString(R.string.q5text), getString(R.string.q5Ans), getString(R.string.q5hint), R.raw.bgm5);
        q6 = new Question(getString(R.string.q6text), getString(R.string.q6Ans), getString(R.string.q6hint), R.raw.bgm6);
        q7 = new Question(getString(R.string.q7text), getString(R.string.q7Ans), getString(R.string.q7hint), R.raw.bgm7);
        q8 = new Question(getString(R.string.q8text), getString(R.string.q8Ans), getString(R.string.q8hint), R.raw.bgm8);
        q9 = new Question(getString(R.string.q9text), getString(R.string.q9Ans), getString(R.string.q9hint), R.raw.bgm9);
        q10 = new Question(getString(R.string.q10text), getString(R.string.q10Ans), getString(R.string.q10hint), R.raw.bgm10);
        q11 = new Question(getString(R.string.q11text), getString(R.string.q11Ans), getString(R.string.q11hint), R.raw.bgm1);
        q12 = new Question(getString(R.string.q12text), getString(R.string.q12Ans), getString(R.string.q12hint), R.raw.bgm2);
        q13 = new Question(getString(R.string.q13text), getString(R.string.q13Ans), getString(R.string.q13hint), R.raw.bgm3);
        q14 = new Question(getString(R.string.q14text), getString(R.string.q14Ans), getString(R.string.q14hint), R.raw.bgm4);
        q15 = new Question(getString(R.string.q15text), getString(R.string.q15Ans), getString(R.string.q15hint), R.raw.bgm5);
        q16 = new Question(getString(R.string.q16text), getString(R.string.q16Ans), getString(R.string.q16hint), R.raw.bgm6);
        q17 = new Question(getString(R.string.q17text), getString(R.string.q17Ans), getString(R.string.q17hint), R.raw.bgm7);
        q18 = new Question(getString(R.string.q18text), getString(R.string.q18Ans), getString(R.string.q18hint), R.raw.bgm8);
        q19 = new Question(getString(R.string.q19text), getString(R.string.q19Ans), getString(R.string.q19hint), R.raw.bgm9);
        q20 = new Question(getString(R.string.q20text), getString(R.string.q20Ans), getString(R.string.q20hint), R.raw.bgm10);

        unusedQuestions = new ArrayList<Question>();
        toBeAnswered = new ArrayList<Question>();

        unusedQuestions.add(q1);
        unusedQuestions.add(q2);
        unusedQuestions.add(q3);
        unusedQuestions.add(q4);
        unusedQuestions.add(q5);
        unusedQuestions.add(q6);
        unusedQuestions.add(q7);
        unusedQuestions.add(q8);
        unusedQuestions.add(q9);
        unusedQuestions.add(q10);
        unusedQuestions.add(q11);
        unusedQuestions.add(q12);
        unusedQuestions.add(q13);
        unusedQuestions.add(q14);
        unusedQuestions.add(q15);
        unusedQuestions.add(q16);
        unusedQuestions.add(q17);
        unusedQuestions.add(q18);
        unusedQuestions.add(q19);
        unusedQuestions.add(q20);

        currentIndex = (int) (Math.random()*20);
        currentQ = unusedQuestions.get(currentIndex);
        questionTV.setText(currentQ.getQText());
        ibeforetotal++;

        if(theme.equals("gloomy")) {
            mainbackgroundLL.setBackgroundColor(getResources().getColor(R.color.gloomy_bg));
            qtextbgLL.setBackgroundColor(getResources().getColor(R.color.gloomy_textbg));
            questionTV.setTextColor(getResources().getColor(R.color.gloomy_qtextcolor));
            answerET.setHintTextColor(getResources().getColor(R.color.gloomy_edittext));
            hintBTN.setBackgroundColor(getResources().getColor(R.color.gloomy_textbg));
            enterBTN.setBackgroundColor(getResources().getColor(R.color.gloomy_textbg));
        }
        if(theme.equals("riddle")) {
            mainbackgroundLL.setBackgroundColor(getResources().getColor(R.color.riddle_bg));
            qtextbgLL.setBackgroundColor(getResources().getColor(R.color.riddle_textbg));
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
                    Toast t = Toast.makeText(getApplicationContext(), getString(R.string.wrongMsg) + " " + currentQ.getCorrectAnswer(), duration);
                    t.show();
                }

                toBeAnswered.add(unusedQuestions.get(currentIndex));
                unusedQuestions.remove(currentIndex);
                currentIndex = (int) (Math.random() * unusedQuestions.size());

                if(ibeforetotal < totalQs) {
                    toBePlayed.release();
                    toBePlayed = MediaPlayer.create(MainActivity.this, currentQ.getBGM());

                    currentQ = unusedQuestions.get(currentIndex);
                    questionTV.setText(currentQ.getQText());
                    answerET.setText("");
                    ibeforetotal++;
                    //Log.d("questionnum", "We are on question number  " + ibeforetotal + " out of " + totalQs);
                }
                else {
                    Intent myIntent = new Intent(MainActivity.this, ScoreActivity.class);
                    myIntent.putExtra("score", score);
                    myIntent.putExtra("totalQs", totalQs);

                    startActivity(myIntent);
                }
                toBePlayed.start();
            }
        });

        hintBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHint = currentQ.getHint();
                int duration = Toast.LENGTH_SHORT;
                Toast t = Toast.makeText(getApplicationContext(), showHint, duration);
                t.show();
            }
        });
    }
}