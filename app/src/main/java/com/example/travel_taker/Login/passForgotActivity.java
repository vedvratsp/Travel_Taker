package com.example.travel_taker.Login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.travel_taker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class passForgotActivity extends AppCompatActivity {

    Toolbar toolbar;
    ProgressBar progressBar;
    EditText Uemail;
    Button btnForgotpass ;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_forgot);

        toolbar = findViewById(R.id.forgetoolbar);
        progressBar = findViewById(R.id.progressbar);
        Uemail = findViewById(R.id.edUserEmail);
        btnForgotpass = findViewById(R.id.btnForgotPassword);
        toolbar.setTitle("Forgot Password....!!!" );
        firebaseAuth = FirebaseAuth.getInstance();

        btnForgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.sendPasswordResetEmail(Uemail.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressBar.setVisibility(View.GONE);
                        if(task.isSuccessful()){
                            Toast.makeText(passForgotActivity.this ,
                                    "Password sent successfully", Toast.LENGTH_LONG).show();
                        }else{

                            Toast.makeText(passForgotActivity.this , task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                         }
                    }
                });
            }
        });

    }
}
