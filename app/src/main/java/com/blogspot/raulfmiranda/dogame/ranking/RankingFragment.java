package com.blogspot.raulfmiranda.dogame.ranking;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blogspot.raulfmiranda.dogame.R;
import com.blogspot.raulfmiranda.dogame.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RankingFragment extends Fragment implements Ranking.View {

    private Ranking.Presenter presenter;
    private RecyclerView mRecyclerView;
    private RankingAdapter mAdapter;
    private List<TypeRanking> typeRankings;

    public RankingFragment() {
        presenter = new RankingPresenter(this);
        presenter.prepareRanking();
        typeRankings = new ArrayList<>(4);
    }

    public static RankingFragment newInstance() {
        return new RankingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking, container, false);

        mRecyclerView = view.findViewById(R.id.rv);

        return view;
    }

    @Override
    public void rankingLoaded() {
        typeRankings
            .add(new TypeRanking(
                "Geral",
                presenter.requestRanking(Ranking.GENERAL_RANKING)
            ));
        typeRankings
            .add(new TypeRanking(
                "Mensal",
                presenter.requestRanking(Ranking.MONTHLY_RANKING)
            ));
        typeRankings
            .add(new TypeRanking(
                "Semanal",
                presenter.requestRanking(Ranking.WEEKLY_RANKING)
            ));
        typeRankings
            .add(new TypeRanking(
                "Diário",
                presenter.requestRanking(Ranking.DAILY_RANKING)
            ));
        mAdapter = new RankingAdapter(typeRankings);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }
}
