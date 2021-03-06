package com.blogspot.raulfmiranda.dogame.ranking;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.blogspot.raulfmiranda.dogame.entity.RankingModel;
import com.blogspot.raulfmiranda.dogame.entity.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class RankingPresenter implements Ranking.Presenter {

    private final int MAXIMUM_USERS = 15;

    private Ranking.Model model;
    private Ranking.View view;
    private List<List<User>> ranking;

    public RankingPresenter(Ranking.View view) {
        model = new RankingModel(this);
        this.view = view;
    }

    @Override
    public List<User> requestRanking(int tipoRanking) {
        return ranking.get(tipoRanking);
    }

    @Override
    public void prepareRanking() {
        model.requestUsers();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void setUsers(List<User> users) {
        Calendar today = Calendar.getInstance();
        TreeMap<Integer, List<User>> mapGeneralRanking = new TreeMap<>();
        TreeMap<Integer, List<User>> mapMonthlyRanking = new TreeMap<>();
        TreeMap<Integer, List<User>> mapWeeklyRanking = new TreeMap<>();
        TreeMap<Integer, List<User>> mapDailyRanking = new TreeMap<>();
        for (User user : users) {
            if (user.getScore() == 0)
                continue;
            Integer score = user.getScore();
            addRanking(mapGeneralRanking, user, score);
            if (user.getScoreMonth() == 0 || (user.getMonth() != today.get(Calendar.MONTH) + 1))
                continue;
            score = user.getScoreMonth();
            addRanking(mapMonthlyRanking, user, score);
            if (user.getScoreWeek() == 0 || (user.getWeek() != today.get(Calendar.WEEK_OF_YEAR)))
                continue;
            score = user.getScoreWeek();
            addRanking(mapWeeklyRanking, user, score);
            if (user.getScoreDay() == 0 || (user.getDay() != today.get(Calendar.DAY_OF_YEAR)))
                continue;
            score = user.getScoreDay();
            addRanking(mapDailyRanking, user, score);
        }
        ranking = new ArrayList<>();
        ranking.add(Ranking.GENERAL_RANKING, getRankingOrdered(mapGeneralRanking));
        ranking.add(Ranking.MONTHLY_RANKING, getRankingOrdered(mapMonthlyRanking));
        ranking.add(Ranking.WEEKLY_RANKING, getRankingOrdered(mapWeeklyRanking));
        ranking.add(Ranking.DAILY_RANKING, getRankingOrdered(mapDailyRanking));
        view.rankingLoaded();
    }

    private List<User> getRankingOrdered(TreeMap<Integer, List<User>> generalRanking) {
        List<User> listUsers = new ArrayList<>(MAXIMUM_USERS);
        for (Map.Entry<Integer, List<User>> entry : generalRanking.descendingMap().entrySet()) {
            List<User> list = entry.getValue();
            listUsers.addAll(list);
            if (listUsers.size() >= MAXIMUM_USERS) {
                break;
            }
        }
        return listUsers;
    }

    private void addRanking(TreeMap<Integer, List<User>> ranking, User user, Integer score) {
        List<User> listUsers = ranking.get(score);
        if (listUsers == null) {
            listUsers = new ArrayList<>();
            ranking.put(score, listUsers);
        }
        listUsers.add(user);
    }
}
