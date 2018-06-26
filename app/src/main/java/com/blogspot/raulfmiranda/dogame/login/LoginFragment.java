package com.blogspot.raulfmiranda.dogame.login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.blogspot.raulfmiranda.dogame.BaseFragment;
import com.blogspot.raulfmiranda.dogame.R;
import com.blogspot.raulfmiranda.dogame.login.exception.EmailException;
import com.blogspot.raulfmiranda.dogame.login.exception.PasswordException;

public class LoginFragment extends BaseFragment implements TextView.OnEditorActionListener, View.OnClickListener {

  private OnLoginInteractionListener mListener;

  private TextInputEditText mEmail;
  private TextInputEditText mPassword;

  @NonNull
  public static LoginFragment newInstance() {
    return new LoginFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_login, container, false);

    view
        .findViewById(R.id.bt_login)
        .setOnClickListener(this);
    view
        .findViewById(R.id.bt_signup)
        .setOnClickListener(this);

    mEmail       = view.findViewById(R.id.login_email);
    mPassword    = view.findViewById(R.id.login_password);
    mView        = view.findViewById(R.id.ll_login);
    mProgressBar = view.findViewById(R.id.pb_login);

    mPassword.setOnEditorActionListener(this);

    return view;
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnLoginInteractionListener) {
      mListener = (OnLoginInteractionListener) context;
    } else {
      throw new RuntimeException(context.toString()
          + " must implement OnLoginInteractionListener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.bt_signup:
        mListener.showRegister();
        break;
      case R.id.bt_login:
        tryToLogin();
        break;
      default:
        break;
    }
  }

  @Override
  public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
    if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
      tryToLogin();
      return true;
    }
    return false;
  }



  private void tryToLogin() {
    String email    = mEmail.getText().toString();
    String password = mPassword.getText().toString();

    mEmail.setError(null);
    mPassword.setError(null);

    try {
      mListener.requestLogin(email, password);
    } catch (EmailException ee) {
      mEmail.setError(ee.getMessage());
      mEmail.requestFocus();
    } catch (PasswordException pe) {
      mPassword.setError(pe.getMessage());
      mPassword.requestFocus();
    }
  }
}
