package com.example.travel_taker.Login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travel_taker.R;

public class splashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 7000;

    // variables
    Animation topanimation, bottomAnimation;
    //ImageView image;
    TextView logo, slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        //animations

        topanimation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //hooks

        // image = findViewById(R.id.vaccy);
        logo =findViewById(R.id.explore);
        slogan =findViewById(R.id.slogan);

        //image.setAnimation(topanimation);
        logo.setAnimation(bottomAnimation);
        slogan.setAnimation(topanimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splashScreen.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        },SPLASH_SCREEN);



    }
}
