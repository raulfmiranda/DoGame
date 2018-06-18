package com.blogspot.raulfmiranda.dogame.login;

import com.blogspot.raulfmiranda.dogame.login.exception.ConfirmException;
import com.blogspot.raulfmiranda.dogame.login.exception.EmailException;
import com.blogspot.raulfmiranda.dogame.login.exception.NameException;
import com.blogspot.raulfmiranda.dogame.login.exception.PasswordException;

public interface OnLoginInteractionListener {
  void registerUser(String name, String email, String password, String confirmationPassword)
      throws NameException, EmailException, PasswordException, ConfirmException;
  void showRegister();
  void requestLogin(String email, String password) throws EmailException, PasswordException;
}
