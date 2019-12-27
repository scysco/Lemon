package com.scysco.lemon;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FirstStartActivity extends AppCompatActivity {

    private static final String TAG = "FirstStartActivity";

    private FirebaseAuth mAuth;

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firststart);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));

        mAuth = FirebaseAuth.getInstance();

        ((EditText)findViewById(R.id.etPassword)).setOnEditorActionListener(
                (v, actionId, event) -> {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                            actionId == EditorInfo.IME_ACTION_DONE ||
                            event != null &&
                                    event.getAction() == KeyEvent.ACTION_DOWN &&
                                    event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                        if (event == null || !event.isShiftPressed()) {
                            signIn(null);
                            return true;
                        }
                    }
                    return false;
                }
        );

    }

    public void signIn(View view){
        Toast.makeText(FirstStartActivity.this, "Verificando Datos",Toast.LENGTH_SHORT).show();
        EditText user = findViewById(R.id.etUser);
        EditText pass = findViewById(R.id.etPassword);
        String email = user.getText().toString();
        String password = pass.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "signInWithEmail:success");
                        Toast.makeText(FirstStartActivity.this, "Autenticación Completada.",Toast.LENGTH_SHORT).show();
                        localInfo();
                        Intent intent = new Intent (getApplicationContext(), PrincipalActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(FirstStartActivity.this, "Autenticación Fallida, contacta a tu administrador.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void localInfo() {

        FirebaseUser user = mAuth.getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        assert user != null;
        db.collection("lemon_users").document(user.getUid()).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        assert document != null;
                        if (document.exists()) {
                            try {
                                FileOutputStream fOut = openFileOutput("SHOP.INFO",MODE_PRIVATE);
                                OutputStreamWriter osw = new OutputStreamWriter(fOut);
                                osw.write(document.get("shop").toString());
                                osw.flush();
                                osw.close();
                                InitActivity.SHOP = document.get("shop").toString();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.d(TAG, "No such document");
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                });
    }
}
