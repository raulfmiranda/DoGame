package com.blogspot.raulfmiranda.dogame.entity;

import java.util.List;

public interface Model {
    Dog getRandomDog();
    List<String> getBreeds();
    void updateScore(int score);
    User getCurrentUser();
    List<User> getUsers();
    List<String> getDogImagesByBreed(String breed);
}
