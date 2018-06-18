package com.blogspot.raulfmiranda.dogame.login;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blogspot.raulfmiranda.dogame.BaseFragment;
import com.blogspot.raulfmiranda.dogame.R;
import com.blogspot.raulfmiranda.dogame.login.exception.ConfirmException;
import com.blogspot.raulfmiranda.dogame.login.exception.EmailException;
import com.blogspot.raulfmiranda.dogame.login.exception.NameException;
import com.blogspot.raulfmiranda.dogame.login.exception.PasswordException;

public class RegisterFragment extends BaseFragment implements View.OnClickListener {

  private OnLoginInteractionListener mListener;

  private TextInputEditText mName;
  private TextInputEditText mEmail;
  private TextInputEditText mPassword;
  private TextInputEditText mConfirmationPassword;

  @NonNull
  public static RegisterFragment newInstance() {
    return new RegisterFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.register_fragment, container, false);

    view
        .findViewById(R.id.bt_register)
        .setOnClickListener(this);

    mName                 = view.findViewById(R.id.register_name);
    mEmail                = view.findViewById(R.id.register_email);
    mPassword             = view.findViewById(R.id.register_password);
    mConfirmationPassword = view.findViewById(R.id.register_confirm_password);
    mView                 = view.findViewById(R.id.ll_register);
    mProgressBar          = view.findViewById(R.id.pb_register);

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
    String name                 = mName.getText().toString();
    String email                = mEmail.getText().toString();
    String password             = mPassword.getText().toString();
    String confirmationPassword = mConfirmationPassword.getText().toString();

    mName.setError(null);
    mEmail.setError(null);
    mPassword.setError(null);
    mConfirmationPassword.setError(null);

    try {
      mListener.registerUser(name, email, password, confirmationPassword);
    } catch (NameException ne) {
      mEmail.setError(ne.getMessage());
      mEmail.requestFocus();
    } catch (EmailException ee) {
      mEmail.setError(ee.getMessage());
      mEmail.requestFocus();
    } catch (PasswordException pe) {
      mPassword.setError(pe.getMessage());
      mPassword.requestFocus();
    } catch (ConfirmException ce) {
      mConfirmationPassword.setError(ce.getMessage());
      mConfirmationPassword.requestFocus();
    }
  }
}
