package profile.profileView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealplanner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import login.view.SignInActivity;
import model.MyDialogFragment;

public class ProfileActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    ImageView profileImage;
    TextView logout;

    TextView name;
    FirebaseUser firebaseUser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name=findViewById(R.id.userName);
        profileImage=findViewById(R.id.profileImageView);
        logout=findViewById(R.id.logoutTX);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        checkUser();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(firebaseUser==null){
                    DialogFragment dialogFragment = new MyDialogFragment();
                    dialogFragment.show(getSupportFragmentManager(), "MyDialogFragment");

                }else {

                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(ProfileActivity.this, SignInActivity.class));
                    Toast.makeText(ProfileActivity.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
                    finish();

                }
            }
        });
    }

    private void checkUser() {


        if(firebaseUser == null){
            name.setText("Guest");

        }else{
            String email=firebaseUser.getEmail();
            name.setText(email);

        }
    }
}