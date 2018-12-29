package com.example.gauth.iot_homeautomationv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UtilityHomePage extends AppCompatActivity {

    EditText enterCustomerName;
    EditText enterCustomerPassword;
    Button submitCustomerData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility_home_page);

    enterCustomerName=(EditText) findViewById(R.id.enterCustomerName);
    enterCustomerPassword=(EditText)findViewById(R.id.enterCustomerPassword);
    submitCustomerData=(Button) findViewById(R.id.submitCustomersData);

    submitCustomerData.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Constants.usernameUtility = enterCustomerName.getText().toString();
            startActivity(new Intent(getBaseContext(), UtilityApplianceStatus.class));

        }
    });
    }

}
