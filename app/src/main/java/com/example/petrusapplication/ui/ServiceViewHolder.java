package com.example.petrusapplication.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.petrusapplication.R;
import com.example.petrusapplication.ServiceDetailActivity;
import com.example.petrusapplication.db.Service;

public class ServiceViewHolder extends RecyclerView.ViewHolder {
    private final TextView serviceDescriptionView;
    private final ImageView serviceImageView;
    private final Button button;
    private final Context context;

    private ServiceViewHolder(View itemView) {
        super(itemView);
        serviceDescriptionView = itemView.findViewById(R.id.textView2);
        serviceImageView=itemView.findViewById(R.id.imageView);
        button=itemView.findViewById(R.id.detailButton);
        context=itemView.getContext();
    }

    public void bindDescription(String text) {
        serviceDescriptionView.setText(text);
    }
    public void bindImage(int resourceIdentifier ) {
        serviceImageView.setImageResource(resourceIdentifier);
    }

    public void bindButton(Service service)
    {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ServiceDetailActivity.class);
                intent.putExtra("service",service);
                context.startActivity(intent);
            }
        });
    }

    static ServiceViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item2, parent, false);
        return new ServiceViewHolder(view);
    }
}
