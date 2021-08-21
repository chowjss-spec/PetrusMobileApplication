package com.example.petrusapplication.ui;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.petrusapplication.db.Service;

public class ServiceListAdapter extends ListAdapter<Service, ServiceViewHolder> {
    private final Context context;

    public ServiceListAdapter(@NonNull DiffUtil.ItemCallback<Service> diffCallback, Context context) {
        super(diffCallback);
        this.context = context;
    }

    @Override
    public ServiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ServiceViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ServiceViewHolder holder, int position) {
        Service current = getItem(position);
        holder.bindDescription(current.getName()+"\n\uD83D\uDCCD " + current.getLocation()+"\n S$" + current.getPrice());
        int resourceId=context.getResources().getIdentifier(current.getImageName(),"drawable", context.getPackageName());
        holder.bindImage(resourceId);
        holder.bindButton(current);
    }

    public static class ServiceDiff extends DiffUtil.ItemCallback<Service> {

        @Override
        public boolean areItemsTheSame(@NonNull Service oldItem, @NonNull Service newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Service oldItem, @NonNull Service newItem) {
            return oldItem.getId()==newItem.getId();
        }
    }
}
