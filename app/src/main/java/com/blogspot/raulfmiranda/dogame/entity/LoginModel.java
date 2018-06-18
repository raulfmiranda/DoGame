package com.blogspot.raulfmiranda.dogame.entity;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.blogspot.raulfmiranda.dogame.login.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class LoginModel implements Login.Model, OnCompleteListener<AuthResult>, OnFailureListener, OnSuccessListener<Void> {

  private static final int LOGIN = 1;
  private static final int REGISTER = 2;

  private int currentTask;

  private Login.Presenter presenter;
  private LoginAsyncTask mAuthTask;

  private String userName;

  public LoginModel(Login.Presenter presenter) {
    this.presenter = presenter;
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
  public void login(String email, String password) {
    currentTask = LOGIN;
    mAuthTask = new LoginAsyncTask(email, password);
    mAuthTask.execute((Void) null);
  }

  @Override
  public void onComplete(@NonNull Task<AuthResult> task) {
    if (task.isSuccessful()) {
      switch (currentTask) {
        case LOGIN:
          mAuthTask = null;
          presenter.endLoginOperation();
          break;
        case REGISTER:
          User user = new User(FirebaseAuth.getInstance().getCurrentUser().getUid(), userName);
          FirebaseDatabase
              .getInstance()
              .getReference()
              .child(User.TABELA)
              .child(user.getId())
              .setValue(user)
              .addOnFailureListener(LoginModel.this)
              .addOnSuccessListener(LoginModel.this);
          break;
      }
    } else {
      mAuthTask = null;
      presenter.issueError(Objects.requireNonNull(task.getException()).getMessage());
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
    presenter.endRegistrationOperation();
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
          login(email, password);
          break;
        case REGISTER:
          register(email, password);
          break;
      }
      return null;
    }

    @Override
    protected void onCancelled() {
      mAuthTask = null;
      presenter.operationAborted();
    }

    private void login(String email, String password) {
      FirebaseAuth
          .getInstance()
          .signInWithEmailAndPassword(email, password)
          .addOnCompleteListener(LoginModel.this);
    }

    private void register(String email, String password) {
      FirebaseAuth
          .getInstance()
          .createUserWithEmailAndPassword(email, password)
          .addOnCompleteListener(LoginModel.this);
    }
  }
}
