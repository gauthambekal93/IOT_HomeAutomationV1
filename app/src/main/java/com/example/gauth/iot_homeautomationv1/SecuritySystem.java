package com.example.gauth.iot_homeautomationv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SecuritySystem extends AppCompatActivity {

    Button disarmed;
    Button armedStay;
    Button armedAway;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_system);

        disarmed = (Button) findViewById(R.id.disarmed);
        armedStay = (Button) findViewById(R.id.armedStay);
        armedAway = (Button) findViewById(R.id.armedAway);

        disarmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Security System disarmed", Toast.LENGTH_SHORT).show();
securityUpdate("DISARMED");
            }
        });

        armedStay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Security System Armed Stay", Toast.LENGTH_SHORT).show();
     securityUpdate("ARMED STAY");
            }
        });

        armedAway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Security System Armed Away", Toast.LENGTH_SHORT).show();
                securityUpdate("ARMED AWAY");
            }
        });
    }

    public void securityUpdate(final String status) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_SECURITY,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(),
                                    jsonObject.getString("message"),Toast.LENGTH_SHORT);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getBaseContext(),"Unsucessfully Registration",Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params =new HashMap<>();
                params.put("username",Constants.username);
                params.put("securityType",status);
                return  params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}

