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

public class Thermostat extends AppCompatActivity {
    Spinner floor;
    Spinner floorMode;
    Spinner floorfan;

    TextView statusThermostat;

    String tempFloorNo;
    String tempModeStatus;
    String tempFanStatus;

    Button update;
    ArrayList<String > door= new ArrayList<String>();
    ArrayList<String > mode= new ArrayList<String>();
    ArrayList<String > fans= new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermostat);

        statusThermostat= (TextView) findViewById(R.id.statusthermostat);
        statusThermostat.setText(CompleteStatus.ThermostatMainFloorModeStatus + "\n" +
                CompleteStatus.ThermostatMainFloorFanStatus + "\n" +
                CompleteStatus.ThermostatUpstairModeStatus + "\n" +
                CompleteStatus.ThermostatUpstairFanStatus);


        floor = (Spinner) findViewById(R.id.floorDropDown);
        floorMode = (Spinner) findViewById(R.id.modeDropDown);
        floorfan = (Spinner) findViewById(R.id.fanDropDown);

        update = (Button) findViewById(R.id.updateThermostat);

        door.add("");
        door.add("Main Floor");
        door.add("Upstairs");

        mode.add("");
        mode.add("Mode Heat");
        mode.add("Mode Cool");
        mode.add("Mode Off");

        fans.add("");
        fans.add("Fan Auto");
        fans.add("Fan Off");
        fans.add("Fan On");

        ArrayAdapter<String> spinAdapterDoor = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, door);
        ArrayAdapter<String> spinAdapterMode = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, mode);
        ArrayAdapter<String> spinAdapterFans = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, fans);

        floor.setAdapter(spinAdapterDoor);
        floorMode.setAdapter(spinAdapterMode);
        floorfan.setAdapter(spinAdapterFans);



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), floor.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                floor.getSelectedItem();
               // thermostatUpdate("Update",  floor.getSelectedItem().toString());

                tempFloorNo= floor.getSelectedItem().toString();


                Toast.makeText(getApplicationContext(), floorMode.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                floorMode.getSelectedItem();
                //thermostatUpdate("Update", floorMode.getSelectedItem().toString());
                tempModeStatus= floorMode.getSelectedItem().toString();


                Toast.makeText(getApplicationContext(), floorfan.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                floorfan.getSelectedItem();
                //thermostatUpdate("Update", floorfan.getSelectedItem().toString());
                tempFanStatus= floorfan.getSelectedItem().toString();
                thermostatUpdate("Update", tempFloorNo, tempModeStatus, tempFanStatus);

            }
        });
    }

    public void thermostatUpdate(final String status, final String floorNo, final String modestatus, final String fanstatus)
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_THERMOSTAT,
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
                params.put("floorNo",floorNo);
                params.put("modestatus",modestatus);
                params.put("fanstatus",fanstatus);
                return  params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void set(String value)
    {
        statusThermostat.setText(value);
    }
}