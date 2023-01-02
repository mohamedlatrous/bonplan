package com.example.bonplan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class categorie extends AppCompatActivity {
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.add:
                Intent intent = new Intent(categorie.this, addproduct.class);
                startActivity(intent);
                return true;
            case R.id.logout:
                Intent intent1 = new Intent(categorie.this, MainActivity.class);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void Vehicles(View view) {
        Intent intent = new Intent(categorie.this, dashboard.class);
        intent.putExtra("cat", "Vehicles");
        startActivity(intent);
    }
    public void Building(View view) {
        Intent intent = new Intent(categorie.this, dashboard.class);
        intent.putExtra("cat","Building");
        startActivity(intent);
    }
    public void Multimedia(View view) {
        Intent intent = new Intent(categorie.this, dashboard.class);
        intent.putExtra("cat","Multimedia");
        startActivity(intent);
    }
    public void Home(View view) {
        Intent intent = new Intent(categorie.this, dashboard.class);
        intent.putExtra("cat","Home/Garden");
        startActivity(intent);
    }
    public void Hobbies(View view) {
        Intent intent = new Intent(categorie.this, dashboard.class);
        intent.putExtra("cat","Hobbies");
        startActivity(intent);
    }
    public void Clothing(View view) {
        Intent intent = new Intent(categorie.this, dashboard.class);
        intent.putExtra("cat","Clothing");
        startActivity(intent);
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorie);

    }
}