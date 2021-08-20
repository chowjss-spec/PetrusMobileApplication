package com.example.petrusapplication.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.petrusapplication.ProductActivity;
import com.example.petrusapplication.ProductDetailActivity;
import com.example.petrusapplication.R;
import com.example.petrusapplication.db.Product;

class ProductViewHolder extends RecyclerView.ViewHolder {
    private final TextView productItemDescriptionView;
    private final ImageView productItemImageView;
    private final Button button;
    private final Context context;

    private ProductViewHolder(View itemView) {
        super(itemView);
        productItemDescriptionView = itemView.findViewById(R.id.textView2);
        productItemImageView=itemView.findViewById(R.id.imageView);
        button=itemView.findViewById(R.id.detailButton);
        context=itemView.getContext();
    }

    public void bindDescription(String text) {
        productItemDescriptionView.setText(text);
    }
    public void bindImage(int resourceIdentifier ) {
        productItemImageView.setImageResource(resourceIdentifier);
    }
    public void bindButton(Product product)
    {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("product",product);
                context.startActivity(intent);
            }
        });
    }
    static ProductViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ProductViewHolder(view);
    }
}
