package com.blogspot.raulfmiranda.dogame.quiz;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.raulfmiranda.dogame.DogameActivity;
import com.blogspot.raulfmiranda.dogame.HomeFragment;
import com.blogspot.raulfmiranda.dogame.Util;
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
    FrameLayout frmProgress;
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
        presenter = new QuizPresenter(this, getActivity());
        presenter.fetchScore();
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
        frmProgress = view.findViewById(R.id.frameProgress);

        frmProgress.setVisibility(FrameLayout.VISIBLE);

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
                DogameActivity dogameActivity = (DogameActivity) getActivity();
                if(dogameActivity != null)
                    dogameActivity.showHome();
            }
        });

        return view;
    }

    private void loadScreen(Dog dog, List<String> breeds) {

        if (isAdded()) {
            txtScore.setText(getString(R.string.score, presenter.getScore()));
            rdGroup.clearCheck();

            Uri uriDogImage = Uri.parse(dog.getImageUrl());
            Picasso
                    .get()
                    .load(uriDogImage)
                    .placeholder(R.drawable.dog)
                    .into(imgView, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            presenter.startTimer();
                            hideProgress();
                            blinkScore();
                        }

                        @Override
                        public void onError(Exception e) {
                            hideProgress();
                            String erroMsg = getString(R.string.erro_dog_image);
                            Toast.makeText(getContext(), erroMsg, Toast.LENGTH_LONG).show();
                        }
                    });

            for (int i = 0; i < rdGroup.getChildCount(); i++) {
                RadioButton rdButton = (RadioButton) rdGroup.getChildAt(i);
                String breed = breeds.get(i);
                breed = breed.replace("-", " ");
                rdButton.setText(breed);
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Util.Companion.initSounds(getActivity());
        presenter.requestRandomDog();
    }

    @Override
    public void onPause() {
        presenter.stopTimer();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        presenter.pushScore();
        super.onDestroy();
    }

    @Override
    public void randomDogSuccessfullyRetrieved(Dog dog, List<String> randomBreeds) {
        loadScreen(dog, randomBreeds);
    }

    @Override
    public void setTime(int time) {
        if (isAdded()) {
            txtTime.setText(getString(R.string.time, time));
        }
    }

    @Override
    public void blinkScore() {
        if (isAdded()) {
            TextView txtView = getView().findViewById(R.id.txtScore);
            if(txtView != null) {
                Animation anim = new AlphaAnimation(0.0f, 1.0f);
                anim.setDuration(50); //You can manage the blinking time with this parameter
                anim.setStartOffset(20);
                anim.setRepeatMode(Animation.REVERSE);
                anim.setRepeatCount(2);
                txtView.startAnimation(anim);
            }
        }
    }

    @Override
    public void showProgress() {
        frmProgress.setVisibility(FrameLayout.VISIBLE);
    }

    @Override
    public void hideProgress() {
        frmProgress.setVisibility(FrameLayout.GONE);
    }
}
