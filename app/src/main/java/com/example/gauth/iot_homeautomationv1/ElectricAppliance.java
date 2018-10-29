package com.example.gauth.iot_homeautomationv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ElectricAppliance extends AppCompatActivity {

    Spinner applianceType;
    ArrayList<String > dropDown= new ArrayList<String>();
     Button onAppliance;
     Button offAppliance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electric_appliance);
applianceType=(Spinner)findViewById(R.id.dropDownAppliance);
onAppliance=(Button)findViewById(R.id.onAppliance);
offAppliance=(Button)findViewById(R.id.offAppliance);

        dropDown.add("");
        dropDown.add("FAN");
        dropDown.add("REFRIGERATOR");
        ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, dropDown);
        applianceType.setAdapter(spinAdapter);

        onAppliance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"ON "+applianceType.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();

            }
        });

        offAppliance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"OFF "+applianceType.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();

            }
        });
    }
}
