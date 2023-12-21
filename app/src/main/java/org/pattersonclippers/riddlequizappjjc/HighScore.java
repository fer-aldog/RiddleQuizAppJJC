package org.pattersonclippers.riddlequizappjjc;

public class HighScore implements Comparable<HighScore>{
    private String name;
    private int score;

    public HighScore() {
        name = "your mother";
        score = 0;
    }
    public HighScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "HighScore{" + "name='" + name + '\'' + ", score=" + score + '}';
    }
    @Override
    public int compareTo(HighScore otherHighScore) {
        int compareQuantity = ((HighScore) otherHighScore).getScore();
        return compareQuantity - this.score;
    }
}
