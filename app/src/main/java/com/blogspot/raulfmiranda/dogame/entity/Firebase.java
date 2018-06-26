package com.blogspot.raulfmiranda.dogame.entity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Firebase {

  private static Firebase instance;

  private FirebaseAuth auth;
  private FirebaseDatabase database;

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
    auth.signOut();
  }

  public FirebaseUser getCurrentUser() {
    return auth.getCurrentUser();
  }

  public void createUser(User user, OnFailureListener failureListener, OnSuccessListener<Void> successListener) {
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

}
