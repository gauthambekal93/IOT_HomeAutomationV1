package com.example.gauth.iot_homeautomationv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class GarageDoors extends AppCompatActivity {
    ArrayList<String > dropDown= new ArrayList<String>();
    Spinner spin;
    Button open;
    Button close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage_doors);
        dropDown.add("");
        dropDown.add("Garage 1");
        dropDown.add("Garage 2");
        open=(Button) findViewById(R.id.openDoor);
        close=(Button)findViewById(R.id.closeDoor);
        spin=(Spinner)findViewById(R.id.garageDoorSpinner);

        ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, dropDown);
        spin.setAdapter(spinAdapter);

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"OPEN "+spin.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"CLOSE "+spin.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
