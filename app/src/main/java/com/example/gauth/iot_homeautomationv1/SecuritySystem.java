package com.example.gauth.iot_homeautomationv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecuritySystem extends AppCompatActivity {

    Button disarmed;
    Button armedStay;
    Button armedAway;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_system);

        disarmed =(Button) findViewById(R.id.disarmed);
        armedStay =(Button) findViewById(R.id.armedStay);
        armedAway= (Button) findViewById(R.id.armedAway);

        disarmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Security System disarmed",Toast.LENGTH_SHORT).show();
            }
        });

        armedStay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Security System Armed Stay",Toast.LENGTH_SHORT).show();
            }
        });

        armedAway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Security System Armed Away",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
