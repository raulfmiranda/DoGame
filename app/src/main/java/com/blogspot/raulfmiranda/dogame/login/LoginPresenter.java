package com.blogspot.raulfmiranda.dogame.login;

import com.blogspot.raulfmiranda.dogame.entity.LoginModel;
import com.blogspot.raulfmiranda.dogame.login.exception.ConfirmException;
import com.blogspot.raulfmiranda.dogame.login.exception.EmailException;
import com.blogspot.raulfmiranda.dogame.login.exception.NameException;
import com.blogspot.raulfmiranda.dogame.login.exception.PasswordException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginPresenter implements Login.Presenter {

  private Login.View view;
  private Login.Model model;

  public LoginPresenter() {
    model = new LoginModel(this);
  }



  @Override
  public void setView(Login.View view) {
    this.view = view;
  }

  @Override
  public void registerUser(String name, String email, String password, String confirmationPassword)
      throws NameException, EmailException, PasswordException, ConfirmException {
    if (model.registrationInProgress()) {
      return;
    }

    validateName(name);
    validateEmail(email);
    validatePassword(password);
    validatePasswordConfirmation(password, confirmationPassword);

    view.showProgress();

    model.registerUser(name, email, password);
  }

  @Override
  public void login(String email, String password) throws EmailException, PasswordException {
    if (model.loginInProgress()) {
      return;
    }

    validateEmail(email);
    validatePassword(password);

    view.showProgress();
    model.login(email, password);
  }

  @Override
  public void startLogin() {
      model.loadUser();
  }

  @Override
  public void operationAborted() {
    view.hideProgress();
  }

  @Override
  public void endOperation() {
    view.showDoGame();
  }

  @Override
  public void issueError(String message) {
    view.issueError(message);
  }




  private void validateName(String name) throws NameException {
    if (name.trim().equals("")) {
      throw new NameException();
    }
  }

  private void validateEmail(String email) throws EmailException {
    Pattern pattern = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
    Matcher matcher = pattern.matcher(email);
    if (!matcher.find()) {
      throw new EmailException();
    }
  }

  private void validatePassword(String password) throws PasswordException {
    if (password.trim().equals("")) {
      throw new PasswordException();
    }
  }

  private void validatePasswordConfirmation(String password, String confirmationPassword)
      throws ConfirmException {
    if (!password.equals(confirmationPassword)) {
      throw new ConfirmException();
    }
  }

}
