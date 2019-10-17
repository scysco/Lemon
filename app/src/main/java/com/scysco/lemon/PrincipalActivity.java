package com.scysco.lemon;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.flexbox.FlexboxLayout;
import com.scysco.lemon.api.test;

public class PrincipalActivity extends AppCompatActivity {

    private static final String TAG = "PrincipalActivity";
    public static String PLACE;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        PLACE = "Products";

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));

        String a = new test("QfMBBlY1XlYU4HVxL9Ro").getName();

        System.out.println(a);

    }
    private void addButton() {
        FlexboxLayout fbContainer = findViewById(R.id.fbContainer);
        ImageView btnUno = new ImageView(this);
        btnUno.setImageResource(R.mipmap.ic_launcher);
        btnUno.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        fbContainer.addView(btnUno);
    }
}
