package com.blogspot.raulfmiranda.dogame.viewdog;

import android.view.View;
import android.widget.TextView;

import com.blogspot.raulfmiranda.dogame.R;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

public class BreedViewHolder extends GroupViewHolder {

  private TextView tvBreedName;

  public BreedViewHolder(View itemView) {
    super(itemView);
    tvBreedName = itemView.findViewById(R.id.list_item_breed_name);
  }

  public void setBreedName(String name) {
    tvBreedName.setText(name);
  }
}
