package com.example.gauth.iot_homeautomationv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Thermostat extends AppCompatActivity {
    Spinner mainDoor;
    Spinner floorMode;
    Spinner fan;

    Button update;
    ArrayList<String > door= new ArrayList<String>();
    ArrayList<String > mode= new ArrayList<String>();
    ArrayList<String > fans= new ArrayList<String>();

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermostate);

    mainDoor=(Spinner) findViewById(R.id.floorDropDown);
    floorMode=(Spinner)findViewById(R.id.modeDropDown);
    fan=(Spinner)findViewById(R.id.fanDropDown);

    update=(Button) findViewById(R.id.updateThermostate);

    door.add("");
    door.add("Main Floor");
    door.add("Upstairs");

    mode.add("");
    mode.add("Mode Heat");
    mode.add("Mode Cool");
    mode.add("Mode Off");

    fans.add("");
    fans.add("Fan Auto");
    fans.add("Fan Off");

    ArrayAdapter<String> spinAdapterDoor = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, door);
    ArrayAdapter<String> spinAdapterMode = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, mode);
    ArrayAdapter<String> spinAdapterFans = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, fans);

    mainDoor.setAdapter(spinAdapterDoor);
    floorMode.setAdapter(spinAdapterMode);
    fan.setAdapter(spinAdapterFans);

    update.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getApplicationContext(),mainDoor.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(),floorMode.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(),fan.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
        }
    });

    }
}
