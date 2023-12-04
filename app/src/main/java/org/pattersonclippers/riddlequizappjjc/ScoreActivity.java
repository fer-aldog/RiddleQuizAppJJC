package org.pattersonclippers.riddlequizappjjc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class ScoreActivity extends AppCompatActivity {

    Intent incomingIntent;
    TextView scoreTV, hintsUsedTV, prevScoreTV, prevHintsTV;
    int score, hintsUsed;
    Button coolBTN;

    private SharedPreferences mySharedPreferences;
    SharedPreferences.Editor preferencesEditor;
    private String spFilename = "org.pattersonclippers.riddlequizappjjc.QuizScore";
    private final String HINT_KEY = "hintsUsed";
    private final String SCORE_KEY = "score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        //initialise shared preferences
        mySharedPreferences = getSharedPreferences(spFilename, MODE_PRIVATE);
        preferencesEditor = mySharedPreferences.edit();

        scoreTV = (TextView) findViewById(R.id.scoreTV);
        hintsUsedTV = (TextView) findViewById(R.id.hintsUsedTV);
        prevScoreTV = (TextView) findViewById(R.id.prevScoreTV);
        prevHintsTV = (TextView) findViewById(R.id.prevHintsTV);
        coolBTN = (Button) findViewById(R.id.coolBTN);

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
    }
}