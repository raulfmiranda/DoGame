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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    private Button btnMoreDogImg;
    private FrameLayout frmViewDogProgress;
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
        btnMoreDogImg = view.findViewById(R.id.btnMoreDogImg);
        frmViewDogProgress = view.findViewById(R.id.frmViewDogProgress);
        frmViewDogProgress.setVisibility(FrameLayout.VISIBLE);

        spnBreeds.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv = (TextView) view;
                String breed = tv.getText().toString();
                requestDog(breed);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        btnMoreDogImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String breed = spnBreeds.getItemAtPosition(spnBreeds.getSelectedItemPosition()).toString();
                requestDog(breed);
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
                .into(imgDogSearch, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        hideProgress();
                    }

                    @Override
                    public void onError(Exception e) {
                        hideProgress();
                        String imgErroMsg = getString(R.string.erro_dog_image);
                        Toast.makeText(getContext(), imgErroMsg, Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public void showProgress() {
        frmViewDogProgress.setVisibility(FrameLayout.VISIBLE);
    }

    @Override
    public void hideProgress() {
        frmViewDogProgress.setVisibility(FrameLayout.GONE);
    }

    private void loadSpinner(List<String> breedSubreeds) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, breedSubreeds);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnBreeds.setAdapter(adapter);
    }

    private void requestDog(String breed) {
        if(!breed.contains(" ")) {
            presenter.requestRandomDogByBreed(breed);
        } else {
            String[] breedSubreed = breed.split(" ");
            presenter.requestRandomDogBySubBreed(breedSubreed[0], breedSubreed[1]);
        }
    }
}
