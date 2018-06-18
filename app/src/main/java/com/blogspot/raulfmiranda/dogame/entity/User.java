package com.blogspot.raulfmiranda.dogame.entity;

public class User {

    public static final String TABELA = "users";

    private String id;
    private String name;
    private int score;

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
}
