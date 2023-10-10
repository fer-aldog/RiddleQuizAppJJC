package org.pattersonclippers.riddlequizappjjc;

public class Question {

    // instance variables
    private String qText;
    private String correctAnswer;

    //default constructor
    public Question() {
        qText = "Mreow mreow mreow mreow?";
        correctAnswer = "Mreow.";
    }

    //pass-through constructor
    public Question(String newQText, String newCorrectAnswer) {
        qText = newQText;
        correctAnswer = newCorrectAnswer;
    }

    //accessors
    public String getQText() { return qText; }

    public String getCorrectAnswer() { return correctAnswer; }

    //mutators
    public void setQText(String newQText) { qText = newQText; }

    public void setCorrectAnswer(String newAnswer) { correctAnswer = newAnswer; }

    //toString
    @Override
    public String toString() {
        return "Question: " + qText + "\nAnswer: " + correctAnswer;
    }
}
