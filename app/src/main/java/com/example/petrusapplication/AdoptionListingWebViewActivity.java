package com.example.petrusapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class AdoptionListingWebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoption_listing_web_view);

        String listingId = getIntent().getStringExtra("listing");
        WebView webWindow = findViewById(R.id.adoptionListingWebView);
        webWindow.setWebViewClient(new SSLTolerentWebViewClient());
        webWindow.loadUrl("https://10.0.2.2:44327/ApplyAdoption/Details/" + listingId);

        Button backBtn = findViewById(R.id.backToListingButton);
        backBtn.setOnClickListener(view -> {
            finish();
        });

        }
    private class SSLTolerentWebViewClient extends WebViewClient {

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed(); // Ignore SSL certificate errors
        }
    }
}

