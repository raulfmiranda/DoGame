package com.blogspot.raulfmiranda.dogame.login.exception;

public class ConfirmException extends LoginException {
  public ConfirmException() {
    super("Confirmação de senha inválida.");
  }
}
