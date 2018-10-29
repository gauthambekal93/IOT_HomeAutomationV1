package com.example.gauth.iot_homeautomationv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Lights extends AppCompatActivity {
Spinner floorLevel;
Button dinnerLevel;
    ArrayList<String > dropDown= new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lights);
        ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, dropDown);

        floorLevel=(Spinner)findViewById(R.id.changeFloorDropdown);
        dinnerLevel =(Button)findViewById(R.id.dimmerLevel);
        dropDown.add("Main Floor");
        dropDown.add("Upstairs");
        floorLevel.setAdapter(spinAdapter);

        dinnerLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),floorLevel.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();

            }
        });

    }
}
