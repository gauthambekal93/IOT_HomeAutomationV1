package com.example.gauth.iot_homeautomationv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    ArrayList<String > dropDown= new ArrayList<String>();
    Spinner spin;
    Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        dropDown.add("");
        dropDown.add("Security System");
        dropDown.add("Garage Door");
        dropDown.add("Thermostat");
        dropDown.add("Lights");
        dropDown.add("Video");
        dropDown.add("Locks");
        dropDown.add("Weather");
        dropDown.add("Window Sensor");
        dropDown.add("Motion Detector");
        dropDown.add("Electric Appliances");
        dropDown.add("Account Settings");

        spin=(Spinner)findViewById(R.id.homePageSpinner);
        update =(Button) findViewById(R.id.updateHomePage);
        ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, dropDown);
        spin.setAdapter(spinAdapter);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),spin.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();

                if(spin.getSelectedItem().toString().equals("Security System"))
                {
                    startActivity(new Intent(getBaseContext(),SecuritySystem.class));
                }
                if(spin.getSelectedItem().toString().equals("Garage Door"))
                {
                    startActivity(new Intent(getBaseContext(),GarageDoors.class));
                }
                if(spin.getSelectedItem().toString().equals("Thermostat"))
                {
                    startActivity(new Intent(getBaseContext(),Thermostat.class));
                }
                if(spin.getSelectedItem().toString().equals("Lights"))
                {
                    startActivity(new Intent(getBaseContext(),Lights.class));
                }
                if(spin.getSelectedItem().toString().equals("Video"))
                {
                    startActivity(new Intent(getBaseContext(),LiveFeed.class));
                }
                if(spin.getSelectedItem().toString().equals("Locks"))
                {
                    startActivity(new Intent(getBaseContext(),Locks.class));
                }
                if(spin.getSelectedItem().toString().equals("Weather"))
                {
                    startActivity(new Intent(getBaseContext(),Weather.class));
                }
                if(spin.getSelectedItem().toString().equals("Window Sensor"))
                {
                    startActivity(new Intent(getBaseContext(),Window.class));
                }
                if(spin.getSelectedItem().toString().equals("Motion Detector"))
                {
                    startActivity(new Intent(getBaseContext(),MotionDetector.class));
                }
                if(spin.getSelectedItem().toString().equals("Electric Appliances"))
                {
                    startActivity(new Intent(getBaseContext(),ElectricAppliance.class));
                }
                if(spin.getSelectedItem().toString().equals("Account Settings"))
                {
                    startActivity(new Intent(getBaseContext(),AccountSettings.class));
                }

            }
        });
    }
}
