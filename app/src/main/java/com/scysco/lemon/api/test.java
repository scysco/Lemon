package com.scysco.lemon.api;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.scysco.lemon.PrincipalActivity;

import java.util.HashMap;
import java.util.Map;

public class test {

    private static final String TAG = "test";
    private FirebaseFirestore DB;
    private Map<String, Object> product = new HashMap<>();
    private DocumentReference productRef;

    private  String id;
    private String name;
    private double grams;
    private double cost;
    private double price;

    public test(String name, double grams, double cost, double price){

        // Connection Instance
        DB = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        DB.setFirestoreSettings(settings);

        // Document Reference
        productRef = DB.collection(PrincipalActivity.PLACE).document();

        // Variable Assignment
        this.name = name;
        this.grams = grams;
        this.cost = cost;
        this.price = price;
        this.id = productRef.getId();

        // Map Filling
        product.put("name", this.name);
        product.put("grams", this.grams);
        product.put("cost", this.cost);
        product.put("price", this.price);

        // Upload Document
        productRef.set(product)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + productRef.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    public test(String id){

        // Connection Instance
        DB = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        DB.setFirestoreSettings(settings);

        // Document Reference
        productRef = DB.collection(PrincipalActivity.PLACE).document(id);

        productRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    assert document != null;
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        name = (String) document.get("name");
                        grams = (double) document.get("grams");
                        cost = (double) document.get("cost");
                        price = (double) document.get("price");

                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
        this.id = productRef.getId();

        // Map Filling
        product.put("name", this.name);
        product.put("grams", this.grams);
        product.put("cost", this.cost);
        product.put("price", this.price);
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        productRef.update("name",name);
        this.name = name;
    }

    public double getGrams() {
        return grams;
    }

    public void setGrams(double grams) {
        productRef.update("grams",grams);
        this.grams = grams;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        productRef.update("cost",cost);
        this.cost = cost;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        productRef.update("price",price);
        this.price = price;
    }

}
