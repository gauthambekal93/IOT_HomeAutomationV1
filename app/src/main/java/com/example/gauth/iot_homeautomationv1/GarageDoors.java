package com.example.gauth.iot_homeautomationv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class GarageDoors extends AppCompatActivity {
    ArrayList<String > dropDown= new ArrayList<String>();
    Spinner spin;
    Button open;
    Button close;
    TextView garagedoor1status,garagedoor2status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage_doors);

        garagedoor1status= (TextView) findViewById(R.id.statusdoor1);
        garagedoor1status.setText("Door 1: " + CompleteStatus.GarageStatusDoor1);

        garagedoor2status= (TextView) findViewById(R.id.statusdoor2);
        garagedoor2status.setText("Door 2: " + CompleteStatus.GarageStatusDoor2);

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
               Toast.makeText(getApplicationContext(),"CLOSED "+spin.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
             garageUpdate("CLOSED",spin.getSelectedItem().toString());
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
                            JSONObject jsonObject1 = new JSONObject(jsonObject.getString("message1"));
                            JSONObject jsonObject2 = new JSONObject(jsonObject.getString("message2"));

                            Toast.makeText(getApplicationContext(),
                                    "Door 1 is "+jsonObject.getString("message1"),Toast.LENGTH_SHORT).show();

                            Toast.makeText(getApplicationContext(),  jsonObject1.getString("door1"),Toast.LENGTH_SHORT).show();
                            set(jsonObject1.getString("door1"));

                            Toast.makeText(getApplicationContext(),"Door 2 is "+
                                    jsonObject.getString("message2"),Toast.LENGTH_SHORT).show();

                            Toast.makeText(getApplicationContext(),  jsonObject2.getString("door2"),Toast.LENGTH_SHORT).show();
                            set(jsonObject2.getString("door2"));
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
    public void set(String value)
    {

        garagedoor1status.setText(value);
        garagedoor2status.setText(value);
    }
}

