package com.example.petrusapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        TextView userDetails=findViewById(R.id.userDetails);
        SharedPreferences appSharedPrefs = getSharedPreferences(
                "petrus", Context.MODE_PRIVATE);
        String results = appSharedPrefs.getString("userDetails", "");
        String name="";
        try {
            name=printJsonObjectByKeyName(new JSONObject(results),"name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
      
        userDetails.setText("Welcome back, "+ name + "!");
      
        Button logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences appSharedPrefs = getSharedPreferences(
                        "petrus", Context.MODE_PRIVATE);
                appSharedPrefs.edit().remove("userDetails").commit();;
                finish();
            }
        });

        CardView applyAdopt=findViewById(R.id.startApply);

        applyAdopt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AdoptionListingsApply.class);
                startActivity(intent);
            }
        });

        Button viewAdoptionRequest = findViewById(R.id.viewAdoptionRequest);
        viewAdoptionRequest.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),ViewAdoptionRequestsActivity.class);
            startActivity(intent);
        });
      
      
    }
    public static String printJsonObjectByKeyName(JSONObject jsonObj, String keyName) throws JSONException {
        String output="";
        for (Iterator<String> it = jsonObj.keys(); it.hasNext(); ) {
            Object key = it.next();

            if (String.valueOf(key).equals(keyName)) {
                String keyStr = (String) key;
                Object keyValue = jsonObj.get(keyStr);
                output=String.valueOf(keyValue);
                return output;
            }
        }
        return null;
    }
}