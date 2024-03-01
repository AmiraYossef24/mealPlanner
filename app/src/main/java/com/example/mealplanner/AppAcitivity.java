package com.example.mealplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
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
import login.view.SignInActivity;
import model.MyDialogFragment;
import profile.profileView.ProfileActivity;
import search.searchView.SearchActivity;

public class AppAcitivity extends AppCompatActivity implements  BottomNavigationView.OnNavigationItemSelectedListener
{
    DrawerLayout drawerLayout;


    ImageButton filter;
    SearchView searchView;


    ImageView menuIcon;
    ImageView logout;
    ImageView imageProfile;
    TextView headerTextView;
    BottomNavigationView bottomNavigationView;

    NavigationView navigationView;

    FirebaseAuth firebaseAuth;
    private GoogleSignInClient gsc;
    private GoogleSignInOptions gso;
    Chip homeBtn;
    View headerView;
    Chip categoryBtn;
    Chip countryBtn;
    Chip ingradiantsBtn;

    private  GoogleSignInAccount account;
    private static final String TAG="AppActivity";
    public  static final String CHANNEL_ID="x_channelId";

    @SuppressLint({"MissingInflatedId", "NonConstantResourceId"})
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_acitivity);
        filter=findViewById(R.id.filterBtnID);
        //logout=findViewById(R.id.notificationImageViewID);
        drawerLayout = findViewById(R.id.drawer_layout);
        imageProfile=findViewById(R.id.profileImageView);
        navigationView = findViewById(R.id.nav_view);
        headerView = navigationView.getHeaderView(0);
        headerTextView = headerView.findViewById(R.id.userName);
        searchView=findViewById(R.id.searchViewID);
        homeBtn=findViewById(R.id.homeChip);
        categoryBtn=findViewById(R.id.cateChip);
        countryBtn=findViewById(R.id.countryChip);
        menuIcon=findViewById(R.id.menuecircleImageView);
        ingradiantsBtn=findViewById(R.id.ingraChip);
        Menu menu = navigationView.getMenu();
        MenuItem menuItem = menu.findItem(R.id.logout);

        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startActivity(new Intent(AppAcitivity.this, ProfileActivity.class));
                Log.i(TAG, "after line 116: ");
                return true;
            }
        });
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


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        firebaseAuth=FirebaseAuth.getInstance();
        gso= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc= GoogleSignIn.getClient(this,gso);
        account=GoogleSignIn.getLastSignedInAccount(this);

        if(account!= null){
            String name=account.getDisplayName();
            String email=account.getEmail();
            headerTextView.setText(email);
        }

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
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

        imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkUser()) {
                    Intent pickPhotoIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhotoIntent, 1);
                }else {
                    DialogFragment dialogFragment = new MyDialogFragment();
                    dialogFragment.show(getSupportFragmentManager(), "MyDialogFragment");
                }
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

    @SuppressLint("MissingPermission")
    private  void displayNotification(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID,"channel display name", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("my cahnnel discription");
            NotificationManager nm=getSystemService(NotificationManager.class);
            nm.createNotificationChannel(notificationChannel);
        }
        Intent intent=new Intent(this,CalendarActivity.class);
        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,0);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(getBaseContext(),CHANNEL_ID);
        builder.setSmallIcon(R.drawable.notification_thin_icon)
                .setContentTitle("Title")
                .setContentText("pla pla")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("my data"))
                .addAction(R.drawable.back_icon,"Show",pendingIntent);
        NotificationManagerCompat nmc=NotificationManagerCompat.from(this);
        nmc.notify(10,builder.build());
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
        else if(itemId==R.id.profile){
            if(checkUser()){
                Log.i(TAG, "line 186 return : ");
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                return true;
            }else {
                DialogFragment dialogFragment = new MyDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "MyDialogFragment");
            }

        }
        else if(itemId==R.id.logout){
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(AppAcitivity.this, "clicked", Toast.LENGTH_SHORT).show();
                    FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();

                    if(firebaseUser==null){

                        DialogFragment dialogFragment = new MyDialogFragment();
                        dialogFragment.show(getSupportFragmentManager(), "MyDialogFragment");

                    }else {

                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(AppAcitivity.this, SignInActivity.class));
                        Toast.makeText(AppAcitivity.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }
            });
        }

        return false;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            imageProfile.setImageURI(selectedImageUri);
        }
    }
}
