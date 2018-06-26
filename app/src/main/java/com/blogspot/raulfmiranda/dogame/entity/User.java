package com.blogspot.raulfmiranda.dogame.entity;

import java.util.Objects;

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
