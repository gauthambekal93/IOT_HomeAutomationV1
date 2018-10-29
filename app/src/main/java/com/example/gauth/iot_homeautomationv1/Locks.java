package com.example.gauth.iot_homeautomationv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Locks extends AppCompatActivity {

    Spinner door;
    Button locked;
    Button unlocked;
    ArrayList<String> doorType= new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locks);

        door=(Spinner)findViewById(R.id.door);
        locked=(Button)findViewById(R.id.locked);
        unlocked=(Button)findViewById(R.id.unlocked);

        doorType.add("");
        doorType.add("Front Door");
        doorType.add("Back Door");
        doorType.add("Garage Door");

        locked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),"Locked "+door.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            }
        });

        unlocked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Unlocked "+door.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();

            }
        });

    }
}
