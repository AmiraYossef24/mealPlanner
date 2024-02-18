package profile.profileView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mealplanner.AppAcitivity;
import com.example.mealplanner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firebaseAuth=FirebaseAuth.getInstance();
        checkUser();
    }

    private void checkUser() {

        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser == null){
            startActivity(new Intent(this,AppAcitivity.class));
            finish();
        }else{
            String email=firebaseUser.getEmail();
            Toast.makeText(this, "welcome"+email, Toast.LENGTH_SHORT).show();
        }
    }
}