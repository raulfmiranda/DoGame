package com.blogspot.raulfmiranda.dogame.ranking;

import android.view.View;
import android.widget.TextView;

import com.blogspot.raulfmiranda.dogame.R;
import com.blogspot.raulfmiranda.dogame.entity.User;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class UserViewHolder extends ChildViewHolder {

  private TextView tvPlacing;
  private TextView tvName;
  private TextView tvScore;

  public UserViewHolder(View itemView) {
    super(itemView);
    tvPlacing = itemView.findViewById(R.id.tv_placing);
    tvName    = itemView.findViewById(R.id.tv_name);
    tvScore   = itemView.findViewById(R.id.tv_score);
  }

  void setItemUser(int tipoRanking, int position, User user) {
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
