package com.blogspot.raulfmiranda.dogame;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.blogspot.raulfmiranda.dogame.entity.Firebase;
import com.blogspot.raulfmiranda.dogame.quiz.Quiz;
import com.blogspot.raulfmiranda.dogame.quiz.QuizFragment;
import com.blogspot.raulfmiranda.dogame.ranking.RankingFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DogameActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int HOME    = 1;
    private static final int QUIZ    = 2;
    private static final int RANKING = 3;
    private static final int LIST    = 4;

    private int currentScreen;

    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogame);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView tvUserName = headerView.findViewById(R.id.tv_user_name);
        tvUserName.setText(Firebase.getInstance().getUser().getName());
        TextView tvUserMail = headerView.findViewById(R.id.tv_user_mail);
        tvUserMail.setText(Firebase.getInstance().getCurrentUser().getEmail());

        showHome();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (currentScreen == HOME)
                logout();
            else
                showHome();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dogame, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            //Todo: Criar as configurações do game
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            if (currentScreen != HOME)
                showHome();
        } else if (id == R.id.nav_quiz) {
            if (currentScreen != QUIZ)
                showQuiz();
        } else if (id == R.id.nav_ranking) {
            if (currentScreen != RANKING)
                showRanking();
        } else if (id == R.id.nav_list_dog) {

        } else if (id == R.id.nav_logout) {
            logout();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showHome() {
        markMenu(HOME);
        currentScreen = HOME;
        showFragment(R.id.activity_dogame, HomeFragment.newInstance());
    }

    public void showQuiz() {
        currentScreen = QUIZ;
        showFragment(R.id.activity_dogame, QuizFragment.newInstance());
    }

    private void showRanking() {
        currentScreen = RANKING;
        showFragment(R.id.activity_dogame, RankingFragment.newInstance());
    }

    private void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
            .setCancelable(false)
            .setMessage("Tem certeza que deseja sair?")
            .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Firebase.getInstance().logout();
                    showLogin();
                    dialogInterface.dismiss();
                }
            })
            .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    markMenu(currentScreen);
                    dialogInterface.dismiss();
                }
            });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void markMenu(int screen) {
        switch (screen) {
            case HOME:
                navigationView.setCheckedItem(R.id.nav_home);
                break;
            case QUIZ:
                navigationView.setCheckedItem(R.id.nav_quiz);
                break;
            case RANKING:
                navigationView.setCheckedItem(R.id.nav_ranking);
                break;
            case LIST:
                navigationView.setCheckedItem(R.id.nav_list_dog);
                break;
            default:
                break;
        }
    }

    private void showLogin() {
        Intent it = new Intent(DogameActivity.this, LoginActivity.class);
        startActivity(it);
        finish();
    }
}
