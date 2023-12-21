package org.pattersonclippers.riddlequizappjjc;
public class Question {

    private String qText, correctAnswer;
    private int bgm;

    public Question() {
        qText = "Mreow mreow mreow mreow?";
        correctAnswer = "Mreow.";
        bgm = R.raw.bgm1;
    }
    public Question(String newQText, String newCorrectAnswer, int newBGM) {
        qText = newQText;
        correctAnswer = newCorrectAnswer;
        bgm = newBGM;
    }

    public String getQText() { return qText; }
    public String getCorrectAnswer() { return correctAnswer; }
    public int getBGM() {return bgm; }
    public void setQText(String newQText) { qText = newQText; }
    public void setCorrectAnswer(String newAnswer) { correctAnswer = newAnswer; }
    public void setBGM(int newMusic) { bgm = newMusic; }

    @Override
    public String toString() {
        return "Question: " + qText + "\nAnswer: " + correctAnswer;
    }
}
