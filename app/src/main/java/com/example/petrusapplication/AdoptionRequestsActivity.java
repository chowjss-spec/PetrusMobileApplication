package com.example.petrusapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.petrusapplication.adapters.AdoptionRequestAdapter;
import com.example.petrusapplication.clients.AdoptionListingRestClient;
import com.example.petrusapplication.models.AdoptionRequest;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class AdoptionRequestsActivity extends AppCompatActivity {

    private ListView adoptionRequestView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoption_requests);
        String adoptionId = getIntent().getStringExtra("listing");
        getAdoptionRequest(adoptionId);
    }

    private void getAdoptionRequest(String id) {
        AdoptionListingRestClient.get(getApplicationContext(),"AdoptionListingAPI/"+id +"/request", null,
                null, new JsonHttpResponseHandler() {
                    @SuppressLint("ResourceType")
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        ArrayList<AdoptionRequest> adoptionRequestArray = new ArrayList<AdoptionRequest>();
                        AdoptionRequestAdapter adoptionRequestAdapter = new AdoptionRequestAdapter(AdoptionRequestsActivity.this, adoptionRequestArray);

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                adoptionRequestAdapter.add(new AdoptionRequest(response.getJSONObject(i)));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adoptionRequestView = (ListView) findViewById(R.id.list_viewAdoptionRequest);
                        adoptionRequestView.setAdapter(adoptionRequestAdapter);
//                        System.out.println("success");

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        System.out.println(statusCode);
                        System.out.println(responseString);
                        throwable.printStackTrace();
//                        ImageView whenEmpty = findViewById(R.id.whenEmpty);
//                        adoptionRequestView.setVisibility(View.GONE);
//                        whenEmpty.setVisibility(View.VISIBLE);
//                        System.out.println("failure");

                    }
                });
    }
}