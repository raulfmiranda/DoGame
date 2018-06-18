package com.blogspot.raulfmiranda.dogame.quiz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blogspot.raulfmiranda.dogame.entity.Dog;
import com.blogspot.raulfmiranda.dogame.entity.Model;
import com.blogspot.raulfmiranda.dogame.R;

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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter.requestRandomDog();
    }

    @Override
    public void loadScreen(Dog dog) {
        TextView txtImageUrl = getView().findViewById(R.id.txtImgUrl);
        if(txtImageUrl != null)
            txtImageUrl.setText(dog.getImageName());
    }

    @Override
    public void randomDogSuccessfullyRetrieved(Dog dog) {
        loadScreen(dog);
    }
}
