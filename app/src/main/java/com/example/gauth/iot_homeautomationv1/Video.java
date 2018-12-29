package com.example.gauth.iot_homeautomationv1;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;


public class Video extends AppCompatActivity {

    Button clk;
    VideoView videov;
    MediaController mediaC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        clk=(Button) findViewById(R.id.play);
        videov= (VideoView) findViewById(R.id.videoView);
        mediaC= new MediaController(this);


    }

    public void videoPlay(View v)
    {
       // String videopath= "android.resource://com.example.gauth.iot_homeautomationv1/"+R.raw.test;
        String videopath= "android.resource://com.example.gauth.iot_homeautomationv1/"+ R.raw.test;
        //String videopath="http://10.216.23.102:8080/viewers.html#vlc";
        Uri uri = Uri.parse(videopath);

        videov.setVideoURI(uri);
        videov.setMediaController(mediaC);
        mediaC.setAnchorView(videov);
        videov.start();
    }
    }