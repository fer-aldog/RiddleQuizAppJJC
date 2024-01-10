package org.pattersonclippers.riddlequizappjjc;

import android.util.Log;

public class HighScore implements Comparable<HighScore>{
    private String name;
    private int score, totalQs;

    public HighScore() {
        name = "your mother";
        score = 0;
        totalQs = 20;
    }
    public HighScore(String name, int score, int totalQs) {
        this.name = name;
        this.score = score;
        this.totalQs = totalQs;
    }

    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }

    public int getTotalQs() { return totalQs; }

    public int getPercent() {
        double percent = (score / (double) totalQs) * 100;
        Log.d("findpercent", "percent is: " + percent);
        return (int) percent;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setTotalQs(int totalQs) { this.totalQs = totalQs; }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "HighScore{" + "name='" + name + '\'' + ", score=" + score + '}';
    }
    @Override
    public int compareTo(HighScore otherHighScore) {
        int compareQuantity = ((HighScore) otherHighScore).getPercent();
        return compareQuantity - this.getPercent();
    }
}
