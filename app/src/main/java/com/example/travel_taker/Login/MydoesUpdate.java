package com.example.travel_taker.Login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travel_taker.R;

public class MydoesUpdate extends AppCompatActivity {

    EditText titledoes,descdoes,datedoes;
    Button btnUpdaeMydoes,Cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydoes_update);

        titledoes = findViewById(R.id.titledoesU);
        descdoes = findViewById(R.id.descdoesU);
        datedoes = findViewById(R.id.datedoesU);
    }
}
