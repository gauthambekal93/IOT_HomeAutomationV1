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

public class MotionDetector extends AppCompatActivity {
    Spinner motionDetector;
    Button active;
    Button inActive;
    TextView mainfloormotionstatus, upstairmotionstatus;

    ArrayList<String > dropDown= new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_detector);

        mainfloormotionstatus= (TextView) findViewById(R.id.status);
        mainfloormotionstatus.setText(CompleteStatus.MainFloorMotionDetectorStatus);

        upstairmotionstatus= (TextView) findViewById(R.id.status1);
        upstairmotionstatus.setText(CompleteStatus.UpstairMotionDetectorStatus);

        motionDetector=(Spinner)findViewById(R.id.motionDetectorDoor);
    active=(Button)findViewById(R.id.active);
    inActive=(Button)findViewById(R.id.inActive);

        ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, dropDown);

        dropDown.add("");
    dropDown.add("Main Floor");
    dropDown.add("Upstairs");
    motionDetector.setAdapter(spinAdapter);

    active.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getApplicationContext(),"ACTIVE "+motionDetector.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            motionUpdate("ACTIVE", motionDetector.getSelectedItem().toString());
        }
    });

    inActive.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getApplicationContext(),"INACTIVE "+motionDetector.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            motionUpdate("INACTIVE", motionDetector.getSelectedItem().toString());
        }
    });
    }

    public void motionUpdate(final String status, final String floor)
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_MOTIONDETECTOR,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObject1 = new JSONObject(jsonObject.getString("message33"));
                            JSONObject jsonObject2 = new JSONObject(jsonObject.getString("message44"));
                            Toast.makeText(getApplicationContext(),
                                    "Main Floor Motion Detection "+jsonObject.getString("detectorMainFloor"),Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),  jsonObject1.getString("MainFloorMotion"),Toast.LENGTH_SHORT).show();
                            set(jsonObject1.getString("MainFloorMotion"));

                            Toast.makeText(getApplicationContext(),"Upstairs Motion Detection "+
                                    jsonObject.getString("detectorUpstairs"),Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),  jsonObject2.getString("UpstairsMotion"),Toast.LENGTH_SHORT).show();
                            set(jsonObject2.getString("UpstairsMotion"));
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
                params.put("motionFloor",floor);
                params.put("motionStatus",status);
                return  params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
public void set(String value){
        mainfloormotionstatus.setText(value);
        upstairmotionstatus.setText(value);

}
}

