package com.blogspot.raulfmiranda.dogame.ranking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blogspot.raulfmiranda.dogame.R;
import com.blogspot.raulfmiranda.dogame.entity.User;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class RankingAdapter extends ExpandableRecyclerViewAdapter<RankingViewHolder, UserViewHolder> {

  public RankingAdapter(List<? extends ExpandableGroup> groups) {
    super(groups);
  }

  @Override
  public RankingViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.ranking_item, parent, false);
    return new RankingViewHolder(view);
  }

  @Override
  public UserViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.user_item, parent, false);
    return new UserViewHolder(view);
  }

  @Override
  public void onBindChildViewHolder(UserViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
    User user = (User) group.getItems().get(childIndex);
    holder.setItemUser(flatPosition, childIndex, user);
  }

  @Override
  public void onBindGroupViewHolder(RankingViewHolder holder, int flatPosition, ExpandableGroup group) {
    holder.setRankingName(group.getTitle());
  }
}
