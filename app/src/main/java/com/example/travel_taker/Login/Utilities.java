package com.example.travel_taker.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travel_taker.Login.CurrencyConverter.CurrencyConverter;
import com.example.travel_taker.R;

public class Utilities extends AppCompatActivity {

    Button todo,clock,currency;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utilities);
        tv = findViewById(R.id.tv1);
        todo = findViewById(R.id.Todo);
        clock = findViewById(R.id.worldC);
        currency = findViewById(R.id.CurrencyConv);


        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Utilities.this , worldClock.class);
                startActivity(i);
            }
        });

        currency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Utilities.this , CurrencyConverter.class);
                startActivity(i);
            }
        });

    }
}
