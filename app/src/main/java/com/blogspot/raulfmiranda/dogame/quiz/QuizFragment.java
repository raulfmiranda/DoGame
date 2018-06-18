package com.blogspot.raulfmiranda.dogame.quiz;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blogspot.raulfmiranda.dogame.entity.Dog;
import com.blogspot.raulfmiranda.dogame.entity.Model;
import com.blogspot.raulfmiranda.dogame.R;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuizFragment extends Fragment implements Quiz.View {

    private Quiz.Presenter presenter;

    public QuizFragment() {
        // Required empty public constructor
    }

    public static QuizFragment newInstance() {
        QuizFragment fragment = new QuizFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new QuizPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        return view;
    }

    private void loadScreen(Dog dog, List<String> randomBreeds) {

        ImageView imgView = getView().findViewById(R.id.imgDog);

        Uri uriDogImage = Uri.parse(dog.getImageUrl());
        Picasso
                .get()
                .load(uriDogImage)
                .placeholder(R.mipmap.ic_launcher)
                .into(imgView);

        RadioGroup rdGroup = getView().findViewById(R.id.rdGroup);
        List<String> breeds = randomBreeds;
        Random rand = new Random();
        breeds.add(rand.nextInt(randomBreeds.size() + 1), dog.getBreedSubreed() + " (A)");

        for (int i = 0; i < rdGroup.getChildCount(); i++) {
            RadioButton rdButton = (RadioButton) rdGroup.getChildAt(i);
            String breed = breeds.get(i);
            breed = breed.replace("-", " ");
            rdButton.setText(breed);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView txtScore = getView().findViewById(R.id.txtHeader);
        txtScore.setText(getString(R.string.score, presenter.getScore()));

        presenter.requestRandomDog();
    }

    @Override
    public void randomDogSuccessfullyRetrieved(Dog dog, List<String> randomBreeds) {
        loadScreen(dog, randomBreeds);
    }
}
