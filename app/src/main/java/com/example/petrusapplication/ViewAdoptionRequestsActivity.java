package com.example.petrusapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import com.example.petrusapplication.adapters.AdoptionListingAdapter;
import com.example.petrusapplication.adapters.AdoptionRequestAdapter;
import com.example.petrusapplication.clients.AdoptionRequestRestClient;
import com.example.petrusapplication.clients.OnLoopjCompleted;
import com.example.petrusapplication.models.AdoptionListing;
import com.example.petrusapplication.models.AdoptionRequest;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class ViewAdoptionRequestsActivity extends AppCompatActivity implements OnLoopjCompleted {

    private ListView adoptionRequestView;
    AdoptionRequestRestClient adoptionRequestRestClient;
    String userId;
    String jsonResponse;
    OnLoopjCompleted loopjListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_adoption_requests);

        SharedPreferences appSharedPrefs = this.getSharedPreferences(
                "petrus", Context.MODE_PRIVATE);
        String results = appSharedPrefs.getString("userDetails", "");
        try {
            userId=UserActivity.printJsonObjectByKeyName(new JSONObject(results),"userID");
            System.out.println("the userID Currently is " + userId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adoptionRequestRestClient = new AdoptionRequestRestClient(this,this);
        try{
            adoptionRequestRestClient.executeAdoptionRequestRestClient(userId, new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                    super.onSuccess(statusCode, headers, response);
                    if (response!=null){
                        try {
                            System.out.println(response.getJSONObject(0));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    ArrayList<AdoptionRequest> adoptionRequestArray = new ArrayList<AdoptionRequest>();
                    AdoptionRequestAdapter adoptionRequestAdapter = new AdoptionRequestAdapter(ViewAdoptionRequestsActivity.this, adoptionRequestArray);

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            adoptionRequestAdapter.add(new AdoptionRequest(response.getJSONObject(i)));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    adoptionRequestView = (ListView) findViewById(R.id.list_viewAdoptionRequest);
                    adoptionRequestView.setAdapter(adoptionRequestAdapter);


                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
//                Log.e("login", "onFailure: " + errorResponse);
                }

            });

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void taskCompleted(String results) {

    }

}