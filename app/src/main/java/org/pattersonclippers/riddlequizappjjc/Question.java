package org.pattersonclippers.riddlequizappjjc;

import android.media.MediaPlayer;

public class Question {

    // instance variables
    private String qText;
    private String correctAnswer;
    private int bgm;

    //default constructor
    public Question() {
        qText = "Mreow mreow mreow mreow?";
        correctAnswer = "Mreow.";
        bgm = R.raw.bgm1;
    }

    //pass-through constructor
    public Question(String newQText, String newCorrectAnswer, int newBGM) {
        qText = newQText;
        correctAnswer = newCorrectAnswer;
        bgm = newBGM;
    }

    //accessors
    public String getQText() { return qText; }

    public String getCorrectAnswer() { return correctAnswer; }

    public int getBGM() {return bgm; }

    //mutators
    public void setQText(String newQText) { qText = newQText; }

    public void setCorrectAnswer(String newAnswer) { correctAnswer = newAnswer; }

    public void setBGM(int newMusic) { bgm = newMusic; }


    //toString
    @Override
    public String toString() {
        return "Question: " + qText + "\nAnswer: " + correctAnswer;
    }
}
