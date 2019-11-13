package com.scysco.lemon;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;
import com.scysco.lemon.databinding.ActivityPrincipalBinding;

public class PrincipalActivity extends AppCompatActivity {

    private static final String TAG = "PrincipalActivity";
    public static String PLACE;
    PrincipalViewModel principalViewModel;
    ActivityPrincipalBinding binding;

    public static float density;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_principal);
        density = getResources().getDisplayMetrics().density;
        principalViewModel = ViewModelProviders.of(this).get(PrincipalViewModel.class);

        binding.setViewmodel(principalViewModel);
        binding.setHandler(new PrincipalHandler(this));
        binding.setLifecycleOwner( this);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

        PrincipalActivity.PLACE = "Products_1";

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(PrincipalActivity.this, "Correo: " + FirebaseAuth.getInstance().getCurrentUser().getEmail(),
                Toast.LENGTH_SHORT).show();
    }


}
