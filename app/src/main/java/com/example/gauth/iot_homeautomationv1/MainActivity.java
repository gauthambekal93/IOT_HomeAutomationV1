package com.example.gauth.iot_homeautomationv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText username;
EditText password;
Button login;
Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
username =(EditText)findViewById(R.id.loginUsername);
password= (EditText)findViewById(R.id.loginPassword);
login =(Button)findViewById(R.id.login);
signUp= (Button)findViewById(R.id.register);

login.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Toast.makeText(getApplicationContext(),"Logged In",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getBaseContext(),HomePage.class));
    }
});

signUp.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Toast.makeText(getApplicationContext(),"Sign Up",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getBaseContext(),Registration.class));
    }
});
    }
}
