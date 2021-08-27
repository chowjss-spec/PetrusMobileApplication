package com.example.petrusapplication.clients;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.entity.StringEntity;

public class AdoptionRequestDeleteRestClient {
    AsyncHttpClient client;
    RequestParams requestParams;
    Context context;
    OnLoopjCompleted loopjListener;
    String jsonResponse;

    private static final String BASE_URL = "https://10.0.2.2:44327/AdoptionListingAPI/request/delete";

    public AdoptionRequestDeleteRestClient(Context context) {
        client = new AsyncHttpClient();
        requestParams = new RequestParams();
        this.context = context;
//        this.loopjListener = loopjListener;
    }

    public void executeAdoptionRequestDeleteRestClient(String requestId, JsonHttpResponseHandler responseHandler) throws JSONException{
        client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
        JSONObject json = new JSONObject();
        json.put("requestId",requestId);
        StringEntity entity = new StringEntity(json.toString(), "UTF-8");

        client.get(context,BASE_URL, entity,"application/json",responseHandler);
    }
}
