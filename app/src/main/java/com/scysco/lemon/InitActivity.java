package com.scysco.lemon;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class InitActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));

        ImageView spinner = findViewById(R.id.spinner);
        new Handler().postDelayed(new Runnable(){
            public void run(){
                Intent intent = new Intent (getApplicationContext(), PrincipalActivity.class);
                startActivity(intent);
                finish();
            };
        }, 1500);

        RotateAnimation rotate = new RotateAnimation(0, 780,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);

        rotate.setDuration(1600);
        rotate.setRepeatCount(Animation.INFINITE);
        spinner.setAnimation(rotate);
    }
}
