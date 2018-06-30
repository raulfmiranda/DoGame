package com.blogspot.raulfmiranda.dogame.ranking;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class TypeRanking extends ExpandableGroup {

  private int realPosition;

  public TypeRanking(int realPosition, String title, List items) {
    super(title, items);
    this.realPosition = realPosition;
  }

  public int getRealPosition() {
    return realPosition;
  }
}
