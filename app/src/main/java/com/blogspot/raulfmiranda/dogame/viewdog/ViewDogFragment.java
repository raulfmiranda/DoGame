package com.blogspot.raulfmiranda.dogame.viewdog;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.blogspot.raulfmiranda.dogame.R;
import com.blogspot.raulfmiranda.dogame.entity.Dog;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewDogFragment extends Fragment implements ViewDog.View {

    private ViewDog.Presenter presenter;
//    private RecyclerView mRecyclerView;
    private Spinner spnBreeds;
    private ImageView imgDogSearch;
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

//        mRecyclerView = view.findViewById(R.id.rv_view_dog);
        spnBreeds = view.findViewById(R.id.spnBreeds);
        imgDogSearch = view.findViewById(R.id.imgDogSearch);

        spnBreeds.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv = (TextView) view;
                String breed = tv.getText().toString();

                if(!breed.contains(" ")) {
                    presenter.requestRandomDogByBreed(breed);
                } else {
                    String[] breedSubreed = breed.split(" ");
                    presenter.requestRandomDogBySubBreed(breedSubreed[0], breedSubreed[1]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    @Override
    public void breedsLoaded() {

        List<String> breedSubreeds = new ArrayList<>();
        Map<String, List<String>> breedMap = presenter.requestBreeds();

        for (Map.Entry<String, List<String>> entry : breedMap.entrySet()) {
            String breed = entry.getKey();
            List<String> subBreeds = entry.getValue();
            breeds.add(new Breed(breed, subBreeds));

            if(subBreeds == null || subBreeds.size() == 0) {
                breedSubreeds.add(breed);
            } else {
                for (String sb : subBreeds) {
                    breedSubreeds.add(breed + " " + sb);
                }
            }
        }
        loadSpinner(breedSubreeds);

//        ViewDogAdapter mAdapter = new ViewDogAdapter(breeds);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void randomDogSuccessfullyRetrieved(@NotNull Dog dog) {
        Uri uriDogImage = Uri.parse(dog.getImageUrl());
        Picasso
                .get()
                .load(uriDogImage)
                .placeholder(R.drawable.dog)
                .into(imgDogSearch);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    private void loadSpinner(List<String> breedSubreeds) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, breedSubreeds);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnBreeds.setAdapter(adapter);
    }
}
