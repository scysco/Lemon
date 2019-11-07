package com.scysco.lemon;

import android.animation.ValueAnimator;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;
import com.scysco.lemon.databinding.ActivityPrincipalBinding;
import com.scysco.lemon.viewmodel.PrincipalViewModel;

public class PrincipalActivity extends AppCompatActivity {

    private static final String TAG = "PrincipalActivity";
    public static String PLACE;
    PrincipalViewModel principalViewModel;
    ActivityPrincipalBinding binding;

    public static float density;

    TextView test;

    private LinearLayout pnlConfig;
    private LinearLayout pnlHome;

    private int homeSelected = 0;
    private int configSelected = 0;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_principal);
        density = getResources().getDisplayMetrics().density;
        principalViewModel = ViewModelProviders.of(this).get(PrincipalViewModel.class);

        binding.setViewmodel(principalViewModel);
        binding.setLifecycleOwner( this);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

        PrincipalActivity.PLACE = "Products_1";

        pnlConfig = findViewById(R.id.pnlConfig);
        pnlHome = findViewById(R.id.pnlHome);

    }

    public void actionTest(View view){
        principalViewModel.tvTest.set(principalViewModel.tvTest.get()+1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(PrincipalActivity.this, "Correo: " + FirebaseAuth.getInstance().getCurrentUser().getEmail(),
                Toast.LENGTH_SHORT).show();
    }


    public void actionHomeButtons(View view){
        preAnimateButton(view,homeSelected,pnlHome.getHeight());
        Log.d(TAG, "actionHomeButtons: "+homeSelected);
    }

    public void actionConfigButtons(View view){
        preAnimateButton(view,configSelected,pnlConfig.getHeight());
    }

    public void preAnimateButton(View view,int selected, int parentHeight){
        if (selected != view.getId()) {
            float h =  parentHeight - (136 * getResources().getDisplayMetrics().density);
            animateButton(view, (int) h);
            if (selected != 0) {
                float he = 60 * getResources().getDisplayMetrics().density;
                animateButton(findViewById(selected), (int) he);
            }
            if (selected == homeSelected)homeSelected = view.getId();
            if (selected == configSelected)configSelected = view.getId();
        }
    }

    public void animateButton(View view, int height){
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        ValueAnimator animator = ValueAnimator.ofInt(params.height, height);
        animator.addUpdateListener(animation ->{
                params.height = (Integer) animation.getAnimatedValue();
                view.setLayoutParams(params);
        });
        animator.setDuration(300);
        animator.start();
    }

    public void showPnl(View view) {
        if (view.getId() == R.id.btnConfig)
        if (pnlConfig.getVisibility() == View.GONE){
                pnlConfig.setVisibility(View.VISIBLE);
                pnlHome.setVisibility(View.GONE);
            }
            else pnlConfig.setVisibility(View.GONE);

        if (view.getId() == R.id.btnHome)
            if (pnlHome.getVisibility() == View.GONE){
                pnlConfig.setVisibility(View.GONE);
                pnlHome.setVisibility(View.VISIBLE);
            }
            else pnlHome.setVisibility(View.GONE);
    }


}
