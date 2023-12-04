package org.pattersonclippers.riddlequizappjjc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HighScoresActivity extends AppCompatActivity {

    TextView highScore1TV, highScore2TV, highScore3TV, highScore4TV, highScore5TV,
            theScorer1TV, theScorer2TV, theScorer3TV, theScorer4TV, theScorer5TV;

    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<HighScore> allHighScores;
    private int currentIndex;
    final String TAG = "SCORES_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("highscores");

        allHighScores = new ArrayList<HighScore>();

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

                //Check if we have any customers
                if (allHighScores.size() > 0) {
                    //Set the current index to 0, which is the first entry in the array
                    currentIndex = 0;
                    //Get the first customer
                    HighScore firstCustomer = allHighScores.get(currentIndex);
                    theScorer1TV.setText(firstCustomer.getName());
                    highScore1TV.setText("" + firstCustomer.getScore());

                }
                //Check if we have more than 1 customer
                if (allHighScores.size() > 1) {
                    currentIndex++;
                    HighScore nextCustomer = allHighScores.get(currentIndex);
                    theScorer2TV.setText(nextCustomer.getName());
                    highScore2TV.setText("" + nextCustomer.getScore());
                }
                if (allHighScores.size() > 2) {
                    currentIndex++;
                    HighScore nextCustomer = allHighScores.get(currentIndex);
                    theScorer3TV.setText(nextCustomer.getName());
                    highScore3TV.setText("" + nextCustomer.getScore());
                }
                if (allHighScores.size() > 3) {
                    currentIndex++;
                    HighScore nextCustomer = allHighScores.get(currentIndex);
                    theScorer4TV.setText(nextCustomer.getName());
                    highScore4TV.setText("" + nextCustomer.getScore());
                }
                if (allHighScores.size() > 4) {
                    currentIndex++;
                    HighScore nextCustomer = allHighScores.get(currentIndex);
                    theScorer5TV.setText(nextCustomer.getName());
                    highScore5TV.setText("" + nextCustomer.getScore());
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