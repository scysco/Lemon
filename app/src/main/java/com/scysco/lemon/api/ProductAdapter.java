package com.scysco.lemon.api;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.scysco.lemon.R;
import com.scysco.lemon.databinding.ItemProductListBinding;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    List<Product> products;

    public ProductAdapter(List<Product> products) {
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