package com.scysco.lemon.api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.scysco.lemon.R;
import com.scysco.lemon.databinding.ItemProductListBinding;

import java.util.Collections;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    List<Product> products = Collections.emptyList();
    Context context;

    public ProductAdapter(List<Product> products, Context context) {
        this.context = context;
        this.products = products;
    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemProductListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_product_list, parent, false);

        return new ProductHolder(binding);
    }

    @Override
    public void onBindViewHolder(ProductHolder holder, int position) {
        holder.bindConnection(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, Product product) {
        products.add(position, product);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(Product product) {
        int position = products.indexOf(product);
        products.remove(position);
        notifyItemRemoved(position);
    }

    public class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ItemProductListBinding mBinding;

        public ProductHolder(ItemProductListBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            itemView.setOnClickListener(this);
        }

        public void bindConnection(Product product){
            mBinding.setProduct(product);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
