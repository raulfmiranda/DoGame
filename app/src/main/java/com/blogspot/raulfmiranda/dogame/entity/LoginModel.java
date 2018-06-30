package com.blogspot.raulfmiranda.dogame.entity;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.blogspot.raulfmiranda.dogame.entity.remote.Firebase;
import com.blogspot.raulfmiranda.dogame.login.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginModel implements Login.Model, OnCompleteListener<AuthResult>, OnFailureListener, OnSuccessListener<Void>, ValueEventListener {

  private static final int LOGIN = 1;
  private static final int REGISTER = 2;

  private int currentTask;

  private Login.Presenter presenter;
  private LoginAsyncTask mAuthTask;
  private Firebase firebase;

  private String userName;

  public LoginModel(Login.Presenter presenter) {
    this.presenter = presenter;
    firebase       = Firebase.getInstance();
  }

  @Override
  public boolean registrationInProgress() {
    return currentTask == REGISTER && mAuthTask != null;
  }

  @Override
  public void registerUser(String name, String email, String password) {
    currentTask = REGISTER;
    userName    = name;
    mAuthTask   = new LoginAsyncTask(email, password);
    mAuthTask.execute((Void) null);
  }

  @Override
  public boolean loginInProgress() {
    return currentTask == LOGIN && mAuthTask != null;
  }

  @Override
  public void loadUser() {
    firebase.loadUser(this);
  }

  @Override
  public void login(String email, String password) {
    currentTask = LOGIN;
    mAuthTask   = new LoginAsyncTask(email, password);
    mAuthTask.execute((Void) null);
  }

  @Override
  public void onComplete(@NonNull Task<AuthResult> task) {
    if (task.isSuccessful()) {
      switch (currentTask) {
        case LOGIN:
          firebase.loadUser(this);
          mAuthTask = null;
          break;
        case REGISTER:
          firebase.createUser(userName, LoginModel.this, LoginModel.this);
          break;
      }
    } else {
      mAuthTask = null;
      String errorCode = ((FirebaseAuthException) Objects.requireNonNull(task.getException())).getErrorCode();
      presenter.issueError(errorCode, task.getException().getMessage());
    }
  }

  @Override
  public void onFailure(@NonNull Exception e) {
    mAuthTask = null;
    presenter.issueError(e.getMessage());
  }

  @Override
  public void onSuccess(Void aVoid) {
    mAuthTask = null;
    firebase.loadUser(this);
  }



  @Override
  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
    firebase.setUser(dataSnapshot.getValue(User.class));
    presenter.endOperation();
  }

  @Override
  public void onCancelled(@NonNull DatabaseError databaseError) {

  }


  class LoginAsyncTask extends AsyncTask<Void, Void, Void> {

    private final String email;
    private final String password;

    LoginAsyncTask(String email, String password) {
      this.email    = email;
      this.password = password;
    }

    @Override
    protected Void doInBackground(Void... params) {
      switch (currentTask) {
        case LOGIN:
          firebase.login(email, password, LoginModel.this);
          break;
        case REGISTER:
          firebase.register(email, password, LoginModel.this);
          break;
      }
      return null;
    }

    @Override
    protected void onCancelled() {
      mAuthTask = null;
      presenter.operationAborted();
    }
  }
}
