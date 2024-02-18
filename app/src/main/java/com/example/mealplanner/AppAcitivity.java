package com.example.mealplanner;

import static com.google.android.material.internal.ContextUtils.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import getAllFavMeals.getAllFavMealsView.getAllFavMeals;
import profile.profileView.ProfileActivity;
import search.searchView.SearchActivity;

public class AppAcitivity extends AppCompatActivity implements  BottomNavigationView.OnNavigationItemSelectedListener
{
    DrawerLayout drawerLayout;

    Button home;
    Button category;
    Button country;
    ImageButton filter;

    ImageView notication;
    BottomNavigationView bottomNavigationView;

    NavigationView navigationView;

    FirebaseAuth firebaseAuth;
    private GoogleSignInClient gsc;
    private GoogleSignInOptions gso;
    private static final String TAG="AppActivity";

    @SuppressLint({"MissingInflatedId", "NonConstantResourceId"})
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_acitivity);
        filter=findViewById(R.id.filterBtnID);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        home=findViewById(R.id.homeBtnID);
        category=findViewById(R.id.categoryBtnID);
        country=findViewById(R.id.countryBtnID);
        notication=findViewById(R.id.notificationImageViewID);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        firebaseAuth=FirebaseAuth.getInstance();
        gso= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc= GoogleSignIn.getClient(this,gso);
        GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(this);
        if(account!= null){
            String name=account.getDisplayName();
            String email=account.getEmail();
        }


        notication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        home.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                NavController navController = Navigation.findNavController(AppAcitivity.this, R.id.nav_host_fragment);
                navController.navigate(R.id.oneMealFragment);
            }
        });
        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NavController navController = Navigation.findNavController(AppAcitivity.this, R.id.nav_host_fragment);
                navController.navigate(R.id.homeFragment);
            }
        });
        country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NavController navController = Navigation.findNavController(AppAcitivity.this, R.id.nav_host_fragment);
                navController.navigate(R.id.countryFragment);
            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                drawerLayout.closeDrawer(GravityCompat.START);
            }else{
                drawerLayout.openDrawer(GravityCompat.START);
            }
        }

        if (item.getItemId() == R.id.fav_page) {
            startActivity(new Intent(this, getAllFavMeals.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.fav_page) {
            startActivity(new Intent(this, getAllFavMeals.class));
            return true;
        } else if (itemId == R.id.home_page) {
            startActivity(new Intent(this, AppAcitivity.class));
            return true;
        } else if (itemId == R.id.profile_page) {
            startActivity(new Intent(this, ProfileActivity.class));
            return true;
        }else if (itemId == R.id.search_page) {
            startActivity(new Intent(this, SearchActivity.class));
            return true;
        }

        return false;
    }
}
