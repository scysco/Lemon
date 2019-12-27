package com.scysco.lemon;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.scysco.lemon.api.Product;
import com.scysco.lemon.api.ProductAdapter;
import com.scysco.lemon.databinding.ActivityPrincipalBinding;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class PrincipalActivity extends AppCompatActivity {

    private static final String TAG = "PrincipalActivity";

    public static String VERSION_APP_SALES;
    public static String VERSION_ICONS;
    public static String RES_PATCH;

    PrincipalViewModel principalViewModel;
    ActivityPrincipalBinding binding;
    FirebaseAuth mAuth;

    public static List<Product> products;
    public static float density;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_principal);
        density = getResources().getDisplayMetrics().density;
        principalViewModel = ViewModelProviders.of(this).get(PrincipalViewModel.class);
        RES_PATCH = getFilesDir().getPath();

        binding.setViewmodel(principalViewModel);
        binding.setHandler(new PrincipalHandler(this));
        binding.setLifecycleOwner( this);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

        RecyclerView recyclerView = binding.searchList;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        products = new ArrayList<>();
        recyclerView.setAdapter(new ProductAdapter(products));

        mAuth = FirebaseAuth.getInstance();
        versions();

        getProducts();

    }

    private void getProducts() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("lemon_products").get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot pl : task.getResult()) {
                            db.collection("lemon_shops").document(InitActivity.SHOP).collection("products").document(pl.getId()).get()
                                    .addOnCompleteListener(task2 -> {
                                        if (task2.isSuccessful()) {
                                            DocumentSnapshot ps = task2.getResult();
                                            if (ps.exists()) {
                                                products.add(new Product(pl.getId(),
                                                        pl.getString("name"),
                                                        pl.getDouble("price"),
                                                        pl.getDouble("cost"),
                                                        ps.getString("stock")));
                                            } else {
                                                Log.e(TAG, "No such document");
                                            }
                                        } else {
                                            Log.e(TAG, "get failed with ", task2.getException());
                                        }
                                    });
                            Log.d(TAG, pl.getId() + " => " + pl.getData());
                        }
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });
    }

    private void versions() {
        try {
            FileInputStream fIn_app = openFileInput("APP.VERSION");
            FileInputStream fIn_res = openFileInput("RESOURCES.VERSION");

            InputStreamReader isr = new InputStreamReader(fIn_app);
            BufferedReader inBuff = new BufferedReader(isr);
            String inputLine = inBuff.readLine();
            inBuff.close();
            PrincipalActivity.VERSION_APP_SALES = inputLine;

            isr = new InputStreamReader(fIn_res);
            inBuff = new BufferedReader(isr);
            inputLine = inBuff.readLine();
            inBuff.close();
            PrincipalActivity.VERSION_ICONS = inputLine;

            Log.e("File Reading stuff", "success = "+inputLine);

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("lemon").document("info").get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            assert document != null;
                            if (document.exists()) {
                                double verapp = (double) document.get("version_app_sales");
                                double vericons = (double) document.get("version_icons");
                                double inappverap = Double.parseDouble(PrincipalActivity.VERSION_APP_SALES);
                                double inappvericons = Double.parseDouble(PrincipalActivity.VERSION_ICONS);
                                if (verapp>inappverap){
                                    PrincipalActivity.VERSION_APP_SALES = String.valueOf(verapp);
                                    try {
                                        FileOutputStream fOut_app = openFileOutput("APP.VERSION",MODE_PRIVATE);
                                        OutputStreamWriter osw = new OutputStreamWriter(fOut_app);
                                        osw.write(PrincipalActivity.VERSION_APP_SALES);
                                        osw.flush();
                                        osw.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if (vericons>inappvericons){
                                    PrincipalActivity.VERSION_ICONS = String.valueOf(vericons);
                                    try {
                                        FileOutputStream fOut_res = openFileOutput("RESOURCES.VERSION",MODE_PRIVATE);
                                        OutputStreamWriter osw = new OutputStreamWriter(fOut_res);
                                        osw.write(PrincipalActivity.VERSION_ICONS);
                                        osw.flush();
                                        osw.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                    updateResources();
                                }

                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    });

        } catch (IOException ioe){
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("lemon").document("info").get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            assert document != null;
                            if (document.exists()) {
                                try {
                                    FileOutputStream fOut_app = openFileOutput("APP.VERSION",MODE_PRIVATE);
                                    FileOutputStream fOut_res = openFileOutput("RESOURCES.VERSION",MODE_PRIVATE);

                                    OutputStreamWriter osw = new OutputStreamWriter(fOut_app);
                                    PrincipalActivity.VERSION_APP_SALES = document.get("version_app_sales").toString();
                                    osw.write(PrincipalActivity.VERSION_APP_SALES);
                                    osw.flush();
                                    osw.close();

                                    osw = new OutputStreamWriter(fOut_res);
                                    PrincipalActivity.VERSION_ICONS = document.get("version_icons").toString();
                                    osw.write(PrincipalActivity.VERSION_ICONS);
                                    osw.flush();
                                    osw.close();

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
            updateResources();
        }
    }

    private void updateResources() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference zipref = storageRef.child("prodicns.zip");

        File localFile = new File(getFilesDir(),"prodicns.zip");

        String a = getFilesDir().getPath()+"/prodicns.zip";

        zipref.getFile(localFile)
                .addOnSuccessListener(taskSnapshot -> Toast.makeText(PrincipalActivity.this, "file created: "+ a, Toast.LENGTH_SHORT).show())
                .addOnFailureListener(exception -> Toast.makeText(PrincipalActivity.this, "An error accoured", Toast.LENGTH_SHORT).show());
        if (unpackZip(getFilesDir().getPath()+"/","prodicns.zip"))
            Log.e(TAG, "onCreate: se creo!");
        else
            Log.e(TAG, "onCreate: no se creo!!");
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private boolean unpackZip(String path, String zipname){
        InputStream is;
        ZipInputStream zis;
        try
        {
            String filename;
            is = new FileInputStream(path + zipname);
            zis = new ZipInputStream(new BufferedInputStream(is));
            ZipEntry ze;
            byte[] buffer = new byte[1024];
            int count;

            while ((ze = zis.getNextEntry()) != null)
            {
                filename = ze.getName();

                // Need to create directories if not exists, or
                // it will generate an Exception...
                if (ze.isDirectory()) {
                    File fmd = new File(path + filename);
                    fmd.mkdirs();
                    continue;
                }

                FileOutputStream fout = new FileOutputStream(path + filename);

                while ((count = zis.read(buffer)) != -1)
                {
                    fout.write(buffer, 0, count);
                }

                fout.close();
                zis.closeEntry();
            }

            zis.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }


}
