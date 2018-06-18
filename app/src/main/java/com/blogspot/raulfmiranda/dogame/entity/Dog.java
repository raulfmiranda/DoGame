package com.blogspot.raulfmiranda.dogame.entity;

public class Dog {
    private String breed;
    private String subBreed;
    private String breedSubreed;
    private String imageName;
    private String imageUrl;

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getSubBreed() {
        return subBreed;
    }

    public void setSubBreed(String subBreed) {
        this.subBreed = subBreed;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageUrl() { return imageUrl; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getBreedSubreed() {
        String bsb = breed;
        if(!subBreed.isEmpty())
            bsb += "-" + subBreed;

        return bsb;
    }

    public void setBreedSubreed(String breedSubreed) {
        this.breedSubreed = breedSubreed;
    }
}
