package com.example.petrusapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petrusapplication.clients.MyLoopjTask;
import com.example.petrusapplication.clients.MyLoopjTask2;
import com.example.petrusapplication.clients.OnLoopjCompleted;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

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
            userId=UserActivity.printJsonObjectByKeyName(new JSONObject(results),"userID");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        TextView userid=findViewById(R.id.userId);
        userid.setText("User ID: "+userId);
        TextView adoptionId=findViewById(R.id.adoptionId);
        adoptionId.setText("Adoption Listing ID: "+adoptionListingId);
        residence=findViewById(R.id.residenceType);
        dogsOwned=findViewById(R.id.dogsOwned);
        description=findViewById(R.id.description);
        Button submitButton=findViewById(R.id.submit);
        myLoopjTask2 = new MyLoopjTask2(this,this);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String residenceType=residence.getText().toString();
                String dogsOwnedd=dogsOwned.getText().toString();
                String descriptionn=description.getText().toString();
                try {
                    myLoopjTask2.executeLoopjCall(userId,adoptionListingId,residenceType,dogsOwnedd,descriptionn);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void taskCompleted(String result) {
        Toast toast=Toast.makeText(getApplicationContext(),"Application is successful", Toast.LENGTH_LONG);
        toast.show();
        finish();
    }
}