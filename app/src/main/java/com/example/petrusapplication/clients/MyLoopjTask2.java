package com.example.petrusapplication.clients;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class MyLoopjTask2 {

    AsyncHttpClient asyncHttpClient;
    RequestParams requestParams;
    Context context;
    OnLoopjCompleted loopjListener;

    String BASE_URL = "https://10.0.2.2:44327/AdoptionListingAPI/application";
    String jsonResponse;

    public MyLoopjTask2(Context context, OnLoopjCompleted listener) {
        asyncHttpClient = new AsyncHttpClient();
        requestParams = new RequestParams();
        this.context = context;
        this.loopjListener = listener;
    }

    public void executeLoopjCall(String userId, String adoptionListingId,String residencetype,String dogsowned, String description) throws JSONException {
        //requestParams.put("s", queryTerm);
        asyncHttpClient.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
        JSONObject json = new JSONObject();
        json.put("userId",userId);
        json.put("adoptionListingId",adoptionListingId);
        json.put("residenceType",residencetype);
        json.put("dogsOwned",dogsowned);
        json.put("description",description);
        StringEntity entity = new StringEntity(json.toString(), "UTF-8");

        asyncHttpClient.post(context,BASE_URL, entity, "application/json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers,byte[]bytes) {
                String response= new String(bytes, StandardCharsets.UTF_8);
                loopjListener.taskCompleted(response);
                Log.i("createApplication","onSuccess "+response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] response,Throwable throwable) {
                Log.e("createApplication", "onFailure: " + response);
                loopjListener.taskCompleted("");
            }
        });
    }
}
