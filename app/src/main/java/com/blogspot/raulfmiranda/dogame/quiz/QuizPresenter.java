package com.blogspot.raulfmiranda.dogame.quiz;

import com.blogspot.raulfmiranda.dogame.entity.Dog;
import com.blogspot.raulfmiranda.dogame.entity.DogameModel;
import com.blogspot.raulfmiranda.dogame.entity.Model;

class QuizPresenter implements Quiz.Presenter {

    private DogameModel dogameModel = new DogameModel(this);
    private Quiz.View view;

    public QuizPresenter(Quiz.View view) {
        this.view = view;
    }


    @Override
    public void requestRandomDog() {
        dogameModel.getRandomDog();
    }

    @Override
    public void randomDogSuccessfullyRetrieved(Dog dog) {
        view.randomDogSuccessfullyRetrieved(dog);
    }
}
