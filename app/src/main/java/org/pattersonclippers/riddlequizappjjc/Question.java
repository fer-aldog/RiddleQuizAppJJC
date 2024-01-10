package org.pattersonclippers.riddlequizappjjc;
public class Question {

    private String qText, correctAnswer, hint;
    private int bgm;

    public Question() {
        qText = "Mreow mreow mreow mreow?";
        correctAnswer = "Mreow.";
        bgm = R.raw.bgm1;
    }
    public Question(String newQText, String newCorrectAnswer, String newHint, int newBGM) {
        qText = newQText;
        correctAnswer = newCorrectAnswer;
        hint = newHint;
        bgm = newBGM;
    }

    public String getQText() { return qText; }
    public String getCorrectAnswer() { return correctAnswer; }
    public String getHint() {return hint; }
    public int getBGM() {return bgm; }
    public void setQText(String newQText) { qText = newQText; }
    public void setCorrectAnswer(String newAnswer) { correctAnswer = newAnswer; }
    public void setHint(String newHint) { hint = newHint; }
    public void setBGM(int newMusic) { bgm = newMusic; }

    @Override
    public String toString() {
        return "Question: " + qText + "\nAnswer: " + correctAnswer + "\nHint: " + hint;
    }
}
