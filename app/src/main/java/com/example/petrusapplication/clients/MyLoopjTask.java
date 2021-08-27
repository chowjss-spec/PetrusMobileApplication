package com.example.petrusapplication.clients;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class MyLoopjTask {

    AsyncHttpClient asyncHttpClient;
    RequestParams requestParams;
    Context context;
    OnLoopjCompleted loopjListener;

    String BASE_URL = "https://10.0.2.2:44327/AdoptionListingAPI/login";
    String jsonResponse;

    public MyLoopjTask(Context context, OnLoopjCompleted listener) {
        asyncHttpClient = new AsyncHttpClient();
        requestParams = new RequestParams();
        this.context = context;
        this.loopjListener = listener;
    }

    public void executeLoopjCall(String username, String password) throws JSONException {
        //requestParams.put("s", queryTerm);
        asyncHttpClient.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
        JSONObject json = new JSONObject();
        json.put("username",username);
        json.put("password",password);
        StringEntity entity = new StringEntity(json.toString(), "UTF-8");

        asyncHttpClient.post(context,BASE_URL, entity, "application/json", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                if (response!=null)
                    jsonResponse = response.toString();
                loopjListener.taskCompleted(jsonResponse);
                Log.i("login","onSuccess: " + jsonResponse);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.e("login", "onFailure: " + errorResponse);
            }
        });
    }
}
