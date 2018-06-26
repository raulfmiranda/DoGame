package com.blogspot.raulfmiranda.dogame.entity;

import android.util.Log;

import com.blogspot.raulfmiranda.dogame.Util;
import com.blogspot.raulfmiranda.dogame.entity.remote.DogAPI;
import com.blogspot.raulfmiranda.dogame.quiz.Quiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DogModel implements Callback<DogResponse> {

    private String TAG = Util.Companion.getTAG();
    private DogAPI.Companion dogAPI = DogAPI.Companion;
    private Quiz.Presenter presenter;

    public DogModel(Quiz.Presenter presenter) {
        this.presenter = presenter;
    }

    public void getRandomDog() {
        dogAPI.getRandomDogResponse(this);
    }

    public List<String> getFourRandomBreeds(String excludedBreed) {
        Random r = new Random();
        List<String> breeds = getAllBreeds();
        List<String> randomBreeds = new ArrayList<String>();

        while(randomBreeds.size() < 4) {
            int i = r.nextInt(breeds.size());
            String breed = breeds.get(i);
            if(!randomBreeds.contains(breed) && !breed.equals(excludedBreed)) {
                randomBreeds.add(breed);
            }
        }

        return randomBreeds;
    }

    // Dog API onResponse
    @Override
    public void onResponse(Call<DogResponse> call, Response<DogResponse> response) {

        if(response.body() != null && response.body().getStatus().equals("success")) {
            DogResponse dogResponse = new DogResponse(response.body().getStatus(), response.body().getImageUrl());
            Dog dog = dogResponse.toDog();
            presenter.randomDogSuccessfullyRetrieved(dog);
        }
    }

    // Dog API onFailure
    @Override
    public void onFailure(Call<DogResponse> call, Throwable t) {
        String erroMsg = t.getMessage();
        Log.d(TAG, erroMsg);
    }

    private List<String> getAllBreeds() {
        // TODO: change to <string-array name="breeds"> in strings.xml
        return Arrays.asList(
                "affenpinscher",
                "african",
                "airedale",
                "akita",
                "appenzeller",
                "basenji",
                "beagle",
                "bluetick",
                "borzoi",
                "bouvier",
                "boxer",
                "brabancon",
                "briard",
                "bulldog-boston",
                "bulldog-french",
                "bullterrier-staffordshire",
                "cairn",
                "cattledog-australian",
                "chihuahua",
                "chow",
                "clumber",
                "collie-border",
                "coonhound",
                "corgi-cardigan",
                "dachshund",
                "dalmatian",
                "dane-great",
                "deerhound-scottish",
                "dhole",
                "dingo",
                "doberman",
                "elkhound-norwegian",
                "entlebucher",
                "eskimo",
                "germanshepherd",
                "greyhound-italian",
                "groenendael",
                "hound-afghan",
                "hound-basset",
                "hound-blood",
                "hound-english",
                "hound-ibizan",
                "hound-walker",
                "husky",
                "keeshond",
                "kelpie",
                "komondor",
                "kuvasz",
                "labrador",
                "leonberg",
                "lhasa",
                "malamute",
                "malinois",
                "maltese",
                "mastiff-bull",
                "mastiff-tibetan",
                "mexicanhairless",
                "mix",
                "mountain-bernese",
                "mountain-swiss",
                "newfoundland",
                "otterhound",
                "papillon",
                "pekinese",
                "pembroke",
                "pinscher-miniature",
                "pointer-german",
                "pomeranian",
                "poodle-miniature",
                "poodle-standard",
                "poodle-toy",
                "pug",
                "pyrenees",
                "redbone",
                "retriever-chesapeake",
                "retriever-curly",
                "retriever-flatcoated",
                "retriever-golden",
                "ridgeback-rhodesian",
                "rottweiler",
                "saluki",
                "samoyed",
                "schipperke",
                "schnauzer-giant",
                "schnauzer-miniature",
                "setter-english",
                "setter-gordon",
                "setter-irish",
                "sheepdog-english",
                "sheepdog-shetland",
                "shiba",
                "shihtzu",
                "spaniel-blenheim",
                "spaniel-brittany",
                "spaniel-cocker",
                "spaniel-irish",
                "spaniel-japanese",
                "spaniel-sussex",
                "spaniel-welsh",
                "springer-english",
                "stbernard",
                "terrier-american",
                "terrier-australian",
                "terrier-bedlington",
                "terrier-border",
                "terrier-dandie",
                "terrier-fox",
                "terrier-irish",
                "terrier-kerryblue",
                "terrier-lakeland",
                "terrier-norfolk",
                "terrier-norwich",
                "terrier-patterdale",
                "terrier-scottish",
                "terrier-sealyham",
                "terrier-silky",
                "terrier-tibetan",
                "terrier-toy",
                "terrier-westhighland",
                "terrier-wheaten",
                "terrier-yorkshire",
                "vizsla",
                "weimaraner",
                "whippet",
                "wolfhound-irish");
    }
}
