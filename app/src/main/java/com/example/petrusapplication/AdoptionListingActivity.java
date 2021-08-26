package com.example.petrusapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petrusapplication.adapters.AdoptionListingAdapter;
import com.example.petrusapplication.clients.AdoptionListingRestClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class AdoptionListingActivity extends AppCompatActivity {

    private ListView adoptionListingView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoption_listing);



        getAdoptionListing();
        Button loginBttn=findViewById(R.id.loginButton);
        loginBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getAdoptionListing() {

        AdoptionListingRestClient.get(AdoptionListingActivity.this, "AdoptionListingAPI", null,
                null, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        ArrayList<AdoptionListing> adoptionListingArray = new ArrayList<AdoptionListing>();
                        AdoptionListingAdapter adoptionListingAdapter = new AdoptionListingAdapter(AdoptionListingActivity.this, adoptionListingArray);


                        for (int i = 0; i < response.length(); i++) {
                            try {
                                adoptionListingAdapter.add(new AdoptionListing(response.getJSONObject(i)));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        adoptionListingView = (ListView) findViewById(R.id.list_adoptionListing);
                        adoptionListingView.setAdapter(adoptionListingAdapter);
                        adoptionListingView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
//                                var selectedItem = (String) parent.getItemAtPosition(position);
                                AdoptionListing listing = adoptionListingAdapter.getItem(position);
                                Intent intent = new Intent(AdoptionListingActivity.this, AdoptionListingWebViewActivity.class);
                                intent.putExtra("listing",listing.getAdoptionListingID());

                                startActivity(intent);
                            }
                        });
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