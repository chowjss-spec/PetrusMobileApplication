package com.example.petrusapplication.clients;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class AdoptionRequestEditRestClient {
    AsyncHttpClient client;
    RequestParams requestParams;
    Context context;
    OnLoopjCompleted loopjListener;

    String BASE_URL = "https://10.0.2.2:44327/AdoptionListingAPI/request/edit";
    String jsonResponse;

    public AdoptionRequestEditRestClient(Context context, OnLoopjCompleted listener){
        client = new AsyncHttpClient();
        requestParams = new RequestParams();
        this.context = context;
        this.loopjListener = listener;
    }

    public void executeAdoptionRequestEditRestClient(String userId, String adoptionListingId, String adoptionRequestId,String residencetype,String dogsowned, String description) throws JSONException{
        client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
        JSONObject json = new JSONObject();
        json.put("userId",userId);
        json.put("adoptionListingId",adoptionListingId);
        json.put("adoptionRequestId",adoptionRequestId);
        json.put("residenceType",residencetype);
        json.put("dogsOwned",dogsowned);
        json.put("description",description);
        StringEntity entity = new StringEntity(json.toString(), "UTF-8");

        client.post(context, BASE_URL, entity, "application/json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response= new String(responseBody, StandardCharsets.UTF_8);
                loopjListener.taskCompleted(response);
                Log.i("createApplication","onSuccess "+response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("createApplication", "onFailure: " + responseBody);
                loopjListener.taskCompleted("");
            }
        });

    }




}
