package com.blogspot.raulfmiranda.dogame.quiz;

import com.blogspot.raulfmiranda.dogame.entity.Dog;
import com.blogspot.raulfmiranda.dogame.entity.DogModel;

class QuizPresenter implements Quiz.Presenter {

    private DogModel dogModel = new DogModel(this);
    private Quiz.View view;
    private int score = 0;

    public QuizPresenter(Quiz.View view) {
        this.view = view;
    }


    @Override
    public void requestRandomDog() {
        dogModel.getRandomDog();
    }

    @Override
    public void randomDogSuccessfullyRetrieved(Dog dog) {
        view.randomDogSuccessfullyRetrieved(dog, dogModel.getFourRandomBreeds(dog.getBreedSubreed()));
    }

    @Override
    public void updateScore() {
        this.score++;
    }

    @Override
    public int getScore() {
        return this.score;
    }
}
