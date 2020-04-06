package com.example.travel_taker.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travel_taker.R;
import com.google.firebase.auth.FirebaseAuth;
import com.uber.sdk.android.core.UberSdk;
import com.uber.sdk.android.rides.RideRequestButton;
import com.uber.sdk.core.auth.Scope;
import com.uber.sdk.rides.client.SessionConfiguration;

import java.util.Arrays;

public class Home extends AppCompatActivity {
    ImageButton logOut, utility,place,flight,hotel;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        utility =findViewById(R.id.UtilityWidget);
         logOut = findViewById(R.id.logOut);
         place = findViewById(R.id.place);
      //  flight = findViewById(R.id.plane1);
        hotel = findViewById(R.id.hotel1);

        SessionConfiguration config = new SessionConfiguration.Builder()
                .setClientId("89jvi1QQQ7YNUb02WJI_AeiMlqrqJdw-")
                .setServerToken("1VnrfjZENfFgaE9U2_I6YO85ss68_1vx9730q2c5")
                //.setRedirectUri("")
                .setScopes(Arrays.asList(Scope.RIDE_WIDGETS))
                .setEnvironment(SessionConfiguration.Environment.PRODUCTION)
                .build();

        UberSdk.initialize(config);

        RideRequestButton requestButton = new RideRequestButton(Home.this);
        LinearLayout layout = new LinearLayout(this);
        layout.addView(requestButton);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(Home.this, MainActivity.class);
                startActivity(i);
            }
        });



        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this , MapsActivity.class);
                startActivity(i);
            }
        });


        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this , HotelFlightActivity.class);
                startActivity(i);
            }
        });


        utility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, Utilities.class);
                startActivity(i);
            }
        });


    }
}
