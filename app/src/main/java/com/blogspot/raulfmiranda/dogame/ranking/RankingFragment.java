package com.blogspot.raulfmiranda.dogame.ranking;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blogspot.raulfmiranda.dogame.R;
import com.blogspot.raulfmiranda.dogame.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RankingFragment extends Fragment implements Ranking.View {

    private Ranking.Presenter presenter;
    private Map<Integer, RankingAdapter> mapAdapter;


    public RankingFragment() {
        presenter = new RankingPresenter(this);
        presenter.prepareRanking();
    }

    public static RankingFragment newInstance() {
        return new RankingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking, container, false);
        setupViewPagerTabs(view);
        return view;
    }

    private void setupViewPagerTabs(View view) {
        final ViewPager viewPager = view.findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(
            new RankingPagerAdapter(getContext(),
                Objects
                    .requireNonNull(getActivity())
                    .getSupportFragmentManager()
            )
        );

        TabLayout tabLayout = view.findViewById(R.id.tabLayout);

        tabLayout.setupWithViewPager(viewPager);
        int cor = ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.white);

        tabLayout.setTabTextColors(cor, cor);
    }

    @Override
    public void rankingLoaded() {
        for (Map.Entry<Integer, RankingAdapter> entry : RankingAdapter.getMapAdapter().entrySet()) {
            int tipoRanking = entry.getKey();
            RankingAdapter adapter = entry.getValue();
            List<User> userList = presenter.requestRanking(tipoRanking);
            adapter.setRanking(userList);
        }
    }
}
