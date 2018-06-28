package com.blogspot.raulfmiranda.dogame.viewdog;

import com.blogspot.raulfmiranda.dogame.entity.Dog;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public interface ViewDog {

    interface View {
        void breedsLoaded();
        void randomDogSuccessfullyRetrieved(@NotNull Dog dog);
        void showProgress();
        void hideProgress();
    }

    interface Presenter {
        void prepareBreeds();
        void setBreeds(Map<String, List<String>> breedMap);
        Map<String, List<String>> requestBreeds();
        void requestRandomDogByBreed(@NotNull String breed);
        void requestRandomDogBySubBreed(@NotNull String breed, @NotNull String subBreed);
    }

    interface Model {
        void requestBreeds();
    }
}
