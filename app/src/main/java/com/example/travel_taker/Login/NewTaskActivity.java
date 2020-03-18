package com.example.travel_taker.Login;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.travel_taker.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Random;

public class NewTaskActivity extends AppCompatActivity {
    TextView titledoes,descdoes,datedoes,Choosedate;
    Button createButton,cancelButton;
    DatabaseReference reference;
    Integer doesNum = new Random().nextInt();
    String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayofmonth;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);


       // Choosedate = findViewById(R.id.datepickerTextView);
        titledoes = findViewById(R.id.titledoes);
        descdoes = findViewById(R.id.descdoes);
        datedoes = findViewById(R.id.datedoes);

        datedoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayofmonth = calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(NewTaskActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        datedoes.setText(dayOfMonth + "/" + (month + 1) + "/" + (year));
                    }
                },year,month,dayofmonth);
                datePickerDialog.show();
            }
        });



        createButton = findViewById(R.id.create);
        cancelButton = findViewById(R.id.cancel);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NewTaskActivity.this,Todoss.class);
                startActivity(i);
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference = FirebaseDatabase.getInstance().getReference().child("Users").child(currentuser).child("todos").child("Does"+doesNum);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("titledoes").setValue(titledoes.getText().toString());
                        dataSnapshot.getRef().child("descdoes").setValue(descdoes.getText().toString());
                        dataSnapshot.getRef().child("datedoes").setValue(datedoes.getText().toString());

                        Intent i = new Intent(NewTaskActivity.this,Todoss.class);
                        startActivity(i);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
