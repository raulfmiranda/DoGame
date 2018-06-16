package com.blogspot.raulfmiranda.dogame.entity;

import com.google.gson.annotations.SerializedName;

public class DogResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String imageUrl;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
