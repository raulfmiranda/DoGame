package com.blogspot.raulfmiranda.dogame;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ProgressBar;

public abstract class BaseFragment extends Fragment {

  protected ProgressBar mProgressBar = null;
  protected View mView = null;

  @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
  public void showProgress() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
      int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

      if (mView != null) {
        mView.setVisibility(View.GONE);
        mView
            .animate()
            .setDuration(shortAnimTime)
            .alpha(0)
            .setListener(new AnimatorListenerAdapter() {
          @Override
          public void onAnimationEnd(Animator animation) {
            mView.setVisibility(View.GONE);
          }
        });
      }

      if (mProgressBar != null) {
        mProgressBar.setVisibility(View.VISIBLE);
        mProgressBar
            .animate()
            .setDuration(shortAnimTime)
            .alpha(1)
            .setListener(new AnimatorListenerAdapter() {
          @Override
          public void onAnimationEnd(Animator animation) {
            mProgressBar.setVisibility(View.VISIBLE);
          }
        });
      }
    } else {

      if (mProgressBar != null) {
        mProgressBar.setVisibility(View.VISIBLE);
      }
      if (mView != null) {
        mView.setVisibility(View.GONE);
      }
    }
  }

  @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
  public void hideProgress() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
      int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

      if (mView != null) {
        mView.setVisibility(View.VISIBLE);
        mView
            .animate()
            .setDuration(shortAnimTime)
            .alpha(1)
            .setListener(new AnimatorListenerAdapter() {
          @Override
          public void onAnimationEnd(Animator animation) {
            mView.setVisibility(View.VISIBLE);
          }
        });
      }

      if (mProgressBar != null) {
        mProgressBar.setVisibility(View.GONE);
        mProgressBar
            .animate()
            .setDuration(shortAnimTime)
            .alpha(0)
            .setListener(new AnimatorListenerAdapter() {
          @Override
          public void onAnimationEnd(Animator animation) {
            mProgressBar.setVisibility(View.GONE);
          }
        });
      }
    } else {

      if (mProgressBar != null) {
        mProgressBar.setVisibility(View.GONE);
      }
      if (mView != null) {
        mView.setVisibility(View.VISIBLE);
      }
    }
  }

}
