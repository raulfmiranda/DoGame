package com.blogspot.raulfmiranda.dogame;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;

import com.blogspot.raulfmiranda.dogame.login.Login;
import com.blogspot.raulfmiranda.dogame.login.LoginFragment;
import com.blogspot.raulfmiranda.dogame.login.LoginPresenter;
import com.blogspot.raulfmiranda.dogame.login.OnLoginInteractionListener;
import com.blogspot.raulfmiranda.dogame.login.RegisterFragment;
import com.blogspot.raulfmiranda.dogame.login.exception.ConfirmException;
import com.blogspot.raulfmiranda.dogame.login.exception.EmailException;
import com.blogspot.raulfmiranda.dogame.login.exception.NameException;
import com.blogspot.raulfmiranda.dogame.login.exception.PasswordException;
import com.google.firebase.auth.FirebaseAuth;

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
    if (FirebaseAuth.getInstance().getCurrentUser() != null) {
      showDoGame();
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
