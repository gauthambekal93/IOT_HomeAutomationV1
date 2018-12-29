package com.example.gauth.iot_homeautomationv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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

public class UtilityApplianceStatus extends AppCompatActivity {
    Button getUtilityApplianceStatus;
    String rows[];
    String coloumn[];
    String twoDarray[][];
    ArrayList<String> applianceStatus = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility_appliance_status);

        listView =(ListView) findViewById(R.id.applianceStatusList);

        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,applianceStatus);

        listView.setAdapter(arrayAdapter);

        getUtilityApplianceStatus =(Button) findViewById(R.id.utilityApplianceStatus);

        getUtilityApplianceStatus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                displayCompleteStatus();
            }
        });
    }

    public  void displayCompleteStatus()
    {
        applianceStatus.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_ApplianceStatus,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {

                            Toast.makeText(getApplicationContext(),
                                    response.toString(),Toast.LENGTH_SHORT).show();
                            Log.i("Response is ", response.toString());
                            rows = response.split("],\\[");


                            for(int i=0; i<rows.length;i++)
                            {
                                coloumn=rows[i].replace("[[","").replace("]]","").split(",");
                                String temp= "";
                                for(int j =0;j<coloumn.length;j++)
                                {

                                    temp =temp+coloumn[j]+"\n";
                                }
                                applianceStatus.add(temp);
                            }
                            arrayAdapter.notifyDataSetChanged();
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
                params.put("username",Constants.usernameUtility);
                return  params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

}
