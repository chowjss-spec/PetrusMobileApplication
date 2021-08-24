package com.example.petrusapplication.ui;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.petrusapplication.db.Product;

public class ProductListAdapter extends ListAdapter<Product, ProductViewHolder> {
    private final Context context;
    public ProductListAdapter(@NonNull DiffUtil.ItemCallback<Product> diffCallback,Context context) {
        super(diffCallback);
        this.context=context;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ProductViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product current = getItem(position);
        holder.bindDescription(current.getName()+"\nPrice: $"+current.getPrice());
        int resourceId=context.getResources().getIdentifier(current.getImageName(),"drawable", context.getPackageName());
        holder.bindImage(resourceId);
        holder.bindButton(current);
    }

    public static class ProductDiff extends DiffUtil.ItemCallback<Product> {

        @Override
        public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.getId()==newItem.getId();
        }
    }
}