package com.example.gauth.iot_homeautomationv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Lights extends AppCompatActivity {
Spinner floorLevel;
Button lightOn;
Button lightOff;
TextView lightstatusmainflr, lightstatusupstairs;
    ArrayList<String > dropDown= new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lights);
        ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, dropDown);

        lightstatusmainflr= (TextView) findViewById(R.id.status);
        lightstatusmainflr.setText("Main Floor " + CompleteStatus.LightStatusMainFlr);

        lightstatusupstairs= (TextView) findViewById(R.id.status1);
        lightstatusupstairs.setText("Upstair " + CompleteStatus.LightStatusUpstair);

        floorLevel=(Spinner)findViewById(R.id.changeFloorDropdown);
        lightOn =(Button)findViewById(R.id.onbutton);
        lightOff= (Button)findViewById(R.id.offbutton);
        dropDown.add("");
        dropDown.add("Main Floor");
        dropDown.add("Upstairs");
        floorLevel.setAdapter(spinAdapter);

        lightOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"ON "+floorLevel.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                floorLevel.getSelectedItem();
                lightUpdate("ON", floorLevel.getSelectedItem().toString());

            }
        });

        lightOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"OFF "+floorLevel.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                floorLevel.getSelectedItem();
                lightUpdate("OFF",floorLevel.getSelectedItem().toString());
            }
        });
    }

    public void lightUpdate(final String status, final String floor)
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_LIGHTS,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObject1 = new JSONObject(jsonObject.getString("mainfloorlight"));
                            JSONObject jsonObject2 = new JSONObject(jsonObject.getString("upstairlight"));

                            Toast.makeText(getApplicationContext(),
                                    "Main Floor Light "+jsonObject.getString("mainfloorlight"),Toast.LENGTH_SHORT).show();

                            Toast.makeText(getApplicationContext(),  jsonObject1.getString("MainFloor"),Toast.LENGTH_SHORT).show();
                            set(jsonObject1.getString("MainFloor"));

                            Toast.makeText(getApplicationContext(),"Upstairs Light "+
                                    jsonObject.getString("upstairlight"),Toast.LENGTH_SHORT).show();

                            Toast.makeText(getApplicationContext(),  jsonObject2.getString("Upstairs"),Toast.LENGTH_SHORT).show();
                            set(jsonObject2.getString("Upstairs"));
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
                params.put("username",Constants.username);
                params.put("lightFloor",floor);
                params.put("lightStatus",status);
                return  params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
    public void set(String value)
    {
        lightstatusmainflr.setText(value);
        lightstatusupstairs.setText(value);
    }
}