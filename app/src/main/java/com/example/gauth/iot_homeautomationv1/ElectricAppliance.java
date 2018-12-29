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

public class ElectricAppliance extends AppCompatActivity {

    Spinner applianceType;
     Button onAppliance;
     Button offAppliance;

     TextView fanstatus, refrigeratorstatus;

    ArrayList<String > dropDown= new ArrayList<String>();
    ArrayAdapter<String> spinAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electric_appliance);
        spinAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, dropDown);
applianceType=(Spinner)findViewById(R.id.dropDownAppliance);
onAppliance=(Button)findViewById(R.id.onAppliance);
offAppliance=(Button)findViewById(R.id.offAppliance);

        fanstatus= (TextView) findViewById(R.id.fanstatus);
        fanstatus.setText("Fan "+ CompleteStatus.FanStatus);

        refrigeratorstatus= (TextView) findViewById(R.id.refristatus);
        refrigeratorstatus.setText("Refrigerator "+CompleteStatus.RefrigeratorStatus);


        dropDown.add("");
        dropDown.add("FAN");
        dropDown.add("REFRIGERATOR");
        applianceType.setAdapter(spinAdapter);

        onAppliance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"ON "+applianceType.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                applianceType.getSelectedItem();
                electricAppliancesUpdate("ON",applianceType.getSelectedItem().toString());
            }
        });

        offAppliance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"OFF "+applianceType.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                applianceType.getSelectedItem();
                electricAppliancesUpdate("OFF",applianceType.getSelectedItem().toString());
            }
        });
    }
    public void electricAppliancesUpdate(final String status, final String appType )
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_ELECTRICAPPLIANCES,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(),
                                    "FAN is "+jsonObject.getString("messageApp1"),Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),"REFRIGERATOR is "+
                                    jsonObject.getString("messageApp2"),Toast.LENGTH_SHORT).show();
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
                params.put("appType",appType);
                params.put("appStatus",status);
                return  params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}
