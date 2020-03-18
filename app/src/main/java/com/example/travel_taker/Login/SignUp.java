package com.example.travel_taker.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    EditText emailId, password, Uname, dob, mobile;
    Button btnSignUp;
    TextView tvSignIn;
    FirebaseAuth mFirebaseAuth;
    DatabaseReference databaseUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailId = findViewById(R.id.email);
        Uname = findViewById(R.id.Uname);
        dob = findViewById(R.id.dob);
        mobile = findViewById(R.id.mobile);
        password = findViewById(R.id.password);

        btnSignUp = findViewById(R.id.btnsignUp);
        tvSignIn = findViewById(R.id.tvsignIn);

        mFirebaseAuth = FirebaseAuth.getInstance();


        btnSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final String email = emailId.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                final String name = Uname.getText().toString().trim();
                final String birth = dob.getText().toString().trim();
                final String mob = mobile.getText().toString().trim();

                if (email.isEmpty()) {
                    emailId.setError("Please enter E-mail");
                    emailId.requestFocus();
                    return;
                }
                if (name.isEmpty()) {
                    password.setError("Please enter Name");
                    password.requestFocus();
                    return;
                }
                if (birth.isEmpty()) {
                    password.setError("Please enter Date Of Birth");
                    password.requestFocus();
                    return;
                }
                if (mob.isEmpty() || mob.length() != 10) {
                    password.setError("Please enter 10 digit Mobile Number");
                    password.requestFocus();
                    return;
                }
                if (pwd.isEmpty()) {
                    password.setError("Please enter Password");
                    password.requestFocus();
                    return;
                }

                mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        try {
                            User user = new User(name, mob, birth, email);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance()
                                            .getCurrentUser()
                                            .getUid())
                                    .setValue(user)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(SignUp.this, getString(R.string.Succ_register)+FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid()), Toast.LENGTH_LONG).show();
                                                Intent i = new Intent(SignUp.this, Login.class);
                                                startActivity(i);

                                            } else {
                                                Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                        } catch (Exception e)
                        {
                            Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }});
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUp.this, Login.class);
                startActivity(i);
            }
        });



    }
}
