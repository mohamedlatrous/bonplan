package com.example.bonplan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bonplan.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class addproduct extends AppCompatActivity {
    private Button selectImage,AddProduct;
    public String category;
    public Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);
        selectImage = findViewById(R.id.selectImage);
        AddProduct = findViewById(R.id.AddProduct);
        Spinner dropdown = findViewById(R.id.categorie);
        String[] items = new String[]{"Vehicles", "Building", "Multimedia", "Home/Garden", "Hobbies", "Clothing"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        protected void onActivityResult (int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == 10 && resultCode == Activity.RESULT_OK) {
                Uri uri = data.getData();
                Context context = addproduct.this;
                String path = RealPathUtil.getRealPath(context, uri);
                bitmap = BitmapFactory.decodeFile(path);

            }
        }

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                category = dropdown.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent, 10);
                } else {
                    ActivityCompat.requestPermissions(addproduct.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }
            }
        });

        AddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText titleProduct = findViewById(R.id.title);
                EditText priceProduct = findViewById(R.id.price);
                EditText telProduct = findViewById(R.id.tel);
                EditText descriptionProduct = findViewById(R.id.description);
                Date currentTime = Calendar.getInstance().getTime();

                String description = descriptionProduct.getText().toString();
                String tel = telProduct.getText().toString();
                String title = titleProduct.getText().toString();
                String price = priceProduct.getText().toString();
                String url = "https://bonplan.clicktechnol.info/product/add";
                String date = currentTime.toString();
                try {

                    RequestQueue requestQueue = Volley.newRequestQueue(addproduct.this);
                    JSONObject jsonBody = new JSONObject();
                    jsonBody.put("description", description);
                    jsonBody.put("tel", tel);
                    jsonBody.put("name", title);
                    jsonBody.put("price", price);
                    jsonBody.put("date", date);
                    jsonBody.put("image", bitmap.toString());
                    jsonBody.put("cat", category);
                    final String mRequestBody = jsonBody.toString();
                    Log.i("LOG_RESPONSE", mRequestBody);

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Toast.makeText(getApplicationContext(), "Product added successfully ! ", Toast.LENGTH_LONG).show();
                                String result = response.getString("result");
                                if(result.equals("success")){
                                    Toast.makeText(getApplicationContext(), "Product added successfully ! ", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(addproduct.this, categorie.class);
                                    startActivity(intent);
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
                }


        }
        });

    }
}