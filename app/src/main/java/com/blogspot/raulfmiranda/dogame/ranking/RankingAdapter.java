package com.blogspot.raulfmiranda.dogame.ranking;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blogspot.raulfmiranda.dogame.R;
import com.blogspot.raulfmiranda.dogame.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.ViewHolder> {

  private List<User> users;
  private int tipoRanking;

  private static Map<Integer, RankingAdapter> mapAdapter = new TreeMap<>();

  static RankingAdapter getInstance(int tipoRanking) {
    if (mapAdapter.get(tipoRanking) == null) {
      mapAdapter.put(tipoRanking, new RankingAdapter(tipoRanking));
    }
    return mapAdapter.get(tipoRanking);
  }

  static Map<Integer, RankingAdapter> getMapAdapter() {
    return mapAdapter;
  }

  public RankingAdapter(int tipoRanking) {
    this.users = new ArrayList<>();
    this.tipoRanking = tipoRanking;
  }

  void setRanking(List<User> userList) {
    users = userList;
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public RankingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
    View v = LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.ranking_item, viewGroup, false);
    return new RankingAdapter.ViewHolder(v);
  }

  @Override
  public void onBindViewHolder(@NonNull RankingAdapter.ViewHolder viewHolder, int position) {
    User user = users.get(position);
    viewHolder.setItemRanking(position, user);
  }

  @Override
  public int getItemCount() {
    return users.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvPlacing;
    private final TextView tvName;
    private final TextView tvScore;

    ViewHolder (View view) {
      super(view);
      tvPlacing = view.findViewById(R.id.tv_placing);
      tvName    = view.findViewById(R.id.tv_name);
      tvScore   = view.findViewById(R.id.tv_score);
    }

    void setItemRanking(int position, User user) {
      tvPlacing.setText(String.valueOf(position + 1));
      tvName.setText(user.getName());
      switch (tipoRanking) {
        case Ranking.GENERAL_RANKING:
          tvScore.setText(String.valueOf(user.getScore()));
          break;
        case Ranking.MONTHLY_RANKING:
          tvScore.setText(String.valueOf(user.getScoreMonth()));
          break;
        case Ranking.WEEKLY_RANKING:
          tvScore.setText(String.valueOf(user.getScoreWeek()));
          break;
        case Ranking.DAILY_RANKING:
          tvScore.setText(String.valueOf(user.getScoreDay()));
          break;
      }
    }

  }
}
