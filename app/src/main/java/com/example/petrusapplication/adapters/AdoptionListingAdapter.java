package com.example.petrusapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.petrusapplication.R;
import com.example.petrusapplication.models.AdoptionListing;

import java.util.ArrayList;

public class AdoptionListingAdapter extends ArrayAdapter<AdoptionListing> {
    public AdoptionListingAdapter(Context context, ArrayList<AdoptionListing> notes) {
        super(context, R.layout.item_adoptionlisting, notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AdoptionListing adoptionListing = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_adoptionlisting, parent, false);

            viewHolder.id = (TextView) convertView.findViewById(R.id.value_note_id);
            viewHolder.title = (TextView) convertView.findViewById(R.id.value_note_title);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.id.setText(adoptionListing.getAdoptionListingID());
        viewHolder.title.setText(adoptionListing.getName());

        return convertView;
    }

    private static class ViewHolder {
        TextView id;
        TextView title;
    }
}