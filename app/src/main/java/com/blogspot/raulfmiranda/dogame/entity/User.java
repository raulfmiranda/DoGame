package com.blogspot.raulfmiranda.dogame.entity;

import com.blogspot.raulfmiranda.dogame.ranking.Ranking;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;

public class User {

    public static final String TABELA = "users";

    private String id;
    private String name;
    private int score;
    private int month;
    private int scoreMonth;
    private int week;
    private int scoreWeek;
    private int day;
    private int scoreDay;

    public User() {
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        score = 0;
    }

    public void increaseScore(int increase) {
        Calendar today = Calendar.getInstance();
        score += increase;
        if (month != today.get(Calendar.MONTH) + 1) {
            scoreMonth = 0;
        }
        scoreMonth += increase;
        if (week != today.get(Calendar.WEEK_OF_YEAR)) {
            scoreWeek = 0;
        }
        scoreWeek += increase;
        if (day != today.get(Calendar.DAY_OF_YEAR)) {
            scoreDay = 0;
        }
        scoreDay += increase;
    }

    public void decreaseScore(int decrease) {
        Calendar today = Calendar.getInstance();
        score -= decrease;
        if (month != today.get(Calendar.MONTH) + 1) {
            scoreMonth = 0;
        }
        scoreMonth -= decrease;
        if (week != today.get(Calendar.WEEK_OF_YEAR)) {
            scoreWeek = 0;
        }
        scoreWeek -= decrease;
        if (day != today.get(Calendar.DAY_OF_YEAR)) {
            scoreDay = 0;
        }
        scoreDay -= decrease;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getScoreMonth() {
        return scoreMonth;
    }

    public void setScoreMonth(int scoreMonth) {
        this.scoreMonth = scoreMonth;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getScoreWeek() {
        return scoreWeek;
    }

    public void setScoreWeek(int scoreWeek) {
        this.scoreWeek = scoreWeek;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getScoreDay() {
        return scoreDay;
    }

    public void setScoreDay(int scoreDay) {
        this.scoreDay = scoreDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
