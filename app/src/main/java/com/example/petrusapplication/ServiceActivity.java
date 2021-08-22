package com.example.petrusapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petrusapplication.ui.ServiceListAdapter;
import com.example.petrusapplication.viewmodel.PetrusViewModel;

import java.util.ArrayList;
import java.util.List;

public class ServiceActivity extends AppCompatActivity {
    private PetrusViewModel myViewModel;

    final List<String> filterOption = new ArrayList<String>();
    final List<String> filterValue = new ArrayList<String>();

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

        //Filter option
        filterOption.add("None");
        filterOption.add("Location");
        filterOption.add("Category");
        filterOption.add("Species");

        Spinner optionSpinner = findViewById(R.id.filterSpinner);
        Spinner valueSpinner = findViewById(R.id.filterValueSpinner);

        ArrayAdapter filterOptionAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,filterOption);
        filterOptionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        optionSpinner.setAdapter(filterOptionAdapter);

        ArrayAdapter filterValueOptionAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,filterValue);
        filterValueOptionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valueSpinner.setAdapter(filterValueOptionAdapter);

        optionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){
                    filterValue.clear();
                    filterValue.add("None");
                    valueSpinner.setAdapter(filterValueOptionAdapter);
                    valueSpinner.setVisibility(View.INVISIBLE);
                }else{
                    filterValue.clear();
                    filterValue.add("None");
                    //Location
                    if(i == 1){
                        filterValue.add("Buona Vista");
                        filterValue.add("Redhill");
                        filterValue.add("Ang Mo Kio");
                        filterValue.add("Clementi");
                        filterValue.add("Queenstown");
                        filterValue.add("Bukit Batok");
                        filterValue.add("Bradell");
                        filterValue.add("Tanjong Pagar");
                        filterValue.add("Bugis");
                    }else if (i == 2){//Category
                        filterValue.add("Sitting");
                        filterValue.add("Visits");
                        filterValue.add("Care");
                        filterValue.add("Taxi");
                        filterValue.add("Walking");
                        filterValue.add("Training");
                        filterValue.add("Washing");
                        filterValue.add("Grooming");
                        filterValue.add("Boarding");
                        filterValue.add("Photography");
                    }else{//Species
                        filterValue.add("Mixed");
                        filterValue.add("Dog");
                        filterValue.add("Rabbit");
                    }

                    valueSpinner.setAdapter(filterValueOptionAdapter);
                    valueSpinner.setVisibility(View.VISIBLE);
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        valueSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int selectedFilterFieldPos = optionSpinner.getSelectedItemPosition();
                String selectedFilterField = "None";
                String selectedFilterValue = filterValue.get(i);

                //Must match with service table field name
                if(selectedFilterFieldPos == 1){
                    selectedFilterField = "location";
                }else if(selectedFilterFieldPos == 2){
                    selectedFilterField = "category";
                }else if(selectedFilterFieldPos == 3){
                    selectedFilterField = "species";
                }

                //Display full list if the filter option or filter value is None
                if(selectedFilterField.equals("None") || selectedFilterValue.equals("None")){
                    myViewModel.getAllServices().observe(ServiceActivity.this, services -> {
                        adapter.submitList(services);
                    });
                }else{
                    myViewModel.getAllServicesByFilter(selectedFilterField, selectedFilterValue).observe(ServiceActivity.this, services -> {
                        adapter.submitList(services);
                    });
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
    }
}
