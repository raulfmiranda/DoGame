package com.blogspot.raulfmiranda.dogame.quiz;

import com.blogspot.raulfmiranda.dogame.entity.Dog;
import com.blogspot.raulfmiranda.dogame.entity.DogModel;

import java.util.List;
import java.util.Random;

class QuizPresenter implements Quiz.Presenter {

    private DogModel dogModel = new DogModel(this);
    private Quiz.View view;
    private Dog currentDog;
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
}
