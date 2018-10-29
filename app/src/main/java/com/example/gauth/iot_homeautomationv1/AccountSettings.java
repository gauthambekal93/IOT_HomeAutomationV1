package com.example.gauth.iot_homeautomationv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AccountSettings extends AppCompatActivity {

    EditText newUsername;
    EditText newPassword;
    Button submitChanges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
     newUsername =(EditText)findViewById(R.id.updateUsername);
     newPassword =(EditText)findViewById(R.id.updatePassword);
     submitChanges=(Button)findViewById(R.id.submitChanges);

     submitChanges.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Toast.makeText(getApplicationContext(),"Changes Submitted",Toast.LENGTH_SHORT).show();

         }
     });

    }
}
