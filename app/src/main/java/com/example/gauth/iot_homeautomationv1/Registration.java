package com.example.gauth.iot_homeautomationv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {

    EditText profileName;
    EditText username;
    EditText password;
    Spinner role;
    Button newRegister;
    ArrayList<String> userRole =new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        profileName=(EditText)findViewById(R.id.signUpName);
        username =(EditText) findViewById(R.id.signUpUsername);
        password =(EditText)findViewById(R.id.signUpPassword);
        role=(Spinner) findViewById(R.id.role);
        newRegister =(Button)findViewById(R.id.register);
        userRole.add("Consumer");
        userRole.add("Utility");
        userRole.add("Power Generator");
        ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, userRole);
        role.setAdapter(spinAdapter);

        newRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (role.getSelectedItem().toString().equals("Consumer"))
                {
                    Toast.makeText(getBaseContext(),"Consumer clicked",Toast.LENGTH_SHORT).show();
                    registerUser("Consumer");
                }
                if (role.getSelectedItem().toString().equals("Utility"))
                {Toast.makeText(getBaseContext(),"Utility clicked",Toast.LENGTH_SHORT).show();
                    registerUser("Utility");
                }
                if (role.getSelectedItem().toString().equals("Power Generator"))
                    Toast.makeText(getBaseContext(),"Power Generator clicked",Toast.LENGTH_SHORT).show();
                {registerUser("Power Generator");
                }
            }
        });
    }

public  void registerUser(final String role)
{
    StringRequest stringRequest = new StringRequest(Request.Method.POST,
            Constants.URL_REGISTER,
            new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                           if(jsonObject.getString("message").equals("User registered successfully"))
                        {
                            //Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),jsonObject.getString("error"),Toast.LENGTH_SHORT).show();
                            Constants.username = username.getText().toString();

                            if (role.equals("Consumer")) {
                                startActivity(new Intent(getBaseContext(), HomePage.class));
                            }
                            if (role.equals("Utility")) {
                                startActivity(new Intent(getBaseContext(), UtilityHomePage.class));
                            }
                            if (role.equals("Power Generator")) {
                                startActivity(new Intent(getBaseContext(), PowerHomePage.class));
                            }
                        }
                        else{
                               Toast.makeText(getApplicationContext(),jsonObject.getString("error"),Toast.LENGTH_SHORT).show();
                              //Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                           }
                    }
                catch (Exception e) {
                            e.printStackTrace();
                        }
            }

            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getBaseContext(),"Unsucessfully Registration",Toast.LENGTH_SHORT).show();
                }
            })
    {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String> params =new HashMap<>();
            params.put("username",username.getText().toString());
            params.put("password",password.getText().toString());
            params.put("name",profileName.getText().toString());
            params.put("role",role);
return  params;
        }
    };
    RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
}
}
