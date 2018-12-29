package com.example.gauth.iot_homeautomationv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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

public class HomePage extends AppCompatActivity {
    ArrayList<String > dropDown= new ArrayList<String>();
    Spinner spin;
    Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        dropDown.add("");
        dropDown.add("Security System");
        dropDown.add("Garage Door");
        dropDown.add("Thermostat");
        dropDown.add("Lights");
        dropDown.add("Live Feed");
        dropDown.add("Video");
        dropDown.add("Locks");
        dropDown.add("Weather");
        dropDown.add("Window Sensor");
        dropDown.add("Motion Detector");
        dropDown.add("Electric Appliances");
        dropDown.add("Account Settings");

        spin=(Spinner)findViewById(R.id.homePageSpinner);
        update =(Button) findViewById(R.id.updateHomePage);
        ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, dropDown);
        spin.setAdapter(spinAdapter);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),spin.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();

                if(spin.getSelectedItem().toString().equals("Security System"))
                {
                    startActivity(new Intent(getBaseContext(),SecuritySystem.class));
                }
                if(spin.getSelectedItem().toString().equals("Garage Door"))
                {
                    startActivity(new Intent(getBaseContext(),GarageDoors.class));
                }
                if(spin.getSelectedItem().toString().equals("Thermostat"))
                {
                    startActivity(new Intent(getBaseContext(),Thermostat.class));
                }
                if(spin.getSelectedItem().toString().equals("Lights"))
                {
                    startActivity(new Intent(getBaseContext(),Lights.class));
                }
                if(spin.getSelectedItem().toString().equals("Live Feed"))
                {
                    startActivity(new Intent(getBaseContext(),LiveFeed.class));
                }
                if(spin.getSelectedItem().toString().equals("Video"))
                {
                    startActivity(new Intent(getBaseContext(),Video.class));
                }
                if(spin.getSelectedItem().toString().equals("Locks"))
                {
                    startActivity(new Intent(getBaseContext(),Locks.class));
                }
                if(spin.getSelectedItem().toString().equals("Weather"))
                {
                    startActivity(new Intent(getBaseContext(),Weather.class));
                }
                if(spin.getSelectedItem().toString().equals("Window Sensor"))
                {
                    startActivity(new Intent(getBaseContext(),Window.class));
                }
                if(spin.getSelectedItem().toString().equals("Motion Detector"))
                {
                    startActivity(new Intent(getBaseContext(),MotionDetector.class));
                }
                if(spin.getSelectedItem().toString().equals("Electric Appliances"))
                {
                    startActivity(new Intent(getBaseContext(),ElectricAppliance.class));
                }
                if(spin.getSelectedItem().toString().equals("Account Settings"))
                {
                    startActivity(new Intent(getBaseContext(),AccountSettings.class));
                }

            }
        });
    getCompleteSecurityStatus();
    getCompleteWindowStatus();
   getCompleteGarageStatus();
    getCompleteElectricApplianceStatus();
    getCompleteLockStatus();
    getCompleteLightStatus();
    getCompleteMotionDetectorStatus();
    getCompleteThermostatStatus();
    }


    public void getCompleteSecurityStatus() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_STATUS,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(),"SECURITY STATUS IS "+
                                    jsonObject.getString("securityStatus"),Toast.LENGTH_SHORT).show();
                        CompleteStatus.SecurityStatus = jsonObject.getString("securityStatus");
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
                return  params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void getCompleteWindowStatus() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_STATUS,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject = new JSONObject(response);

                            //Toast.makeText(getApplicationContext(), "TRY IS "+ response.toString(),Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),"WINDOW STATUS IS "+
                                    jsonObject.getString("windowStatus"),Toast.LENGTH_SHORT).show();
                           CompleteStatus.WindowStatus= jsonObject.getString("windowStatus");
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
                return  params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
    public void getCompleteGarageStatus() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_STATUS,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(),
                                    "Garage Door 1 is "+jsonObject.getString("garageDoor1"),Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),"Garage Door 2 is "+
                                    jsonObject.getString("garageDoor2"),Toast.LENGTH_SHORT).show();
                            CompleteStatus.GarageStatusDoor1= jsonObject.getString("garageDoor1");
                            CompleteStatus.GarageStatusDoor2= jsonObject.getString("garageDoor2");
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
                return  params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void getCompleteElectricApplianceStatus()
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_STATUS,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(),
                                    "FAN is "+jsonObject.getString("fanStatus"),Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),"REFRIGERATOR is "+
                                    jsonObject.getString("refrigeratorStatus"),Toast.LENGTH_SHORT).show();
                            CompleteStatus.FanStatus= jsonObject.getString("fanStatus");
                            CompleteStatus.RefrigeratorStatus= jsonObject.getString("refrigeratorStatus");
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
                return  params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void getCompleteLockStatus()
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_STATUS,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(),
                                    "Front Door "+jsonObject.getString("Lock1status"),Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),"Back Door "+
                                    jsonObject.getString("Lock2status"),Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),"Garage Door "+
                                    jsonObject.getString("Lock3status"),Toast.LENGTH_SHORT).show();

                            CompleteStatus.FrontLockStatus= jsonObject.getString("Lock1status");
                            CompleteStatus.BackLockStatus= jsonObject.getString("Lock2status");
                            CompleteStatus.GarageLockStatus= jsonObject.getString("Lock3status");
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
                return  params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void getCompleteLightStatus()
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_STATUS,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(),
                                    "Main Floor Light "+jsonObject.getString("mainfloorlight"),Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),"Upstairs Light "+
                                    jsonObject.getString("upstairlight"),Toast.LENGTH_SHORT).show();

                            CompleteStatus.LightStatusMainFlr= jsonObject.getString("mainfloorlight");
                            CompleteStatus.LightStatusUpstair= jsonObject.getString("upstairlight");

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
                return  params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void getCompleteMotionDetectorStatus()
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_STATUS,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(),
                                    "Main Floor Motion Detection "+jsonObject.getString("detectorMainFloor"),Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),"Upstairs Motion Detection "+
                                    jsonObject.getString("detectorUpstairs"),Toast.LENGTH_SHORT).show();

                            CompleteStatus.MainFloorMotionDetectorStatus =jsonObject.getString("detectorMainFloor");
                            CompleteStatus.UpstairMotionDetectorStatus =jsonObject.getString("detectorUpstairs");
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
                return  params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }


    public void getCompleteThermostatStatus()
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_STATUS,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(),
                                    "mainFloorMode "+jsonObject.getString("modeMainFloor"),Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),"mainFloorFan "+
                                    jsonObject.getString("fanMainFloor"),Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),
                                    "upstairsMode "+jsonObject.getString("modeUpstairs"),Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),"upstairsFan "+
                                    jsonObject.getString("fanUpstairs"),Toast.LENGTH_SHORT).show();


                            CompleteStatus.ThermostatMainFloorModeStatus =jsonObject.getString("modeMainFloor");
                            CompleteStatus.ThermostatMainFloorFanStatus =jsonObject.getString("fanMainFloor");
                            CompleteStatus.ThermostatUpstairModeStatus =jsonObject.getString("modeUpstairs");
                            CompleteStatus.ThermostatUpstairFanStatus =jsonObject.getString("fanUpstairs");
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
                return  params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}
