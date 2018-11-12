package com.example.gauth.iot_homeautomationv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class GarageDoors extends AppCompatActivity {
    ArrayList<String > dropDown= new ArrayList<String>();
    Spinner spin;
    Button open;
    Button close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage_doors);
        dropDown.add("");
        dropDown.add("Garage 1");
        dropDown.add("Garage 2");
        open=(Button) findViewById(R.id.openDoor);
        close=(Button)findViewById(R.id.closeDoor);
        spin=(Spinner)findViewById(R.id.garageDoorSpinner);

        ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, dropDown);
        spin.setAdapter(spinAdapter);

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"OPEN "+spin.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                 spin.getSelectedItem();
                 garageUpdate("OPEN", spin.getSelectedItem().toString());

            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"CLOSE "+spin.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
             garageUpdate("CLOSE",spin.getSelectedItem().toString());
            }
        });

    }

    public void garageUpdate(final String status, final String doorNo)
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_GARAGEDOOR,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(),
                                    "Door 1 is "+jsonObject.getString("message1"),Toast.LENGTH_SHORT);
                            Toast.makeText(getApplicationContext(),"Door 2 is "+
                                    jsonObject.getString("message2"),Toast.LENGTH_SHORT);
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
                params.put("garageDoorNo",doorNo);
                params.put("garageDoorStatus",status);
                return  params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}

