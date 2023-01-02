package com.example.bonplan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class dashboard extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Product> productList;
    public String search ="";
    public String newString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_dashboard);
        Bundle extras = getIntent().getExtras();
        newString= extras.getString("cat");

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        EditText SearchBar = findViewById(R.id.search);
        Button searchBtn = findViewById(R.id.searchBtn);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search = SearchBar.getText().toString();
                requestQueue = Volley.newRequestQueue(dashboard.this);
                productList = new ArrayList<>();
                fetchProducts(search);
            }
        });
        requestQueue = Volley.newRequestQueue(dashboard.this);
        productList = new ArrayList<>();
        fetchProducts(search);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.add:
                Intent intent = new Intent(dashboard.this, addproduct.class);
                startActivity(intent);
                return true;
            case R.id.logout:
                Intent intent1 = new Intent(dashboard.this, MainActivity.class);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }
    private void fetchProducts(String search) {
        String url;
        String cat = newString;
        if(Objects.equals(search, "")){
             url = "https://bonplan.clicktechnol.info/product/list?cat="+cat;}
        else{
             url = "https://bonplan.clicktechnol.info/product/list?cat="+cat+"&s="+search;
        }
Log.d("Search",url);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i("LOG_RESPONSE", "");

                        for (int i = 0 ; i < response.length() ; i ++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String name = jsonObject.getString("name");
                                String price = jsonObject.getString("price");
                                String date = jsonObject.getString("date");
                                String image = jsonObject.getString("image");
                                String tel = jsonObject.getString("tel");
                                String description = jsonObject.getString("description");
                                Product product = new Product(name, price, date, image,tel , description);
                                productList.add(product);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            ProductAdapter adapter = new ProductAdapter(dashboard.this , productList);
                            recyclerView.setAdapter(adapter);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(dashboard.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);
    }
}