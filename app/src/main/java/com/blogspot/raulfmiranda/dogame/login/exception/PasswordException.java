package com.blogspot.raulfmiranda.dogame.login.exception;

public class PasswordException extends LoginException {

  public PasswordException() {
    super("Senha deve ser preenchida.");
  }
}
