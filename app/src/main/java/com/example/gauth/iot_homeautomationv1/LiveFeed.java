package com.example.gauth.iot_homeautomationv1;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class LiveFeed extends AppCompatActivity {


    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_feed);

        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        //webView.loadUrl("http://www.gooogle.com");
        webView.loadUrl("http://192.168.1.144:8080/");

        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    public void onBackPressed(){
        if (webView.canGoBack()){
            webView.goBack();
        }
        else{
            super.onBackPressed();
        }}
}

/*Button clk;
    VideoView videov;
    MediaController mediaC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_feed);

        clk=(Button) findViewById(R.id.play);
        videov= (VideoView) findViewById(R.id.videoView);
        mediaC= new MediaController(this);


    }

    public void videoplay(View v)
    {
        String videopath= "android.resource://com.example.gauth.iot_homeautomationv1/"+R.raw.test;
        //String videopath="http://10.216.23.102:8080/viewers.html#vlc";
        Uri uri = Uri.parse(videopath);

        videov.setVideoURI(uri);
        videov.setMediaController(mediaC);
        mediaC.setAnchorView(videov);
        videov.start();
    }*/
