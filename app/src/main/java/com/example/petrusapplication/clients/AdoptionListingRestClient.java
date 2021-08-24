package com.example.petrusapplication.clients;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class AdoptionListingRestClient {
    private static final String BASE_URL = "https://10.0.2.2:44327/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    static {
        client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
    }

    public static void get(Context context, String url, Header[] headers, RequestParams params,
                           AsyncHttpResponseHandler responseHandler) {
        client.get(context, getAbsoluteUrl(url), headers, params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}