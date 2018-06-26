package com.blogspot.raulfmiranda.dogame.ranking;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blogspot.raulfmiranda.dogame.BaseFragment;
import com.blogspot.raulfmiranda.dogame.R;
import com.blogspot.raulfmiranda.dogame.entity.User;

import java.util.List;

public class RankingListFragment extends Fragment {

  private static final String PARAM_TIPO_RANKING = "tipo de ranking";

  private int tipoRanking;
  private RankingAdapter adapter;

  public static RankingListFragment newInstance(int tipoRanking) {
    RankingListFragment fragment = new RankingListFragment();
    Bundle args = new Bundle();
    args.putInt(PARAM_TIPO_RANKING, tipoRanking);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      tipoRanking = getArguments().getInt(PARAM_TIPO_RANKING);
    }
    adapter = RankingAdapter.getInstance(tipoRanking);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_ranking_list, container, false);

    RecyclerView rvRanking = view.findViewById(R.id.rv_ranking);
    rvRanking.addItemDecoration(new SpacesItemDecoration(6));
    rvRanking.setHasFixedSize(true);
    int scrollPosition = 0;
    if (rvRanking.getLayoutManager() != null) {
      scrollPosition = ((LinearLayoutManager) rvRanking.getLayoutManager())
          .findFirstCompletelyVisibleItemPosition();
    }
    rvRanking.setLayoutManager(new LinearLayoutManager(getActivity()));
    rvRanking.scrollToPosition(scrollPosition);
    rvRanking.setItemAnimator(new DefaultItemAnimator());
    rvRanking.setAdapter(adapter);

//    mView        = rvRanking;
//    mProgressBar = view.findViewById(R.id.pb_list_ranking);
//
//    showProgress();

    return view;
  }

  private class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    SpacesItemDecoration(int space) {
      this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
      outRect.left = space;
      outRect.right = space;
      outRect.bottom = space;

      if(parent.getChildAdapterPosition(view) == 0)
        outRect.top = space;

    }
  }
}
