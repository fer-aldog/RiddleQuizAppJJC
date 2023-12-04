package org.pattersonclippers.riddlequizappjjc;

import static android.content.Intent.EXTRA_TEXT;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    Button saveBTN, startBTN, riddlethemeBTN, gloomythemeBTN;
    String initialName;
    int initialColor;
    TextView greetingTV;
    EditText usernameET;
    LinearLayout backgroundLL;
    private SharedPreferences mySharedPreferences;
    SharedPreferences.Editor preferencesEditor;
    private String spFilename = "org.pattersonclippers.sharedpreferencesjjc";
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
        initialColor = mySharedPreferences.getInt(COLOR_KEY, 0);
        initialName = mySharedPreferences.getString(UN_KEY, "player");

        saveBTN = (Button) findViewById(R.id.saveBTN);
        startBTN = (Button) findViewById(R.id.startBTN);
        riddlethemeBTN = (Button) findViewById(R.id.riddlethemeBTN);
        gloomythemeBTN = (Button) findViewById(R.id.gloomythemeBTN);
        backgroundLL = (LinearLayout) findViewById(R.id.backgroundLL);
        greetingTV = (TextView) findViewById(R.id.greetingTV);
        usernameET = (EditText) findViewById(R.id.usernameET);

        //shows the color
        if(initialColor == R.color.riddle_bg){
            backgroundLL.setBackgroundColor(getResources().getColor(R.color.riddle_bg));
            greetingTV.setTextColor(getResources().getColor(R.color.riddle_qtextcolor));
            startBTN.setBackgroundColor(getResources().getColor(R.color.riddle_hintbtn));
            startBTN.setTextColor(getResources().getColor(R.color.def_txt));
            saveBTN.setBackgroundColor(getResources().getColor(R.color.riddle_hintbtn));
            saveBTN.setTextColor(getResources().getColor(R.color.def_txt));
            usernameET.setHintTextColor(getResources().getColor(R.color.riddle_edittext));
        } else if (initialColor == R.color.gloomy_bg) {
            backgroundLL.setBackgroundColor(getResources().getColor(R.color.gloomy_bg));
            greetingTV.setTextColor(getResources().getColor(R.color.gloomy_questionbg));
            startBTN.setBackgroundColor(getResources().getColor(R.color.gloomy_hintbtn));
            startBTN.setTextColor(getResources().getColor(R.color.def_txt));
            saveBTN.setBackgroundColor(getResources().getColor(R.color.gloomy_hintbtn));
            saveBTN.setTextColor(getResources().getColor(R.color.def_txt));
            usernameET.setHintTextColor(getResources().getColor(R.color.gloomy_enterbtn));
        } else {
            backgroundLL.setBackgroundColor(getResources().getColor(R.color.def_bg));
            greetingTV.setTextColor(getResources().getColor(R.color.def_txt));
            startBTN.setBackgroundColor(getResources().getColor(R.color.def_btn));
            startBTN.setTextColor(getResources().getColor(R.color.def_btntxt));
            saveBTN.setBackgroundColor(getResources().getColor(R.color.def_btn));
            saveBTN.setTextColor(getResources().getColor(R.color.def_btntxt));
            usernameET.setHintTextColor(getResources().getColor(R.color.def_hint));
        }

        greetingTV.setText("Welcome, " + initialName + "!\nPress the start button to begin." +
                "\nYou can also change the color\nscheme of this game.");

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
                String theme = "riddle";
                preferencesEditor.putString(COLOR_KEY, theme);
                preferencesEditor.apply();
            }
        });

        gloomythemeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backgroundLL.setBackgroundColor(getResources().getColor(R.color.gloomy_bg));
                greetingTV.setTextColor(getResources().getColor(R.color.gloomy_questionbg));
                startBTN.setBackgroundColor(getResources().getColor(R.color.gloomy_hintbtn));
                startBTN.setTextColor(getResources().getColor(R.color.def_txt));
                saveBTN.setBackgroundColor(getResources().getColor(R.color.gloomy_hintbtn));
                saveBTN.setTextColor(getResources().getColor(R.color.def_txt));
                usernameET.setHintTextColor(getResources().getColor(R.color.gloomy_enterbtn));
                String theme = "gloomy";
                preferencesEditor.putString(COLOR_KEY, theme);
                preferencesEditor.apply();
            }
        });

        saveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initialName = usernameET.getText().toString();
                preferencesEditor.putString(UN_KEY, initialName);
                preferencesEditor.apply();
                greetingTV.setText("Welcome, " + initialName + "!\nPress the start button to begin." +
                        "\nYou can also change the color\nscheme of this game.");
            }
        });

        startBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });


    }
}