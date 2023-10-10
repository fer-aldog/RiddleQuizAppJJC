package org.pattersonclippers.riddlequizappjjc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    Intent incomingIntent;
    TextView scoreTV, hintsUsedTV;
    int score, hintsUsed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        scoreTV = (TextView) findViewById(R.id.scoreTV);
        hintsUsedTV = (TextView) findViewById(R.id.hintsUsedTV);

        incomingIntent = getIntent();
        score = incomingIntent.getIntExtra("score", 0);
        hintsUsed = incomingIntent.getIntExtra("hintsUsed", 0);
        scoreTV.setText("" + score);
        hintsUsedTV.setText("" + hintsUsed);

        if (score > 5) {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_SUBJECT, "Riddle quiz score");
            intent.putExtra(Intent.EXTRA_TEXT, "I got a score of " + score + " on the Riddle Quiz! This quiz was definitely fun to answer, and I think you should give the app a try too!\nDo you think you can beat my score?");
            if (intent.resolveActivity(getPackageManager()) != null) { startActivity(intent); }

        } else {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, "Never back down never what");
            if (intent.resolveActivity(getPackageManager()) != null) { startActivity(intent); }

        }

    }
}