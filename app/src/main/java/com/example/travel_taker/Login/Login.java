package com.example.travel_taker.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.travel_taker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText emailId, password;
    TextView btnSignIn;
    TextView tvSignUp, passForgot
            ;

    FirebaseAuth mFirebaseAuth;
    public FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.email2);
        password = findViewById(R.id.password2);
        btnSignIn = findViewById(R.id.btnSignIn);
        tvSignUp = findViewById(R.id.tvsignUp);
        passForgot = findViewById(R.id.forgotPassword);

        passForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenti = new Intent(Login.this, passForgotActivity.class);
                startActivity(intenti);
            }
        });


        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if(mFirebaseUser != null)
                {
                    Toast.makeText(Login.this,"You are logged In",Toast.LENGTH_SHORT).show();
                    Intent i =new Intent(Login.this, Home.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(Login.this,"Please Login",Toast.LENGTH_SHORT).show();
                }

            }
        };

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();

                if(email.isEmpty())
                {
                    emailId.setError("Please enter E-mail");
                    emailId.requestFocus();
                }
                else if(pwd.isEmpty())
                {
                    password.setError("Please enter Password");
                    password.requestFocus();
                }
                else if(email.isEmpty() && pwd.isEmpty())
                {
                    Toast.makeText(Login.this,"Fields are empty",Toast.LENGTH_LONG).show();
                }
                else if(!(email.isEmpty() && pwd.isEmpty()))
                {
                    mFirebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(Login.this,"Login Error, Please login again.",Toast.LENGTH_LONG).show();

                            }
                            else {

                                startActivity(new Intent(Login.this, Home.class));

                            }
                        }
                    });

                }else {
                    Toast.makeText(Login.this,"Error Occurred",Toast.LENGTH_LONG).show();
                }
            }

        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, SignUp.class);
                startActivity(i);
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }


}