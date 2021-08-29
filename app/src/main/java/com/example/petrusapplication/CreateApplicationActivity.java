package com.example.petrusapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petrusapplication.clients.MyLoopjTask2;
import com.example.petrusapplication.clients.OnLoopjCompleted;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;

public class CreateApplicationActivity extends AppCompatActivity implements OnLoopjCompleted {
    EditText residence;
    EditText dogsOwned;
    EditText description;
    MyLoopjTask2 myLoopjTask2;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_application);
        Intent intent=getIntent();
        String adoptionListingId=intent.getStringExtra("adoptionListingId");
        SharedPreferences appSharedPrefs = this.getSharedPreferences(
                "petrus", Context.MODE_PRIVATE);
        String results = appSharedPrefs.getString("userDetails", "");
        try {
            userId=UserActivity.printJsonObjectByKeyName(new JSONObject(results),"id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        TextView userid=findViewById(R.id.userId);
        userid.setText("User ID: "+userId);

        TextView adoptionId=findViewById(R.id.adoptionId);
        adoptionId.setText("Adoption Listing ID: "+adoptionListingId);

        Spinner residenceSpinner = (Spinner) findViewById(R.id.residentSpinner);
        ArrayAdapter<CharSequence> residenceSpinnerAdapter = ArrayAdapter.createFromResource(this,R.array.residenceArray, android.R.layout.simple_spinner_item);
        residenceSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        residenceSpinner.setAdapter(residenceSpinnerAdapter);

        Spinner dogsOwnedSpinner = (Spinner) findViewById(R.id.dogsOwnedSpinner);
        ArrayAdapter<CharSequence> dogsOwnedSpinnerAdapter = ArrayAdapter.createFromResource(this,R.array.dogsOwnedArray, android.R.layout.simple_spinner_item);
        dogsOwnedSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dogsOwnedSpinner.setAdapter(dogsOwnedSpinnerAdapter);

//        dogsOwned=findViewById(R.id.dogsOwned);
        description=findViewById(R.id.description);
        Button submitButton=findViewById(R.id.submit);
        myLoopjTask2 = new MyLoopjTask2(this,this);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String residenceType = residenceSpinner.getSelectedItem().toString();
                String dogsOwnedd;

                if(dogsOwnedSpinner.getSelectedItem().toString().equals(">3")){
                    dogsOwnedd = "4";
                }
                else{
                    dogsOwnedd = dogsOwnedSpinner.getSelectedItem().toString();
                }
                System.out.println(dogsOwnedd);
                String descriptionn=description.getText().toString();
                try {
                    myLoopjTask2.executeLoopjCall(userId,adoptionListingId,residenceType,dogsOwnedd,descriptionn);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener{
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            parent.getItemAtPosition(pos);
        }
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    @Override
    public void taskCompleted(String result) {
        if (result.contains("success")) {
            Toast toast = Toast.makeText(getApplicationContext(), "Application is successful!", Toast.LENGTH_LONG);
            toast.show();
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG);
            toast.show();
        }
        finish();
    }
}