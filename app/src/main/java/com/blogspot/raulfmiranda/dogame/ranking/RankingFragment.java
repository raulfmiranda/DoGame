package com.blogspot.raulfmiranda.dogame.ranking;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blogspot.raulfmiranda.dogame.entity.Model;
import com.blogspot.raulfmiranda.dogame.R;

public class RankingFragment extends Fragment {

    private Ranking.Presenter presenter;

    private TextView mTextMessage;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
        = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_general:
                    mTextMessage.setText("geral");
                    return true;
                case R.id.navigation_monthly:
                    mTextMessage.setText("Mensal");
                    return true;
                case R.id.navigation_weekly:
                    mTextMessage.setText("Semanal");
                    return true;
                case R.id.navigation_daily:
                    mTextMessage.setText("diário");
                    return true;
            }
            return false;
        }
    };

    public RankingFragment() {
    }

    public static RankingFragment newInstance() {
        RankingFragment fragment = new RankingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new RankingPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking, container, false);
        mTextMessage = view.findViewById(R.id.tv_message);
        BottomNavigationView navigation = view.findViewById(R.id.navigation_ranking);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        return view;
    }
}
