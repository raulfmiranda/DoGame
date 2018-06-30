package com.blogspot.raulfmiranda.dogame.entity.remote;

import android.support.annotation.NonNull;

import com.blogspot.raulfmiranda.dogame.entity.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Firebase {

  private static Firebase instance;

  private FirebaseAuth auth;
  private FirebaseDatabase database;
  private User user;

  public static Firebase getInstance() {
    if (instance == null) {
      instance = new Firebase();
    }
    return instance;
  }

  private Firebase() {
    auth     = FirebaseAuth.getInstance();
    database = FirebaseDatabase.getInstance();
  }

  public void register(String email, String password, OnCompleteListener<AuthResult> listener) {
    auth
        .createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener(listener);
  }

  public void login(String email, String password, OnCompleteListener<AuthResult> listener) {
    auth
        .signInWithEmailAndPassword(email, password)
        .addOnCompleteListener(listener);
  }

  public void logout() {
    user = null;
    auth.signOut();
  }

  public FirebaseUser getCurrentUser() {
    return auth.getCurrentUser();
  }

  public User getUser() {
    return user;
  }

  public void createUser(String userName, OnFailureListener failureListener, OnSuccessListener<Void> successListener) {
    user = new User(getCurrentUser().getUid(), userName);
    database
        .getReference()
        .child(User.TABELA)
        .child(user.getId())
        .setValue(user)
        .addOnFailureListener(failureListener)
        .addOnSuccessListener(successListener);
  }

  public void requestUsers(ValueEventListener listener) {
    database
        .getReference()
        .child("users")
        .addListenerForSingleValueEvent(listener);
  }

  public void increaseScore(int increase) {
    user.increaseScore(increase);
    database
        .getReference()
        .child("users")
        .child(user.getId())
        .setValue(user);

  }

  public void decreaseScore(int decrement) {
    user.decreaseScore(decrement);
    database
        .getReference()
        .child("users")
        .child(user.getId())
        .setValue(user);

  }

  public void loadUser(ValueEventListener listener) {
    database
        .getReference()
        .child("users")
        .child(auth.getCurrentUser().getUid())
        .addListenerForSingleValueEvent(listener);
  }

  public void setUser(User user) {
    this.user = user;
  }
}
