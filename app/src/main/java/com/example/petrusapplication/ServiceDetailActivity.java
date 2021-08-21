package com.example.petrusapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petrusapplication.R;
import com.example.petrusapplication.db.Service;

public class ServiceDetailActivity extends AppCompatActivity {
    private Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_detail);
        service= (Service) getIntent().getSerializableExtra("service");

        TextView name = findViewById(R.id.txtName);
        TextView listingDate = findViewById(R.id.txtListingDate);
        TextView price = findViewById(R.id.txtPrice);
        TextView location = findViewById(R.id.txtLocation);
        TextView category = findViewById(R.id.txtCategory);
        TextView species = findViewById(R.id.txtSpecies);
        TextView description = findViewById(R.id.txtDescription);

        name.setText(service.getName());
        price.setText("S$" + service.getPrice());
        listingDate.setText(service.getListingDate());
        location.setText("\uD83D\uDCCD " + service.getLocation());
        category.setText(service.getCategory());
        species.setText(service.getSpecies());
        description.setText(service.getDescription());

        int resourceId = this.getResources().getIdentifier(service.getImageName(),"drawable", this.getPackageName());
        ImageView image = findViewById(R.id.serviceImageView);
        image.setImageResource(resourceId);

        Button contactButton = findViewById(R.id.btnContact);
        Button mapButton = findViewById(R.id.btnMap);

        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("text/plain");
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{service.getEmail()});
                intent.putExtra(Intent.EXTRA_SUBJECT, "For Enquiry\"");
                intent.putExtra(Intent.EXTRA_TEXT,"Please state your enquiry");
                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + service.getLocation());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }
}