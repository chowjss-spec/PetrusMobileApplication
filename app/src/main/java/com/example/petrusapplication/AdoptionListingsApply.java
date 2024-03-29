package com.example.petrusapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.petrusapplication.adapters.AdoptionListingApplicationAdapter;
import com.example.petrusapplication.clients.AdoptionListingRestClient;
import com.example.petrusapplication.models.AdoptionListing;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class AdoptionListingsApply extends AppCompatActivity {

    private ListView adoptionListingView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoption_listings_apply);
        Button logout=findViewById(R.id.Logoutt);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences appSharedPrefs = getSharedPreferences(
                        "petrus", Context.MODE_PRIVATE);
                appSharedPrefs.edit().remove("userDetails").commit();;
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });


        getAdoptionListing();
    }

    private void getAdoptionListing() {

        AdoptionListingRestClient.get(AdoptionListingsApply.this, "AdoptionListingAPI", null,
                null, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        ArrayList<AdoptionListing> adoptionListingArray = new ArrayList<AdoptionListing>();
                        AdoptionListingApplicationAdapter myAdapter = new AdoptionListingApplicationAdapter(AdoptionListingsApply.this, adoptionListingArray);


                        for (int i = 0; i < response.length(); i++) {
                            try {
                                myAdapter.add(new AdoptionListing(response.getJSONObject(i)));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        adoptionListingView = (ListView) findViewById(R.id.list_applyListing);
                        adoptionListingView.setAdapter(myAdapter);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        System.out.println(statusCode);
                        System.out.println(responseString);
                        throwable.printStackTrace();
                    }
                });
    }
}