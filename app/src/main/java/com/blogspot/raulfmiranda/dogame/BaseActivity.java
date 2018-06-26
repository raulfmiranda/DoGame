package com.blogspot.raulfmiranda.dogame;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

  private Fragment fragment;

//  protected void showFragment(int activity, Fragment fragment) {
  public void showFragment(int activity, Fragment fragment) {
    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction ft = fm.beginTransaction();
    if (this.fragment != null) {
      ft.remove(this.fragment);
    }
    this.fragment = fragment;
    ft.add(activity, fragment);
    ft.commit();
  }
}
