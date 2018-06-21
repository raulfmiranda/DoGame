package com.blogspot.raulfmiranda.dogame;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class HomeFragment extends Fragment {

  public HomeFragment() {
    // Required empty public constructor
  }

  public static HomeFragment newInstance() {
    return new HomeFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

      View view = inflater.inflate(R.layout.fragment_home, container, false);
      Button btnPlayQuiz = view.findViewById(R.id.btnPlayQuiz);
      btnPlayQuiz.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              DogameActivity dogameActivity = (DogameActivity) getActivity();
              if(dogameActivity != null) {
                  dogameActivity.showQuiz();
              }
          }
      });

      return view;
  }

}
