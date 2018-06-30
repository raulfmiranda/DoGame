package com.blogspot.raulfmiranda.dogame.quiz;

import com.blogspot.raulfmiranda.dogame.entity.Dog;
import com.blogspot.raulfmiranda.dogame.entity.User;

import java.util.List;

public interface Quiz {
    interface View {
        void randomDogSuccessfullyRetrieved(Dog dog, List<String> breeds);
        void setTime(int time);
        void blinkScore();
        void showProgress();
        void hideProgress();
    }
    interface Presenter {
        void requestRandomDog();
        void updateScore(QuizChoice quizChoice, String checkedAnswer);
        int getScore();
        void fetchScore();
        void pushScore();
        void startTimer();
        void stopTimer();
    }
}
