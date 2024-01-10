package org.pattersonclippers.riddlequizappjjc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class HighScoresActivity extends AppCompatActivity {

    Intent incomingIntent;
    int totalQs;

    TextView highScore1TV, highScore2TV, highScore3TV, highScore4TV, highScore5TV,
            theScorer1TV, theScorer2TV, theScorer3TV, theScorer4TV, theScorer5TV;
    LinearLayout highscoreLL, titlebgLL, top5LL;
    String theme, initialName;

    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<HighScore> allHighScores;
    private int currentIndex;
    final String TAG = "SCORES_TAG";
    private SharedPreferences mySharedPreferences;
    private String spFilename = "org.pattersonclippers.riddlequizappjjc.QuizScore";
    private final String COLOR_KEY = "color";
    private final String UN_KEY = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        //initialise shared preferences
        mySharedPreferences = getSharedPreferences(spFilename, MODE_PRIVATE);

        //read initial value for color from last time
        theme = mySharedPreferences.getString(COLOR_KEY, "riddle");
        initialName = mySharedPreferences.getString(UN_KEY, "player");

        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("highscores");

        allHighScores = new ArrayList<HighScore>();

        incomingIntent = getIntent();
        totalQs = incomingIntent.getIntExtra("totalQs", 0);

        highscoreLL = (LinearLayout) findViewById(R.id.highscoreLL);
        titlebgLL = (LinearLayout) findViewById(R.id.titlebgLL);
        top5LL = (LinearLayout) findViewById(R.id.top5LL);

        highScore1TV = (TextView) findViewById(R.id.highScore1TV);
        highScore2TV = (TextView) findViewById(R.id.highScore2TV);
        highScore3TV = (TextView) findViewById(R.id.highScore3TV);
        highScore4TV = (TextView) findViewById(R.id.highScore4TV);
        highScore5TV = (TextView) findViewById(R.id.highScore5TV);
        theScorer1TV = (TextView) findViewById(R.id.theScorer1TV);
        theScorer2TV = (TextView) findViewById(R.id.theScorer2TV);
        theScorer3TV = (TextView) findViewById(R.id.theScorer3TV);
        theScorer4TV = (TextView) findViewById(R.id.theScorer4TV);
        theScorer5TV = (TextView) findViewById(R.id.theScorer5TV);

        if(theme.equals("gloomy")) {
            highscoreLL.setBackgroundColor(getResources().getColor(R.color.gloomy_bg));
            titlebgLL.setBackgroundColor(getResources().getColor(R.color.gloomy_textbg));
            top5LL.setBackgroundColor(getResources().getColor(R.color.gloomy_textbg));
        }
        if(theme.equals("riddle")) {
            highscoreLL.setBackgroundColor(getResources().getColor(R.color.riddle_scorebg));
            titlebgLL.setBackgroundColor(getResources().getColor(R.color.riddle_textbg));
            top5LL.setBackgroundColor(getResources().getColor(R.color.riddle_textbg));
        }

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // Iterate through all the children in the snapshot, this should be
                // all the children in the "highscore" object
                for (DataSnapshot highscoreSnapShot : snapshot.getChildren()) {

                    //From our snapshot, get the value of our key/value pair. This value
                    //contains a highscore object
                    HighScore myHighScore = highscoreSnapShot.getValue(HighScore.class);

                    Log.d("onDataChange()", myHighScore.toString());
                    allHighScores.add(myHighScore);
                }


                Collections.sort(allHighScores);

                for(HighScore temp: allHighScores) {
                    Log.d("score sorting:3", temp.toString());
                }

                //Check if we have any players
                if (allHighScores.size() > 0) {
                    //Set the current index to 0, which is the first entry in the array
                    currentIndex = 0;
                    //Get the first player
                    HighScore firstPlayer = allHighScores.get(currentIndex);
                    theScorer1TV.setText(firstPlayer.getName());
                    highScore1TV.setText(firstPlayer.getPercent() + "%");

                }
                //Check if we have more than 1 player
                if (allHighScores.size() > 1) {
                    currentIndex++;
                    HighScore nextPlayer = allHighScores.get(currentIndex);
                    theScorer2TV.setText(nextPlayer.getName());
                    highScore2TV.setText(nextPlayer.getPercent() + "%");
                }
                if (allHighScores.size() > 2) {
                    currentIndex++;
                    HighScore nextPlayer = allHighScores.get(currentIndex);
                    theScorer3TV.setText(nextPlayer.getName());
                    highScore3TV.setText(nextPlayer.getPercent() + "%");
                }
                if (allHighScores.size() > 3) {
                    currentIndex++;
                    HighScore nextPlayer = allHighScores.get(currentIndex);
                    theScorer4TV.setText(nextPlayer.getName());
                    highScore4TV.setText(nextPlayer.getPercent() + "%");
                }
                if (allHighScores.size() > 4) {
                    currentIndex++;
                    HighScore nextPlayer = allHighScores.get(currentIndex);
                    theScorer5TV.setText(nextPlayer.getName());
                    highScore5TV.setText(nextPlayer.getPercent() + "%");
                }

            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}