package signup.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealplanner.AppAcitivity;
import com.example.mealplanner.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import login.view.SignInActivity;
import model.HelperUser;
import profile.profileView.ProfileActivity;

public class SignUpActivity extends AppCompatActivity {

    EditText username;
    EditText email;
    EditText phone;
    EditText pass;
    EditText conPass;
    Button signUp;

    TextView redirectSignInTx;

    Button guistBtn;
    ImageButton googleBtn;

    FirebaseDatabase database;
    DatabaseReference reference;
    private FirebaseAuth firebaseAuth;
    private GoogleSignInClient gsc;
    private  GoogleSignInOptions gso;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        redirectSignInTx=findViewById(R.id.singUpTxID);
        guistBtn=findViewById(R.id.guistBtnId);
        signUp=findViewById(R.id.signInBtnId);
        googleBtn = findViewById(R.id.googleBtnID);
        googleBtn.setZ(SignInButton.SIZE_STANDARD);

        username=findViewById(R.id.firstNameTxId);
        email=findViewById(R.id.emailTxId);
        phone=findViewById(R.id.phoneTxId);
        pass=findViewById(R.id.passsTxId);
        conPass=findViewById(R.id.conPasssTxId);
        //firebase
        database= FirebaseDatabase.getInstance();
        reference=database.getReference("Users");

        firebaseAuth=FirebaseAuth.getInstance();
        gso= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc=GoogleSignIn.getClient(this,gso);

        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInByGoogle();

            }


        });


        redirectSignInTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRedirectSignInTx();
            }
        });

        guistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRedirectGuistBtn();
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSignUp();
            }
        });

    }

    private void signInByGoogle() {

        Intent signInIntent=gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1000){
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
            navigateToSecondActivity();
            try {
                task.getResult(ApiException.class);
            } catch (ApiException e) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void navigateToSecondActivity(){
        finish();
        Intent intent=new Intent(SignUpActivity.this,AppAcitivity.class);
        startActivity(intent);
    }

    public void setRedirectSignInTx(){
        Intent intent=new Intent(this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

    public void setRedirectGuistBtn(){
        Intent intent=new Intent(this, AppAcitivity.class);
        startActivity(intent);
        finish();
    }
    public boolean isPassEqual(String pass,String conPass){
        if (pass.equals(conPass)){
            return true;
        }
        return false;
    }

    public void setSignUp(){


        String _username=username.getText().toString().trim();
        String _email=email.getText().toString().trim();
        String _phone=phone.getText().toString().trim();
        String _pass=pass.getText().toString().trim();
        String _conPass=conPass.getText().toString().trim();

        if(isAllFilled() && isPassEqual(_pass,_conPass)) {

            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("name", _username);
            hashMap.put("email", _email);
            hashMap.put("phone", _phone);
            hashMap.put("password", _pass);

            firebaseAuth.createUserWithEmailAndPassword(_email, _pass)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(SignUpActivity.this, "auth success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                            startActivity(intent);
                            finish();


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
        }else {
            pass.setError("password does not match");
        }

    }

    public  boolean isAllFilled(){
        String usrname=username.getText().toString().trim();
        String _email=email.getText().toString().trim();
        String _phone=phone.getText().toString().trim();
        String _pass=pass.getText().toString().trim();
        String _conPass=conPass.getText().toString().trim();
        if (usrname.isEmpty()){

            username.setError("Required Field");
            return false;
        }
        if (_email.isEmpty()){

            email.setError("Required Field");
            return false;
        }
        if (_phone.isEmpty()){

            phone.setError("Required Field");
            return false;
        }
        if (_pass.isEmpty()){

            pass.setError("Required Field");
            return false;
        }
        if (_conPass.isEmpty()){

            conPass.setError("Required Field");
            return false;
        }
        return true;

    }



}