package com.example.gauth.iot_homeautomationv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Arrays;


public class PowerGraph extends AppCompatActivity {
    BarDataSet barDataSet;
    BarChart barChart;
    BarData theData;

     ArrayList<String> theDates;

     ArrayList<BarEntry> barEntries;

     ArrayList <Integer> newList= new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_graph);

        //Toast.makeText(getApplicationContext(),"THE DATE "+PowerGeneratorReading.tempTheDates.size(),Toast.LENGTH_SHORT).show();
        //PowerGeneratorReading.tempBarEntries = (ArrayList<String>) Arrays.asList(PowerGeneratorReading.temp.split(" "));
        //Toast.makeText(getApplicationContext()," THE SIZE "+PowerGeneratorReading.tempBarEntries.size(),Toast.LENGTH_LONG).show();



     for(int i =0; i<PowerGeneratorReading.tempBarEntries.size();i++)
     {
         newList.add(Integer.parseInt(PowerGeneratorReading.tempBarEntries.get(i).replaceAll("\"","").replaceAll(" ","")));
     }
Log.i("HELLLLLLLOOOOO ","WORLLLLLLD");
        Log.i("TEMP SIZE IS!!! ", Arrays.toString(PowerGeneratorReading.tempBarEntries.toArray())+"");
        Log.i("Date SIZE IS!!! ",Arrays.toString(PowerGeneratorReading.tempTheDates.toArray())+"");
        Log.i("AFTER REPLACEMENT!!! ",Arrays.toString(newList.toArray()));

        /*for(int i =0; i<PowerGeneratorReading.tempBarEntries.size();i++)
        {
            Log.i("The values are",PowerGeneratorReading.tempBarEntries.get(i).replaceAll("\"","").replaceAll(" ",""));
        }*/
        barChart = (BarChart) findViewById(R.id.bargraph);

        barEntries = new ArrayList<>();

        theDates = new ArrayList<>();
        for(int i =0;i<newList.size();i++)
        {
            barEntries.add(new BarEntry(newList.get(i),i));
            theDates.add(PowerGeneratorReading.tempTheDates.get(i));
        }
 /*
        barEntries.add(new BarEntry(10f,0));
        barEntries.add(new BarEntry(20f,1));

        theDates.add("laukik");
        theDates.add("aji");
*/
        barDataSet = new BarDataSet(barEntries,"dates");

        theData = new BarData(theDates,barDataSet);

        barChart.setData(theData);


        /*
        for(int i=0; i<barEntries.size();i++)
        {
            //barEntries.add(new BarEntry(i,i));
            barDataSet = new BarDataSet(barEntries,"Dates");
            theDates.add("X "+i);
            //display();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }*/


       /* for(int i=0; i<=20;i++)
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

        }*/



    }
}
