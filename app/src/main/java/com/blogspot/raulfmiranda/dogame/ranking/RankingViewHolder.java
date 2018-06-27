package com.blogspot.raulfmiranda.dogame.ranking;

import android.view.View;
import android.widget.TextView;

import com.blogspot.raulfmiranda.dogame.R;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

public class RankingViewHolder extends GroupViewHolder {

  private TextView rankingTitle;

  public RankingViewHolder(View itemView) {
    super(itemView);
    rankingTitle = itemView.findViewById(R.id.list_item_ranking_name);
  }

  public void setRankingName(String name) {
    rankingTitle.setText(name);
  }
}
