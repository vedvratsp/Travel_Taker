package com.example.travel_taker.Login;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travel_taker.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class Todoss extends AppCompatActivity {

    TextView titlePage, subtitle, endpage,setalarmTextView;
    Button addTodobutton;


    DatabaseReference reference;
    RecyclerView ourdoes;
    ArrayList<Mydoes> list;
    MydoesAdapter mydoesAdapter;

    String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();


    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currenthours;
    int currentminutes;
    String AMorPM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todoss);

        setalarmTextView = findViewById(R.id.setAlarmButton);


        addTodobutton = findViewById(R.id.addTodo);

        addTodobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Todoss.this,NewTaskActivity.class);
                startActivity(i);

            }
        });

        ourdoes = findViewById(R.id.ourdoes);
        ourdoes.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<Mydoes>();

        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(currentuser).child("todos");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Mydoes p = dataSnapshot1.getValue(Mydoes.class);
                    list.add(p);
                }
                mydoesAdapter = new MydoesAdapter(Todoss.this,list);
                ourdoes.setAdapter(mydoesAdapter);
                mydoesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"No data",Toast.LENGTH_SHORT).show();
            }
        });



    }
    public void SetAlarm(View view){
            Intent i = new Intent(Todoss.this,SetAlarmActivity.class);
            startActivity(i);
        }
}
