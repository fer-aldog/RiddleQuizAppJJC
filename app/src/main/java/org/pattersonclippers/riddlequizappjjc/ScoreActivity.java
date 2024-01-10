package org.pattersonclippers.riddlequizappjjc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ScoreActivity extends AppCompatActivity {

    Intent incomingIntent;
    TextView greetingTV, compareTV, scoreTV, totalQsTV, prevScoreTV, prevtotalQsTV;
    int score, totalQs;
    Button coolBTN, highscoreBTN, restartBTN;
    String theme, initialName;
    LinearLayout scorebgLL, greetingLL, yourscoreLL, yourtotalLL, compareLL,
            otherscoreLL, othertotalLL;

    private SharedPreferences mySharedPreferences;
    SharedPreferences.Editor preferencesEditor;
    private String spFilename = "org.pattersonclippers.riddlequizappjjc.QuizScore";
    private final String TOTAL_KEY = "totalQs";
    private final String SCORE_KEY = "score";
    FirebaseDatabase database;
    DatabaseReference myRef;
    HighScore myHighScore;
    final String TAG = "SCORES_TAG";
    private final String COLOR_KEY = "color";
    private final String UN_KEY = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        //initialise shared preferences
        mySharedPreferences = getSharedPreferences(spFilename, MODE_PRIVATE);
        preferencesEditor = mySharedPreferences.edit();

        //read initial value for color from last time
        theme = mySharedPreferences.getString(COLOR_KEY, "riddle");
        initialName = mySharedPreferences.getString(UN_KEY, "player");
        Log.d("aaaaaaa", initialName);

        greetingTV = (TextView) findViewById(R.id.greetingTV);
        compareTV = (TextView) findViewById(R.id.compareTV);
        scoreTV = (TextView) findViewById(R.id.scoreTV);
        totalQsTV = (TextView) findViewById(R.id.totalQsTV);
        prevScoreTV = (TextView) findViewById(R.id.prevScoreTV);
        prevtotalQsTV = (TextView) findViewById(R.id.prevtotalQsTV);
        coolBTN = (Button) findViewById(R.id.coolBTN);
        highscoreBTN = (Button) findViewById(R.id.highscoreBTN);
        restartBTN = (Button) findViewById(R.id.restartBTN);

        scorebgLL = (LinearLayout) findViewById(R.id.scorebgLL);
        greetingLL = (LinearLayout) findViewById(R.id.greetingLL);
        yourscoreLL = (LinearLayout) findViewById(R.id.yourscoreLL);
        yourtotalLL = (LinearLayout) findViewById(R.id.yourtotalLL);
        compareLL = (LinearLayout) findViewById(R.id.compareLL);
        otherscoreLL = (LinearLayout) findViewById(R.id.otherscoreLL);
        othertotalLL = (LinearLayout) findViewById(R.id.othertotalLL);

        incomingIntent = getIntent();
        score = incomingIntent.getIntExtra("score", 0);
        totalQs = incomingIntent.getIntExtra("totalQs", 0);
        scoreTV.setText("" + score);
        totalQsTV.setText("" + totalQs);
        greetingTV.setText(getString(R.string.scoregreet1) + initialName + getString(R.string.scoregreet2));

        //read initial value for color from last time
        int initialScore = mySharedPreferences.getInt(SCORE_KEY, 0);
        int initialHints = mySharedPreferences.getInt(TOTAL_KEY, 0);

        prevScoreTV.setText("" + initialScore);
        prevtotalQsTV.setText("" + initialHints);

        preferencesEditor.putInt(SCORE_KEY, score);
        preferencesEditor.putInt(TOTAL_KEY, totalQs);
        preferencesEditor.apply();

        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("highscores");

        if(theme.equals("gloomy")) {
            scorebgLL.setBackgroundColor(getResources().getColor(R.color.gloomy_bg));
            greetingLL.setBackgroundColor(getResources().getColor(R.color.gloomy_textbg));
            compareLL.setBackgroundColor(getResources().getColor(R.color.gloomy_textbg));

            yourscoreLL.setBackgroundColor(getResources().getColor(R.color.gloomy_textbg));
            yourtotalLL.setBackgroundColor(getResources().getColor(R.color.gloomy_textbg));
            otherscoreLL.setBackgroundColor(getResources().getColor(R.color.gloomy_textbg));
            othertotalLL.setBackgroundColor(getResources().getColor(R.color.gloomy_textbg));

            coolBTN.setBackgroundColor(getResources().getColor(R.color.gloomy_textbg));
            highscoreBTN.setBackgroundColor(getResources().getColor(R.color.gloomy_textbg));
            restartBTN.setBackgroundColor(getResources().getColor(R.color.gloomy_textbg));
        }
        if(theme.equals("riddle")) {
            scorebgLL.setBackgroundColor(getResources().getColor(R.color.riddle_scorebg));
            greetingLL.setBackgroundColor(getResources().getColor(R.color.riddle_textbg));
            compareLL.setBackgroundColor(getResources().getColor(R.color.riddle_textbg));

            yourscoreLL.setBackgroundColor(getResources().getColor(R.color.riddle_textbg));
            yourtotalLL.setBackgroundColor(getResources().getColor(R.color.riddle_textbg));
            otherscoreLL.setBackgroundColor(getResources().getColor(R.color.riddle_textbg));
            othertotalLL.setBackgroundColor(getResources().getColor(R.color.riddle_textbg));

            coolBTN.setBackgroundColor(getResources().getColor(R.color.riddle_textbg));
            highscoreBTN.setBackgroundColor(getResources().getColor(R.color.riddle_textbg));
            restartBTN.setBackgroundColor(getResources().getColor(R.color.riddle_textbg));
        }

        coolBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (score > totalQs / 2) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:"));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Riddle quiz score");
                    intent.putExtra(Intent.EXTRA_TEXT, "I got a score of " + score + " on the" +
                            " Riddle Quiz! This quiz was definitely fun to answer, and I think you" +
                            " should give the app a try too!\nDo you think you can beat my score?");
                    if (intent.resolveActivity(getPackageManager()) != null) { startActivity(intent); }
                } else {
                    Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                    intent.putExtra(SearchManager.QUERY, "Never back down never what");
                    if (intent.resolveActivity(getPackageManager()) != null) { startActivity(intent); }
                }
            }
        });

        highscoreBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myHighScore = new HighScore(initialName, score, totalQs);
                String key = myRef.push().getKey();
                myRef.child(key).setValue(myHighScore);
                Intent myIntent = new Intent(ScoreActivity.this, HighScoresActivity.class);
                myIntent.putExtra("totalQs", totalQs);
                startActivity(myIntent);
            }
        });

        restartBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ScoreActivity.this, SettingsActivity.class);
                startActivity(myIntent);
            }
        });
    }
}