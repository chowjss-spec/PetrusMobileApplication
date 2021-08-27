package com.example.petrusapplication.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.petrusapplication.AdoptionRequestEditActivity;
import com.example.petrusapplication.AdoptionRequestsActivity;
import com.example.petrusapplication.CreateApplicationActivity;
import com.example.petrusapplication.R;
import com.example.petrusapplication.ViewAdoptionRequestsActivity;
import com.example.petrusapplication.clients.AdoptionRequestDeleteRestClient;
import com.example.petrusapplication.clients.AdoptionRequestEditRestClient;
import com.example.petrusapplication.models.AdoptionRequest;
import com.example.petrusapplication.models.Residence;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;

import java.util.ArrayList;

import static java.lang.String.valueOf;

public class AdoptionRequestAdapter extends ArrayAdapter<AdoptionRequest> {
    private final Context context;
    private AdoptionRequestAdapter adapter;
    public AdoptionRequestAdapter(Context context, ArrayList<AdoptionRequest> notes) {
        super(context, R.layout.item_adoptionrequest, notes);
        this.context = context;
        this.adapter = this;
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
            viewHolder.remove = (Button) convertView.findViewById(R.id.removeButton);
            viewHolder.edit = (Button) convertView.findViewById(R.id.editButton);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.id.setText(adoptionRequest.getAdoptionListingID());
        viewHolder.title.setText(adoptionRequest.getName());
        String imgName = adoptionRequest.getImage();
        String imgTrimmed = imgName.substring(0,imgName.lastIndexOf(".")).toLowerCase();
        int resourceId=context.getResources().getIdentifier(imgTrimmed,"drawable", context.getPackageName());
        viewHolder.img.setImageResource(resourceId);
        viewHolder.status.setText(adoptionRequest.getApplicationStatus().name());
        viewHolder.breed.setText(adoptionRequest.getBreed1().name());
        viewHolder.color.setText(adoptionRequest.getColor1().name());
        viewHolder.age.setText(valueOf(adoptionRequest.getAge()));

        //Set onClickListener
        viewHolder.remove.setOnClickListener(view -> {
            Intent intent = new Intent(context, AdoptionRequestsActivity.class);
            AdoptionRequestDeleteRestClient adoptionRequestDeleteRestClient= new AdoptionRequestDeleteRestClient(context);
            String requestId = adoptionRequest.getAdoptionRequestId();
            try {
                adoptionRequestDeleteRestClient.executeAdoptionRequestDeleteRestClient(requestId,new JsonHttpResponseHandler(){
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
            ((Activity)context).recreate();
            ((Activity)context).overridePendingTransition( 0, 0);;
        });

        viewHolder.edit.setOnClickListener(view -> {
            Intent intent = new Intent(context, AdoptionRequestEditActivity.class);
//            System.out.println("the current adoptionListing is "+ adoptionRequest.getAdoptionListingID());
            intent.putExtra("adoptionRequestId", adoptionRequest.getAdoptionRequestId());
            intent.putExtra("adoptionListingId", adoptionRequest.getAdoptionListingID());
            ((Activity)context).startActivity(intent);
        });



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
        Button remove;
        Button edit;
    }


}


