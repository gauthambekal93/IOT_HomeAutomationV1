package com.example.gauth.iot_homeautomationv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PowerHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_home_page);

        final EditText powerCustomerName;
        Button customerPowerData;

        powerCustomerName=(EditText) findViewById(R.id.powerCustomerUserName);
            customerPowerData=(Button) findViewById(R.id.getCustomerPower);

        customerPowerData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Constants.usernamePowerGenerator = powerCustomerName.getText().toString();
                    startActivity(new Intent(getBaseContext(), PowerGeneratorReading.class));

                }
            });
        }

    }

