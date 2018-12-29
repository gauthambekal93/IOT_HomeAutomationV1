package com.example.gauth.iot_homeautomationv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PowerGeneratorReading extends AppCompatActivity {



    static ArrayList<String> tempBarEntries;
    static ArrayList<String> tempTheDates;

    static String rows[];
    static String coloumn[];
    Button displayPower;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_generator_reading);


        displayPower= (Button) findViewById(R.id.displayPowerData);


tempBarEntries = new ArrayList<>();
tempTheDates = new ArrayList<>();
        displayCompleteStatus();

        displayPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(getApplicationContext(),"USERNAME IS "+Constants.usernamePowerGenerator,Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getBaseContext(),PowerGraph.class));
                //setGraph();
/*
                for(int i=0; i<=20;i++)
                {
                    barEntries.add(new BarEntry(i,i));
                    barDataSet = new BarDataSet(barEntries,"Dates");
                    theDates.add("X "+i);
                    //display();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                theData = new BarData(theDates,barDataSet);  //passing x and y coordinates
                barChart.setData(theData);*/
            }
        });





        //barChart.setTouchEnabled(true);
        // barChart.setDragEnabled(true);
        //   barChart.setScaleEnabled(true);
    }

    public  void displayCompleteStatus()
    {
       // applianceStatus.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_PowerGeneratorStatus,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {

                            /*Toast.makeText(getApplicationContext(),
                                    response.toString(),Toast.LENGTH_SHORT).show();*/
                          //  Log.i("Response is ", response.toString());

                            rows = response.split("],\\[");
//                            Toast.makeText(getApplicationContext(),
//                                    rows[0].toString(),Toast.LENGTH_SHORT).show();

                            for(int i=0; i<rows.length;i++)
                            {
                                coloumn=rows[i].replace("[[","").replace("]]","").split(",");

   //                             Toast.makeText(getApplicationContext(), "Aji Loves" + coloumn[1].toString() + " " +coloumn[2].toString(),Toast.LENGTH_SHORT).show();
                                  //tempBarEntries.add(Integer.parseInt(coloumn[1]));
                                   tempTheDates.add(coloumn[2]);
                                   tempBarEntries.add(coloumn[1]);
                               //barEntries.add(new BarEntry(Float.parseFloat(coloumn[1]),i));
                              //  barEntries.add(new BarEntry(10.0f,i));
                            //     theDates.add(coloumn[2]);

                               // Toast.makeText(getApplicationContext(), "date is "+ rows.length, Toast.LENGTH_SHORT).show();
                               //Toast.makeText(getApplicationContext(), "length is "+ barEntries.size(), Toast.LENGTH_SHORT).show();

                      //          Toast.makeText(getApplicationContext(), "date is "+ theDates.size(), Toast.LENGTH_SHORT).show();
                              //  Toast.makeText(getApplicationContext(), "length is "+ barEntries.size(), Toast.LENGTH_SHORT).show();

                               // Toast.makeText(getApplicationContext(), coloumn[1].toString()+" "+coloumn[2], Toast.LENGTH_SHORT).show();
                                /*for(int j =0;j<coloumn.length;j++)
                                {

                                    temp =temp+coloumn[j]+"\n";
                                }*/

                           //     applianceStatus.add(temp);

                            }
                            // arrayAdapter.notifyDataSetChanged();

                        }

                        catch (Exception e) {
                            Toast.makeText(getBaseContext(),e.getStackTrace().toString(),Toast.LENGTH_SHORT).show();
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
                params.put("username",Constants.usernamePowerGenerator);
                return  params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }



}
