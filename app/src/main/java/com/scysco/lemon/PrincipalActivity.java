package com.scysco.lemon;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.flexbox.FlexboxLayout;
import com.google.firebase.auth.FirebaseAuth;

public class PrincipalActivity extends AppCompatActivity {

    private static final String TAG = "PrincipalActivity";
    public static String PLACE;

    private int counter = 1;

        /*
        Product p = new Product("Duquesa",500,10,20);
        FirebaseFirestore DB = FirebaseFirestore.getInstance();
        DB.collection("Products").document().set(p);

        final ObservableArrayList<Product> products = new ObservableArrayList<Product>();
        DocumentReference docRef = DB.collection("Products").document("87ZSkrom4Rz8G5I7xGEf");
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Product p = documentSnapshot.toObject(Product.class);
                assert p != null;
                System.out.println(p.getName());
                products.add(p);
            }
        });
        FirebaseFirestore DB = FirebaseFirestore.getInstance();

        final DocumentReference docRef = DB.collection(PLACE).document("example");
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    Log.d(TAG, "Current data: " + snapshot.getData());
                } else {
                    Log.d(TAG, "Current data: null");
                }

                String source = snapshot != null && snapshot.getMetadata().hasPendingWrites()
                        ? "Local" : "Server";

                if (snapshot != null && snapshot.exists()) {
                    Log.d(TAG, source + " data: " + snapshot.getData());
                } else {
                    Log.d(TAG, source + " data: null");
                }
            }
        });

         public void incrementSpace(View view) {
        FirebaseFirestore DB = FirebaseFirestore.getInstance();
        final DocumentReference docRef = DB.collection(PLACE).document("example");
        docRef.update("space_1", counter++)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });
    }

        */


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));

        PrincipalActivity.PLACE = "Products_1";


    }
    @Override
    protected void onStart(){
        super.onStart();
        Toast.makeText(PrincipalActivity.this, "Correo: "+ FirebaseAuth.getInstance().getCurrentUser().getEmail(),
                Toast.LENGTH_SHORT).show();
    }
    private void addButton() {
        FlexboxLayout fbContainer = findViewById(R.id.fbcFavorites);
        ImageView btnUno = new ImageView(this);
        btnUno.setImageResource(R.mipmap.ic_launcher);
        btnUno.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        fbContainer.addView(btnUno);
    }

}
