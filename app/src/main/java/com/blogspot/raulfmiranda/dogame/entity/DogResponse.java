package com.blogspot.raulfmiranda.dogame.entity;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

import java.net.URI;
import java.net.URL;
import java.util.List;

public class DogResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String imageUrl;

    DogResponse() {}

    DogResponse(String status, String imageUrl) {
        this.status = status;
        this.imageUrl = imageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) { this.status = status; }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Dog toDog() {
        Dog dog = new Dog();
        int breedSubBreedIndex = 1; // ex. retriever-curly
        int imageNameIndex = 2; // ex. imagename.jpg

        if(!this.imageUrl.isEmpty()) {
            Uri uri = Uri.parse(this.imageUrl);
            List<String> segments = uri.getPathSegments();
            String breedSubBreed = segments.get(breedSubBreedIndex);

            String[] splitBreedSubBreed = breedSubBreed.split("-");
            String breed = splitBreedSubBreed[0];
            String subBreed = "";
            if(splitBreedSubBreed.length > 1)
                subBreed = splitBreedSubBreed[1];

            dog.setBreed(breed);
            dog.setSubBreed(subBreed);
            dog.setImageName(segments.get(imageNameIndex));
        }

        return dog;
    }
}
