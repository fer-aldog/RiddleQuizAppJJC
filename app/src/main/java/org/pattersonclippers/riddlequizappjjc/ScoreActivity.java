package org.pattersonclippers.riddlequizappjjc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    }
}