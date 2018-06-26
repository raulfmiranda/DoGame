package com.blogspot.raulfmiranda.dogame.ranking;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class RankingPagerAdapter extends FragmentPagerAdapter {

  private Context context;

  private List<String> titles;

  RankingPagerAdapter(Context context, FragmentManager fm) {
    super(fm);
    this.context = context;
    titles = new ArrayList<>();
    titles.add(Ranking.GENERAL_RANKING, "Geral");
    titles.add(Ranking.MONTHLY_RANKING, "Mensal");
    titles.add(Ranking.WEEKLY_RANKING, "Semanal");
    titles.add(Ranking.DAILY_RANKING, "Diário");
  }

  @Override
  public int getCount() {
    return titles.size();
  }

  @Nullable
  @Override
  public CharSequence getPageTitle(int position) {
    return titles.get(position);
  }

  @Override
  public Fragment getItem(int position) {
    return RankingListFragment.newInstance(position);
  }
}
