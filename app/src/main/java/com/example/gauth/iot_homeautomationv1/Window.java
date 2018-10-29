package com.example.gauth.iot_homeautomationv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class Window extends AppCompatActivity {

    Button openWindow;
    Button closeWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window);

    openWindow=(Button)findViewById(R.id.onWindow);
    closeWindow=(Button)findViewById(R.id.offWindow);

    }
}
