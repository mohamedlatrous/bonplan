package com.example.bonplan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class signup extends AppCompatActivity {
    private EditText signupEmail, signupPassword,signupName;
    private Button signupButton;
    private TextView loginRedirectText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signupEmail = findViewById(R.id.email);
        signupPassword = findViewById(R.id.password);
        signupName = findViewById(R.id.name);
        signupButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = signupEmail.getText().toString();
                String pass = signupPassword.getText().toString();
                String name = signupName.getText().toString();
                if (email.isEmpty()){
                    signupEmail.setError("Email cannot be empty");
                }
                if (name.isEmpty()){
                    signupName.setError("Name cannot be empty");
                }
                if (pass.isEmpty()){
                    signupPassword.setError("Password cannot be empty");
                } else{
                   String url = "https://bonplan.clicktechnol.info/user/signup";
                    try {
                        RequestQueue requestQueue = Volley.newRequestQueue(signup.this);
                        JSONObject jsonBody = new JSONObject();
                        jsonBody.put("email", email);
                        jsonBody.put("password", pass);
                        jsonBody.put("name", name);
                        final String mRequestBody = jsonBody.toString();
                        Log.i("LOG_RESPONSE", mRequestBody);

                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String result = response.getString("result");
                                    if(result.equals("success")){
                                    Toast.makeText(getApplicationContext(), "User registered successfully ! ", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(signup.this, MainActivity.class);
                                    startActivity(intent);
                                    }else{
                                        signupEmail.setError(result);
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        });

                        requestQueue.add(jsonObjectRequest);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }                    }

                }

        });
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signup.this, MainActivity.class));
            }
        });
    }
}
