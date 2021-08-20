package com.example.petrusapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        content.setText("Name:\n"+product.getName()+"\n\nDescription:\n"+product.getDescription()+"\n\nPrice:\n$"+product.getPrice()+"\n\nContact:\n"+product.getEmail());
        Button contactButton=findViewById(R.id.contactButton);
        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("mailto:"+product.getEmail());
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra(Intent.EXTRA_SUBJECT, "For Enquiry");
                intent.putExtra(Intent.EXTRA_TEXT, "Please state your enquiry");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        Button backButton=findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}