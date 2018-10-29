package com.example.gauth.iot_homeautomationv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MotionDetector extends AppCompatActivity {
    Spinner motionDetector;
    Button active;
    Button inActive;
    ArrayList<String > dropDown= new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_detector);

    motionDetector=(Spinner)findViewById(R.id.motionDetectorDoor);
    active=(Button)findViewById(R.id.active);
    inActive=(Button)findViewById(R.id.inActive);
        ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, dropDown);
    dropDown.add("");
        dropDown.add("Main Floor");
    dropDown.add("Upstairs");
    motionDetector.setAdapter(spinAdapter);

    active.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getApplicationContext(),"Active "+motionDetector.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
        }
    });

    inActive.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getApplicationContext(),"Inactive "+motionDetector.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
        }
    });
    }
}
