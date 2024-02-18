package login.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealplanner.AppAcitivity;
import com.example.mealplanner.R;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import signup.view.SignUpActivity;

public class SignInActivity extends AppCompatActivity {

    EditText email;
    EditText pass;

    TextView redirectSignUpTX;
    Button signin;
    private FirebaseAuth firebaseAuth;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        redirectSignUpTX=findViewById(R.id.singUpTxID);
        email=findViewById(R.id.emailTxId);
        pass=findViewById(R.id.passsTxId);
        signin=findViewById(R.id.signInBtnId);
        firebaseAuth=FirebaseAuth.getInstance();

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               setSignIn();

            }
        });



        redirectSignUpTX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRedirectSignUpTx();
            }
        });
    }
    public void setRedirectSignUpTx(){
        Intent intent=new Intent(this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    public void setSignIn() {
        String _email = email.getText().toString().trim();
        String _pass = pass.getText().toString().trim();

        firebaseAuth.signInWithEmailAndPassword(_email,_pass)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(SignInActivity.this, "Sign in Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(), AppAcitivity.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignInActivity.this, "Failed"+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
//        Query checkUserDatabase = reference.orderByChild("username").equalTo(_username);
//        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    Log.i("TAG", ">>>>>>>>>>>>>onDataChange:exists ");
//
//                    String passDb = snapshot.child(_username).child("pass").getValue(String.class);
//                    Log.i("TAG", "the object are: "+Objects.equal(passDb, _pass));
//                    if (Objects.equal(passDb, _pass)) {
//                        Intent intent = new Intent(getApplicationContext(), AppAcitivity.class);
//                        startActivity(intent);
//
//
//                    } else {
//                        pass.setError("Invalid Password");
//                        pass.requestFocus();
//                    }
//
//                }
//                else{
//                    username.setError("User does not exist");
//                    username.requestFocus();
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }

//    public boolean isValidate(String _username,String _pass){
//        if(_username.isEmpty()){
//            username.setError("Required Field");
//            return false;
//        }
//        if(_pass.isEmpty()){
//            pass.setError("Required Field");
//            return false;
//        }
//        return true;
//    }
}