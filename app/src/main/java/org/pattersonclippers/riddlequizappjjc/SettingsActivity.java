package org.pattersonclippers.riddlequizappjjc;

import static android.content.Intent.EXTRA_TEXT;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    Button saveBTN, startBTN, riddlethemeBTN, gloomythemeBTN, q5sBTN, q10sBTN, q15sBTN, q20sBTN;
    String initialName, theme;
    int totalQs;
    TextView greetingTV;
    EditText usernameET;
    LinearLayout backgroundLL;
    private SharedPreferences mySharedPreferences;
    SharedPreferences.Editor preferencesEditor;
    private String spFilename = "org.pattersonclippers.riddlequizappjjc.QuizScore";
    private final String COLOR_KEY = "color";
    private final String UN_KEY = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //initialise shared preferences
        mySharedPreferences = getSharedPreferences(spFilename, MODE_PRIVATE);
        preferencesEditor = mySharedPreferences.edit();

        //read initial value for color from last time
        theme = mySharedPreferences.getString(COLOR_KEY, "default");
        initialName = mySharedPreferences.getString(UN_KEY, "player");

        saveBTN = (Button) findViewById(R.id.saveBTN);
        startBTN = (Button) findViewById(R.id.startBTN);
        riddlethemeBTN = (Button) findViewById(R.id.riddlethemeBTN);
        gloomythemeBTN = (Button) findViewById(R.id.gloomythemeBTN);
        q5sBTN = (Button) findViewById(R.id.q5sBTN);
        q10sBTN = (Button) findViewById(R.id.q10sBTN);
        q15sBTN = (Button) findViewById(R.id.q15sBTN);
        q20sBTN = (Button) findViewById(R.id.q20sBTN);
        backgroundLL = (LinearLayout) findViewById(R.id.backgroundLL);
        greetingTV = (TextView) findViewById(R.id.greetingTV);
        usernameET = (EditText) findViewById(R.id.usernameET);
        totalQs = 5;

        //shows the color
        if(theme.equals("riddle")){
            backgroundLL.setBackgroundColor(getResources().getColor(R.color.riddle_bg));
            greetingTV.setTextColor(getResources().getColor(R.color.riddle_qtextcolor));
            startBTN.setBackgroundColor(getResources().getColor(R.color.riddle_hintbtn));
            startBTN.setTextColor(getResources().getColor(R.color.def_txt));
            q5sBTN.setBackgroundColor(getResources().getColor(R.color.riddle_hintbtn));
            q5sBTN.setTextColor(getResources().getColor(R.color.def_txt));
            q10sBTN.setBackgroundColor(getResources().getColor(R.color.riddle_hintbtn));
            q10sBTN.setTextColor(getResources().getColor(R.color.def_txt));
            q15sBTN.setBackgroundColor(getResources().getColor(R.color.riddle_hintbtn));
            q15sBTN.setTextColor(getResources().getColor(R.color.def_txt));
            q20sBTN.setBackgroundColor(getResources().getColor(R.color.riddle_hintbtn));
            q20sBTN.setTextColor(getResources().getColor(R.color.def_txt));
            saveBTN.setBackgroundColor(getResources().getColor(R.color.riddle_hintbtn));
            saveBTN.setTextColor(getResources().getColor(R.color.def_txt));
            usernameET.setHintTextColor(getResources().getColor(R.color.riddle_edittext));
        } else if (theme.equals("gloomy")) {
            backgroundLL.setBackgroundColor(getResources().getColor(R.color.gloomy_bg));
            greetingTV.setTextColor(getResources().getColor(R.color.gloomy_textbg));
            startBTN.setBackgroundColor(getResources().getColor(R.color.gloomy_textbg));
            startBTN.setTextColor(getResources().getColor(R.color.def_txt));
            q5sBTN.setBackgroundColor(getResources().getColor(R.color.gloomy_textbg));
            q5sBTN.setTextColor(getResources().getColor(R.color.def_txt));
            q10sBTN.setBackgroundColor(getResources().getColor(R.color.gloomy_textbg));
            q10sBTN.setTextColor(getResources().getColor(R.color.def_txt));
            q15sBTN.setBackgroundColor(getResources().getColor(R.color.gloomy_textbg));
            q15sBTN.setTextColor(getResources().getColor(R.color.def_txt));
            q20sBTN.setBackgroundColor(getResources().getColor(R.color.gloomy_textbg));
            q20sBTN.setTextColor(getResources().getColor(R.color.def_txt));
            saveBTN.setBackgroundColor(getResources().getColor(R.color.gloomy_textbg));
            saveBTN.setTextColor(getResources().getColor(R.color.def_txt));
            usernameET.setHintTextColor(getResources().getColor(R.color.gloomy_textbg));
        } else {
            backgroundLL.setBackgroundColor(getResources().getColor(R.color.def_bg));
            greetingTV.setTextColor(getResources().getColor(R.color.def_txt));
            startBTN.setBackgroundColor(getResources().getColor(R.color.def_btn));
            startBTN.setTextColor(getResources().getColor(R.color.def_btntxt));
            q5sBTN.setBackgroundColor(getResources().getColor(R.color.def_btn));
            q5sBTN.setTextColor(getResources().getColor(R.color.def_txt));
            q10sBTN.setBackgroundColor(getResources().getColor(R.color.def_btn));
            q10sBTN.setTextColor(getResources().getColor(R.color.def_txt));
            q15sBTN.setBackgroundColor(getResources().getColor(R.color.def_btn));
            q15sBTN.setTextColor(getResources().getColor(R.color.def_txt));
            q20sBTN.setBackgroundColor(getResources().getColor(R.color.def_btn));
            q20sBTN.setTextColor(getResources().getColor(R.color.def_txt));
            saveBTN.setBackgroundColor(getResources().getColor(R.color.def_btn));
            saveBTN.setTextColor(getResources().getColor(R.color.def_btntxt));
            usernameET.setHintTextColor(getResources().getColor(R.color.def_hint));
        }

        greetingTV.setText(getString(R.string.menugreet1) + " " + initialName + getString(R.string.menugreet2));

        riddlethemeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backgroundLL.setBackgroundColor(getResources().getColor(R.color.riddle_bg));
                greetingTV.setTextColor(getResources().getColor(R.color.riddle_qtextcolor));
                startBTN.setBackgroundColor(getResources().getColor(R.color.riddle_hintbtn));
                startBTN.setTextColor(getResources().getColor(R.color.def_txt));
                saveBTN.setBackgroundColor(getResources().getColor(R.color.riddle_hintbtn));
                saveBTN.setTextColor(getResources().getColor(R.color.def_txt));
                usernameET.setHintTextColor(getResources().getColor(R.color.riddle_edittext));
                q5sBTN.setBackgroundColor(getResources().getColor(R.color.riddle_hintbtn));
                q5sBTN.setTextColor(getResources().getColor(R.color.def_txt));
                q10sBTN.setBackgroundColor(getResources().getColor(R.color.riddle_hintbtn));
                q10sBTN.setTextColor(getResources().getColor(R.color.def_txt));
                q15sBTN.setBackgroundColor(getResources().getColor(R.color.riddle_hintbtn));
                q15sBTN.setTextColor(getResources().getColor(R.color.def_txt));
                q20sBTN.setBackgroundColor(getResources().getColor(R.color.riddle_hintbtn));
                q20sBTN.setTextColor(getResources().getColor(R.color.def_txt));
                theme = "riddle";
                preferencesEditor.putString(COLOR_KEY, theme);
                preferencesEditor.apply();
            }
        });

        gloomythemeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backgroundLL.setBackgroundColor(getResources().getColor(R.color.gloomy_bg));
                greetingTV.setTextColor(getResources().getColor(R.color.gloomy_textbg));
                startBTN.setBackgroundColor(getResources().getColor(R.color.gloomy_textbg));
                startBTN.setTextColor(getResources().getColor(R.color.def_txt));
                saveBTN.setBackgroundColor(getResources().getColor(R.color.gloomy_textbg));
                saveBTN.setTextColor(getResources().getColor(R.color.def_txt));
                usernameET.setHintTextColor(getResources().getColor(R.color.gloomy_textbg));
                q5sBTN.setBackgroundColor(getResources().getColor(R.color.gloomy_textbg));
                q5sBTN.setTextColor(getResources().getColor(R.color.def_txt));
                q10sBTN.setBackgroundColor(getResources().getColor(R.color.gloomy_textbg));
                q10sBTN.setTextColor(getResources().getColor(R.color.def_txt));
                q15sBTN.setBackgroundColor(getResources().getColor(R.color.gloomy_textbg));
                q15sBTN.setTextColor(getResources().getColor(R.color.def_txt));
                q20sBTN.setBackgroundColor(getResources().getColor(R.color.gloomy_textbg));
                q20sBTN.setTextColor(getResources().getColor(R.color.def_txt));
                theme = "gloomy";
                preferencesEditor.putString(COLOR_KEY, theme);
                preferencesEditor.apply();
            }
        });

        q5sBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalQs = 5;
                //Log.d("totalquestions", "the total questions are: " + totalQs);
            }
        });

        q10sBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalQs = 10;
                //Log.d("totalquestions", "the total questions are: " + totalQs);
            }
        });

        q15sBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalQs = 15;
                //Log.d("totalquestions", "the total questions are: " + totalQs);
            }
        });

        q20sBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalQs = 20;
                //Log.d("totalquestions", "the total questions are: " + totalQs);
            }
        });

        saveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initialName = usernameET.getText().toString();
                preferencesEditor.putString(UN_KEY, initialName);
                preferencesEditor.apply();
                greetingTV.setText(getString(R.string.menugreet1) + " " + initialName + getString(R.string.menugreet2));;
            }
        });

        startBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(SettingsActivity.this, MainActivity.class);
                myIntent.putExtra("totalQs", totalQs);
                //Log.d("savedtotal", "the final total is: " + totalQs);
                startActivity(myIntent);
            }
        });
    }
}