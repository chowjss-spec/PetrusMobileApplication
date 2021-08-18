package com.example.petrusapplication.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.petrusapplication.R;

class ProductViewHolder extends RecyclerView.ViewHolder {
    private final TextView productItemDescriptionView;
    private final ImageView productItemImageView;

    private ProductViewHolder(View itemView) {
        super(itemView);
        productItemDescriptionView = itemView.findViewById(R.id.textView2);
        productItemImageView=itemView.findViewById(R.id.imageView);
    }

    public void bind(String text) {
        productItemDescriptionView.setText(text);
    }
    public void bind(int resourceIdentifier ) {
        productItemImageView.setImageResource(resourceIdentifier);
    }
    static ProductViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ProductViewHolder(view);
    }
}
