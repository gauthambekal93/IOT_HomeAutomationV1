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

public class Locks extends AppCompatActivity {

    Spinner door;
    Button locked;
    Button unlocked;
    TextView frontlockstatus, backlockstatus,  garagelockstatus;
    ArrayList<String> doorType= new ArrayList<String>();
    ArrayAdapter<String> spinAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locks);

        frontlockstatus= (TextView) findViewById(R.id.status);
        frontlockstatus.setText("Front "+ CompleteStatus.FrontLockStatus+"\n");

        backlockstatus= (TextView) findViewById(R.id.status1);
        backlockstatus.setText("Back "+
                CompleteStatus.BackLockStatus);

        garagelockstatus= (TextView) findViewById(R.id.status2);
        garagelockstatus.setText("Garage "+CompleteStatus.GarageLockStatus);



        spinAdapter = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_item,doorType);
        door=(Spinner)findViewById(R.id.door);
        locked=(Button)findViewById(R.id.locked);
        unlocked=(Button)findViewById(R.id.unlocked);

        doorType.add("");
        doorType.add("Front Door");
        doorType.add("Back Door");
        doorType.add("Garage Door");
        door.setAdapter(spinAdapter);

        locked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),"Locked "+door.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                door.getSelectedItem();
                locksUpdate("LOCKED",door.getSelectedItem().toString());
            }
        });

        unlocked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Unlocked "+door.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                door.getSelectedItem();
                locksUpdate("UNLOCKED",door.getSelectedItem().toString());
            }
        });

    }

    public void locksUpdate(final String status, final String type)
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_LOCK,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObject1 = new JSONObject(jsonObject.getString("messageLock1"));
                            JSONObject jsonObject2 = new JSONObject(jsonObject.getString("messageLock2"));
                            JSONObject jsonObject3 = new JSONObject(jsonObject.getString("messageLock3"));

                            Toast.makeText(getApplicationContext(),
                                    "Front Door "+jsonObject.getString("Lock1status"),Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),  jsonObject1.getString("FrontDoor"),Toast.LENGTH_SHORT).show();
                            set(jsonObject1.getString("FrontDoor"));


                            Toast.makeText(getApplicationContext(),"Back Door "+
                                    jsonObject.getString("Lock2status"),Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),  jsonObject2.getString("BackDoor"),Toast.LENGTH_SHORT).show();
                            set(jsonObject2.getString("BackDoor"));


                            Toast.makeText(getApplicationContext(),"Garage Door "+
                                    jsonObject.getString("Lock3status"),Toast.LENGTH_SHORT).show();

                            Toast.makeText(getApplicationContext(),  jsonObject3.getString("GarageDoor"),Toast.LENGTH_SHORT).show();
                            set(jsonObject3.getString("GarageDoor"));



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
                params.put("doorType",type);
                params.put("doorStatus",status);
                return  params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void set(String value)
    {
        frontlockstatus.setText(value);
        backlockstatus.setText(value);
        garagelockstatus.setText(value);
    }
}