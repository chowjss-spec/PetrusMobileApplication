package com.example.petrusapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.petrusapplication.R;
import com.example.petrusapplication.models.AdoptionRequest;

import java.util.ArrayList;

public class AdoptionRequestAdapter extends ArrayAdapter<AdoptionRequest> {
    private final Context context;
    public AdoptionRequestAdapter(Context context, ArrayList<AdoptionRequest> notes) {
        super(context, R.layout.item_adoptionrequest, notes);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AdoptionRequest adoptionRequest = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_adoptionrequest, parent, false);

            viewHolder.id = (TextView) convertView.findViewById(R.id.value_note_id);
            viewHolder.title = (TextView) convertView.findViewById(R.id.value_note_title);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.imagePet);
            viewHolder.status=(TextView) convertView.findViewById(R.id.adoptionStatus);
            viewHolder.breed = (TextView) convertView.findViewById(R.id.petBreed);
            viewHolder.color = (TextView) convertView.findViewById(R.id.petColor);
            viewHolder.age = (TextView) convertView.findViewById(R.id.petAge);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.id.setText(adoptionRequest.getAdoptionRequestId());
        viewHolder.title.setText(adoptionRequest.getName());
        String imgName = adoptionRequest.getImage();
        String imgTrimmed = imgName.substring(0,imgName.lastIndexOf(".")).toLowerCase();
        int resourceId=context.getResources().getIdentifier(imgTrimmed,"drawable", context.getPackageName());
        viewHolder.img.setImageResource(resourceId);
        viewHolder.status.setText(adoptionRequest.getApplicationStatus().name());
        viewHolder.breed.setText(adoptionRequest.getBreed1().name());
        viewHolder.color.setText(adoptionRequest.getColor1().name());
        viewHolder.age.setText(String.valueOf(adoptionRequest.getAge()));


        return convertView;
    }

    private static class ViewHolder {
        TextView id;
        TextView title;
        ImageView img;
        TextView status;
        TextView breed;
        TextView color;
        TextView age;
    }


}


