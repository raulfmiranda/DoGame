package com.blogspot.raulfmiranda.dogame.ranking;

import com.blogspot.raulfmiranda.dogame.entity.User;

import java.util.List;

public interface Ranking {
    int GENERAL_RANKING = 0;
    int MONTHLY_RANKING = 1;
    int WEEKLY_RANKING = 2;
    int DAILY_RANKING = 3;

    interface View {
        void rankingLoaded();
    }
    interface Presenter {
        void prepareRanking();
        void setUsers(List<User> userList);
        List<User> requestRanking(int tipoRanking);
    }
    interface Model {
        void requestUsers();
    }
}
