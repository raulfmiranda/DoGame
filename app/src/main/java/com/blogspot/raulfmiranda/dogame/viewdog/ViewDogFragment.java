package com.blogspot.raulfmiranda.dogame.viewdog;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blogspot.raulfmiranda.dogame.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewDogFragment extends Fragment implements ViewDog.View {

  private ViewDog.Presenter presenter;
  private RecyclerView mRecyclerView;
  private List<Breed> breeds;

  public ViewDogFragment() {
    presenter = new ViewDogPresenter(this);
    presenter.prepareBreeds();
    breeds = new ArrayList<>();
  }

  public static ViewDogFragment newInstance() {
    return new ViewDogFragment();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_view_dog, container, false);

    mRecyclerView = view.findViewById(R.id.rv_view_dog);

    return view;
  }

  @Override
  public void breedsLoaded() {
    Map<String, List<String>> breedMap = presenter.requestBreeds();
    for (Map.Entry<String,List<String>> entry : breedMap.entrySet()) {
      String breed = entry.getKey();
      List<String> subBreeds = entry.getValue();
      breeds.add(new Breed(breed, subBreeds));
    }
    ViewDogAdapter mAdapter = new ViewDogAdapter(breeds);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    mRecyclerView.setAdapter(mAdapter);
  }
}
