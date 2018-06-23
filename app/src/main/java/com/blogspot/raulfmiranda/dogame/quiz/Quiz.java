package com.blogspot.raulfmiranda.dogame.quiz;

import com.blogspot.raulfmiranda.dogame.entity.Dog;
import com.blogspot.raulfmiranda.dogame.entity.User;

import java.util.List;

public interface Quiz {
    interface View {
        void randomDogSuccessfullyRetrieved(Dog dog, List<String> breeds);
    }
    interface Presenter {
        void requestRandomDog();
        void randomDogSuccessfullyRetrieved(Dog dog);
        void updateScore(String checkedAnswer);
        int getScore();
    }
}
