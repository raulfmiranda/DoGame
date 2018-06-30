package com.blogspot.raulfmiranda.dogame.entity;

import android.support.annotation.NonNull;

import com.blogspot.raulfmiranda.dogame.entity.remote.Firebase;
import com.blogspot.raulfmiranda.dogame.ranking.Ranking;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RankingModel implements Ranking.Model, ValueEventListener {

    private Firebase firebase;
    private Ranking.Presenter presenter;

    public RankingModel(Ranking.Presenter presenter) {
        firebase = Firebase.getInstance();
        this.presenter = presenter;
    }

    @Override
    public void requestUsers() {
        firebase.requestUsers(this);
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        List<User> listUser = new ArrayList<>();
        for (DataSnapshot child : dataSnapshot.getChildren()) {
            listUser.add(child.getValue(User.class));
        }
        presenter.setUsers(listUser);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
