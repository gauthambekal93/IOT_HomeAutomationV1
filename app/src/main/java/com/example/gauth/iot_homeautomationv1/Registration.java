package com.example.gauth.iot_homeautomationv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Registration extends AppCompatActivity {

    EditText profileName;
    EditText username;
    EditText password;
    Spinner role;
    Button newRegister;
    ArrayList<String> userRole =new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        profileName=(EditText)findViewById(R.id.signUpName);
        username =(EditText) findViewById(R.id.signUpUsername);
        password =(EditText)findViewById(R.id.signUpPassword);
        role=(Spinner) findViewById(R.id.role);
        newRegister =(Button)findViewById(R.id.register);
        userRole.add("Consumer");
        userRole.add("Utility");
        userRole.add("Power Generator");
        ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, userRole);
        role.setAdapter(spinAdapter);

        newRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (role.getSelectedItem().toString().equals("Consumer"))
                {
                    startActivity(new Intent(getBaseContext(), HomePage.class));
                }
                if (role.getSelectedItem().toString().equals("Utility"))
                {
                    startActivity(new Intent(getBaseContext(), UtilityHomePage.class));
                }
                if (role.getSelectedItem().toString().equals("Power Generator"))
                {
                    startActivity(new Intent(getBaseContext(), PowerHomePage.class));
                }
            }
        });


    }
}
