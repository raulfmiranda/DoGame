package com.blogspot.raulfmiranda.dogame.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class BreedResponse {

  @SerializedName("status")
  private String status;

  @SerializedName("message")
  private Map<String, List<String>> breeds;

  public BreedResponse(String status, Map<String, List<String>> breeds) {
    this.status = status;
    this.breeds = breeds;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Map<String, List<String>> getBreeds() {
    return breeds;
  }

  public void setBreeds(Map<String, List<String>> breeds) {
    this.breeds = breeds;
  }
}
