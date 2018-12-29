package com.example.gauth.iot_homeautomationv1;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Window extends AppCompatActivity {

    Button openWindow;
    Button closeWindow;
    TextView status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window);

        status= (TextView) findViewById(R.id.status);
        status.setText(CompleteStatus.WindowStatus);
        //Toast.makeText(this, "Test is" + CompleteStatus.WindowStatus, Toast.LENGTH_SHORT).show();

    openWindow=(Button)findViewById(R.id.onWindow);
    closeWindow=(Button)findViewById(R.id.offWindow);

        openWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "Open Window", Toast.LENGTH_SHORT).show();
                windowUpdate("OPEN");
            }
        });

        closeWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getApplicationContext(), "Close Window", Toast.LENGTH_SHORT).show();
                windowUpdate("CLOSED");
            }
        });
    }

    public void windowUpdate(final String status) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_WINDOW,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObject1 = new JSONObject(jsonObject.getString("window"));
                            Toast.makeText(getApplicationContext(),  jsonObject1.getString("windowstatus"),Toast.LENGTH_SHORT).show();
                        set(jsonObject1.getString("windowstatus"));
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
                params.put("windowstatus",status);
                return  params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
public void set(String value)
{
    status.setText(value);
}
}
