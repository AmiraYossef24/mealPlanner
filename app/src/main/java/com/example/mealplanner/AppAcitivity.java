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
import androidx.fragment.app.DialogFragment;
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
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.chip.Chip;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import calendar.calendarView.CalendarActivity;
import getAllFavMeals.getAllFavMealsView.getAllFavMeals;
import model.MyDialogFragment;
import profile.profileView.ProfileActivity;
import search.searchView.SearchActivity;
import androidx.fragment.app.DialogFragment;

public class AppAcitivity extends AppCompatActivity implements  BottomNavigationView.OnNavigationItemSelectedListener
{
    DrawerLayout drawerLayout;


    ImageButton filter;
    SearchView searchView;

    ImageView notication;
    BottomNavigationView bottomNavigationView;

    NavigationView navigationView;

    FirebaseAuth firebaseAuth;
    private GoogleSignInClient gsc;
    private GoogleSignInOptions gso;
    Chip homeBtn;
    Chip categoryBtn;
    Chip countryBtn;
    Chip ingradiantsBtn;

    private  GoogleSignInAccount account;
    private static final String TAG="AppActivity";

    @SuppressLint({"MissingInflatedId", "NonConstantResourceId"})
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_acitivity);
        filter=findViewById(R.id.filterBtnID);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        searchView=findViewById(R.id.searchViewID);

        homeBtn=findViewById(R.id.homeChip);
        categoryBtn=findViewById(R.id.cateChip);
        countryBtn=findViewById(R.id.countryChip);
        ingradiantsBtn=findViewById(R.id.ingraChip);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SearchActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        notication=findViewById(R.id.notificationImageViewID);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        firebaseAuth=FirebaseAuth.getInstance();
        gso= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc= GoogleSignIn.getClient(this,gso);
        account=GoogleSignIn.getLastSignedInAccount(this);

        if(account!= null){
            String name=account.getDisplayName();
            String email=account.getEmail();
        }


        notication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                NavController navController = Navigation.findNavController(AppAcitivity.this, R.id.nav_host_fragment);
                navController.navigate(R.id.oneMealFragment);
            }
        });
        categoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NavController navController = Navigation.findNavController(AppAcitivity.this, R.id.nav_host_fragment);
                navController.navigate(R.id.homeFragment);
            }
        });
        countryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NavController navController = Navigation.findNavController(AppAcitivity.this, R.id.nav_host_fragment);
                navController.navigate(R.id.countryFragment);
            }
        });

        ingradiantsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NavController navController = Navigation.findNavController(AppAcitivity.this, R.id.nav_host_fragment);
                navController.navigate(R.id.ingradiantFragment);
            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private boolean checkUser() {

        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser == null){
            return false;
        }else  {
            return true;
        }

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
            if(checkUser()){
                Log.i(TAG, "line 186 return : ");
                startActivity(new Intent(getApplicationContext(), getAllFavMeals.class));
                return true;
            }else {
                DialogFragment dialogFragment = new MyDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "MyDialogFragment");
            }

        } else if (itemId == R.id.home_page) {
            startActivity(new Intent(this, AppAcitivity.class));
            return true;
        } else if (itemId == R.id.profile_page) {
            if(checkUser()){
                Log.i(TAG, "line 198 return : ");
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                return true;
            }else {
                DialogFragment dialogFragment = new MyDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "MyDialogFragment");
            }
        }else if (itemId == R.id.search_page) {
            startActivity(new Intent(this, SearchActivity.class));
            return true;
        }else if (itemId == R.id.calender_page) {
            if(checkUser()){
                Log.i(TAG, "line 186 return : ");
                startActivity(new Intent(getApplicationContext(), CalendarActivity.class));
                return true;
            }else {
                DialogFragment dialogFragment = new MyDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "MyDialogFragment");
            }
        }

        return false;
    }
}
