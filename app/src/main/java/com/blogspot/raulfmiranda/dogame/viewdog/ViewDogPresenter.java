package com.blogspot.raulfmiranda.dogame.viewdog;

import com.blogspot.raulfmiranda.dogame.entity.BreedModel;
import com.blogspot.raulfmiranda.dogame.entity.Dog;
import com.blogspot.raulfmiranda.dogame.entity.DogModel;
import com.blogspot.raulfmiranda.dogame.entity.DogModelListener;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class ViewDogPresenter implements ViewDog.Presenter, DogModelListener {


    private BreedModel breedModel;
    private DogModel dogModel;
    private ViewDog.View view;
    private Map<String, List<String>> breeds;

    public ViewDogPresenter(ViewDog.View view) {
        breedModel = new BreedModel(this);
        dogModel = new DogModel(this);
        this.view = view;
    }

    @Override
    public void prepareBreeds() {
        breedModel.requestBreeds();
    }

    @Override
    public void setBreeds(Map<String, List<String>> breedMap) {
        breeds = breedMap;
        view.breedsLoaded();
    }

    @Override
    public Map<String, List<String>> requestBreeds() {
        return breeds;
    }

    @Override
    public void randomDogSuccessfullyRetrieved(@NotNull Dog dog) {
        view.randomDogSuccessfullyRetrieved(dog);
    }

    @Override
    public void requestRandomDogByBreed(@NotNull String breed) {
        view.showProgress();
        dogModel.getRandomDogResponseByBreed(breed);
    }

    @Override
    public void requestRandomDogBySubBreed(@NotNull String breed, @NotNull String subBreed) {
        view.showProgress();
        dogModel.getRandomDogResponseBySubBreed(breed, subBreed);
    }
}
