package com.example.petrusapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

import com.example.petrusapplication.db.Product;
import com.example.petrusapplication.viewmodel.PetrusViewModel;

import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {
    private PetrusViewModel myViewModel;
    private Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        myViewModel = new ViewModelProvider(this).get(PetrusViewModel.class);
        product= (Product) getIntent().getSerializableExtra("product");
        TextView content=findViewById(R.id.productDetails);
        content.setText("Name:\n"+product.getName()+"\n\nDescription:\n"+product.getDescription()+"\n\nQuantity:\n"+product.getQuantity()+"\n\nContact:\n"+product.getEmail());
    }
}