package com.blogspot.raulfmiranda.dogame.login;

import com.blogspot.raulfmiranda.dogame.login.exception.ConfirmException;
import com.blogspot.raulfmiranda.dogame.login.exception.EmailException;
import com.blogspot.raulfmiranda.dogame.login.exception.NameException;
import com.blogspot.raulfmiranda.dogame.login.exception.PasswordException;

public interface Login {
  interface View {
    void showProgress();
    void hideProgress();
    void showDoGame();
    void finishRegistration();
    void issueError(String message);
  }

  interface Presenter {
    void setView(Login.View view);
    void registerUser(String name, String email, String password, String confirmationPassword)
        throws NameException, EmailException, PasswordException, ConfirmException;
    void login(String email, String password)
        throws EmailException, PasswordException;
    void operationAborted();
    void endOperation();
    void issueError(String message);
    void startLogin();
  }

  interface Model {
    void registerUser(String name, String email, String password);
    boolean registrationInProgress();
    void login(String email, String password);
    boolean loginInProgress();
    void loadUser();
  }
}
