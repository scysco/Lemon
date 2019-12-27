package com.scysco.lemon.api;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;

import com.scysco.lemon.PrincipalActivity;
import com.squareup.picasso.Picasso;

import java.io.File;

public class Product {


    public ObservableField<String> id = new ObservableField<>();
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<Double> price = new ObservableField<>();
    public ObservableField<Double> cost = new ObservableField<>();
    public ObservableField<String> stock = new ObservableField<>();


    public Product(){
    }

    public Product(String id,String name, double price, double cost, String stock){
        this.id.set(id);
        this.name.set(name);
        this.price.set(price);
        this.cost.set(cost);
        this.stock.set(stock);
    }

    @BindingAdapter({"bind:imageUrl", "bind:error"})
    public static void loadImage(ImageView view, String url, Drawable error){
        Picasso.with(view.getContext()).load(new File(PrincipalActivity.RES_PATCH +"/"+url+".png")).error(error).into(view);
    }
}
