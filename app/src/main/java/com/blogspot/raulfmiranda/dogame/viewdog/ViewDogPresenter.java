package com.blogspot.raulfmiranda.dogame.viewdog;

import com.blogspot.raulfmiranda.dogame.entity.ViewDogModel;

import java.util.List;
import java.util.Map;

public class ViewDogPresenter implements ViewDog.Presenter {


  private ViewDog.Model model;
  private ViewDog.View view;
  private Map<String, List<String>> breeds;

  public ViewDogPresenter(ViewDog.View view) {
    model = new ViewDogModel(this);
    this.view = view;
  }

  @Override
  public void prepareBreeds() {
    model.requestBreeds();
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
}
