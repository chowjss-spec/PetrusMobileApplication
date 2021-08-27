package com.example.petrusapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.petrusapplication.R;
import com.example.petrusapplication.models.AdoptionRequest;

import java.util.ArrayList;

public class AdoptionRequestAdapter extends ArrayAdapter<AdoptionRequest> {
    public AdoptionRequestAdapter(Context context, ArrayList<AdoptionRequest> notes) {
        super(context, R.layout.item_adoptionrequest, notes);
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

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.id.setText(adoptionRequest.getAdoptionRequestId());
        try{
            viewHolder.title.setText(adoptionRequest.getRequestStatus().toString());
        }
        catch(Exception e){

        }
        return convertView;
    }

    private static class ViewHolder {
        TextView id;
        TextView title;
    }


}


