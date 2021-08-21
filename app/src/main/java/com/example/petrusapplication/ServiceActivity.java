package com.example.petrusapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petrusapplication.ui.ServiceListAdapter;
import com.example.petrusapplication.viewmodel.PetrusViewModel;

public class ServiceActivity extends AppCompatActivity {
    private PetrusViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        RecyclerView recyclerView = findViewById(R.id.recyclerview2);
        final ServiceListAdapter adapter = new ServiceListAdapter(new ServiceListAdapter.ServiceDiff(),this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myViewModel = new ViewModelProvider(this).get(PetrusViewModel.class);

        myViewModel.getAllServices().observe(this, services -> {
            adapter.submitList(services);
        });
    }
}
