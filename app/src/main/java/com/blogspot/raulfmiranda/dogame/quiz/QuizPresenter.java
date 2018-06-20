package com.blogspot.raulfmiranda.dogame.quiz;

import android.os.Looper;
import android.util.Log;

import com.blogspot.raulfmiranda.dogame.entity.Dog;
import com.blogspot.raulfmiranda.dogame.entity.DogModel;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;

class QuizPresenter implements Quiz.Presenter {

    private DogModel dogModel = new DogModel(this);
    private Quiz.View view;
    private Dog currentDog;
    private Timer timer;
    private final int TIME_MAX = 30;
    private int score = 0;
    private int time = 30;

    public QuizPresenter(Quiz.View view) {
        this.view = view;
    }


    @Override
    public void requestRandomDog() {
        dogModel.getRandomDog();
    }

    @Override
    public void randomDogSuccessfullyRetrieved(Dog dog) {

        currentDog = dog;
        List<String> randomBreeds = dogModel.getFourRandomBreeds(dog.getBreedSubreed());
        Random rand = new Random();
        randomBreeds.add(rand.nextInt(randomBreeds.size() + 1), dog.getBreedSubreed() + "(A)");

        view.randomDogSuccessfullyRetrieved(dog, randomBreeds);
    }

    @Override
    public void updateScore(QuizChoice quizChoice, String checkedAnswer) {

        switch (quizChoice) {
            case NEXT:
                checkedAnswer = checkedAnswer.replace(" ", "-");
                checkedAnswer = checkedAnswer.replace("(A)", "");

                if(checkedAnswer.equals(currentDog.getBreedSubreed()))
                    this.score++;
                else
                    this.score = 0;
                break;
            case SKIP:
                if(this.score > 0)
                    this.score--;
                break;
        }
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public void startTimer() {
        this.time = TIME_MAX;
        view.setTime(TIME_MAX);

        this.timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){

                new Handler(Looper.getMainLooper()).post(new Runnable () {
                    @Override
                    public void run () {
                        updateTime();
                    }
                });
            }
        },0,1000);
    }

    @Override
    public void stopTimer() {
        if(this.timer != null)
            this.timer.cancel();
    }

    private void updateTime() {
        this.time--;
        view.setTime(QuizPresenter.this.time);
    }
}
