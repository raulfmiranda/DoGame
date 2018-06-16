package com.blogspot.raulfmiranda.dogame;

import com.blogspot.raulfmiranda.dogame.entity.Dog;
import com.blogspot.raulfmiranda.dogame.entity.User;

import java.util.List;

public interface DogameMVP {
    public interface View {}
    public interface Presenter {}

    public interface Model {
        Dog getRandomDog();
        List<String> getBreeds();
        void updateScore(int score);
        User getCurrentUser();
        List<User> getUsers();
        List<String> getDogImagesByBreed(String breed);
    }
}
