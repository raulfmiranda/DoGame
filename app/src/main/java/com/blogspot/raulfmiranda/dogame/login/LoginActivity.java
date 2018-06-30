package com.blogspot.raulfmiranda.dogame.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;

import com.blogspot.raulfmiranda.dogame.BaseActivity;
import com.blogspot.raulfmiranda.dogame.BaseFragment;
import com.blogspot.raulfmiranda.dogame.DogameActivity;
import com.blogspot.raulfmiranda.dogame.R;
import com.blogspot.raulfmiranda.dogame.entity.remote.Firebase;
import com.blogspot.raulfmiranda.dogame.login.exception.ConfirmException;
import com.blogspot.raulfmiranda.dogame.login.exception.EmailException;
import com.blogspot.raulfmiranda.dogame.login.exception.NameException;
import com.blogspot.raulfmiranda.dogame.login.exception.PasswordException;

public class LoginActivity extends BaseActivity implements OnLoginInteractionListener , Login.View {

  private static final int LOGIN    = 1;
  private static final int REGISTER = 2;

  private BaseFragment fragment;
  private Login.Presenter presenter;
  private int currentScreen;


  // Redefinições da Activity
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    if( presenter == null ){
      presenter = new LoginPresenter();
    }
    presenter.setView(this);
  }

  @Override
  public void onStart() {
    super.onStart();
    if (Firebase.getInstance().getCurrentUser() != null) {
      presenter.startLogin();
    } else {
      showLogin();
    }
  }

  @Override
  public void onBackPressed() {
    if (currentScreen == LOGIN) {
      super.onBackPressed();
    } else {
      showLogin();
    }
  }


  // Implementações de OnLoginInteractionListener
  @Override
  public void registerUser(String name, String email, String password, String confirmationPassword)
      throws NameException, EmailException, PasswordException, ConfirmException {
    presenter.registerUser(name, email, password, confirmationPassword);
  }

  @Override
  public void showRegister() {
    currentScreen = REGISTER;
    fragment      = RegisterFragment.newInstance();
    showFragment(R.id.activity_login, fragment);
  }

  @Override
  public void requestLogin(String email, String password) throws EmailException, PasswordException {
    presenter.login(email, password);
  }


  // Implementações da View
  @Override
  public void showProgress() {
    fragment.showProgress();
  }

  @Override
  public void hideProgress() {
    fragment.hideProgress();
  }

  @Override
  public void issueError(String mensagem) {
    fragment.hideProgress();
    Snackbar
            .make(fragment.getView(), mensagem, Snackbar.LENGTH_SHORT)
            .show();
  }

  @Override
  public void issueError(String errorCode, String messageDefault) {
    String msgError;
    switch (errorCode) {
      case "ERROR_INVALID_CUSTOM_TOKEN":
        msgError = getString(R.string.ERROR_INVALID_CUSTOM_TOKEN);
        break;

      case "ERROR_CUSTOM_TOKEN_MISMATCH":
        msgError = getString(R.string.ERROR_CUSTOM_TOKEN_MISMATCH);
        break;

      case "ERROR_INVALID_CREDENTIAL":
        msgError = getString(R.string.ERROR_INVALID_CREDENTIAL);
        break;

      case "ERROR_INVALID_EMAIL":
        msgError = getString(R.string.ERROR_INVALID_EMAIL);
        break;

      case "ERROR_WRONG_PASSWORD":
        msgError = getString(R.string.ERROR_WRONG_PASSWORD);
        break;

      case "ERROR_USER_MISMATCH":
        msgError = getString(R.string.ERROR_USER_MISMATCH);
        break;

      case "ERROR_REQUIRES_RECENT_LOGIN":
        msgError = getString(R.string.ERROR_REQUIRES_RECENT_LOGIN);
        break;

      case "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL":
        msgError = getString(R.string.ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL);
        break;

      case "ERROR_EMAIL_ALREADY_IN_USE":
        msgError = getString(R.string.ERROR_EMAIL_ALREADY_IN_USE);
        break;

      case "ERROR_CREDENTIAL_ALREADY_IN_USE":
        msgError = getString(R.string.ERROR_CREDENTIAL_ALREADY_IN_USE);
        break;

      case "ERROR_USER_DISABLED":
        msgError = getString(R.string.ERROR_USER_DISABLED);
        break;

      case "ERROR_USER_TOKEN_EXPIRED":
        msgError = getString(R.string.ERROR_USER_TOKEN_EXPIRED);
        break;

      case "ERROR_USER_NOT_FOUND":
        msgError = getString(R.string.ERROR_USER_NOT_FOUND);
        break;

      case "ERROR_INVALID_USER_TOKEN":
        msgError = getString(R.string.ERROR_INVALID_USER_TOKEN);
        break;

      case "ERROR_OPERATION_NOT_ALLOWED":
        msgError = getString(R.string.ERROR_OPERATION_NOT_ALLOWED);
        break;

      case "ERROR_WEAK_PASSWORD":
        msgError = getString(R.string.ERROR_WEAK_PASSWORD);
        break;

      default:
        msgError = messageDefault;
    }

    fragment.hideProgress();
    Snackbar
            .make(fragment.getView(), msgError, Snackbar.LENGTH_SHORT)
            .show();
  }

  @Override
  public void finishRegistration() {
    issueError("Cadastro efetuado com sucesso!");
    showLogin();
  }

  @Override
  public void showDoGame() {
    Intent it = new Intent(this, DogameActivity.class);
    startActivity(it);
    finish();
  }



  private void showLogin() {
    currentScreen = LOGIN;
    fragment      = LoginFragment.newInstance();
    showFragment(R.id.activity_login, fragment);
  }
}
