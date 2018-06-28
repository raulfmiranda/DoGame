package com.blogspot.raulfmiranda.dogame.viewdog;

import java.util.List;
import java.util.Map;

public interface ViewDog {

  interface View {
    void breedsLoaded();
  }
  interface Presenter {
    void prepareBreeds();
    void setBreeds(Map<String, List<String>> breedMap);
    Map<String, List<String>> requestBreeds();
  }
  interface Model {
    void requestBreeds();
  }
}
