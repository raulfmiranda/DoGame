package com.blogspot.raulfmiranda.dogame.ranking;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blogspot.raulfmiranda.dogame.R;

public class BlankFragment extends Fragment {

  private static final String ARG_PARAM1 = "param1";

  private String mParam1;

  public BlankFragment() {
    // Required empty public constructor
  }

  public static BlankFragment newInstance(String param1) {
    BlankFragment fragment = new BlankFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_ranking_list, container, false);
    TextView textView = view.findViewById(R.id.tv2);
    textView.setText(mParam1);
    return view;
  }
}
