package com.blogspot.raulfmiranda.dogame.viewdog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blogspot.raulfmiranda.dogame.R;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class ViewDogAdapter extends ExpandableRecyclerViewAdapter<BreedViewHolder, SubBreedViewHolder> {

  public ViewDogAdapter(List<? extends ExpandableGroup> groups) {
    super(groups);
  }

  @Override
  public BreedViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.breed_item, parent, false);
    return new BreedViewHolder(view);
  }

  @Override
  public SubBreedViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.sub_breed_item, parent, false);
    return new SubBreedViewHolder(view);
  }

  @Override
  public void onBindChildViewHolder(SubBreedViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
    String breedName = (String) group.getItems().get(childIndex);
    holder.setItemSubBreed(breedName);
  }

  @Override
  public void onBindGroupViewHolder(BreedViewHolder holder, int flatPosition, ExpandableGroup group) {
    holder.setBreedName(group.getTitle());
  }
}
