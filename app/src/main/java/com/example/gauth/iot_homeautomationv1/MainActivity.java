package com.example.gauth.iot_homeautomationv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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

        //ADD THE API CALLING HERE
         loginUser();
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

    public  void loginUser()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_LOGIN,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            if(jsonObject.getString("message").equals("Welcome Back!"))
                            {
                                String Role = jsonObject.getString("role");
                                Constants.username=jsonObject.getString("username");

                                if (Role.equals("Consumer")) {
                                    Toast.makeText(getApplicationContext(),"WELCOME BACK "+Constants.username,Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getBaseContext(), HomePage.class));
                                }
                                if (Role.equals("Utility")) {
                                    Toast.makeText(getApplicationContext(),"WELCOME BACK!",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getBaseContext(), UtilityHomePage.class));
                                }
                                if (Role.equals("Power Generator")) {
                                    Toast.makeText(getApplicationContext(),"WELCOME BACK!",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getBaseContext(), PowerHomePage.class));
                                }
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"INCORRECT USERNAME OR PASSWORD!",Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getBaseContext(),"Unsuccessful Registration",Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params =new HashMap<>();
                params.put("username",username.getText().toString());
                params.put("password",password.getText().toString());
                return  params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}
