package com.example.petrusapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petrusapplication.db.Product;


public class ProductDetailActivity extends AppCompatActivity {
    private Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        product= (Product) getIntent().getSerializableExtra("product");
        ImageView productImage=findViewById(R.id.myImage);
        int imageId=this.getResources().getIdentifier(product.getImageName(),"drawable", this.getPackageName());
        productImage.setImageResource(imageId);
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