package com.scysco.lemon;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class InitActivity extends AppCompatActivity {

    public static String SHOP;
    private int threadDuration;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));

        try {
            FileInputStream fIn = openFileInput("SHOP.INFO");
            InputStreamReader isr = new InputStreamReader(fIn);
            BufferedReader inBuff = new BufferedReader(isr);
            String inputLine = inBuff.readLine();
            inBuff.close();
            InitActivity.SHOP = inputLine;
            if (!inputLine.equals("default"))
                threadDuration = 0;
            else
                threadDuration = 2500;
            Log.e("File Reading stuff", "success = "+inputLine);
        } catch (IOException ioe){
            try {
                FileOutputStream fOut = openFileOutput("SHOP.INFO",MODE_PRIVATE);
                OutputStreamWriter osw = new OutputStreamWriter(fOut);
                osw.write("default");
                osw.flush();
                osw.close();
                threadDuration = 3500;
            } catch (IOException e) {
                e.printStackTrace();
            }
            InitActivity.SHOP = "default";
        }

        spinnerAnimation();
        initAnotherActivity();

    }

    private void initAnotherActivity() {
        new Handler().postDelayed(() -> {
            if(!InitActivity.SHOP.equals("default")){
                Intent intent = new Intent (getApplicationContext(), PrincipalActivity.class);
                startActivity(intent);
                finish();
            }else{
                Intent intent = new Intent (getApplicationContext(), FirstStartActivity.class);
                startActivity(intent);
                finish();
            }

        }, threadDuration);
    }

    private void spinnerAnimation() {
        ImageView spinner = findViewById(R.id.spinner);
        RotateAnimation rotate = new RotateAnimation(0, 1180,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);

        rotate.setDuration(threadDuration+100);
        rotate.setRepeatCount(Animation.INFINITE);
        spinner.setAnimation(rotate);
    }
}
