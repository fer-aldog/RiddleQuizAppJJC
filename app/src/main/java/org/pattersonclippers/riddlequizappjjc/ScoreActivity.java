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
    TextView greetingTV, compareTV, scoreTV, hintsUsedTV, prevScoreTV, prevHintsTV;
    int score, hintsUsed;
    Button coolBTN, highscoreBTN;
    String theme, initialName;
    LinearLayout scorebgLL, greetingLL, yourcountLL,yourscoreLL, yourhintLL, compareLL,
            othercountLL, otherscoreLL, otherhintLL;

    private SharedPreferences mySharedPreferences;
    SharedPreferences.Editor preferencesEditor;
    private String spFilename = "org.pattersonclippers.riddlequizappjjc.QuizScore";
    private final String HINT_KEY = "hintsUsed";
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
        hintsUsedTV = (TextView) findViewById(R.id.hintsUsedTV);
        prevScoreTV = (TextView) findViewById(R.id.prevScoreTV);
        prevHintsTV = (TextView) findViewById(R.id.prevHintsTV);
        coolBTN = (Button) findViewById(R.id.coolBTN);
        highscoreBTN = (Button) findViewById(R.id.highscoreBTN);

        scorebgLL = (LinearLayout) findViewById(R.id.scorebgLL);
        greetingLL = (LinearLayout) findViewById(R.id.greetingLL);
        yourcountLL = (LinearLayout) findViewById(R.id.yourcountLL);
        yourscoreLL = (LinearLayout) findViewById(R.id.yourscoreLL);
        yourhintLL = (LinearLayout) findViewById(R.id.yourhintLL);
        compareLL = (LinearLayout) findViewById(R.id.compareLL);
        othercountLL = (LinearLayout) findViewById(R.id.othercountLL);
        otherscoreLL = (LinearLayout) findViewById(R.id.otherscoreLL);
        otherhintLL = (LinearLayout) findViewById(R.id.otherhintLL);

        incomingIntent = getIntent();
        score = incomingIntent.getIntExtra("score", 0);
        hintsUsed = incomingIntent.getIntExtra("hintsUsed", 0);
        scoreTV.setText("" + score);
        hintsUsedTV.setText("" + hintsUsed);

        //read initial value for color from last time
        int initialScore = mySharedPreferences.getInt(SCORE_KEY, 0);
        int initialHints = mySharedPreferences.getInt(HINT_KEY, 0);

        prevScoreTV.setText("" + initialScore);
        prevHintsTV.setText("" + initialHints);

        preferencesEditor.putInt(SCORE_KEY, score);
        preferencesEditor.putInt(HINT_KEY, hintsUsed);
        preferencesEditor.apply();

        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("highscores");


        /*if(theme.equals("gloomy")) {
            scorebgLL.setBackgroundColor(getResources().getColor(R.color.gloomy_scorebg));
            greetingLL.setBackgroundColor(getResources().getColor(R.color.gloomy_textbg));
            yourcountLL.setBackgroundColor(getResources().getColor(R.color.gloomy_textbg));
            yourscoreLL.setBackgroundColor(getResources().getColor(R.color.gloomy_countwindow));
            yourhintLL.setBackgroundColor(getResources().getColor(R.color.gloomy_countwindow));
            compareLL.setBackgroundColor(getResources().getColor(R.color.gloomy_textbg));
            othercountLL.setBackgroundColor(getResources().getColor(R.color.gloomy_textbg));
            otherscoreLL.setBackgroundColor(getResources().getColor(R.color.gloomy_countwindow));
            otherhintLL.setBackgroundColor(getResources().getColor(R.color.gloomy_countwindow));
            greetingTV.setTextColor(getResources().getColor(R.color.gloomy_textcolor));
            compareTV.setTextColor(getResources().getColor(R.color.gloomy_textcolor));

        }
        if(theme.equals("riddle")) {
            mainbackgroundLL.setBackgroundColor(getResources().getColor(R.color.riddle_bg));
            qtextbgLL.setBackgroundColor(getResources().getColor(R.color.riddle_questionbg));
            questionTV.setTextColor(getResources().getColor(R.color.riddle_qtextcolor));
            answerET.setHintTextColor(getResources().getColor(R.color.riddle_edittext));
            hintBTN.setBackgroundColor(getResources().getColor(R.color.riddle_hintbtn));
            enterBTN.setBackgroundColor(getResources().getColor(R.color.riddle_enterbtn));
        }*/

        coolBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (score > 5) {
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
                myHighScore = new HighScore(initialName, score);
                String key = myRef.push().getKey();
                myRef.child(key).setValue(myHighScore);
                Intent myIntent = new Intent(ScoreActivity.this, HighScoresActivity.class);
                startActivity(myIntent);
            }
        });
    }
}