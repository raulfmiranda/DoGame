package com.blogspot.raulfmiranda.dogame.viewdog;

import android.view.View;
import android.widget.TextView;

import com.blogspot.raulfmiranda.dogame.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class SubBreedViewHolder extends ChildViewHolder {

  private TextView tvSubBreedName;

  public SubBreedViewHolder(View itemView) {
    super(itemView);
    tvSubBreedName = itemView.findViewById(R.id.list_item_sub_breed_name);
  }

  void setItemSubBreed(String subBreedName) {
    tvSubBreedName.setText(subBreedName);
  }
}
