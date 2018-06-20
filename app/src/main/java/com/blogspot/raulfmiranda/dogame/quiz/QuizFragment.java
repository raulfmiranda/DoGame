package com.blogspot.raulfmiranda.dogame.quiz;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.raulfmiranda.dogame.DogameActivity;
import com.blogspot.raulfmiranda.dogame.HomeFragment;
import com.blogspot.raulfmiranda.dogame.entity.Dog;
import com.blogspot.raulfmiranda.dogame.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class QuizFragment extends Fragment implements Quiz.View {

    private Quiz.Presenter presenter;
    Button btnNext;
    Button btnSkip;
    ImageButton ibtClose;
    RadioGroup rdGroup;
    RadioButton checkedRdButton;
    ImageView imgView;
    TextView txtScore;
    TextView txtTime;

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

        btnNext = view.findViewById(R.id.btnNext);
        btnSkip = view.findViewById(R.id.btnSkip);
        ibtClose = view.findViewById(R.id.ibtClose);
        rdGroup = view.findViewById(R.id.rdGroup);
        imgView = view.findViewById(R.id.imgDog);
        txtScore = view.findViewById(R.id.txtScore);
        txtTime = view.findViewById(R.id.txtTime);
        rdGroup = view.findViewById(R.id.rdGroup);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int checkRdBtnId = rdGroup.getCheckedRadioButtonId();

                if(checkRdBtnId == -1) {
                    Toast.makeText(getActivity(), getString(R.string.not_checked), Toast.LENGTH_SHORT).show();
                } else {
                    presenter.stopTimer();
                    checkedRdButton = rdGroup.findViewById(checkRdBtnId);
                    presenter.updateScore(QuizChoice.NEXT, checkedRdButton.getText().toString());
                    presenter.requestRandomDog();
                }

            }
        });

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.stopTimer();
                presenter.updateScore(QuizChoice.SKIP, null);
                presenter.requestRandomDog();
            }
        });

        ibtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.stopTimer();
//                DogameActivity dogameActivity = (DogameActivity) getActivity();
//                if(dogameActivity != null)
//                    dogameActivity.showFragment(R.id.activity_dogame, HomeFragment.newInstance());
            }
        });

        return view;
    }

    private void loadScreen(Dog dog, List<String> breeds) {

        txtScore.setText(getString(R.string.score, presenter.getScore()));
        rdGroup.clearCheck();

        Uri uriDogImage = Uri.parse(dog.getImageUrl());
        Picasso
                .get()
                .load(uriDogImage)
                .placeholder(R.mipmap.ic_launcher)
                .into(imgView);

        for (int i = 0; i < rdGroup.getChildCount(); i++) {
            RadioButton rdButton = (RadioButton) rdGroup.getChildAt(i);
            String breed = breeds.get(i);
            breed = breed.replace("-", " ");
            rdButton.setText(breed);
        }
        presenter.startTimer();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter.requestRandomDog();
    }

    @Override
    public void onPause() {
        presenter.stopTimer();
        super.onPause();
    }

    @Override
    public void randomDogSuccessfullyRetrieved(Dog dog, List<String> randomBreeds) {
        loadScreen(dog, randomBreeds);
    }

    @Override
    public void setTime(int time) {
        txtTime.setText(getString(R.string.time, time));
    }
}
