package com.example.petrusapplication.clients;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import com.example.petrusapplication.AdoptionListingActivity;
import com.example.petrusapplication.R;
import com.example.petrusapplication.adapters.AdoptionListingAdapter;
import com.example.petrusapplication.adapters.AdoptionRequestAdapter;
import com.example.petrusapplication.models.AdoptionListing;
import com.example.petrusapplication.models.AdoptionRequest;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class AdoptionRequestRestClient {
    AsyncHttpClient client;
    RequestParams requestParams;
    Context context;
    OnLoopjCompleted loopjListener;
    String jsonResponse;

    private ListView adoptionRequestView;

    private static final String BASE_URL = "https://10.0.2.2:44327/AdoptionListingAPI/request";

//    private static AsyncHttpClient client = new AsyncHttpClient();

//    static {
//        client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
//    }

    public AdoptionRequestRestClient(Context context, OnLoopjCompleted listener) {
        client = new AsyncHttpClient();
        requestParams = new RequestParams();
        this.context = context;
        this.loopjListener = listener;
    }

//    public static void post(Context context, StringEntity entity, String contentType, JsonHttpResponseHandler responseHandler) {
//        client.post(context,BASE_URL,entity,contentType,responseHandler);
//    }


    public void executeAdoptionRequestRestClient(String userId,JsonHttpResponseHandler responseHandler) throws JSONException{
        client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
        JSONObject json = new JSONObject();
        json.put("userId",userId);
        StringEntity entity = new StringEntity(json.toString(), "UTF-8");

        client.post(context,BASE_URL, entity,"application/json",responseHandler);
//                {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                super.onSuccess(statusCode, headers, response);
//                if (response!=null){
//                    try {
//                        System.out.println(response.getJSONObject(0));
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
////                    jsonResponse = response.toString();
////                loopjListener.taskCompleted(jsonResponse);
////                Log.i("login","onSuccess: " + jsonResponse);
//                ArrayList<AdoptionRequest> adoptionRequestArray = new ArrayList<AdoptionRequest>();
//                AdoptionRequestAdapter adoptionRequestAdapter = new AdoptionRequestAdapter(context, adoptionRequestArray);
//
//                for (int i = 0; i < response.length(); i++) {
//                    try {
//                        adoptionRequestAdapter.add(new AdoptionRequest(response.getJSONObject(i)));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                super.onFailure(statusCode, headers, throwable, errorResponse);
////                Log.e("login", "onFailure: " + errorResponse);
//            }

    }
}
