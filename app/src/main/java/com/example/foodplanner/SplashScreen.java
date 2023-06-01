package com.example.foodplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {

    private static final long DELAY_MILLIS = 5000; // 3 seconds
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        LottieAnimationView animationView = findViewById(R.id.animation_view);

        animationView.setAnimation("lotti.json");
        animationView.playAnimation();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this , MainActivity.class );
                startActivity(intent);
                finish();
            }
        },DELAY_MILLIS);
    }
}
